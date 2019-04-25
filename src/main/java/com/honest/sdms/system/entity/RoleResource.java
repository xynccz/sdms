package com.honest.sdms.system.entity;

import com.honest.sdms.basedata.entity.BaseVO;

public class RoleResource extends BaseVO{
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long roleId;
    private Long resourceId;
    private Long[] roleIdIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long[] getRoleIdIn() {
		return roleIdIn;
	}

	public void setRoleIdIn(Long[] roleIdIn) {
		this.roleIdIn = roleIdIn;
	}

	public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}