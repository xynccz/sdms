package com.honest.sdms.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.IRolesService;

public class BaseController {

	/**
	 * 获取当前操作的用户名
	 * @return
	 */
	public static String getLoginName(){
		SysUser user = Constants.getCurrentSysUser();
		String userName = user.getLoginName();
		return userName;
	}
	
	public static Long getOrganizationId() {
		return Constants.getCurrentSysUser().getOrganizationId();
	}
	
	/**
	 * 获取总权限菜单
	 * @param resource 当前角色拥有的权限
	 * @return
	 */
	/*
	 * public String getRolesAclTree(IRolesService rolesService, Long roleId){
	 * List<String> resourceCodes = new ArrayList<String>();
	 * 
	 * if(roleId != null) resourceCodes = rolesService.getRolesOfCodes(new
	 * Long[]{roleId});
	 * 
	 * ModuleManager manager = ModuleManager.getInstance(); return
	 * manager.getRolesAclTree(resourceCodes); }
	 */
	
	/**
	 * 获取当前用户菜单
	 * @param rolesService
	 * @return
	 */
	/*
	 * public String getCurrentMenus(){ List<String> menusGroup =
	 * Constants.getCurrentSysUser().getMenusGroups(); if(menusGroup == null ||
	 * menusGroup.size() == 0) return null;
	 * 
	 * ModuleManager manager = ModuleManager.getInstance(); return
	 * manager.getSystemMenus(menusGroup);
	 * 
	 * return null; }
	 */
	
	/**
	 * 查询角色对应的权限列表
	 * @param resource
	 * @return
	 */
	public String getRoleViews(IRolesService rolesService, Long roleId)throws HSException{
		if(roleId == null)
			throw new HSException("getRoleViews出错，roleId不允许为空！");
		
		/*
		 * List<String> resourceCodes = rolesService.getRolesOfCodes(new
		 * Long[]{roleId}); if(resourceCodes != null && resourceCodes.size() > 0){
		 * ModuleManager manager = ModuleManager.getInstance(); return
		 * manager.getRoleViews(resourceCodes); }else{ return null; }
		 */
		return null;
	}
	
	/**
	 * 对用户名和账套设置cookie
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void setCookie(HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie cookie = new Cookie("username",URLEncoder.encode(getLoginName(), "UTF-8"));
		addCookie(response, cookie);
		
		cookie = new Cookie("organizationId",Constants.getCurrentSysUser().getOrganizationId()+"");
		addCookie(response, cookie);
	}
	
	private void addCookie(HttpServletResponse response, Cookie cookie) throws UnsupportedEncodingException{
		//设置时间为1年
		cookie.setMaxAge(30*24*3600);   
		cookie.setPath("/");
		//把cookie给浏览器
		response.addCookie(cookie);
	}
}
