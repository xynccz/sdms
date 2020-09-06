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

import com.honest.sdms.tools.StringUtil;

/**
 * 快递鸟API服务服务
 * 
 * @author beisi
 *
 */
public class KdniaoApiServiceBak {
	private static final Logger logger = LoggerFactory.getLogger(KdniaoApiServiceBak.class);

	private static final String Character = "UTF-8";
	// 电商ID
	private static final String EBusinessID = "1574456";
	// 电商加密私钥，快递鸟提供，注意保管，不要泄漏
	private static final String AppKey = "d2c0d37d-07e3-4f8e-91a7-b5a1212d2385";

	// 快递鸟物流轨迹即时查询接口
	private static final String ReqURL = "http://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
	
	//测试请求url,快递鸟订阅推送2.0接口,正式环境地址：http://api.kdniao.com/api/dist
	private static final String ReqURL1 = "http://testapi.kdniao.com:8081/api/dist";
	
	//测试请求url,电子面单正式环境地址：http://api.kdniao.com/api/Eorderservice
	private static final String ReqURL2="http://testapi.kdniao.com:8081/api/Eorderservice";	

	//DEMO
		public static void main(String[] args) {
			KdniaoApiServiceBak api = new KdniaoApiServiceBak();
			try{
//				String result = api.orderOnlineByJson();
//				System.out.print(result);	
				
//				String result = api.orderTracesSubByJson();
//				System.out.print(result);
				
				String result = api.getOrderTracesByJson("YTO", "YT4711918387687");
				System.out.print(result);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	/**
     * Json方式 电子面单
	 * @throws Exception 
     */
	public String orderOnlineByJson() throws Exception{
		String requestData= "{'OrderCode': '012657700387'," +
                "'ShipperCode':'SF'," +
                "'PayType':1," +
                "'ExpType':1," +
                "'Cost':1.0," +
                "'OtherCost':1.0," +
                "'Sender':" +
                "{" +
                "'Company':'LV','Name':'Taylor','Mobile':'15018442396','ProvinceName':'上海','CityName':'上海','ExpAreaName':'青浦区','Address':'明珠路73号'}," +
                "'Receiver':" +
                "{" +
                "'Company':'GCCUI','Name':'Yann','Mobile':'15018442396','ProvinceName':'北京','CityName':'北京','ExpAreaName':'朝阳区','Address':'三里屯街道雅秀大厦'}," +
                "'Commodity':" +
                "[{" +
                "'GoodsName':'鞋子','Goodsquantity':1,'GoodsWeight':1.0}]," +
                "'Weight':1.0," +
                "'Quantity':1," +
                "'Volume':0.0," +
                "'Remark':'小心轻放'," +
                "'IsReturnPrintTemplate':1}";
		String result=sendPost(ReqURL2, setParams(requestData, "1007", "2"));	
		//根据公司业务处理返回的信息......
		
		return result;
	}
	
	/**
     * Json方式 查询订单物流轨迹
	 * @throws Exception 
     */
	public String getOrderTracesByJson(String expCode, String expNo) throws Exception{
		String requestData= "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";
		String result=sendPost(ReqURL, setParams(requestData, "1002", "2"));	
		
		//根据公司业务处理返回的信息......
		
		return result;
	}
	
	/**
     * Json方式  物流信息订阅
	 * @throws Exception 
     */
	public String orderTracesSubByJson() throws Exception{
		String requestData="{'OrderCode': 'SF201608081055208281'," +
                                "'ShipperCode':'SF'," +
                                "'LogisticCode':'3100707578976'," +
                                "'PayType':1," +
                                "'ExpType':1," +
                                "'CustomerName':'',"+
                                "'CustomerPwd':''," +
                                "'MonthCode':''," +
                                "'IsNotice':0," +
                                "'Cost':1.0," +
                                "'OtherCost':1.0," +
                                "'Sender':" +
                                "{" +
                                "'Company':'LV','Name':'Taylor','Mobile':'15018442396','ProvinceName':'上海','CityName':'上海','ExpAreaName':'青浦区','Address':'明珠路73号'}," +
                                "'Receiver':" +
                                "{" +
                                "'Company':'GCCUI','Name':'Yann','Mobile':'15018442396','ProvinceName':'北京','CityName':'北京','ExpAreaName':'朝阳区','Address':'三里屯街道雅秀大厦'}," +
                                "'Commodity':" +
                                "[{" +
                                "'GoodsName':'鞋子','Goodsquantity':1,'GoodsWeight':1.0}]," +
                                "'Weight':1.0," +
                                "'Quantity':1," +
                                "'Volume':0.0," +
                                "'Remark':'小心轻放'}";
		
		String result=sendPost(ReqURL1, setParams(requestData, "1008", "2"));	
		
		//根据公司业务处理返回的信息......
		
		return result;
	}
	
	private Map<String, String> setParams(String requestData, String requestType, String dataType) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("RequestData", urlEncoder(requestData, Character));
		params.put("EBusinessID", EBusinessID);
		params.put("RequestType", requestType);
		String dataSign=encrypt(requestData, AppKey, Character);
		params.put("DataSign", urlEncoder(dataSign, Character));
		params.put("DataType", dataType);
		return params;
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
