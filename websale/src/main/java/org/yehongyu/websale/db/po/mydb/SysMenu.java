package org.yehongyu.websale.db.po.mydb;

import java.io.Serializable;

/**
 *持久类
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
    * 取得id
    * @return 
    */
   public long getId () {
       return id;
   }
   /**
    * 设置id
    * @param 待设置id
    */
   public void setId(long id) {
       this.id = id;
   }
   
   /**
    * 取得menucode
    * @return 
    */
   public String getMenucode () {
       return menucode;
   }
   /**
    * 设置menucode
    * @param 待设置menucode
    */
   public void setMenucode(String menucode) {
       this.menucode = menucode;
   }
   
   /**
    * 取得menuname
    * @return 
    */
   public String getMenuname () {
       return menuname;
   }
   /**
    * 设置menuname
    * @param 待设置menuname
    */
   public void setMenuname(String menuname) {
       this.menuname = menuname;
   }
   
   /**
    * 取得menuurl
    * @return 
    */
   public String getMenuurl () {
       return menuurl;
   }
   /**
    * 设置menuurl
    * @param 待设置menuurl
    */
   public void setMenuurl(String menuurl) {
       this.menuurl = menuurl;
   }
   
   /**
    * 取得menulevel
    * @return 
    */
   public long getMenulevel () {
       return menulevel;
   }
   /**
    * 设置menulevel
    * @param 待设置menulevel
    */
   public void setMenulevel(long menulevel) {
       this.menulevel = menulevel;
   }
   
   /**
    * 取得isleaf
    * @return 
    */
   public long getIsleaf () {
       return isleaf;
   }
   /**
    * 设置isleaf
    * @param 待设置isleaf
    */
   public void setIsleaf(long isleaf) {
       this.isleaf = isleaf;
   }
   
   /**
    * 取得parentscode
    * @return 
    */
   public String getParentscode () {
       return parentscode;
   }
   /**
    * 设置parentscode
    * @param 待设置parentscode
    */
   public void setParentscode(String parentscode) {
       this.parentscode = parentscode;
   }
   
   /**
    * 取得rootcode
    * @return 
    */
   public String getRootcode () {
       return rootcode;
   }
   /**
    * 设置rootcode
    * @param 待设置rootcode
    */
   public void setRootcode(String rootcode) {
       this.rootcode = rootcode;
   }
   
   /**
    * 取得displayorder
    * @return 
    */
   public long getDisplayorder () {
       return displayorder;
   }
   /**
    * 设置displayorder
    * @param 待设置displayorder
    */
   public void setDisplayorder(long displayorder) {
       this.displayorder = displayorder;
   }
   
   /**
    * 取得status
    * @return 
    */
   public long getStatus () {
       return status;
   }
   /**
    * 设置status
    * @param 待设置status
    */
   public void setStatus(long status) {
       this.status = status;
   }
   
   /**
    * 取得issys
    * @return 
    */
   public long getIssys () {
       return issys;
   }
   /**
    * 设置issys
    * @param 待设置issys
    */
   public void setIssys(long issys) {
       this.issys = issys;
   }
   

}