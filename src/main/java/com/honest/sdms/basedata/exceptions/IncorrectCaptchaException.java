package com.honest.sdms.basedata.exceptions;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码错误异常类
 * @author beisi
 *
 */
public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -1774041055730226564L;

	public IncorrectCaptchaException() { 
		super(); 
	} 
	
	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}
}
