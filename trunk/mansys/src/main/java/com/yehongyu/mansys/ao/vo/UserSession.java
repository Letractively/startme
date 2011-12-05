package com.yehongyu.mansys.ao.vo;

import java.io.Serializable;

/**
 * �û���½��Ϣ
 * @version 1.0 --- JDK1.4
 */
public class UserSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long  userid;		//�û�id
	private String username; 	//�û���¼��
	private String password; 	//�û�����
	private String pwdtxt;		//�û���������
	private String name; 		//�û�����
	private long issys; 		//�Ƿ�ϵͳ�û�
	private long status; 		//�Ƿ���Ч
	private String memo;		//��ע��Ϣ
	
	public long getIssys() {
		return issys;
	}
	public void setIssys(long issys) {
		this.issys = issys;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwdtxt() {
		return pwdtxt;
	}
	public void setPwdtxt(String pwdtxt) {
		this.pwdtxt = pwdtxt;
	}
	
}