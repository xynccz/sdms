package com.honest.sdms.order.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.order.dao.OrderHeaderMapper;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderHeaderService;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;

/**
 * 订单头服务处理类
 * @author beisi
 */
@Service
public class OrderHeaderServiceImp extends BaseServiceImp<OrderHeader, Long> implements IOrderHeaderService{

	private OrderHeaderMapper orderHeaderMapper;
	
	@Autowired
	@Qualifier("orderHeaderMapper")
	@Override
	public void setBaseDao(IBaseMapper<OrderHeader, Long> baseMapper) {
		this.baseMapper = baseMapper;
		orderHeaderMapper = (OrderHeaderMapper)baseMapper;
		
	}

}
