package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class ExpressCustomerParameter extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long expressCompanyId;
	private String expressCompanyName;
    private String isNeedCustomerNo;
    private String customerName;
    private String customerPwd;
    private String monthCode;
    private String sendSite;
    private String sendStaff;
    private String isValid;

    public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	public Long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsNeedCustomerNo() {
        return isNeedCustomerNo;
    }

    public void setIsNeedCustomerNo(String isNeedCustomerNo) {
        this.isNeedCustomerNo = isNeedCustomerNo == null ? null : isNeedCustomerNo.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPwd() {
        return customerPwd;
    }

    public void setCustomerPwd(String customerPwd) {
        this.customerPwd = customerPwd == null ? null : customerPwd.trim();
    }

    public String getMonthCode() {
        return monthCode;
    }

    public void setMonthCode(String monthCode) {
        this.monthCode = monthCode == null ? null : monthCode.trim();
    }

    public String getSendSite() {
        return sendSite;
    }

    public void setSendSite(String sendSite) {
        this.sendSite = sendSite == null ? null : sendSite.trim();
    }

    public String getSendStaff() {
        return sendStaff;
    }

    public void setSendStaff(String sendStaff) {
        this.sendStaff = sendStaff == null ? null : sendStaff.trim();
    }

}