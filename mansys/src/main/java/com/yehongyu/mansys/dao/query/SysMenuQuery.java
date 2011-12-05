package com.yehongyu.mansys.dao.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sys_menu��ѯ����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuQuery extends BaseQuery {

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
	/** menucode **/
    private String menucode;
	/**
    * ��ȡ����:menucode
    * menucode
    * @return menucode
    */
	public String getMenucode () {
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
	/** menuname **/
    private String menuname;
	/**
    * ��ȡ����:menuname
    * menuname
    * @return menuname
    */
	public String getMenuname () {
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
	/** menuurl **/
    private String menuurl;
	/**
    * ��ȡ����:menuurl
    * menuurl
    * @return menuurl
    */
	public String getMenuurl () {
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
	/** menulevel **/
    private Integer menulevel;
	/**
    * ��ȡ����:menulevel
    * menulevel
    * @return menulevel
    */
	public Integer getMenulevel () {
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
	/** isleaf **/
    private Integer isleaf;
	/**
    * ��ȡ����:isleaf
    * isleaf
    * @return isleaf
    */
	public Integer getIsleaf () {
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
	/** parentscode **/
    private String parentscode;
	/**
    * ��ȡ����:parentscode
    * parentscode
    * @return parentscode
    */
	public String getParentscode () {
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
	/** rootcode **/
    private String rootcode;
	/**
    * ��ȡ����:rootcode
    * rootcode
    * @return rootcode
    */
	public String getRootcode () {
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
	/** displayorder **/
    private String displayorder;
	/**
    * ��ȡ����:displayorder
    * displayorder
    * @return displayorder
    */
	public String getDisplayorder () {
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
	 * �����������ԣ�menucode
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMenucode(boolean isAsc){
		orderFields.add(new OrderField("menucode",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�menuname
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMenuname(boolean isAsc){
		orderFields.add(new OrderField("menuname",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�menuurl
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMenuurl(boolean isAsc){
		orderFields.add(new OrderField("menuurl",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�menulevel
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyMenulevel(boolean isAsc){
		orderFields.add(new OrderField("menulevel",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�isleaf
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyIsleaf(boolean isAsc){
		orderFields.add(new OrderField("isleaf",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�parentscode
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyParentscode(boolean isAsc){
		orderFields.add(new OrderField("parentscode",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�rootcode
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyRootcode(boolean isAsc){
		orderFields.add(new OrderField("rootcode",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�displayorder
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyDisplayorder(boolean isAsc){
		orderFields.add(new OrderField("displayorder",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�status
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyStatus(boolean isAsc){
		orderFields.add(new OrderField("status",isAsc?"ASC":"DESC"));
	}
	/**
	 * �����������ԣ�issys
	 * @param isAsc �Ƿ����򣬷���Ϊ����
	 */	
	public void orderbyIssys(boolean isAsc){
		orderFields.add(new OrderField("issys",isAsc?"ASC":"DESC"));
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
