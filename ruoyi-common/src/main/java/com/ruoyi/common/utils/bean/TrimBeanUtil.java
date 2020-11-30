package com.ruoyi.common.utils.bean;

import com.ruoyi.common.utils.HashKit;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TrimBeanUtil {
    private static final Logger log = LoggerFactory.getLogger(HashKit.class);

    /**
     * /**
     *
     * @Description: 将 类中的 类型为 String 的属性值中的,空格(包括制表符等其他)去掉
     * @Param: 要转换的类
     * @return: 转换完的类
     * @date: 2020/11/29 1:00
     */
    public static void trimBean(Object model) {
        //int a = 1/0;
        Class clazz = model.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getGenericType().toString().equals("class java.lang.String")) {
                try {
                    Method getMethod = clazz.getMethod("get" + getMethodName(field.getName()));
                    String value = null;// 调用getter方法获取属性值

                    value = (String) getMethod.invoke(model);
                    if (StringUtils.isNotBlank(value)) {
                        value = value.replaceAll("\\s*", "");
                        field.setAccessible(true);
                        field.set(model, value);
                    }
                } catch (IllegalAccessException e) {
                    log.info("[实体类去除空格失败]", e);
                } catch (InvocationTargetException e) {
                    log.info("[实体类去除空格失败]", e);
                } catch (NoSuchMethodException e) {
                    log.info("[实体类去除空格失败]", e);
                } catch (Exception e) {

                }
            }
        }
    }


    /*
     * @Description: 把一个字符串的第一个字母大写
     */
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
