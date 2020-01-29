package com.honest.sdms.order.service;

import java.util.List;

import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.system.service.IBaseService;

public interface IOrderExpressService extends IBaseService<OrderExpress, Long>{

	abstract void saveList(List<OrderExpress> list);
}
