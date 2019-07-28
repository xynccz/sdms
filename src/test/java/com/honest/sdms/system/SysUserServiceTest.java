package com.honest.sdms.system;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.ApplicationTests;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.Role;
import com.honest.sdms.system.entity.SysLog;
import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.system.service.ISysLogService;
import com.honest.sdms.system.service.ISysUserService;
import com.honest.sdms.tools.DateTimeUtil;

public class SysUserServiceTest extends ApplicationTests{
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private ISysLogService sysLogService;
	
	@Test
	public void testFindByCond() {
		SysLog cond = new SysLog();
		cond.setOrganizationId(360L);
		
//		String[] rangeDatas = new String[] {"1559489050758","1560093850758"};
//		cond.setCreatedDateStart(new Date(Long.parseLong(rangeDatas[0])));
//		cond.setCreatedDateEnd(new Date(Long.parseLong(rangeDatas[1])));
//		System.out.println(cond.getCreatedDateStart().toLocaleString()+"======"+cond.getCreatedDateEnd().toLocaleString());
		List<SysLog> list = sysLogService.findByCond(cond);
		for(SysLog lg : list) {
			System.out.println(lg.getCreatedDate()+"=="+new DateTimeUtil(lg.getCreatedDate()).toString(DateTimeUtil.DATE_FORMAT));
		}
		System.out.println(list.size());
		
	}
	
//	@Test
	public void testFindSysUserPage() {
		SysUser cond = new SysUser();
		PageInfo<SysUser> pageInfo = sysUserService.findByCondWithPage(cond, null, "desc", 1, 2);
		List<SysUser> list = pageInfo.getList();
		for(SysUser user : list) {
			System.out.println(user.getUserId()+"=="+user.getLoginName());
		}
		System.out.println(pageInfo.getList().size());
	}
	
//	@Test
	public void testFindUserByCond() {
		SysUser cond = new SysUser();
		List<SysUser> list = sysUserService.findByCond(cond);
		System.out.println("******"+list.get(0).getUserName());
	}
	
//	@Test
	public void testFindRolesByCond() {
		List<Role> currentRoles = rolesService.findRolesByUserId(1L, 360L);
		System.out.println(currentRoles.size());
	}

	//@Test
	public void saveUser() throws HSException {
		SysUser user = sysUserService.selectByPrimaryKey(3L);
		user.setUserName(user.getUserName()+"22");
		sysUserService.updateByPrimaryKeySelective(user);
//		throw new HSException("dsf");
	}
}
