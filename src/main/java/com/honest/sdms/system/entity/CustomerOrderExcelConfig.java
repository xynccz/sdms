package com.honest.sdms.system.entity;

public class CustomerOrderExcelConfig extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long customerId;
    private String customerName;
    private String codeField;
    private String codeDesc;
    private String operateType;
    private String codeRelation;
    private String remarks;
    private String isValid;
    private Long position;
    
    public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCodeRelation() {
		return codeRelation;
	}

	public void setCodeRelation(String codeRelation) {
		this.codeRelation = codeRelation;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }
    
}