package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

/**
 * sys_user_menu操作类
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysUserMenuDAO {

	/**
	 * 获取sys_user_menu记录
	 * @param sysUserMenuQuery
	 * @return 唯一记录
	 */
	SysUserMenuDO getSysUserMenuDO(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * 获取sys_user_menu记录列表
	 * @param sysUserMenuQuery
	 * @return 记录列表
	 */
	List<SysUserMenuDO> getSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * 按分页获取sys_user_menu记录列表
	 * @param sysUserMenuQuery
	 * @return 记录列表
	 */
	List<SysUserMenuDO> getSysUserMenuDOListWithPage(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * 插入sys_user_menu记录
	 * @param sysUserMenuDO
	 * @return 插入成功的条数
	 */
	Long insertSysUserMenuDO(SysUserMenuDO sysUserMenuDO);
	
	/**
	 * 按ID更新sys_user_menu记录
	 * @param sysUserMenuDO
	 * @return 成功更新的条数，正常为1
	 */
	Integer updateSysUserMenuDO(SysUserMenuDO sysUserMenuDO);
	
	/**
	 * 按idList批量更新sys_user_menu记录
	 * @param sysUserMenuDO
	 * @return 成功更新的条数
	 */
	Integer updateSysUserMenuDOList(SysUserMenuDO sysUserMenuDO);

	/**
	 * 删除sys_user_menu记录
	 * @param id
	 * @return 成功删除的条数
	 */
	Integer deleteSysUserMenuDO(Long id) ;

	/**
	 * 按条件批量删除sys_user_menu记录
	 * @param sysUserMenuQuery
	 * @return 成功删除的条数
	 */
	Integer deleteSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) ;
}
