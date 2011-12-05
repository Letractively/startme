package com.yehongyu.mansys.dao.ibatis;

import java.util.List;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.query.SysUserQuery;

/**
 * sys_user������
 * @author yingyang
 * @since 2011-11-11
 */
public interface SysUserDAO {

	/**
	 * ��ȡsys_user��¼
	 * @param sysUserQuery
	 * @return Ψһ��¼
	 */
	SysUserDO getSysUserDO(SysUserQuery sysUserQuery);

	/**
	 * ��ȡsys_user��¼�б�
	 * @param sysUserQuery
	 * @return ��¼�б�
	 */
	List<SysUserDO> getSysUserDOList(SysUserQuery sysUserQuery);

	/**
	 * ����ҳ��ȡsys_user��¼�б�
	 * @param sysUserQuery
	 * @return ��¼�б�
	 */
	List<SysUserDO> getSysUserDOListWithPage(SysUserQuery sysUserQuery);

	/**
	 * ����sys_user��¼
	 * @param sysUserDO
	 * @return ����ɹ�������
	 */
	Long insertSysUserDO(SysUserDO sysUserDO);
	
	/**
	 * ��ID����sys_user��¼
	 * @param sysUserDO
	 * @return �ɹ����µ�����������Ϊ1
	 */
	Integer updateSysUserDO(SysUserDO sysUserDO);
	
	/**
	 * ��idList��������sys_user��¼
	 * @param sysUserDO
	 * @return �ɹ����µ�����
	 */
	Integer updateSysUserDOList(SysUserDO sysUserDO);

	/**
	 * ɾ��sys_user��¼
	 * @param id
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysUserDO(Long id) ;

	/**
	 * ����������ɾ��sys_user��¼
	 * @param sysUserQuery
	 * @return �ɹ�ɾ��������
	 */
	Integer deleteSysUserDOList(SysUserQuery sysUserQuery) ;
}
