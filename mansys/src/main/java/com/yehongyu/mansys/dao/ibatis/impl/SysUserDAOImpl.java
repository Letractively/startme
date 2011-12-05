package com.yehongyu.mansys.dao.ibatis.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.ibatis.BaseDAO;
import com.yehongyu.mansys.dao.ibatis.SysUserDAO;
import com.yehongyu.mansys.dao.query.SysUserQuery;
import com.yehongyu.mansys.dao.validate.SysUserValidate;

/**
 * sys_user操作实现类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserDAOImpl implements SysUserDAO {
	@Resource
	private BaseDAO baseDAO;
	
	public SysUserDO getSysUserDO(SysUserQuery sysUserQuery){
		SysUserValidate.getSysUserDO(sysUserQuery);
		return (SysUserDO)baseDAO.getSqlMapClientTemplate().queryForObject("SysUserDAO.getSysUserDO", sysUserQuery);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserDO> getSysUserDOList(SysUserQuery sysUserQuery){
		if(sysUserQuery==null) sysUserQuery = new SysUserQuery();
		SysUserValidate.getSysUserDOList(sysUserQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		return (List<SysUserDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysUserDAO.getSysUserDOList", sysUserQuery);
	}

	@SuppressWarnings("unchecked")
	public List<SysUserDO> getSysUserDOListWithPage(SysUserQuery sysUserQuery){
		if(sysUserQuery==null) sysUserQuery = new SysUserQuery();
		SysUserValidate.getSysUserDOListWithPage(sysUserQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		int count = (Integer)baseDAO.getSqlMapClientTemplate().queryForObject("SysUserDAO.getSysUserDOListCount",sysUserQuery);
		sysUserQuery.setTotalItem(count);
		return (List<SysUserDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysUserDAO.getSysUserDOListWithPage", sysUserQuery);
	}

	public Long insertSysUserDO(SysUserDO sysUserDO){
		SysUserValidate.insertSysUserDO(sysUserDO);
		return (Long)baseDAO.getSqlMapClientTemplate().insert("SysUserDAO.insertSysUserDO", sysUserDO);
	}

	public Integer updateSysUserDO(SysUserDO sysUserDO){
		SysUserValidate.updateSysUserDO(sysUserDO);
		return baseDAO.getSqlMapClientTemplate().update("SysUserDAO.updateSysUserDO", sysUserDO);
	}

	public Integer updateSysUserDOList(SysUserDO sysUserDO){
		SysUserValidate.updateSysUserDOList(sysUserDO);
		return baseDAO.getSqlMapClientTemplate().update("SysUserDAO.updateSysUserDOList", sysUserDO);
	}
	
	public Integer deleteSysUserDO(Long id){
		SysUserValidate.deleteSysUserDO(id);
		return baseDAO.getSqlMapClientTemplate().delete("SysUserDAO.deleteSysUserDO", id);
	}
	
	public Integer deleteSysUserDOList(SysUserQuery sysUserQuery){
		SysUserValidate.deleteSysUserDOList(sysUserQuery);
		return baseDAO.getSqlMapClientTemplate().delete("SysUserDAO.deleteSysUserDOList", sysUserQuery);
	}

}
