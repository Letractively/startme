package com.yehongyu.mansys.ao;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysMenuDO;

public interface PermissionAO {
	
	
	/**
	 * 判断用户是否具有菜单权限
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public boolean isAuthenMenu(long userid, long menuid);
	
	/**
	 * 获取所有有效的级联菜单
	 * @return
	 */
	public List<SysMenuDO> getAllMenu();

}
