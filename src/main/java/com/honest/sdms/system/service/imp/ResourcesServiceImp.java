package com.honest.sdms.system.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.ResourcesMapper;
import com.honest.sdms.system.entity.Resources;
import com.honest.sdms.system.service.IResourcesService;

@Service
public class ResourcesServiceImp extends BaseServiceImp<Resources, Long> implements IResourcesService{
	
	private ResourcesMapper resourcesMapper; 

	@Resource
	@Qualifier("resourcesMapper")
	@Override
	public void setBaseDao(IBaseMapper<Resources, Long> baseMapper) {
		this.baseMapper = baseMapper;
		resourcesMapper = (ResourcesMapper)baseMapper;
	}
	
	@Override
	public List<Resources> findResourcesByRoleIds(Long[] roleIds, Long organizationId) {
		return resourcesMapper.findResourcesByRoleIds(roleIds, organizationId);
	}

	

	
}
