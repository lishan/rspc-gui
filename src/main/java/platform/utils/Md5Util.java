package platform.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Md5Util
{
	public static String md5(String str)
	{
		String s = str;
		if (s == null)
		{
			return "";
		}
		else
		{
			String value = null;
			MessageDigest md5 = null;
			try
			{
				md5 = MessageDigest.getInstance("MD5");
			}
			catch (NoSuchAlgorithmException ex)
			{
				Logger.getLogger(Md5Util.class.getName()).log(Level.SEVERE, null, ex);
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try
			{
				value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
			}
			catch (Exception ex)
			{
			}
			return value;
		}
	}

	/**
	 * 
	 * @param username
	 * @param originPassword
	 * @param random
	 * @return
	 */
	public static String encryptPassword(String username, String originPassword, String random)
	{
		return md5(username + originPassword + random);
	}

}
