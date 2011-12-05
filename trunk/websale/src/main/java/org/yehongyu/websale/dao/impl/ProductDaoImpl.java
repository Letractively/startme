/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.common.util.StringUtils;
import org.yehongyu.websale.dao.ProductDao;
import org.yehongyu.websale.db.MyDaoMydb;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.db.po.mydb.SUser;

/**
 * 【类说明】产品管理DAO实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:09:54
 */
public class ProductDaoImpl extends MyDaoMydb implements ProductDao {

	/**
	 * 【函数功能】获取产品列表
	 * @param p
	 * @param pageBean
	 * @return Page对象
	 * @throws MyException
	 */
	public Page getProductList(ProductParam p,PageBean pageBean)throws MyException{
		StringBuffer sql = new StringBuffer("from BProduct where state<>3");
		Map<String,Object> params = new HashMap<String,Object>();
		if(!StringUtils.isNullorSpace(p.getBookname())){
			sql.append(" and bookname like :bookname");
			params.put("bookname", p.getBookname()+"%");
		}
		if(!StringUtils.isNullorSpace(p.getBookwriter())){
			sql.append(" and bookwriter like :bookwriter");
			params.put("bookwriter", p.getBookwriter()+"%");
		}
		if(!StringUtils.isNullorSpace(p.getCatid())){
			sql.append(" and catid = :catid");
			params.put("catid", p.getCatid());
		}
//		if(p.getIspopulate()!=null&&!p.getIspopulate().trim().equals("")){
//			sql.append(" and ispopulate = :ispopulate");
//			params.put("ispopulate", Long.valueOf(p.getIspopulate()));
//		}
//		if(p.getIscommend()!=null&&!p.getIscommend().trim().equals("")){
//			sql.append(" and iscommend = :iscommend");
//			params.put("iscommend", Long.valueOf(p.getIscommend()));
//		}
//		if(p.getState()!=null&&!p.getState().trim().equals("")){
//			sql.append(" and state = :state");
//			params.put("state", Long.valueOf(p.getState()));
//		}
		if(p.getIspopulate()!=-1){
			sql.append(" and ispopulate = :ispopulate");
			params.put("ispopulate", p.getIspopulate());
		}
		if(p.getIscommend()!=-1){
			sql.append(" and iscommend = :iscommend");
			params.put("iscommend", Long.valueOf(p.getIscommend()));
		}
		if(p.getState()!=-1){
			sql.append(" and state = :state");
			params.put("state", p.getState());
		}
//		if(p.getStartdate()!=null&&!p.getStartdate().trim().equals("")){
//			sql.append(" and pubdate >= to_date(:startdate,'yyyy-mm-dd hh24:mi:ss')");
//			params.put("startdate", p.getStartdate() + " 00:00:00");
//		}
//		if(p.getEnddate()!=null&&!p.getEnddate().trim().equals("")){
//			sql.append(" and pubdate <= to_date(:enddate,'yyyy-mm-dd hh24:mi:ss')");
//			params.put("enddate", p.getEnddate() + " 23:59:59");
//		}
		if(p.getStartdate()!=null){
			sql.append(" and pubdate >= :startdate");
			params.put("startdate", new Date(p.getStartdate().getTime()));
		}
		if(p.getEnddate()!=null){
			sql.append(" and pubdate <= :enddate");
			params.put("enddate", new Date(p.getEnddate().getTime() + 24*60*60*1000-1));
		}
		sql.append(" order by pubdate desc");
		return super.getPageQuery(sql.toString(), params, pageBean);
	}
	
	/**
	 * 【函数功能】根据产品ID获取产品对象
	 * @param productid
	 * @return 产品对象
	 * @throws MyException
	 */
	public BProduct getProductById(long productid)throws MyException{
		return (BProduct)super.find(BProduct.class, productid);
	}
	
	/**
	 * 【函数功能】根据分类和书名获取唯一产品对象
	 * @param bookname
	 * @param catid
	 * @return 产品对象
	 * @throws MyException
	 */
	public BProduct getProductByBooknameAndCatid(String bookname, String catid)
			throws MyException {
		// 参数验证
		if (StringUtils.isNullorSpace(bookname)
				|| StringUtils.isNullorSpace(catid))
			return null;

		String sql = "from BProduct a where 1=1 and a.bookname = :bookname and a.catid = :catid";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bookname", bookname);
		map.put("catid", catid);
		BProduct obj = null;
		try {
			obj = (BProduct) uniqueQuery(sql, map);
		} catch (MyException e) {
			log.info("查询对象不唯一");
		}
		return obj;
	}

	/**
	 * 【函数功能】获取分类列表
	 * 
	 * @return Page对象
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException{
		String sql = "from BCategory where id like '001001___' and state<>0 order by sortnum";
		List<BCategory> list = super.query(sql);
		return list;
	}
	
}
