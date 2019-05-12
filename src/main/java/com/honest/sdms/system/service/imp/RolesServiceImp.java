package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.RolesMapper;
import com.honest.sdms.system.entity.Roles;
import com.honest.sdms.system.service.IRolesService;

@Service
public class RolesServiceImp extends BaseServiceImp<Roles, Long> implements IRolesService{

	private RolesMapper rolesMapper;

	@Resource
	@Qualifier("rolesMapper")
	@Override
	public void setBaseDao(IBaseMapper<Roles, Long> baseMapper) {
		this.baseMapper = baseMapper;
		rolesMapper = (RolesMapper)baseMapper;
	}
	
	@Override
	public int saveRoles(Roles role) {
		return rolesMapper.insert(role);
	}

	@Override
	public void saveListRoles(List<Roles> list) {
		if(list != null && list.size() > 0)
		{
			for(Roles vo : list) {
				saveRoles(vo);
			}
		}
	}

	@Override
	public List<Roles> findRolesByUserId(Long userId, Long organizationId) {
		return rolesMapper.findRolesByUserId(userId, organizationId);
	}

}
