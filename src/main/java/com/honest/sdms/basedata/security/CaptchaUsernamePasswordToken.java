package com.honest.sdms.basedata.security;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.honest.sdms.tools.StringUtil;

/**
 * Shiro 表单认证，页面提交的用户名密码等信息，用 UsernamePasswordToken 类来接收
 * @author beisi
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 3548220564731460612L;
	private String loginName;
	private String passWord;
	private Long organiaztionId;//组织号
	private String captcha;//验证码

	public CaptchaUsernamePasswordToken(String loginName, String passWord,Long organiaztionId,boolean rememberMe, String captcha) {
		super(loginName, StringUtil.encrypt(passWord), rememberMe);
		this.captcha = captcha;
		this.organiaztionId = organiaztionId;
		this.loginName = loginName;
		this.passWord = passWord;
	}
	
	public CaptchaUsernamePasswordToken(String loginName, String passWord,Long organiaztionId, String captcha) {
		super(loginName, StringUtil.encrypt(passWord));
		this.organiaztionId = organiaztionId;
		this.captcha = captcha;
		this.loginName = loginName;
		this.passWord = passWord;
	}
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Long getOrganiaztionId() {
		return organiaztionId;
	}

	public void setOrganiaztionId(Long organiaztionId) {
		this.organiaztionId = organiaztionId;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
