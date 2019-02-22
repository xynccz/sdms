package com.honest.sdms.basedata;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.honest.sdms.Constants;

import com.honest.sdms.basedata.security.jwt.TokenHelper;

/**
 * 系统全局所有请求拦截器
 * @author beisi
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
    private Object lock = new Object();
    
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
		log.info(request.getMethod()+"==============执行顺序: 1、preHandle================"+request.getRequestURI());
		
		//XSS攻击：跨站脚本攻击,设置https的cookie可以预防xss攻击
		response.addHeader("Set-Cookie", "uid=112; Path=/; Secure; HttpOnly");
		
		//判断session超时，如果超时跳转登录页面
//		Subject subject = SecurityUtils.getSubject();
//		Object currentUser = subject.getPrincipal();
//		if(currentUser == null && !"/scdp/login".equals(request.getRequestURI())){
//			toAlert(response);
//			return false;
//		}
			
		return true;
        /*
         * 拦截器的配置拦截两类请求，一类是到页面的，一类是提交表单的。
		 * 		1、当页面的请求时，生成token的名字和token值，一份放到服务器端缓存中，一份放传给页面表单的隐藏域。
		 * 		2、当表单请求提交时，拦截器得到参数中的tokenName和token，然后验证token值，如果能匹配上，请求就通过，不能匹配上就不通过。
		 * 		3、可以设计防止表单重复提交本，原理服务端缓存当次请求的token，第一次提交后将服务端的当前token值清空，这样如果用户重复提交表单，传到后台服务端已经没有token值了，验证失败请求被驳回，
		 * token值来源，只有GET请求到后台，生成一个新的token值给前台，就是每次表单提交，token值都会不同
		 * 		
         * 这部分是做定制化页面token校验的，开发人员可以自定义哪些请求页面或表单做token验证
         * 在一定程度上可以防止网络攻击(因为每次页面提交自带token信息，这个是动态的)，
         */
        /*
         * String urlRequest = request.getRequestURI();  
         * String method = request.getMethod(); 
         * if(viewUrls.containsKey(urlRequest) && method.equals(viewUrls.get(urlRequest))){//get请求页面设置token
        	Map<String, Object> claims = new HashMap<>(); 
        	claims.put("url", urlRequest);
        	claims.put("currentTime", new DateTimeUtil().toString("yyyy-mm-dd hh:mi hh24:mi:ss") );
        	TokenHelper.setToken(request,claims);
        }else if(actionUrls.containsKey(urlRequest) && method.equals(actionUrls.get(urlRequest))){//post提交页面获取token
        	if(!handleToken(request, response, handler))//如果验证不通过，跳转error页面并返回false，不往下走
        		return false;
        }*/
	}
	
	/** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) 
			throws Exception {
	}
	
	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等，servlet部分已经结束所以不可以在这个方法做页面跳转的动作   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception{
	}
	
	protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{  
        synchronized(lock){  
            if(!TokenHelper.validToken(request)){  
            	log.info("未通过验证.......");  
            	redirectUrl(request, response, Constants.ERROR_PAGE, "<a href=\"javascript:history.back(-1)\">返回地球🌎</a>");  
            	return false;
            }
            log.info("通过验证...");  
            return true;
        }
    }
	
	/** 
     * 当出现一个非法令牌时调用 
	 * @throws IOException 
	 * @throws ServletException 
     */  
	protected void redirectUrl(HttpServletRequest request, HttpServletResponse response, String url, String errorMsg) throws ServletException, IOException{  
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher(url).forward(request, response);
    }
	
	//前台弹出alert框
	public void toAlert( HttpServletResponse response){
	    try {
	         response.setContentType("text/html;charset=UTF-8");
	         response.setCharacterEncoding("UTF-8");
	         OutputStreamWriter out=new OutputStreamWriter(response.getOutputStream());   
	         String msg="由于您长时间没有操作，session已过期，请重新登录！";
	         msg=new String(msg.getBytes("UTF-8"));
	         out.write("<meta http-equiv='Content-Type' content='text/html';charset='UTF-8'>");
	         out.write("<script>");
	         out.write("alert('"+msg+"');");
	         out.write("top.location.href = '/'; ");
	         out.write("</script>");
	         out.flush();
	         out.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
