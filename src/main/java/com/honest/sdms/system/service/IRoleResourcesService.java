package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.RoleResource;

public interface IRoleResourcesService extends IBaseService<RoleResource, Long>{

	abstract List<RoleResource> getRoleResourcesByRoleId(Long roleId);
	
	abstract void deleteResourceByRoleId(Long roleId);
}
