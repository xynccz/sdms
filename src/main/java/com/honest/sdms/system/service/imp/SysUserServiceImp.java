package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.SysUserMapper;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.ISysUserService;

@Service("sysUserService")
public class SysUserServiceImp extends BaseServiceImp<SysUser, Long> implements ISysUserService{

	
	private SysUserMapper sysUserMapper;
	
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

}
