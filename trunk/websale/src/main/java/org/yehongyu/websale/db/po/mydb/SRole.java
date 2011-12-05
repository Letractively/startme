package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SRole implements Serializable {


    private long id;

    private String rolename;

    private String memo;

    private long type;

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
    * 取得rolename
    * @return 
    */
   public String getRolename () {
       return rolename;
   }
   /**
    * 设置rolename
    * @param 待设置rolename
    */
   public void setRolename(String rolename) {
       this.rolename = rolename;
   }
   
   /**
    * 取得memo
    * @return 
    */
   public String getMemo () {
       return memo;
   }
   /**
    * 设置memo
    * @param 待设置memo
    */
   public void setMemo(String memo) {
       this.memo = memo;
   }
   
   /**
    * 取得type
    * @return 
    */
   public long getType () {
       return type;
   }
   /**
    * 设置type
    * @param 待设置type
    */
   public void setType(long type) {
       this.type = type;
   }
   

}