package com.yehongyu.mansys.dao.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * sys_menu
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuDO extends BaseDO {

	/** ���л�ID */
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
    * ��ȡ����:menucode
    * menucode
    * @return menucode
    */
   public String getMenucode() {
       return menucode;
   }
   /**
    * ��������:menucode
    * menucode
    * @param menucode
    */
   public void setMenucode(String menucode) {
       this.menucode = menucode;
   }
	
   /**
    * ��ȡ����:menuname
    * menuname
    * @return menuname
    */
   public String getMenuname() {
       return menuname;
   }
   /**
    * ��������:menuname
    * menuname
    * @param menuname
    */
   public void setMenuname(String menuname) {
       this.menuname = menuname;
   }
	
   /**
    * ��ȡ����:menuurl
    * menuurl
    * @return menuurl
    */
   public String getMenuurl() {
       return menuurl;
   }
   /**
    * ��������:menuurl
    * menuurl
    * @param menuurl
    */
   public void setMenuurl(String menuurl) {
       this.menuurl = menuurl;
   }
	
   /**
    * ��ȡ����:menulevel
    * menulevel
    * @return menulevel
    */
   public Integer getMenulevel() {
       return menulevel;
   }
   /**
    * ��������:menulevel
    * menulevel
    * @param menulevel
    */
   public void setMenulevel(Integer menulevel) {
       this.menulevel = menulevel;
   }
	
   /**
    * ��ȡ����:isleaf
    * isleaf
    * @return isleaf
    */
   public Integer getIsleaf() {
       return isleaf;
   }
   /**
    * ��������:isleaf
    * isleaf
    * @param isleaf
    */
   public void setIsleaf(Integer isleaf) {
       this.isleaf = isleaf;
   }
	
   /**
    * ��ȡ����:parentscode
    * parentscode
    * @return parentscode
    */
   public String getParentscode() {
       return parentscode;
   }
   /**
    * ��������:parentscode
    * parentscode
    * @param parentscode
    */
   public void setParentscode(String parentscode) {
       this.parentscode = parentscode;
   }
	
   /**
    * ��ȡ����:rootcode
    * rootcode
    * @return rootcode
    */
   public String getRootcode() {
       return rootcode;
   }
   /**
    * ��������:rootcode
    * rootcode
    * @param rootcode
    */
   public void setRootcode(String rootcode) {
       this.rootcode = rootcode;
   }
	
   /**
    * ��ȡ����:displayorder
    * displayorder
    * @return displayorder
    */
   public String getDisplayorder() {
       return displayorder;
   }
   /**
    * ��������:displayorder
    * displayorder
    * @param displayorder
    */
   public void setDisplayorder(String displayorder) {
       this.displayorder = displayorder;
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