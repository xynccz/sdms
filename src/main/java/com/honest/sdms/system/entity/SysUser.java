package com.honest.sdms.system.entity;

import java.util.List;

import net.sf.json.JSONObject;

public class SysUser extends BaseVO{

	private static final long serialVersionUID = 1L;
	private Long userId;
    private String loginName;
    private String loginPassword;
    private String userName;
    private String sex;
    private String phoneNumber;
    private String telephone;
    private String email;
    private String isValid;
    protected Long organizationId;
    private String selectRoleIds;
    private List<String> buttonGroups;//按钮组权限
	private JSONObject promise;//权限相关信息
	private Long[] roleIds;
	private List<Role> roles;

	public SysUser() {
	}
	
	public SysUser(String loginName, String passWord, Long organizationId, String isValid) {
		this.loginName = loginName;
		this.loginPassword = passWord;
		this.organizationId = organizationId;
		this.isValid = isValid;
	}
	
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getSelectRoleIds() {
		return selectRoleIds;
	}

	public void setSelectRoleIds(String selectRoleIds) {
		this.selectRoleIds = selectRoleIds;
	}

	public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

	public List<String> getButtonGroups() {
		return buttonGroups;
	}

	public void setButtonGroups(List<String> buttonGroups) {
		this.buttonGroups = buttonGroups;
	}

	public JSONObject getPromise() {
		return promise;
	}

	public void setPromise(JSONObject promise) {
		this.promise = promise;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
}