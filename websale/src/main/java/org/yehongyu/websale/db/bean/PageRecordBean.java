/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.db.bean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yehongyu.websale.common.util.MyException;

/**
 * ����˵����JDBC��ʽ��ѯ���ÿ��¼����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:21:27
 */
public class PageRecordBean {
	/** ÿ��¼�洢���ӳ�� */
	private final Map<String, Object> columnMap = new HashMap<String, Object>();
	/** ÿ��¼�洢����б� */
	private final List<Object> lstColumn = new ArrayList<Object>();
	
	/**
	 * ���ݼ�¼����ǰָ��������¼����
	 * @param rs ��ѯ��¼��
	 * @throws MmtException ��¼�����������쳣
	 */
	public PageRecordBean(ResultSet rs) throws MyException {
		if (rs == null) return;
		setResultColumnInfo(rs);
	}
	
	/**
	 * ���캯�����ã������¼����ǰָ���¼��Ϣ
	 * @param rs ��¼��
	 * @throws MmtException �����¼��Ϣʱ�����쳣
	 */
	private final void setResultColumnInfo(ResultSet rs) throws MyException {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCnt = rsmd.getColumnCount();
			for (int i = 1; i <= colCnt; i++) {
				String colName = rsmd.getColumnName(i).toLowerCase();
				Object value = rs.getObject(i);
				columnMap.put(colName, value);
				lstColumn.add(value);
			}
		} catch (SQLException e) {
			throw new MyException("��¼�����������쳣", e);
		}
	}
	
	/**
	 * ���ݷ��ؽ����¼��������ȡ�ö�Ӧ��¼��Ϣ
	 * @param columnName ������Oracle��Ӧ����������ʹ��Hibernate�־���������
	 * @return ָ������Ϣ
	 */
	public Object getValue(String columnName) {
		return columnMap.get(columnName.toLowerCase());
	}
	
	/**
	 * �����б��ȡ�ü�¼��Ϣ
	 * @param idx �б�ţ����㿪ʼ
	 * @return ��¼��Ϣ
	 */
	public Object getValue(int idx) {
		return lstColumn.get(idx);
	}
}
