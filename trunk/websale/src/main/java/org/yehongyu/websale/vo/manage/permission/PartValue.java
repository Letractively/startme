/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

/**
 * ����˵������ɫVO����
 * @author yehongyu.org
 * @version 1.0 2007-11-28 ����04:22:22
 */
public class PartValue{
	/**�����checked����*/
    private boolean checked;
    /**�����id*/
    private long roleid;
    /**�����name*/
    private String rolename;
    /**��ɫ��ע*/
    private String memo;
    /**��ɫ����*/
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
