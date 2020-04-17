package com.honest.sdms.system.entity;

public class VendorArchives extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long vendorId;
    private Long itemSpecificId;
    private Long vendorWarehouseId;
    private String vendorSpecificCode;
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

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(Long itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }

    public Long getVendorWarehouseId() {
        return vendorWarehouseId;
    }

    public void setVendorWarehouseId(Long vendorWarehouseId) {
        this.vendorWarehouseId = vendorWarehouseId;
    }

    public String getVendorSpecificCode() {
        return vendorSpecificCode;
    }

    public void setVendorSpecificCode(String vendorSpecificCode) {
        this.vendorSpecificCode = vendorSpecificCode == null ? null : vendorSpecificCode.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

}