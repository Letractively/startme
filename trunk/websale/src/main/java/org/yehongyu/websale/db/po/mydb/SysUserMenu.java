package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SysUserMenu implements Serializable {


    private long id;

    private long userid;

    private long menuid;

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
    * 取得menuid
    * @return 
    */
   public long getMenuid () {
       return menuid;
   }
   /**
    * 设置menuid
    * @param 待设置menuid
    */
   public void setMenuid(long menuid) {
       this.menuid = menuid;
   }
   

}