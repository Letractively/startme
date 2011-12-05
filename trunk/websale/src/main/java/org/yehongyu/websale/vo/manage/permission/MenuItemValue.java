/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 【类说明】菜单栏对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:23:27
 */
public class MenuItemValue implements Comparable<Object>{
	/**菜单编号*/
	private String id;
	/**菜单名称*/
	private String text;
	/**菜单地址*/
	private String url;
	/**子菜单，无子菜单则当前为叶子菜单*/
    private List<MenuItemValue> subMenu = new ArrayList<MenuItemValue>();
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean equals(Object other) {
		if (!(other instanceof MenuItemValue)) {
			return false;
		}
		MenuItemValue castOther = (MenuItemValue) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public int compareTo(Object o) {
		if (o instanceof MenuItemValue){
			MenuItemValue c = (MenuItemValue) o;
			return this.id.compareTo(c.getId());
		}
		return 0;
	}

    /**
     * @return Returns the subMenu.
     */
    public List<MenuItemValue> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu The subMenu to set.
     */
    public void setSubMenu(List<MenuItemValue> subMenu) {
        this.subMenu = subMenu;
    }
		
}
