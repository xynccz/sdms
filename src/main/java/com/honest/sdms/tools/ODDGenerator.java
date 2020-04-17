package com.honest.sdms.tools;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *	编号策略
 * @fileName ODDGenerator.java
 * @Description
 */
public class ODDGenerator {
	//private static final FastDateFormat pattern = FastDateFormat.getInstance("yyyyMMddHHmmss");
	private static final AtomicInteger atomicInteger = new AtomicInteger(1);
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

	/**
	 * 【短码生成策略】
	 * @return
	 */
	public static String getD(String type) {
		System.out.println(threadLocal.toString()+"***"+atomicInteger.toString());
		Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
		threadLocal.set(type + Math.abs(uuidHashCode)+atomicInteger.getAndIncrement());
		return threadLocal.get().toString();
	}
	
}