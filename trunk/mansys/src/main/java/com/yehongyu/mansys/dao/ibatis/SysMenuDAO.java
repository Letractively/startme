package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;

/**
 * sys_menu������
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysMenuDAO {

	/**
	 * ��ȡsys_menu��¼
	 * @param sysMenuQuery
	 * @return Ψһ��¼
	 */
	SysMenuDO getSysMenuDO(SysMenuQuery sysMenuQuery);

	/**
	 * ��ȡsys_menu��¼�б�
	 * @param sysMenuQuery
	 * @return ��¼�б�
	 */
	List<SysMenuDO> getSysMenuDOList(SysMenuQuery sysMenuQuery);

	/**
	 * ����ҳ��ȡsys_menu��¼�б�
	 * @param sysMenuQuery
	 * @return ��¼�б�
	 */
	List<SysMenuDO> getSysMenuDOListWithPage(SysMenuQuery sysMenuQuery);

	/**
	 * ����sys_menu��¼
	 * @param sysMenuDO
	 * @return ����ɹ�������
	 */
	Long insertSysMenuDO(SysMenuDO sysMenuDO);
	
	/**
	 * ��ID����sys_menu��¼
	 * @param sysMenuDO
	 * @return �ɹ����µ�����������Ϊ1
	 */
	Integer updateSysMenuDO(SysMenuDO sysMenuDO);
	
	/**
	 * ��idList��������sys_menu��¼
	 * @param sysMenuDO
	 * @return �ɹ����µ�����
	 */
	Integer updateSysMenuDOList(SysMenuDO sysMenuDO);

	/**
	 * ɾ��sys_menu��¼
	 * @param id
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysMenuDO(Long id) ;

	/**
	 * ����������ɾ��sys_menu��¼
	 * @param sysMenuQuery
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysMenuDOList(SysMenuQuery sysMenuQuery) ;
}
