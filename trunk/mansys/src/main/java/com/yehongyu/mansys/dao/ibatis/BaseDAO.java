package com.yehongyu.mansys.dao.ibatis;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * DAO����
 * @author yingyang
 * @since 2011-11-11
 */
public class BaseDAO {
	
	/** ���ݿ�������� */
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
