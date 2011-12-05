package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_user��ѯ����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserQuery extends BaseQuery {

	/** ���л�ID */
	private static final long serialVersionUID = 1L;
	
	/** ====================��ѯΨһ������¼ʹ��==========================**/
	
	/**==============================������ѯ�����¡�ɾ��ʱ��Where��������==================================**/
	/** id **/
    private Long id;
	/**
    * ��ȡ����:id
    * id
    * @return id
    */
	public Long getId () {
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
	/** username **/
    private String username;
	/**
    * ��ȡ����:username
    * username
    * @return username
    */
	public String getUsername () {
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
	/** password **/
    private String password;
	/**
    * ��ȡ����:password
    * password
    * @return password
    */
	public String getPassword () {
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
	/** name **/
    private String name;
	/**
    * ��ȡ����:name
    * name
    * @return name
    */
	public String getName () {
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
	/** issys **/
    private Integer issys;
	/**
    * ��ȡ����:issys
    * issys
    * @return issys
    */
	public Integer getIssys () {
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
	/** status **/
    private Integer status;
	/**
    * ��ȡ����:status
    * status
    * @return status
    */
	public Integer getStatus () {
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
	/** memo **/
    private String memo;
	/**
    * ��ȡ����:memo
    * memo
    * @return memo
    */
	public String getMemo () {
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

	/**==============================������ѯʱ��Order����˳������==================================**/
	/**�����б��ֶ�**/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	/**
	 * �����������ԣ�id
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyId(boolean isAsc){
		orderFields.add(new OrderField("id",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�username
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyUsername(boolean isAsc){
		orderFields.add(new OrderField("username",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�password
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyPassword(boolean isAsc){
		orderFields.add(new OrderField("password",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�name
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyName(boolean isAsc){
		orderFields.add(new OrderField("name",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�issys
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyIssys(boolean isAsc){
		orderFields.add(new OrderField("issys",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�status
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyStatus(boolean isAsc){
		orderFields.add(new OrderField("status",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�memo
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMemo(boolean isAsc){
		orderFields.add(new OrderField("memo",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�gmt_create
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyGmtCreate(boolean isAsc){
		orderFields.add(new OrderField("gmt_create",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�gmt_modified
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyGmtModified(boolean isAsc){
		orderFields.add(new OrderField("gmt_modified",isAsc?"ASC":"DESC"));
	}

}
