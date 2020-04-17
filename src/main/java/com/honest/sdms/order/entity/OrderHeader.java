package com.honest.sdms.order.entity;

import java.util.Date;

import com.honest.sdms.system.entity.BaseVO;

public class OrderHeader extends BaseVO{
	private static final long serialVersionUID = 1L;
	
    private String headerId;
    private Long recordId;
    private String orderNo;
    private Long orderTypeId;
    private String orderStatus;
    private String orderStatusDesc;
    private Long itemSpecificId;
    private String customerItemSpecific;//客户订单产品规则信息
    private String buyerNotes;
    private String businessPlatform;
    private Long customerId;
    private String customerOrderNo;
    private String customerName;
    private String customerServiceNotes;
    private Long orderCount;
    private Double orderAmount;
    private String isReviewed;
    private String isGeneratedExpressNo;
    private String isPrinted;
    private String isShipped;
    private String isCompleted;
    private String isCreatedExpressInfo;
    private String isCanceled;
    private String orderLog;
    private String remarks;
    private String isValid;
    
    private Date createdDateStart;
    private Date createdDateEnd;
    private String selectDatas;
	private String item;
    private String itemGrade;//产品包装分类
    private String itemStandards;//产品包装规格
    private String itemSpecificCode;//系统产品牌规格信息
    private String description;

    private String consigneeRealname;
    private String consigneeTelphone;
    private String consigneeAddress;
    
    public String getItemSpecificCode() {
		return itemSpecificCode;
	}

	public void setItemSpecificCode(String itemSpecificCode) {
		this.itemSpecificCode = itemSpecificCode;
	}

	public String getCustomerItemSpecific() {
		return customerItemSpecific;
	}

	public void setCustomerItemSpecific(String customerItemSpecific) {
		this.customerItemSpecific = customerItemSpecific;
	}

	public String getConsigneeRealname() {
		return consigneeRealname;
	}

	public void setConsigneeRealname(String consigneeRealname) {
		this.consigneeRealname = consigneeRealname;
	}

	public String getConsigneeTelphone() {
		return consigneeTelphone;
	}

	public void setConsigneeTelphone(String consigneeTelphone) {
		this.consigneeTelphone = consigneeTelphone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}

	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
	}

	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	public String getSelectDatas() {
		return selectDatas;
	}

	public void setSelectDatas(String selectDatas) {
		this.selectDatas = selectDatas;
	}
    
	public Date getCreatedDateStart() {
		return createdDateStart;
	}

	public void setCreatedDateStart(Date createdDateStart) {
		this.createdDateStart = createdDateStart;
	}

	public Date getCreatedDateEnd() {
		return createdDateEnd;
	}

	public void setCreatedDateEnd(Date createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}

	public void setOrderTypeId(Long orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemGrade() {
		return itemGrade;
	}

	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}
	
	public String getBusinessPlatform() {
		return businessPlatform;
	}

	public void setBusinessPlatform(String businessPlatform) {
		this.businessPlatform = businessPlatform;
	}

	public String getItemStandards() {
		return itemStandards;
	}

	public void setItemStandards(String itemStandards) {
		this.itemStandards = itemStandards;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId == null ? null : headerId.trim();
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Long getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderType(Long orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(Long itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }

    public String getBuyerNotes() {
        return buyerNotes;
    }

    public void setBuyerNotes(String buyerNotes) {
        this.buyerNotes = buyerNotes == null ? null : buyerNotes.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerServiceNotes() {
        return customerServiceNotes;
    }

    public void setCustomerServiceNotes(String customerServiceNotes) {
        this.customerServiceNotes = customerServiceNotes == null ? null : customerServiceNotes.trim();
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(String isReviewed) {
        this.isReviewed = isReviewed == null ? null : isReviewed.trim();
    }

    public String getIsGeneratedExpressNo() {
        return isGeneratedExpressNo;
    }

    public void setIsGeneratedExpressNo(String isGeneratedExpressNo) {
        this.isGeneratedExpressNo = isGeneratedExpressNo == null ? null : isGeneratedExpressNo.trim();
    }

    public String getIsPrinted() {
        return isPrinted;
    }

    public void setIsPrinted(String isPrinted) {
        this.isPrinted = isPrinted == null ? null : isPrinted.trim();
    }

    public String getIsShipped() {
        return isShipped;
    }

    public void setIsShipped(String isShipped) {
        this.isShipped = isShipped == null ? null : isShipped.trim();
    }

    public String getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getIsCreatedExpressInfo() {
        return isCreatedExpressInfo;
    }

    public void setIsCreatedExpressInfo(String isCreatedExpressInfo) {
        this.isCreatedExpressInfo = isCreatedExpressInfo == null ? null : isCreatedExpressInfo.trim();
    }

    public String getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(String isCanceled) {
        this.isCanceled = isCanceled == null ? null : isCanceled.trim();
    }

    public String getOrderLog() {
        return orderLog;
    }

    public void setOrderLog(String orderLog) {
        this.orderLog = orderLog == null ? null : orderLog.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    
}