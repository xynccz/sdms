package com.honest.sdms.system.entity;

public class CustomerOrderRelation extends BaseVO{
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private Long shopId;
    private String codeField;
    private String initialVal;
    private String actualVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCodeField() {
        return codeField;
    }

    public void setCodeField(String codeField) {
        this.codeField = codeField == null ? null : codeField.trim();
    }

    public String getInitialVal() {
        return initialVal;
    }

    public void setInitialVal(String initialVal) {
        this.initialVal = initialVal == null ? null : initialVal.trim();
    }

    public String getActualVal() {
        return actualVal;
    }

    public void setActualVal(String actualVal) {
        this.actualVal = actualVal == null ? null : actualVal.trim();
    }

}