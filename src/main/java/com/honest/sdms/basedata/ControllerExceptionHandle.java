package com.honest.sdms.basedata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.tools.StringUtil;

/**
 * controller层异常统一处理类
 * @author beisi
 *
 */
@ControllerAdvice
public class ControllerExceptionHandle {
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandle.class);
	private static final int ERROR = -500;
	
	/**
	 * 自定义异常捕获
	 * @param ex
	 * @return
	 */
	@ResponseBody
    @ExceptionHandler(value = HSException.class)
    public APIResponse<String> messageCenterExceptionHandler(HSException ex) {
		StringUtil.writeStackTraceToLog(logger, ex);
		return new APIResponse<String>(ERROR,"业务操作异常,请稍后再试......");
    }
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResponse<String> handleException(Exception e){
		StringUtil.writeStackTraceToLog(logger, e);
		return new APIResponse<String>(ERROR,"系统运行异常,请稍后再试......");
	}
	
	@ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public APIResponse<String> handleException(NullPointerException e){
		StringUtil.writeStackTraceToLog(logger, e);
		return new APIResponse<String>(ERROR,"系统异常：空指针异常,请稍后再试......");
	}
	
	@ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public APIResponse<String> handleException(RuntimeException e){
		StringUtil.writeStackTraceToLog(logger, e);
		return new APIResponse<String>(ERROR,"系统异常：运行时异常,请稍后再试......");
	}
	
}
