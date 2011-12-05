package org.yehongyu.websale.common.secure;

/**
 * 后台登陆以后记录的session对象
 * ***类说明***
 * @author yehy
 * @version 4.0 2007-4-12 15:53:51
 * @since 4.0
 */
public class UserSession {

    /** 用户唯一标识ID */
    private long userid;
    /** 用户名 */
	private String userName;
	/** 密码 */
	private String password;
    /** 用户姓名 */
    private String name;
    /** 联系电话 */
    private String phone;
    /** 邮件地址 */
	private String email;
    /** 所在部门 */
    private String department;
    /** 父账号 */
    private String admin;
    /** 用户拥有的角色（逗号分隔的角色字符串）*/
    private String sroles;

    /**
     * 获取用户名
     * @return 用户名
     */
	public String getUserName() {
		return userName;
	}

    /**
     * 设置用户名
     * @param userName 用户名
     */
	public void setUserName(String userName) {
		this.userName = userName;
	}

    /**
     * 返回用户密码
     * @return 用户密码
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 设置用户密码
     * @param password 用户密码
     */
	public void setPassword(String password) {
		this.password = password;
	}
    
    /**
     * 获取用户所在部门名称
     * @return 用户所在部门名称
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * 设置用户所在部门名称
     * @param depName 用户所在部门名称
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * 获取用户Email
     * @return 用户Email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 设置用户Email
     * @param email 用户Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the userid.
     */
    public long getUserid() {
        return userid;
    }

    /**
     * @param userid The userid to set.
     */
    public void setUserid(long userid) {
        this.userid = userid;
    }

    /**
     * @return Returns the father.
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * @param admin The father to set.
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

	public String getSroles() {
		return sroles;
	}

	public void setSroles(String sroles) {
		this.sroles = sroles;
	}

}
