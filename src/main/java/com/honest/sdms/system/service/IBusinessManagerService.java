package com.honest.sdms.system.service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.basedata.security.CaptchaUsernamePasswordToken;
import com.honest.sdms.system.entity.SysUserVO;

public abstract class IBusinessManagerService {
	
	/**
	 * 获取当前用户所有的权限
	 */
	public abstract SysUserVO queryPermissions(CaptchaUsernamePasswordToken authcToken)throws HSException;

}
