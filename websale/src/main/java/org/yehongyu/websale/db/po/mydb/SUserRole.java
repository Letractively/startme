package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class SUserRole implements Serializable {


    private long id;

    private long userid;

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
    * ȡ��userid
    * @return 
    */
   public long getUserid () {
       return userid;
   }
   /**
    * ����userid
    * @param ������userid
    */
   public void setUserid(long userid) {
       this.userid = userid;
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