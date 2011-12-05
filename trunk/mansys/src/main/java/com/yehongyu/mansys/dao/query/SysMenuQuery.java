package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_menu查询对象
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuQuery extends BaseQuery {

	/** 序列化ID */
	private static final long serialVersionUID = 1L;
	
	/** ====================查询唯一单条记录使用==========================**/
	
	/**==============================批量查询、更新、删除时的Where条件设置==================================**/
	/** id **/
    private Long id;
	/**
    * 获取属性:id
    * id
    * @return id
    */
	public Long getId () {
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
	/** menucode **/
    private String menucode;
	/**
    * 获取属性:menucode
    * menucode
    * @return menucode
    */
	public String getMenucode () {
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
	/** menuname **/
    private String menuname;
	/**
    * 获取属性:menuname
    * menuname
    * @return menuname
    */
	public String getMenuname () {
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
	/** menuurl **/
    private String menuurl;
	/**
    * 获取属性:menuurl
    * menuurl
    * @return menuurl
    */
	public String getMenuurl () {
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
	/** menulevel **/
    private Integer menulevel;
	/**
    * 获取属性:menulevel
    * menulevel
    * @return menulevel
    */
	public Integer getMenulevel () {
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
	/** isleaf **/
    private Integer isleaf;
	/**
    * 获取属性:isleaf
    * isleaf
    * @return isleaf
    */
	public Integer getIsleaf () {
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
	/** parentscode **/
    private String parentscode;
	/**
    * 获取属性:parentscode
    * parentscode
    * @return parentscode
    */
	public String getParentscode () {
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
	/** rootcode **/
    private String rootcode;
	/**
    * 获取属性:rootcode
    * rootcode
    * @return rootcode
    */
	public String getRootcode () {
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
	/** displayorder **/
    private String displayorder;
	/**
    * 获取属性:displayorder
    * displayorder
    * @return displayorder
    */
	public String getDisplayorder () {
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
	/** status **/
    private Integer status;
	/**
    * 获取属性:status
    * status
    * @return status
    */
	public Integer getStatus () {
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
	/** issys **/
    private Integer issys;
	/**
    * 获取属性:issys
    * issys
    * @return issys
    */
	public Integer getIssys () {
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

	/**==============================批量查询时的Order条件顺序设置==================================**/
	/**排序列表字段**/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	/**
	 * 设置排序按属性：id
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：menucode
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMenucode(boolean isAsc){
		orderFields.add(new OrderField("menucode",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：menuname
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMenuname(boolean isAsc){
		orderFields.add(new OrderField("menuname",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：menuurl
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMenuurl(boolean isAsc){
		orderFields.add(new OrderField("menuurl",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：menulevel
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMenulevel(boolean isAsc){
		orderFields.add(new OrderField("menulevel",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：isleaf
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyIsleaf(boolean isAsc){
		orderFields.add(new OrderField("isleaf",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：parentscode
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyParentscode(boolean isAsc){
		orderFields.add(new OrderField("parentscode",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：rootcode
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyRootcode(boolean isAsc){
		orderFields.add(new OrderField("rootcode",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：displayorder
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyDisplayorder(boolean isAsc){
		orderFields.add(new OrderField("displayorder",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：status
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyStatus(boolean isAsc){
		orderFields.add(new OrderField("status",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：issys
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyIssys(boolean isAsc){
		orderFields.add(new OrderField("issys",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：gmt_create
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyGmtCreate(boolean isAsc){
		orderFields.add(new OrderField("gmt_create",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：gmt_modified
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyGmtModified(boolean isAsc){
		orderFields.add(new OrderField("gmt_modified",isAsc?"ASC":"DESC"));
	}

}
