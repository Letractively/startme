/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

import java.util.List;

/**
 * ����˵����ģ��VO����,һ��ģ����N����ť����
 * �����ڽ�ɫ�����޸�ʱǰ̨ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-28 ����04:14:42
 */
public class ModuleValue {
	
	/**ģ���Ƿ�ѡ��*/
	private boolean checked;
	/**ģ����*/
	private String id;
	/**ģ������*/
	private String name;
	/**ģ���ַ*/
	private String url;
	/**ģ�鼶��1 ��ʾ�ǵ�һ��ģ��,2 ��ʾ�ǵ�2��ģ�� 3��ʾ�ǵ�3��ģ��*/	
	private int level=1;
	/**�Ƿ�Ҷ��*/
	private String isleaf;
	/**��ť�б�,Ԫ��ΪModuleActionValue*/
	List<ModuleActionValue> actions;
	
	public List<ModuleActionValue> getActions() {
		return actions;
	}
	public void setActions(List<ModuleActionValue> actions) {
		this.actions = actions;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
}
