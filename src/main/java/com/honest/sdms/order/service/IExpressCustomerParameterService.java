package com.honest.sdms.order.service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.ExpressCustomerParameter;
import com.honest.sdms.system.service.IBaseService;

public interface IExpressCustomerParameterService extends IBaseService<ExpressCustomerParameter, Long>{

	abstract void save(ExpressCustomerParameter obj);
	
	abstract void saveOrUpdate(ExpressCustomerParameter obj)throws HSException;
}
