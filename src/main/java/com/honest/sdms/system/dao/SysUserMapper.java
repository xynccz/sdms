package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.SysUser;

public interface SysUserMapper extends IBaseMapper<SysUser, Long>{
	
	List<SysUser> getUsersByRoleId(@Param("roleId") Long roleId, @Param("organizationId") Long organizationId);

}