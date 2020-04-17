package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.Customers;

public interface CustomersMapper extends IBaseMapper<Customers, Long>{

	List<Customers> getCustomerList(@Param("customerName") String customerName, @Param("organizationId") Long organizationId);
}