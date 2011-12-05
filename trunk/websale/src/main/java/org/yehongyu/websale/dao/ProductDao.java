/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao;

import java.util.List;

import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyDaoManager;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;

/**
 * ����˵������Ʒ����DAO��
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:09:26
 */
public interface ProductDao extends MyDaoManager {
	
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
	 * @return ��Ʒ����
	 * @throws MyException
	 */
	public BProduct getProductById(long productid)throws MyException;
	
	/**
	 * ���������ܡ����ݷ����������ȡΨһ��Ʒ����
	 * @param bookname
	 * @param catid
	 * @return ��Ʒ����
	 * @throws MyException
	 */
	public BProduct getProductByBooknameAndCatid(String bookname, String catid)
			throws MyException;

	/**
	 * ���������ܡ���ȡ�����б�
	 * @return Page����
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException;
	
}
