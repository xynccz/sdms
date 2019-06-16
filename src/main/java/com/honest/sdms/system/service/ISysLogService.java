package com.honest.sdms.system.service;

import com.honest.sdms.system.entity.SysLog;

public interface ISysLogService extends IBaseService<SysLog, Long>{
	
	abstract void saveSysLog(SysLog log);
	
}
