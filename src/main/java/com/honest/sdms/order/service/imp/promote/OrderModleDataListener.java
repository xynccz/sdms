package com.honest.sdms.order.service.imp.promote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.honest.sdms.Constants;
import com.honest.sdms.order.entity.OrderDetail;
import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderHeaderService;
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.entity.ErrorDataLog;
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.tools.StringUtil;

import net.sf.json.JSONObject;

/**
 * Excel格式订单附件数据解析监听类
 * @author beisi
 *
 */
public class OrderModleDataListener extends AnalysisEventListener<Map<Integer, String>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderModleDataListener.class);
    private IOrderHeaderService orderHeaderService;
    private DownloadRecords record;
    private Map<Integer, String> headMap;//文件头信息
    private Map<String,JSONObject> contrastMap;//客户给定值与系统设定值对应关系
    private Map<String,CustomerOrderExcelConfig> relateMap;//表字段与文件头字段对应关系
    private StringBuilder message;//记录解析异常信息
    private Long totalCount = -1L, errorCount = 0L;
    
	/**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 200;//每100条数据保存一次
    private List<OrderHeader> headList;//订单头信息
    private List<OrderDetail> detailList;//订单行信息
    private List<OrderExpress> expressList;//订单物流信息
    
    public OrderModleDataListener(IOrderHeaderService orderHeaderService, StringBuilder message, Map<String,CustomerOrderExcelConfig> relateMap, DownloadRecords record){
    	this.relateMap = relateMap;
    	this.message = message;
    	this.orderHeaderService = orderHeaderService;
    	this.record = record;
    	
    	headList = new ArrayList<OrderHeader>();
        detailList = new ArrayList<OrderDetail>();
        expressList = new ArrayList<OrderExpress>();
        contrastMap = new HashMap<String, JSONObject>();
    }

    /**
     * 解析单行excel数据
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        //封装表字段与excel值对应关系
        Map<String, String> dataMap = convertData(data);  
        //开始保存数据到库
        if(dataMap.size() > 0){
        	boolean isSucc = true;
        	//订单头信息
        	OrderHeader head = new OrderHeader();
        	//订单明细信息
        	OrderDetail detail = new OrderDetail();
        	//订单物流信息
        	OrderExpress express = new OrderExpress();
        	
        	try {
				BeanUtils.populate(head, dataMap);
				BeanUtils.populate(detail, dataMap);
				BeanUtils.populate(express, dataMap);
				head.setRecordId(record.getId());
				head.setIsValid(Constants.Status.Y);
				
				//重要，获取订单产品规格，包括是什么产品，规格多少
				Long itemSpecificId = detail.getItemSpecificId();
				ItemSpecific itemSpecific = orderHeaderService.selectItemSpecificById(itemSpecificId);
				if(itemSpecific == null){
					head.setIsValid(Constants.Status.N);
					head.setRemarks("未找到商品规格配置信息，请检查");
				}else{
					detail.setItem(itemSpecific.getItem());
					detail.setItemId(itemSpecific.getItemId());
				}
				
				//手动生成订单头主键ID
				String headerId = UUID.randomUUID().toString().replace("-", "");
				head.setHeaderId(headerId);
				head.setCustomerId(record.getCustomerId());//店铺ID
				head.setCustomerName(record.getCustomerName());//店铺名称
				
				detail.setHeaderId(head.getHeaderId());
				express.setHeaderId(head.getHeaderId());
				
				//对导入订单数据进行数据准确性校验及一些自定义规则校验
				verifyOrderData(head, detail, express);
				
			}catch(Exception e){
				errorCount++;
				LOGGER.error("解析到一条数据异常:{},{}", data, context.readRowHolder().getRowIndex());
				head.setIsValid(Constants.Status.N);
				head.setRemarks(data + e.getMessage());
				isSucc = false;
			}finally{
				headList.add(head);
				if(isSucc){
					detailList.add(detail);
					expressList.add(express);
				}
				
				if (headList.size() >= BATCH_COUNT){
					try{
						saveData();
			            headList.clear();
			            detailList.clear();
			            expressList.clear();
			    	}catch(Exception e){
			    		StringUtil.writeStackTraceToLog(LOGGER, e);
			    		errorCount += headList.size();
			    		LOGGER.error("******{}条数据存储数据库失败,{}********",headList.size(),e.getMessage());
			    	}
		        }
			}
        }
    }

    /**
     * 解析动作最后结束入口
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    	try{
    		saveData();
    	}catch(Exception e) {
    		errorCount += headList.size();
    		LOGGER.error("******{}条数据存储数据库失败,{}********",headList.size(),e.getMessage());
    	}
    	
    	message.append("共").append(totalCount).append("条数据，其中解析成功")
    	.append(totalCount-errorCount).append("条,解析失败").append(errorCount).append("条");
        LOGGER.info("******所有数据存储数据库完成！********");
    }

    /**
	 * 这里会返回头信息
	 */
	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		this.headMap = headMap;
	    LOGGER.info("解析到一条头数据:{}", headMap);
	}
	
	@Override
	public boolean hasNext(AnalysisContext context) {
		totalCount++;
		return true;
	}

	/**
	 * 在转换异常（和业务无关） 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
	 */
	@Override
	public void onException(Exception exception, AnalysisContext context) {
	    LOGGER.error("解析第{}行数据失败，{}，但是继续解析下一行", context.readRowHolder().getRowIndex(), exception.getMessage());
	    errorCount++;
	    
	    ErrorDataLog errorLog = new ErrorDataLog();
	    errorLog.setSourceId(record.getId());
	    errorLog.setData(exception.getMessage());
	    errorLog.setRemarks("解析第"+context.readRowHolder().getRowIndex()+"行异常");
	    
	    // 如果是某一个单元格的转换异常 能获取到具体行号
	    // 如果要获取头的信息 配合invokeHeadMap使用
	    if (exception instanceof ExcelDataConvertException) {
	        ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
	        errorLog.setRemarks(errorLog.getRemarks() + "第"+excelDataConvertException.getColumnIndex()+"列异常");
	    }
	    
	    orderHeaderService.saveErrorLog(errorLog);
	}
	
	/**
	 * 对用户导入的订单数据进行合法性校验或自定义规则校验，比如：电话号码是否有效，或指定的哪些省份不配送等等
	 * @param head
	 * @param detail
	 * @param express
	 */
	private void verifyOrderData(OrderHeader head, OrderDetail detail, OrderExpress express){
		//如果校验不通过就设置order head头表的is_valid=N
	}
	
	/**
     * 封装数据
     * @param data
     * @return
     */
	private Map<String, String> convertData(Map<Integer, String> data) {
		Map<String, String> dataMap = new HashMap<String, String>();
        for(Iterator<Integer> it = headMap.keySet().iterator();it.hasNext();){
        	Integer key = it.next();
        	String headName = headMap.get(key);//excel表头列名
        	String value = data.get(key);//excel表头对应的值
        	CustomerOrderExcelConfig excelConfig = relateMap.get(headName);
        	if(excelConfig != null){
        		String codeField = excelConfig.getCodeField();
        		if(!contrastMap.containsKey(codeField)){
        			String relatStr = excelConfig.getCodeRelation();
            		if(!StringUtil.isNullOrEmpty(relatStr)){
            			contrastMap.put(codeField, JSONObject.fromObject(relatStr));
            		}
        		}
        		
        		//如果有设置值对应关系就用指定值替换
        		JSONObject json = contrastMap.get(codeField);
        		if(json != null && json.containsKey(value)){
        			dataMap.put(codeField, json.getString(value));
        		}else{
        			dataMap.put(codeField, value);
        		}
        		//这里原始的订单规格信息保存在订单行中
        		if("itemSpecificId".equals(codeField)){
        			dataMap.put("description", value);
        		}
        	}
        }
		return dataMap;
	}
	
    /**
     * 数据保存数据库
     * @throws Exception 
     */
    private void saveData() throws Exception {
        LOGGER.info("{}条数据，开始存储数据库！", headList.size());
        orderHeaderService.saveOrderInfos(headList, detailList, expressList);
        LOGGER.info("{}条数据，存储数据库成功！", headList.size());
    }
    
    public static void main(String[] args) {
    	OrderHeader bean = new OrderHeader();
    	Map<String, String> properties = new HashMap<String, String>();
    	properties.put("orderStatus", "N");
    	properties.put("orderAmount", "15");
    	properties.put("consigneeAddress", "中华网人玫瑰酥");
    	properties.put("expressCreateTime", "2020-12-12 23:22:22");
    	
    	OrderExpress order = new OrderExpress();
    	try {
			BeanUtils.populate(bean, properties);
			BeanUtils.populate(order, properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println(bean.getOrderStatus());
    	System.out.println(bean.getOrderAmount()+"==="+bean.getOrderCount());
    	System.out.println(order.getConsigneeAddress());
    	System.out.println(order.getExpressCreateTime());
    	
    	UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-", ""));
    }

}
