package com.honest.sdms.order.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 订单交互实体
 * @author beisi
 * R 必填(Required)。
 * O 可选(Optional)
 * C 一定条件下可选(Conditional
 */
public class OrderEntity {

	@JSONField(name="Callback")
	private String callback;//O 用户自定义回传字段
	
	@JSONField(name="MemberID")
	private String memberID;//O ERP 系统、电商平台等系统或平台类型用户的会员 ID 或店铺账号等唯一性标识，用于区分其用户
	
	/*
	 * C 电子面单客户号，需要下载《快递鸟电子面单客户号参数对照表.xlsx》，参考对应字段传值
	 */
	@JSONField(name="CustomerName")
	private String customerName;
	
	@JSONField(name="CustomerPwd")
	private String customerPwd;
	
	@JSONField(name="SendSite")
	private String sendSite;
	
	@JSONField(name="SendStaff")
	private String sendStaff;
	
	@JSONField(name="MonthCode")
	private String monthCode;
	
	@JSONField(name="CustomArea")
	private String customArea;//O 商家自定义区域
	
	@JSONField(name="WareHouseID")
	private String sareHouseID;//C 发货仓编码(ShipperCode 为 JD 或 JDKY 时必填)
	
	@JSONField(name="TransType")
	private Integer transType;//O ShipperCode为JD或JKKY时需填，具体值从快递鸟接口技术文档中找相关字段值
	
	@JSONField(name="ShipperCode")
	private String shipperCode;// R 快递公司编码 详细编码参考《快递鸟接口支 持快递公司编码.xlsx》
	
	@JSONField(name="LogisticCode")
	private String logisticCode;// O 快递单号(仅宅急送可用)
	
	@JSONField(name="ThrOrderCode")
	private String thrOrderCode;// C 京东商城的订单号 (ShipperCode 为 JD 且 ExpType 为 1 时必填) 
	
	@JSONField(name="OrderCode")
	private String orderCode;// R 订单编号(自定义，不可重）
	
	@JSONField(name="PayType")
	private Integer payType;//R 运费支付方式：1-现付，2-到付，3-月结，4- 第三方付(仅 SF、KYSY 支持)
	
	@JSONField(name="ExpType")
	private String expType;//R 详细快递类型参考《快递公司快递业务类型.xlsx》
	
	@JSONField(name="IsReturnSignBill")
	private Integer isReturnSignBill;//O 是否要求签回单0-不要求，1-要求
	
	@JSONField(name="OperateRequire")
	private String operateRequire;// O 签回单操作要求(如：签名、盖章、身份证复印件等)
	
	@JSONField(name="Cost")
	private Double cost;//O 快递运费
	
	@JSONField(name="OtherCost")
	private Double otherCost;//O 其他费用
	
	@JSONField(name="IsNotice")
	private Integer isNotice;//O 是否通知快递员上门揽件0-通知，1-不通知，不填则默认为 1
	
	@JSONField(name="StartDate")
	private String startDate;//O 上门揽件时间段，格式：YYYY-MM-DD HH24:MM:S
	
	@JSONField(name="EndDate")
	private String endDate;//O 上门揽件时间段，格式：YYYY-MM-DD HH24:MM:S
	
	@JSONField(name="Weight")
	private Double weight;//C 包裹总重量 kg (1、 当为快运的订单时必填；2、 ShipperCode 为 JD 时必填；)

	@JSONField(name="Quantity")
	private Long quantity;//R 包裹数(最多支持 300 件) 一个包裹对应一个运单号，如果是大于 1 个包裹，返回则按照子母件的方式返回母运单号和子运单号
	
	@JSONField(name="Volume")
	private Double volume;// C 包裹总体积 m3 1、 当为快运的订单时必填；2、 ShipperCode 为 JD 时必填；
	
	@JSONField(name="Remark")
	private String remark;//O 备注
	
	@JSONField(name="IsReturnPrintTemplate")
	private String isReturnPrintTemplate;//O 是否返回电子面单模板：0-不需要，1-需要
	
	@JSONField(name="IsSendMessage")
	private Integer isSendMessage;//O 是否订阅短信：0-不需要，1-需要
	
	@JSONField(name="IsSubscribe")
	private String isSubscribe;//O 是否订阅轨迹推送0-不订阅，1-订阅，不填默认为 1
	
	@JSONField(name="TemplateSize")
	private String templateSize;//O 模板规格(默认的模板无需传值，非默认模板传对应模板尺寸)
	
	@JSONField(name="PackingType")
	private Integer packingType;//C 包装类型(快运字段)；0-纸，1-纤，2-木，3-托膜，4-木托，99-其他
	
	@JSONField(name="DeliveryMethod")
	private Integer deliveryMethod;//C 送货方式/派送类型(快运字段)：0-自提 1-送货上门（不含上楼）2-送货上楼 当ShipperCode 为 JTSD 时必填，支持以下传值：3-派送上门 4-站点自提 5-快递柜自提 6-代收点自提
	
	@JSONField(name="CurrencyCode")
	private String currencyCode;//C 货物单价的币种：CNY: 人民币 HKD: 港币 NTD: 新台币 MOP: 澳门元(ShipperCode 为 SF 且收件地址为港澳台地区，必填)
	
	@JSONField(name="Dutiable")
	private Dutiable dutiable;
	@JSONField(name="Commodity")
	private Commodity commodity;
	@JSONField(name="AddService")
	private AddService addService;
	@JSONField(name="Receiver")
	private Receiver receiver;
	@JSONField(name="Sender")
	private Sender sender;
	
	class Dutiable{
		@JSONField(name="DeclaredValue")
		private Double declaredValue;//C 申报价值：订单货物总声明价值，包含子母件，精确到小数点后 3 位(ShipperCode 为 SF 且收件地址为港澳台地区，必填)

		public Double getDeclaredValue() {
			return declaredValue;
		}

		public void setDeclaredValue(Double declaredValue) {
			this.declaredValue = declaredValue;
		}
	}
	/*
	 * 商品类实体
	 */
	class Commodity{
		@JSONField(name="GoodsName")
		private String goodsName;//R 商品名称
		@JSONField(name="GoodsCode")
		private String goodsCode;//O 商品编码
		@JSONField(name="Goodsquantity")
		private Integer goodsquantity;//O 商品件数
		@JSONField(name="GoodsPrice")
		private Double goodsPrice;//O 商品价格
		@JSONField(name="GoodsWeight")
		private Double goodsWeight;//O 商品重量 kg
		@JSONField(name="GoodsDesc")
		private String goodsDesc;//O 商品描述
		@JSONField(name="GoodsVol")
		private Double goodsVol;//O 商品体积 m3
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsCode() {
			return goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public Integer getGoodsquantity() {
			return goodsquantity;
		}
		public void setGoodsquantity(Integer goodsquantity) {
			this.goodsquantity = goodsquantity;
		}
		public Double getGoodsPrice() {
			return goodsPrice;
		}
		public void setGoodsPrice(Double goodsPrice) {
			this.goodsPrice = goodsPrice;
		}
		public Double getGoodsWeight() {
			return goodsWeight;
		}
		public void setGoodsWeight(Double goodsWeight) {
			this.goodsWeight = goodsWeight;
		}
		public String getGoodsDesc() {
			return goodsDesc;
		}
		public void setGoodsDesc(String goodsDesc) {
			this.goodsDesc = goodsDesc;
		}
		public Double getGoodsVol() {
			return goodsVol;
		}
		public void setGoodsVol(Double goodsVol) {
			this.goodsVol = goodsVol;
		}
		
	}
	/*
	 * 增值服务实体,参考《快递鸟接口技术文档v5.28.pdf》第27页
	 * 保价:INSURE; 专属客服:HIN_SCS; 代收货款 COD; 配送类型 Delivery
	 */
	class AddService{
		@JSONField(name="Name")
		private String name;//C 增值服务名称(数组形式，可以有多个增值服务)
		@JSONField(name="Value")
		private String value;//C 增值服务值
		@JSONField(name="CustomerID")
		private String customerID;//O 客户标识
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getCustomerID() {
			return customerID;
		}
		public void setCustomerID(String customerID) {
			this.customerID = customerID;
		}
		
	}
	/*
	 * 收件实体相关信息
	 */
	class Receiver{
		@JSONField(name="Company")
		private String company;//O 收件人公司
		@JSONField(name="Name")
		private String name;//R 收件人
		@JSONField(name="Tel")
		private String tel;//R 电话与手机，必填一个
		@JSONField(name="Mobile")
		private String mobile;//R 电话与手机，必填一个
		@JSONField(name="PostCode")
		private String postCode;//C 收件地邮编(ShipperCode 为EMS、YZPY、YZBK 时必填)
		@JSONField(name="ProvinceName")
		private String provinceName;//R 收件省(如广东省，不要缺少“省”；如是直辖市，请直接传北京、上海等；如是自治区，请直接传广西壮族自治区等)
		@JSONField(name="CityName")
		private String cityName;//R 收件市(如深圳市，不要缺少“市；如是市辖区，请直接传北京市、上海市等”)
		@JSONField(name="ExpAreaName")
		private String expAreaName;//R 收件区/县(如福田区，不要缺少“区”或“县”)
		@JSONField(name="Address")
		private String address;//R 收件人详细地址
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPostCode() {
			return postCode;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		public String getProvinceName() {
			return provinceName;
		}
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getExpAreaName() {
			return expAreaName;
		}
		public void setExpAreaName(String expAreaName) {
			this.expAreaName = expAreaName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
	}
	
	/*
	 * 发件实体
	 */
	class Sender{
		@JSONField(name="Company")
		private String company;//O 发件人公司
		@JSONField(name="Name")
		private String name;//R 发件人
		@JSONField(name="Tel")
		private String tel;//R 电话与手机，必填一个
		@JSONField(name="Mobile")
		private String mobile;//R 电话与手机，必填一个
		@JSONField(name="PostCode")
		private String postCode;//C 发件地邮编(ShipperCode 为EMS、YZPY、YZBK 时必填)
		@JSONField(name="ProvinceName")
		private String provinceName;//R 发件省(如广东省，不要缺少“省”；如是直辖市，请直接传北京、上海等；如是自治区，请直接传广西壮族自治区等)
		@JSONField(name="CityName")
		private String cityName;//R 发件市(如深圳市，不要缺少“市；如是市辖区，请直接传北京市、上海市等”)
		@JSONField(name="ExpAreaName")
		private String expAreaName;//R 发件区/县(如福田区，不要缺少“区”或“县”)
		@JSONField(name="Address")
		private String address;//R 发件人详细地址
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPostCode() {
			return postCode;
		}
		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}
		public String getProvinceName() {
			return provinceName;
		}
		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getExpAreaName() {
			return expAreaName;
		}
		public void setExpAreaName(String expAreaName) {
			this.expAreaName = expAreaName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}
	
	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPwd() {
		return customerPwd;
	}

	public void setCustomerPwd(String customerPwd) {
		this.customerPwd = customerPwd;
	}

	public String getSendSite() {
		return sendSite;
	}

	public void setSendSite(String sendSite) {
		this.sendSite = sendSite;
	}

	public String getSendStaff() {
		return sendStaff;
	}

	public void setSendStaff(String sendStaff) {
		this.sendStaff = sendStaff;
	}

	public String getMonthCode() {
		return monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}

	public String getCustomArea() {
		return customArea;
	}

	public void setCustomArea(String customArea) {
		this.customArea = customArea;
	}

	public String getSareHouseID() {
		return sareHouseID;
	}

	public void setSareHouseID(String sareHouseID) {
		this.sareHouseID = sareHouseID;
	}

	public Integer getTransType() {
		return transType;
	}

	public void setTransType(Integer transType) {
		this.transType = transType;
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

	public String getThrOrderCode() {
		return thrOrderCode;
	}

	public void setThrOrderCode(String thrOrderCode) {
		this.thrOrderCode = thrOrderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public Integer getIsReturnSignBill() {
		return isReturnSignBill;
	}

	public void setIsReturnSignBill(Integer isReturnSignBill) {
		this.isReturnSignBill = isReturnSignBill;
	}

	public String getOperateRequire() {
		return operateRequire;
	}

	public void setOperateRequire(String operateRequire) {
		this.operateRequire = operateRequire;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}

	public Integer getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(Integer isNotice) {
		this.isNotice = isNotice;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsReturnPrintTemplate() {
		return isReturnPrintTemplate;
	}

	public void setIsReturnPrintTemplate(String isReturnPrintTemplate) {
		this.isReturnPrintTemplate = isReturnPrintTemplate;
	}

	public Integer getIsSendMessage() {
		return isSendMessage;
	}

	public void setIsSendMessage(Integer isSendMessage) {
		this.isSendMessage = isSendMessage;
	}

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getTemplateSize() {
		return templateSize;
	}

	public void setTemplateSize(String templateSize) {
		this.templateSize = templateSize;
	}

	public Integer getPackingType() {
		return packingType;
	}

	public void setPackingType(Integer packingType) {
		this.packingType = packingType;
	}

	public Integer getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(Integer deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Dutiable getDutiable() {
		return dutiable;
	}

	public void setDutiable(Dutiable dutiable) {
		this.dutiable = dutiable;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public AddService getAddService() {
		return addService;
	}

	public void setAddService(AddService addService) {
		this.addService = addService;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public static void main(String[] args){
		OrderEntity entity = new OrderEntity();
		entity.setOrderCode("012657018199");
		entity.setShipperCode("SF");
		Sender sender = entity.new Sender();
		sender.setCompany("LV");
		sender.setName("Taylor");
		entity.setSender(sender);
		
		JSONObject json = (JSONObject)JSONObject.toJSON(entity);
		System.out.println(json);
	}
	
	
}
