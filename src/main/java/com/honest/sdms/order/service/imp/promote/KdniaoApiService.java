package com.honest.sdms.order.service.imp.promote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.honest.sdms.basedata.config.SpringCommonConfigs;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.entity.OrderOnline;
import com.honest.sdms.order.entity.OrderTraces;
import com.honest.sdms.order.entity.OrderTrancesReturn;
import com.honest.sdms.order.service.IKdniaoApiService;
import com.honest.sdms.tools.StringUtil;

/**
 * 快递鸟API服务服务
 * @author beisi
 */
@Service
public class KdniaoApiService implements IKdniaoApiService{
	private static final Logger logger = LoggerFactory.getLogger(KdniaoApiService.class);
	@Autowired
	private SpringCommonConfigs springCommonConfigs;

	private static final String Character = "UTF-8";
	private static final String DATA_TYPE = "2";
	
	/**
	 * 查询订单物流轨迹
	 * @param expCode 快递公司
	 * @param expNo 快递单号
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrderTrancesReturn getOrderTraces(OrderTraces orderTraces) throws HSException{
		try{
			validOrder(orderTraces);
			String result = sendPost(springCommonConfigs.getOrderTracesUrl(), setParams(JSONObject.toJSONString(orderTraces), "1002"));	
			return JSONObject.parseObject(result, OrderTrancesReturn.class);
		}catch(Exception e){
			StringUtil.writeStackTraceToLog(logger, e);
			throw new HSException("获取物流信息出错，" + e.getMessage());
		}
	}

	/**
	 * 查询订单物流轨迹,增强型，需要花钱的
	 * @param expCode 快递公司
	 * @param expNo 快递单号
	 * @return
	 * @throws Exception
	 */
	@Override
	public OrderTrancesReturn getOrderTracesUpgrade(OrderTraces orderTraces) throws Exception{
		validOrder(orderTraces);
		String result = sendPost(springCommonConfigs.getOrderTracesUrl(), setParams(JSONObject.toJSONString(orderTraces), "8001"));	
		return JSONObject.parseObject(result, OrderTrancesReturn.class);
	}
	
	/**
     * 获取电子面单
	 * @throws Exception 
     */
	@Override
	public String orderOnlineByJson(OrderOnline requestData) throws Exception{
		String result=sendPost(springCommonConfigs.getOrderOnlineUrl(), setParams(JSONObject.toJSONString(requestData), "1007"));	
		return result;
	}
	
	/**
     * 物流信息订阅
	 * @throws Exception 
     */
	@Override
	public String orderTracesSubscribe(String requestData) throws Exception{
		String result=sendPost(springCommonConfigs.getOrderTrancesSubUrl(), setParams(requestData, "1008"));	
		return result;
	}
	
	/**
	 * 封装系统级参数 
	 * @param requestData 请求内容为JSON格式 R
	 * @param requestType 请求接口指令 R
	 * @param dataType DataType=2,请求、返回数据类型均为JSON 格式 R
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> setParams(String requestData, String requestType) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, Character));
		params.put("EBusinessID", springCommonConfigs.getEBusinessID());//用户 ID
		params.put("RequestType", requestType);
		String dataSign=encrypt(requestData, springCommonConfigs.getAppKey(), Character);
		params.put("DataSign", urlEncoder(dataSign, Character));//数据内容签名
		params.put("DataType", DATA_TYPE);
		return params;
	}
	
	/**
	 * CustomerName校验规则：
	 * ShipperCode 为 JD,必填,对应京东的青龙配送编码,也叫商家编码,格式:数字 +字母+数字,9 位数字加一个字母,共 10 位,举例:001K123450; 
	 * ShipperCode 为 SF,且快递单号非快递鸟渠道返回时,必填,对应收件人/寄件人手机号后四位
	 * @param orderTraces
	 * @throws HSException
	 */
	private void validOrder(OrderTraces orderTraces) throws HSException {
		if(StringUtil.isNullOrEmpty(orderTraces.getShipperCode()) 
				|| StringUtil.isNullOrEmpty(orderTraces.getLogisticCode())){
			throw new HSException("快递公司编码或快递单号不允许为空，请检查");
		}
		
		if("JD".equals(orderTraces.getShipperCode()) || "SF".equals(orderTraces.getShipperCode())){
			if(StringUtil.isNullOrEmpty(orderTraces.getCustomerName())){
				throw new HSException("ShipperCode为JD时,customerName不允许为空");
			}
		}
	}
	
	/**
	 * MD5加密
	 * @param str     内容
	 * @param charset 编码方式
	 * @throws Exception
	 */
	private String MD5(String str, String charset) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes(charset));
		byte[] result = md.digest();
		StringBuilder sb = new StringBuilder(32);
		for (int i = 0; i < result.length; i++) {
			int val = result[i] & 0xff;
			if (val <= 0xf) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * base64编码
	 * @param str     内容
	 * @param charset 编码方式
	 * @throws UnsupportedEncodingException
	 */
	private String base64(String str, String charset) throws UnsupportedEncodingException {
		String encoded = base64Encode(str.getBytes(charset));
		return encoded;
	}

	private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
		String result = URLEncoder.encode(str, charset);
		return result;
	}

	/**
	 * 电商Sign签名生成
	 * @param content  内容
	 * @param keyValue Appkey
	 * @param charset  编码方式
	 * @throws UnsupportedEncodingException ,Exception
	 * @return DataSign签名
	 */
	private String encrypt(String content, String keyValue, String charset)
			throws UnsupportedEncodingException, Exception {
		if(keyValue != null){
			return base64(MD5(content + keyValue, charset), charset);
		}
		return base64(MD5(content, charset), charset);
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url    发送请求的 URL
	 * @param params 请求的参数集合
	 * @return 远程资源的响应结果
	 */
	private String sendPost(String url, Map<String, String> params) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// POST方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), Character);
			// 发送请求参数
			if (params != null) {
				StringBuilder param = new StringBuilder();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (param.length() > 0) {
						param.append("&");
					}
					param.append(entry.getKey());
					param.append("=");
					param.append(entry.getValue());
				}
				out.write(param.toString());
			}
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Character));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			StringUtil.writeStackTraceToLog(logger, e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

	public static String base64Encode(byte[] data) {
		StringBuilder sb = new StringBuilder();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}
}
