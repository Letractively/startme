package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class SRole implements Serializable {


    private long id;

    private String rolename;

    private String memo;

    private long type;

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
    * ȡ��rolename
    * @return 
    */
   public String getRolename () {
       return rolename;
   }
   /**
    * ����rolename
    * @param ������rolename
    */
   public void setRolename(String rolename) {
       this.rolename = rolename;
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
   

}