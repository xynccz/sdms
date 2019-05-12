package com.honest.sdms.system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.UserRoleMapper;
import com.honest.sdms.system.entity.UserRole;
import com.honest.sdms.system.service.IUserRoleService;

@Service
public class UserRoleServiceImp extends BaseServiceImp<UserRole, Long> implements IUserRoleService{
	
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	@Qualifier("userRoleMapper")
	@Override
	public void setBaseDao(IBaseMapper<UserRole, Long> baseMapper) {
		this.baseMapper = baseMapper;
		userRoleMapper = (UserRoleMapper)baseMapper;
	}

	@Override
	public void deleteRolesByUserId(Long userId, Long organizationId) {
		userRoleMapper.deleteRolesByUserId(userId, organizationId);
	}

	

	
}
