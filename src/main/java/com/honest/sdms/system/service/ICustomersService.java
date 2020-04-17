package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.CustomerArchives;
import com.honest.sdms.system.entity.Customers;

import net.sf.json.JSONObject;

public interface ICustomersService extends IBaseService<Customers, Long>{

	abstract List<Customers> getCustomerList(String customerName);
	
	abstract List<CustomerArchives> getCustomerArchivesByCustomerId(Long customerId);
	
	abstract void saveOrUpdateCustomer(Customers customer)throws HSException;
	
	abstract void saveCustomerArchives(List<CustomerArchives> customerArchives)throws HSException;
	
	abstract List<JSONObject> getCustomerOrderFieldList();
	
}
