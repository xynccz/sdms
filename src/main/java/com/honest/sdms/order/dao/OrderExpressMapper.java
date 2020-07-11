package com.honest.sdms.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.order.entity.OrderExpress;
import com.honest.sdms.system.dao.IBaseMapper;

public interface OrderExpressMapper extends IBaseMapper<OrderExpress, Long>{

	abstract List<OrderExpress> findOrderExpressByHeadIds(@Param("headerIds") String[] headerIds, @Param("organizationId") Long organizationId);
	
	abstract void updateOrderExpressByHeadIds(@Param("expressCompanyId") Long expressCompanyId, @Param("headerIds") String[] headerIds, @Param("organizationId") Long organizationId);
}