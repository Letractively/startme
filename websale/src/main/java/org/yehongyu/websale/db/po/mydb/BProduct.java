package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Clob;
import java.math.BigDecimal;

/**
 *持久类
 */

public class BProduct implements Serializable {


    private long id;

    private BigDecimal price;

    private long num;

    private String introduce;

    private String picname;

    private Timestamp pubdate;

    private String pubman;

    private long pubtimes;

    private long state;

    private String catid;

    private String bookname;

    private String bookwriter;

    private String bookpublish;

    private Timestamp bookpubdate;
    
    private Timestamp saveindate;
    
    private long ispopulate;
    
    private long iscommend;

   /**
    * 取得id
    * @return 
    */
   public long getId () {
       return id;
   }
   /**
    * 设置id
    * @param 待设置id
    */
   public void setId(long id) {
       this.id = id;
   }
   
   /**
    * 取得price
    * @return 
    */
   public BigDecimal getPrice () {
       return price;
   }
   /**
    * 设置price
    * @param 待设置price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
   }
   
   /**
    * 取得num
    * @return 
    */
   public long getNum () {
       return num;
   }
   /**
    * 设置num
    * @param 待设置num
    */
   public void setNum(long num) {
       this.num = num;
   }
   
   /**
    * 取得picname
    * @return 
    */
   public String getPicname () {
       return picname;
   }
   /**
    * 设置picname
    * @param 待设置picname
    */
   public void setPicname(String picname) {
       this.picname = picname;
   }
   
   /**
    * 取得pubdate
    * @return 
    */
   public Timestamp getPubdate () {
       return pubdate;
   }
   /**
    * 设置pubdate
    * @param 待设置pubdate
    */
   public void setPubdate(Timestamp pubdate) {
       this.pubdate = pubdate;
   }
   
   /**
    * 取得pubman
    * @return 
    */
   public String getPubman () {
       return pubman;
   }
   /**
    * 设置pubman
    * @param 待设置pubman
    */
   public void setPubman(String pubman) {
       this.pubman = pubman;
   }
   
   /**
    * 取得pubtimes
    * @return 
    */
   public long getPubtimes () {
       return pubtimes;
   }
   /**
    * 设置pubtimes
    * @param 待设置pubtimes
    */
   public void setPubtimes(long pubtimes) {
       this.pubtimes = pubtimes;
   }
   
   /**
    * 取得state
    * @return 
    */
   public long getState () {
       return state;
   }
   /**
    * 设置state
    * @param 待设置state
    */
   public void setState(long state) {
       this.state = state;
   }
   
   /**
    * 取得catid
    * @return 
    */
   public String getCatid () {
       return catid;
   }
   /**
    * 设置catid
    * @param 待设置catid
    */
   public void setCatid(String catid) {
       this.catid = catid;
   }
   
   /**
    * 取得bookname
    * @return 
    */
   public String getBookname () {
       return bookname;
   }
   /**
    * 设置bookname
    * @param 待设置bookname
    */
   public void setBookname(String bookname) {
       this.bookname = bookname;
   }
   
   /**
    * 取得bookwriter
    * @return 
    */
   public String getBookwriter () {
       return bookwriter;
   }
   /**
    * 设置bookwriter
    * @param 待设置bookwriter
    */
   public void setBookwriter(String bookwriter) {
       this.bookwriter = bookwriter;
   }
   
   /**
    * 取得bookpublish
    * @return 
    */
   public String getBookpublish () {
       return bookpublish;
   }
   /**
    * 设置bookpublish
    * @param 待设置bookpublish
    */
   public void setBookpublish(String bookpublish) {
       this.bookpublish = bookpublish;
   }
   
   /**
    * 取得bookpubdate
    * @return 
    */
   public Timestamp getBookpubdate () {
       return bookpubdate;
   }
   /**
    * 设置bookpubdate
    * @param 待设置bookpubdate
    */
   public void setBookpubdate(Timestamp bookpubdate) {
       this.bookpubdate = bookpubdate;
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
/**
 * @return the saveindate
 */
public Timestamp getSaveindate() {
	return saveindate;
}
/**
 * @param saveindate the saveindate to set
 */
public void setSaveindate(Timestamp saveindate) {
	this.saveindate = saveindate;
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
   

}