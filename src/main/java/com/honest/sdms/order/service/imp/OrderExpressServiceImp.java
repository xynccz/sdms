package com.honest.sdms.order.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.order.dao.OrderExpressMapper;
import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.order.service.IOrderExpressService;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;

@Service
public class OrderExpressServiceImp extends BaseServiceImp<OrderExpress, Long> implements IOrderExpressService{

	private OrderExpressMapper orderExpressMapper;
	
	@Autowired
	@Qualifier("orderExpressMapper")
	@Override
	public void setBaseDao(IBaseMapper<OrderExpress, Long> baseMapper) {
		this.baseMapper = baseMapper;
		orderExpressMapper = (OrderExpressMapper)baseMapper;
	}

	@Override
	public void saveList(List<OrderExpress> list) {
		if(list != null && list.size() > 0){
			for(OrderExpress order : list) {
				orderExpressMapper.insert(order);
			}
		}
		
	}
	
	

}
