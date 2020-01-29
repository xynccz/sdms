package com.honest.sdms.basedata.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.honest.sdms.Constants;
import com.honest.sdms.system.entity.SysLog;
import com.honest.sdms.system.service.ISysLogService;
import com.honest.sdms.tools.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 系统日志处理类：AOP切面处理类
 * @author beisi
 *
 */
@Aspect
@Component
public class SysLogAspect {

	@Autowired
	private ISysLogService sysLogService;
	private long startTimeMillis = 0; // 开始时间  
    private long endTimeMillis = 0; // 结束时间
	
	@Pointcut("execution(* com.honest.sdms.*.controller..*.*(..))")//切入点描述 这个是controller包的切入点
    public void logPointCut(){}//签名，可以理解成这个切入点的一个名称
	
	@Before("logPointCut()") //在切入点的方法run之前要干的
    public void logBeforePointCut(JoinPoint joinPoint) {
		startTimeMillis = System.currentTimeMillis();
	}
	
	//切面 配置通知
    @After("logPointCut()")
    public void logAfterPointCut(JoinPoint joinPoint) {
    	endTimeMillis = System.currentTimeMillis();
    	if(Constants.getCurrentSysUser() != null) {
    		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
    		
            if("/logManagement/search".equals(request.getRequestURI())){
            	return;
            }
            
    		//保存日志
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(Constants.getCurrentSysUser().getLoginName());
            sysLog.setExecuteTime(endTimeMillis-startTimeMillis);
            sysLog.setOperateUrl(request.getRequestURI());
            sysLog.setOperateType(request.getMethod());
            sysLog.setOrganizationId(Constants.getCurrentOrganizationId());
            sysLog.setIp(HttpUtil.getIpAddress(request));
            sysLog.setContent(JSONObject.fromObject(request.getParameterMap()).toString());
            
            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            SdmsLog myLog = method.getAnnotation(SdmsLog.class);
            if (myLog != null){
                String value = myLog.value();
                sysLog.setRemarks(value);//保存获取的操作
            }
            
            sysLogService.insert(sysLog);
    	}
    }
    
}
