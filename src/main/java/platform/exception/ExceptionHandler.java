package platform.exception;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * spring 异常处理,可对异常做出处理
 * @author Administrator
 *
 */
@Service
public class ExceptionHandler implements HandlerExceptionResolver { 
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex.getMessage());  
		if(ex instanceof ServiceException ){
			ServiceException exception=(ServiceException) ex;
			 return new ModelAndView(exception.getViewPath(), model);  
		}
		return null;
	}

}
