package com.honest.sdms.basedata;

/**
 * 全局统一返回对象
 * @author beisi
 *
 */
public class APIResponse<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private int code;//返回码
	private String msg;//返回消息
	private T data;//返回数据
	
	public APIResponse () {
	}
	
	public APIResponse (int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public APIResponse (ResultStatus status) {
		this(status, null);
	}
	
	public APIResponse (T data) {
		this(null, data);
	}
	
	public APIResponse (ResultStatus status, T data) {
		
		if(status != null){
			this.code = status.getValue();
			this.msg = status.getReasonPhrase();
		}
		
		if(data != null){
			this.data = data;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
