package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;
import java.util.Set;

/**
 * 持久类
 */

public class SModule implements Serializable {

	private String id;

	private String modulename;

	private String upmodule;

	private String url;

	private long sortnum;

	private Set actions;

	public Set getActions() {
		return actions;
	}

	public void setActions(Set actions) {
		this.actions = actions;
	}

	/**
	 * 取得id
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param 待设置id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 取得modulename
	 * 
	 * @return
	 */
	public String getModulename() {
		return modulename;
	}

	/**
	 * 设置modulename
	 * 
	 * @param 待设置modulename
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	/**
	 * 取得upmodule
	 * 
	 * @return
	 */
	public String getUpmodule() {
		return upmodule;
	}

	/**
	 * 设置upmodule
	 * 
	 * @param 待设置upmodule
	 */
	public void setUpmodule(String upmodule) {
		this.upmodule = upmodule;
	}

	/**
	 * 取得url
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url
	 * 
	 * @param 待设置url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 取得sortnum
	 * 
	 * @return
	 */
	public long getSortnum() {
		return sortnum;
	}

	/**
	 * 设置sortnum
	 * 
	 * @param 待设置sortnum
	 */
	public void setSortnum(long sortnum) {
		this.sortnum = sortnum;
	}


}