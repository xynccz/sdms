package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.CustomerArchives;

public interface CustomerArchivesMapper extends IBaseMapper<CustomerArchives, Long>{

	abstract List<CustomerArchives> getCustomerArchivesByCustomerId(@Param("customerId")Long customerId, @Param("organizationId")Long organizationId);
}