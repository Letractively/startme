package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class BCategory implements Serializable {


    private String id;

    private String catname;

    private String pcatid;

    private long sortnum;

    private long state;

   /**
    * 取得id
    * @return 
    */
   public String getId () {
       return id;
   }
   /**
    * 设置id
    * @param 待设置id
    */
   public void setId(String id) {
       this.id = id;
   }
   
   /**
    * 取得catname
    * @return 
    */
   public String getCatname () {
       return catname;
   }
   /**
    * 设置catname
    * @param 待设置catname
    */
   public void setCatname(String catname) {
       this.catname = catname;
   }
   
   /**
    * 取得pcatid
    * @return 
    */
   public String getPcatid () {
       return pcatid;
   }
   /**
    * 设置pcatid
    * @param 待设置pcatid
    */
   public void setPcatid(String pcatid) {
       this.pcatid = pcatid;
   }
   
   /**
    * 取得sortnum
    * @return 
    */
   public long getSortnum () {
       return sortnum;
   }
   /**
    * 设置sortnum
    * @param 待设置sortnum
    */
   public void setSortnum(long sortnum) {
       this.sortnum = sortnum;
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
   

}