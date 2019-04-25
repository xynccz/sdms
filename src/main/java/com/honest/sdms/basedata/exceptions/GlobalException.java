package com.honest.sdms.basedata.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.honest.sdms.tools.StringUtil;

/**
 *  利用springmvc自动异常处理组件来管理系统异常
 *  
 *  CustomExceptionResolver为全局异常处理器,处理思路：
 *	解析出异常类型
 *	1、如果该异常类型是业务自定义的异常，直接取出异常信息，在错误页面展示
 *	2、如果该异常类型不是业务自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
 * @author beisi
 *
 */
public class HSExceptionResolver extends HandlerExceptionResolverComposite{
	private Logger logger = LoggerFactory.getLogger(HSExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.error("**************"+ex.getMessage()+"*************");
		StringUtil.writeStackTraceToLog(logger, ex);
		HSException customException = null;
		//如果抛出的是系统自定义的异常则直接转换
        if(ex instanceof HSException) {
            customException = (HSException) ex;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            customException = new HSException("系统未知错误,"+ex.getMessage());
        }
        
        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg", "<a href=\"javascript:history.back(-1)\">返回地球🌎</a>,"+customException.getMessage());
        modelAndView.setViewName("/error/error");//对应到的是jsp/error/error.jsp页面
        return modelAndView;
	}

	
}
