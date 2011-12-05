package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.query.SysUserQuery;

/**
 * sys_user操作类
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysUserDAO {

	/**
	 * 获取sys_user记录
	 * @param sysUserQuery
	 * @return 唯一记录
	 */
	SysUserDO getSysUserDO(SysUserQuery sysUserQuery);

	/**
	 * 获取sys_user记录列表
	 * @param sysUserQuery
	 * @return 记录列表
	 */
	List<SysUserDO> getSysUserDOList(SysUserQuery sysUserQuery);

	/**
	 * 按分页获取sys_user记录列表
	 * @param sysUserQuery
	 * @return 记录列表
	 */
	List<SysUserDO> getSysUserDOListWithPage(SysUserQuery sysUserQuery);

	/**
	 * 插入sys_user记录
	 * @param sysUserDO
	 * @return 插入成功的条数
	 */
	Long insertSysUserDO(SysUserDO sysUserDO);
	
	/**
	 * 按ID更新sys_user记录
	 * @param sysUserDO
	 * @return 成功更新的条数，正常为1
	 */
	Integer updateSysUserDO(SysUserDO sysUserDO);
	
	/**
	 * 按idList批量更新sys_user记录
	 * @param sysUserDO
	 * @return 成功更新的条数
	 */
	Integer updateSysUserDOList(SysUserDO sysUserDO);

	/**
	 * 删除sys_user记录
	 * @param id
	 * @return 成功删除的条数
	 */
	Integer deleteSysUserDO(Long id) ;

	/**
	 * 按条件批量删除sys_user记录
	 * @param sysUserQuery
	 * @return 成功删除的条数
	 */
	Integer deleteSysUserDOList(SysUserQuery sysUserQuery) ;
}
