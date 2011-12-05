package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

/**
 * sys_user_menuУ����
 * ���ڡ�ServiceClient����ServiceImpl����DAOImpl���㹲ͬͳһУ�����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuValidate extends BaseValidate {
	
	//SysUserMenu�ַ��ֶγ���ֵ
	
	/**
	 * ������ѯ������֤ID��Ϊ��
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDO(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{
		if(sysUserMenuQuery==null||sysUserMenuQuery.getId()==null || sysUserMenuQuery.getId()<=0){
			throw new IllegalArgumentException("sysUserMenuQuery Parameter is not correct,sysUserMenuQuery:" + sysUserMenuQuery);
		}
	}
	
	/**
	 * ������ѯ������У��
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������ҳ��ѯ������У��
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDOListWithPage(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������¼����У�鲻Ϊ�գ�����У��
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysUserMenuDO(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO) {
			throw new IllegalArgumentException("sysUserMenuDO Parameter is null!");
		}
		if(sysUserMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysUserMenuDO.getUserid(),sb,"userid");
		sb = checkNotNull(sysUserMenuDO.getMenuid(),sb,"menuid");
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysUserMenuDO Parameter verify not correct,sysUserMenuDO:"+sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//���ó�У����ˡ�
	}

	/**
	 * �������޸ļ�¼����Ҫ��֤���󳤶�
	 * @param sysUserMenuDO
	 * @param sb
	 * @return
	 */
	private static StringBuffer validateSysUserMenuDOFieldLength(SysUserMenuDO sysUserMenuDO,StringBuffer sb) {
		return sb;
	}
	
	/**
	 * �޸ļ�¼ʱ��֤ID��Ϊ�ռ��ֶγ���
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserMenuDO(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO || sysUserMenuDO.getId() == null||sysUserMenuDO.getId()<1) {
			throw new IllegalArgumentException("updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sysUserMenuDO);
		}
		if(sysUserMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * �����޸ļ�¼ʱ��֤IDList��Ϊ�ռ��ֶγ���
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserMenuDOList(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO || sysUserMenuDO.getIdList() == null||sysUserMenuDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sysUserMenuDO);
		}
		if(sysUserMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysUserMenuDOList Parameter verify not correct,sysUserMenuDO:" + sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * ����ɾ����¼ʱ��֤ID��Ϊ��
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserMenuDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysUserMenuDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * ����ɾ����¼ʱ��֤IDList��Ϊ��
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{
		if (null == sysUserMenuQuery || sysUserMenuQuery.getIdList() == null||sysUserMenuQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysUserMenuDO Parameter verify not correct,sysUserMenuQuery:" + sysUserMenuQuery);
		}
	}
	
}
