package com.honest.sdms;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.honest.sdms.system.entity.SysUser;
import com.honest.sdms.tools.DateTimeUtil;

public class Constants {
	 
	public static Pattern SPLIT = Pattern.compile(",");
	public static final Integer BUTTON = 3;//按钮
	public static final Integer MENU = 2;//菜单
	public static final Integer MODEL = 1;//模块
	public static final String DEFAULT_PASSWORD = "123456";
	public static final Long DEFAULT_ORGANIZATIONID = 360L;
	public static final Integer DEFAULT_ID = 1000;
	
	//系统主目录
	public static final String MAIN_PATH = "/Users/beisi/home" + File.separator + new DateTimeUtil().toString("yyyy") + File.separator;
	public static final String ORDER_PATH_KEY = "orderAttachmentPath";//保存订单附件的服务器地址key值
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	public static class FileType{
		public static final Long ORDER_TYPE = 1000L;//订单类型
	}
	
	public static class Status{
		public static final String Y = "Y";
		public static final String N = "N";
		public static final String P = "P";
		public static final String E = "E";
	}
	
	public static class IoType{
		public static final String PO_IN = "PO_IN";
		public static final String PO_OUT = "PO_OUT";
		public static final String PO_SHIP = "PO_SHIP";
	}
	
	//库存类别
	public static class InventoryType{
		public static final Long rawStock = 42L;//原料
		public static final Long product = 43L;//成品
		public static final Long unqualified = 44L;//次品
	}
	 
	 public static final String JWT_ERRCODE_EXPIRE = "TOKEN_ERRCODE_EXPIRE_TOKEN签名过期";//Token超时异常
	 public static final String JWT_ERRCODE_FAIL = "JWT_ERRCODE_FAIL_TOKEN签名验证不通过";//token校验失败
	 
	 public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";//验证码id
	 
	 public static final String TOKEN_NAME = "token";
	 public static final String TOKEN_HEAD = "X-Token";
	 public static final String SYS_USER = "sysuser";
	 
	 public static final String INPUT = "INPUT";
	 public static final String OUTPUT = "OUTPUT";
	 
	 public static final String ORGANIZATIONID = "organizationId";
	 public static final String DEFAULT_CAPTCHA_PARAM = "captcha"; //为的是页面表单提交验证码的参数
	 public static final String USERNAME = "userName";
	 
	 public static final String ADMINISTRATR = "administrator";
	 
	 public static final Long MODIFY = 0L;
	 public static final Long VIEW = 1L;
	 
	 public static final String ERROR_PAGE = "/jsp/error/error.jsp";
	 
	 /**
	  * ehcache缓存分类
	  * @author beisi
	  */
	 public static class EhcacheTypes{
		 public static final String dict_cache = "dictCache";
	 }
	 
	 /**
	  * 数据操作类型
	  * @author beisi
	  */
	 public static class Operator{
		 public static final String ADD = "add";
		 public static final String MODIFY = "modify";
		 public static final String DELETE = "delete";
		 public static final String SEARCH = "search";
		 public static final String UPDATE = "update";
	 }
	 
	 public static SysUser getCurrentSysUser(){
		 Subject subject = SecurityUtils.getSubject();//第一步:获取我们的主体
	     SysUser user = (SysUser) subject.getPrincipal();
	     return user;
	 }
	 
	 public static Long getCurrentOrganizationId() {
		 return Constants.getCurrentSysUser().getOrganizationId();
	 }
	 
	 /**
	  * 生成token载体
	  * @param tokenExpiration 超时时间，单位毫秒
	  * @return
	  */
	 public static Map<String, Object> getClaims(Long tokenExpiration){
		 SysUser user =  getCurrentSysUser();
		 Map<String, Object> claims = new HashMap<>(); 
		 claims.put("loginName", user.getLoginName());
		 claims.put("passWord", user.getLoginPassword());
		 claims.put("uuid", UUID.randomUUID());
		 if(tokenExpiration != null)
			 claims.put("tokenExpiration", tokenExpiration);
		 return claims;
	 }
	 
	 public static Map<String, Object> getClaims(){
		 return getClaims(null);
	 }
	 
}
