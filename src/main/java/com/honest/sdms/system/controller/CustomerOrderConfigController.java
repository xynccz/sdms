package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;
import com.honest.sdms.system.service.ICustomerOrderExcelConfigService;

@RequestMapping("/customerOrderExcelConfig")
@RestController
public class CustomerOrderConfigController {

	@Autowired
	private ICustomerOrderExcelConfigService customerOrderExcelConfigService;
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody List<CustomerOrderExcelConfig> searchRoles(CustomerOrderExcelConfig cond){
		List<CustomerOrderExcelConfig> result = customerOrderExcelConfigService.findByCond(cond);
		return result;
	}
	
	@RequestMapping(value="/saveCustomerOrderConfigs",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> saveCustomerOrderConfigs(@RequestBody List<CustomerOrderExcelConfig> configs) throws HSException{
		customerOrderExcelConfigService.saveOrUpdateCustomerOrderExcelConfigs(configs);
		return new APIResponse<String>(ResultStatus.OK);
	}  
} 
