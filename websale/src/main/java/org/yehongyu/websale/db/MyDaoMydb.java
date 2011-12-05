/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import org.yehongyu.websale.common.util.MyException;

/**
 * 【类说明】MyDB Dao基类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:19:33
 */
public abstract class MyDaoMydb extends MyDao {

	/**
	 * 取得MYDB会话
	 */
	MySessionBean openSession() throws MyException {
		return MySessionFactory.openSession(DBConstants.CFG_PROP_DB_MYDB,null);
	}
}
