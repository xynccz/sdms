package com.honest.sdms.system.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.basedata.security.CaptchaUsernamePasswordToken;
import com.honest.sdms.system.entity.ResourcesVO;
import com.honest.sdms.system.entity.RolesVO;
import com.honest.sdms.system.entity.SysUserVO;
import com.honest.sdms.system.service.IBusinessManagerService;
import com.honest.sdms.system.service.IResourcesService;
import com.honest.sdms.system.service.IRolesService;
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
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IResourcesService resourcesService;
	
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
				setPermissionForUser(currentUser);
			}
			
		}catch(Exception e) {
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("queryPermissions 出错!"+e.getMessage());
		}
		return currentUser;
	}
	
	/**
	 * 查询当前用户所拥有的权限
	 * @param currentUser
	 */
	private void setPermissionForUser(SysUserVO currentUser) {
		if (currentUser != null) {
			Long organizationId = currentUser.getOrganizationId();
			
			// 通过用户id查询用户角色关系表，获得当前用户所拥有的全部角色
			List<RolesVO> roles = rolesService.findRolesByUserId(currentUser.getUserId(), organizationId);
			if (roles != null && roles.size() > 0) {
				Long[] roleIds = new Long[roles.size()];
				for (int i = 0, len = roles.size(); i < len; i++) {
					RolesVO role = roles.get(i);
					roleIds[i] = role.getRoleId();
				}

				// 保存角色对应的权限
				List<ResourcesVO> resourcesList = resourcesService.findResourcesByRoleIds(roleIds, organizationId);
				if (resourcesList != null && resourcesList.size() > 0) {
					List<String> buttonGroups = new ArrayList<String>();
					List<String> menusGroups = new ArrayList<String>();

					for (ResourcesVO rs : resourcesList) {
						Integer actionType = rs.getType();
						String code = rs.getCode();
						if (Constants.BUTTON.intValue() == actionType.intValue()) 
						{
							buttonGroups.add(code);
						} else if (Constants.MENU.intValue() == actionType.intValue()
								|| Constants.MODEL.intValue() == actionType.intValue()) 
						{
							menusGroups.add(code);
						}
					}

					currentUser.setButtonGroups(buttonGroups);// 获取当前登录用户按钮组权限列表
					currentUser.setMenusGroups(menusGroups);// 获取当前登录用户菜单组权限列表
				}

				// 保存用户对应的角色
				currentUser.setRoles(roles);
				currentUser.setRoleIds(roleIds);
			}
		}
	}
	
}
