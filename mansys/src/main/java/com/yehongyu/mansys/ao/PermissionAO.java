package com.yehongyu.mansys.ao;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysMenuDO;

public interface PermissionAO {
	
	
	/**
	 * �ж��û��Ƿ���в˵�Ȩ��
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public boolean isAuthenMenu(long userid, long menuid);
	
	/**
	 * ��ȡ������Ч�ļ����˵�
	 * @return
	 */
	public List<SysMenuDO> getAllMenu();

}
