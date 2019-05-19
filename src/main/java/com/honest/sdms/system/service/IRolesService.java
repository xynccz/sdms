package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.Role;

public interface IRolesService extends IBaseService<Role, Long>{
	
	abstract int saveRoles(Role role);
	
	abstract void saveListRoles(List<Role> list);
	
	/**
	 * 根据用户id查询对应的角色
	 * @param userId
	 * @return
	 */
	abstract List<Role> findRolesByUserId(Long userId, Long organizationId);
	
	abstract void saveOrUpdateRole(Role role) throws HSException ;
	
}
