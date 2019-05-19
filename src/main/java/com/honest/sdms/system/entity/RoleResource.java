package com.honest.sdms.system.entity;

public class RoleResource extends BaseVO{
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long roleId;
    private Long resourceId;
    private Integer type;//'菜单级别:1模块；2菜单；3按钮'
    private Long[] roleIdIn;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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