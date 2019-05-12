package com.honest.sdms.system.service;

import com.honest.sdms.system.entity.UserRole;

public interface IUserRoleService extends IBaseService<UserRole, Long>{
	
	abstract void deleteRolesByUserId(Long userId, Long organizationId);

}
