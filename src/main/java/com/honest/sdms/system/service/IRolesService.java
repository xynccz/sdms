package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.Roles;

public interface IRolesService extends IBaseService<Roles, Long>{
	
	abstract int saveRoles(Roles role);
	
	abstract void saveListRoles(List<Roles> list);
	
	/**
	 * 根据用户id查询对应的角色
	 * @param userId
	 * @return
	 */
	abstract List<Roles> findRolesByUserId(Long userId, Long organizationId);
	
}
