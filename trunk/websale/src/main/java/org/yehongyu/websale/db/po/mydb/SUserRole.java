package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SUserRole implements Serializable {


    private long id;

    private long userid;

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
    * 取得userid
    * @return 
    */
   public long getUserid () {
       return userid;
   }
   /**
    * 设置userid
    * @param 待设置userid
    */
   public void setUserid(long userid) {
       this.userid = userid;
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