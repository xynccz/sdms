package com.honest.sdms.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.APIResponse;
import com.honest.sdms.basedata.ResultStatus;
import com.honest.sdms.system.entity.CustomerArchives;
import com.honest.sdms.system.entity.Customers;
import com.honest.sdms.system.entity.Role;
import com.honest.sdms.system.entity.RoleResource;
import com.honest.sdms.system.entity.SysDictDatas;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.entity.Vendors;
import com.honest.sdms.system.service.ICustomersService;
import com.honest.sdms.system.service.IResourcesService;
import com.honest.sdms.system.service.IRoleResourcesService;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.system.service.ISysDictDatasService;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.system.service.IVendorsService;
import com.honest.sdms.tools.StringUtil;
import com.honest.sdms.transaction.entity.Item;
import com.honest.sdms.transaction.service.IItemService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/baseSearch")
@RestController
public class BaseSearchController {
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IRoleResourcesService roleResourcesService;
	@Autowired
	private IResourcesService resourcesService;
	@Autowired
	private ISysDictDatasService sysDictDatasService;
	@Autowired
	private IItemService itemService;
	@Autowired
	private ICustomersService customersService;
	@Autowired
	private IVendorsService vendorsService;
	
	/**
	 * 获取指定字典的键值对
	 * @param dictId 字典类型
	 * @return
	 */
	@RequestMapping(value="/getDictDatasByDictId", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<SysDictDatas> getDictDatasByDictId(@RequestParam("dictId") Long dictId) {
		return sysDictDatasService.getDictDatasByDictId(dictId);
	}
	
	@RequestMapping(value="/getDictDatasByDictCode", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<SysDictDatas> getDictDatasByDictCode(@RequestParam("dictCode") String dictCode) {
		return sysDictDatasService.getDictDatasByDictCode(dictCode);
	}
	
	@RequestMapping(value="/getItems", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<Item> getItems() {
		return itemService.findByCond(new Item());
	}
	
	@RequestMapping(value="/getCustomerList", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<Customers> getCustomerList(String customerName) {
		return customersService.getCustomerList(customerName);
	}
	
	@RequestMapping(value="/getDistinctCustomerSpecific", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<CustomerArchives> getDistinctCustomerSpecific() {
		return customersService.getDistinctCustomerSpecificCode();
	}
	
	@RequestMapping(value="/getVendorList", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<Vendors> getVendorist(String vendorName) {
		return vendorsService.getVendorList(vendorName);
	}
	
	@RequestMapping(value="/getOrderStatus", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody JSONArray getOrderStatus() {
		JSONArray array = new JSONArray();
		JSONObject json1 = new JSONObject();
		json1.put("key", Constants.OrderStatus.UN_REVIEWED);
		json1.put("value", "待审核");
		
		JSONObject json2 = new JSONObject();
		json2.put("key", Constants.OrderStatus.ALREADY_REVIEWED);
		json2.put("value", "已审核");
		
		JSONObject json3 = new JSONObject();
		json3.put("key", Constants.OrderStatus.ALREADY_CREATE_EXPROCESS_ORDER);
		json3.put("value", "已生成单号");
		
		JSONObject json4 = new JSONObject();
		json4.put("key", Constants.OrderStatus.ALREADY_PRINTED_ORDER);
		json4.put("value", "已打印");
		
		JSONObject json5 = new JSONObject();
		json5.put("key", Constants.OrderStatus.ALREADY_OUT_STOCK);
		json5.put("value", "已出库");
		
		JSONObject json6 = new JSONObject();
		json6.put("key", Constants.OrderStatus.ALREADY_CANCEL_ORDER);
		json6.put("value", "已撕单");
		
		array.add(json1);
		array.add(json2);
		array.add(json3);
		array.add(json4);
		array.add(json5);
		array.add(json6);
		return array;
	}
	
	/**
	 * 获取客户订单字段合集
	 * @return
	 */
	@RequestMapping(value="/getOrderFieldList", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody JSONArray getOrderFieldList() {
		return JSONArray.fromObject(customersService.getCustomerOrderFieldList());
	}

	/**
	 * 获取当前账套的用户列表
	 * @return
	 */
	@RequestMapping(value="/getUserList", method = RequestMethod.GET,produces= {"application/json;charset=UTF-8;"})
	public @ResponseBody List<SysUser> getUserList(){
		return sysUserService.findByCond(new SysUser());
	}
	
	/**
	 * 获取指定角色所拥有的资源权限
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value = "/getResourcesByRoleId", method = RequestMethod.GET, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<JSONObject> getResourcesByRoleId(@RequestParam(value = "roleId",required = false) Long roleId) {
		JSONObject result = new JSONObject();
		if(roleId != null)
		{
			List<RoleResource> list = roleResourcesService.getRoleResourcesByRoleId(roleId);
			List<Long> checkResouceIds = new ArrayList<Long>();
			StringBuilder resourceIds = new StringBuilder();
			for(RoleResource resource : list) {
				Integer type = resource.getType();
				Long resourceId = resource.getResourceId();
				
				if(resourceIds.length() > 0)
					resourceIds.append(",");
				
				resourceIds.append(resourceId);
				
				//后端返回前端树节点时，不可以包含父节点，不然会把所有子节点全默认选中
				if(Constants.BUTTON.compareTo(type) == 0)
				{
					checkResouceIds.add(resourceId);
				}
			}
			result.put("checkResourceIds", checkResouceIds);
			result.put("resourceIds",resourceIds.toString() );
		}
		
		result.put("resourceTree", resourcesService.getResourcesTree());
		return new APIResponse<JSONObject>(ResultStatus.OK,result);
	}
	
	/**
	 * 获取指定角色包含的用户列表
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/getUsersByRoleId", method = RequestMethod.GET, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<JSONObject> getUsersByRoleId(@RequestParam(value = "roleId",required = false) Long roleId) {
		JSONObject obj = new JSONObject();
		Long[] userIds = null;
		//获取用户所包含的角色
		if(roleId != null)
		{
			List<SysUser> userList = sysUserService.getUsersByRoleId(roleId);
			userIds = new Long[userList.size()];
			for(int i = 0,len = userList.size();i < len;i++) {
				userIds[i] = userList.get(i).getUserId();
			}
		}
		
		//获取系统角色列表
		JSONArray array = new JSONArray();
		List<SysUser> users = sysUserService.findByCond(new SysUser());
		for(SysUser user : users) {
			JSONObject json = new JSONObject();
			json.put("userId", user.getUserId());
			json.put("userName", user.getUserName());
			json.put("pinyin", StringUtil.ToPinyin(user.getUserName()));
			array.add(json);
		}
		
		obj.put("checkUserIds", userIds == null?new Long[0]:userIds);
		obj.put("userList", array);
		return new APIResponse<JSONObject>(ResultStatus.OK, obj);
	} 
	
	/**
	 * 获取指定用户对应的角色列表
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getRolesByUserId", method = RequestMethod.GET, produces={"application/json;charset=UTF-8;"})
	public @ResponseBody APIResponse<JSONObject> getRolesByUserId(@RequestParam(value = "userId",required = false) Long userId) {
		JSONObject obj = new JSONObject();
		Long[] roleIds = null;
		//获取用户所包含的角色
		if(userId != null)
		{
			List<Role> currentRoles = rolesService.findRolesByUserId(userId, Constants.getCurrentSysUser().getOrganizationId());
			roleIds = new Long[currentRoles.size()];
			for(int i = 0,len = currentRoles.size();i < len;i++) {
				roleIds[i] = currentRoles.get(i).getRoleId();
			}
		}
		
		//获取系统角色列表
		JSONArray array = new JSONArray();
		List<Role> roles = rolesService.findByCond(new Role());
		for(Role role : roles) {
			JSONObject json = new JSONObject();
			json.put("value", role.getRoleId());
			json.put("label", role.getRoleName());
			array.add(json);
		}
		
		obj.put("roleIds", roleIds == null?new Long[0]:roleIds);
		obj.put("roleList", array);
		return new APIResponse<JSONObject>(ResultStatus.OK,obj);
	}
}
