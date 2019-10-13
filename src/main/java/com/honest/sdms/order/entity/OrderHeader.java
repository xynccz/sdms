package com.honest.sdms.order.entity;

import com.honest.sdms.system.entity.BaseVO;

public class OrderHeader extends BaseVO{
	private static final long serialVersionUID = 1L;

	private Long headerId;

    private String orderNo;

    private Long orderTypeId;

    private String orderStatus;

    private String buyerNotes;

    private Long shopId;

    private String shopName;

    private String customerServiceNotes;

    private Double orderAmount;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

}