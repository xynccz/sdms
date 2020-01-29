package com.honest.sdms.basedata;

/**
 * 系统状态码枚举类统一管理
 * @author beisi
 *
 */
public enum ResultStatus {
	
	OK(200,"ok"),
	ERROR(300,"error"),
	EXCEPTION(500,"exception");
	
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
