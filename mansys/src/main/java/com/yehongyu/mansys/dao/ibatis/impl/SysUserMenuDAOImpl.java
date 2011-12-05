package com.yehongyu.mansys.dao.ibatis.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.ibatis.BaseDAO;
import com.yehongyu.mansys.dao.ibatis.SysUserMenuDAO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;
import com.yehongyu.mansys.dao.validate.SysUserMenuValidate;

/**
 * sys_user_menu操作实现类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuDAOImpl implements SysUserMenuDAO {
	@Resource
	private BaseDAO baseDAO;
	
	public SysUserMenuDO getSysUserMenuDO(SysUserMenuQuery sysUserMenuQuery){
		SysUserMenuValidate.getSysUserMenuDO(sysUserMenuQuery);
		return (SysUserMenuDO)baseDAO.getSqlMapClientTemplate().queryForObject("SysUserMenuDAO.getSysUserMenuDO", sysUserMenuQuery);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUserMenuDO> getSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery){
		if(sysUserMenuQuery==null) sysUserMenuQuery = new SysUserMenuQuery();
		SysUserMenuValidate.getSysUserMenuDOList(sysUserMenuQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		return (List<SysUserMenuDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysUserMenuDAO.getSysUserMenuDOList", sysUserMenuQuery);
	}

	@SuppressWarnings("unchecked")
	public List<SysUserMenuDO> getSysUserMenuDOListWithPage(SysUserMenuQuery sysUserMenuQuery){
		if(sysUserMenuQuery==null) sysUserMenuQuery = new SysUserMenuQuery();
		SysUserMenuValidate.getSysUserMenuDOListWithPage(sysUserMenuQuery);
		//如果是Oracle且查询条件有IdList,则SQL语句要优化下
		int count = (Integer)baseDAO.getSqlMapClientTemplate().queryForObject("SysUserMenuDAO.getSysUserMenuDOListCount",sysUserMenuQuery);
		sysUserMenuQuery.setTotalItem(count);
		return (List<SysUserMenuDO>)baseDAO.getSqlMapClientTemplate().queryForList("SysUserMenuDAO.getSysUserMenuDOListWithPage", sysUserMenuQuery);
	}

	public Long insertSysUserMenuDO(SysUserMenuDO sysUserMenuDO){
		SysUserMenuValidate.insertSysUserMenuDO(sysUserMenuDO);
		return (Long)baseDAO.getSqlMapClientTemplate().insert("SysUserMenuDAO.insertSysUserMenuDO", sysUserMenuDO);
	}

	public Integer updateSysUserMenuDO(SysUserMenuDO sysUserMenuDO){
		SysUserMenuValidate.updateSysUserMenuDO(sysUserMenuDO);
		return baseDAO.getSqlMapClientTemplate().update("SysUserMenuDAO.updateSysUserMenuDO", sysUserMenuDO);
	}

	public Integer updateSysUserMenuDOList(SysUserMenuDO sysUserMenuDO){
		SysUserMenuValidate.updateSysUserMenuDOList(sysUserMenuDO);
		return baseDAO.getSqlMapClientTemplate().update("SysUserMenuDAO.updateSysUserMenuDOList", sysUserMenuDO);
	}
	
	public Integer deleteSysUserMenuDO(Long id){
		SysUserMenuValidate.deleteSysUserMenuDO(id);
		return baseDAO.getSqlMapClientTemplate().delete("SysUserMenuDAO.deleteSysUserMenuDO", id);
	}
	
	public Integer deleteSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery){
		SysUserMenuValidate.deleteSysUserMenuDOList(sysUserMenuQuery);
		return baseDAO.getSqlMapClientTemplate().delete("SysUserMenuDAO.deleteSysUserMenuDOList", sysUserMenuQuery);
	}

}
