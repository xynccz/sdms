package com.honest.sdms.transaction.entity;

import com.honest.sdms.system.entity.BaseVO;

public class PoLine extends BaseVO{

	private static final long serialVersionUID = 1L;

	private Long lineId;

    private Long headerId;

    private Double weight;

    private Long specificId;
    
    private String specificName;//从数据字典中查询，配置到xml中

    private Long pieceNum;

    private Double unitPrice;

    private String remarks;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

	public Long getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Long pieceNum) {
        this.pieceNum = pieceNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}