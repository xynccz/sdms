package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.Resources;

public interface IResourcesService extends IBaseService<Resources, Long> {

	/**
	 * 查询指定角色下所有的权限列表
	 * @param roleIds 角色id
	 * @param organizationId 组织账套
	 * @return
	 */
	public List<Resources> findResourcesByRoleIds(Long[] roleIds, Long organizationId);
}
