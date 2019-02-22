package com.honest.sdms.tools;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * JSON操作工具类
 * @author beisi
 *
 */
public class JsonUtil {

	/**
	 * object中包含date类型的转json
	 * @param bean
	 * @return
	 */
	public static JSONObject formatObjectWithDate(Object bean){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		return JSONObject.fromObject(bean, config);
	}
	
	/**
	 * object中包含date类型的转json
	 * @param bean
	 * @return
	 */
	public static JSONArray formatListWithDate(List<?> bean){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		return JSONArray.fromObject(bean, config);
	}
	
	/**
	 * Map转json
	 * @param map
	 * @return
	 */
	public static JSONObject formatMap(Map<Object, Object> map){
		JSONObject json = new JSONObject();
		json.putAll(map);
		return json;
	}
}
