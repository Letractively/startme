package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class BCategory implements Serializable {


    private String id;

    private String catname;

    private String pcatid;

    private long sortnum;

    private long state;

   /**
    * ȡ��id
    * @return 
    */
   public String getId () {
       return id;
   }
   /**
    * ����id
    * @param ������id
    */
   public void setId(String id) {
       this.id = id;
   }
   
   /**
    * ȡ��catname
    * @return 
    */
   public String getCatname () {
       return catname;
   }
   /**
    * ����catname
    * @param ������catname
    */
   public void setCatname(String catname) {
       this.catname = catname;
   }
   
   /**
    * ȡ��pcatid
    * @return 
    */
   public String getPcatid () {
       return pcatid;
   }
   /**
    * ����pcatid
    * @param ������pcatid
    */
   public void setPcatid(String pcatid) {
       this.pcatid = pcatid;
   }
   
   /**
    * ȡ��sortnum
    * @return 
    */
   public long getSortnum () {
       return sortnum;
   }
   /**
    * ����sortnum
    * @param ������sortnum
    */
   public void setSortnum(long sortnum) {
       this.sortnum = sortnum;
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
   

}