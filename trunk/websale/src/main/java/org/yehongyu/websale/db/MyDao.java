/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.bean.PageRecordBean;

/**
 * 【类说明】公共DB会话类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:18:29
 */
abstract class MyDao implements MyDaoManager {
	/** 存储过程无返回值类型 */
	public static final int PROCEDURE_RETURN_TYPE_NORET = Integer.MIN_VALUE;
	/** 错误信息---持久类对象不能为null */
	private static final String ERR_MSG_NULL_PERSISTENCE_OBJECT = "持久类对象不能为null";
	/** SQL语句---取得序列号 */
	private static final String SQL_GET_SEQUENCE = "select %%1%%.nextval as cnt from dual";
	/** SQL语句---存储过程模版 */
	private static final String PROCEDURE_TEMPLATE = "{call %%1%%(%%2%%)}";	
	/** 日志对象 */
	protected final Logger log = Logger.getLogger(this.getClass());

	
	/**
	 * 取得当前管理类默认DB会话连接
	 * @return 当前管理类默认DB会话连接
	 * @throws MyException 取得默认DB会话时发生异常
	 */
	abstract MySessionBean openSession() throws MyException;
	
	/**
	 * 根据持久类类对象与记录主键值取得默认DB对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @return 记录持久类对象
	 * @throws MyException 取得记录时发生异常或持久类所对应表中无此主键记录
	 * @see #load(Class, Serializable, int)
	 * @see #find(Class, Serializable)
	 * @see #find(Class, Serializable, int)
	 */
	public final Serializable load(Class theClass, Serializable id) throws MyException {
		return load(theClass, id, openSession());
	}
	/**
	 * 根据持久类类对象与记录主键值取得指定DB对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @param dbType 指定操作的DB索引
	 * @return 记录持久类对象
	 * @throws MyException 取得记录时发生异常或持久类所对应表中无此主键记录
	 * @see #load(Class, Serializable)
	 * @see #find(Class, Serializable)
	 * @see #find(Class, Serializable, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final Serializable load(Class theClass, Serializable id, int dbType) throws MyException {
		return load(theClass, id, getDbTypeSession(dbType));
	}
	/**
	 * 根据持久类对象与记录主键值取得指定DB会话对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @param sessionBean 指定DB会话对象
	 * @return 记录持久类对象
	 * @throws MyException 取得记录时发生异常或持久类所对应表中无此主键记录
	 */
	private Serializable load(Class theClass, Serializable id, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		Serializable po = (Serializable)session.load(theClass, id);
		//session.flush();
		return po;
	}
	
	/**
	 * 根据持久类类对象与记录主键值取得指定DB对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @return 记录存在时，返回记录持久类对象；否则，返回null
	 * @throws MyException 取得记录时发生异常
	 * @see #find(Class, Serializable, int)
	 * @see #load(Class, Serializable)
	 * @see #load(Class, Serializable, int)
	 */
	public final Serializable find(Class theClass, Serializable id) throws MyException {
		return find(theClass, id, openSession());
	}
	/**
	 * 根据持久类类对象与记录主键值取得指定DB对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @param dbType 指定操作的DB索引
	 * @return 记录存在时，返回记录持久类对象；否则，返回null
	 * @throws MyException 取得记录时发生异常
	 * @see #find(Class, Serializable)
	 * @see #load(Class, Serializable)
	 * @see #load(Class, Serializable, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final Serializable find(Class theClass, Serializable id, int dbType) throws MyException {
		return find(theClass, id, getDbTypeSession(dbType));
	}
	/**
	 * 根据持久类对象与记录主键值取得指定DB会话对应表的唯一记录。
	 * @param theClass 持久类类对象
	 * @param id 记录主键值
	 * @param sessionBean 指定DB会话对象
	 * @return 记录存在时，返回记录持久类对象；否则，返回null
	 * @throws MyException 取得记录时发生异常
	 */
	private Serializable find(Class theClass, Serializable id, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		Serializable po = (Serializable)session.get(theClass, id);
		//session.flush();
		return po;
	}
	
