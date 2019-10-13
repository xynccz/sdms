package com.honest.sdms.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.config.SdmsLog;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.transaction.entity.MaterialTransaction;
import com.honest.sdms.transaction.service.IMaterialTransactionService;

/**
 * 交易信息管理控制类
 * @author beisi
 *
 */
@RequestMapping("/transactionManagement")
@RestController
public class MaterialTransController {

	@Autowired
	private IMaterialTransactionService materialTransactionService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<MaterialTransaction> search(MaterialTransaction cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<MaterialTransaction> pageInfo = materialTransactionService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}
	
	@SdmsLog(value = "创建交易信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/createMaterialTransactions",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> createMaterialTransactions(MaterialTransaction cond)throws HSException{
		materialTransactionService.createMaterialTransactions(cond);
		return new APIResponse<String>(ResultStatus.OK);
	}
	        
	@SdmsLog(value = "创建库存信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/createStock",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> createStock(String transactionIds)throws HSException{
		materialTransactionService.createStocks(transactionIds);
		return new APIResponse<String>(ResultStatus.OK);
	}
}
