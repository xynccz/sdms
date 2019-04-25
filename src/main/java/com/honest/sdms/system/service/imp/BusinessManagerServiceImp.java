package com.honest.sdms.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.basedata.security.CaptchaUsernamePasswordToken;
import com.honest.sdms.system.entity.Resources;
import com.honest.sdms.system.entity.Roles;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.IBusinessManagerService;
import com.honest.sdms.system.service.IResourcesService;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.tools.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	private static final String INDEX = "index", ICON = "icon", TITLE = "title";
	
	@Override
	public SysUser queryPermissions(CaptchaUsernamePasswordToken authcToken) throws HSException {
		logger.info("*******queryPermissions*********");
		SysUser currentUser = null;
		try {
			String loginName = authcToken.getUsername();
			String passWord = new String(authcToken.getPassword());
			Long organizationId = authcToken.getOrganiaztionId();
			String isValid = "Y";
			SysUser cond = new SysUser(loginName, passWord, organizationId, isValid);
			List<SysUser> list = sysUserService.findSysUsersByCond(cond);
			
			currentUser = list.size() == 1?list.get(0):null;
			
			//可以执行查询权限操作
			if(currentUser != null)
			{
				setPermissionForCurrentUser(currentUser);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("queryPermissions 出错!"+e.getMessage());
		}
		return currentUser;
	}
	
	/**
	 * 查询当前用户所拥有的权限
	 * @param currentUser
	 */
	private void setPermissionForCurrentUser(SysUser currentUser) {
		if (currentUser != null) {
			Long organizationId = currentUser.getOrganizationId();
			
			// 通过用户id查询用户角色关系表，获得当前用户所拥有的全部角色
			List<Roles> roles = rolesService.findRolesByUserId(currentUser.getUserId(), organizationId);
			if (roles != null && roles.size() > 0) {
				Long[] roleIds = new Long[roles.size()];
				for (int i = 0, len = roles.size(); i < len; i++) {
					Roles role = roles.get(i);
					roleIds[i] = role.getRoleId();
				}

				// 保存用户对应的角色
				currentUser.setRoles(roles);
				currentUser.setRoleIds(roleIds);
				
				// 保存角色对应的权限
				List<Resources> resourcesList = resourcesService.findResourcesByRoleIds(roleIds, organizationId);
				if (resourcesList != null && resourcesList.size() > 0) {
					List<String> buttonGroups = new ArrayList<String>();
					Map<Long, Resources> sourceMap = new HashMap<Long, Resources>();
					for (Resources rs : resourcesList) {
						Integer actionType = rs.getType();
						String code = rs.getCode();
						Long resoruceId = rs.getResourceId();
						if (Constants.BUTTON.intValue() == actionType.intValue()) 
						{
							buttonGroups.add(code);
						} else if (Constants.MENU.intValue() == actionType.intValue()
								|| Constants.MODEL.intValue() == actionType.intValue()) 
						{
							if(!sourceMap.containsKey(resoruceId))
							{
								sourceMap.put(resoruceId, rs);
							}
						}
					}
					currentUser.setButtonGroups(buttonGroups);// 获取当前登录用户按钮组权限列表
					currentUser.setMenus(getCurrentMenusList(currentUser,sourceMap));// 获取当前登录用户左侧菜单组权限列表
				}
			}
		}
	}
	
	/**
	 * 拼装左侧菜单列表
	 * @param sourceMap
	 * @return
	 */
	private String getCurrentMenusList(SysUser currentUser,Map<Long, Resources> sourceMap) {
		JSONObject menusList = new JSONObject();
		List<String> roleList = new ArrayList<String>();
		for(Roles role : currentUser.getRoles()) {
			roleList.add(role.getRoleCode());
		}
		menusList.put("roles",roleList);
		menusList.put("loginName", currentUser.getLoginName());
		menusList.put("userId", currentUser.getUserId());
		JSONArray array = new JSONArray();
		
		JSONObject dashboard = new JSONObject();
		dashboard.put(ICON, "el-icon-lx-home");
		dashboard.put(INDEX, "dashboard");
		dashboard.put(TITLE, "系统首页");
		array.add(dashboard);
		  
		Map<Integer,Resources> rootMap = new TreeMap<Integer, Resources>();
		Map<Long,Map<Integer,Resources>> childMap = new HashMap<Long, Map<Integer,Resources>>();
		
		for(Iterator<Entry<Long, Resources>> it = sourceMap.entrySet().iterator();it.hasNext();) {
			Entry<Long, Resources> entry = it.next();
			Resources resource = entry.getValue();
			Long parentId = resource.getParentId();
			int order = resource.getSortOrder();//菜单排列顺序
			
			if(parentId == null)//说明是根节点
			{
				rootMap.put(order,resource);
			}else
			{
				if(!childMap.containsKey(parentId))
				{
					Map<Integer,Resources> map = new TreeMap<Integer, Resources>();
					map.put(order,resource);
					childMap.put(parentId, map);
				}else
				{
					Map<Integer,Resources> map = childMap.get(parentId);
					map.put(order,resource);
				}
			}
		}
		
		for(Iterator<Resources> rit = rootMap.values().iterator();rit.hasNext();) {
			Resources rs = rit.next();
			JSONObject rootNode = setResouece(rs);
			
			Long resourceId = rs.getResourceId();
			setTreeNodes(childMap, resourceId, rootNode);
			
			array.add(rootNode);
		}
		menusList.put("menulist", array);
		return menusList.toString();
	}

	private void setTreeNodes(Map<Long, Map<Integer, Resources>> childMap, Long resourceId, JSONObject rootNode) {
		if(childMap.containsKey(resourceId))//说明此节点下有子节点 
		{
			Map<Integer,Resources> map = childMap.get(resourceId);
			JSONArray subArray = new JSONArray();
			for(Iterator<Resources> sit = map.values().iterator();sit.hasNext();) {
				Resources srs = sit.next();
				JSONObject subObj = setResouece(srs);
				subArray.add(subObj);
				
				Long rid = srs.getResourceId();
				setTreeNodes(childMap, rid, subObj);
			}
			rootNode.put("subs", subArray);
		}
	}
	
	private static JSONObject setResouece(Resources rs) {
		JSONObject json = new JSONObject();
		json.put(ICON, rs.getIcon());
		json.put(INDEX, rs.getPath());
		json.put(TITLE, rs.getTitle());
		return json;
	}
	
}
