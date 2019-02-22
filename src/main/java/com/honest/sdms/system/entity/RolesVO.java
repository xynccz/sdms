package com.honest.sdms.system.entity;

import com.honest.sdms.basedata.entity.BaseVO;

/**
 * 角色表
 * @author beisi
 *
 */
public class RolesVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleCode;

	private String description;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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