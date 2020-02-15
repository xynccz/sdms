package com.honest.sdms.order.service.imp;

import java.io.File;
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
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.entity.ErrorDataLog;
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.system.entity.SysDictDatas;
import com.honest.sdms.system.service.ICustomerOrderExcelConfigService;
import com.honest.sdms.system.service.IDownloadRecordsService;
import com.honest.sdms.system.service.IErrorDataLogService;
import com.honest.sdms.system.service.IItemSpecificService;
import com.honest.sdms.system.service.ISysDictDatasService;
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
	private ISysDictDatasService sysDictDatasService;
	@Autowired
	private IErrorDataLogService errorDataLogService;
	
	@Autowired
	@Qualifier("orderHeaderMapper")
	@Override
	public void setBaseDao(IBaseMapper<OrderHeader, String> baseMapper) {
		this.baseMapper = baseMapper;
		orderHeaderMapper = (OrderHeaderMapper)baseMapper;
	}

	@Override
	public ItemSpecific selectItemSpecificById(Long id) {
		return itemSpecificService.selectByPrimaryKey(id);
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
	 * 解析订单
	 * @param file 上传附件
	 * @param shopCode 商家类型
	 */
	@Override
	public APIResponse<String> saveOrdersFromFiles(List<DownloadRecords> records) {
		APIResponse<String> result = new APIResponse<String>(ResultStatus.DEFAULT);
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
			SysDictDatas dictData = sysDictDatasService.selectByPrimaryKey(record.getCustomerId());
			//1、对订单字段配置数据进行封装
			Map<String,CustomerOrderExcelConfig> relateMap = new HashMap<String, CustomerOrderExcelConfig>();
			List<CustomerOrderExcelConfig> list = customerOrderExcelConfigService.findCustomerOrderConfigByCustomerId(record.getCustomerId(), Constants.INPUT);
			if(list == null || list.size() == 0){
				throw new HSException(dictData.getDictDataName()+"未查询到订单字段配置，请检查");
			}
			for(CustomerOrderExcelConfig config : list){
				String codeDesc = config.getCodeDesc();
				relateMap.put(codeDesc, config);
			}
			
			//2、对订单信息进行解析
			StringBuilder returnMsg = new StringBuilder();
			record.setCustomerName(dictData.getDictDataName());
			EasyExcel.read(record.getFilePath() + File.separator + record.getFileName(), new OrderModleDataListener(this, returnMsg, relateMap, record)).sheet().doRead();
			//3、记录操作
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

}
