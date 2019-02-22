package com.honest.sdms.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.slf4j.Logger;

@SuppressWarnings("restriction")
public class StringUtil {   
    /**  
     * replace s1 with s2 in source String  
     * @param source in which to find s1  
     * @param s1 the String to be replaced  
     * @param s2 the String used to replace  
     * @return the replaced String  
     */  
    public static String replace(String source, String s1, String s2){   
        return source.replace(s1, s2 == null?"":s2);   
    }   
  
    /**  
     * replace each item in s1 with the item in s2 whose index is the same  
     * @param source the source String to operate  
     * @param s1 an array holds all the String to be replaced  
     * @param s2 an array holds all the String used to replace  
     * @return the replaced String  
     */  
    public static String replace(String source, String[] s1, String[] s2){   
        for(int i = 0;i < s1.length;i++){   
            source = StringUtil.replace(source, s1[i], s2[i]);   
        }   
  
        return source;   
    }   
  
  
    /**  
     * 字符串替换
     * @param source  
     * @param s1  
     * @param ss  
     * @return  
     */  
    public static String replace(String source, String s1, String[] ss){   
        String[] ss1 = StringUtil.split(",", s1);   
        return replace(source, ss1, ss);   
    }   
  
  
    /**  
     * 格式化指定字符串
     * @param format  
     * @param s1  
     * @return  
     */  
    public static String format(String format, Object s1){   
        return replace(format, "{0}", s1 == null?"":s1.toString());   
    }   
    public static String format(String format, Object s1, Object s2){   
        return replace(format, new String[]{"{0}", "{1}"}, new String[]{   
            s1 == null?"":s1.toString(),   
            s2 == null?"":s2.toString()   
        });   
    }   
    public static String format(String format, Object s1, Object s2, Object s3){   
        return replace(format, new String[]{"{0}", "{1}", "{2}"}, new String[]{   
            s1 == null?"":s1.toString(),   
            s2 == null?"":s2.toString(),   
            s3 == null?"":s3.toString()   
        });   
    }   
    public static String format(String format, Object s1, Object s2, Object s3, Object s4){   
        return replace(format, new String[]{"{0}", "{1}", "{2}", "{3}"}, new String[]{   
            s1 == null?"":s1.toString(),   
            s2 == null?"":s2.toString(),   
            s3 == null?"":s3.toString(),   
            s4 == null?"":s4.toString()   
        });   
    }   
    public static String format(String format, Object s1, Object s2, Object s3, Object s4,Object s5){   
        return replace(format, new String[]{"{0}", "{1}", "{2}", "{3}","{4}"}, new String[]{   
            s1 == null?"":s1.toString(),   
            s2 == null?"":s2.toString(),   
            s3 == null?"":s3.toString(),   
            s4 == null?"":s4.toString(),   
            s5 == null?"":s5.toString()   
        });   
    }   
    public static String format(String format, Object s1, Object s2, Object s3, Object s4,Object s5,Object s6){   
        return replace(format, new String[]{"{0}", "{1}", "{2}", "{3}","{4}","{5}"}, new String[]{   
            s1 == null?"":s1.toString(),   
            s2 == null?"":s2.toString(),   
            s3 == null?"":s3.toString(),   
            s4 == null?"":s4.toString(),   
            s5 == null?"":s5.toString(),   
            s6 == null?"":s6.toString()   
        });   
    }   
    public static String format(String format, String[] ss){   
        for(int i = 0;ss != null && i < ss.length;i++){   
            format = replace(format, '{' + String.valueOf(i) + '}', ss[i]);   
        }   
        return format;   
    }   
    
    
    /**  
     * when given String is blank or blank after trim(), return null  
     * @param s  
     * @return  
     */  
    public static String noblank(String s){   
        if (s != null && "".equals(s.trim())){   
            return null;   
        }   
    
        return s;   
    }   
    
    
    public static String fill(String s, int n){   
        if (s == null || s.length() == 0){   
            return s;   
        }   
    
        StringBuffer buf = new StringBuffer(s.length() * n);   
    
        for(int i = 0;i < n;i++){   
            buf.append(s);   
        }   
    
        return buf.toString();   
    }   
    
