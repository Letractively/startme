/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.bo.manage.web;


/**
 * 【类说明】公告页面参数对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:24:38
 */
public class ContentParam {
	
	private long conid;
	
	private long contype;
	
	private String content;
	
	private long state;

	/**
	 * @return the conid
	 */
	public long getConid() {
		return conid;
	}

	/**
	 * @param conid the conid to set
	 */
	public void setConid(long conid) {
		this.conid = conid;
	}

	/**
	 * @return the contype
	 */
	public long getContype() {
		return contype;
	}

	/**
	 * @param contype the contype to set
	 */
	public void setContype(long contype) {
		this.contype = contype;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the state
	 */
	public long getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(long state) {
		this.state = state;
	}

   
}
