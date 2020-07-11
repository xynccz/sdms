package com.honest.sdms.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.order.entity.OrderDetail;
import com.honest.sdms.system.dao.IBaseMapper;

public interface OrderDetailMapper extends IBaseMapper<OrderDetail, Long>{

	abstract List<OrderDetail> findOrderDetailsByHeadId(@Param("headerId") String headerId, @Param("organizationId") Long organizationId);
	
	abstract List<OrderDetail> findOrderDetailsByHeadIds(@Param("headerIds") String[] headerIds, @Param("organizationId") Long organizationId);
	
	abstract void updateOrderDetailsByHeadIds(@Param("warehouseId") Long warehouseId, @Param("headerIds") String[] headerIds, @Param("organizationId") Long organizationId);
	
}