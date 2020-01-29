package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class OrderDetail extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long detailId;
    private Long headerId;
    private String productName;
    private Long itemId;
    private String item;
    private Long specificId;
    private String warehouse;
    private Double weight;
    private Long pieceNum;
    private String remarks;


    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
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

    public Long getSpecificId() {
        return specificId;
    }

    public void setSpecificId(Long specificId) {
        this.specificId = specificId;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse == null ? null : warehouse.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Long pieceNum) {
        this.pieceNum = pieceNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}