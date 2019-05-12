package com.honest.sdms.system.service.imp;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.SysUserMapper;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.entity.UserRole;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.system.service.IUserRoleService;
import com.honest.sdms.tools.StringUtil;

@Service
public class SysUserServiceImp extends BaseServiceImp<SysUser, Long> implements ISysUserService{
	
	private SysUserMapper sysUserMapper;
	private static Pattern SPLIT = Pattern.compile(",");
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Resource
	@Qualifier("sysUserMapper")
	@Override
	public void setBaseDao(IBaseMapper<SysUser, Long> baseMapper) {
		this.baseMapper = baseMapper;
		sysUserMapper = (SysUserMapper)baseMapper;
	}
	
	@Override
	public List<SysUser> findSysUsersByCond(SysUser sysUserVO) {
		return findByCond(sysUserVO);
	}

	@Override
	public int saveSysUser(SysUser sysUser) {
		return sysUserMapper.insertSelective(sysUser);
	}

	/**
	 * 保存或新增用户信息
	 */
	@Override
	public void saveOrUpdateSysUser(SysUser user) throws HSException {

		/*
		 * 是保存新用户的操作
		 */
		if (user.getUserId() == null) {
			user.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
			user.setCreatedDate(new Date());
			user.setLoginPassword(StringUtil.encrypt(Constants.DEFAULT_PASSWORD));
			user.setOrganizationId(Constants.DEFAULT_ORGANIZATIONID);
		}

		user.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
		user.setLastUpdatedDate(new Date());

		if (user.getUserId() == null) {
			sysUserMapper.insertSelective(user);
		} else {
			sysUserMapper.updateByPrimaryKeySelective(user);
		}
		
		//更新角色信息
		userRoleService.deleteRolesByUserId(user.getUserId(), user.getOrganizationId());
		String newRoleIds = StringUtil.replace(user.getSelectRoleIds(), new String[] {"[","]"}, new String[] {"",""});
		if(!StringUtil.isNullOrEmpty(newRoleIds))
		{
			for(String roleId : SPLIT.split(newRoleIds)){
				UserRole ur = new UserRole(user.getUserId(),Long.parseLong(roleId));
				ur.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
				ur.setOrganizationId(user.getOrganizationId());
				userRoleService.insertSelective(ur);
			}
		}
	}
	
	public static void main(String[] args) {
		String str = "[]";
		System.out.println(StringUtil.replace(str, new String[] {"[","]"}, new String[] {"",""}));
	}
	
}
