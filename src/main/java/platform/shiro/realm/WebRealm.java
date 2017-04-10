package platform.shiro.realm;

import modules.adminUser.entity.AdminUser;
import modules.adminUser.service.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WebRealm extends AuthorizingRealm {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private AdminUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		AdminUser user =(AdminUser)  principals.fromRealm(
		         getName()).iterator().next(); 
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		 if( user != null ){ 
		      // 查询用户授权信息
		            //角色
		            List menus = new ArrayList();
					SecurityUtils.getSubject().getSession().setAttribute("menus", menus);
		      } 
		 return info; 
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		      UsernamePasswordToken token = (UsernamePasswordToken) authcToken; 
		      // 通过表单接收的用户名
		      String username = token.getUsername(); 
		      if(StringUtils.isBlank(username)){ 
		    	  throw new UnknownAccountException(); //如果用户名错误
		      } else{
		    	  AdminUser account;
					try {
						String pwd = new String (token.getPassword());
						
						account=userService.getAdminUser(username,pwd);
						if(  null==account ){
							throw new IncorrectCredentialsException();
				         }
			        	 return new SimpleAuthenticationInfo(account,pwd,this.getName());
				         
					}  catch (Exception e) {
						logger.error("webRealm 认证失败！", e);
					} 
		      }
			return null;
	}

}
