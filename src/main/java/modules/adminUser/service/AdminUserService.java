package modules.adminUser.service;

import com.asiainfo.rspc.auth.authc.AuthenticateAdapter;
import modules.adminUser.entity.AdminUser;
import modules.rspc.Rspc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.utils.HttpUtils;

/**
 * 系统用户
 * @author Administrator
 *
 */
@Service
public class AdminUserService {
	@Value("#{config.api_host_name}")
	private String apiHost;

	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * 根据用户名获得用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public AdminUser getAdminUser(String userName,String pwd){
		String token = AuthenticateAdapter.generateToken(userName, pwd);
		HttpUtils.HttpRuest httpRuest = HttpUtils.get(apiHost.concat(Rspc.loginUrl).concat(token), null);
		if(httpRuest.getStatusCode()==HttpUtils.SUCCESS){
			AdminUser adminUser = new AdminUser();
			adminUser.setUserName(userName);
			adminUser.setPassword(pwd);
			adminUser.setSalt(token);
			return adminUser;
		}
		return null;
	}

}
