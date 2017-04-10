package platform.shiro.listener;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;

public class SessionListener implements
		org.apache.shiro.session.SessionListener {
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void onExpiration(Session arg0) {
		log.info(arg0);
	}

	@Override
	public void onStart(Session arg0) {
		log.info(arg0);
	}

	@Override
	public void onStop(Session arg0) {
		log.info(arg0);
	}
}
