package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.SysUserMapper;
import com.honest.sdms.system.entity.SysUserVO;
import com.honest.sdms.system.service.ISysUserService;

@Service("sysUserService")
public class SysUserServiceImp implements ISysUserService{

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Override
	public List<SysUserVO> findSysUsersByCond(SysUserVO cond) {
		return sysUserMapper.findSysUsersByCond(cond);
	}

	@Override
	public int saveSysUser(SysUserVO sysUser) {
		return sysUserMapper.insertSelective(sysUser);
	}

	
}
