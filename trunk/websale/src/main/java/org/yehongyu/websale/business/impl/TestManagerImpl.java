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
 * ����˵��������Managerʵ����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:07:39
 */
public class TestManagerImpl extends MyBaseBusiness implements TestManager {
	

    /** ������ʱ����һ��˽��Dao����ֻ�ڱ�������� */
    private final static TestDao testDao;
    /** ��ʼ��Dao���󣬴�Dao������ȡ�����п����׳��쳣 */
    static {
        try {
        	testDao = (TestDao) MyDaoFactory
                    .getDaoManager(AppConstants.TestDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("����Dao����ʧ�ܣ�", e);
        }
    }

    /**
     * ˽�й��췽������ֹ�ⲿ��������ʵ����ֻ�ܹ�����������ʵ��
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
