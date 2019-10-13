package com.honest.sdms.system.service.imp;

import java.util.List;

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
		return insertSelective(sysUser);
	}
	
	@Override
	public List<SysUser> getUsersByRoleId(Long roleId) {
		return sysUserMapper.getUsersByRoleId(roleId,Constants.getCurrentOrganizationId());
	}

	/**
	 * 保存或新增用户信息
	 */
	@Override
	public void saveOrUpdateSysUser(SysUser user) throws HSException {
		//说明是新用户
		if(user.getUserId() == null){
			user.setLoginPassword(StringUtil.encrypt(Constants.DEFAULT_PASSWORD));
			insertSelective(user);
		}else{
			updateByPrimaryKeySelective(user);
		}
		
		//更新角色信息
		userRoleService.deleteRolesByUserId(user.getUserId());
		String[] roleIds = StringUtil.stringToArray(user.getSelectRoleIds());
		if(roleIds != null && roleIds.length > 0){
			for(String roleId : roleIds){
				UserRole ur = new UserRole(user.getUserId(),Long.parseLong(roleId));
				userRoleService.insertSelective(ur);
			}
		}
	}
	
}
