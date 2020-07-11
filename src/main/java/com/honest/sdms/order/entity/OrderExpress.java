package com.honest.sdms.order.entity;

import java.sql.Timestamp;

import com.honest.sdms.system.entity.BaseVO;

public class OrderExpress extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long id;
    private String headerId;
    private String expressCompany;
    private String expressCompanyCode;
    private Long expressCompanyId;
    private String expressNo;
    private String expressStatus;
    private String netName;
    private String consigneeRealname;
    private String consigneeTelphone;
    private String consigneeProvince;
    private String consigneeCity;
    private String consigneeCounty;
    private String consigneeAddress;
    private String consigneeZip;
    private String sendingAddress;
    private String senderName;
    private String senderPhone;
    private String deliveryConditions;
    private Double deliveryAmount;
    private String expressResultLast;
    private String expressResult;
    private Timestamp expressCreateTime;
    private Timestamp expressUpdateTime;

    public Long getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Long expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public String getExpressCompanyCode() {
		return expressCompanyCode;
	}

	public void setExpressCompanyCode(String expressCompanyCode) {
		this.expressCompanyCode = expressCompanyCode;
	}

	public String getSendingAddress() {
		return sendingAddress;
	}

	public void setSendingAddress(String sendingAddress) {
		this.sendingAddress = sendingAddress;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getExpressStatus() {
        return expressStatus;
    }

    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus == null ? null : expressStatus.trim();
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName == null ? null : netName.trim();
    }

    public String getConsigneeRealname() {
        return consigneeRealname;
    }

    public void setConsigneeRealname(String consigneeRealname) {
        this.consigneeRealname = consigneeRealname == null ? null : consigneeRealname.trim();
    }

    public String getConsigneeTelphone() {
        return consigneeTelphone;
    }

    public void setConsigneeTelphone(String consigneeTelphone) {
        this.consigneeTelphone = consigneeTelphone == null ? null : consigneeTelphone.trim();
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince == null ? null : consigneeProvince.trim();
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity == null ? null : consigneeCity.trim();
    }

    public String getConsigneeCounty() {
        return consigneeCounty;
    }

    public void setConsigneeCounty(String consigneeCounty) {
        this.consigneeCounty = consigneeCounty == null ? null : consigneeCounty.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsigneeZip() {
        return consigneeZip;
    }

    public void setConsigneeZip(String consigneeZip) {
        this.consigneeZip = consigneeZip == null ? null : consigneeZip.trim();
    }

    public String getDeliveryConditions() {
        return deliveryConditions;
    }

    public void setDeliveryConditions(String deliveryConditions) {
        this.deliveryConditions = deliveryConditions == null ? null : deliveryConditions.trim();
    }

    public Double getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Double deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getExpressResultLast() {
        return expressResultLast;
    }

    public void setExpressResultLast(String expressResultLast) {
        this.expressResultLast = expressResultLast == null ? null : expressResultLast.trim();
    }

    public String getExpressResult() {
        return expressResult;
    }

    public void setExpressResult(String expressResult) {
        this.expressResult = expressResult == null ? null : expressResult.trim();
    }

    public Timestamp getExpressCreateTime() {
        return expressCreateTime;
    }

    public void setExpressCreateTime(Timestamp expressCreateTime) {
        this.expressCreateTime = expressCreateTime;
    }

    public Timestamp getExpressUpdateTime() {
        return expressUpdateTime;
    }

    public void setExpressUpdateTime(Timestamp expressUpdateTime) {
        this.expressUpdateTime = expressUpdateTime;
    }

}