	/**
	 * 将持久类中内容保存到默认DB
	 * @param obj 持久类对象内容
	 * @return 新生成的记录主键
	 * @throws MyException 保存持久类内容时发生异常
	 * @see #save(Serializable, int)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 */
	public final Serializable save(Serializable obj) throws MyException {
		return save(obj, openSession());
	}
	/**
	 * 将持久类中内容保存到默认DB
	 * @param obj 持久类对象内容
	 * @param dbType 指定操作的DB索引
	 * @return 新生成的记录主键
	 * @throws MyException 保存持久类内容时发生异常
	 * @see #save(Serializable)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final Serializable save(Serializable obj, int dbType) throws MyException {
		return save(obj, getDbTypeSession(dbType));
	}
	/**
	 * 将持久类中内容保存到默认DB
	 * @param obj 持久类对象内容
	 * @param sessionBean 指定DB会话对象
	 * @return 新生成的记录主键
	 * @throws MyException 保存持久类内容时发生异常
	 */
	private Serializable save(Serializable obj, MySessionBean sessionBean) throws MyException {
		if (obj == null) throw new MyException(ERR_MSG_NULL_PERSISTENCE_OBJECT);
		beginTransaction(sessionBean);
		Session session = sessionBean.getSession();
		Serializable key = session.save(obj);
		session.flush();
		return key;
	}
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB的记录执行新增或更新操作
	 * @param obj 持久类对象
	 * @throws MyException 执行DB操作时发生异常
	 * @see #saveOrUpdate(Serializable, int)
	 * @see #save(Serializable)
	 * @see #save(Serializable, int)
	 * @see #update(Serializable)
	 * @see #update(Serializable, int)
	 */
	public final void saveOrUpdate(Serializable obj) throws MyException {
		saveOrUpdate(obj, openSession());
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行新增或更新操作
	 * @param obj 持久类对象
	 * @param dbType 指定操作的DB索引
	 * @throws MyException 执行DB操作时发生异常
	 * @see #saveOrUpdate(Serializable)
	 * @see #save(Serializable)
	 * @see #save(Serializable, int)
	 * @see #update(Serializable)
	 * @see #update(Serializable, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final void saveOrUpdate(Serializable obj, int dbType) throws MyException {
		saveOrUpdate(obj, getDbTypeSession(dbType));
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对指定DB的记录执行新增或更新操作
	 * @param obj 持久类对象
	 * @param sessionBean 指定DB会话对象
	 * @throws MyException 执行DB操作时发生异常
	 */
	private void saveOrUpdate(Serializable obj, MySessionBean sessionBean) throws MyException {
		if (obj == null) throw new MyException(ERR_MSG_NULL_PERSISTENCE_OBJECT);
		beginTransaction(sessionBean);
		Session session = sessionBean.getSession();
		session.saveOrUpdate(obj);
		session.flush();
	}
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行更新操作
	 * @param obj 持久类对象
	 * @throws MyException 执行DB操作时发生异常
	 * @see #update(Serializable, int)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 */
	public final void update(Serializable obj) throws MyException {
		update(obj, openSession());
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行更新操作
	 * @param obj 持久类对象
	 * @param dbType 指定操作的DB索引
	 * @throws MyException 执行DB操作时发生异常
	 * @see #update(Serializable)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final void update(Serializable obj, int dbType) throws MyException {
		update(obj, getDbTypeSession(dbType));
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对指定DB记录执行更新操作
	 * @param obj 持久类对象
	 * @param sessionBean 指定DB会话对象
	 * @throws MyException 执行DB操作时发生异常
	 */
	private void update(Serializable obj, MySessionBean sessionBean) throws MyException {
		if (obj == null) throw new MyException(ERR_MSG_NULL_PERSISTENCE_OBJECT);
		beginTransaction(sessionBean);
		Session session = sessionBean.getSession();
		session.update(obj);
		session.flush();
	}
	
	/**
	 * 根据SQL语句更新默认DB记录
	 * @param sql SQL语句
	 * @return 更新记录数
	 * @throws MyException 更新DB时发生异常
	 * @see #update(String, int)
	 * @see #update(String, Map)
	 * @see #update(String, Map, int)
	 */
	protected final int update(String sql) throws MyException {
		return update(sql, null, openSession());
	}
	
	/**
	 * 根据SQL语句更新指定DB记录
	 * @param sql SQL语句
	 * @param dbType DB索引
	 * @return 更新记录数
	 * @throws MyException 更新DB时发生异常
	 * @see #update(String)
	 * @see #update(String, Map)
	 * @see #update(String, Map, int)
	 */
	protected final int update(String sql, int dbType) throws MyException {
		return update(sql, null, getDbTypeSession(dbType));
	}
	
	/**
	 * 根据SQL语句更新默认DB记录
	 * @param sql SQL语句
	 * @param params 更新条件
	 * @return 更新记录数
	 * @throws MyException 更新DB时发生异常
	 * @see #update(String)
	 * @see #update(String, int)
	 * @see #update(String, Map, int)
	 */
	protected final int update(String sql, Map<String, Object> params) throws MyException {
		return update(sql, params, openSession());
	}
	
	/**
	 * 根据SQL语句更新指定DB记录
	 * @param sql SQL语句
	 * @param params 更新条件
	 * @param dbType 指定DB索引
	 * @return 更新记录数
	 * @throws MyException 更新DB时发生异常
	 * @see #update(String)
	 * @see #update(String, int)
	 * @see #update(String, Map)
	 */
	protected final int update(String sql, Map<String, Object> params, int dbType) throws MyException {
		return update(sql, params, getDbTypeSession(dbType));
	}
	
	/**
	 * 根据SQL语句更新指定DB记录
	 * @param sql SQL语句
	 * @param params 更新条件
	 * @param sessionBean DB会话对象
	 * @return 更新记录数
	 * @throws MyException 更新DB时发生异常
	 */
	private int update(String sql, Map<String, Object> params, MySessionBean sessionBean) throws MyException {
		this.beginTransaction(sessionBean);
		Query q = this.createQuery(sql, params, sessionBean.getSession());
		return q.executeUpdate();
	}
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行删除操作
	 * @param obj 持久类对象
	 * @throws MyException 执行DB操作时发生异常
	 * @see #delete(Serializable, int)
	 */
	public final void delete(Serializable obj) throws MyException {
		delete(obj, openSession());
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对指定DB记录执行删除操作
	 * @param obj 持久类对象
	 * @param dbType 指定操作的DB索引
	 * @throws MyException 执行DB操作时发生异常
	 * @see #delete(Serializable)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final void delete(Serializable obj, int dbType) throws MyException {
		delete(obj, getDbTypeSession(dbType));
	}
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对指定DB记录执行删除操作
	 * @param obj 持久类对象
	 * @param sessionBean 指定DB会话对象
	 * @throws MyException 执行DB操作时发生异常
	 */ 
	private void delete(Serializable obj, MySessionBean sessionBean) throws MyException {
		if (obj == null) throw new MyException(ERR_MSG_NULL_PERSISTENCE_OBJECT);
		beginTransaction(sessionBean);
		Session session = sessionBean.getSession();
		session.delete(obj);
		session.flush();
	}
	
	/**
	 * 根据查询条件，对默认DB进行检索
	 * @param queryString 查询SQL语句
	 * @return 查询结果记录集(Iterator)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 */
	protected final Iterator iteratorQuery(String queryString) throws MyException {
		List lst = query(queryString, null, 0, openSession());
		return (lst == null) ? null : lst.iterator();
	}
	/**
	 * 根据查询条件与查询参数，对默认DB进行检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @return 查询结果记录集(Iterator)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 */
	protected final Iterator iteratorQuery(String queryString, Map<String, Object>bean) throws MyException {
		List lst = query(queryString, bean, 0, openSession());
		return (lst == null) ? null : lst.iterator();
	}
	/**
	 * 根据查询条件，对指定DB进行检索
	 * @param queryString 查询SQL语句
	 * @param dbType 指定DB索引
	 * @return 查询结果记录集(Iterator)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final Iterator iteratorQuery(String queryString, int dbType) throws MyException {
    	List lst = query(queryString, null, 0, getDbTypeSession(dbType));
    	return (lst == null) ? null : lst.iterator();
    }
	/**
	 * 根据查询条件与查询参数，对指定DB进行检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param dbType 指定DB索引
	 * @return 查询结果记录集(Iterator)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final Iterator iteratorQuery(String queryString, Map<String, Object>bean, int dbType) throws MyException {
		List lst = query(queryString, bean, 0, getDbTypeSession(dbType));
		return (lst == null) ? null : lst.iterator();
	}
	
	/**
	 * 根据查询条件，对默认DB进行检索
	 * @param queryString 查询SQL语句
	 * @return 查询结果记录集(List)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 */
	protected final List query(String queryString) throws MyException {
		return query(queryString, null, 0, openSession());
	}
	/**
	 * 根据查询条件与查询参数，对默认DB进行检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @return 查询结果记录集(List)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, int)
	 * @see #query(String, Map, int)
	 */
	protected final List query(String queryString, Map<String, Object>bean) throws MyException {
		return query(queryString, bean, 0, openSession());
	}
	protected final List queryWithListParams(String queryString, Map<String, Object>params) throws MyException {
		return query(queryString, params, 1, openSession());
	}
	/**
	 * 根据查询条件，对指定DB进行检索
	 * @param queryString 查询SQL语句
	 * @param dbType 指定DB索引
	 * @return 查询结果记录集(List)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, Map, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final List query(String queryString, int dbType) throws MyException {
		return query(queryString, null, 0, getDbTypeSession(dbType));
	}
	/**
	 * 根据查询条件与查询参数，对指定DB进行检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param dbType 指定DB索引
	 * @return 查询结果记录集(List)
	 * @throws MyException 查询结果记录时发生异常
	 * @see #iteratorQuery(String)
	 * @see #iteratorQuery(String, Map)
	 * @see #iteratorQuery(String, int)
	 * @see #iteratorQuery(String, Map, int)
	 * @see #query(String)
	 * @see #query(String, Map)
	 * @see #query(String, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final List query(String queryString, Map<String, Object>bean, int dbType) throws MyException {
		return query(queryString, bean, 0, getDbTypeSession(dbType));
	}
	/**
	 * 根据查询条件与查询参数，对指定DB进行检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param sessionBean DB会话对象
	 * @return 查询结果记录集(List)
	 * @throws MyException 查询结果记录时发生异常
	 */
	private List query(String queryString, Map<String, Object>bean, int paramType, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		Query q;
		if (paramType == 0) {
			q = createQuery(queryString, bean, session);
		} else {
			q = createQueryWithListParams(queryString, bean, session);
		}
		return q.list();
	}
	
	/**
	 * 使用JDBC方式查询数据库
	 * @param queryString 查询语句
	 * @return 查询结果
	 * @throws MyException 查询时发生异常
	 * @see #queryByJDBC(String, List)
	 * @see #queryByJDBC(String, int)
	 * @see #queryByJDBC(String, List, int)
	 */
	protected List<PageRecordBean> queryByJDBC(String queryString) throws MyException {
		return queryByJDBC(queryString, null, openSession());
	}
	/**
	 * 使用JDBC方式查询数据库
	 * @param queryString 查询语句
	 * @param params 查询参数
	 * @return 查询结果
	 * @throws MyException 查询时发生异常
	 * @see #queryByJDBC(String)
	 * @see #queryByJDBC(String, int)
	 * @see #queryByJDBC(String, List, int)
	 */
	protected List<PageRecordBean> queryByJDBC(String queryString, List<Object> params) throws MyException {
		return queryByJDBC(queryString, params, openSession());
	}
	/**
	 * 使用JDBC方式查询数据库
	 * @param queryString 查询语句
	 * @param dbType 指定DB索引
	 * @return 查询结果 查询参数
	 * @throws MyException 查询时发生异常
	 * @see #queryByJDBC(String)
	 * @see #queryByJDBC(String, List)
	 * @see #queryByJDBC(String, List, int)
	 */
	protected List<PageRecordBean> queryByJDBC(String queryString, int dbType) throws MyException {
		return queryByJDBC(queryString, null, getDbTypeSession(dbType));
	}
	/**
	 * 使用JDBC方式查询数据库
	 * @param queryString 查询语句
	 * @param params 查询参数
	 * @param dbType 指定DB索引
	 * @return 查询结果
	 * @throws MyException 查询时发生异常
	 * @see #queryByJDBC(String)
	 * @see #queryByJDBC(String, List)
	 * @see #queryByJDBC(String, int)
	 */
	protected List<PageRecordBean> queryByJDBC(String queryString, List<Object> params, int dbType) throws MyException {
		return queryByJDBC(queryString, params, getDbTypeSession(dbType));
	}
	
	private List<PageRecordBean> queryByJDBC(String queryString, List<Object> params, MySessionBean sessionBean) throws MyException {
		List<PageRecordBean> lstResult;
		PreparedStatement stmt = null;
		try {
			stmt = getPreparedStatement(queryString, params, sessionBean.getSession().connection());
			ResultSet rs = stmt.executeQuery();
			lstResult = new ArrayList<PageRecordBean>();
			while (rs != null && rs.next()) {
				lstResult.add(new PageRecordBean(rs));
			}
			rs.close();
		} catch (SQLException e) {
			throw new MyException("取得记录总数时发生异常", e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new MyException("关闭状态时发生异常", e);
			}
		}
		return lstResult;
	}
	
	/**
	 * 根据查询条件，对默认DB进行唯一记录检索
	 * @param queryString 查询SQL语句
	 * @return 查询记录持久类对象
	 * @throws MyException 查询记录时发生异常或者当前查询结果返回两条或两条以上的结果集
	 * @see #uniqueQuery(String, int)
	 * @see #uniqueQuery(String, Map)
	 * @see #uniqueQuery(String, Map, int)
	 */
	protected final Object uniqueQuery(String queryString) throws MyException {
		return uniqueQuery(queryString, null, openSession());
	}
	/**
	 * 根据查询条件与查询参数，对默认DB进行唯一记录检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @return 查询记录持久类对象
	 * @throws MyException 查询记录时发生异常或者当前查询结果返回两条或两条以上的结果集
	 * @see #uniqueQuery(String)
	 * @see #uniqueQuery(String, int)
	 * @see #uniqueQuery(String, Map, int)
	 */
	protected final Object uniqueQuery(String queryString, Map<String, Object>bean) throws MyException {
		return uniqueQuery(queryString, bean, openSession());
	}
	/**
	 * 根据查询条件，对默认DB进行唯一记录检索
	 * @param queryString 查询SQL语句
	 * @param dbType 指定DB索引
	 * @return 查询记录持久类对象
	 * @throws MyException 查询记录时发生异常或者当前查询结果返回两条或两条以上的结果集
	 * @see #uniqueQuery(String)
	 * @see #uniqueQuery(String, Map)
	 * @see #uniqueQuery(String, Map, int)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final Object uniqueQuery(String queryString, int dbType) throws MyException {
		return uniqueQuery(queryString, null, getDbTypeSession(dbType));
	}
	/**
	 * 根据查询条件与查询参数，对默认DB进行唯一记录检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param dbType 指定DB索引
	 * @return 查询记录持久类对象
	 * @throws MyException 查询记录时发生异常或者当前查询结果返回两条或两条以上的结果集
	 * @see #uniqueQuery(String)
	 * @see #uniqueQuery(String, int)
	 * @see #uniqueQuery(String, Map)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final Object uniqueQuery(String queryString, Map<String, Object>bean, int dbType) throws MyException {
		return uniqueQuery(queryString, bean, getDbTypeSession(dbType));
	}
	/**
	 * 根据查询条件与查询参数，对默认DB进行唯一记录检索
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param sessionBean 指定DB会话
	 * @return 查询记录持久类对象
	 * @throws MyException 查询记录时发生异常或者当前查询结果返回两条或两条以上的结果集
	 */
	private Object uniqueQuery(String queryString, Map<String, Object>bean, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		Query q = createQuery(queryString, bean, session);
		return q.uniqueResult();
	}
	
	/**
	 * 根据查询语句及查询条件，对默认DB数据进行分页查询
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param pageBean 分页信息
	 * @return 查询分页结果
	 * @throws MyException 查询数据时发生异常
	 * @see #getPageQuery(String, Map, PageBean, int)
	 */
	protected final Page getPageQuery(String queryString, Map<String, Object>bean, PageBean pageBean) throws MyException {
		return getPageQuery(queryString, bean, pageBean, openSession());
	}
	/**
	 * 根据查询语句及查询条件，对指定DB数据进行分页查询
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param pageBean 分页信息
	 * @param dbType 指定DB索引
	 * @return 查询分页结果
	 * @throws MyException 查询数据时发生异常
	 * @see #getPageQuery(String, Map, PageBean)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	protected final Page getPageQuery(String queryString, Map<String, Object>bean, PageBean pageBean, int dbType) throws MyException {
		return getPageQuery(queryString, bean, pageBean, getDbTypeSession(dbType));
	}
	/**
	 * 根据查询语句及查询条件，对指定DB数据进行分页查询
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param pageBean 分页信息
	 * @param sessionBean 指定DB索引
	 * @return 查询分页结果
	 * @throws MyException 查询数据时发生异常
	 */
	private Page getPageQuery(String queryString, Map<String, Object>bean, PageBean pageBean, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		//取得本次查询返回的记录数
		pageBean.setCount(getResultCnt(queryString, bean, sessionBean));
		if (pageBean.getCount() == 0) {
			return new Page(new ArrayList(), pageBean);
		}
		//取得本次查询返回结果集
		Query q = createQuery(queryString, bean, session);
		q.setFirstResult(pageBean.getStartNo() - 1);
		q.setMaxResults(pageBean.getEndNo() - pageBean.getStartNo() + 1);
		return new Page(q.list(), pageBean);
	}
	
	private Query createQuery(String queryString, Map<String, Object>bean, Session session) throws MyException {
		Query q = session.createQuery(queryString);
		String[] keys = q.getNamedParameters();
		if (keys == null || bean == null) return q;
		for (String key : keys) {
			if (!bean.containsKey(key))
				throw new MyException("没有设置参数" + key + "的值");
			Object value = bean.get(key);
			int flg = getParameterType(value);
			switch (flg) {
			case 0:
				q.setParameter(key, value);
				break;
			case 1:
				q.setParameterList(key, (Collection)value);
				break;
			case 2:
				q.setParameterList(key, (Object[])value);
				break;
			}
		}
		return q;
	}
	
	private Query createQueryWithListParams(String queryString, Map<String, Object>bean, Session session) throws MyException {
		Query q = session.createQuery(queryString);
		String[] keys = q.getNamedParameters();
		if (keys == null || bean == null) return q;
		for (String key : keys) {
			if (!bean.containsKey(key))
				throw new MyException("没有设置参数" + key + "的值");
			Object value = bean.get(key);
			int flg = getParameterType(value);
			switch (flg) {
			case 0:
				q.setParameter(key, value);
				break;
			case 1:
				q.setParameterList(key, (Collection)value);
				break;
			case 2:
				q.setParameterList(key, (Object[])value);
				break;
			}
			
		}
		return q;
	}
	
	private int getParameterType(Object param) {
		if (param == null) return 0;
		if (param.getClass().isArray()) return 2;
		Class[] clss = param.getClass().getInterfaces();
		if (clss == null) return 0;
		for (Class cls : clss) {
			if (cls == Collection.class) return 1;
		}
		return 0;
	}
	
	/**
	 * 使用JDBC方式对默认DB进行分页查询。
     * <p><pre>
     * String sql = "select * from on_cor_table, on_cor_table_shop";<br>
     * com.hc360.mmt.common.bean.PageBean pageBean = new PageBean();<br>
     * pageBean.setPage(1);<br>
     * pageBean.setPageSize(10);<br>
     * List lstResult = getPageQueryByJDBC(sql, pageBean);<br>
     * if (lstResult == null) return;<br>
     * for (com.hc360.mmt.common.bean.PageRecordBean rb : lstResult) {<br>
     *   Map<String, Object> onCorTable = rb.getTableRecord("on_cor_table");<br>
     *   System.out.println(onCorTable.get("providerid"));  //输出企业ID<br>
     *   Map<String, Object> onCorTableShop = rb.getTableRecord("on_cor_table_shop");<br>
     *   System.out.println(onCorTableShop.get("providerid"));  //输出企业ID<br>
     *   System.out.println(rb.getValue("on_cor_table", "providerid");  //输出企业ID<br>
     * }
     * </pre></p>
	 * @param queryString 查询SQL语句
	 * @param pageBean 分页对象
	 * @return 查询结果列表
	 * @throws MyException 分页查询时发生异常
	 */
	protected final Page getPageQueryByJDBC(String queryString, PageBean pageBean) throws MyException {
		return getPageQueryByJDBC(queryString, null, pageBean, openSession(), 0);
	}
	
	protected final Page getPageQueryRawByJDBC(String queryString, PageBean pageBean) throws MyException {
		return getPageQueryByJDBC(queryString, null, pageBean, openSession(), 1);
	}
	protected final Page getPageQueryRawByJDBC(String queryString, List<Object> params, PageBean pageBean) throws MyException {
		return getPageQueryByJDBC(queryString, params, pageBean, openSession(), 1);
	}
	/**
	 * 使用JDBC方式对指定DB进行分页查询
	 * @param queryString 查询SQL语句
	 * @param pageBean 分页对象
	 * @param dbType 指定DB索引
	 * @return 查询结果列表
	 * @throws MyException 分页查询时发生异常
	 */
	protected final Page getPageQueryByJDBC(String queryString, PageBean pageBean, int dbType) throws MyException {
		return getPageQueryByJDBC(queryString, null, pageBean, getDbTypeSession(dbType), 0);
	}
	/**
	 * 使用JDBC方式对默认DB进行分页查询
	 * @param queryString 查询SQL语句
	 * @param params 查询参数
	 * @param pageBean 分页对象
	 * @return 查询结果列表
	 * @throws MyException 分页查询时发生异常
	 */
	protected final Page getPageQueryByJDBC(String queryString, List<Object>params, PageBean pageBean) throws MyException {
		return getPageQueryByJDBC(queryString, params, pageBean, openSession(), 0);
	}
	/**
	 * 使用JDBC方式对指定DB进行分页查询
	 * @param queryString 查询SQL语句
	 * @param params 查询参数
	 * @param pageBean 分页对象
	 * @param dbType 指定DB索引
	 * @return 查询结果列表
	 * @throws MyException 分页查询时发生异常
	 */
	protected final Page getPageQueryByJDBC(String queryString, List<Object>params, PageBean pageBean, int dbType) throws MyException {
		return getPageQueryByJDBC(queryString, params, pageBean, getDbTypeSession(dbType), 0);
	}
	/**
	 * 使用JDBC方式对指定DB进行分页查询
	 * @param queryString 查询SQL语句
	 * @param params 查询参数
	 * @param pageBean 分页对象
	 * @param sessionBean DB会话对象
	 * @param type 查询类别
	 * @return 查询结果列表
	 * @throws MyException 分页查询时发生异常
	 */
	private Page getPageQueryByJDBC(String queryString, List<Object>params, PageBean pageBean, MySessionBean sessionBean, int type) throws MyException {
		//取得本次查询返回的记录数
		pageBean.setCount(getResultCntByJDBC(queryString, params, sessionBean, type));
		if (pageBean.getCount() == 0) {
			return new Page(new ArrayList(), pageBean);
		}
		
		//取得本次查询返回结果集
		String newSql = getLimitString(queryString, pageBean);
		
		List<PageRecordBean> lstResult;
		PreparedStatement stmt = null;
		try {
			stmt = getPreparedStatement(newSql, params, sessionBean.getSession().connection());
			ResultSet rs = stmt.executeQuery();
			lstResult = new ArrayList<PageRecordBean>();
			while (rs != null && rs.next()) {
				lstResult.add(new PageRecordBean(rs));
			}
			rs.close();
		} catch (SQLException e) {
			throw new MyException("取得记录总数时发生异常", e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new MyException("关闭状态时发生异常", e);
			}
		}
		return new Page(lstResult, pageBean);
	}
	
	
	
	private PreparedStatement getPreparedStatement(String queryString, List<Object> params, Connection conn) throws MyException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(queryString);
			if (params == null) return stmt;
			
			int idx = 1;
			for (Object obj : params) {
				if (obj != null) {
					stmt.setObject(idx, obj);
				} else {
					stmt.setNull(idx, Types.NULL);
				}
				idx++;
			}
		} catch (SQLException e) {
			throw new MyException("创建JDBC查询时发生异常[sql = " + queryString + "]", e);
		}

		return stmt;
	}
	
