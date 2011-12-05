package com.yehongyu.mansys.ao.vo;

import java.io.Serializable;

/**
 * 用户登陆信息
 * @version 1.0 --- JDK1.4
 */
public class UserSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long  userid;		//用户id
	private String username; 	//用户登录名
	private String password; 	//用户密码
	private String pwdtxt;		//用户密码明文
	private String name; 		//用户名称
	private long issys; 		//是否系统用户
	private long status; 		//是否有效
	private String memo;		//备注信息
	
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