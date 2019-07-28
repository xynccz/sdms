package com.honest.sdms.transaction.entity;

import com.honest.sdms.system.entity.BaseVO;

public class MaterialStoreage extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long storeId;

    private Long itemId;

    private String item;

    private Long transactionSummaryId;

    private Long poNumber;

    private String warehouse;

    private Double netWeight;

    private Double grossWeight;

    private Long pieceNum;

    private Long createTransactionId;

    private Long updateTransactionId;

    private String remarks;

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

    public Long getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(Long poNumber) {
        this.poNumber = poNumber;
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