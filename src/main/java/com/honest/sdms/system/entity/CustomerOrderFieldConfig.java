package com.honest.sdms.system.entity;

public class CustomerOrderFieldConfig extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long configId;

    private String codeField;

    private String codeDesc;

    private Integer position;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getCodeField() {
        return codeField;
    }

    public void setCodeField(String codeField) {
        this.codeField = codeField == null ? null : codeField.trim();
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc == null ? null : codeDesc.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}