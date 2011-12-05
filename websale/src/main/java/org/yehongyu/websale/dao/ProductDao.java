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
 * 【类说明】产品管理DAO类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:09:26
 */
public interface ProductDao extends MyDaoManager {
	
	/**
	 * 【函数功能】获取产品列表
	 * @param p
	 * @param pageBean
	 * @return Page对象
	 * @throws MyException
	 */
	public Page getProductList(ProductParam p,PageBean pageBean)throws MyException;
	
	/**
	 * 【函数功能】根据产品ID获取产品对象
	 * @param productid
	 * @return 产品对象
	 * @throws MyException
	 */
	public BProduct getProductById(long productid)throws MyException;
	
	/**
	 * 【函数功能】根据分类和书名获取唯一产品对象
	 * @param bookname
	 * @param catid
	 * @return 产品对象
	 * @throws MyException
	 */
	public BProduct getProductByBooknameAndCatid(String bookname, String catid)
			throws MyException;

	/**
	 * 【函数功能】获取分类列表
	 * @return Page对象
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException;
	
}
