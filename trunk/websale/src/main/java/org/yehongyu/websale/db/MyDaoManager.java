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
 * ����˵����Dao����ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:18:57
 */
public interface MyDaoManager {

	/**
	 * ���ݳ־�����������¼����ֵȡ��Ĭ��DB��Ӧ���Ψһ��¼��
	 * @param theClass �־��������
	 * @param id ��¼����ֵ
	 * @return ��¼�־������
	 * @throws MyException ȡ�ü�¼ʱ�����쳣��־�������Ӧ�����޴�������¼
	 * @see #load(Class, Serializable, int)
	 * @see #find(Class, Serializable)
	 * @see #find(Class, Serializable, int)
	 */
	public Serializable load(Class theClass, Serializable id) throws MyException;
	
	/**
	 * ���ݳ־�����������¼����ֵȡ��ָ��DB��Ӧ���Ψһ��¼��
	 * @param theClass �־��������
	 * @param id ��¼����ֵ
	 * @param dbType ָ��������DB����
	 * @return ��¼�־������
	 * @throws MyException ȡ�ü�¼ʱ�����쳣��־�������Ӧ�����޴�������¼
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
	 * ���ݳ־�����������¼����ֵȡ��ָ��DB��Ӧ���Ψһ��¼��
	 * @param theClass �־��������
	 * @param id ��¼����ֵ
	 * @return ��¼����ʱ�����ؼ�¼�־�����󣻷��򣬷���null
	 * @throws MyException ȡ�ü�¼ʱ�����쳣
	 * @see #find(Class, Serializable, int)
	 * @see #load(Class, Serializable)
	 * @see #load(Class, Serializable, int)
	 */
	public Serializable find(Class theClass, Serializable id) throws MyException;
	
	/**
	 * ���ݳ־�����������¼����ֵȡ��ָ��DB��Ӧ���Ψһ��¼��
	 * @param theClass �־��������
	 * @param id ��¼����ֵ
	 * @param dbType ָ��������DB����
	 * @return ��¼����ʱ�����ؼ�¼�־�����󣻷��򣬷���null
	 * @throws MyException ȡ�ü�¼ʱ�����쳣
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
	 * ���־��������ݱ��浽Ĭ��DB
	 * @param obj �־����������
	 * @return �����ɵļ�¼����
	 * @throws MyException ����־�������ʱ�����쳣
	 * @see #save(Serializable, int)
	 * @see #saveOrUpdate(Serializable)
	 * @see #saveOrUpdate(Serializable, int)
	 */
	public Serializable save(Serializable obj) throws MyException;
	/**
	 * ���־��������ݱ��浽Ĭ��DB
	 * @param obj �־����������
	 * @param dbType ָ��������DB����
	 * @return �����ɵļ�¼����
	 * @throws MyException ����־�������ʱ�����쳣
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
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���Ĭ��DB�ļ�¼ִ����������²���
	 * @param obj �־������
	 * @throws MyException ִ��DB����ʱ�����쳣
	 * @see #saveOrUpdate(Serializable, int)
	 * @see #save(Serializable)
	 * @see #save(Serializable, int)
	 * @see #update(Serializable)
	 * @see #update(Serializable, int)
	 */
	public void saveOrUpdate(Serializable obj) throws MyException;
	/**
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���Ĭ��DB��¼ִ����������²���
	 * @param obj �־������
	 * @param dbType ָ��������DB����
	 * @throws MyException ִ��DB����ʱ�����쳣
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
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���Ĭ��DB��¼ִ�и��²���
	 * @param obj �־������
	 * @throws MyException ִ��DB����ʱ�����쳣
	 * @see #update(Serializable, int)
	 */
	public void update(Serializable obj) throws MyException;
	
	/**
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���Ĭ��DB��¼ִ�и��²���
	 * @param obj �־������
	 * @param dbType ָ��������DB����
	 * @throws MyException ִ��DB����ʱ�����쳣
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
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���Ĭ��DB��¼ִ��ɾ������
	 * @param obj �־������
	 * @throws MyException ִ��DB����ʱ�����쳣
	 * @see #delete(Serializable, int)
	 */
	public void delete(Serializable obj) throws MyException;
	
	/**
	 * ����HIBERNATE��ѯ���еĻ�����־������״̬�Ƚϣ���ָ��DB��¼ִ��ɾ������
	 * @param obj �־������
	 * @param dbType ָ��������DB����
	 * @throws MyException ִ��DB����ʱ�����쳣
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
	 * ȡ��Ĭ��DBָ���������ֵ
	 * @param seqName Ҫȡ�õ���������
	 * @return ȡ�õ�����ֵ
	 * @throws MyException ȡ������ֵʱ�����쳣
	 * @see #getSequence(String, int)
	 */
	public long getSequence(String seqName) throws MyException;
	
	/**
	 * ȡ��ָ��DBָ���������ֵ
	 * @param seqName Ҫȡ�õ���������
	 * @param dbType ָ��DB����
	 * @return ȡ�õ�����ֵ
	 * @throws MyException ȡ������ֵʱ�����쳣
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
	 * ָ����������־������
	 * @param po �־������
	 * @param id ����
	 * @throws MyException �������ʱ�����쳣
	 * @see #classicSave(Object, Serializable, int)
	 */
//	public void classicSave(Object po, Serializable id) throws MyException;
	/**
	 * ָ����������־������
	 * @param po �־������
	 * @param dbType ָ��DB����
	 * @param id ����
	 * @throws MyException �������ʱ�����쳣
	 * @see #classicSave(Object, Serializable)
	 */
//	public void classicSave(Object po, Serializable id, int dbType) throws MyException;
	
	
	
	/**
	 * ˢ��Ĭ��DB�Ự
	 * @throws MyException ˢ�»Ự�����쳣
	 */
	public void flush() throws MyException;
	/**
	 * ˢ��ָ��DB�Ự
	 * @param dbType ָ��DB����
	 * @throws MyException ˢ�»Ự�����쳣
	 */
	public void flush(int dbType) throws MyException;
	
	/**
	 * �����ǰDBĬ�ϻỰ�Ļ�������
	 * @throws MyException �����������ʱ�����쳣
	 */
	public void clear() throws MyException;
	/**
	 * ���ָ��DB�Ự�Ļ�������
	 * @throws MyException �����������ʱ�����쳣
	 */
	public void clear(int dbType) throws MyException;
	
	public void commit() throws MyException;
	
	public void commit(int dbType) throws MyException;
	
	public void rollback() throws MyException;
	
	public void rollback(int dbType) throws MyException;
	
	
	//add by sbm MMT_4.2
	
	
	/**
	 * ��ѯ��ͨ�÷���
	 * @param queryString Ҫ��ѯ��HQL���
	 * @param bean ����map
	 * @return List���� ��ѯ���������
	 * @since 4.2
	 * @throws MyException
	 */
	public List queryList(String queryString, Map<String, Object>bean) throws MyException;
	
	
	
	
	//add by sbm MMT_4.2
}
