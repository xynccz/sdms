package com.honest.sdms.order.service;

import java.util.List;

import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.OrderDetail;
import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.entity.ErrorDataLog;
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.system.service.IBaseService;

public interface IOrderHeaderService extends IBaseService<OrderHeader, String>{
	
	/**
	 * 解析订单
	 * @param file 上传附件
	 * @param shopCode 商家类型
	 */
	abstract APIResponse<String> saveOrdersFromFiles(List<DownloadRecords> records);
	
	abstract List<OrderHeader> getOrderHeadsByHeaderIds(String[] headerIds);
	
	abstract void saveOrderInfos(List<OrderHeader> orderHeaders, List<OrderDetail> orderDetails, List<OrderExpress> orderExpress)throws HSException;
	
	abstract ItemSpecific selectItemSpecificById(Long id);
	
	abstract void saveErrorLog(ErrorDataLog errorLog);
	
	abstract String updateMatchOrdersByCustomerId(Long customerId)throws HSException;
	
	abstract void updateOrderHeaders(List<OrderHeader> orderHeaders)throws HSException;
	
	abstract void updateOrderDetails(List<OrderDetail> orderDetails)throws HSException;
	
	abstract String updateReviewOrders(OrderHeader record)throws HSException;
}
