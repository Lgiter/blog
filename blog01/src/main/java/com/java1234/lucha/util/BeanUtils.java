package com.java1234.lucha.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class BeanUtils {
	
	/**
	 * 将对象转为Map<String,String>
	 * 用于向redis存入hash
	 * @param obj
	 * @return
	 */
	public static Map<String,String> object2Map(Object obj){
		if (obj == null) {
			return null;
		}
		Class<? extends Object> calzz = obj.getClass();
		Map<String,String> map = new HashMap<String, String>();
		Field[] fields = calzz.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			String getMethodName = "get" + StringUtils.capitalize(name);
			try {
				Method method = calzz.getMethod(getMethodName, new Class[0]);
				Object object = method.invoke(obj, new Object[0]);
				map.put(name, String.valueOf(object));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return map;
	}
}
