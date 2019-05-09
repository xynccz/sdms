package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.Roles;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.tools.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/userManagement")
public class SysUserController extends BaseController{

	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRolesService rolesService;
	
	/**
	 * 用户查询
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody String searchUsers(SysUser cond,int pageNum,int pageSize,String sortName, String sortOrder){
		JSONObject result = new JSONObject();
		PageInfo<SysUser> pageInfo = sysUserService.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
		result.put("total", pageInfo.getTotal());
		result.put("rows", JsonUtil.formatListWithDate(pageInfo.getList()));
		result.put("pageNum", pageInfo.getPageNum());
		return result.toString();
	}
	
	/**
	 * 新增和更新用户
	 * @throws HSException 
	 * @throws CustomException 
	 */
	@RequestMapping(value="/saveUser",method={RequestMethod.POST})
	public @ResponseBody APIResponse<String> saveUser(SysUser user) throws HSException{
		sysUserService.saveOrUpdateSysUser(user);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
	@RequestMapping(value = "/getUserRoles", method = RequestMethod.GET, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<JSONObject> getUserRoles(@RequestParam(value = "userId",required = false) Long userId) {
		JSONObject obj = new JSONObject();
		Long[] roleIds = null;
		//获取用户所包含的角色
		if(userId != null)
		{
			List<Roles> currentRoles = rolesService.findRolesByUserId(userId, getOrganizationId());
			roleIds = new Long[currentRoles.size()];
			for(int i = 0,len = currentRoles.size();i < len;i++) {
				roleIds[i] = currentRoles.get(i).getRoleId();
			}
		}
		
		//获取系统角色列表
		JSONArray array = new JSONArray();
		List<Roles> roles = rolesService.findByCond(new Roles());
		for(Roles role : roles) {
			JSONObject json = new JSONObject();
			json.put("value", role.getRoleId());
			json.put("label", role.getRoleName());
			array.add(json);
		}
		
		obj.put("roleIds", roleIds == null?new Long[0]:roleIds);
		obj.put("options", array);
		return new APIResponse<JSONObject>(obj);
	}
	
}
