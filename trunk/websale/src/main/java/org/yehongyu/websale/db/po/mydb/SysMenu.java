package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *�־���
 */

public class SysMenu implements Serializable {


    private long id;

    private String menucode;

    private String menuname;

    private String menuurl;

    private long menulevel;

    private long isleaf;

    private String parentscode;

    private String rootcode;

    private long displayorder;

    private long status;

    private long issys;

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
    * ȡ��menucode
    * @return 
    */
   public String getMenucode () {
       return menucode;
   }
   /**
    * ����menucode
    * @param ������menucode
    */
   public void setMenucode(String menucode) {
       this.menucode = menucode;
   }
   
   /**
    * ȡ��menuname
    * @return 
    */
   public String getMenuname () {
       return menuname;
   }
   /**
    * ����menuname
    * @param ������menuname
    */
   public void setMenuname(String menuname) {
       this.menuname = menuname;
   }
   
   /**
    * ȡ��menuurl
    * @return 
    */
   public String getMenuurl () {
       return menuurl;
   }
   /**
    * ����menuurl
    * @param ������menuurl
    */
   public void setMenuurl(String menuurl) {
       this.menuurl = menuurl;
   }
   
   /**
    * ȡ��menulevel
    * @return 
    */
   public long getMenulevel () {
       return menulevel;
   }
   /**
    * ����menulevel
    * @param ������menulevel
    */
   public void setMenulevel(long menulevel) {
       this.menulevel = menulevel;
   }
   
   /**
    * ȡ��isleaf
    * @return 
    */
   public long getIsleaf () {
       return isleaf;
   }
   /**
    * ����isleaf
    * @param ������isleaf
    */
   public void setIsleaf(long isleaf) {
       this.isleaf = isleaf;
   }
   
   /**
    * ȡ��parentscode
    * @return 
    */
   public String getParentscode () {
       return parentscode;
   }
   /**
    * ����parentscode
    * @param ������parentscode
    */
   public void setParentscode(String parentscode) {
       this.parentscode = parentscode;
   }
   
   /**
    * ȡ��rootcode
    * @return 
    */
   public String getRootcode () {
       return rootcode;
   }
   /**
    * ����rootcode
    * @param ������rootcode
    */
   public void setRootcode(String rootcode) {
       this.rootcode = rootcode;
   }
   
   /**
    * ȡ��displayorder
    * @return 
    */
   public long getDisplayorder () {
       return displayorder;
   }
   /**
    * ����displayorder
    * @param ������displayorder
    */
   public void setDisplayorder(long displayorder) {
       this.displayorder = displayorder;
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
   

}