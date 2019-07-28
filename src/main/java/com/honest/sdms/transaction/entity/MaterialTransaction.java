package com.honest.sdms.transaction.entity;

import java.util.Date;

import com.honest.sdms.system.entity.BaseVO;

public class MaterialTransaction extends BaseVO{

	private static final long serialVersionUID = 1L;

	private Long transactionId;

    private Long transactionSummaryId;

    private Long itemId;

    private String item;

    private Long poHeaderId;
    
    private String poNumber;

    private Long poLine;

    private Long ioType;

    private String ioStatus;

    private String carNumber;

    private String driverName;

    private String driverPhone;

    private Date shipDate;

    private Date scheduledArrivalDate;

    private Date actualArrivalDate;

    private Date transactionDate;

    private Long transactionPiece;

    private Double transactionWeight;

    private String warehouse;

    private String remarks;

    
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionSummaryId() {
        return transactionSummaryId;
    }

    public void setTransactionSummaryId(Long transactionSummaryId) {
        this.transactionSummaryId = transactionSummaryId;
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

    public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Long getPoLine() {
        return poLine;
    }

    public void setPoLine(Long poLine) {
        this.poLine = poLine;
    }

    public Long getIoType() {
        return ioType;
    }

    public void setIoType(Long ioType) {
        this.ioType = ioType;
    }

    public String getIoStatus() {
        return ioStatus;
    }

    public void setIoStatus(String ioStatus) {
        this.ioStatus = ioStatus == null ? null : ioStatus.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getScheduledArrivalDate() {
        return scheduledArrivalDate;
    }

    public void setScheduledArrivalDate(Date scheduledArrivalDate) {
        this.scheduledArrivalDate = scheduledArrivalDate;
    }

    public Date getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(Date actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionPiece() {
        return transactionPiece;
    }

    public void setTransactionPiece(Long transactionPiece) {
        this.transactionPiece = transactionPiece;
    }

    public Double getTransactionWeight() {
        return transactionWeight;
    }

    public void setTransactionWeight(Double transactionWeight) {
        this.transactionWeight = transactionWeight;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}