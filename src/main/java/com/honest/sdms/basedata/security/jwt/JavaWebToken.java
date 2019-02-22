package com.honest.sdms.basedata.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honest.sdms.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * token操作类
 * Token 可以放在 URL、Cookie、请求头Auth或者body中以一种特定格式解析
 * 服务端一般在过滤器或拦截器中获取token信息进行验证
 * @author beisi
 *
 */
public class JavaWebToken {

	private static Logger logger = LoggerFactory.getLogger(JavaWebToken.class);
	
	private static final String Secret = "zhiyunkejicczkfd";//加密密匙
	
	//构造函数私有化防止静态类被实例化,使用这种方式保障全局唯一性  
    private JavaWebToken() {  
    } 
    
	/**
	 * 该方法使用HS512算法生成signKey
	 * @return
	 */
    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//      Key key = MacProvider.generateKey();
        return signingKey;
    }
    
    /**
     * 使用HS512签名算法和生成的signingKey最终的Token
     * @param claims 有效载荷 数据声明,其实就是一个Map，比如我们想放入用户名，可以简单的创建一个Map然后put进去就可以了,比如：
     * 			Map<String, Object> claims = new HashMap<>(); 
     * 			claims.put("username", username());
     * @param outTimeMillis超时时间(单位毫秒)
     * @return
     */
    public static String generateToken(Map<String, Object> claims, Long outTimeMillis) {
    	JwtBuilder builder = Jwts.builder().setClaims(claims)
    			.signWith(SignatureAlgorithm.HS512, getKeyInstance());
    	
    	//如果知道超时时间的话，就设置token超时时间
    	if (outTimeMillis != null && outTimeMillis >= 0) {
    		long expMillis = System.currentTimeMillis() + outTimeMillis;
    		Date exp = new Date(expMillis);
    		builder.setExpiration(exp);
    	}
        return builder.compact();
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static TokenCheckResult validateJWT(String token) {
    	if(token == null)
    		return null;
    	
    	TokenCheckResult checkResult = new TokenCheckResult();
        try {
        	Claims claims = parserToken(token);
            checkResult.setIsSucess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
        	logger.error(Constants.JWT_ERRCODE_EXPIRE);
            checkResult.setErrorCode(Constants.JWT_ERRCODE_EXPIRE);
            checkResult.setIsSucess(false);
        } catch (Exception e) {
            checkResult.setErrorCode(Constants.JWT_ERRCODE_FAIL);
            checkResult.setIsSucess(false);
        }
        return checkResult;
    }
    
    /**
     * 解析Token，同时也能验证Token，当验证失败返回null
     * @param jwt
     * @return
     */
    public static Claims parserToken(String jwt) {
    	Claims jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
    	return jwtClaims;
    }
    
    /**
	 * 生成带主题信息的token码
	 * @param id 设置token码id
	 * @param issuer发布者
	 * @param subject主题
	 * @param outTimeMillis超时时间
	 * @return
	 */
	public static String createAccessJwtToken(String id, String issuer, String subject, Long outTimeMillis){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, getKeyInstance());
		
		//如何知道超时时间的话，就设置token超时时间
		if (outTimeMillis != null && outTimeMillis >= 0) {
		    long expMillis = nowMillis + outTimeMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		 
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
}

/**
 * token校验结果类
 * @author beisi
 *
 */
class TokenCheckResult {
	private String errorCode;
	private Boolean isSucess;
	private Claims claims;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Boolean getIsSucess() {
		return isSucess;
	}
	public void setIsSucess(Boolean isSucess) {
		this.isSucess = isSucess;
	}
	public Claims getClaims() {
		return claims;
	}
	public void setClaims(Claims claims) {
		this.claims = claims;
	}
}
