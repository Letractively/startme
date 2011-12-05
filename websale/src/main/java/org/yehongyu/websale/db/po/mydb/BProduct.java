package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Clob;
import java.math.BigDecimal;

/**
 *�־���
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
    * ȡ��id
    * @return 
    */
   public long getId () {
       return id;
   }
   /**
    * ����id
    * @param ������id
    */
   public void setId(long id) {
       this.id = id;
   }
   
   /**
    * ȡ��price
    * @return 
    */
   public BigDecimal getPrice () {
       return price;
   }
   /**
    * ����price
    * @param ������price
    */
   public void setPrice(BigDecimal price) {
       this.price = price;
   }
   
   /**
    * ȡ��num
    * @return 
    */
   public long getNum () {
       return num;
   }
   /**
    * ����num
    * @param ������num
    */
   public void setNum(long num) {
       this.num = num;
   }
   
   /**
    * ȡ��picname
    * @return 
    */
   public String getPicname () {
       return picname;
   }
   /**
    * ����picname
    * @param ������picname
    */
   public void setPicname(String picname) {
       this.picname = picname;
   }
   
   /**
    * ȡ��pubdate
    * @return 
    */
   public Timestamp getPubdate () {
       return pubdate;
   }
   /**
    * ����pubdate
    * @param ������pubdate
    */
   public void setPubdate(Timestamp pubdate) {
       this.pubdate = pubdate;
   }
   
   /**
    * ȡ��pubman
    * @return 
    */
   public String getPubman () {
       return pubman;
   }
   /**
    * ����pubman
    * @param ������pubman
    */
   public void setPubman(String pubman) {
       this.pubman = pubman;
   }
   
   /**
    * ȡ��pubtimes
    * @return 
    */
   public long getPubtimes () {
       return pubtimes;
   }
   /**
    * ����pubtimes
    * @param ������pubtimes
    */
   public void setPubtimes(long pubtimes) {
       this.pubtimes = pubtimes;
   }
   
   /**
    * ȡ��state
    * @return 
    */
   public long getState () {
       return state;
   }
   /**
    * ����state
    * @param ������state
    */
   public void setState(long state) {
       this.state = state;
   }
   
   /**
    * ȡ��catid
    * @return 
    */
   public String getCatid () {
       return catid;
   }
   /**
    * ����catid
    * @param ������catid
    */
   public void setCatid(String catid) {
       this.catid = catid;
   }
   
   /**
    * ȡ��bookname
    * @return 
    */
   public String getBookname () {
       return bookname;
   }
   /**
    * ����bookname
    * @param ������bookname
    */
   public void setBookname(String bookname) {
       this.bookname = bookname;
   }
   
   /**
    * ȡ��bookwriter
    * @return 
    */
   public String getBookwriter () {
       return bookwriter;
   }
   /**
    * ����bookwriter
    * @param ������bookwriter
    */
   public void setBookwriter(String bookwriter) {
       this.bookwriter = bookwriter;
   }
   
   /**
    * ȡ��bookpublish
    * @return 
    */
   public String getBookpublish () {
       return bookpublish;
   }
   /**
    * ����bookpublish
    * @param ������bookpublish
    */
   public void setBookpublish(String bookpublish) {
       this.bookpublish = bookpublish;
   }
   
   /**
    * ȡ��bookpubdate
    * @return 
    */
   public Timestamp getBookpubdate () {
       return bookpubdate;
   }
   /**
    * ����bookpubdate
    * @param ������bookpubdate
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