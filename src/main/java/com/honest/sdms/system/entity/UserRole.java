package com.honest.sdms.system.entity;

/**
 * 用户与角色关系
 * @author beisi
 *
 */
public class UserRole extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long userId;
    private Long roleId;

    public UserRole(Long userId, Long roleId) {
    	this.userId = userId;
    	this.roleId = roleId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}