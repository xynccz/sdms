package com.honest.sdms.tools;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json时间转换器
 * @author beisi
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor{
	private String format ="yyyy-MM-dd hh24:mi:ss";
	
	public JsonDateValueProcessor() {
	}
	
	public JsonDateValueProcessor(String format) {
		this.format = format;
	}
	
	@Override
	public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	@Override
	public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
		return process(paramObject);
	}

	private Object process(Object value){
        if(value instanceof Date || value instanceof Timestamp){  
            DateTimeUtil date = new DateTimeUtil((Date)value);
            return date.toString(format);
        } 
        return value == null ? "" : value.toString();  
    }
}
