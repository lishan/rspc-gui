package platform.cache.ehcach;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class EhcacheMethodInterceptor implements MethodInterceptor {
	
    private static final Logger log = Logger.getLogger(EhcacheMethodInterceptor.class);
    
    private Cache cache;

    public void afterPropertiesSet() throws Exception {
        log.info(cache + " A cache is required. Use setCache(Cache) to provide one.");
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object result;
        String cacheKey = getCacheKey(targetName, methodName, arguments);
        Element element = null;
        synchronized (this) {
            element = cache.get(cacheKey);
            if (element == null) {
                log.debug(cacheKey + "加入到缓存： " + cache.getName());
                // 调用实际的方法
                result = invocation.proceed();
                element = new Element(cacheKey, result);
                cache.put(element);
            } else {
                log.debug(cacheKey + "使用缓存： " + cache.getName());
            }
        }
        return element.getObjectValue();
    }

    /**

     * <b>function:</b> 返回具体的方法全路径名称 参数
     * @param targetName 全路径
     * @param methodName 方法名称
     * @param arguments 参数
     * @return 完整方法名称
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }

	public void setCache(Cache cache) {
		this.cache = cache;
	}
    
}
