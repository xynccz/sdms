package com.honest.sdms.order.entity;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 物流查询返回结果实体类
 * @author beisi
 *
 */
public class OrderTrancesReturn {

	private String ebusinessId;//用户 ID
	private String orderCode;//订单编号
	private String shipperCode;//快递公司编码
	private String logisticCode;//快递单号
	private String callback;//用户自定义回传字段
	private boolean success;//成功与否(true/false)
	private String reason;//失败原因
	private String state;//物流状态:0 - 暂无轨迹信息 1-已揽收 2-在途中 3-签收 4-问题件
	
	/*
	 * 增值物流状态:
	 * 0-暂无轨迹信息 1-已揽收 
	 * 2-在途中 201-到达派件城市 202-派件中 211-已放入快递柜或驿站
     * 3-已签收 301-正常签收 302-派件异常后最终签收 304-代收签收 311-快递柜或驿站签收
     * 4-问题件 401-发货无信息 402-超时未签收 403-超时未更新 404-拒收(退件) 405-派件异常 406-退货签收 407-退货未签收 412-快递柜或驿站超时未取
	 */
	private String stateEx;
	private String location;
	private List<Trace> Traces;
	
	public String getEbusinessId() {
		return ebusinessId;
	}

	public void setEbusinessId(String ebusinessId) {
		this.ebusinessId = ebusinessId;
	}

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

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateEx() {
		return stateEx;
	}

	public void setStateEx(String stateEx) {
		this.stateEx = stateEx;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Trace> getTraces() {
		return Traces;
	}

	public void setTraces(List<Trace> traces) {
		Traces = traces;
	}

	//物流信息轨迹类
	public class Trace{
		private String acceptTime;//轨迹发生时间
		private String acceptStation;//轨迹描述
		private String location;//当前城市
		private String action;//当前状态(同 StateEx)
		private String remark;
		public String getAcceptTime() {
			return acceptTime;
		}
		public void setAcceptTime(String acceptTime) {
			this.acceptTime = acceptTime;
		}
		public String getAcceptStation() {
			return acceptStation;
		}
		public void setAcceptStation(String acceptStation) {
			this.acceptStation = acceptStation;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
	}
	
	public static void main(String[] args) {
		String result = "{  \"LogisticCode\" : \"YT4711918387687\",  \"ShipperCode\" : \"YTO\",  \"Traces\" : [ {    \"AcceptStation\" : \"【广东省深圳市坂田公司】 已收件 取件人: 肖洪伟 (13798495219)\",    \"AcceptTime\" : \"2020-08-12 00:22:21\"  }, {    \"AcceptStation\" : \"【广东省深圳市坂田公司】 已打包\",    \"AcceptTime\" : \"2020-08-12 03:57:10\"  }, {    \"AcceptStation\" : \"【广东省深圳市坂田】 已发出 下一站 【深圳转运中心公司】\",    \"AcceptTime\" : \"2020-08-12 04:08:01\"  }, {    \"AcceptStation\" : \"【深圳转运中心公司】 已收入\",    \"AcceptTime\" : \"2020-08-12 04:21:56\"  }, {    \"AcceptStation\" : \"【深圳转运中心】 已发出 下一站 【西安转运中心公司】\",    \"AcceptTime\" : \"2020-08-12 04:45:53\"  }, {    \"AcceptStation\" : \"【西安转运中心公司】 已收入\",    \"AcceptTime\" : \"2020-08-13 15:33:18\"  }, {    \"AcceptStation\" : \"【西安转运中心】 已发出 下一站 【陕西省西安市锦业公司】\",    \"AcceptTime\" : \"2020-08-13 15:54:03\"  }, {    \"AcceptStation\" : \"【陕西省西安市锦业公司】 派件中  派件人: 杨雷党 电话 18521183360  如有疑问，请联系：029-33354761\",    \"AcceptTime\" : \"2020-08-14 07:11:59\"  }, {    \"AcceptStation\" : \"快件已暂存至西安尚品美地城东区南门店菜鸟驿站，如有疑问请联系15902941695\",    \"AcceptTime\" : \"2020-08-14 11:27:24\"  }, {    \"AcceptStation\" : \"客户签收人: 已签收，签收人凭取货码签收。 已签收  感谢使用圆通速递，期待再次为您服务 如有疑问请联系：18521183360，投诉电话：029-33354761\",    \"AcceptTime\" : \"2020-08-14 21:56:34\"  } ],  \"State\" : \"3\",  \"EBusinessID\" : \"1574456\",  \"Success\" : true}";
		OrderTrancesReturn order = JSONObject.parseObject(result, OrderTrancesReturn.class);
		System.out.println(order.getLogisticCode()+"===="+order.getTraces());
	}
}
