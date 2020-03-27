package test.demo.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

public class MapUtil {
	/**
	 * <p>实体类对象转map</p>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (obj == null) 
            return map;
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try { 
        	for (Field field : fields) {
	            field.setAccessible(true);
	            map.put(field.getName(), field.get(obj));
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
	/**
	 * <p>使用Introspector，map集合成javabean</p>
	 * @param map       map
	 * @param beanClass bean的Class类
	 * @return bean对象
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {
	    if (cn.hutool.core.map.MapUtil.isEmpty(map)) 
	        return null;
	    try {
	        T t = beanClass.newInstance();
	        BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors) {
	            Method setter = property.getWriteMethod();
	            if (setter != null) {
	                setter.invoke(t, map.get(property.getName()));
	            }
	        }
	        return t;
	    } catch (Exception ex) {
	        throw new RuntimeException();
	    }
	}
	/**
	 * <p>对参数map进行升序排序用&拼接</p>
	 * @param map
	 * @return
	 */
	public static String createParam(Map<String, Object> map) {
		try {
			if (map == null || map.isEmpty())
				return null;
			Object[] key = map.keySet().toArray();
			Arrays.sort(key);
			StringBuffer res = new StringBuffer(128);
			for (int i = 0; i < key.length; i++) 
				if(ObjectUtil.isNotNull(map.get(key[i])))
					res.append(key[i] + "=" + map.get(key[i]) + "&");
			String rStr = res.substring(0, res.length() - 1);
			return rStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
