package com.honest.sdms.order.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 此实体类用于封装向快递/快运公司下发订单并获取快递单号时返回的数据
 * @author beisi
 *
 */
public class OrderOnlineReturn {

	private String eBusinessId;//R 用户ID
	private boolean success;//R 成功与否(true/false)
	private String resultCode;//R 返回编码
	private String reason;//O 失败原因
	private String uniquerRequestNumber;//O 唯一标识
	private String printTemplate;//O 面单打印模板内容(html 格式)
	private Date estimatedDeliveryTime;//O 订单预计到货时间
	private Integer subCount;//O 子单数量
	private String subOrders;//O 子单单号
	private String subPrintTemplates;//O 子单模板内容(html 格式)
	private String signBillPrintTemplate;//O 签回单模板内容(html 格式)
	private Order order;
	
	class Order{
		private String orderCode;//订单编号
		private String shipperCode;//快递公司编码
		private String logisticCode;//快递单号
		private String markDestination;//大头笔
		private String signWaybillCode;//签回单单号
		private String originCode;//始发地区域编码
		private String originName;//始发地/始发网点
		private String destinatioCode;//目的地区域编码
		private String destinatioName;//目的地/到达网点
		private String sortingCode;//分拣编码
		private String packageCode;//集包编码
		private String packageName;//集包地
		private String destinationAllocationCentre;//目的地分拨
		private Integer transType;
		private Integer transportType;//运输方式(用于自行设计京东模 板): 0:陆运 1:航空
		private ShipperInfo shipperInfo;//自行设计模板用(仅 SF 返回)
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
		public String getMarkDestination() {
			return markDestination;
		}
		public void setMarkDestination(String markDestination) {
			this.markDestination = markDestination;
		}
		public String getSignWaybillCode() {
			return signWaybillCode;
		}
		public void setSignWaybillCode(String signWaybillCode) {
			this.signWaybillCode = signWaybillCode;
		}
		public String getOriginCode() {
			return originCode;
		}
		public void setOriginCode(String originCode) {
			this.originCode = originCode;
		}
		public String getOriginName() {
			return originName;
		}
		public void setOriginName(String originName) {
			this.originName = originName;
		}
		public String getDestinatioCode() {
			return destinatioCode;
		}
		public void setDestinatioCode(String destinatioCode) {
			this.destinatioCode = destinatioCode;
		}
		public String getDestinatioName() {
			return destinatioName;
		}
		public void setDestinatioName(String destinatioName) {
			this.destinatioName = destinatioName;
		}
		public String getSortingCode() {
			return sortingCode;
		}
		public void setSortingCode(String sortingCode) {
			this.sortingCode = sortingCode;
		}
		public String getPackageCode() {
			return packageCode;
		}
		public void setPackageCode(String packageCode) {
			this.packageCode = packageCode;
		}
		public String getPackageName() {
			return packageName;
		}
		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}
		public String getDestinationAllocationCentre() {
			return destinationAllocationCentre;
		}
		public void setDestinationAllocationCentre(String destinationAllocationCentre) {
			this.destinationAllocationCentre = destinationAllocationCentre;
		}
		public Integer getTransType() {
			return transType;
		}
		public void setTransType(Integer transType) {
			this.transType = transType;
		}
		public Integer getTransportType() {
			return transportType;
		}
		public void setTransportType(Integer transportType) {
			this.transportType = transportType;
		}
		public ShipperInfo getShipperInfo() {
			return shipperInfo;
		}
		public void setShipperInfo(ShipperInfo shipperInfo) {
			this.shipperInfo = shipperInfo;
		}
		
		/*
		 * ShipperInfo 参数说明(仅 ShipperCode 为 SF 时返回)
		 * 格式:ShipperInfo={"Details":[{"Detail":{主单号的信息}},{"Detail":{签回单的信息}}]}
		 */
		class ShipperInfo{
			private List<Detail2> details;
			
			class Detail2{
				private Detail detail;
				
				public Detail getDetail() {
					return detail;
				}

				public void setDetail(Detail detail) {
					this.detail = detail;
				}

