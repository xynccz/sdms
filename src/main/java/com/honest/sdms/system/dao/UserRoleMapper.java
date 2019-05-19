package com.honest.sdms.system.dao;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.UserRole;

public interface UserRoleMapper extends IBaseMapper<UserRole, Long>{
	
	abstract void deleteRolesByUserId(@Param("userId") Long userId, @Param("organizationId") Long organizationId);

	abstract void deleteUsersByRoleId(@Param("roleId") Long roleId, @Param("organizationId") Long organizationId);
}