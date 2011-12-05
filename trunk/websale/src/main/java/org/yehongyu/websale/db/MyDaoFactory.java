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
 * 【类说明】创建DAO的工厂类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:18:44
 */
public abstract class MyDaoFactory {
	/** 日志对象 */
	private static final Logger log = Logger.getLogger(MyDaoFactory.class);
	/** 已创建的管理类实例 */
	private static final HashMap<String, Object> hmBm = new HashMap<String, Object>();
	
	/**
	 * 取得DAO实例
	 * @param name DAO类属性名称
	 * @return DAO类实例
	 * @throws MyException 取得管理类实例时发生异常
	 */
	public static Object getDaoManager(String name) throws MyException {
		Object manager = hmBm.get(name);
		if (manager == null) {
			manager = createManager(name);
		}

		return manager;
	}
	
	/**
	 * 创建管理类实例
	 * @param name 管理类属性名称
	 * @return 管理类实例
	 * @throws MyException 创建管理类时发生异常
	 */
	private static synchronized Object createManager(String name) throws MyException {
		Object dao;
		try {
			//取得真正管理类路径
			String className = ConfigUtil.getConfigByRealPath(Turbine.getRealPath("WEB-INF/conf/AppConfig.properties")).getPropValue(name);
			Constructor c = Class.forName(className).getDeclaredConstructor(new Class[0]);
			c.setAccessible(true);
			dao = c.newInstance(new Object[0]);
			hmBm.put(name, dao);
		} catch (Exception e) {
			log.error("无法创建管理类实例[name = " + name + "]", e);
			throw new MyException("ERROR CODE", e);
		}
		return dao;
	}
}
