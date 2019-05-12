package com.honest.sdms.basedata;

/**
 * 系统状态码枚举类统一管理
 * @author beisi
 *
 */
public enum ResultStatus {
	
	OK(200,"ok");
	
	
	private final int value;
	private final String reasonPhrase;

	ResultStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase; 
	}

	public int getValue() {
		return value;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}
	

}
