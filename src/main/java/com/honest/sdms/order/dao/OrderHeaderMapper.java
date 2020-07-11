package com.honest.sdms.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.order.entity.OrderHeader;
import com.honest.sdms.system.dao.IBaseMapper;

public interface OrderHeaderMapper extends IBaseMapper<OrderHeader, String>{
	List<OrderHeader> getOrderHeadsByHeaderIds(@Param("headerIds")String[] headerIds, @Param("organizationId") Long organizationId);
}