/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.bo.manage.product;

import java.util.Date;

/**
 * 【类说明】产品列表页面参数对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:24:38
 */
public class ProductParam {

    private String bookname;
    
    private String bookwriter;
    
    private String catid;

    private Date startdate;

    private Date enddate;
    
    private long ispopulate = -1;
    
    private long iscommend = -1;
    
    private long state = -1;

	/**
	 * @return the bookname
	 */
	public String getBookname() {
		return bookname;
	}

	/**
	 * @param bookname the bookname to set
	 */
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	/**
	 * @return the catid
	 */
	public String getCatid() {
		return catid;
	}

	/**
	 * @param catid the catid to set
	 */
	public void setCatid(String catid) {
		this.catid = catid;
	}

	/**
	 * @return the bookwriter
	 */
	public String getBookwriter() {
		return bookwriter;
	}

	/**
	 * @param bookwriter the bookwriter to set
	 */
	public void setBookwriter(String bookwriter) {
		this.bookwriter = bookwriter;
	}

	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the ispopulate
	 */
	public long getIspopulate() {
		return ispopulate;
	}

	/**
	 * @param ispopulate the ispopulate to set
	 */
	public void setIspopulate(long ispopulate) {
		this.ispopulate = ispopulate;
	}

	/**
	 * @return the iscommend
	 */
	public long getIscommend() {
		return iscommend;
	}

	/**
	 * @param iscommend the iscommend to set
	 */
	public void setIscommend(long iscommend) {
		this.iscommend = iscommend;
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
