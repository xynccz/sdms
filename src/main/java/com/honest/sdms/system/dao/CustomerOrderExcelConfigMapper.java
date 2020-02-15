package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.CustomerOrderExcelConfig;

public interface CustomerOrderExcelConfigMapper extends IBaseMapper<CustomerOrderExcelConfig, Long>{
	
	abstract List<CustomerOrderExcelConfig> findCustomerOrderConfigByCustomerId(@Param("customerId")Long customerId,@Param("operateType") String operateType);

}