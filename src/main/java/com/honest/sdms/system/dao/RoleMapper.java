package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.Role;

public interface RoleMapper extends IBaseMapper<Role, Long> {
	
	abstract List<Role> findRolesByUserId(@Param("userId") Long userId, @Param("organizationId") Long organizationId);
	
}