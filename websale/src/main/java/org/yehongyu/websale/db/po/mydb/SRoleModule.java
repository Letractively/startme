package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class SRoleModule implements Serializable {


    private long id;

    private String actionids;

    private String moduleid;

    private long roleid;

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
    * ȡ��actionids
    * @return 
    */
   public String getActionids () {
       return actionids;
   }
   /**
    * ����actionids
    * @param ������actionids
    */
   public void setActionids(String actionids) {
       this.actionids = actionids;
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
   
   /**
    * ȡ��roleid
    * @return 
    */
   public long getRoleid () {
       return roleid;
   }
   /**
    * ����roleid
    * @param ������roleid
    */
   public void setRoleid(long roleid) {
       this.roleid = roleid;
   }
   

}