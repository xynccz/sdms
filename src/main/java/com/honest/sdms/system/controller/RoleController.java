package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.Role;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.tools.JsonUtil;

import net.sf.json.JSONArray;

/**
 * 角色管理
 * @author beisi
 *
 */
@RequestMapping("/roleManager")
@RestController
public class RoleController{

	@Autowired
	private IRolesService rolesService;
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody JSONArray searchRoles(){
		List<Role> result = rolesService.findByCond(new Role());
		return JsonUtil.formatListWithDate(result);
	}
	
	/**
	 * 新增和更新角色
	 * @throws HSException 
	 * @throws CustomException 
	 */
	@RequestMapping(value="/saveRole",method={RequestMethod.POST},produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<String> saveRole(Role role) throws HSException{
		rolesService.saveOrUpdateRole(role);
		return new APIResponse<String>(ResultStatus.OK);
	}
	
}
