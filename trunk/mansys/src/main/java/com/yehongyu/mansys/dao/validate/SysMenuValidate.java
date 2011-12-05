package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;

/**
 * sys_menu校验类
 * 将在×ServiceClient、×ServiceImpl、×DAOImpl三层共同统一校验机制
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuValidate extends BaseValidate {
	
	//SysMenu字符字段长度值
	public final static int BYTELENGTH_OF_MENUCODE = 40;
	public final static int BYTELENGTH_OF_MENUNAME = 40;
	public final static int BYTELENGTH_OF_MENUURL = 255;
	public final static int BYTELENGTH_OF_PARENTSCODE = 40;
	public final static int BYTELENGTH_OF_ROOTCODE = 40;
	public final static int BYTELENGTH_OF_DISPLAYORDER = 10;
	
	/**
	 * 单个查询条件验证ID不为空
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDO(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{
		if(sysMenuQuery==null||sysMenuQuery.getId()==null || sysMenuQuery.getId()<=0){
			throw new IllegalArgumentException("sysMenuQuery Parameter is not correct,sysMenuQuery:" + sysMenuQuery);
		}
	}
	
	/**
	 * 批量查询条件不校验
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDOList(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 批量翻页查询条件不校验
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysMenuDOListWithPage(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 新增记录对象校验不为空，长度校验
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysMenuDO(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO) {
			throw new IllegalArgumentException("sysMenuDO Parameter is null!");
		}
		if(sysMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysMenuDO.getMenucode(),sb,"menucode");
		sb = checkNotNull(sysMenuDO.getMenuname(),sb,"menuname");
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysMenuDO Parameter verify not correct,sysMenuDO:"+sb.toString());
		}
		sysMenuDO.setValidate(true);	//设置成校验过了。
	}

	/**
	 * 新增和修改记录是需要验证对象长度
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
	 * 修改记录时验证ID不为空及字段长度
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysMenuDO(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO || sysMenuDO.getId() == null||sysMenuDO.getId()<1) {
			throw new IllegalArgumentException("updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sysMenuDO);
		}
		if(sysMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sb.toString());
		}
		sysMenuDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 批量修改记录时验证IDList不为空及字段长度
	 * @param sysMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysMenuDOList(SysMenuDO sysMenuDO) throws IllegalArgumentException{
		if (null == sysMenuDO || sysMenuDO.getIdList() == null||sysMenuDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysMenuDO Parameter verify not correct,sysMenuDO:" + sysMenuDO);
		}
		if(sysMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysMenuDOFieldLength(sysMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysMenuDOList Parameter verify not correct,sysMenuDO:" + sb.toString());
		}
		sysMenuDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 单个删除记录时验证ID不为空
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysMenuDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysMenuDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * 批量删除记录时验证IDList不为空
	 * @param sysMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysMenuDOList(SysMenuQuery sysMenuQuery) throws IllegalArgumentException{
		if (null == sysMenuQuery || sysMenuQuery.getIdList() == null||sysMenuQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysMenuDO Parameter verify not correct,sysMenuQuery:" + sysMenuQuery);
		}
	}
	
}
