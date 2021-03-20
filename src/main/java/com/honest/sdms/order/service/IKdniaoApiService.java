package com.honest.sdms.order.service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.OrderOnline;
import com.honest.sdms.order.entity.OrderTraces;
import com.honest.sdms.order.entity.OrderTrancesReturn;

/**
 * 快递鸟对外接口API
 * @author beisi
 *
 */
public interface IKdniaoApiService {

	abstract OrderTrancesReturn getOrderTraces(OrderTraces orderTraces) throws HSException;
	
	abstract OrderTrancesReturn getOrderTracesUpgrade(OrderTraces orderTraces) throws Exception;

	abstract String orderOnlineByJson(OrderOnline requestData) throws Exception;
	
	abstract String orderTracesSubscribe(String requestData) throws Exception;
}
