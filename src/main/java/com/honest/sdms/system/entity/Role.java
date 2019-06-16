package com.honest.sdms.system.entity;

/**
 * 角色表
 * @author beisi
 *
 */
public class Role extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	private Long roleId;
	private Long[] roleIdIn;
	private String roleName;
	private String roleCode;//角色编码
	private String description;
	
	private String userIds;
	private String resourceIds;

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Long[] getRoleIdIn() {
		return roleIdIn;
	}

	public void setRoleIdIn(Long[] roleIdIn) {
		this.roleIdIn = roleIdIn;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

}