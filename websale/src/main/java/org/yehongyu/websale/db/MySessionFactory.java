/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.yehongyu.websale.common.util.ConfigUtil;
import org.yehongyu.websale.common.util.MyException;

/**
 * ����˵��������HibernateSession�Ĺ�����
 * @author yehongyu.org
 * @version 1.0 2007-11-14 ����10:26:59
 */
public class MySessionFactory {
	/** ��־���� */
	private static final Logger log = Logger.getLogger(MySessionFactory.class);
	/** �����ݿ�Hibernate�������ö��� */
	private static final Map<String, SessionFactory> factorys = new HashMap<String, SessionFactory>();
	/** ���̴߳洢ʹ�õĿ����Ӷ��� */
	private static final ThreadLocal<Map<String, MySessionBean>> sessionLocal = new ThreadLocal<Map<String, MySessionBean>>();
	//�߳���,Ϊ�˱�֤��websphere������ʱ,���̻߳�����,���Ტ������sessionFactory
//	private static final Object lock = new Object();

	/**
	 * ���������ܡ��رյ�ǰ�߳�����DB�Ự����
	 * @throws MyException
	 */
	public static void releaseAllSession() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			if (log.isDebugEnabled()) {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]û��ʹ��DB���ӣ�����Ҫ�ͷ����ݿ�����");
			}
			return;
		}
		
		Iterator<String> iter = sessions.keySet().iterator();
		boolean releaseFlg = false;
		while (iter.hasNext()) {
			MySessionBean sessionBean = sessions.get(iter.next());
			Session s = sessionBean.getSession();
			if (s != null && s.isOpen()) {
				releaseFlg = true;
				s.close();
			}
		}

		sessionLocal.remove();
		if (log.isDebugEnabled()) {
			if (releaseFlg) {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]�黹���ݿ����ӳɹ�");
			} else {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]û��ʹ�����ݿ����ӣ�����Ҫ�ͷ���Դ");
			}
		}
	}
	
	/**
	 * ���������ܡ��ύ��ǰ�߳������Ѿ�������DB����
	 * @throws MyException
	 */
	public static void commitAllTransaction() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			if (log.isDebugEnabled()) {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]û��ʹ��DB���ӣ�����Ҫ�ύ����");
			}
			return;
		}
		
		Iterator<String> iter = sessions.keySet().iterator();
		boolean commitFlg = false;
		while (iter.hasNext()) {
			MySessionBean sessionBean = sessions.get(iter.next());
			commitFlg = commit(sessionBean);
		}
		
		if (log.isDebugEnabled()) {
			if (commitFlg) {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]�ύ���ݿ�����ɹ�");
			} else {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]û�п�ʼ���ݿ����񣬲���Ҫ�ύ");
			}
		}
	}
	
	/**
	 * ���������ܡ��ύָ��Session
	 * @param sessionBean
	 * @return true or false
	 * @throws MyException
	 */
	public static boolean commit(MySessionBean sessionBean) throws MyException {
		boolean commitFlg = false;
		Session s = sessionBean.getSession();
		try {
			if (s != null && s.isOpen()) {
				if (sessionBean.getTransaction() != null) {
					commitFlg = true;
					sessionBean.getTransaction().commit();
					sessionBean.setTransaction(null);
				}
			}
		} catch (HibernateException e) {
			log.error("�ع�DB�������쳣", e);
		}
		
		return commitFlg;
	}
	
	/**
	 * ���������ܡ��ع���ǰ�߳������Ѿ�������DB����
	 * @throws MyException
	 */
	public static void rollbackAllTransaction() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			log.debug("�߳�[" + Thread.currentThread().getName() + "]û��ʹ��DB���ӣ�����Ҫ�ع�����");
			return;
		}
		
		Iterator<String> iter = sessions.keySet().iterator();
		boolean rollbackFlg = false;
		while (iter.hasNext()) {
			MySessionBean sessionBean = sessions.get(iter.next());
			rollbackFlg = rollback(sessionBean);
		}
		
		if (log.isDebugEnabled()) {
			if (rollbackFlg) {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]�ع����ݿ�����ɹ�");
			} else {
				log.debug("�߳�[" + Thread.currentThread().getName() + "]û�п�ʼ���ݿ����񣬲���Ҫ�ع�");
			}
		}
	}

	/**
	 * ���������ܡ��ع�ָ��Session
	 * @param sessionBean
	 * @return true or false
	 * @throws MyException
	 */
	public static boolean rollback(MySessionBean sessionBean)throws MyException {
		boolean rollbackFlg = false;
		Session s = sessionBean.getSession();
		
		try {
			if (s != null && s.isOpen()) {
				if (sessionBean.getTransaction() != null) {
					rollbackFlg = true;
					sessionBean.getTransaction().rollback();
					sessionBean.setTransaction(null);
				}
			}
		} catch (HibernateException e) {
			log.error("�ع�DB�������쳣", e);
		}
		
		return rollbackFlg;
	}
