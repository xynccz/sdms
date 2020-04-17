package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.VendorArchives;
import com.honest.sdms.system.entity.VendorWarehouse;
import com.honest.sdms.system.entity.Vendors;

public interface IVendorsService extends IBaseService<Vendors, Long>{

	abstract List<Vendors> getVendorList(String vendorName);
	
	abstract List<VendorWarehouse> getVendorWarehouseListByVendorId(Long vendorId);
	
	abstract List<VendorArchives> getVendorArchiveListByHouseId(Long vendorId, Long vendorWarehouseId);
	
	abstract List<VendorArchives> getVendorArchiveListByVendorId(Long vendorId);
	
	abstract void saveOrUpdateVendor(Vendors vendor, List<VendorWarehouse> vendorWarhouses)throws HSException;

	abstract void saveVendorArchives(List<VendorArchives> vendorArchives)throws HSException;
}
