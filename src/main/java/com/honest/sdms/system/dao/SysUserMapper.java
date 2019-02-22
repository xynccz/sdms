package com.honest.sdms.system.dao;

import java.util.List;

import com.honest.sdms.basedata.dao.IBaseDao;
import com.honest.sdms.system.entity.SysUserVO;

public interface SysUserMapper extends IBaseDao<SysUserVO, Long>{

	List<SysUserVO> findSysUsersByCond(SysUserVO cond);
}