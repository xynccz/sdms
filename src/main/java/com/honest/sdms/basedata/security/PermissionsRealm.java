package com.honest.sdms.basedata.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.Roles;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.IBusinessManagerService;
import com.honest.sdms.tools.StringUtil;

/**
 * scdp 系统认证类
 * 在 Shiro 认证与授权处理过程中，Realm 可以理解为读取用户信息、角色及权限的 DAO
 * @author beisi
 *
 */
public class PermissionsRealm extends AuthorizingRealm{
	private Logger logger = LoggerFactory.getLogger(PermissionsRealm.class);
	
	// 用于获取用户信息及用户权限信息的业务接口
	@Autowired
	@Lazy
	private IBusinessManagerService businessManagerService; 

	/**
	 * 用户登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("******doGetAuthenticationInfo*******");
		SysUser sysUser = null;
		try {
			sysUser = businessManagerService.queryPermissions((CaptchaUsernamePasswordToken) authcToken);
		} catch (HSException e) {
			StringUtil.writeStackTraceToLog(logger, e);
			logger.error("doGetAuthenticationInfo 出错，获取用户信息出错，"+e.getMessage());
		}
		
		if (sysUser != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getLoginPassword(), getName());
            return authenticationInfo;
        }
        return null;
		
	}
	
	/**
	 * 用户授权认证
     * 对当前路径予权限和角色,配置文件里面已经配置了缓存管理器，因此每次页面打开，后台只会读取一次用户角色+权限，后续都会用缓存，重新登陆后缓存自动清空
     * 当用户调用Security.getSubject().isPermitted(permissions)，Security.getSubject().hasRole(roleIdentifier)等方法时，
     * 会触发doGetAuthorizationInfo此方法，以及jsp中调用role权限标签也会触发此方法
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection PrincipalCollection) {
		logger.info("******doGetAuthorizationInfo*******");
		SysUser user = Constants.getCurrentSysUser();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        simpleAuthorInfo.addRoles(getRoles(user));
        simpleAuthorInfo.addStringPermissions(user.getButtonGroups());
        return simpleAuthorInfo;
	}
	
	/**
     * 获取角色集合，string存放的角色名称
     * @param user
     * @return
     */    
	private List<String> getRoles(SysUser user) {
        List<String> roles = new ArrayList<String>();
        if(user.getRoles() != null){
        	for (Roles role : user.getRoles()) {
                roles.add(role.getRoleCode());
            }
        }
        return roles;
    }

}
