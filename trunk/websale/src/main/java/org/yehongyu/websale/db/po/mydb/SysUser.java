package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
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
    * ȡ��issys
    * @return 
    */
   public long getIssys () {
       return issys;
   }
   /**
    * ����issys
    * @param ������issys
    */
   public void setIssys(long issys) {
       this.issys = issys;
   }
   
   /**
    * ȡ��status
    * @return 
    */
   public long getStatus () {
       return status;
   }
   /**
    * ����status
    * @param ������status
    */
   public void setStatus(long status) {
       this.status = status;
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