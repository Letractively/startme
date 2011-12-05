/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.yehongyu.websale.common.util.MyException;

/**
 * 【类说明】Dao基类接口
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:18:57
 */
public interface MyDaoManager {

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
	public Serializable load(Class theClass, Serializable id) throws MyException;
	
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
	public Serializable load(Class theClass, Serializable id, int dbType) throws MyException;
	
	
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
	public Serializable find(Class theClass, Serializable id) throws MyException;
	
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
	public Serializable find(Class theClass, Serializable id, int dbType) throws MyException;
	
	/**
	 * 将持久类中内容保存到默认DB
	 * @param obj 持久类对象内容
	 * @return 新生成的记录主键
	 * @throws MyException 保存持久类内容时发生异常
	 * @see #save(Serializable, int)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 */
	public Serializable save(Serializable obj) throws MyException;
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
	public Serializable save(Serializable obj, int dbType) throws MyException;
	
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
	public void saveOrUpdate(Serializable obj) throws MyException;
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
	public void saveOrUpdate(Serializable obj, int dbType) throws MyException;
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行更新操作
	 * @param obj 持久类对象
	 * @throws MyException 执行DB操作时发生异常
	 * @see #update(Serializable, int)
	 */
	public void update(Serializable obj) throws MyException;
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行更新操作
	 * @param obj 持久类对象
	 * @param dbType 指定操作的DB索引
	 * @throws MyException 执行DB操作时发生异常
	 * @see #update(Serializable)
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
	public void update(Serializable obj, int dbType) throws MyException;
	
	/**
	 * 根据HIBERNATE查询池中的缓存与持久类对象状态比较，对默认DB记录执行删除操作
	 * @param obj 持久类对象
	 * @throws MyException 执行DB操作时发生异常
	 * @see #delete(Serializable, int)
	 */
	public void delete(Serializable obj) throws MyException;
	
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
	public void delete(Serializable obj, int dbType) throws MyException;
	
	/**
	 * 取得默认DB指定表的序列值
	 * @param seqName 要取得的序列名称
	 * @return 取得的序列值
	 * @throws MyException 取得序列值时发生异常
	 * @see #getSequence(String, int)
	 */
	public long getSequence(String seqName) throws MyException;
	
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
	public long getSequence(String seqName, int dbType) throws MyException;
	
	/**
	 * 指定主键保存持久类对象
	 * @param po 持久类对象
	 * @param id 主键
	 * @throws MyException 保存对象时发生异常
	 * @see #classicSave(Object, Serializable, int)
	 */
//	public void classicSave(Object po, Serializable id) throws MyException;
	/**
	 * 指定主键保存持久类对象
	 * @param po 持久类对象
	 * @param dbType 指定DB索引
	 * @param id 主键
	 * @throws MyException 保存对象时发生异常
	 * @see #classicSave(Object, Serializable)
	 */
//	public void classicSave(Object po, Serializable id, int dbType) throws MyException;
	
	
	
	/**
	 * 刷新默认DB会话
	 * @throws MyException 刷新会话发生异常
	 */
	public void flush() throws MyException;
	/**
	 * 刷新指定DB会话
	 * @param dbType 指定DB索引
	 * @throws MyException 刷新会话发生异常
	 */
	public void flush(int dbType) throws MyException;
	
	/**
	 * 清除当前DB默认会话的缓存内容
	 * @throws MyException 清楚缓存内容时发生异常
	 */
	public void clear() throws MyException;
	/**
	 * 清除指定DB会话的缓存内容
	 * @throws MyException 清楚缓存内容时发生异常
	 */
	public void clear(int dbType) throws MyException;
	
	public void commit() throws MyException;
	
	public void commit(int dbType) throws MyException;
	
	public void rollback() throws MyException;
	
	public void rollback(int dbType) throws MyException;
	
	
	//add by sbm MMT_4.2
	
	
	/**
	 * 查询的通用方法
	 * @param queryString 要查询的HQL语句
	 * @param bean 参数map
	 * @return List对象 查询结果集对象
	 * @since 4.2
	 * @throws MyException
	 */
	public List queryList(String queryString, Map<String, Object>bean) throws MyException;
	
	
	
	
	//add by sbm MMT_4.2
}
