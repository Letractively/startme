package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
 */

public class SUser implements Serializable {


    private long id;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    private String department;

    private String admin;

    private long type;

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
    * 取得phone
    * @return 
    */
   public String getPhone () {
       return phone;
   }
   /**
    * 设置phone
    * @param 待设置phone
    */
   public void setPhone(String phone) {
       this.phone = phone;
   }
   
   /**
    * 取得email
    * @return 
    */
   public String getEmail () {
       return email;
   }
   /**
    * 设置email
    * @param 待设置email
    */
   public void setEmail(String email) {
       this.email = email;
   }
   
   /**
    * 取得department
    * @return 
    */
   public String getDepartment () {
       return department;
   }
   /**
    * 设置department
    * @param 待设置department
    */
   public void setDepartment(String department) {
       this.department = department;
   }
   
   /**
    * 取得admin
    * @return 
    */
   public String getAdmin () {
       return admin;
   }
   /**
    * 设置admin
    * @param 待设置admin
    */
   public void setAdmin(String admin) {
       this.admin = admin;
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