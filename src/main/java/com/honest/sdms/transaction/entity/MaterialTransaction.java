package com.honest.sdms.transaction.entity;

import java.sql.Timestamp;

import com.honest.sdms.system.entity.BaseVO;

public class MaterialTransaction extends BaseVO{

	private static final long serialVersionUID = 1L;

	private Long transactionId;
	private Long[] transactionIdIn;
    private Long transactionSummaryId;
    private Long itemId;
    private String item;
    private Long poHeaderId;
    private String poHeaderIds;
    private String poNumber;
    private Long poLine;
    private Long specificId;
    private String specificName;//规格
    private String ioType;//交易类型，来源数据字典
    private String ioTypeName;
    private String ioStatus;
    private String carNumber;
    private String driverName;
    private String driverPhone;
    private Timestamp shipDate;
    private Timestamp scheduledArrivalDate;
    private Timestamp actualArrivalDate;
    private Timestamp transactionDate;
    private Long transactionPiece;
    private Double transactionWeight;
    private String warehouse;
    private String warehouseName;
    private String remarks;

    public Long getSpecificId() {
		return specificId;
	}

	public void setSpecificId(Long specificId) {
		this.specificId = specificId;
	}

	public String getSpecificName() {
		return specificName;
	}

	public void setSpecificName(String specificName) {
		this.specificName = specificName;
	}

	public Long[] getTransactionIdIn() {
		return transactionIdIn;
	}

	public void setTransactionIdIn(Long[] transactionIdIn) {
		this.transactionIdIn = transactionIdIn;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getIoTypeName() {
		return ioTypeName;
	}

	public void setIoTypeName(String ioTypeName) {
		this.ioTypeName = ioTypeName;
	}

	public String getPoHeaderIds() {
		return poHeaderIds;
	}

	public void setPoHeaderIds(String poHeaderIds) {
		this.poHeaderIds = poHeaderIds;
	}

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

    public String getIoType() {
        return ioType;
    }

    public void setIoType(String ioType) {
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

    public Timestamp getShipDate() {
        return shipDate;
    }

    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    public Timestamp getScheduledArrivalDate() {
        return scheduledArrivalDate;
    }

    public void setScheduledArrivalDate(Timestamp scheduledArrivalDate) {
        this.scheduledArrivalDate = scheduledArrivalDate;
    }

    public Timestamp getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(Timestamp actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
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