//	public static Session openSession(){
//		return buildSession("mydb",null);
//	}
	
	/**
	 * ȡ�ø�DB�Ự����
	 * @param dbServer DB��
	 * @param dbSchema DB�ƻ���
	 * @return DB��������
	 * @throws MyException ȡ��DB�Ựʱ�����쳣
	 */
	public static MySessionBean openSession(String dbServer, String dbSchema) throws MyException {
		String dbKey = getDbKey(dbServer, dbSchema);

		//ȡ�ñ���DB�Ự�߳�������ʹ�õ�DB�Ự���󼯺�
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			sessions = new HashMap<String, MySessionBean>();
			sessionLocal.set(sessions);
		}
		
		//ȡ�ûỰBean�������װBean
		MySessionBean sessionBean = (sessions.containsKey(dbKey)) 
                                   ? sessions.get(dbKey) : null;
        if (sessionBean == null) {
        	sessionBean = new MySessionBean();
        	sessions.put(dbKey, sessionBean);
        }
        
        //ȡ��ָ��DB���ͼƻ�������Ӧ��DB�Ự����
		Session s = sessionBean.getSession();
        if ((s == null) || (!s.isConnected()) || (!s.isOpen())) {
        	sessionBean.setTransaction(null);
        	sessionBean.setSession(buildSession(dbServer, dbSchema));
        }
		
		return sessionBean;
	}
	
	/**
	 * �����ӳ���ȡ��ָ��DB�ĻỰ����
	 * @param dbServer DB������
	 * @param dbSchema DB�û���
	 * @return DB�Ự����
	 */
	private static Session buildSession(String dbServer, String dbSchema){
		String dbKey = getDbKey(dbServer, dbSchema);
		
		//���Hibernateδ���ظ�DB����������DB����
		if (!factorys.containsKey(dbKey)) {
			buildFactory(dbServer, dbSchema);
		}

		//��Hibernate������ȡ��DB�Ự����
		Session session = factorys.get(dbKey).openSession();
		
		if (log.isDebugEnabled()) {
			log.debug("�߳�[" + Thread.currentThread().getName() + "]ȡ�����ݿ�[" + dbKey + "]���ӳɹ�");
		}
		
		return session;
	}
	
	/**
	 * ���������DB����
	 * @param dbServer DB������
	 * @param dbSchema DB�û���
	 */
	private static synchronized void buildFactory(String dbServer, String dbSchema){
		String dbKey = getDbKey(dbServer, dbSchema);
		if (factorys.containsKey(dbKey)) return;

		//�������ļ��ж�ȡDB��������
		ConfigUtil config = ConfigUtil.getConfig("/org/yehongyu/websale/db/cfg/db.properties");
		String cfg = config.getPropValue(dbServer); //dbCfg.getString(valueOf(dbServer));
		String schema = config.getPropValue(dbSchema); //dbCfg.getString(valueOf(dbSchema));
//		String cfg="/org/yehongyu/websale/db/cfg/mydb.cfg.xml";
//		String schema = null;
		Configuration conf = new Configuration().configure(cfg);
		if (dbSchema != null) conf.setProperty("hibernate.default_schema", schema);
		SessionFactory factory = conf.buildSessionFactory();
		factorys.put(dbKey, factory);
		
		log.info("����HibernateSession����[" + dbKey + "]�ɹ�");
	}
	
	/**
	 * ȡ���߳����б����DB�Ự����KEYֵ
	 * @param dbServer DB������
	 * @param dbSchema DB�û���
	 * @return �߳����б����DB�Ự����KEYֵ
	 */
	private static String getDbKey(String dbServer, String dbSchema) {
		return (dbSchema == null) ? dbServer : dbServer + "@" + dbSchema;
	}
}
