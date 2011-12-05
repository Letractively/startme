/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business;

import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * ����˵������Ʒ����Manager�ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:53:22
 */
public interface ProductManager {
	
    /**
     * ���������ܡ���ȡ��Ʒ�б�
     * @param p
     * @param pageBean
     * @return Page����
     * @throws MyException
     */
    public Page getProductList(ProductParam p,PageBean pageBean)throws MyException;
    
    /**
     * ���������ܡ����ݲ�ƷID��ȡ��Ʒ����
     * @param productid
     * @return BProduct
     * @throws MyException
     */
    public BProduct getProductById(long productid)throws MyException;
    
    /**
     * ���������ܡ���ȡ��Ʒ����Map
     * @return Map
     * @throws MyException
     */
    public Map<String,String> getCatnameMap()throws MyException;
    
	/**
	 * ���������ܡ���ȡ�����б�
	 * @return Page����
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException;
	
	/**
	 * ���������ܡ����Ӳ�Ʒ
	 * @param ��Ʒ����
	 * @return String ���ص���Ϣ
	 */
	public abstract String addProduct(ProductValue product) throws MyException;

	/**
	 * ���������ܡ����²�Ʒ
	 * @param ��Ʒ����
	 * @return String ���ص���Ϣ
	 */
	public abstract String editProduct(ProductValue product) throws MyException;

	/**
	 * ���������ܡ�����ɾ����Ʒ
	 * @param ids ��ƷID
	 * @return String ���ص���Ϣ
	 */
	public abstract String deleteProduct(long[] ids,String imgpath) throws MyException;
	
	/**
	 * ���������ܡ���������������Ʒ
	 * @param ids ��ƷID
	 * @param isPub �Ƿ񷢲�
	 * @return String ���ص���Ϣ
	 * @throws MyException
	 */
	public String pubProduct(long[] ids,long isPub) throws MyException;
	
	/**
	 * ���������ܡ���Ϊ���ǣ�������Ʒ
	 * @param ids ��ƷID
	 * @param isPop �Ƿ���
	 * @return String ���ص���Ϣ
	 * @throws MyException
	 */
	public String popProduct(long[] ids,long isPop) throws MyException;
	
	/**
	 * ���������ܡ��Ƽ���ȡ���Ƽ���Ʒ
	 * @param ids ��ƷID
	 * @param isCommend �Ƿ��Ƽ�
	 * @return String ���ص���Ϣ
	 * @throws MyException
	 */
	public String commendProduct(long[] ids,long isCommend) throws MyException;
	
	/**
	 * ���������ܡ���ȡһ������ͼ��
	 * @return����Ʒ����
	 * @throws MyException
	 */
	public BProduct getPopulateProduct()throws MyException;

	/**
	 * ���������ܡ���ȡһ���Ƽ�ͼ��
	 * @return����Ʒ����
	 * @throws MyException
	 */
	public BProduct getCommendProduct()throws MyException;
	
}
