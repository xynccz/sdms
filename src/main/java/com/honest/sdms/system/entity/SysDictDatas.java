package com.honest.sdms.system.entity;

public class SysDictDatas extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String dictDataCode;

    private String dictDataName;

    private String isValid;

    private Long dictId;

    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode == null ? null : dictDataCode.trim();
    }

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName == null ? null : dictDataName.trim();
    }

    public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
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
}