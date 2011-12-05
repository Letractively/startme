/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 【类说明】管理类代理实现接口
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:17:54
 */
public class MyBusinessInvocationHandler implements InvocationHandler {
	/** 管理类实例 */
	private MyBaseBusiness businessImpl;
	
	/**
	 * 根据传入管理类实例定义当前代理实例代理的对象
	 * @param businessImpl 管理类实例
	 */
	public MyBusinessInvocationHandler(MyBaseBusiness businessImpl) {
		this.businessImpl = businessImpl;
	}
	
	/**
	 * 代理器入口
	 * @param obj 代理实例
	 * @param method 调用方法对象
	 * @param args 调用方法参数
	 * @return 调用返回结果
	 * @throws Throwable 调用时发生异常
	 */
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		Object ret;
		
		try {
			ret = method.invoke(businessImpl, args);
			MySessionFactory.commitAllTransaction();
		} catch (Throwable e) {
			MySessionFactory.rollbackAllTransaction();
			throw e.getCause();
		} finally {
			MySessionFactory.releaseAllSession();
		}
		
		return ret;
	}
}