    public static String fill(char c, int n){   
        StringBuffer buf = new StringBuffer(n);   
    
        for(int i = 0;i < n;i++){   
            buf.append(c);   
        }   
    
        return buf.toString();   
    }   
    
    
	private static sun.misc.BASE64Encoder base64Encoder = new sun.misc.BASE64Encoder(); 
    
    
	public static String base64Encode(byte[] buf){   
        return base64Encoder.encode(buf);   
    }   
    
    public static String base64Encode(String s, String enc){   
        if (s == null || s.length() == 0){   
            return s;   
        }   
        try{   
            s = base64Encode(s.getBytes(enc));   
        }catch(Exception e){   
            e.printStackTrace();   
        }   
        return s;   
    }   
    
	private static sun.misc.BASE64Decoder base64Decoder = new sun.misc.BASE64Decoder();

	public static byte[] base64Decode(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}
		try {
			return base64Decoder.decodeBuffer(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}   
    
    public static String base64Decode(String s, String enc){   
        if (s == null || s.length() == 0){   
            return s;   
        }   
   
        byte[] buf = base64Decode(s);   
        try{   
            return buf == null?s:new String(buf, enc);   
        }catch(Exception e){   
            e.printStackTrace();   
        }   
        return s;   
    }   
   
   
    /**  
     * 字符串拆分  
     * @param seperators  
     * @param list  
     * @return  
     */  
    public static String[] split(String seperators, String list) {   
     return split(seperators, list, false);   
    }   
   
    /**  
     * @param seperators  
     * @param list  
     * @param include  
     * @return  
     */  
    public static String[] split(String seperators, String list, boolean include) {   
	     StringTokenizer tokens = new StringTokenizer(list, seperators, include);   
	     String[] result = new String[ tokens.countTokens() ];   
	     int i = 0;   
	     while ( tokens.hasMoreTokens() ) {   
	         result[i++] = tokens.nextToken();   
	     }   
	     return result;   
	 }   
   
   
    /**  
     * @param strArray  
     * @param separator  
     * @return  
     */  
    public static String join(String[] strArray, char[] separator){   
        if (separator == null) separator = new char[0];   
        StringBuffer buf = new StringBuffer();   
        for(int i = 0;i < strArray.length;i++){   
            if (i > 0){   
                buf.append(separator);   
            }   
            buf.append(strArray[i]);   
        }   
        return buf.toString();   
    }   
   
    /**  
     * @param strArray  
     * @param separator  
     * @return  
     */  
    public static String join(String[] strArray, String separator){   
        return join(strArray, separator == null?null:separator.toCharArray());   
    }   
   
    /**  
     * @param strArray  
     * @param separator  
     * @return  
     */  
    public static String join(String[] strArray, char separator){   
        return join(strArray, new char[]{separator});   
    }   
   
   
    public static String javaish(String s, boolean wimpyCaps){   
        StringBuffer buf = new StringBuffer(s.length());   
        char list[] = s.toLowerCase().toCharArray();   
        for(int i = 0; i < list.length; i++){   
            if(i == 0 && !wimpyCaps){   
                buf.append(Character.toUpperCase(list[i]));   
            }else if(list[i] == '_' && (i+1) < list.length && i != 0){   
                buf.append(Character.toUpperCase(list[++i]));   
            }   
            else buf.append(list[i]);   
        }   
   
        return buf.toString();   
    }  
    
    public static void main(String[] args){   
//      System.out.println(StringUtil.base64Encode("DRP", "GBK"));   
    	
        String pwd = "123456";    
        System.out.println("pwd is :" + pwd);   
        System.out.println("encrypt pwd is :" + StringUtil.encrypt(pwd));   
        String aa = StringUtil.encrypt(pwd);   
        System.out.println("decrypt pwd is :" + StringUtil.decrypt(aa));   
           
      /*String title = "<bean:message key=\"sysuser.title\"/>";   
      String[] strSrc = {"<",">","\"","\\"};   
      String[] strDist = {"&lt;","&gt;","&quot;","&#39;"};   
      System.out.println("source :" + title);   
      System.out.println("new:" + StringUtil.replace(title,strSrc,strDist));*/   
    }   
  
  
    /**  
     * 判断字符串是否为null或空
     * @param s  
     * @return  
     */  
    public static boolean isNullOrEmpty(String s){   
        if (s == null)   
        {   
            return true;   
        }   
  
        s = s.trim();   
        if (s.length() == 0)   
        {   
            return true;   
        }   
  
        return false;   
    }   
  
    /*
     * 判断字符串是否为数字,整数不包含小数
     * @param str 传入的字符串 
     * @return 是整数返回true,否则返回false 
   */
     public static boolean isDigit(String str) {  
           Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
           return pattern.matcher(str).matches();  
     }
  
    /**  
     * @param text  
     * @param encodeCharset  
     * @return  
     */  
    public static String charset(String text, String encodeCharset)   
    {   
        return charset(text, null, encodeCharset);   
    }   
  
    /**  
     * @param text  
     * @param decodeCharset  
     * @param encodeCharset  
     * @return  
     */  
    public static String charset(String text, String decodeCharset, String encodeCharset)   
    {   
        try  
        {   
            text = new String(   
                    decodeCharset == null?text.getBytes():text.getBytes(decodeCharset),   
                    encodeCharset);   
        }   
        catch(Exception e)   
        {   
            e.printStackTrace();   
        }   
  
        return text;   
    }  
    
    private static final byte[] BLOWFISH_KEY = {   
        5, 3, 127, 23,   
        17, 29, 59, 61,   
        41, 43, 31, 37,   
        113, 11, 13, 19   
    };   
      
    /**  
     * 加密
     * @param text 
     * @return  
     */  
    public static String encrypt(String text){   
        try{   
            byte[] buf = CryptUtil.encode(CryptUtil.Blowfish, text.getBytes(), BLOWFISH_KEY);   
            return new String(StringUtil.base64Encode(buf));   
        }catch(Exception e){   
            e.printStackTrace();   
            return null;   
        }   
    }   
      
    /**  
     * 解密
     * @return  
     */  
    public static String decrypt(String text){   
        try{   
            byte[] buf = CryptUtil.decode(CryptUtil.Blowfish, StringUtil.base64Decode(text), BLOWFISH_KEY);   
            return new String(buf);   
        }catch(Exception e){   
            return null;   
        }   
    }   
      
    public static String encode(String text){   
        return StringUtil.replace(StringUtil.encrypt(text), "+,/,=", new String[]{"[", "]", "$"});   
    }   
      
    public static String decode(String text){   
        return StringUtil.decrypt(StringUtil.replace(text, "[,],$", new String[]{"+", "/", "="}));   
    }   
      
      
    /**  
     * @param s  
     * @return  
     */  
    public static String lowerField(String s){   
        if (s.length() == 1){   
            return s.toLowerCase();   
        }else{   
            return s.substring(0,1).toLowerCase() + s.substring(1);   
        }   
    }   
      
    /**  
     * @param s  
     * @return  
     */  
    public static String upperField(String s){   
        if (s.length() == 1){   
            return s.toUpperCase();   
        }else{   
            return s.substring(0,1).toUpperCase() + s.substring(1);   
        }   
    }   
    
    public static String prefix(Object source, int len, char prefix){   
        if (len <= 0){   
            return "";   
        }   
   
        String s;   
        if (source instanceof String){   
            s = (String)source;   
        }else{   
            s = source.toString();   
        }   
   
        StringBuffer buf = new StringBuffer();   
        for(int i = 0;i < len - s.length();i++){   
            buf.append(prefix);   
        }   
        buf.append(s);   
        return buf.toString();   
    }   
   
    public static String suffix(Object source, int len, char suffix){   
        if (len <= 0){   
            return "";   
        }   
   
        String s;   
        if (source instanceof String){   
            s = (String)source;   
        }else{   
            s = source.toString();   
        }   
   
        StringBuffer buf = new StringBuffer();   
        buf.append(s);   
        for(int i = 0;i < len - s.length();i++){   
            buf.append(suffix);   
        }   
        return buf.toString();   
    }     
   
    /**
     * 讲异常堆栈信息写入日志中
     * @param logger
     * @param exception
     */
   public static void writeStackTraceToLog(Logger logger,Exception e){
	   StringWriter sw = null;
	   PrintWriter pw = null;
	   try{
		   sw = new StringWriter();
           pw =  new PrintWriter(sw);
		   e.printStackTrace(pw);
		   pw.flush();
           sw.flush();
	   }finally{
		   if (sw != null) {
               try {
                   sw.close();
               } catch (IOException e1) {
                   e1.printStackTrace();
               }
           }
           if (pw != null) {
               pw.close();
           }
	   }
	   logger.error(sw.toString());
   }
   
}
