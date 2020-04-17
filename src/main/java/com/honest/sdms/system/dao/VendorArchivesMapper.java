package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.VendorArchives;

public interface VendorArchivesMapper extends IBaseMapper<VendorArchives, Long>{

	List<VendorArchives> getVendorArchiveListByHouseId(@Param("vendorId")Long vendorId, @Param("vendorWarehouseId")Long vendorWarehouseId, @Param("organizationId") Long organizationId);
	
	List<VendorArchives> getVendorArchiveListByVendorId(@Param("vendorId")Long vendorId, @Param("organizationId") Long organizationId);
}