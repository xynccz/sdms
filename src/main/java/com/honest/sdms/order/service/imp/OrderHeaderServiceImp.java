package com.honest.sdms.order.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.honest.sdms.Constants;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.dao.OrderDetailMapper;
import com.honest.sdms.order.dao.OrderHeaderMapper;
import com.honest.sdms.order.entity.OrderDetail;
import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderExpressService;
import com.honest.sdms.order.service.IOrderHeaderService;
import com.honest.sdms.order.service.imp.promote.OrderModleDataListener;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.CustomerArchives;
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;
import com.honest.sdms.system.entity.Customers;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.entity.ErrorDataLog;
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.system.service.ICustomerOrderExcelConfigService;
import com.honest.sdms.system.service.ICustomersService;
import com.honest.sdms.system.service.IDownloadRecordsService;
import com.honest.sdms.system.service.IErrorDataLogService;
import com.honest.sdms.system.service.IItemSpecificService;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.tools.DateTimeUtil;
import com.honest.sdms.tools.StringUtil;

/**
 * 订单头服务处理类
 * @author beisi
 */
@Service
public class OrderHeaderServiceImp extends BaseServiceImp<OrderHeader, String> implements IOrderHeaderService{
	private static final Logger logger = LoggerFactory.getLogger(OrderHeaderServiceImp.class);
	
	private OrderHeaderMapper orderHeaderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private ICustomerOrderExcelConfigService customerOrderExcelConfigService;
	@Autowired
	private IItemSpecificService itemSpecificService;
	@Autowired
	private IOrderExpressService orderExpressService;
	@Autowired
	private IDownloadRecordsService downloadRecordsService;
	@Autowired
	private IErrorDataLogService errorDataLogService;
	@Autowired
	private ICustomersService customersService;
	
	@Autowired
	@Qualifier("orderHeaderMapper")
	@Override
	public void setBaseDao(IBaseMapper<OrderHeader, String> baseMapper) {
		this.baseMapper = baseMapper;
		orderHeaderMapper = (OrderHeaderMapper)baseMapper;
	}

	@Override
	public List<OrderHeader> getOrderHeadsByHeaderIds(String[] headerIds) {
		return orderHeaderMapper.getOrderHeadsByHeaderIds(headerIds,Constants.getCurrentOrganizationId());
	}
	
	@Override
	public ItemSpecific selectItemSpecificById(Long id) {
		return itemSpecificService.selectByPrimaryKey(id);
	}
	
	@Override
	public void updateOrderHeaders(List<OrderHeader> orderHeaders) throws HSException {
		orderHeaderMapper.updateList(orderHeaders);
	}

	@Override
	public void updateOrderDetails(List<OrderDetail> orderDetails) throws HSException {
		orderDetailMapper.updateList(orderDetails);
	}

	@Override
	public PageInfo<OrderHeader> findByCondWithPage(OrderHeader cond, String sortName, String sortOrder, int pageNum, int pageSize) {
		String selectData = cond.getSelectDatas();
		if(!StringUtil.isNullOrEmpty(selectData)){
			selectData = StringUtil.replace(selectData, new String[] {"[","]"}, new String[] {"",""});
			String[] rangeDatas = Constants.SPLIT.split(selectData);
			cond.setCreatedDateStart(new Date(Long.parseLong(rangeDatas[0])));
			cond.setCreatedDateEnd(new Date(Long.parseLong(rangeDatas[1])));
		}
		return super.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
	}
	
	/**
	 * 批量保存订单信息，包括订单头，订单行及订单物流信息
	 */
	@Override
	public void saveOrderInfos(List<OrderHeader> orderHeaders, List<OrderDetail> orderDetails,
			List<OrderExpress> orderExpress) throws HSException {
		
		//1、批量保存订单头信息
		saveList(orderHeaders);
		//2、批量保存订单行信息
		if(orderDetails != null && orderDetails.size() > 0){
			for(OrderDetail detail : orderDetails){
				detail.setOrganizationId(Constants.getCurrentOrganizationId());
				detail.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
				detail.setLastUpdatedBy(detail.getCreatedBy());
				detail.setCreatedDate(new DateTimeUtil().toTimestamp());
				detail.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
			}
			orderDetailMapper.saveList(orderDetails);
		}
		//3、批量保存订单物流信息
		orderExpressService.saveList(orderExpress);
	}

