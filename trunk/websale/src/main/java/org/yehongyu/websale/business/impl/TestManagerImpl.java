/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business.impl;

import java.util.List;

import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.TestManager;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.dao.TestDao;
import org.yehongyu.websale.db.MyBaseBusiness;
import org.yehongyu.websale.db.MyDaoFactory;
import org.yehongyu.websale.db.po.mydb.SysMenu;
import org.yehongyu.websale.db.po.mydb.SysUser;

/**
 * 【类说明】测试Manager实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:07:39
 */
public class TestManagerImpl extends MyBaseBusiness implements TestManager {
	

    /** 加载类时声明一个私有Dao对象，只在本类可以用 */
    private final static TestDao testDao;
    /** 初始化Dao对象，从Dao工厂中取出，有可能抛出异常 */
    static {
        try {
        	testDao = (TestDao) MyDaoFactory
                    .getDaoManager(AppConstants.TestDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("创建Dao对象失败！", e);
        }
    }

    /**
     * 私有构造方法，防止外部创建本类实例，只能工厂创建本类实例
     */
    private TestManagerImpl() {
    }
    
	public List<SysMenu> queryMenu() throws MyException{
		return testDao.queryMenu();
	}
	
	public List<SysUser> queryUser() throws MyException{
		return testDao.queryUser();
	}
}
