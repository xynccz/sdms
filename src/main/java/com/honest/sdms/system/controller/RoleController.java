package com.honest.sdms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honest.sdms.system.entity.Roles;
import com.honest.sdms.system.service.IRolesService;

/**
 * 角色管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/roleManager")
public class RoleController extends BaseController{

	@Autowired
	private IRolesService rolesService;
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody List<Roles> searchRoles(){
		List<Roles> result = rolesService.findByCond(new Roles());
		return result;
	}
	
}
