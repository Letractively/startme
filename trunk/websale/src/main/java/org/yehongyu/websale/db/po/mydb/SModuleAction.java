package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class SModuleAction implements Serializable {


    private String id;

    private String actionname;

    private long actionshortid;

    private long sortnum;

    private String moduleid;

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
    * ȡ��actionname
    * @return 
    */
   public String getActionname () {
       return actionname;
   }
   /**
    * ����actionname
    * @param ������actionname
    */
   public void setActionname(String actionname) {
       this.actionname = actionname;
   }
   
   /**
    * ȡ��actionshortid
    * @return 
    */
   public long getActionshortid () {
       return actionshortid;
   }
   /**
    * ����actionshortid
    * @param ������actionshortid
    */
   public void setActionshortid(long actionshortid) {
       this.actionshortid = actionshortid;
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
    * ȡ��moduleid
    * @return 
    */
   public String getModuleid () {
       return moduleid;
   }
   /**
    * ����moduleid
    * @param ������moduleid
    */
   public void setModuleid(String moduleid) {
       this.moduleid = moduleid;
   }
   

}