package com.honest.sdms.order.service;

import org.springframework.web.multipart.MultipartFile;

import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.system.service.IBaseService;

public interface IOrderHeaderService extends IBaseService<OrderHeader, Long>{
	
	/**
	 * 解析订单
	 * @param file 上传附件
	 * @param shopCode 商家类型
	 */
	abstract APIResponse<String> saveOrdersFromFiles(MultipartFile[]  file, String shopCode);

}
