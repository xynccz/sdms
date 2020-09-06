package com.honest.sdms.order.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 此实体类用于封装向快递公司实时查询物流轨迹信息
 * RequestType = 1002(免费服务，主流快递仅支持中通快递、申通快递、圆通速递 3 家),
 * RequestType = 8001(付费增强服务，主流快递公司都支持，以及返回报文中 增加如下几个参数:
 * 							1. 增加增值物流状态 StateEx 和所在城市 Location;
 * 							2. 物流轨迹中增加当前城市 Location 和当前状态 Action。)
 * R 必填(Required)。
 * O 可选(Optional)
 * C 一定条件下可选Conditional
 * @author beisi
 *
 */
public class OrderTraces {

	@JSONField(name="OrderCode")
	private String orderCode;//O 订单编号
	@JSONField(name="ShipperCode")
	private String shipperCode;//R 快递公司编码 详细编码参考《快递鸟接口支持快递公司 编码.xlsx》
	@JSONField(name="LogisticCode")
	private String logisticCode;//R 快递单号
	/*
	 * ShipperCode 为 JD,必填,对应京东的青 龙配送编码,也叫商家编码,格式:数字 +字母+数字,9 位数字加一个字母,共 10 位,举例:001K123450;
	 * ShipperCode 为 SF,且快递单号非快递鸟 渠道返回时,必填,对应收件人/寄件人手 机号后四位;ShipperCode 为 SF,且快递单号为快递鸟 渠道返回时,不填;
	 * ShipperCode 为其他快递时,不填 
	 */
	@JSONField(name="CustomerName")
	private String customerName;//C 
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public String getLogisticCode() {
		return logisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
