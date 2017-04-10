package platform.springUtil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取 SpringFramework 中配置的 Bean 信息<BR>
 * 用于无 spring 注入的普通 java 文件中,获取 Spring 配置的相关对象
 * 
 * @author 
 *
 */
@Component("springConfigUtil")
public class SpringConfigUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if(SpringConfigUtil.applicationContext == null){
			SpringConfigUtil.applicationContext  = applicationContext;
		}
	}

	/**
	 * 获取 SpringFramework 的 applicationContext 对象
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取 SpringFramework 配置的某个 bean 对象
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
		
	}
}
