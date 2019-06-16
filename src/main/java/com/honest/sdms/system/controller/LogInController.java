package com.honest.sdms.system.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.security.jwt.JavaWebToken;
import com.honest.sdms.system.entity.SysUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class LogInController{
	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	@Value("${jwt.token-expiration}")
    private Long tokenExpiration;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST},produces={"application/json;charset=UTF-8;","application/json;"})
	@ResponseBody
	public String login(HttpServletRequest request) {
        //获取错误信息
        String exceptionClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String errorMsg = null;
        if(exceptionClassName != null){
            if(exceptionClassName.equals("org.apache.shiro.authc.UnknownAccountException")){
            	errorMsg = "用户名或密码错误，请检查";
            }else if(exceptionClassName.equals("IncorrectCaptchaException")) {
            	errorMsg = "验证码或组织账套错误，请检查";
            }else if(exceptionClassName.equals("OrganizationException")){
            	errorMsg = "账套不允许为空，请检查";
            }
        }
        
        JSONObject json = new JSONObject();
        json.put("errorMsg", errorMsg);
        return json.toString();
    }
	
	/**
     * 登陆成功后进入这里，获取用户信息和菜单权限
     */
	@RequestMapping(value = "/index", method = {RequestMethod.GET})
	@ResponseBody
    public String index(Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        JSONObject json = new JSONObject(); 
        String jwtToken = JavaWebToken.generateToken(Constants.getClaims(tokenExpiration));
        json.put(Constants.TOKEN_NAME, jwtToken);
        return json.toString();
    }
	
	@RequestMapping(value="/user/info", method = {RequestMethod.GET},produces={"application/json;charset=UTF-8;","application/json;"})
	public @ResponseBody JSONObject info(HttpServletRequest request) { 
		SysUser sysUser = Constants.getCurrentSysUser();
		return sysUser.getPromise();
	}
	
	@RequestMapping(value = "/getout", method = {RequestMethod.GET})
    public void getout() {
		logger.info("*****退出当前登录*****");
        SecurityUtils.getSubject().logout();
    }
	
	public String getPromise() {
		JSONObject json = new JSONObject();
		json.put("roles", "['admin']");
		JSONArray array = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("icon", "el-icon-lx-home");
		j.put("index", "dashboard");
		j.put("title", "系统首页");
		
		JSONObject j1 = new JSONObject();
		j1.put("icon", "el-icon-lx-cascades");
		j1.put("index", "table");
		j1.put("title", "基础表格");
		
		JSONObject j2 = new JSONObject();
		j2.put("icon", "el-icon-lx-cascades");
		j2.put("index", "2");
		j2.put("title", "拖拽组件");
		
		JSONArray subArray = new JSONArray();
		JSONObject subJ = new JSONObject();
		subJ.put("index", "drag");
		subJ.put("title","拖拽列表");
		
		JSONObject subJ1 = new JSONObject();
		subJ1.put("index", "dialog");
		subJ1.put("title","拖拽列表2");
		JSONObject subJ12 = new JSONObject();
		subJ12.put("index", "icon");
		subJ12.put("title","自定义图标");
		
		subArray.add(subJ);
		subArray.add(subJ1);
		subArray.add(subJ12);
		j2.put("subs", subArray);
		array.add(j);
		array.add(j1);
		array.add(j2);
		json.put("menulist", array);
		return json.toString();
	}
	
}
