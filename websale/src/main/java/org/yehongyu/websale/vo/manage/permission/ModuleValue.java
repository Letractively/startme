/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

import java.util.List;

/**
 * 【类说明】模块VO对象,一个模块有N个按钮对象
 * 用于在角色新增修改时前台页面
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:14:42
 */
public class ModuleValue {
	
	/**模块是否被选中*/
	private boolean checked;
	/**模块编号*/
	private String id;
	/**模块名称*/
	private String name;
	/**模块地址*/
	private String url;
	/**模块级别　1 表示是第一级模块,2 表示是第2级模块 3表示是第3级模块*/	
	private int level=1;
	/**是否叶子*/
	private String isleaf;
	/**按钮列表,元素为ModuleActionValue*/
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
