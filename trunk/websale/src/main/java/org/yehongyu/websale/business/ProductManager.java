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
 * 【类说明】产品管理Manager接口
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午10:53:22
 */
public interface ProductManager {
	
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
     * @return BProduct
     * @throws MyException
     */
    public BProduct getProductById(long productid)throws MyException;
    
    /**
     * 【函数功能】获取产品分类Map
     * @return Map
     * @throws MyException
     */
    public Map<String,String> getCatnameMap()throws MyException;
    
	/**
	 * 【函数功能】获取分类列表
	 * @return Page对象
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException;
	
	/**
	 * 【函数功能】增加产品
	 * @param 产品对象
	 * @return String 返回的信息
	 */
	public abstract String addProduct(ProductValue product) throws MyException;

	/**
	 * 【函数功能】更新产品
	 * @param 产品对象
	 * @return String 返回的信息
	 */
	public abstract String editProduct(ProductValue product) throws MyException;

	/**
	 * 【函数功能】批量删除产品
	 * @param ids 产品ID
	 * @return String 返回的信息
	 */
	public abstract String deleteProduct(long[] ids,String imgpath) throws MyException;
	
	/**
	 * 【函数功能】发布或撤销发布产品
	 * @param ids 产品ID
	 * @param isPub 是否发布
	 * @return String 返回的信息
	 * @throws MyException
	 */
	public String pubProduct(long[] ids,long isPub) throws MyException;
	
	/**
	 * 【函数功能】置为（非）畅销产品
	 * @param ids 产品ID
	 * @param isPop 是否畅销
	 * @return String 返回的信息
	 * @throws MyException
	 */
	public String popProduct(long[] ids,long isPop) throws MyException;
	
	/**
	 * 【函数功能】推荐或取消推荐产品
	 * @param ids 产品ID
	 * @param isCommend 是否推荐
	 * @return String 返回的信息
	 * @throws MyException
	 */
	public String commendProduct(long[] ids,long isCommend) throws MyException;
	
	/**
	 * 【函数功能】获取一条畅销图书
	 * @return　产品对象
	 * @throws MyException
	 */
	public BProduct getPopulateProduct()throws MyException;

	/**
	 * 【函数功能】获取一条推荐图书
	 * @return　产品对象
	 * @throws MyException
	 */
	public BProduct getCommendProduct()throws MyException;
	
}
