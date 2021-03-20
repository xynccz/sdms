package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class ExpressWarehouseRelation extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long expressId;

    private Long vendorWarehouseId;

    private String isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public Long getVendorWarehouseId() {
        return vendorWarehouseId;
    }

    public void setVendorWarehouseId(Long vendorWarehouseId) {
        this.vendorWarehouseId = vendorWarehouseId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

}