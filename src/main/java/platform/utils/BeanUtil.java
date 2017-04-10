/**
 * Copyright(C) 22008-2014   Technology LTD. All Rights Reserved.  
 * 版权所有(C)2008-2014 西安睿通信息技术有限责任公司 
 * 公司名称：西安睿通信息技术有限责任公司  
 * 公司地址：中国，陕西省西安市 
 * 网址:http://www.ido56.com
 * <p>
 * Compiler: JDK1.6.0_04
 * <p>
 * 版本: ICT 1.0版
 * 文件名：com.ristone.platform.util.BeanUtil.java
 * <p>
 * 作者:侯锋利
 * <p>
 * 创建时间: 2014-8-29 上午10:59:15
 * <p>
 * 负责人: (侯锋利)
 * <p>
 * 部门: 物流
 * <p>
 * <p> 
 * 修改者：
 * <p>
 * 修改时间：
 * <p>
 */
package platform.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hfl
 *
 */
public class BeanUtil {

	 // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean  
	
    public static <T> T  transMap2Bean(Map<String, Object> map, T t) {  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
                if (map.containsKey(key)) {  
                    Object value = map.get(key);  
                    // 得到property对应的setter方法  
                    Method setter = property.getWriteMethod();  
                    setter.invoke(t, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transMap2Bean Error " + e);  
        }  
        return t;  
  
    }  
  
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
    public static Map<String, Object> transBean2Map(Object obj) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    map.put(key, value);  
                }  
            }  
        } catch (Exception e) {  
            System.out.println("transBean2Map Error " + e);  
        }  
        return map;  
  
    }  
}
