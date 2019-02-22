package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.RolesMapper;
import com.honest.sdms.system.entity.RolesVO;
import com.honest.sdms.system.service.IRolesService;

@Service("rolesService")
public class RolesServiceImp implements IRolesService{

	@Autowired
	private RolesMapper roleMapper;

	@Override
	public int saveRoles(RolesVO role) {
		return roleMapper.insert(role);
	}

	@Override
	public void saveListRoles(List<RolesVO> list) {
		if(list != null && list.size() > 0)
		{
			for(RolesVO vo : list) {
				saveRoles(vo);
			}
		}
		
	}

	

}
