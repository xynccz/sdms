package com.honest.sdms.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.system.entity.SysLog;
import com.honest.sdms.system.service.ISysLogService;

@RequestMapping("/logManagement")
@RestController
public class SysLogController {

	@Autowired
	private ISysLogService sysLogService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<SysLog> search(SysLog cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<SysLog> pageInfo = sysLogService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	} 
}
