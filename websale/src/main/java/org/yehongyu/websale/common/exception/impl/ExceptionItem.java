/*
 * $Id: ExceptionItem.java,v 1.1 2007/04/16 08:17:46 sunbm Exp $
 */
package org.yehongyu.websale.common.exception.impl;

/**
 * 
 * exception.xml里对应的一条配置错误异常项
 * @author sbm
 * @version 4.0 Apr 16, 2007
 * @since 4.0
 */
public class ExceptionItem {
	
	/**编码*/
	private String code;
	
	/**页面里要用到的提示信息*/
	private String pageMessage;
	
	/**调试信息*/
	private String debugMessage;
	
	/**中转页地址**/
	private String topageURL;
	
	/**是否要跳转到某页，只有这true,topageURL才有用*/
	private boolean istopage;

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return Returns the debugMessage.
	 */
	public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * @param debugMessage The debugMessage to set.
	 */
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	/**
	 * @return Returns the istopage.
	 */
	public boolean isIstopage() {
		return istopage;
	}

	/**
	 * @param istopage The istopage to set.
	 */
	public void setIstopage(boolean istopage) {
		this.istopage = istopage;
	}

	/**
	 * @return Returns the pageMessage.
	 */
	public String getPageMessage() {
		return pageMessage;
	}

	/**
	 * @param pageMessage The pageMessage to set.
	 */
	public void setPageMessage(String pageMessage) {
		this.pageMessage = pageMessage;
	}

	/**
	 * @return Returns the topageURL.
	 */
	public String getTopageURL() {
		return topageURL;
	}

	/**
	 * @param topageURL The topageURL to set.
	 */
	public void setTopageURL(String topageURL) {
		this.topageURL = topageURL;
	}

	
	
	
	
	
}
