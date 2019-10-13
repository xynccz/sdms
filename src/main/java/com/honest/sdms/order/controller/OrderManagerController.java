package com.honest.sdms.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
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
}
