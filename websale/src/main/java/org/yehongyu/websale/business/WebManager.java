/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business;

import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.bo.manage.web.ContentParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.AContent;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * 【类说明】网站管理Manager接口
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午10:53:22
 */
public interface WebManager {
	
	/**
	 * 【函数功能】获取有效的最新公告内容对象
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException;
	
	/**
	 * 【函数功能】编辑公告信息
	 * @param cp
	 * @return
	 * @throws MyException
	 */
	public String editBoard(ContentParam cp)throws MyException;
}
