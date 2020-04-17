package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.VendorArchivesMapper;
import com.honest.sdms.system.dao.VendorWarehouseMapper;
import com.honest.sdms.system.dao.VendorsMapper;
import com.honest.sdms.system.entity.VendorArchives;
import com.honest.sdms.system.entity.VendorWarehouse;
import com.honest.sdms.system.entity.Vendors;
import com.honest.sdms.system.service.IVendorsService;
import com.honest.sdms.tools.DateTimeUtil;

/**
 * 供应商服务类
 * @author beisi
 *
 */
@Service
public class VendorsServiceImp extends BaseServiceImp<Vendors, Long> implements IVendorsService{
	private VendorsMapper vendorsMapper;
	@Autowired
	private VendorArchivesMapper vendorArchivesMapper;
	@Autowired
	private VendorWarehouseMapper vendorWarehouseMapper;

	@Autowired
	@Qualifier("vendorsMapper")
	@Override
	public void setBaseDao(IBaseMapper<Vendors, Long> baseMapper) {
		this.baseMapper = baseMapper;
		vendorsMapper = (VendorsMapper)baseMapper;
	}

	@Override
	public List<Vendors> getVendorList(String vendorName) {
		return vendorsMapper.getVendorList(vendorName, Constants.getCurrentSysUser().getOrganizationId());
	}

	@Override
	public List<VendorWarehouse> getVendorWarehouseListByVendorId(Long vendorId) {
		return vendorWarehouseMapper.getVendorWarehouseListByVendorId(vendorId, Constants.getCurrentOrganizationId());
	}

	@Override
	public List<VendorArchives> getVendorArchiveListByHouseId(Long vendorId, Long vendorWarehouseId) {
		return vendorArchivesMapper.getVendorArchiveListByHouseId(vendorId, vendorWarehouseId, Constants.getCurrentOrganizationId());
	}

	@Override
	public List<VendorArchives> getVendorArchiveListByVendorId(Long vendorId) {
		return vendorArchivesMapper.getVendorArchiveListByVendorId(vendorId, Constants.getCurrentOrganizationId());
	}

	/**
	 * 保存或更新供应商信息及仓位信息
	 */
	@Override
	public void saveOrUpdateVendor(Vendors vendor, List<VendorWarehouse> vendorWarhouses) throws HSException {
		//1、保存或更新供应商
		if(vendor.getId() == null){
			insert(vendor);
		}else{
			updateByPrimaryKey(vendor);
		}
		
		//2、保存或更新仓库信息
		if(vendorWarhouses != null && vendorWarhouses.size() > 0){
			for(VendorWarehouse data : vendorWarhouses) {
				Long id = data.getId();
				if(id != null){
					data.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
					data.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					vendorWarehouseMapper.updateByPrimaryKey(data);
				}else{
					data.setOrganizationId(Constants.getCurrentOrganizationId());
					data.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
					data.setLastUpdatedBy(data.getCreatedBy());
					data.setCreatedDate(new DateTimeUtil().toTimestamp());
					data.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					vendorWarehouseMapper.insert(data);
				}
			}
		}
	}

	/**
	 * 更新或保存供应商档案信息
	 */
	@Override
	public void saveVendorArchives(List<VendorArchives> vendorArchives) throws HSException {

		if(vendorArchives != null && vendorArchives.size() > 0){
			vendorArchives.forEach(item->{
				Long id = item.getId();
				if(id != null){
					item.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
					item.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					vendorArchivesMapper.updateByPrimaryKey(item);
				}else{
					item.setOrganizationId(Constants.getCurrentOrganizationId());
					item.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
					item.setLastUpdatedBy(item.getCreatedBy());
					item.setCreatedDate(new DateTimeUtil().toTimestamp());
					item.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					vendorArchivesMapper.insert(item);
				}
			});
		}
	}
	
}