	/**
	 * 解析订单,后期可以优化为多线程解析订单
	 * @param file 上传附件
	 * @param shopCode 商家类型
	 */
	@Override
	public APIResponse<String> saveOrdersFromFiles(List<DownloadRecords> records) {
		APIResponse<String> result = new APIResponse<String>(ResultStatus.OK);
		StringBuilder message = new StringBuilder();
		if(records != null && records.size() > 0){
			for(DownloadRecords record : records){
				if(Constants.Status.P.equals(record.getStatus())){
					saveOrderFile(record,message);
				}
			}
		}
		result.setMsg(message.toString());
		return result;
	}

	/**
	 * 解析订单附件
	 * @param record 文件记录表
	 * @param message 返回系统信息
	 */
	private void saveOrderFile(DownloadRecords record, StringBuilder message) {
		try{
			//获取客户信息
			Customers customer = customersService.selectByPrimaryKey(record.getCustomerId());
			//1、对订单字段配置数据进行封装
			Map<String,CustomerOrderExcelConfig> relateMap = new HashMap<String, CustomerOrderExcelConfig>();
			List<CustomerOrderExcelConfig> list = customerOrderExcelConfigService.findCustomerOrderConfigByCustomerId(record.getCustomerId(), Constants.INPUT);
			if(list == null || list.size() == 0){
				throw new HSException("店铺:'"+customer.getCustomerName()+"'未查询到订单字段配置，请检查");
			}
			for(CustomerOrderExcelConfig config : list){
				String codeDesc = config.getCodeDesc();
				relateMap.put(codeDesc, config);
			}
			
			//2、对订单信息进行解析
			StringBuilder returnMsg = new StringBuilder();
			record.setCustomerName(customer.getCustomerName());
			
			//3、封装当前客户档案信息
			List<CustomerArchives> archivesList = customersService.getCustomerArchivesByCustomerId(record.getCustomerId());
			Map<String, CustomerArchives> customerArchiveMap = new HashMap<String, CustomerArchives>();
			if(archivesList != null && archivesList.size() > 0) {
				archivesList.forEach(item->{
					customerArchiveMap.put(item.getCustomerSpecificCode(), item);
				});
			}
			EasyExcel.read(record.getFilePath() + File.separator + record.getFileName(), new OrderModleDataListener(this, returnMsg, customerArchiveMap, relateMap, record)).sheet().doRead();
			//4、记录操作
			record.setStatus(Constants.Status.Y);
			record.setDescription(returnMsg.toString());
			
			//拼接程序解析返回的信息
			message.append(record.getFileName()).append(record.getDescription()).append("; ");
		}catch(Exception e){
			record.setStatus(Constants.Status.E);
			record.setDescription(e.getMessage());
			message.append(record.getFileName() + "导入异常，"+e.getMessage()).append(";");
			
			StringUtil.writeStackTraceToLog(logger, e);
		}finally {
			record.setOperationDate(new Date());
			downloadRecordsService.updateByPrimaryKey(record);
		}
	}

	@Override
	public void saveErrorLog(ErrorDataLog errorLog) {
		errorDataLogService.insert(errorLog);
	}

