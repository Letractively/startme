/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao;

import java.util.List;

import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyDaoManager;
import org.yehongyu.websale.db.po.mydb.SysMenu;
import org.yehongyu.websale.db.po.mydb.SysUser;

/**
 * ����˵��������DAO��
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:09:26
 */
public interface TestDao extends MyDaoManager {

	public List<SysMenu> queryMenu()throws MyException;
	
	public List<SysUser> queryUser()throws MyException;
	
}