				class Detail {
					private String logisticCode;//快递单号
					private String originTransferCode;//原寄地中转场
					private String originCityCode;//原寄地城市代码
					private String originDeptCode;//原寄地网点代码
					private String originTeamCode;//原寄地单元区域
					private String destCityCode;//目的地城市代码
					private String destDeptCode;//目的地网点代码
					private String destDeptCodeMapping;//目的地网点代码映射码
					private String destTeamCode;//目的地单元区域
					private String destTeamCodeMapping;//目的地单元区域映射码
					private String destTransferCode;//目的地中转场
					private String destRouteLabel;//打单时的路由标签信息
					private String codingMapping;//入港映射码
					private String codingMappingOut;//出港映射码
					private String xbFlag;//XB 标志 0:不需要打印 1:需要打印
					private String printFlag;// 打印标志 返回值总共有 9 位,每一位 只有0和1两种,0表示按 丰密运单默认的规则,1 表 示显示,顺序如下,如 111110000 表示打印寄方 姓名、寄方电话、寄方公司 名、寄方地址和重量,收方 姓名、收方电话、收方公司 名和收方地址按丰密运单
					private String twoDimensionCode;//二维码 根据规则生成字符串信息, 格式为: MMM={'k1':'(目的地中转 场代码)','k2':'(目的地原 始网点代码)','k3':'(目的 地单元区域)','k4':'(附件 通过三维码 ( express_type_code 、 limit_type_code 、 cargo_type_code)映射时 效类型)','k5':'(运单号)', 'k6':'(AB 标识)'}
					private String proCode;//O 时效类型: 值为二维码中的 K4
					private String printIcon;//O 打印图标
					private String abFlag;//O AB标
					public String getLogisticCode() {
						return logisticCode;
					}
					public void setLogisticCode(String logisticCode) {
						this.logisticCode = logisticCode;
					}
					public String getOriginTransferCode() {
						return originTransferCode;
					}
					public void setOriginTransferCode(String originTransferCode) {
						this.originTransferCode = originTransferCode;
					}
					public String getOriginCityCode() {
						return originCityCode;
					}
					public void setOriginCityCode(String originCityCode) {
						this.originCityCode = originCityCode;
					}
					public String getOriginDeptCode() {
						return originDeptCode;
					}
					public void setOriginDeptCode(String originDeptCode) {
						this.originDeptCode = originDeptCode;
					}
					public String getOriginTeamCode() {
						return originTeamCode;
					}
					public void setOriginTeamCode(String originTeamCode) {
						this.originTeamCode = originTeamCode;
					}
					public String getDestCityCode() {
						return destCityCode;
					}
					public void setDestCityCode(String destCityCode) {
						this.destCityCode = destCityCode;
					}
					public String getDestDeptCode() {
						return destDeptCode;
					}
					public void setDestDeptCode(String destDeptCode) {
						this.destDeptCode = destDeptCode;
					}
					public String getDestDeptCodeMapping() {
						return destDeptCodeMapping;
					}
					public void setDestDeptCodeMapping(String destDeptCodeMapping) {
						this.destDeptCodeMapping = destDeptCodeMapping;
					}
					public String getDestTeamCode() {
						return destTeamCode;
					}
					public void setDestTeamCode(String destTeamCode) {
						this.destTeamCode = destTeamCode;
					}
					public String getDestTeamCodeMapping() {
						return destTeamCodeMapping;
					}
					public void setDestTeamCodeMapping(String destTeamCodeMapping) {
						this.destTeamCodeMapping = destTeamCodeMapping;
					}
					public String getDestTransferCode() {
						return destTransferCode;
					}
					public void setDestTransferCode(String destTransferCode) {
						this.destTransferCode = destTransferCode;
					}
					public String getDestRouteLabel() {
						return destRouteLabel;
					}
					public void setDestRouteLabel(String destRouteLabel) {
						this.destRouteLabel = destRouteLabel;
					}
					public String getCodingMapping() {
						return codingMapping;
					}
					public void setCodingMapping(String codingMapping) {
						this.codingMapping = codingMapping;
					}
					public String getCodingMappingOut() {
						return codingMappingOut;
					}
					public void setCodingMappingOut(String codingMappingOut) {
						this.codingMappingOut = codingMappingOut;
					}
					public String getXbFlag() {
						return xbFlag;
					}
					public void setXbFlag(String xbFlag) {
						this.xbFlag = xbFlag;
					}
					public String getPrintFlag() {
						return printFlag;
					}
					public void setPrintFlag(String printFlag) {
						this.printFlag = printFlag;
					}
					public String getTwoDimensionCode() {
						return twoDimensionCode;
					}
					public void setTwoDimensionCode(String twoDimensionCode) {
						this.twoDimensionCode = twoDimensionCode;
					}
					public String getProCode() {
						return proCode;
					}
					public void setProCode(String proCode) {
						this.proCode = proCode;
					}
					public String getPrintIcon() {
						return printIcon;
					}
					public void setPrintIcon(String printIcon) {
						this.printIcon = printIcon;
					}
					public String getAbFlag() {
						return abFlag;
					}
					public void setAbFlag(String abFlag) {
						this.abFlag = abFlag;
					}
				}
			}
			public List<Detail2> getDetails() {
				return details;
			}

