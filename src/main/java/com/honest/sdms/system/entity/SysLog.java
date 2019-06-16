package com.honest.sdms.system.entity;

import java.util.Date;

public class SysLog extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long id;
    private String loginName;
    private String ip;
    private String operateType;
    private String operateUrl;
    private String remarks;
    private String content;
    private Long executeTime;//请求执行时间，单位毫秒
    private Date createdDateStart;
    private Date createdDateEnd;
    private String selectDatas;
    
	public Long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}

	public Date getCreatedDateStart() {
		return createdDateStart;
	}

	public void setCreatedDateStart(Date createdDateStart) {
		this.createdDateStart = createdDateStart;
	}

	public Date getCreatedDateEnd() {
		return createdDateEnd;
	}

	public void setCreatedDateEnd(Date createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}

	public String getSelectDatas() {
		return selectDatas;
	}

	public void setSelectDatas(String selectDatas) {
		this.selectDatas = selectDatas;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(String operateUrl) {
        this.operateUrl = operateUrl == null ? null : operateUrl.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}