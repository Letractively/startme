/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;


/**
 * ����˵������ʾǰ̨��ʾʱ��ֵ����,������ʾһ����ť,����˵ҪȨ�޿��Ƶĵ�
 * @author yehongyu.org
 * @version 1.0 2007-11-28 ����04:23:17
 */
public class ModuleActionValue {
	/**�Ƿ�ѡ��*/
	private boolean checked;
	/**��ť����*/
	private String actionname;
	/**��ť���*/
	private String actionid;
	/**����ģ��ı��*/
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
