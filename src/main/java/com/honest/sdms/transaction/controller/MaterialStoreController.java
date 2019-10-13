package com.honest.sdms.transaction.controller;

import org.apache.commons.beanutils.ConvertUtils;
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
import com.honest.sdms.tools.StringUtil;
import com.honest.sdms.transaction.entity.MaterialStoreage;
import com.honest.sdms.transaction.service.IMaterialStorageService;

/**
 * 库存管理控制类
 */
@RequestMapping("/storeManagement")
@RestController
public class MaterialStoreController {

	@Autowired
	private IMaterialStorageService materialStorageService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<MaterialStoreage> search(MaterialStoreage cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<MaterialStoreage> pageInfo = materialStorageService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}
	
	@SdmsLog(value = "出库操作")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/outStock",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> createStock(String storeIds)throws HSException{
		String[] strArr = StringUtil.stringToArray(storeIds);
		Long[] _transactionIds = (Long[]) ConvertUtils.convert(strArr,Long.class);
		materialStorageService.outStocks(storeIds);
		return new APIResponse<String>(ResultStatus.OK);
	}
}
