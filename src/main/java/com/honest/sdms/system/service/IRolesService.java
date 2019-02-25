package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.RolesVO;

public interface IRolesService {
	
	abstract int saveRoles(RolesVO role);
	
	abstract void saveListRoles(List<RolesVO> list);
	
	/**
	 * 根据用户id查询对应的角色
	 * @param userId
	 * @return
	 */
	abstract List<RolesVO> findRolesByUserId(Long userId, Long organizationId);
	
}
