package com.honest.sdms.order.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.ExpressCustomerParameter;
import com.honest.sdms.system.entity.VendorWarehouse;
import com.honest.sdms.system.service.IBaseService;

public interface IExpressCustomerParameterService extends IBaseService<ExpressCustomerParameter, Long>{

	abstract List<VendorWarehouse> getWareHouseByExpressId(Long expressId);
	
	abstract void save(ExpressCustomerParameter obj);
	
	abstract void saveOrUpdate(ExpressCustomerParameter obj)throws HSException;
}
