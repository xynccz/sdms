package com.honest.sdms.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.config.SdmsLog;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.transaction.entity.PoHeader;
import com.honest.sdms.transaction.entity.PoLine;
import com.honest.sdms.transaction.service.IPoHeaderService;
import com.honest.sdms.transaction.service.IPoLineService;

@RequestMapping("/poManager")
@RestController
public class PoController {

	@Autowired
	private IPoHeaderService poHeaderService;
	@Autowired
	private IPoLineService poLineService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<PoHeader> search(PoHeader cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<PoHeader> pageInfo = poHeaderService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}
	                                                                                                           
	@SdmsLog(value = "创建PO信息")  //这里添加了AOP的自定义注解
	@RequestMapping(value="/savePo",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> savePo(PoHeader poHeader,@RequestBody List<PoLine> poLines)throws HSException{
		poHeaderService.savePo(poHeader, poLines);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@RequestMapping(value="/getPoLinesByHeadId", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<PoLine> getPoLinesByHeadId(@RequestParam("headerId") Long headerId) {
		return poLineService.findPoLinesByPoHeaderId(headerId);
	}
	
}
