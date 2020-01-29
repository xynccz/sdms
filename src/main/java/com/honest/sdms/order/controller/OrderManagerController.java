package com.honest.sdms.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.order.service.IOrderHeaderService;

/**
 * 订单管理控制类
 * @author beisi
 */
@RequestMapping("/orderManagement")
@RestController
public class OrderManagerController {

	@Autowired
	private IOrderHeaderService orderHeaderService;
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody PageInfo<OrderHeader> search(OrderHeader cond,int pageNum,int pageSize,String sortName, String sortOrder){
		PageInfo<OrderHeader> pageInfo = orderHeaderService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		return pageInfo;
	}

	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public APIResponse<String> uploadOrder(@RequestParam("file")MultipartFile[]  file, @RequestParam("shopCode")String shopCode) {
		orderHeaderService.saveOrdersFromFiles(file, shopCode);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
}
