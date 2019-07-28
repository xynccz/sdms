package com.honest.sdms.transaction.entity;

import com.honest.sdms.system.entity.BaseVO;

public class Item  extends BaseVO{

	private static final long serialVersionUID = 1L;

	private Long itemId;

    private String item;

    private String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

}