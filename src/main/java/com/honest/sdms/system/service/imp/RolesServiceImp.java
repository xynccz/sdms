package com.honest.sdms.system.service.imp;

import java.util.Date;
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
	public int saveRoles(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public void saveListRoles(List<Role> list) {
		if(list != null && list.size() > 0)
		{
			for(Role vo : list) {
				saveRoles(vo);
			}
		}
	}

	@Override
	public List<Role> findRolesByUserId(Long userId, Long organizationId) {
		return roleMapper.findRolesByUserId(userId, organizationId);
	}

	@Override
	public void saveOrUpdateRole(Role role) throws HSException {
		//说明是新用户
		if (role.getRoleId() == null) {
			role.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
			role.setCreatedDate(new Date());
			role.setOrganizationId(Constants.getCurrentSysUser().getOrganizationId());
		}

		if (role.getRoleId() == null) {
			roleMapper.insertSelective(role);
		} else {
			roleMapper.updateByPrimaryKeySelective(role);
		}
		
		//更新角色信息
		userRoleService.deleteUsersByRoleId(role.getRoleId());
		String newUserIds = StringUtil.replace(role.getCheckedUserId(), new String[] {"[","]"}, new String[] {"",""});
		if(!StringUtil.isNullOrEmpty(newUserIds))
		{
			for(String userId : Constants.SPLIT.split(newUserIds)){
				UserRole ur = new UserRole(Long.parseLong(userId),role.getRoleId());
				ur.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
				ur.setOrganizationId(role.getOrganizationId());
				userRoleService.insertSelective(ur);
			}
		}
		
		//更新角色对应的资源信息
		String resourceIds = role.getReosurceIds();
		roleResourcesService.deleteResourceByRoleId(role.getRoleId());
		for(String resourceId : Constants.SPLIT.split(resourceIds)) {
			RoleResource obj = new RoleResource();
			obj.setResourceId(Long.parseLong(resourceId));
			obj.setRoleId(role.getRoleId());
			obj.setOrganizationId(role.getOrganizationId());
			obj.setCreatedDate(new Date());
			obj.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
			roleResourcesService.insertSelective(obj);
		}
		
	}
	
}
