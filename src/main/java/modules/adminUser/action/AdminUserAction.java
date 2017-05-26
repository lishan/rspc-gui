package modules.adminUser.action;

import modules.adminUser.entity.AdminUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import platform.comm.BaseAction;

@Controller
@RequestMapping("/admin/user")
public class AdminUserAction extends BaseAction{
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("toLogin")
	public String toLogin(){
		return "adminUser/login";
	}
	/**
	 * 登录
	 * @param user
	 * @param model
	 */
	@RequestMapping("login")
	public String login(AdminUser user,Model model,RedirectAttributes attributes) {
		 String msg ="";
		/*/
		 //未使用shiro
		if(null==user_T||!user_T.getPassword().equals(Md5Util.encryptPassword(user.getUserName(), user.getPassword(),user_T.getSalt()))){
			msg = "账号错误!";
			logger.info(msg);
			return null;
		}
		/*/
		 //使用shiro
		Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
	   
	    try {  
	        //4、登录，即身份验证  
	        subject.login(token);  
	    } 	catch (UnknownSessionException use) {
			msg = "异常会话!";
			logger.info(msg);
		}
		catch (UnknownAccountException ex) {
			msg = "账号错误!";
			logger.info(msg,ex);
		}
		catch (IncorrectCredentialsException ice) {
			msg = "密码错误!";
			logger.info(msg);
		}
		catch (AuthenticationException ae) {
			msg = "用户名或密码错误!";
			logger.info(msg);
		}
		catch (Exception e) {
			msg = "出现未知异常,请与系统管理员联系!";
			logger.info(msg);
		} finally{
			if(token != null){
				token.clear();
			}
		}
		if(!subject.isAuthenticated()){
			attributes.addAttribute("msg", "error");
			 return "redirect:toLogin";
		}
		//*/
	    return "redirect:/rspc/dashBoard";
	}
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();  
		subject.logout();
		return "redirect:toLogin";
	}
}
