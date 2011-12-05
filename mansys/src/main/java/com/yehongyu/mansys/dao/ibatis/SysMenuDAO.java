package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;

/**
 * sys_menu操作类
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysMenuDAO {

	/**
	 * 获取sys_menu记录
	 * @param sysMenuQuery
	 * @return 唯一记录
	 */
	SysMenuDO getSysMenuDO(SysMenuQuery sysMenuQuery);

	/**
	 * 获取sys_menu记录列表
	 * @param sysMenuQuery
	 * @return 记录列表
	 */
	List<SysMenuDO> getSysMenuDOList(SysMenuQuery sysMenuQuery);

	/**
	 * 按分页获取sys_menu记录列表
	 * @param sysMenuQuery
	 * @return 记录列表
	 */
	List<SysMenuDO> getSysMenuDOListWithPage(SysMenuQuery sysMenuQuery);

	/**
	 * 插入sys_menu记录
	 * @param sysMenuDO
	 * @return 插入成功的条数
	 */
	Long insertSysMenuDO(SysMenuDO sysMenuDO);
	
	/**
	 * 按ID更新sys_menu记录
	 * @param sysMenuDO
	 * @return 成功更新的条数，正常为1
	 */
	Integer updateSysMenuDO(SysMenuDO sysMenuDO);
	
	/**
	 * 按idList批量更新sys_menu记录
	 * @param sysMenuDO
	 * @return 成功更新的条数
	 */
	Integer updateSysMenuDOList(SysMenuDO sysMenuDO);

	/**
	 * 删除sys_menu记录
	 * @param id
	 * @return 成功删除的条数
	 */
	Integer deleteSysMenuDO(Long id) ;

	/**
	 * 按条件批量删除sys_menu记录
	 * @param sysMenuQuery
	 * @return 成功删除的条数
	 */
	Integer deleteSysMenuDOList(SysMenuQuery sysMenuQuery) ;
}
