package com.honest.sdms.transaction.controller;

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
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.system.service.IItemSpecificService;
import com.honest.sdms.transaction.entity.Item;
import com.honest.sdms.transaction.service.IItemService;

@RequestMapping("/itemManagement")
@RestController
public class ItemSearchController {
	@Autowired
	private IItemService itemService;
	@Autowired
	private IItemSpecificService itemSpecificService;

	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody PageInfo<Item> searchRoles(Item cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<Item> result = itemService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return result;
	}
	
	@RequestMapping(value = "/searchItemSpecific",method = RequestMethod.POST)
	public @ResponseBody List<ItemSpecific> searchItemSpecific(ItemSpecific cond){
		List<ItemSpecific> result = itemSpecificService.findByCond(cond);
		return result;
	}
	
	@SdmsLog(value = "新增产品")
	@RequestMapping(value = "/saveItem",method = RequestMethod.POST)
	public @ResponseBody APIResponse<String> saveItem(Item item)throws HSException{
		itemService.insert(item);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@SdmsLog(value = "更新产品")
	@RequestMapping(value = "/updateItem",method = RequestMethod.POST)
	public @ResponseBody APIResponse<String> updateItem(Item item)throws HSException{
		itemService.updateByPrimaryKey(item);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@SdmsLog(value = "修改产品规格信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/saveItemSpecific",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> saveItemSpecific(@RequestBody List<ItemSpecific> itemSpecifics)throws HSException{
		itemSpecificService.saveOrUpdateItemSpecificConfigs(itemSpecifics);
		return new APIResponse<String>(ResultStatus.OK);
	}
}
