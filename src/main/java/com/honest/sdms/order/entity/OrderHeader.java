package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class OrderHeader extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	private Long headerId;
    private String orderNo;
    private Long orderTypeId;
    private String orderStatus;
    private String buyerNotes;
    private String shopId;
    private String shopOrderNo;
    private String shopName;
    private String customerServiceNotes;
    private Long orderCount;
    private Double orderAmount;
    private String cancelOrder;
    private String orderLog;
    private String isValid;

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
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

    public void setOrderTypeId(Long orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getBuyerNotes() {
        return buyerNotes;
    }

    public void setBuyerNotes(String buyerNotes) {
        this.buyerNotes = buyerNotes == null ? null : buyerNotes.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopOrderNo() {
        return shopOrderNo;
    }

    public void setShopOrderNo(String shopOrderNo) {
        this.shopOrderNo = shopOrderNo == null ? null : shopOrderNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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

    public String getCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(String cancelOrder) {
        this.cancelOrder = cancelOrder == null ? null : cancelOrder.trim();
    }

    public String getOrderLog() {
        return orderLog;
    }

    public void setOrderLog(String orderLog) {
        this.orderLog = orderLog == null ? null : orderLog.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

}