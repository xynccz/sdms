package com.honest.sdms.tools;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class ObjectUtil {
	
	/**
	 * 对象非空属性拷贝
	 * @param target 目标对象
	 * @param src 源对象
	 */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void copyProperties(Object src, Object target){
        BeanUtils.copyProperties(src, target);
    }
    
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
