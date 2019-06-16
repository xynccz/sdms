package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.RoleMapper;
import com.honest.sdms.system.entity.Role;
import com.honest.sdms.system.entity.RoleResource;
import com.honest.sdms.system.entity.UserRole;
import com.honest.sdms.system.service.IRoleResourcesService;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.system.service.IUserRoleService;
import com.honest.sdms.tools.StringUtil;

@Service
public class RolesServiceImp extends BaseServiceImp<Role, Long> implements IRolesService{

	private RoleMapper roleMapper;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleResourcesService roleResourcesService;

	@Resource
	@Qualifier("roleMapper")
	@Override
	public void setBaseDao(IBaseMapper<Role, Long> baseMapper) {
		this.baseMapper = baseMapper;
		roleMapper = (RoleMapper)baseMapper;
	}
	
	@Override
	public List<Role> findRolesByUserId(Long userId, Long organizationId) {
		return roleMapper.findRolesByUserId(userId, organizationId);
	}

	@Override
	public void saveOrUpdateRole(Role role) throws HSException {
		//说明是新用户
		if(role.getRoleId() == null) 
			insertSelective(role);
		else 
			updateByPrimaryKeySelective(role);
		
		//更新角色信息
		userRoleService.deleteUsersByRoleId(role.getRoleId());
		String newUserIds = StringUtil.replace(role.getUserIds(), new String[] {"[","]"}, new String[] {"",""});
		if(!StringUtil.isNullOrEmpty(newUserIds))
		{
			for(String userId : Constants.SPLIT.split(newUserIds)){
				UserRole ur = new UserRole(Long.parseLong(userId),role.getRoleId());
				userRoleService.insertSelective(ur);
			}
		}
		
		//更新角色对应的资源信息
		String resourceIds = role.getResourceIds();
		roleResourcesService.deleteResourceByRoleId(role.getRoleId());
		if(!StringUtil.isNullOrEmpty(resourceIds))
		{
			for(String resourceId : Constants.SPLIT.split(resourceIds)) {
				RoleResource obj = new RoleResource();
				obj.setResourceId(Long.parseLong(resourceId));
				obj.setRoleId(role.getRoleId());
				roleResourcesService.insertSelective(obj);
			}
		}
	}
	
}
