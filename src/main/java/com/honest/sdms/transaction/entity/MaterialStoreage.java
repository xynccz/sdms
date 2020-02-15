package com.honest.sdms.transaction.entity;

import com.honest.sdms.system.entity.BaseVO;

public class MaterialStoreage extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long storeId;
	private Long inventoryTypeId;
	private String inventoryType;
	private String storeIds;
    private Long itemId;
    private String item;
    private Long transactionSummaryId;
    private Long poHeaderId;
    private Long itemGradeId;
    private String itemGradeName;
    private Long specificId;
    private String specificName;//规格
    private String warehouse;
    private String warehouseName;
    private Double netWeightPerUnit;
    private Double netWeight;
    private Double grossWeight;
    private Long pieceNum;
    private Long createTransactionId;
    private Long updateTransactionId;
    private String remarks;

    public Long getItemGradeId() {
		return itemGradeId;
	}

	public void setItemGradeId(Long itemGradeId) {
		this.itemGradeId = itemGradeId;
	}

	public String getItemGradeName() {
		return itemGradeName;
	}

	public void setItemGradeName(String itemGradeName) {
		this.itemGradeName = itemGradeName;
	}

	public Double getNetWeightPerUnit() {
		return netWeightPerUnit;
	}

	public void setNetWeightPerUnit(Double netWeightPerUnit) {
		this.netWeightPerUnit = netWeightPerUnit;
	}

	public Long getInventoryTypeId() {
		return inventoryTypeId;
	}

	public void setInventoryTypeId(Long inventoryTypeId) {
		this.inventoryTypeId = inventoryTypeId;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(String storeIds) {
		this.storeIds = storeIds;
	}

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

	public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public Long getTransactionSummaryId() {
        return transactionSummaryId;
    }

    public void setTransactionSummaryId(Long transactionSummaryId) {
        this.transactionSummaryId = transactionSummaryId;
    }

    public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
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

    public Long getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Long pieceNum) {
        this.pieceNum = pieceNum;
    }

    public Long getCreateTransactionId() {
        return createTransactionId;
    }

    public void setCreateTransactionId(Long createTransactionId) {
        this.createTransactionId = createTransactionId;
    }

    public Long getUpdateTransactionId() {
        return updateTransactionId;
    }

    public void setUpdateTransactionId(Long updateTransactionId) {
        this.updateTransactionId = updateTransactionId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}