/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.turbine.Turbine;
import org.yehongyu.websale.common.util.ConfigUtil;
import org.yehongyu.websale.common.util.MyException;

/**
 * 【类说明】创建管理类的工厂
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:17:43
 */
public abstract class MyBusinessFactory {
	/** 日志对象 */
	private static final Logger log = Logger.getLogger(MyBusinessFactory.class);
	/** 已创建的管理类实例 */
	private static final HashMap<String, Object> hmBm = new HashMap<String, Object>();
	
	/**
	 * 取得管理类实例
	 * @param name 管理类属性名称
	 * @return 管理类实例
	 * @throws MyException 取得管理类实例时发生异常
	 */
	public static Object getManager(String name) throws MyException {
		Object manager = hmBm.get(name);
		if (manager == null) {
			manager = createManager(name);
		}

		return manager;
	}
	
	/**
	 * 取得管理类实例所实现的接口
	 * @param manager 管理类实例
	 * @return 管理类所实现的接口
	 * @throws MyException 管理类没有实现接口
	 */
	private static Class getInterfaceClass(MyBaseBusiness manager) throws MyException {
		Class[] cls = manager.getClass().getInterfaces();
		if (cls == null || cls.length == 0)
			throw new MyException("ERROR CODE");
		return cls[0];
	}
	
	/**
	 * 创建管理类实例
	 * @param name 管理类属性名称
	 * @return 管理类实例
	 * @throws MyException 创建管理类时发生异常
	 */
	private static synchronized Object createManager(String name) throws MyException {
		Object proxyManager;
		try {
			//取得真正管理类路径
			String className = ConfigUtil.getConfigByRealPath(Turbine.getRealPath("WEB-INF/conf/AppConfig.properties")).getPropValue(name);
			Constructor c = Class.forName(className).getDeclaredConstructor(new Class[0]);
			c.setAccessible(true);
			proxyManager = c.newInstance(new Object[0]);
			MyBaseBusiness manager = (MyBaseBusiness)c.newInstance(new Object[0]);
			
			Class clsInterface = getInterfaceClass(manager);
			
			InvocationHandler handler = new MyBusinessInvocationHandler(manager);
			proxyManager = Proxy.newProxyInstance(
					clsInterface.getClassLoader(),
	                new Class[]{clsInterface},
	                handler);
			hmBm.put(name, proxyManager);
			
		} catch (Exception e) {
			log.error("无法创建管理类实例[name = " + name + "]", e);
			throw new MyException("ERROR CODE", e);
		}
		
		return proxyManager;
	}
}
