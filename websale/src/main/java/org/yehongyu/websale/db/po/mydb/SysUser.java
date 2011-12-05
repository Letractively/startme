package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SysUser implements Serializable {


    private long id;

    private String username;

    private String password;

    private String name;

    private long issys;

    private long status;

    private String memo;

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
    * 取得username
    * @return 
    */
   public String getUsername () {
       return username;
   }
   /**
    * 设置username
    * @param 待设置username
    */
   public void setUsername(String username) {
       this.username = username;
   }
   
   /**
    * 取得password
    * @return 
    */
   public String getPassword () {
       return password;
   }
   /**
    * 设置password
    * @param 待设置password
    */
   public void setPassword(String password) {
       this.password = password;
   }
   
   /**
    * 取得name
    * @return 
    */
   public String getName () {
       return name;
   }
   /**
    * 设置name
    * @param 待设置name
    */
   public void setName(String name) {
       this.name = name;
   }
   
   /**
    * 取得issys
    * @return 
    */
   public long getIssys () {
       return issys;
   }
   /**
    * 设置issys
    * @param 待设置issys
    */
   public void setIssys(long issys) {
       this.issys = issys;
   }
   
   /**
    * 取得status
    * @return 
    */
   public long getStatus () {
       return status;
   }
   /**
    * 设置status
    * @param 待设置status
    */
   public void setStatus(long status) {
       this.status = status;
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
   

}