/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao.impl;

import java.util.List;

import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.dao.TestDao;
import org.yehongyu.websale.db.MyDaoMydb;
import org.yehongyu.websale.db.po.mydb.SysMenu;
import org.yehongyu.websale.db.po.mydb.SysUser;

/**
 * 【类说明】测试DAO实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:09:54
 */
public class TestDaoImpl extends MyDaoMydb implements TestDao {

	public List<SysMenu> queryMenu()throws MyException{
		String sql = "from SysMenu where isleaf=1";
		List<SysMenu> list = super.query(sql);
		return list;
	}

	public List<SysUser> queryUser()throws MyException{
		String sql = "from SysUser";
		List<SysUser> list = super.query(sql);
		return list;
	}
	
	public static void main(String[] args)throws MyException{
		TestDaoImpl dao = new TestDaoImpl();
		List lst = dao.queryMenu();
		System.out.println(lst.size());
		for(Object obj:lst){
			SysMenu sm = (SysMenu)obj;
			System.out.println(sm.getMenuname());
		}
		System.out.println("successful");
	}
	
}
