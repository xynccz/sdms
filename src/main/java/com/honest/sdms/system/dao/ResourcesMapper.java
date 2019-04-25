package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.Resources;

public interface ResourcesMapper extends IBaseMapper<Resources, Long> {
	
	abstract List<Resources> findResourcesByRoleIds(@Param("roleIds") Long[] roleIds, @Param("organizationId") Long organizationId);

}