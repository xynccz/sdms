package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class OrderDetail extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long detailId;
    private String headerId;
    private Long itemId;
    private String item;
    private Long warehouseId;
    private Double weight;
    private Long pieceNum;
    private String description;


    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
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

    public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}