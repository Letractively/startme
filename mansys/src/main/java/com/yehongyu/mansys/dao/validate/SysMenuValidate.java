package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;

/**
 * sys_menuУ����
 * ���ڡ�ServiceClient����ServiceImpl����DAOImpl���㹲ͬͳһУ�����
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuValidate extends BaseValidate {
	
	//SysMenu�ַ��ֶγ���ֵ
	public final static int BYTELENGTH_OF_MENUCODE = 40;
	public final static int BYTELENGTH_OF_MENUNAME = 40;
	public final static int BYTELENGTH_OF_MENUURL = 255;
	public final static int BYTELENGTH_OF_PARENTSCODE = 40;
	public final static int BYTELENGTH_OF_ROOTCODE = 40;
	public final static int BYTELENGTH_OF_DISPLAYORDER = 10;
	
	/**
	 * ������ѯ������֤ID��Ϊ��
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDO(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{
		if(sysMenuQuery==null||sysMenuQuery.getId()==null || sysMenuQuery.getId()<=0){
			throw new IllegalArgumentException("sysMenuQuery Parameter is not correct,sysMenuQuery:" + sysMenuQuery);
		}
	}
	
	/**
	 * ������ѯ������У��
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDOList(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������ҳ��ѯ������У��
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDOListWithPage(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * ������¼����У�鲻Ϊ�գ�����У��
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysMenuDO(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO) {
			throw new IllegalArgumentException("sysMenuDO Parameter is null!");
		}
		if(sysMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysMenuDO.getMenucode(),sb,"menucode");
		sb = checkNotNull(sysMenuDO.getMenuname(),sb,"menuname");
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysMenuDO Parameter verify not correct,sysMenuDO:"+sb.toString());
		}
		sysMenuDO.setValidate(true);	//���ó�У����ˡ�
	}

	/**
	 * �������޸ļ�¼����Ҫ��֤���󳤶�
	 * @param sysMenuDO
	 * @param sb
	 * @return
	 */
	private static StringBuffer validateSysMenuDOFieldLength(SysMenuDO sysMenuDO,StringBuffer sb) {
		sb = checkLength(sysMenuDO.getMenucode(),BYTELENGTH_OF_MENUCODE,sb,"menucode");
		sb = checkLength(sysMenuDO.getMenuname(),BYTELENGTH_OF_MENUNAME,sb,"menuname");
		sb = checkLength(sysMenuDO.getMenuurl(),BYTELENGTH_OF_MENUURL,sb,"menuurl");
		sb = checkLength(sysMenuDO.getParentscode(),BYTELENGTH_OF_PARENTSCODE,sb,"parentscode");
		sb = checkLength(sysMenuDO.getRootcode(),BYTELENGTH_OF_ROOTCODE,sb,"rootcode");
		return sb;
	}
	
	/**
	 * �޸ļ�¼ʱ��֤ID��Ϊ�ռ��ֶγ���
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysMenuDO(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO || sysMenuDO.getId() == null||sysMenuDO.getId()<1) {
			throw new IllegalArgumentException("updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sysMenuDO);
		}
		if(sysMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sb.toString());
		}
		sysMenuDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * �����޸ļ�¼ʱ��֤IDList��Ϊ�ռ��ֶγ���
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysMenuDOList(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO || sysMenuDO.getIdList() == null||sysMenuDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sysMenuDO);
		}
		if(sysMenuDO.isValidate()) return;	//�Ѿ�У�����
		//У������
		StringBuffer sb = new StringBuffer();
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//У���β
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysMenuDOList Parameter verify not correct,sysMenuDO:" + sb.toString());
		}
		sysMenuDO.setValidate(true);	//���ó�У����ˡ�
	}
	
	/**
	 * ����ɾ����¼ʱ��֤ID��Ϊ��
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysMenuDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysMenuDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * ����ɾ����¼ʱ��֤IDList��Ϊ��
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysMenuDOList(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{
		if (null == sysMenuQuery || sysMenuQuery.getIdList() == null||sysMenuQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysMenuDO Parameter verify not correct,sysMenuQuery:" + sysMenuQuery);
		}
	}
	
}
