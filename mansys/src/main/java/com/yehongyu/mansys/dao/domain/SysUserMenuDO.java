package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;

/**
 * sys_user_menu
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuDO extends BaseDO {

	/** ���л�ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Long id;
	/** userid **/
    private Long userid;
	/** menuid **/
    private Long menuid;
	/** gmt_create **/
    private Timestamp gmtCreate;
	/** gmt_modified **/
    private Timestamp gmtModified;

	
   /**
    * ��ȡ����:id
    * id
    * @return id
    */
   public Long getId() {
       return id;
   }
   /**
    * ��������:id
    * id
    * @param id
    */
   public void setId(Long id) {
       this.id = id;
   }
	
   /**
    * ��ȡ����:userid
    * userid
    * @return userid
    */
   public Long getUserid() {
       return userid;
   }
   /**
    * ��������:userid
    * userid
    * @param userid
    */
   public void setUserid(Long userid) {
       this.userid = userid;
   }
	
   /**
    * ��ȡ����:menuid
    * menuid
    * @return menuid
    */
   public Long getMenuid() {
       return menuid;
   }
   /**
    * ��������:menuid
    * menuid
    * @param menuid
    */
   public void setMenuid(Long menuid) {
       this.menuid = menuid;
   }
	
   /**
    * ��ȡ����:gmtCreate
    * gmt_create
    * @return gmtCreate
    */
   public Timestamp getGmtCreate() {
       return gmtCreate;
   }
   /**
    * ��������:gmtCreate
    * gmt_create
    * @param gmtCreate
    */
   public void setGmtCreate(Timestamp gmtCreate) {
       this.gmtCreate = gmtCreate;
   }
	
   /**
    * ��ȡ����:gmtModified
    * gmt_modified
    * @return gmtModified
    */
   public Timestamp getGmtModified() {
       return gmtModified;
   }
   /**
    * ��������:gmtModified
    * gmt_modified
    * @param gmtModified
    */
   public void setGmtModified(Timestamp gmtModified) {
       this.gmtModified = gmtModified;
   }

}