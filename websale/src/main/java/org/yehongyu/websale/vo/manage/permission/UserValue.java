/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

import java.util.List;

/**
 * 【类说明】用户值对象,用于显示一个前台的页面里的用户信息
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:21:58
 */
public class UserValue{

    /** 用户ID */
    private long userid;
    /** 用户名 */
    private String username;
    /** 用户密码 */
	private String password;
    /** 用户姓名 */
    private String name;
    /** 用户邮箱 */
	private String email;
	/** 用户所在的部门 */
	private String depname;
	/** 用户说明 */
	private String memo;
	/** 用户电话 */
	private String phone;
	/** 用户父账号 */
    private String admin; 
	/** 角色,如果是用户已经有的角色checked=true 元素为PartValue */
	private List<PartValue> part;
	
	

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    /**
     * @return Returns the id.
     */
    public long getUserid() {
        return userid;
    }

    /**
     * @param userid The id to set.
     */
    public void setUserid(long userid) {
        this.userid = userid;
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

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
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

	public List<PartValue> getPart() {
		return part;
	}

	public void setPart(List<PartValue> part) {
		this.part = part;
	}
    
}