	private String getLimitString(String sql, PageBean pageBean) {
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (pageBean.getStartNo() > 1) {
			pagingSelect.append(
			  "select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (pageBean.getStartNo() > 1) {
			pagingSelect.append(" ) row_ where rownum <= ")
			            .append(pageBean.getEndNo())
			            .append(") where rownum_ >= ")
			            .append(pageBean.getStartNo());
		} else {
			pagingSelect.append(" ) where rownum <= ")
			            .append(pageBean.getEndNo());
		}
		return pagingSelect.toString();
	}
	
	/**
	 * 取得默认DB指定表的序列值
	 * @param seqName 要取得的序列名称
	 * @return 取得的序列值
	 * @throws MyException 取得序列值时发生异常
	 * @see #getSequence(String, int)
	 */
	public final long getSequence(String seqName) throws MyException {
		return getSequence(seqName, openSession());
	}
	/**
	 * 取得指定DB指定表的序列值
	 * @param seqName 要取得的序列名称
	 * @param dbType 指定DB索引
	 * @return 取得的序列值
	 * @throws MyException 取得序列值时发生异常
	 * @see #getSequence(String)
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	public final long getSequence(String seqName, int dbType) throws MyException {
		return getSequence(seqName, getDbTypeSession(dbType));
	}
	
	/**
	 * 取得指定DB指定表的序列值
	 * @param seqName 要取得的序列名称
	 * @param sessionBean 指定DB会话
	 * @return 取得的序列值
	 * @throws MyException 取得序列值时发生异常
	 */
	private long getSequence(String seqName, MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		String sql = SQL_GET_SEQUENCE.replaceAll("%%1%%", seqName);
		Connection conn = session.connection();
		Statement stmt = null;
		long count;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			count = (rs.next()) ? rs.getLong("cnt") : 0L;
		} catch (SQLException e) {
			throw new MyException("取得序列值时发生异常[seq_name : " + seqName + "]", e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new MyException("关闭状态时发生异常", e);
			}
		}

		return count;
	}
	
