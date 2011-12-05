/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;


/**
 * 【类说明】表示前台显示时的值对象,这个类表示一个按钮,或者说要权限控制的点
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:23:17
 */
public class ModuleActionValue {
	/**是否被选中*/
	private boolean checked;
	/**按钮名字*/
	private String actionname;
	/**按钮编号*/
	private String actionid;
	/**所属模块的编号*/
	private String modid;

	public String getActionid() {
		return actionid;
	}

	public void setActionid(String actionid) {
		this.actionid = actionid;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getModid() {
		return modid;
	}

	public void setModid(String modid) {
		this.modid = modid;
	}
	
	
}
