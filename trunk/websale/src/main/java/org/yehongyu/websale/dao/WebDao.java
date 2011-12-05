/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao;

import java.util.List;

import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyDaoManager;
import org.yehongyu.websale.db.po.mydb.AContent;
import org.yehongyu.websale.db.po.mydb.SysMenu;
import org.yehongyu.websale.db.po.mydb.SysUser;

/**
 * ����˵������վ����DAO��
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:09:26
 */
public interface WebDao extends MyDaoManager {
	
	/**
	 * ���������ܡ���ȡ��Ч�����¹������ݶ���
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException;
	
	/**
	 * ���������ܡ���ȡ��Ч�����¹������ݶ���
	 * @return
	 * @throws MyException
	 */
	public AContent getContentById(long id)throws MyException;
}
