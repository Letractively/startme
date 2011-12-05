package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;

/**
 * sys_user
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserDO extends BaseDO {

	/** ���л�ID */
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
    * ��ȡ����:username
    * username
    * @return username
    */
   public String getUsername() {
       return username;
   }
   /**
    * ��������:username
    * username
    * @param username
    */
   public void setUsername(String username) {
       this.username = username;
   }
	
   /**
    * ��ȡ����:password
    * password
    * @return password
    */
   public String getPassword() {
       return password;
   }
   /**
    * ��������:password
    * password
    * @param password
    */
   public void setPassword(String password) {
       this.password = password;
   }
	
   /**
    * ��ȡ����:name
    * name
    * @return name
    */
   public String getName() {
       return name;
   }
   /**
    * ��������:name
    * name
    * @param name
    */
   public void setName(String name) {
       this.name = name;
   }
	
   /**
    * ��ȡ����:issys
    * issys
    * @return issys
    */
   public Integer getIssys() {
       return issys;
   }
   /**
    * ��������:issys
    * issys
    * @param issys
    */
   public void setIssys(Integer issys) {
       this.issys = issys;
   }
	
   /**
    * ��ȡ����:status
    * status
    * @return status
    */
   public Integer getStatus() {
       return status;
   }
   /**
    * ��������:status
    * status
    * @param status
    */
   public void setStatus(Integer status) {
       this.status = status;
   }
	
   /**
    * ��ȡ����:memo
    * memo
    * @return memo
    */
   public String getMemo() {
       return memo;
   }
   /**
    * ��������:memo
    * memo
    * @param memo
    */
   public void setMemo(String memo) {
       this.memo = memo;
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