package com.honest.sdms.basedata;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.honest.sdms.Constants;
import com.honest.sdms.tools.VerifyCodeUtils;

/**
 * 生成验证码servlet
 * @author beisi
 *
 */
@WebServlet(name = "AuthImage", urlPatterns = "/verifyCode")
public class VerifyCodeServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{
	static final long serialVersionUID = 1L;  
	  
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg"); 
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, verifyCode.toLowerCase());  
        //生成图片  
        VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), verifyCode);  
    }  
}
