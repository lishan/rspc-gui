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
 * 文件名：springUtil.SpringAop.java
 * <p>
 * 作者:侯锋利
 * <p>
 * 创建时间: 2014-6-25 下午02:34:10
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
package platform.springUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component("methodExecutionAdvice")
public class MethodExecutionAdvice {
		private Log log = LogFactory.getLog(this.getClass());
		
		@Pointcut("execution (* cc.bluemc.modules.*.service..*.*(..))")
		public void pointcut(){}
		
		//方法执行的前后调用  
	    @Around("pointcut()")  
	    public Object around(ProceedingJoinPoint point) throws Throwable{
	    	log.info("开始执行方法：");
	    	log.info(point.toLongString());
	    	log.info("参数："+StringUtils.join(point.getArgs(),','));
	        Object object = point.proceed();  
	        log.info("执行方法完成");  
	        return object;  
	    } 
}
