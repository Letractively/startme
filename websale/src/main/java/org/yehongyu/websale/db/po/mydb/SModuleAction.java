package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SModuleAction implements Serializable {


    private String id;

    private String actionname;

    private long actionshortid;

    private long sortnum;

    private String moduleid;

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
    * 取得actionname
    * @return 
    */
   public String getActionname () {
       return actionname;
   }
   /**
    * 设置actionname
    * @param 待设置actionname
    */
   public void setActionname(String actionname) {
       this.actionname = actionname;
   }
   
   /**
    * 取得actionshortid
    * @return 
    */
   public long getActionshortid () {
       return actionshortid;
   }
   /**
    * 设置actionshortid
    * @param 待设置actionshortid
    */
   public void setActionshortid(long actionshortid) {
       this.actionshortid = actionshortid;
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
    * 取得moduleid
    * @return 
    */
   public String getModuleid () {
       return moduleid;
   }
   /**
    * 设置moduleid
    * @param 待设置moduleid
    */
   public void setModuleid(String moduleid) {
       this.moduleid = moduleid;
   }
   

}