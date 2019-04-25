package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.SysUser;

public interface ISysUserService extends IBaseService<SysUser, Long>{
	
	abstract List<SysUser> findSysUsersByCond(SysUser cond);
	
	abstract int saveSysUser(SysUser sysUser);

}
