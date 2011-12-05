package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_user_menu查询对象
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuQuery extends BaseQuery {

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
	/** userid **/
    private Long userid;
	/**
    * 获取属性:userid
    * userid
    * @return userid
    */
	public Long getUserid () {
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
	/** menuid **/
    private Long menuid;
	/**
    * 获取属性:menuid
    * menuid
    * @return menuid
    */
	public Long getMenuid () {
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
	 * 设置排序按属性：userid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyUserid(boolean isAsc){
		orderFields.add(new OrderField("userid",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：menuid
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMenuid(boolean isAsc){
		orderFields.add(new OrderField("menuid",isAsc?"ASC":"DESC"));
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
