package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
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
    * ȡ��username
    * @return 
    */
   public String getUsername () {
       return username;
   }
   /**
    * ����username
    * @param ������username
    */
   public void setUsername(String username) {
       this.username = username;
   }
   
   /**
    * ȡ��password
    * @return 
    */
   public String getPassword () {
       return password;
   }
   /**
    * ����password
    * @param ������password
    */
   public void setPassword(String password) {
       this.password = password;
   }
   
   /**
    * ȡ��name
    * @return 
    */
   public String getName () {
       return name;
   }
   /**
    * ����name
    * @param ������name
    */
   public void setName(String name) {
       this.name = name;
   }
   
   /**
    * ȡ��phone
    * @return 
    */
   public String getPhone () {
       return phone;
   }
   /**
    * ����phone
    * @param ������phone
    */
   public void setPhone(String phone) {
       this.phone = phone;
   }
   
   /**
    * ȡ��email
    * @return 
    */
   public String getEmail () {
       return email;
   }
   /**
    * ����email
    * @param ������email
    */
   public void setEmail(String email) {
       this.email = email;
   }
   
   /**
    * ȡ��department
    * @return 
    */
   public String getDepartment () {
       return department;
   }
   /**
    * ����department
    * @param ������department
    */
   public void setDepartment(String department) {
       this.department = department;
   }
   
   /**
    * ȡ��admin
    * @return 
    */
   public String getAdmin () {
       return admin;
   }
   /**
    * ����admin
    * @param ������admin
    */
   public void setAdmin(String admin) {
       this.admin = admin;
   }
   
   /**
    * ȡ��type
    * @return 
    */
   public long getType () {
       return type;
   }
   /**
    * ����type
    * @param ������type
    */
   public void setType(long type) {
       this.type = type;
   }
   
   /**
    * ȡ��memo
    * @return 
    */
   public String getMemo () {
       return memo;
   }
   /**
    * ����memo
    * @param ������memo
    */
   public void setMemo(String memo) {
       this.memo = memo;
   }
   

}