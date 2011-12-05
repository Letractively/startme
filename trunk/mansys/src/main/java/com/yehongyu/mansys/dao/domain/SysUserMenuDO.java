package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;

/**
 * sys_user_menu
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuDO extends BaseDO {

	/** 序列化ID */
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
    * 获取属性:id
    * id
    * @return id
    */
   public Long getId() {
       return id;
   }
   /**
    * 设置属性:id
    * id
    * @param id
    */
   public void setId(Long id) {
       this.id = id;
   }
	
   /**
    * 获取属性:userid
    * userid
    * @return userid
    */
   public Long getUserid() {
       return userid;
   }
   /**
    * 设置属性:userid
    * userid
    * @param userid
    */
   public void setUserid(Long userid) {
       this.userid = userid;
   }
	
   /**
    * 获取属性:menuid
    * menuid
    * @return menuid
    */
   public Long getMenuid() {
       return menuid;
   }
   /**
    * 设置属性:menuid
    * menuid
    * @param menuid
    */
   public void setMenuid(Long menuid) {
       this.menuid = menuid;
   }
	
   /**
    * 获取属性:gmtCreate
    * gmt_create
    * @return gmtCreate
    */
   public Timestamp getGmtCreate() {
       return gmtCreate;
   }
   /**
    * 设置属性:gmtCreate
    * gmt_create
    * @param gmtCreate
    */
   public void setGmtCreate(Timestamp gmtCreate) {
       this.gmtCreate = gmtCreate;
   }
	
   /**
    * 获取属性:gmtModified
    * gmt_modified
    * @return gmtModified
    */
   public Timestamp getGmtModified() {
       return gmtModified;
   }
   /**
    * 设置属性:gmtModified
    * gmt_modified
    * @param gmtModified
    */
   public void setGmtModified(Timestamp gmtModified) {
       this.gmtModified = gmtModified;
   }

}