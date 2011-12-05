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
 * 【类说明】创建HibernateSession的工厂类
 * @author yehongyu.org
 * @version 1.0 2007-11-14 下午10:26:59
 */
public class MySessionFactory {
	/** 日志对象 */
	private static final Logger log = Logger.getLogger(MySessionFactory.class);
	/** 各数据库Hibernate工厂引用对象 */
	private static final Map<String, SessionFactory> factorys = new HashMap<String, SessionFactory>();
	/** 按线程存储使用的库连接对象 */
	private static final ThreadLocal<Map<String, MySessionBean>> sessionLocal = new ThreadLocal<Map<String, MySessionBean>>();
	//线程锁,为了保证在websphere里启动时,多线程环境下,不会并发创建sessionFactory
//	private static final Object lock = new Object();

	/**
	 * 【函数功能】关闭当前线程所有DB会话对象
	 * @throws MyException
	 */
	public static void releaseAllSession() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			if (log.isDebugEnabled()) {
				log.debug("线程[" + Thread.currentThread().getName() + "]没有使用DB连接，不需要释放数据库连接");
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
				log.debug("线程[" + Thread.currentThread().getName() + "]归还数据库连接成功");
			} else {
				log.debug("线程[" + Thread.currentThread().getName() + "]没有使用数据库连接，不需要释放资源");
			}
		}
	}
	
	/**
	 * 【函数功能】提交当前线程所有已经创建的DB事务
	 * @throws MyException
	 */
	public static void commitAllTransaction() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			if (log.isDebugEnabled()) {
				log.debug("线程[" + Thread.currentThread().getName() + "]没有使用DB连接，不需要提交事务");
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
				log.debug("线程[" + Thread.currentThread().getName() + "]提交数据库事务成功");
			} else {
				log.debug("线程[" + Thread.currentThread().getName() + "]没有开始数据库事务，不需要提交");
			}
		}
	}
	
	/**
	 * 【函数功能】提交指定Session
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
			log.error("回滚DB事务发生异常", e);
		}
		
		return commitFlg;
	}
	
	/**
	 * 【函数功能】回滚当前线程所有已经创建的DB事务
	 * @throws MyException
	 */
	public static void rollbackAllTransaction() throws MyException {
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			log.debug("线程[" + Thread.currentThread().getName() + "]没有使用DB连接，不需要回滚事务");
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
				log.debug("线程[" + Thread.currentThread().getName() + "]回滚数据库事务成功");
			} else {
				log.debug("线程[" + Thread.currentThread().getName() + "]没有开始数据库事务，不需要回滚");
			}
		}
	}

	/**
	 * 【函数功能】回滚指定Session
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
			log.error("回滚DB事务发生异常", e);
		}
		
		return rollbackFlg;
	}
//	public static Session openSession(){
//		return buildSession("mydb",null);
//	}
	
	/**
	 * 取得各DB会话对象
	 * @param dbServer DB名
	 * @param dbSchema DB计划名
	 * @return DB操作对象
	 * @throws MyException 取得DB会话时发生异常
	 */
	public static MySessionBean openSession(String dbServer, String dbSchema) throws MyException {
		String dbKey = getDbKey(dbServer, dbSchema);

		//取得本地DB会话线程组中已使用的DB会话对象集合
		Map<String, MySessionBean> sessions = sessionLocal.get();
		if (sessions == null) {
			sessions = new HashMap<String, MySessionBean>();
			sessionLocal.set(sessions);
		}
		
		//取得会话Bean与事务封装Bean
		MySessionBean sessionBean = (sessions.containsKey(dbKey)) 
                                   ? sessions.get(dbKey) : null;
        if (sessionBean == null) {
        	sessionBean = new MySessionBean();
        	sessions.put(dbKey, sessionBean);
        }
        
        //取得指定DB名和计划名所对应的DB会话对象
		Session s = sessionBean.getSession();
        if ((s == null) || (!s.isConnected()) || (!s.isOpen())) {
        	sessionBean.setTransaction(null);
        	sessionBean.setSession(buildSession(dbServer, dbSchema));
        }
		
		return sessionBean;
	}
	
	/**
	 * 从连接池中取得指定DB的会话对象
	 * @param dbServer DB服务名
	 * @param dbSchema DB用户名
	 * @return DB会话对象
	 */
	private static Session buildSession(String dbServer, String dbSchema){
		String dbKey = getDbKey(dbServer, dbSchema);
		
		//如果Hibernate未加载该DB工厂，加载DB工厂
		if (!factorys.containsKey(dbKey)) {
			buildFactory(dbServer, dbSchema);
		}

		//从Hibernate工厂中取得DB会话对象
		Session session = factorys.get(dbKey).openSession();
		
		if (log.isDebugEnabled()) {
			log.debug("线程[" + Thread.currentThread().getName() + "]取得数据库[" + dbKey + "]连接成功");
		}
		
		return session;
	}
	
	/**
	 * 创建服务的DB工厂
	 * @param dbServer DB服务名
	 * @param dbSchema DB用户名
	 */
	private static synchronized void buildFactory(String dbServer, String dbSchema){
		String dbKey = getDbKey(dbServer, dbSchema);
		if (factorys.containsKey(dbKey)) return;

		//从配置文件中读取DB配置内容
		ConfigUtil config = ConfigUtil.getConfig("/org/yehongyu/websale/db/cfg/db.properties");
		String cfg = config.getPropValue(dbServer); //dbCfg.getString(valueOf(dbServer));
		String schema = config.getPropValue(dbSchema); //dbCfg.getString(valueOf(dbSchema));
//		String cfg="/org/yehongyu/websale/db/cfg/mydb.cfg.xml";
//		String schema = null;
		Configuration conf = new Configuration().configure(cfg);
		if (dbSchema != null) conf.setProperty("hibernate.default_schema", schema);
		SessionFactory factory = conf.buildSessionFactory();
		factorys.put(dbKey, factory);
		
		log.info("创建HibernateSession工厂[" + dbKey + "]成功");
	}
	
	/**
	 * 取得线程组中保存的DB会话对象KEY值
	 * @param dbServer DB服务名
	 * @param dbSchema DB用户名
	 * @return 线程组中保存的DB会话对象KEY值
	 */
	private static String getDbKey(String dbServer, String dbSchema) {
		return (dbSchema == null) ? dbServer : dbServer + "@" + dbSchema;
	}
}
