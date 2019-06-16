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
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.SysDictDatas;
import com.honest.sdms.system.entity.SysDictType;
import com.honest.sdms.system.service.ISysDictDatasService;
import com.honest.sdms.system.service.ISysDictTypeService;

@RequestMapping("/dictManagement")
@RestController
public class SysDictManagerController {

	@Autowired
	private ISysDictTypeService sysDictTypeService;
	@Autowired
	private ISysDictDatasService sysDictDatasService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<SysDictType> searchUsers(SysDictType cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<SysDictType> pageInfo = sysDictTypeService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	} 
	
	@RequestMapping(value="/saveDictDatas",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> saveUser(@RequestBody List<SysDictDatas> dictDatas) throws HSException{
		sysDictDatasService.saveOrUpdateDictDatas(dictDatas);
		return new APIResponse<String>(ResultStatus.OK);
	} 
	
	/**
	 * 新增或更新数据字典
	 * @throws HSException 
	 */
	@RequestMapping(value="/saveDictType",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> saveUser(SysDictType dictType) throws HSException{
		sysDictTypeService.saveOrUpdateDictType(dictType);
		return new APIResponse<String>(ResultStatus.OK);
	} 
}
