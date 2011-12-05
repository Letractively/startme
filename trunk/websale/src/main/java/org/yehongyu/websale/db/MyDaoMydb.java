/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import org.yehongyu.websale.common.util.MyException;

/**
 * ����˵����MyDB Dao����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:19:33
 */
public abstract class MyDaoMydb extends MyDao {

	/**
	 * ȡ��MYDB�Ự
	 */
	MySessionBean openSession() throws MyException {
		return MySessionFactory.openSession(DBConstants.CFG_PROP_DB_MYDB,null);
	}
}
