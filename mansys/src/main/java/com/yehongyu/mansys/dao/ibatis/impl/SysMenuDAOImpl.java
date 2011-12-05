package com.yehongyu.mansys.dao.ibatis.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.ibatis.BaseDAO;
import com.yehongyu.mansys.dao.ibatis.SysMenuDAO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;
import com.yehongyu.mansys.dao.validate.SysMenuValidate;

/**
 * sys_menu操作实现类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuDAOImpl implements SysMenuDAO {
	@Resource
	private BaseDAO baseDAO;
	
	public SysMenuDO getSysMenuDO(SysMenuQuery sysMenuQuery){
		SysMenuValidate.getSysMenuDO(sysMenuQuery);
		return (SysMenuDO)baseDAO.getSqlMapClientTemplate().queryForObject("SysMenuDAO.getSysMenuDO", sysMenuQuery);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysMenuDO> getSysMenuDOList(SysMenuQuery sysMenuQuery){
		if(sysMenuQuery==null) sysMenuQuery = new SysMenuQuery();
		SysMenuValidate.getSysMenuDOList(sysMenuQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		return (List<SysMenuDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysMenuDAO.getSysMenuDOList", sysMenuQuery);
	}

	@SuppressWarnings("unchecked")
	public List<SysMenuDO> getSysMenuDOListWithPage(SysMenuQuery sysMenuQuery){
		if(sysMenuQuery==null) sysMenuQuery = new SysMenuQuery();
		SysMenuValidate.getSysMenuDOListWithPage(sysMenuQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		int count = (Integer)baseDAO.getSqlMapClientTemplate().queryForObject("SysMenuDAO.getSysMenuDOListCount",sysMenuQuery);
		sysMenuQuery.setTotalItem(count);
		return (List<SysMenuDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysMenuDAO.getSysMenuDOListWithPage", sysMenuQuery);
	}

	public Long insertSysMenuDO(SysMenuDO sysMenuDO){
		SysMenuValidate.insertSysMenuDO(sysMenuDO);
		return (Long)baseDAO.getSqlMapClientTemplate().insert("SysMenuDAO.insertSysMenuDO", sysMenuDO);
	}

	public Integer updateSysMenuDO(SysMenuDO sysMenuDO){
		SysMenuValidate.updateSysMenuDO(sysMenuDO);
		return baseDAO.getSqlMapClientTemplate().update("SysMenuDAO.updateSysMenuDO", sysMenuDO);
	}

	public Integer updateSysMenuDOList(SysMenuDO sysMenuDO){
		SysMenuValidate.updateSysMenuDOList(sysMenuDO);
		return baseDAO.getSqlMapClientTemplate().update("SysMenuDAO.updateSysMenuDOList", sysMenuDO);
	}
	
	public Integer deleteSysMenuDO(Long id){
		SysMenuValidate.deleteSysMenuDO(id);
		return baseDAO.getSqlMapClientTemplate().delete("SysMenuDAO.deleteSysMenuDO", id);
	}
	
	public Integer deleteSysMenuDOList(SysMenuQuery sysMenuQuery){
		SysMenuValidate.deleteSysMenuDOList(sysMenuQuery);
		return baseDAO.getSqlMapClientTemplate().delete("SysMenuDAO.deleteSysMenuDOList", sysMenuQuery);
	}

}
