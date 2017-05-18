package modules.adminUser.service;

import modules.adminUser.entity.AdminUser;
import modules.rspc.Rspc;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import platform.utils.HttpUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			path=path.replace("classes","lib").replaceFirst("/","");
			String cmd = String.format("java -Dtype=encrypt -Dusername=%s -Dpassword=%s -jar %sauth-1.0.jar", userName, pwd, path);
			log.info("请求登录cmd:"+cmd);
			Process exec = Runtime.getRuntime().exec(new String []{"cmd","/c",cmd});
			int i = exec.waitFor();
			InputStream inputStream = exec.getInputStream();
			BufferedInputStream in = new BufferedInputStream(inputStream);// 获得文件输入流
			BufferedReader reader = new BufferedReader (new InputStreamReader(in));
			while (reader.ready()) {
				String token =reader.readLine();
				log.info("获取到登录token:"+token);
				reader.close();
				String s = HttpUtils.get(apiHost.concat(Rspc.loginUrl).concat(token), null);
				log.info("请求登录返回："+s);
				if("Login Succes".equals(s)){
					AdminUser adminUser = new AdminUser();
					adminUser.setUserName(userName);
					adminUser.setPassword(pwd);
					return adminUser;
				}
			}
			if(null!=exec){
				exec.destroy();
			}
		} catch (Exception e) {
			log.error("根据用户名获得用户异常",e);
		}
		return null;
	}

}
