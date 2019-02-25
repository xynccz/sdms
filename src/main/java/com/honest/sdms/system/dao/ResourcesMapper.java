package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.basedata.dao.IBaseDao;
import com.honest.sdms.system.entity.ResourcesVO;

public interface ResourcesMapper extends IBaseDao<ResourcesVO, Long> {
	
	abstract List<ResourcesVO> findResourcesByRoleIds(@Param("roleIds") Long[] roleIds, @Param("organizationId") Long organizationId);
}