	/**
	 * 调用默认DB的存储过程
	 * @param procName 存储过程名
	 * @param params 存储过程参数
	 * @param returnTypes 存储过程返回值类型，必须为下述值之一：
	 * <li>java.sql.Types类中所有的类别</li>
	 * <li>无返回值时，传入com.hc360.mmt.MmtConstants.PROCEDURE_RETURN_TYPE_NORET</li>
	 * @return 成功时，返回true；失败时，返回false
	 * @throws MyException 调用存储过程时发生异常
	 * @see #callProcedure(int, String, List, int...)
	 * @see com.hc360.mmt.MmtConstants#PROCEDURE_RETURN_TYPE_NORET
	 */
	protected final Object callProcedure(String procName, List<Object> params, int... returnTypes) throws MyException {
		return callProcedure(procName, params, openSession(), returnTypes);
	}
	/**
	 * 调用默认DB的存储过程
	 * @param procName 存储过程名
	 * @param params 存储过程参数
	 * @param returnTypes 存储过程返回值类型，必须为下述值之一：
	 * <li>java.sql.Types类中所有的类别</li>
	 * <li>无返回值时，传入com.hc360.mmt.MmtConstants.PROCEDURE_RETURN_TYPE_NORET</li>
	 * @param dbType 指定DB索引
	 * @return 成功时，返回true；失败时，返回false
	 * @throws MyException 调用存储过程时发生异常
	 * @see #callProcedure(String, List, int...)
	 * @see com.hc360.mmt.MmtConstants#PROCEDURE_RETURN_TYPE_NORET
	 */
	protected final Object callProcedure(int dbType, String procName, List<Object> params, int... returnTypes) throws MyException {
		return callProcedure(procName, params, getDbTypeSession(dbType), returnTypes);
	}
	/**
	 * 调用默认DB的存储过程
	 * @param procName 存储过程名
	 * @param params 存储过程参数
	 * @param returnTypes 存储过程返回值类型，必须为下述值之一：
	 * <li>java.sql.Types类中所有的类别</li>
	 * <li>无返回值时，传入com.hc360.mmt.MmtConstants.PROCEDURE_RETURN_TYPE_NORET</li>
	 * @param sessionBean 指定DB会话
	 * @return 成功时，返回true；失败时，返回false
	 * @throws MyException 调用存储过程时发生异常
	 */
	private Object callProcedure(String procName, List<Object> params, MySessionBean sessionBean, int... returnTypes) throws MyException {
		//组合存储过程调用语句
		StringBuffer paramSql = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				if (paramSql.length() > 0) paramSql.append(",");
				paramSql.append("?");
			}
		}
		if (returnTypes != null) {
			for (int returnType : returnTypes) {
				if (returnType != PROCEDURE_RETURN_TYPE_NORET) {
					if (paramSql.length() > 0) paramSql.append(",");
					paramSql.append("?");
				}
			}
		}
		String procSql = PROCEDURE_TEMPLATE.replaceAll("%%1%%", procName)
		                .replaceAll("%%2%%", paramSql.toString());

		beginTransaction(sessionBean);
		Connection conn = sessionBean.getSession().connection();
		
		Object ret;
		CallableStatement cStmt = null;
		try {
			//设置调用存储过程参数
			cStmt = conn.prepareCall(procSql);
			if (params != null & params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					cStmt.setObject((i + 1), params.get(i));
				}
			}
			int retPos = (params == null) ? 0 : params.size();
			int outCnt = 0;
			if (returnTypes != null) {
				for (int returnType : returnTypes) {
					outCnt++;
					cStmt.registerOutParameter(retPos + outCnt, returnType);
				}
			}
			
			//调用存储过程
			cStmt.execute();
			if (outCnt > 0) {
				if (outCnt > 1) {
					Object[] retObj = new Object[outCnt];
					for (int i = 1; i <= outCnt; i++) {
						retObj[i -1] = cStmt.getObject(retPos + i);
					}
					ret = retObj;
				} else {
					ret = cStmt.getObject(retPos + 1);
				}
			} else {
				ret = null;
			}
			
		} catch (SQLException e) {
			throw new MyException("调用存储过程发生异常[sql = " + procSql + "]", e);
		} finally {
			try {
				if (cStmt != null) cStmt.close();
			} catch (SQLException e) {
				throw new MyException("关闭存储过程状态异常", e);
			}
		}

		return ret;
	}

	/**
	 * 根据查询条件及查询参数
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @return 查询结果记录集的记录数
	 * @throws MyException 查询数据时发生异常
	 */
	protected final int getResultCnt(String queryString, Map<String, Object>bean) throws MyException {
		return getResultCnt(queryString, bean, openSession());
	}
	
	/**
	 * 将给定的对象复制到持久类对象
	 * @param object 给定对象
	 * @return 持久类对象
	 * @throws MyException 复制时发生异常
	 * @see #merge(Object, int)
	 */
	protected final Object merge(Object object) throws MyException {
		return merge(object, openSession());
	}
	/**
	 * 将给定的对象复制到持久类对象
	 * @param object 给定对象
	 * @param dbType 指定DB索引
	 * @return 持久类对象
	 * @throws MyException 复制时发生异常
	 * @see #merge(Object)
	 */
	protected final Object merge(Object object, int dbType) throws MyException {
		return merge(object, getDbTypeSession(dbType));
	}
	private Object merge(Object object, MySessionBean sessionBean) throws MyException {
		this.beginTransaction(sessionBean);
		return sessionBean.getSession().merge(object);
	}
	
	/**
	 * 指定主键保存持久类对象
	 * @param po 持久类对象
	 * @param id 主键
	 * @throws MyException 保存对象时发生异常
	 * @see #classicSave(Object, Serializable, int)
	 */
