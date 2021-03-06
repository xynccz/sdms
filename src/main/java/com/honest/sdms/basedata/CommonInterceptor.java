package com.honest.sdms.basedata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.security.jwt.JavaWebToken;

/**
 * 系统全局所有请求拦截器
 * @author beisi
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);
    @Value("${jwt.token-expiration}")
    private Long tokenExpiration;
    
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     不会进入postHandle()和afteCompletion()方法，一般直接跳转页面处理
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */ 
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) 
			throws Exception {
		
		logger.info("*****url:{}; methodType:{}*****",request.getRequestURI(), request.getMethod());
		
		//XSS攻击：跨站脚本攻击,设置https的cookie可以预防xss攻击
		response.addHeader("Set-Cookie", "uid=112; Path=/; Secure; HttpOnly");
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if(obj == null)
		{
			response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
			return false;
		}else
		{
			/*
			 * 校验token，校验不通过判定为非法请求
			 */
			String token = request.getHeader(Constants.TOKEN_HEAD);
			JavaWebToken.TokenCheckResult tokenRst = JavaWebToken.validateJWT(token);
			if (tokenRst == null || !tokenRst.getIsSucess()) 
			{
				logger.error("*****token校验不通过,token:{}*****",token);
				SecurityUtils.getSubject().logout();
				if(Constants.JWT_ERRCODE_EXPIRE.equals(tokenRst.getErrorCode()))
				{
					response.sendError(HttpStatus.GATEWAY_TIMEOUT.value());
				}else 
				{
					response.sendError(HttpStatus.UNAUTHORIZED.value());
				}
				return false;
			}
		}
		
		setHeaderWithToken(response);
		return true;
	}
	
	/** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) 
			throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等，servlet部分已经结束所以不可以在这个方法做页面跳转的动作   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception{
		super.afterCompletion(request, response, handler, ex);
	}

	/**  
	 * 数据正常请求返回结果，更新token传到前台
	 * 更新思路：重新生成token，指定失效时间
	 * 
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等，servlet部分已经结束所以不可以在这个方法做页面跳转的动作   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */
	public void setHeaderWithToken( HttpServletResponse response){
		String updateToken = JavaWebToken.generateToken(Constants.getClaims(tokenExpiration));
		response.addHeader(Constants.TOKEN_NAME, updateToken);
		// 跨域的情况下，能够拿到token
        response.addHeader("Access-Control-Expose-Headers", Constants.TOKEN_NAME);
	}
}
