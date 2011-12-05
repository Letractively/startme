/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business;

import java.util.List;

import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.po.mydb.SysMenu;
import org.yehongyu.websale.db.po.mydb.SysUser;

/**
 * ����˵��������Manager�ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:53:22
 */
public interface TestManager {
	
	public List<SysMenu> queryMenu() throws MyException;
	
	public List<SysUser> queryUser() throws MyException;
	
}
