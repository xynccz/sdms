package com.honest.sdms.basedata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 系统公共配置
 * @author beisi
 */
@Component
public class SpringCommonConfigs {
	
	//用户ID
	@Value("${kdniao.bussiness-id}")
	private String EBusinessID;
	
	//快递鸟电商加密私钥，快递鸟提供，注意保管
	@Value("${kdniao.app-key}")
	private String AppKey;
	
	//快递鸟快递信息查询地址
	@Value("${kdniao.order-traces-url}")
    private String orderTracesUrl;
	
	//快递鸟电子面单环境地址
	@Value("${kdniao.order-online-url}")
    private String orderOnlineUrl;

	//快递鸟订阅推送2.0接口
	@Value("${kdniao.order-traces-sub-url}")
	private String orderTrancesSubUrl;
	
	public String getOrderTrancesSubUrl() {
		return orderTrancesSubUrl;
	}

	public String getEBusinessID() {
		return EBusinessID;
	}

	public String getAppKey() {
		return AppKey;
	}

	public String getOrderTracesUrl() {
		return orderTracesUrl;
	}

	public String getOrderOnlineUrl() {
		return orderOnlineUrl;
	}
	
}
