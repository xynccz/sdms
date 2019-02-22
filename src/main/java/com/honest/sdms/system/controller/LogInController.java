package com.honest.sdms.system.controller;



import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.honest.sdms.Constants;
import com.honest.sdms.system.entity.SysUserVO;
import com.honest.sdms.system.service.IRolesService;
import com.honest.sdms.tools.VerifyCodeUtils;

@RestController
public class LogInController {
	private static final Logger logger = LoggerFactory.getLogger(LogInController.class);
	
	@Autowired
	private IRolesService rolesService;

	@RequestMapping(value = "/login", method = {RequestMethod.POST},produces={"text/html;charset=UTF-8;","application/json;"})
	public String login(HttpServletRequest request,HttpSession session) {
		
		System.out.println(request.getParameter("username")+"====="+"*********"+request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
		
        //获取错误信息
        String exceptionClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(exceptionClassName != null){
            if(exceptionClassName.equals("org.apache.shiro.authc.UnknownAccountException")){
                request.setAttribute("errorMsg", "用户名或密码错误!");
            }else if(exceptionClassName.equals("IncorrectCaptchaException")) {
                request.setAttribute("errorMsg", "验证码或组织账套错误！");
            }else if(exceptionClassName.equals("OrganizationException")){
            	request.setAttribute("errorMsg", "账套不允许为空!");
            }
        }
        return "/login";
    }
	
	/**
     * 登陆成功后进入这里，获取用户信息和菜单权限
     * @param model
     * @param session
     * @return
	 * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/index",method = {RequestMethod.GET})
    public String index(Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        SysUserVO user = Constants.getCurrentSysUser();
        if(user == null){
        	return "redirect:/";
        }else{
//			model.addAttribute("menu", getCurrentMenus());
//        	setCookie(response);
        	System.out.println("*********进来了************dsfdfd");
            return "/main";
        }
        
    }
}
