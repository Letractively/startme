/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

import java.util.List;

/**
 * ����˵�����û�ֵ����,������ʾһ��ǰ̨��ҳ������û���Ϣ
 * @author yehongyu.org
 * @version 1.0 2007-11-28 ����04:21:58
 */
public class UserValue{

    /** �û�ID */
    private long userid;
    /** �û��� */
    private String username;
    /** �û����� */
	private String password;
    /** �û����� */
    private String name;
    /** �û����� */
	private String email;
	/** �û����ڵĲ��� */
	private String depname;
	/** �û�˵�� */
	private String memo;
	/** �û��绰 */
	private String phone;
	/** �û����˺� */
    private String admin; 
	/** ��ɫ,������û��Ѿ��еĽ�ɫchecked=true Ԫ��ΪPartValue */
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
