/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.turbine.Turbine;
import org.yehongyu.websale.common.util.ConfigUtil;
import org.yehongyu.websale.common.util.MyException;

/**
 * ����˵��������DAO�Ĺ�����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:18:44
 */
public abstract class MyDaoFactory {
	/** ��־���� */
	private static final Logger log = Logger.getLogger(MyDaoFactory.class);
	/** �Ѵ����Ĺ�����ʵ�� */
	private static final HashMap<String, Object> hmBm = new HashMap<String, Object>();
	
	/**
	 * ȡ��DAOʵ��
	 * @param name DAO����������
	 * @return DAO��ʵ��
	 * @throws MyException ȡ�ù�����ʵ��ʱ�����쳣
	 */
	public static Object getDaoManager(String name) throws MyException {
		Object manager = hmBm.get(name);
		if (manager == null) {
			manager = createManager(name);
		}

		return manager;
	}
	
	/**
	 * ����������ʵ��
	 * @param name ��������������
	 * @return ������ʵ��
	 * @throws MyException ����������ʱ�����쳣
	 */
	private static synchronized Object createManager(String name) throws MyException {
		Object dao;
		try {
			//ȡ������������·��
			String className = ConfigUtil.getConfigByRealPath(Turbine.getRealPath("WEB-INF/conf/AppConfig.properties")).getPropValue(name);
			Constructor c = Class.forName(className).getDeclaredConstructor(new Class[0]);
			c.setAccessible(true);
			dao = c.newInstance(new Object[0]);
			hmBm.put(name, dao);
		} catch (Exception e) {
			log.error("�޷�����������ʵ��[name = " + name + "]", e);
			throw new MyException("ERROR CODE", e);
		}
		return dao;
	}
}
