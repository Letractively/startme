/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db.bean;

import java.util.List;

/**
 * 【类说明】分页查询结果Bean
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:20:51
 */
public class Page {

	/** 查询结果 */
	private List lstResult;
	/** 分页信息Bean */
	private PageBean pageBean;
	
	/**
	 * (空)
	 */
	public Page() {}
	
	/**
	 * 根据查询结果、分页信息构造
	 * @param lstResult 查询结果
	 * @param pageBean 分页信息Bean
	 */
	public Page(List lstResult, PageBean pageBean) {
		this.lstResult = lstResult;
		this.pageBean = pageBean;
	}
	
	/**
	 * 取得查询结果
	 * @return 查询结果
	 */
	public List getLstResult() {
		return lstResult;
	}
	/**
	 * 设置查询结果
	 * @param lstResult 查询结果
	 */
	public void setLstResult(List lstResult) {
		this.lstResult = lstResult;
	}
	
	/**
	 * 取得分页信息Bean
	 * @return 分页信息Bean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * 设置分页信息Bean
	 * @param pageBean 分页信息Bean
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
