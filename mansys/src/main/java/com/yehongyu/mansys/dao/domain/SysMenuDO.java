package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * sys_menu
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuDO extends BaseDO {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;
	
	private List<SysMenuDO> sysMenuDOList;

	public List<SysMenuDO> getSysMenuDOList() {
		return sysMenuDOList;
	}

	public void setSysMenuDOList(List<SysMenuDO> sysMenuDOList) {
		this.sysMenuDOList = sysMenuDOList;
	}
	
	public void addSysMenuDOList(SysMenuDO sysMenuDO) {
		this.sysMenuDOList.add(sysMenuDO);
	}

	/** id **/
    private Long id;
	/** menucode **/
    private String menucode;
	/** menuname **/
    private String menuname;
	/** menuurl **/
    private String menuurl;
	/** menulevel **/
    private Integer menulevel;
	/** isleaf **/
    private Integer isleaf;
	/** parentscode **/
    private String parentscode;
	/** rootcode **/
    private String rootcode;
	/** displayorder **/
    private String displayorder;
	/** status **/
    private Integer status;
	/** issys **/
    private Integer issys;
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
    * 获取属性:menucode
    * menucode
    * @return menucode
    */
   public String getMenucode() {
       return menucode;
   }
   /**
    * 设置属性:menucode
    * menucode
    * @param menucode
    */
   public void setMenucode(String menucode) {
       this.menucode = menucode;
   }
	
   /**
    * 获取属性:menuname
    * menuname
    * @return menuname
    */
   public String getMenuname() {
       return menuname;
   }
   /**
    * 设置属性:menuname
    * menuname
    * @param menuname
    */
   public void setMenuname(String menuname) {
       this.menuname = menuname;
   }
	
   /**
    * 获取属性:menuurl
    * menuurl
    * @return menuurl
    */
   public String getMenuurl() {
       return menuurl;
   }
   /**
    * 设置属性:menuurl
    * menuurl
    * @param menuurl
    */
   public void setMenuurl(String menuurl) {
       this.menuurl = menuurl;
   }
	
   /**
    * 获取属性:menulevel
    * menulevel
    * @return menulevel
    */
   public Integer getMenulevel() {
       return menulevel;
   }
   /**
    * 设置属性:menulevel
    * menulevel
    * @param menulevel
    */
   public void setMenulevel(Integer menulevel) {
       this.menulevel = menulevel;
   }
	
   /**
    * 获取属性:isleaf
    * isleaf
    * @return isleaf
    */
   public Integer getIsleaf() {
       return isleaf;
   }
   /**
    * 设置属性:isleaf
    * isleaf
    * @param isleaf
    */
   public void setIsleaf(Integer isleaf) {
       this.isleaf = isleaf;
   }
	
   /**
    * 获取属性:parentscode
    * parentscode
    * @return parentscode
    */
   public String getParentscode() {
       return parentscode;
   }
   /**
    * 设置属性:parentscode
    * parentscode
    * @param parentscode
    */
   public void setParentscode(String parentscode) {
       this.parentscode = parentscode;
   }
	
   /**
    * 获取属性:rootcode
    * rootcode
    * @return rootcode
    */
   public String getRootcode() {
       return rootcode;
   }
   /**
    * 设置属性:rootcode
    * rootcode
    * @param rootcode
    */
   public void setRootcode(String rootcode) {
       this.rootcode = rootcode;
   }
	
   /**
    * 获取属性:displayorder
    * displayorder
    * @return displayorder
    */
   public String getDisplayorder() {
       return displayorder;
   }
   /**
    * 设置属性:displayorder
    * displayorder
    * @param displayorder
    */
   public void setDisplayorder(String displayorder) {
       this.displayorder = displayorder;
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