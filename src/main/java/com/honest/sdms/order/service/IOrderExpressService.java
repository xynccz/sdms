package com.honest.sdms.order.service;

import java.util.List;

import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.system.service.IBaseService;

public interface IOrderExpressService extends IBaseService<OrderExpress, Long>{

	abstract List<OrderExpress> findOrderExpressByHeadIds(String[] headerIds);
	
	/**
	 * 订单匹配分配快递公司
	 * @param expressCompanyId
	 * @param headerIds
	 * @return
	 */
	abstract void updateOrderExpressByHeadIds(Long expressCompanyId, String[] headerIds);
}