//	public final void classicSave(Object po, Serializable id) throws MyException {
//		classicSave(po, id, openSession());
//	}
	/**
	 * 指定主键保存持久类对象
	 * @param po 持久类对象
	 * @param dbType 指定DB索引
	 * @param id 主键
	 * @throws MyException 保存对象时发生异常
	 * @see #classicSave(Object, Serializable)
	 */
//	public final void classicSave(Object po, Serializable id, int dbType) throws MyException {
//		classicSave(po, id, getDbTypeSession(dbType));
//	}
//	private void classicSave(Object po, Serializable id, MySessionBean sessionBean) throws MyException {
//		this.beginTransaction(sessionBean);
//		((org.hibernate.classic.Session)sessionBean.getSession()).save(po, id);
//	}
	
	/**
	 * 根据查询条件及查询参数
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param dbType DB类型
	 * @return 查询结果记录集的记录数
	 * @throws MyException 查询数据时发生异常
	 */
	protected final int getResultCnt(String queryString, Map<String, Object>bean, int dbType) throws MyException {
		return getResultCnt(queryString, bean, getDbTypeSession(dbType));
	}
	
	/**
	 * 根据查询条件及查询参数
	 * @param queryString 查询SQL语句
	 * @param bean 查询参数
	 * @param session DB会话对象
	 * @return 查询结果记录集的记录数
	 * @throws MyException 查询数据时发生异常
	 */
	private int getResultCnt(String queryString, Map<String, Object>bean, MySessionBean session) throws MyException {
		//删除查询语句中的order by子句
		String newSql =  delOrderbySQL(queryString);
		
		//修改SQL语句
		int idx = newSql.toUpperCase().indexOf("FROM ");
		newSql = "select count(*) " + newSql.substring(idx);
		
		//取得当前查询总记录数
		Query q = createQuery(newSql, bean, session.getSession());
		List lst = q.list();
		return (lst == null || lst.size() == 0) ? 0 : Integer.parseInt(lst.get(0).toString());
	}
	
	/**
	 * 使用JDBC方式取得查询语句的记录数
	 * @param queryString SQL语句
	 * @param params 查询参数
	 * @param sessionBean DB会话对象
	 * @param type 查询类别
	 * @return 查询语句返回的记录数
	 * @throws MyException 执行查询语句时发生异常
	 */
	private int getResultCntByJDBC(String queryString, List<Object>params, MySessionBean sessionBean, int type) throws MyException {
		//删除查询语句中的order by子句
		String newSql;
		
		//修改SQL语句
		if (type == 0) {
			newSql = delOrderbySQL(queryString);
			int idx = newSql.toUpperCase().indexOf("FROM ");
			newSql = "select count(*) as cnt " + newSql.substring(idx);
		} else {
			newSql = "select count(*) as cnt from (" + queryString + ")";
		}

		//取得当前查询总记录数
		Connection conn = sessionBean.getSession().connection();
		PreparedStatement stmt = null;
		int count;
		try {
			stmt = this.getPreparedStatement(newSql, params, conn);
			ResultSet rs = stmt.executeQuery();
			count = (rs.next()) ? rs.getInt("cnt") : 0;
			rs.close();
		} catch (SQLException e) {
			throw new MyException("取得记录总数时发生异常", e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new MyException("关闭状态时发生异常", e);
			}
		}
		
		return count;
	}
	
	/**
	 * 删除查询条件中的order by子句
	 * @param queryString 查询SQL语句
	 * @return 删除查询语句中的order by子句后的查询语句
	 */
	private String delOrderbySQL(String queryString) {
		StringBuffer temp = new StringBuffer();
		queryString = "(" + queryString + ")";

		String[] strArray = queryString.split("order by");
		for (int i = 1; i < strArray.length; i++) {
			strArray[i] = ")";
		}
		for (int i = 0; i < strArray.length; i++) {
			temp.append(strArray[i]);
		}
		String result = temp.toString();
		if (temp.length() >= 2) {
			result = result.substring(1, result.length() - 1);
		}
		return result;
	}
	
	/**
	 * 根据DB索引取得DB会话
	 * @param dbType DB索引值
	 * @return DB会话对象
	 * @throws MyException 取得DB会话对象时发生异常
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_BUSIN
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_CHAT
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_DETAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_KEYWORD
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MAIL
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_MARKET
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_ORDER
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SEARCH
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SHOW
	 * @see com.hc360.mmt.MmtConstants#DB_TYPE_SMS
	 */
	private MySessionBean getDbTypeSession(int dbType) throws MyException {
		MySessionBean sessionBean;
		switch (dbType) {
		case DBConstants.DB_TYPE_MYDB:
			sessionBean = MySessionFactory.openSession(DBConstants.CFG_PROP_DB_MYDB, null);
			break;
		default:
			throw new MyException("错误的DB类型[DB_TYPE = " + dbType + "]");
		}
		return sessionBean;
	}
	
	/**
	 * 开始一个数据库事务
	 * @param sessionBean DB会话对象
	 * @throws MyException 开始数据库事务时发生异常
	 */
	private void beginTransaction(MySessionBean sessionBean) throws MyException {
		Transaction tx = sessionBean.getTransaction();
		if (tx != null && tx.isActive() 
		&& !tx.wasCommitted() && !tx.wasRolledBack()) {
			return;
		}
		sessionBean.setTransaction(sessionBean.getSession().beginTransaction());
	}
	
	/**
	 * 刷新默认DB会话
	 * @throws MyException 刷新会话发生异常
	 */
	public final void flush() throws MyException {
		flush(openSession());
	}
	/**
	 * 刷新指定DB会话
	 * @param dbType 指定DB索引
	 * @throws MyException 刷新会话发生异常
	 */
	public final void flush(int dbType) throws MyException {
		flush(getDbTypeSession(dbType));
	}
	private void flush(MySessionBean sessionBean) throws MyException {
		Session session = sessionBean.getSession();
		session.flush();
	}
	
	/**
	 * 清除当前DB默认会话的缓存内容
	 * @throws MyException 清楚缓存内容时发生异常
	 */
	public final void clear() throws MyException {
		clear(openSession());
	}
	/**
	 * 清除指定DB会话的缓存内容
	 * @throws MyException 清楚缓存内容时发生异常
	 */
	public final void clear(int dbType) throws MyException {
		clear(getDbTypeSession(dbType));
	}
	private final void clear(MySessionBean sessionBean) throws MyException {
		sessionBean.getSession().clear();
	}
	
	public final void commit() throws MyException {
		commit(openSession());
	}
	public final void commit(int dbType) throws MyException {
		commit(getDbTypeSession(dbType));
	}
	private void commit(MySessionBean sessionBean) throws MyException {
		MySessionFactory.commit(sessionBean);
	}
	public final void rollback() throws MyException {
		rollback(openSession());
	}
	public final void rollback(int dbType) throws MyException {
		rollback(getDbTypeSession(dbType));
	}
	private void rollback(MySessionBean sessionBean) throws MyException {
		MySessionFactory.rollback(sessionBean);
	}
	
	/**
	 * 直接使用HSQL语句进行查询，调用hibernate的executeUpdate的方式
	 * @param queryString 要查询的HSQL语句
	 * @param bean 要查询的参数
	 * @return int 受影响的记录条数
	 * @throws MyException 操作失败会发生异常
	 */
	protected final int executeUpdate(String queryString, Map<String, Object>bean) throws MyException {
		MySessionBean sessionBean = openSession(); 
		beginTransaction(sessionBean);
		Session session = sessionBean.getSession();
		return this.createQuery(queryString, bean, session).executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.hc360.mmt.db.dao.MmtDaoManager#queryList(java.lang.String, java.util.Map)
	 */
	public List queryList(String queryString, Map<String, Object> bean) throws MyException {		
		return query(queryString, bean);
	}
}
