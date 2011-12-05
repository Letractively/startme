/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * ����˵����DB�Ự���������Bean
 * @author yehongyu.org
 * @version 1.0 2007-11-14 ����10:26:26
 */
public class MySessionBean {

	/** ���ݿ�Ự */
	private Session session;
	/** ���ݿ����� */
	private Transaction transaction;

	/**
	 * ȡ�����ݿ�Ự
	 * @return ���ݿ�Ự
	 */
	public Session getSession() {
		return session;
	}
	/**
	 * �������ݿ�Ự
	 * @param s ���ݿ�Ự
	 */
	public void setSession(Session s) {
		this.session = s;
	}

	/**
	 * ȡ�����ݿ�����
	 * @return ���ݿ�����
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * �������ݿ�����
	 * @param tx ���ݿ�����
	 */
	public void setTransaction(Transaction tx) {
		this.transaction = tx;
	}
}
