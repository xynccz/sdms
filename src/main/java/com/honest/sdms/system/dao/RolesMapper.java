package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.basedata.dao.IBaseDao;
import com.honest.sdms.system.entity.RolesVO;

public interface RolesMapper extends IBaseDao<RolesVO, Long> {
	
	abstract List<RolesVO> findRolesByUserId(@Param("userId") Long userId, @Param("organizationId") Long organizationId);
	
}