	/**
	 * 重新匹配客户失效订单，使用场景：客户导入订单的时候未设置产品规则信息导致订单匹配失败，
	 * 用户在客户档案信息中维护好规则信息后，点击刷新订单后会请求此方法
	 */
	@Override
	public String updateMatchOrdersByCustomerId(Long customerId) throws HSException {
		String msg = null;
		try{
			//获取当前用户匹配失败的订单
			OrderHeader cond = new OrderHeader();
			cond.setCustomerId(customerId);
			cond.setIsValid(Constants.Status.N);
			List<OrderHeader> orderHeads = findByCond(cond);
			if(orderHeads != null && orderHeads.size() > 0){
				//获取当前客户档案信息
				List<CustomerArchives> archives = customersService.getCustomerArchivesByCustomerId(customerId);
				if(archives != null && archives.size() > 0){
					Map<Long, ItemSpecific> itemSpecificMap = new HashMap<Long, ItemSpecific>();
					Map<String, CustomerArchives> archiveMap = new HashMap<String, CustomerArchives>();
					
					archives.forEach(archive->{
						//封装产品规格信息
						Long itemSpecificId = archive.getItemSpecificId();
						ItemSpecific itemSpecific = null;
						if(itemSpecificMap.containsKey(itemSpecificId)){
							itemSpecific = itemSpecificMap.get(itemSpecificId);
						}else {
							itemSpecific = selectItemSpecificById(itemSpecificId);
							itemSpecificMap.put(itemSpecificId, itemSpecific);
						}
						//封装产品规格字典信息
						archiveMap.put(archive.getCustomerSpecificCode(), archive);
					});
					
					List<OrderHeader> updateHeadList = new ArrayList<OrderHeader>();
					List<OrderDetail> updateDetailList = new ArrayList<OrderDetail>();
					orderHeads.forEach(orderHead->{
						String customerItemSpec = orderHead.getCustomerItemSpecific();
						if(archiveMap.containsKey(customerItemSpec)){
							CustomerArchives archive = archiveMap.get(customerItemSpec);
							Long itemSpecificId = archive.getItemSpecificId();
							ItemSpecific itemSpecific = itemSpecificMap.get(itemSpecificId);
							
							orderHead.setItemSpecificId(itemSpecificId);
							orderHead.setIsValid(Constants.Status.Y);
							orderHead.setRemarks(null);
							orderHead.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
							orderHead.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
							
							List<OrderDetail> orderDetails = orderDetailMapper.findOrderDetailsByHeadId(orderHead.getHeaderId(),Constants.getCurrentOrganizationId());
							orderDetails.forEach(orderDetail->{
								orderDetail.setItem(itemSpecific.getItem());
								orderDetail.setItemId(itemSpecific.getItemId());
								orderDetail.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
								orderDetail.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
								updateDetailList.add(orderDetail);
							});
							updateHeadList.add(orderHead);
						}
					});
					this.updateOrderHeaders(updateHeadList);
					this.updateOrderDetails(updateDetailList);
					msg = "订单匹配成功，共成功匹配"+updateHeadList.size();
				}
			}
		}catch(Exception e){
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("刷新订单信息失败，"+ e.getMessage());
		}
		return msg;
	}

	/**
	 * 订单审核,分两种情况：
	 * 1、用户选择指定行进行审核，那么HeaderId不为空，为一组id
	 * 2、用户没有选择指定行进行审核，那么系统会判断为按界面中查询条件查询到的所有订单进行审核，那么HeaderId为空
	 */
	@Override
	public String updateReviewOrders(OrderHeader record) throws HSException {
		List<OrderHeader> list = null;
		List<String> headerIdList = new ArrayList<String>();
		try{
			/*
			 * 当审核通过时对订单的仓库进行非空校验,快递公司可以暂时不用强制填写，可填也可不填
			 * 仓库管理员可以出库的时候再指定快递公司也可以，
			 */
			if(Constants.Status.Y.equals(record.getIsValidSet())){
				if(record.getWarehouseId() == null) {
					throw new HSException("仓库不允许为空！");
				}
			}
			
			String headerId = record.getHeaderIds();
			//情况1,选中指定订单行进行审核
			if(!StringUtil.isNullOrEmpty(headerId)){
				String[] headerIds = StringUtil.stringToArray(headerId);
				list = getOrderHeadsByHeaderIds(headerIds);
			}
			//情况2，全部审核，按查询条件查出所有订单进行审核
			else{
				list = findByCond(record);
			}
			if(list != null){
				list.forEach(item->{
					//如果用户驳回订单，那么设置isValid=N
					item.setIsValid(record.getIsValidSet());
					item.setRemarks(record.getRemarks());
					item.setIsReviewed(Constants.Status.Y);
					item.setOrderStatus(Constants.OrderStatus.ALREADY_REVIEWED);
					item.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
					item.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					Constants.setOrderOperatorLog(item, "订单审核");
					
					headerIdList.add(item.getHeaderId());
				});
				updateOrderHeaders(list);
				
				//只有审核通过的订单前台才分配仓库和快递公司
				if(Constants.Status.Y.equals(record.getIsValidSet())) {
					String[] headerIds = headerIdList.toArray(new String[headerIdList.size()]);
					//订单明细表存放仓库信息
					orderDetailMapper.updateOrderDetailsByHeadIds(record.getWarehouseId(), headerIds,Constants.getCurrentOrganizationId());
					//订单物流表存放快递信息
					orderExpressService.updateOrderExpressByHeadIds(record.getExpressCompanyId(), headerIds);
				}
			}
		}catch(Exception e){
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("订单审核异常，"+e.getMessage());
		}
		return "共成功审核"+list.size()+"条订单";
	}
}
