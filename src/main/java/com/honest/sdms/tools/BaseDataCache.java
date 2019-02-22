package com.honest.sdms.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存类
 * @author beisi
 *
 */
public class BaseDataCache {

	private static BaseDataCache instance;
	private static Map<Class<?>,Map<String,Method>> classMethodsPool = new HashMap<Class<?>,Map<String,Method>>();//记录class类的方法
	private static Map<Class<?>,Map<String,Field>> classFieldsPool = new HashMap<Class<?>,Map<String,Field>>();//记录class类的属性
	private static Map<Object,Object> cache;
	
	private BaseDataCache(){
		cache = new HashMap<Object,Object>();
	};
	
	public static synchronized BaseDataCache getInstance(){
		if(instance == null)
			instance = new BaseDataCache();
		return instance;
	}
	
	public Map<Object,Object> getCache(){
		return cache;
	}
	
	public static void parse(Class<?> c){
		//1、将class类中的属性Field缓存起来
		if(!classFieldsPool.containsKey(c)){
			Field[] fields = c.getDeclaredFields();
			Map<String,Field> fieldMap = new HashMap<String,Field>();
			for(Field field : fields){
				String name = field.getName().toLowerCase();
				fieldMap.put(name, field);
			}
			classFieldsPool.put(c, fieldMap);
		}
		
		//2、将class类属性对应的方法缓存起来
		if(!classMethodsPool.containsKey(c)){
			Map<String,Method> methodMap = new HashMap<String,Method>();
			Method[] methods = c.getDeclaredMethods();
			for(Method method : methods){
				methodMap.put(method.getName(), method);
			}
			classMethodsPool.put(c, methodMap);
		}
	}
	
	public static Map<String,Field> getClassFieldsMap(Class<?> c){
		parse(c);
		Map<String,Field> fieldsMap = classFieldsPool.get(c);
		return fieldsMap;
	}
	
	/**
	 * 获取字段的public类型的get方法
	 * @param c
	 * @param fieldName
	 * @return
	 */
	public static Method getGetMethod(Class<?> c,String fieldName){
		parse(c);
		Map<String,Method> map = classMethodsPool.get(c);
		Method method = map.get("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1));
		if(method == null){
			return null;
		}else{
			return Modifier.isPublic(method.getModifiers())?method:null;
		}
	}
	
	/**
	 * 获取字段对象
	 * @param c
	 * @param fieldName
	 * @return
	 */
	public static Field getField(Class<?> c,String fieldName){
		parse(c);
		Map<String,Field> map = classFieldsPool.get(c);
		Field field = map.get(fieldName.toLowerCase());
		if(field == null){
			return null;
		}else{
			return field;
		}
	}
	
	
}
