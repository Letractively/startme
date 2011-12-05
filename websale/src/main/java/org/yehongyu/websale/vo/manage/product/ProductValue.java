/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.vo.manage.product;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;

/**
 * 【类说明】产品对象
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午04:21:58
 */
public class ProductValue{

	private long id;
	
	private String bookname;
	
	private BigDecimal price;
	
	private long num;
	
	private String introduce;
	
	private String picname;
	
	private FileItem fipic;
	
	private String imgpath;
	
	private Date pubdate;
	
	private String pubman;
	
//	private long pubtimes;
//	
	private long state;
	
	private String catid;
	
	private String bookwriter;
	
	private String bookpublish;
	
	private Date bookpubdate;
    
//    private Date saveindate;
    
    private long ispopulate;
    
    private long iscommend;

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
	 * @return the bookpubdate
	 */
	public Date getBookpubdate() {
		return bookpubdate;
	}

	/**
	 * @param bookpubdate the bookpubdate to set
	 */
	public void setBookpubdate(Date bookpubdate) {
		this.bookpubdate = bookpubdate;
	}

	/**
	 * @return the bookpublish
	 */
	public String getBookpublish() {
		return bookpublish;
	}

	/**
	 * @param bookpublish the bookpublish to set
	 */
	public void setBookpublish(String bookpublish) {
		this.bookpublish = bookpublish;
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
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the num
	 */
	public long getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(long num) {
		this.num = num;
	}

	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}

	/**
	 * @param picname the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
	}

	/**
	 * @return the pubdate
	 */
	public Date getPubdate() {
		return pubdate;
	}

	/**
	 * @param pubdate the pubdate to set
	 */
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	/**
	 * @return the pubman
	 */
	public String getPubman() {
		return pubman;
	}

	/**
	 * @param pubman the pubman to set
	 */
	public void setPubman(String pubman) {
		this.pubman = pubman;
	}

//	/**
//	 * @return the pubtimes
//	 */
//	public long getPubtimes() {
//		return pubtimes;
//	}
//
//	/**
//	 * @param pubtimes the pubtimes to set
//	 */
//	public void setPubtimes(long pubtimes) {
//		this.pubtimes = pubtimes;
//	}

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
//
//	/**
//	 * @return the saveindate
//	 */
//	public Date getSaveindate() {
//		return saveindate;
//	}
//
//	/**
//	 * @param saveindate the saveindate to set
//	 */
//	public void setSaveindate(Date saveindate) {
//		this.saveindate = saveindate;
//	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the fipic
	 */
	public FileItem getFipic() {
		return fipic;
	}

	/**
	 * @param fipic the fipic to set
	 */
	public void setFipic(FileItem fipic) {
		this.fipic = fipic;
	}

	/**
	 * @return the imgpath
	 */
	public String getImgpath() {
		return imgpath;
	}

	/**
	 * @param imgpath the imgpath to set
	 */
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
    
}
