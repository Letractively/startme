/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

/**
 * 【类说明】角色VO对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:22:22
 */
public class PartValue{
	/**对象的checked属性*/
    private boolean checked;
    /**对象的id*/
    private long roleid;
    /**对象的name*/
    private String rolename;
    /**角色备注*/
    private String memo;
    /**角色类型*/
    private long type;
    
    
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
}
