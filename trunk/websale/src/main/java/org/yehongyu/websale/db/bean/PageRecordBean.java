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
 * 【类说明】JDBC方式查询结果每记录对象
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:21:27
 */
public class PageRecordBean {
	/** 每记录存储结果映射 */
	private final Map<String, Object> columnMap = new HashMap<String, Object>();
	/** 每记录存储结果列表 */
	private final List<Object> lstColumn = new ArrayList<Object>();
	
	/**
	 * 根据记录集当前指向对象构造记录内容
	 * @param rs 查询记录集
	 * @throws MmtException 记录集操作发生异常
	 */
	public PageRecordBean(ResultSet rs) throws MyException {
		if (rs == null) return;
		setResultColumnInfo(rs);
	}
	
	/**
	 * 构造函数调用，保存记录集当前指向记录信息
	 * @param rs 记录集
	 * @throws MmtException 保存记录信息时发生异常
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
			throw new MyException("记录集操作发生异常", e);
		}
	}
	
	/**
	 * 根据返回结果记录集中列名取得对应记录信息
	 * @param columnName 列名，Oracle对应列名，不能使用Hibernate持久类中列名
	 * @return 指定列信息
	 */
	public Object getValue(String columnName) {
		return columnMap.get(columnName.toLowerCase());
	}
	
	/**
	 * 根据列编号取得记录信息
	 * @param idx 列编号，从零开始
	 * @return 记录信息
	 */
	public Object getValue(int idx) {
		return lstColumn.get(idx);
	}
}
