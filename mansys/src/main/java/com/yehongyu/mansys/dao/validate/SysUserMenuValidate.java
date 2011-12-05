package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

/**
 * sys_user_menu校验类
 * 将在×ServiceClient、×ServiceImpl、×DAOImpl三层共同统一校验机制
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuValidate extends BaseValidate {
	
	//SysUserMenu字符字段长度值
	
	/**
	 * 单个查询条件验证ID不为空
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDO(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{
		if(sysUserMenuQuery==null||sysUserMenuQuery.getId()==null || sysUserMenuQuery.getId()<=0){
			throw new IllegalArgumentException("sysUserMenuQuery Parameter is not correct,sysUserMenuQuery:" + sysUserMenuQuery);
		}
	}
	
	/**
	 * 批量查询条件不校验
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 批量翻页查询条件不校验
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserMenuDOListWithPage(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 新增记录对象校验不为空，长度校验
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysUserMenuDO(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO) {
			throw new IllegalArgumentException("sysUserMenuDO Parameter is null!");
		}
		if(sysUserMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysUserMenuDO.getUserid(),sb,"userid");
		sb = checkNotNull(sysUserMenuDO.getMenuid(),sb,"menuid");
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysUserMenuDO Parameter verify not correct,sysUserMenuDO:"+sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//设置成校验过了。
	}

	/**
	 * 新增和修改记录是需要验证对象长度
	 * @param sysUserMenuDO
	 * @param sb
	 * @return
	 */
	private static StringBuffer validateSysUserMenuDOFieldLength(SysUserMenuDO sysUserMenuDO,StringBuffer sb) {
		return sb;
	}
	
	/**
	 * 修改记录时验证ID不为空及字段长度
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserMenuDO(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO || sysUserMenuDO.getId() == null||sysUserMenuDO.getId()<1) {
			throw new IllegalArgumentException("updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sysUserMenuDO);
		}
		if(sysUserMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 批量修改记录时验证IDList不为空及字段长度
	 * @param sysUserMenuDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserMenuDOList(SysUserMenuDO sysUserMenuDO) throws IllegalArgumentException{
		if (null == sysUserMenuDO || sysUserMenuDO.getIdList() == null||sysUserMenuDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysUserMenuDO Parameter verify not correct,sysUserMenuDO:" + sysUserMenuDO);
		}
		if(sysUserMenuDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserMenuDOFieldLength(sysUserMenuDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysUserMenuDOList Parameter verify not correct,sysUserMenuDO:" + sb.toString());
		}
		sysUserMenuDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 单个删除记录时验证ID不为空
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserMenuDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysUserMenuDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * 批量删除记录时验证IDList不为空
	 * @param sysUserMenuQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserMenuDOList(SysUserMenuQuery sysUserMenuQuery) throws IllegalArgumentException{
		if (null == sysUserMenuQuery || sysUserMenuQuery.getIdList() == null||sysUserMenuQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysUserMenuDO Parameter verify not correct,sysUserMenuQuery:" + sysUserMenuQuery);
		}
	}
	
}
