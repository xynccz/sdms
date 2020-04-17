package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.config.SdmsLog;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.CustomerArchives;
import com.honest.sdms.system.entity.Customers;
import com.honest.sdms.system.service.ICustomersService;

/**
 * 客户关系管理
 * @author beisi
 *
 */
@RequestMapping("/customersManager")
@RestController
public class CustomersController {
	@Autowired
	private ICustomersService customersService;

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<Customers> search(Customers cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<Customers> pageInfo = customersService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}
	
	/**
	 * 新增和更新客户信息                                                                                                                                                          
	 * @throws HSException 
	 * @throws CustomException 
	 */
	@SdmsLog(value = "新增或修改客户信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/saveCustomer",method={RequestMethod.POST},produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<String> saveCustomer(Customers customer) throws HSException{
		customersService.saveOrUpdateCustomer(customer);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@SdmsLog(value = "新增或修改客户档案信息")
	@RequestMapping(value="/saveCustomerArchive",method={RequestMethod.POST},produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<String> saveCustomerArchive(@RequestBody List<CustomerArchives> customerArchives) throws HSException{
		customersService.saveCustomerArchives(customerArchives);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@RequestMapping("/getCustomerArchivesByCustomerId")
	public @ResponseBody List<CustomerArchives> getCustomerArchivesByCustomerId(Long customerId){
		return customersService.getCustomerArchivesByCustomerId(customerId);
	}
}
