package com.honest.sdms.transaction.entity;

import java.sql.Timestamp;

import com.honest.sdms.system.entity.BaseVO;

public class PoHeader extends BaseVO{

	private static final long serialVersionUID = 1L;

	private Long headerId;
    private String poNumber;
    private String poType;
    private String vendor;
    private Double totalAmount;
    private String buyer;
    private String agent;
    private Double agentPay;
    private Long itemId;
    private String item;
    private String originPlace;
    private String originPlaceName;
    private Double netWeight;
    private Double grossWeight;
    private Double settlementWeight;
    private Double lossWeight;
    private String unit;
    private Long totlePieces;
    private String packageType;
    private Timestamp dateOfPurchase;
    private String isClosed;
    private String remarks;

    public String getOriginPlaceName() {
		return originPlaceName;
	}

	public void setOriginPlaceCode(String originPlaceName) {
		this.originPlaceName = originPlaceName;
	}

	public String getPoType() {
		return poType;
	}

	public void setPoType(String poType) {
		this.poType = poType;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor == null ? null : vendor.trim();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer == null ? null : buyer.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public Double getAgentPay() {
        return agentPay;
    }

    public void setAgentPay(Double agentPay) {
        this.agentPay = agentPay;
    }

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
        this.item = item == null ? null : item.trim();
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace == null ? null : originPlace.trim();
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getSettlementWeight() {
        return settlementWeight;
    }

    public void setSettlementWeight(Double settlementWeight) {
        this.settlementWeight = settlementWeight;
    }

    public Double getLossWeight() {
        return lossWeight;
    }

    public void setLossWeight(Double lossWeight) {
        this.lossWeight = lossWeight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Long getTotlePieces() {
        return totlePieces;
    }

    public void setTotlePieces(Long totlePieces) {
        this.totlePieces = totlePieces;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
    }

    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Timestamp dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}