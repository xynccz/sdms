package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.RoleResourceMapper;
import com.honest.sdms.system.entity.RoleResource;
import com.honest.sdms.system.service.IRoleResourcesService;

@Service
public class RoleResourcesServiceImp extends BaseServiceImp<RoleResource, Long> implements IRoleResourcesService{

	private RoleResourceMapper roleResourceMapper;
	
	@Qualifier("roleResourceMapper")
	@Autowired
	@Override
	public void setBaseDao(IBaseMapper<RoleResource, Long> baseMapper) {
		this.baseMapper = baseMapper;
		roleResourceMapper = (RoleResourceMapper)baseMapper;
	}
	
	@Override
	public List<RoleResource> getRoleResourcesByRoleId(Long roleId) {
		return roleResourceMapper.getRoleResourcesByRoleId(roleId, Constants.getCurrentSysUser().getOrganizationId());
	}

	@Override
	public void deleteResourceByRoleId(Long roleId) {
		roleResourceMapper.deleteResourceByRoleId(roleId,Constants.getCurrentSysUser().getOrganizationId());
	}
	
}
