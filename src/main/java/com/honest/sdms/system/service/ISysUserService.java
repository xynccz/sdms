package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.SysUserVO;

public interface ISysUserService {
	
	abstract List<SysUserVO> findSysUsersByCond(SysUserVO cond);
	
	abstract int saveSysUser(SysUserVO sysUser);

}