			public void setDetails(List<Detail2> details) {
				this.details = details;
			}
		}
	}

	public String geteBusinessId() {
		return eBusinessId;
	}

	public void seteBusinessId(String eBusinessId) {
		this.eBusinessId = eBusinessId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUniquerRequestNumber() {
		return uniquerRequestNumber;
	}

	public void setUniquerRequestNumber(String uniquerRequestNumber) {
		this.uniquerRequestNumber = uniquerRequestNumber;
	}

	public String getPrintTemplate() {
		return printTemplate;
	}

	public void setPrintTemplate(String printTemplate) {
		this.printTemplate = printTemplate;
	}

	public Date getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}

	public String getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(String subOrders) {
		this.subOrders = subOrders;
	}

	public String getSubPrintTemplates() {
		return subPrintTemplates;
	}

	public void setSubPrintTemplates(String subPrintTemplates) {
		this.subPrintTemplates = subPrintTemplates;
	}

	public String getSignBillPrintTemplate() {
		return signBillPrintTemplate;
	}

	public void setSignBillPrintTemplate(String signBillPrintTemplate) {
		this.signBillPrintTemplate = signBillPrintTemplate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public static void main(String[] args) {
		String str = "{\n" + 
				"	\"EBusinessID\": \"1237100\",\n" + 
				"	\"Order\": {\n" + 
				"		\"OrderCode\": \"012657700387\",\n" + 
				"		\"ShipperCode\": \"HTKY\",\n" + 
				"		\"LogisticCode\": \"50002498503427\",\n" + 
				"		\"MarkDestination\": \"京-朝阳(京-1)\",\n" + 
				"		\"OriginCode\": \"200000\",\n" + 
				"		\"OriginName\": \"上海分拨中心\",\n" + 
				"		\"PackageCode\": \"北京\",\n" + 
				"		\"ShipperInfo\": {\n" + 
				"			\"Details\": [{\n" + 
				"					\"Detail\": {\n" + 
				"						\"logisticCode\": \"ccc\"\n" + 
				"					}\n" + 
				"				},\n" + 
				"				{\n" + 
				"					\"Detail\": {\n" + 
				"						\"logisticCode\": \"dddd\",\n" + 
				"					}\n" + 
				"				}\n" + 
				"			]\n" + 
				"		}\n" + 
				"	},\n" + 
				"	\"PrintTemplate\": \"此处省略打印模板 HTML 内容\",\n" + 
				"	\"EstimatedDeliveryTime\": \"2016-03-06\",\n" + 
				"	\"Success\": true,\n" + 
				"	\"ResultCode\": \"100\",\n" + 
				"	\"Reason\": \"成功\"\n" + 
				"}";
		OrderOnlineReturn order = JSONObject.parseObject(str, OrderOnlineReturn.class);
		System.out.println(order.geteBusinessId());
		System.out.println(JSONObject.toJSON(order));
	}
}
