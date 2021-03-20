package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.VendorWarehouse;

public interface VendorWarehouseMapper extends IBaseMapper<VendorWarehouse, Long>{

	List<VendorWarehouse> getWareHouseByExpressId(@Param("expressId") Long expressId, @Param("organizationId")Long organizationId);
	List<VendorWarehouse> getVendorWarehouseListByVendorId(@Param("vendorId")Long vendorId, @Param("organizationId")Long organizationId);
}