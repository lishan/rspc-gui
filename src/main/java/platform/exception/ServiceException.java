package platform.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 同时通过 Spring 配置, 对异常进行统一捕获及响应处理
 * 
 * @author gggao
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;
	
	private String viewPath;

	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		this.viewPath="error/error";
	}
	public ServiceException(String message,String viewPath) {
		super(message);
		this.viewPath=viewPath;
	}
	public String getViewPath(){
		return viewPath;
	}
}
