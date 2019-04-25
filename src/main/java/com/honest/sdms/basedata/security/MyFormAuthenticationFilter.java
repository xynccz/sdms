package com.honest.sdms.basedata.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.honest.sdms.Constants;
import com.honest.sdms.tools.StringUtil;

/**
 * 扩展 FormAuthenticationFilter类，首先覆盖 createToken方法，以便获取CaptchaUsernamePasswordToken实例；
 * 然后增加验证码校验方法doCaptchaValidate；
 * 最后覆盖Shiro的认证方法 xecuteLogin在原表单认证逻辑处理之前进行验证码校验。
 * @author beisi
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
	private static Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
	
	/**
	 * 获取验证码值
	 * @param request
	 * @return
	 */
	protected String getCaptcha(ServletRequest request) { 
		return WebUtils.getCleanParam(request, Constants.DEFAULT_CAPTCHA_PARAM); 
	}
	
	/**
	 * 获取组织账套值
	 * @param request
	 * @return
	 */
	protected Long getOrganizationId(ServletRequest request) { 
		String organization =  WebUtils.getCleanParam(request, Constants.ORGANIZATIONID); 
		if(!StringUtil.isNullOrEmpty(organization) && StringUtil.isDigit(organization))
		{
			return Long.parseLong(organization);
		}
		return 360L;//设置个默认值，为以后多账套系统保留功能
	}
	
	
	/**
	  * 最先执行的方法,对跨域提供支持
	  */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		  
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
		
		// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
		 if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			 httpServletResponse.setStatus(HttpStatus.OK.value()); return false; 
		 }
		return super.preHandle(request, response);
	}
	
	/**
     * 每次被authc拦截的url都会到这里来，这里用来处理 不注销之前已登录用户下，再次登录
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
	/**
     * 用户自定义验证方法，这里用来做验证码及账套的验证
     * 此方法第一次登录会进来，执行executeLogin方法前执行，验证通过返回false，验证不通过返回true
     * 验证失败，不会去执行executeLogin方法
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		logger.info("*************onAccessDenied***************");
		
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		
		//从session获取验证码，正确的验证码
        HttpSession session = httpServletRequest.getSession();
        String validate = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        
        //获取输入的验证码,验证失败，设置错误信息
        String myValidate = getCaptcha(request); 
        if (validate == null || myValidate == null || !validate.equalsIgnoreCase(myValidate)) {
            httpServletRequest.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "IncorrectCaptchaException");
            //拒绝访问
            return true;
        }
        
        //组织号为空，验证失败，设置错误信息
        Long organizationId = getOrganizationId(request);
        if(organizationId == null){
        	httpServletRequest.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "OrganizationException");
            //拒绝访问
            return true;
        }
        
        return super.onAccessDenied(request, response);
	}

	/**
	 * 登录认证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
		logger.info("*************executeLogin***************");
		
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}
	
	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,ServletResponse response) {
		String loginName = getUsername(request); 
		String password = getPassword(request); 
		String captcha = getCaptcha(request); 
		Long   organizationId = getOrganizationId(request);
		boolean rememberMe = isRememberMe(request); 
		return new CaptchaUsernamePasswordToken(loginName, password, organizationId, rememberMe, captcha);
	}
	
}
