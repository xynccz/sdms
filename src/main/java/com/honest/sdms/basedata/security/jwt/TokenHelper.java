package com.honest.sdms.basedata.security.jwt;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honest.sdms.Constants;

/**
 * TokenHelper
 * Token校验类，做页面请求校验，每次生成的token缓存起来，当页面请求时做token校验，
 * 通过了校验就删除缓存token，如果页面重复请求就返回false
 * 
 */
public class TokenHelper{
    
    /**
     * 保存token值的默认命名空间
     */
    public static final String TOKEN_NAMESPACE = "honest.token";
    
    /**
     * 持有token名称的字段名
     */
    private static final Logger LOG = LoggerFactory.getLogger(TokenHelper.class);
    private static final Random RANDOM = new Random();
    private static Map<String, String> cacheToken = new HashMap<String, String>();
    
    /**
     * 使用随机字串作为token名字保存token
     * @param request
     * @return token
     */
    public static String setToken(HttpServletRequest request,Map<String, Object> claims){
        return setToken(request, generateGUID(),claims);
    }
    
    /**
     * 使用给定的字串作为token名字保存token
     * @param request
     * @param tokenName
     * @return token
     */
    private static String setToken(HttpServletRequest request, String tokenName,Map<String, Object> claims){
        String token = JavaWebToken.generateToken(claims, null);
        setCacheToken(request, tokenName, token);
        return token;
    }
    
    /**
     * 保存一个给定名字和值的token
     * 
     * @param request
     * @param tokenName
     * @param token
     */
    private static void setCacheToken(HttpServletRequest request, String tokenName, String token){
        try{
        	//服务端缓存当次token
        	cacheToken.put(buildTokenCacheAttributeName(tokenName), token);
        	
        	//客户端设置当次token
            request.setAttribute(Constants.TOKEN_NAME, tokenName);
            request.setAttribute(Constants.TOKEN, token);
        }catch(IllegalStateException e){
            String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
            LOG.error(msg, e);
            throw new IllegalArgumentException(msg);
        }
    }
    
    /**
     * 构建一个基于token名字的带有命名空间为前缀的token名字
     * @param tokenName
     * @return the name space prefixed session token name
     */
    public static String buildTokenCacheAttributeName(String tokenName){
        return TOKEN_NAMESPACE + "." + tokenName;
    }
    
    /**
     * 从请求域中获取给定token名字的token值
     * 
     * @param tokenName
     * @return the token String or null, if the token could not be found
     */
    public static String getToken(HttpServletRequest request, String tokenName){
        if(tokenName == null){
            return null;
        }
        
        Map<String,String[]> params = request.getParameterMap();
        String[] tokens = params.get(tokenName);
        if(tokens == null || tokens.length < 1){
            LOG.warn("Could not find token mapped to token name " + tokenName);
            return null;
        }
        
        return tokens[0];
    }
    
    /**
     * 从请求参数中获取token名字
     * @return the token name found in the params, or null if it could not be found
     */
    public static String getTokenName(HttpServletRequest request){
    	Map<String,String[]> params = request.getParameterMap();
        
        if(!params.containsKey(Constants.TOKEN_NAME)){
            LOG.warn("Could not find token name in params.");
            return null;
        }
        
        String[] tokenNames = params.get(Constants.TOKEN_NAME);
        if((tokenNames == null) || (tokenNames.length < 1)){
            LOG.warn("Got a null or empty token name.");
            return null;
        }
        
        return tokenNames[0];
    }
    
    /**
     * 验证当前请求参数中的token是否合法，如果合法的token出现就会删除它，它不会再次成功合法的token
     * @return 验证结果
     */
    public static boolean validToken(HttpServletRequest request){
        String tokenName = getTokenName(request);
        if(tokenName == null){
            LOG.error("no token name found -> Invalid token ");
            return false;
        }
        
        String token = getToken(request, tokenName);
        TokenCheckResult isCheck = JavaWebToken.validateJWT(token);
        if(isCheck == null || !isCheck.getIsSucess()){
        	LOG.error("invalid.token for token name " + tokenName + " , does not match the token");
        	return false;
        }
        
        /*
         * 
         * 当次token服务端验证通过后，删除缓存token，客户端重复提交就报异常，因为服务端认为此token已经失效
         */
        String tokenKey = buildTokenCacheAttributeName(tokenName);
        if(cacheToken.get(tokenKey) == null)
        	return false;
        
        cacheToken.remove(tokenKey);
        return true;
    }
    
    public static String generateGUID(){
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    } 
    
}
