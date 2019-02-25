package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.ResourcesMapper;
import com.honest.sdms.system.entity.ResourcesVO;
import com.honest.sdms.system.service.IResourcesService;

@Service("resourcesService")
public class ResourcesServiceImp implements IResourcesService{
	
	@Resource
	private ResourcesMapper resourcesMapper; 

	@Override
	public List<ResourcesVO> findResourcesByRoleIds(Long[] roleIds, Long organizationId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
