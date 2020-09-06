package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.ExpressCustomerParameter;
import com.honest.sdms.order.service.IExpressCustomerParameterService;

@RestController
@RequestMapping("/expressCustomerConfigManager")
public class ExpressCustomerConfigController {
	@Autowired
	private IExpressCustomerParameterService expressCustomerParameterService;

	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody List<ExpressCustomerParameter> search(ExpressCustomerParameter cond){
		List<ExpressCustomerParameter> result = expressCustomerParameterService.findByCond(cond);
		return result;
	}
	
	@RequestMapping(value="/saveExpressConfig", method = RequestMethod.POST)
	public @ResponseBody APIResponse<String> saveExpressConfig(ExpressCustomerParameter config)throws HSException{
		expressCustomerParameterService.saveOrUpdate(config);
		return new APIResponse<String>(ResultStatus.OK);
	}
}
