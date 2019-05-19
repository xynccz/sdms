package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.RoleResource;

public interface RoleResourceMapper extends IBaseMapper<RoleResource, Long>{
	
	abstract List<RoleResource> getRoleResourcesByRoleId(@Param("roleId") Long roleId, @Param("organizationId") Long organizationId);
   
	abstract void deleteResourceByRoleId(@Param("roleId") Long roleId, @Param("organizationId") Long organizationId);
}