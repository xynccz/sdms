package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.Roles;

public interface RolesMapper extends IBaseMapper<Roles, Long> {
	
	abstract List<Roles> findRolesByUserId(@Param("userId") Long userId, @Param("organizationId") Long organizationId);
	
}