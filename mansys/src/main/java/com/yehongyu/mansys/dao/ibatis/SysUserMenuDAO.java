package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

/**
 * sys_user_menu������
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysUserMenuDAO {

	/**
	 * ��ȡsys_user_menu��¼
	 * @param sysUserMenuQuery
	 * @return Ψһ��¼
	 */
	SysUserMenuDO getSysUserMenuDO(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * ��ȡsys_user_menu��¼�б�
	 * @param sysUserMenuQuery
	 * @return ��¼�б�
	 */
	List<SysUserMenuDO> getSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * ����ҳ��ȡsys_user_menu��¼�б�
	 * @param sysUserMenuQuery
	 * @return ��¼�б�
	 */
	List<SysUserMenuDO> getSysUserMenuDOListWithPage(SysUserMenuQuery sysUserMenuQuery);

	/**
	 * ����sys_user_menu��¼
	 * @param sysUserMenuDO
	 * @return ����ɹ�������
	 */
	Long insertSysUserMenuDO(SysUserMenuDO sysUserMenuDO);
	
	/**
	 * ��ID����sys_user_menu��¼
	 * @param sysUserMenuDO
	 * @return �ɹ����µ�����������Ϊ1
	 */
	Integer updateSysUserMenuDO(SysUserMenuDO sysUserMenuDO);
	
	/**
	 * ��idList��������sys_user_menu��¼
	 * @param sysUserMenuDO
	 * @return �ɹ����µ�����
	 */
	Integer updateSysUserMenuDOList(SysUserMenuDO sysUserMenuDO);

	/**
	 * ɾ��sys_user_menu��¼
	 * @param id
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysUserMenuDO(Long id) ;

	/**
	 * ����������ɾ��sys_user_menu��¼
	 * @param sysUserMenuQuery
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) ;
}
