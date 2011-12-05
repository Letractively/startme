/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.dao.WebDao;
import org.yehongyu.websale.db.MyDaoMydb;
import org.yehongyu.websale.db.po.mydb.AContent;

/**
 * 【类说明】网站管理DAO实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:09:54
 */
public class WebDaoImpl extends MyDaoMydb implements WebDao {

	/**
	 * 【函数功能】获取有效的最新公告内容对象
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException{
		String sql = "from AContent where contype=1";
		return (AContent)super.uniqueQuery(sql);
	}
	
	/**
	 * 【函数功能】获取有效的最新公告内容对象
	 * @return
	 * @throws MyException
	 */
	public AContent getContentById(long id)throws MyException{
		return (AContent)super.find(AContent.class, id);
	}

	
}
