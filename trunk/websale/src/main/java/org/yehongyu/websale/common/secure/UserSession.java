package org.yehongyu.websale.common.secure;

/**
 * ��̨��½�Ժ��¼��session����
 * ***��˵��***
 * @author yehy
 * @version 4.0 2007-4-12 15:53:51
 * @since 4.0
 */
public class UserSession {

    /** �û�Ψһ��ʶID */
    private long userid;
    /** �û��� */
	private String userName;
	/** ���� */
	private String password;
    /** �û����� */
    private String name;
    /** ��ϵ�绰 */
    private String phone;
    /** �ʼ���ַ */
	private String email;
    /** ���ڲ��� */
    private String department;
    /** ���˺� */
    private String admin;
    /** �û�ӵ�еĽ�ɫ�����ŷָ��Ľ�ɫ�ַ�����*/
    private String sroles;

    /**
     * ��ȡ�û���
     * @return �û���
     */
	public String getUserName() {
		return userName;
	}

    /**
     * �����û���
     * @param userName �û���
     */
	public void setUserName(String userName) {
		this.userName = userName;
	}

    /**
     * �����û�����
     * @return �û�����
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * �����û�����
     * @param password �û�����
     */
	public void setPassword(String password) {
		this.password = password;
	}
    
    /**
     * ��ȡ�û����ڲ�������
     * @return �û����ڲ�������
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * �����û����ڲ�������
     * @param depName �û����ڲ�������
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * ��ȡ�û�Email
     * @return �û�Email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * �����û�Email
     * @param email �û�Email
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
