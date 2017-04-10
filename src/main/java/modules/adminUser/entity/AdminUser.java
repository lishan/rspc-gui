package modules.adminUser.entity;

import org.apache.commons.lang.math.RandomUtils;

import java.io.Serializable;

public class AdminUser implements Serializable {
	
	public static final String _DEFULTPWD="123456"; //用户默认密码
	
    private Integer id;

    private String userName;

    private String password=_DEFULTPWD;
    
    private String salt;
    

    public AdminUser() {
    	salt=String.valueOf(RandomUtils.nextInt(1000));
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


}
