package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_user查询对象
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserQuery extends BaseQuery {

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
	/** username **/
    private String username;
	/**
    * 获取属性:username
    * username
    * @return username
    */
	public String getUsername () {
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
	/** password **/
    private String password;
	/**
    * 获取属性:password
    * password
    * @return password
    */
	public String getPassword () {
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
	/** name **/
    private String name;
	/**
    * 获取属性:name
    * name
    * @return name
    */
	public String getName () {
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
	/** memo **/
    private String memo;
	/**
    * 获取属性:memo
    * memo
    * @return memo
    */
	public String getMemo () {
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
	 * 设置排序按属性：username
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyUsername(boolean isAsc){
		orderFields.add(new OrderField("username",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：password
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyPassword(boolean isAsc){
		orderFields.add(new OrderField("password",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：name
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：issys
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyIssys(boolean isAsc){
		orderFields.add(new OrderField("issys",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：status
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyStatus(boolean isAsc){
		orderFields.add(new OrderField("status",isAsc?"ASC":"DESC"));
	}
	/**
	 * 设置排序按属性：memo
	 * @param isAsc 是否升序，否则为降序
	 */	
	public void orderbyMemo(boolean isAsc){
		orderFields.add(new OrderField("memo",isAsc?"ASC":"DESC"));
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
