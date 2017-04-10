package modules.adminUser.service;

import modules.adminUser.entity.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 系统用户
 * @author Administrator
 *
 */
@Service
public class AdminUserService {
	@Value("#{config.api_host_name}")
	private String apiHost;

	private Log log = LogFactory.getLog(this.getClass());

	/** 根据用户名获得用户
	 * @param userName
	 * @return
	 */
	public AdminUser getAdminUser(String userName){
//		String s = HttpUtils.get(apiHost.concat(Rspc.configUrl), null);
		return  new AdminUser();
	}
	/**
	 * 根据用户名获得用户
	 * @param userName
	 * @param pwd
	 * @return
	 */
	public AdminUser getAdminUser(String userName,String pwd){
		if(StringUtils.equals(userName,"admin")&&StringUtils.equals(pwd,"123456")){
			AdminUser adminUser = new AdminUser();
			adminUser.setUserName(userName);
			adminUser.setPassword(pwd);
			return adminUser;
		}
		return null;
	}

}
