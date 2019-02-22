package com.honest.sdms.tools;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * md5加密和解密
 * @author beisi
 *
 */
@SuppressWarnings("restriction")
public class CryptUtil {   
    public static final String DES = "DES";   
    public static final String DESede = "DESede";   
    public static final String Blowfish = "Blowfish";   
    public static final String MD5 = "MD5";   
    public static final String SHA = "SHA";   
  
    static{   
        Security.addProvider(new com.sun.crypto.provider.SunJCE());   
    }   
  
    public static byte[] getKey(String algorithm) throws Exception{   
        KeyGenerator keygen = KeyGenerator.getInstance(algorithm);   
        keygen.init(448);   
        SecretKey deskey = keygen.generateKey();   
        return deskey.getEncoded();   
    }   
  
    public static byte[] encode(String algorithm, byte[] input, byte[] key) throws Exception{   
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, algorithm);   
        Cipher c1 = Cipher.getInstance(algorithm);   
        c1.init(Cipher.ENCRYPT_MODE, deskey);   
        byte[] cipherByte = c1.doFinal(input);   
        return cipherByte;   
    }   

    public static byte[] decode(String algorithm, byte[] input, byte[] key) throws Exception{   
        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, algorithm);   
        Cipher c1 = Cipher.getInstance(algorithm);   
        c1.init(Cipher.DECRYPT_MODE, deskey);   
        byte[] clearByte = c1.doFinal(input);   
        return clearByte;   
    }   

    public static String Encrypt(String message, String key) throws Exception{   
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
  
           return StringUtil.base64Encode(cipher.doFinal(message.getBytes("UTF-8")));   
    }   
    
    public static String Encrypt(String message) throws Exception{   
        return  Encrypt(message, "yy12");   
    }   
    
    public static String Decrypt(String message, String key)throws Exception{   
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);   
  
           return new String(cipher.doFinal(StringUtil.base64Decode(message)));   
    }   
    
    public static String Decrypt(String message)throws Exception{   
           return Decrypt(message, "yy12");   
    }   

    public static byte[] digest(String algorithm, byte[] input) throws Exception{   
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance(algorithm); //or "SHA-1"   
        alg.update(input);   
        byte[] digest = alg.digest();   
        return digest;   
    }   
  
  
    public static String toHex(byte[] b) {   
        StringBuffer buf = new StringBuffer(2 * b.length);   
        String s = null;   
        for (int n = 0;n < b.length;n++){   
            s = Integer.toHexString(b[n] & 0XFF);   
  
            if (s.length() == 1){   
                buf.append('0').append(s);   
            }else{   
                buf.append(s);   
            }   
        }   
  
        return buf.toString().toUpperCase();   
    }   
  
  
    public static final byte[] hex_to_byte = {   
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9,   
        0, 0, 0, 0, 0, 0, 0,   
        10, 11, 12, 13, 14, 15   
    };   
    
    public static byte[] toByte(String hex){   
        byte[] buf = new byte[hex.length() / 2];   
        for(int i = 0, ii = hex.length(), p = 0;i < ii;i += 2, p++){   
            buf[p] += hex_to_byte[(hex.charAt(i) - 48)] * 16;   
            buf[p] += hex_to_byte[(hex.charAt(i + 1) - 48)];   
        }   
  
        return buf;   
    }   
}  

