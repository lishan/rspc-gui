/*
 * 陕西识代运筹技术有限公司
 */

package platform.sys;

import platform.utils.DesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DefaultPropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class PropertiesPersis extends DefaultPropertiesPersister {
	private static Log log = LogFactory.getLog(PropertiesPersis.class);
	private final String _DEFAULTKEY="A1B2C3D4E5F60708";
	private String desKey;
	public PropertiesPersis(String key) {
		if(StringUtils.isBlank(key)||key.length()<8){
			log.info("配置文件解密用ＫＥＹ为空或长度小于8，试用默认ＫＥＹ");
			desKey = _DEFAULTKEY;
		}else{
			desKey = key;
		}
	}
	public PropertiesPersis() {
		desKey = _DEFAULTKEY;
	}
	@Override
	public void load(Properties props, InputStream is) throws IOException {
		Enumeration<Object> keys = props.keys();
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			String value = (String) props.get(key);
			if(value.matches("^\\[.+\\]$")){
				String realV=value.replaceAll("\\[(.+)?\\]", "$1");
				try {
					String decodeV= DesUtil.decodeValue(desKey, realV);
					props.setProperty((String) key, decodeV);
				} catch (Exception e) {
					log.error("配置文件解密失败", e);
				}
			}
		}
		super.load(props, is);
	}
	
}
