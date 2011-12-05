package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.query.SysUserQuery;

/**
 * sys_userУ����
 * ���ڡ�ServiceClient����ServiceImpl����DAOImpl���㹲ͬͳһУ�����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserValidate extends BaseValidate {
	
	//SysUser�ַ��ֶγ���ֵ
	public final static int BYTELENGTH_OF_USERNAME = 64;
	public final static int BYTELENGTH_OF_PASSWORD = 100;
	public final static int BYTELENGTH_OF_NAME = 64;
	public final static int BYTELENGTH_OF_MEMO = 200;
	
	/**
	 * ������ѯ������֤ID��Ϊ��
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDO(SysUserQuery sysUserQuery) throws IllegalArgumentException{
		if(sysUserQuery==null||sysUserQuery.getId()==null || sysUserQuery.getId()<=0){
			throw new IllegalArgumentException("sysUserQuery Parameter is not correct,sysUserQuery:" + sysUserQuery);
		}
	}
	
	/**
	 * ������ѯ������У��
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDOList(SysUserQuery sysUserQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������ҳ��ѯ������У��
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDOListWithPage(SysUserQuery sysUserQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������¼����У�鲻Ϊ�գ�����У��
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysUserDO(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO) {
			throw new IllegalArgumentException("sysUserDO Parameter is null!");
		}
		if(sysUserDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysUserDO.getUsername(),sb,"username");
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysUserDO Parameter verify not correct,sysUserDO:"+sb.toString());
		}
		sysUserDO.setValidate(true);	//���ó�У����ˡ�
	}

	/**
	 * �������޸ļ�¼����Ҫ��֤���󳤶�
	 * @param sysUserDO
	 * @param sb
	 * @return
	 */
	private static StringBuffer validateSysUserDOFieldLength(SysUserDO sysUserDO,StringBuffer sb) {
		sb = checkLength(sysUserDO.getUsername(),BYTELENGTH_OF_USERNAME,sb,"username");
		sb = checkLength(sysUserDO.getPassword(),BYTELENGTH_OF_PASSWORD,sb,"password");
		sb = checkLength(sysUserDO.getName(),BYTELENGTH_OF_NAME,sb,"name");
		sb = checkLength(sysUserDO.getMemo(),BYTELENGTH_OF_MEMO,sb,"memo");
		return sb;
	}
	
	/**
	 * �޸ļ�¼ʱ��֤ID��Ϊ�ռ��ֶγ���
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserDO(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO || sysUserDO.getId() == null||sysUserDO.getId()<1) {
			throw new IllegalArgumentException("updateSysUserDO Parameter verify not correct,sysUserDO:" + sysUserDO);
		}
		if(sysUserDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysUserDO Parameter verify not correct,sysUserDO:" + sb.toString());
		}
		sysUserDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * �����޸ļ�¼ʱ��֤IDList��Ϊ�ռ��ֶγ���
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserDOList(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO || sysUserDO.getIdList() == null||sysUserDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysUserDO Parameter verify not correct,sysUserDO:" + sysUserDO);
		}
		if(sysUserDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysUserDOList Parameter verify not correct,sysUserDO:" + sb.toString());
		}
		sysUserDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * ����ɾ����¼ʱ��֤ID��Ϊ��
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysUserDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * ����ɾ����¼ʱ��֤IDList��Ϊ��
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserDOList(SysUserQuery sysUserQuery) throws IllegalArgumentException{
		if (null == sysUserQuery || sysUserQuery.getIdList() == null||sysUserQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysUserDO Parameter verify not correct,sysUserQuery:" + sysUserQuery);
		}
	}
	
}
