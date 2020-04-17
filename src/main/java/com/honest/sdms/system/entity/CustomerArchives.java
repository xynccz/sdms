package com.honest.sdms.system.entity;

public class CustomerArchives extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
    private Long customerId;
    private Long itemSpecificId;
    private String customerSpecificCode;
    private String isValid;
    
    private Long itemId;
    private String item;
    private String specificCode;
    
    public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSpecificCode() {
		return specificCode;
	}

	public void setSpecificCode(String specificCode) {
		this.specificCode = specificCode;
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

    public Long getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(Long itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }

    public String getCustomerSpecificCode() {
        return customerSpecificCode;
    }

    public void setCustomerSpecificCode(String customerSpecificCode) {
        this.customerSpecificCode = customerSpecificCode == null ? null : customerSpecificCode.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}