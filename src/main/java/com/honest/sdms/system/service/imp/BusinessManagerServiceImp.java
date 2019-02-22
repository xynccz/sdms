package com.honest.sdms.system.service.imp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.basedata.security.CaptchaUsernamePasswordToken;
import com.honest.sdms.system.entity.SysUserVO;
import com.honest.sdms.system.service.IBusinessManagerService;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.tools.StringUtil;

/**
 * 获取用户信息及用户权限管理类
 * @author beisi
 *
 */
@Service("businessManagerService")
public class BusinessManagerServiceImp extends IBusinessManagerService{
	private Logger logger = LoggerFactory.getLogger(BusinessManagerServiceImp.class);

	@Autowired
	private ISysUserService sysUserService;
	@Override
	public SysUserVO queryPermissions(CaptchaUsernamePasswordToken authcToken) throws HSException {
		logger.info("*******queryPermissions*********");
		SysUserVO currentUser = null;
		try {
			String loginName = authcToken.getUsername();
			String passWord = new String(authcToken.getPassword());
			Long organizationId = authcToken.getOrganiaztionId();
			String isValid = "Y";
			SysUserVO cond = new SysUserVO(loginName, passWord, organizationId, isValid);
			List<SysUserVO> list = sysUserService.findSysUsersByCond(cond);
			
			currentUser = list.size() == 1?list.get(0):null;
			
			//可以执行查询权限操作
			if(currentUser != null)
			{
				
			}
			
		}catch(Exception e) {
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("queryPermissions 出错!"+e.getMessage());
		}
		return currentUser;
	}
	
	
//	
//	@Autowired
//	private ISysUserService sysUserService;
//	@Autowired
//	private IRolesService rolesService;
//	@Autowired
//	private IPermissionService permissionService;
//	
//	/**
//	 * 用户查询，并获取当前用户所拥有的所有权限
//	 * @param loginName 登录名
//	 * @param passWord 登录密码
//	 * @param organizationId 组织号
//	 */
//	@Override
//	public SysUserVO queryPermissions(CaptchaUsernamePasswordToken authcToken)throws HSException{
//		SysUserVO currentUser = null;
//		try{
//			String loginName = authcToken.getUsername();
//			String passWord = new String(authcToken.getPassword());
//			Long organizationId = authcToken.getOrganiaztionId();
//			String isValid = "Y";
//			SysUserVO cond = new SysUserVO(loginName, passWord, organizationId, isValid);
//			List<SysUserVO> list = sysUserService.findByCond(cond);
//			
//			currentUser = list.size() == 1?list.get(0):null;
//			
//			if(currentUser != null)
//			{
//				//通过用户id查询用户角色关系表，获得当前用户所拥有的全部角色
//				List<Roles> roles = rolesService.findRolesByUserId(currentUser.getUserId(), organizationId);
//				if(roles != null && roles.size() > 0)
//				{
//					Long[] roleIds = new Long[roles.size()];
//					for(int i = 0,len = roles.size();i < len;i++){
//						Roles role = roles.get(i);
//						roleIds[i] = role.getRoleId();
//					}
//					
//					//保存角色对应的权限
//					List<RoleResource> roleResources = rolesService.findRoleResourcesByRoleIds(roleIds, organizationId);
//					if(roleResources != null && roleResources.size() > 0)
//					{
//						List<String> buttonGroups = new ArrayList<String>();
//						List<String> menusGroups = new ArrayList<String>();
//						
//						for(RoleResource rs : roleResources){
//							String actionType = rs.getActionType();
//							String code = rs.getResourceCode();
//							if(Constants.BUTTON.equalsIgnoreCase(actionType))
//							{
//								buttonGroups.add(code);
//							}
//							else if(Constants.MENU.equalsIgnoreCase(actionType) || Constants.MODEL.equalsIgnoreCase(actionType))
//							{
//								menusGroups.add(code);
//							}
//						}
//						
//						currentUser.setButtonGroups(buttonGroups);//获取当前登录用户按钮组权限列表
//						currentUser.setMenusGroups(menusGroups);//获取当前登录用户菜单组权限列表
//						
//					}
//					
//					//保存用户对应的角色
//					currentUser.setRoles(roles);
//					currentUser.setRoleIds(roleIds);
//					
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			StringUtil.writeStackTraceToLog(logger, e);
//			throw new HSException("queryPermissions 出错!"+e.getMessage());
//		}
//		
//		return currentUser;
//	}
//
}
