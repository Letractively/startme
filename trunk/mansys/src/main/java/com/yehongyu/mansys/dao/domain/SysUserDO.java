package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;

/**
 * sys_user
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserDO extends BaseDO {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;

	/** id **/
    private Long id;
	/** username **/
    private String username;
	/** password **/
    private String password;
	/** name **/
    private String name;
	/** issys **/
    private Integer issys;
	/** status **/
    private Integer status;
	/** memo **/
    private String memo;
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
    * 获取属性:username
    * username
    * @return username
    */
   public String getUsername() {
       return username;
   }
   /**
    * 设置属性:username
    * username
    * @param username
    */
   public void setUsername(String username) {
       this.username = username;
   }
	
   /**
    * 获取属性:password
    * password
    * @return password
    */
   public String getPassword() {
       return password;
   }
   /**
    * 设置属性:password
    * password
    * @param password
    */
   public void setPassword(String password) {
       this.password = password;
   }
	
   /**
    * 获取属性:name
    * name
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * 设置属性:name
    * name
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * 获取属性:issys
    * issys
    * @return issys
    */
   public Integer getIssys() {
       return issys;
   }
   /**
    * 设置属性:issys
    * issys
    * @param issys
    */
   public void setIssys(Integer issys) {
       this.issys = issys;
   }
	
   /**
    * 获取属性:status
    * status
    * @return status
    */
   public Integer getStatus() {
       return status;
   }
   /**
    * 设置属性:status
    * status
    * @param status
    */
   public void setStatus(Integer status) {
       this.status = status;
   }
	
   /**
    * 获取属性:memo
    * memo
    * @return memo
    */
   public String getMemo() {
       return memo;
   }
   /**
    * 设置属性:memo
    * memo
    * @param memo
    */
   public void setMemo(String memo) {
       this.memo = memo;
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