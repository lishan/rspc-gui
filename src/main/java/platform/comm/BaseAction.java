package platform.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import modules.adminUser.entity.AdminUser;
import platform.exception.ServiceException;
import platform.springUtil.SpringConfigUtil;

public class BaseAction {
		protected Logger log = Logger.getLogger(this.getClass());
		protected ActionResultMap resultMap = new ActionResultMap();
		protected String _RESULTMAP="resultMap";
		protected Pagination page = new Pagination();
		public static String _SESSION_USER="adminUser"; //session用户
		
		/**
		 * 获取项目目录
		 * @return
		 */
		protected String getBasePath(){
//			this.getClass().getResource("/").getPath();
			return SpringConfigUtil.getApplicationContext().getClassLoader().getResource("").getPath();
		}
	    /**
	     * 异常页面控制
	     * @param runtimeException
	     * @return
	     */
	    @ExceptionHandler(Exception.class)
	    public ModelAndView runtimeExceptionHandler(HttpServletRequest request,
	                                                HttpServletResponse response, Exception exception) {
	    	log.error("异常抛出", exception);
	    	ModelAndView modelAndView = new ModelAndView("error/error");
	    	//如果有必要，可在此处添加针对某些异常的处理
	    	if(exception instanceof ServiceException){
	    		ServiceException serviceException = (ServiceException)exception;
	    		modelAndView.setViewName(serviceException.getViewPath());
	    		modelAndView.addObject("msg",serviceException.getMessage());
	    	}else if(exception instanceof AuthorizationException){
				getSubject().logout();
	    	}
			return modelAndView;
	    }
	    protected AdminUser getAdminUser(){
	    	Subject subject = getSubject(); 
	    	AdminUser user= (AdminUser) subject.getPrincipal();
	    	return user;
	    }

	    /**
	     * 获取　Subject
	     * @return
	     */
	    protected Subject getSubject(){
	    	return  SecurityUtils.getSubject();
	    }

}
