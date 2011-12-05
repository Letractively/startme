package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_user_menu��ѯ����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuQuery extends BaseQuery {

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
	/** userid **/
    private Long userid;
	/**
    * ��ȡ����:userid
    * userid
    * @return userid
    */
	public Long getUserid () {
    	return userid;
   	}
   	/**
     * ��������:userid
     * userid
     * @param userid
     */
    public void setUserid(Long userid) {
    	this.userid = userid;
    }
	/** menuid **/
    private Long menuid;
	/**
    * ��ȡ����:menuid
    * menuid
    * @return menuid
    */
	public Long getMenuid () {
    	return menuid;
   	}
   	/**
     * ��������:menuid
     * menuid
     * @param menuid
     */
    public void setMenuid(Long menuid) {
    	this.menuid = menuid;
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
	 * �����������ԣ�userid
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyUserid(boolean isAsc){
		orderFields.add(new OrderField("userid",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�menuid
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMenuid(boolean isAsc){
		orderFields.add(new OrderField("menuid",isAsc?"ASC":"DESC"));
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
