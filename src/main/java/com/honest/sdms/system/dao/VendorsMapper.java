package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.Vendors;

public interface VendorsMapper extends IBaseMapper<Vendors, Long>{

	List<Vendors> getVendorList(@Param("vendorName") String vendorName, @Param("organizationId") Long organizationId);
}