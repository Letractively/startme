package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SRoleModule implements Serializable {


    private long id;

    private String actionids;

    private String moduleid;

    private long roleid;

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
    * 取得actionids
    * @return 
    */
   public String getActionids () {
       return actionids;
   }
   /**
    * 设置actionids
    * @param 待设置actionids
    */
   public void setActionids(String actionids) {
       this.actionids = actionids;
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
   
   /**
    * 取得roleid
    * @return 
    */
   public long getRoleid () {
       return roleid;
   }
   /**
    * 设置roleid
    * @param 待设置roleid
    */
   public void setRoleid(long roleid) {
       this.roleid = roleid;
   }
   

}