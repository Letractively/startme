/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 【类说明】DB会话及事务对象Bean
 * @author yehongyu.org
 * @version 1.0 2007-11-14 下午10:26:26
 */
public class MySessionBean {

	/** 数据库会话 */
	private Session session;
	/** 数据库事务 */
	private Transaction transaction;

	/**
	 * 取得数据库会话
	 * @return 数据库会话
	 */
	public Session getSession() {
		return session;
	}
	/**
	 * 设置数据库会话
	 * @param s 数据库会话
	 */
	public void setSession(Session s) {
		this.session = s;
	}

	/**
	 * 取得数据库事务
	 * @return 数据库事务
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * 设置数据库事务
	 * @param tx 数据库事务
	 */
	public void setTransaction(Transaction tx) {
		this.transaction = tx;
	}
}
