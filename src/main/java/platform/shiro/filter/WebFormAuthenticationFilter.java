/**
 * Copyright(C) 22008-2014 Technology LTD. All Rights Reserved. 版权所有(C)2008-2014
 * 西安睿通信息技术有限责任公司 公司名称：西安睿通信息技术有限责任公司 公司地址：中国，陕西省西安市 网址:http://www.ido56.com
 * <p>
 * Compiler: JDK1.6.0_04
 * <p>
 * 版本: ICT 1.0版
 * 文件名：com.ristone.modules.permission.realm.MiniCommunityFormAuthenticationFilter.java
 * <p>
 * 作者:杨一超
 * <p>
 * 创建时间: May 21, 20146:41:47 PM
 * <p>
 * 负责人: 杨一超
 * <p>
 * 部门: 物流组
 * <p>
 * <p>
 * 修改者：杨一超
 * <p>
 * 修改时间：May 21, 20146:41:47 PM
 * <p>
 */
package platform.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class WebFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		saveRequest(request);
		redirectToLogin(request, response);
	}
	
}
