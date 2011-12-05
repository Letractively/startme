package com.yehongyu.mansys.dao.validate;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.query.SysUserQuery;

/**
 * sys_user校验类
 * 将在×ServiceClient、×ServiceImpl、×DAOImpl三层共同统一校验机制
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserValidate extends BaseValidate {
	
	//SysUser字符字段长度值
	public final static int BYTELENGTH_OF_USERNAME = 64;
	public final static int BYTELENGTH_OF_PASSWORD = 100;
	public final static int BYTELENGTH_OF_NAME = 64;
	public final static int BYTELENGTH_OF_MEMO = 200;
	
	/**
	 * 单个查询条件验证ID不为空
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDO(SysUserQuery sysUserQuery) throws IllegalArgumentException{
		if(sysUserQuery==null||sysUserQuery.getId()==null || sysUserQuery.getId()<=0){
			throw new IllegalArgumentException("sysUserQuery Parameter is not correct,sysUserQuery:" + sysUserQuery);
		}
	}
	
	/**
	 * 批量查询条件不校验
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDOList(SysUserQuery sysUserQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 批量翻页查询条件不校验
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void getSysUserDOListWithPage(SysUserQuery sysUserQuery) throws IllegalArgumentException{

	}
	
	/**
	 * 新增记录对象校验不为空，长度校验
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void insertSysUserDO(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO) {
			throw new IllegalArgumentException("sysUserDO Parameter is null!");
		}
		if(sysUserDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = checkNotNull(sysUserDO.getUsername(),sb,"username");
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("insertSysUserDO Parameter verify not correct,sysUserDO:"+sb.toString());
		}
		sysUserDO.setValidate(true);	//设置成校验过了。
	}

	/**
	 * 新增和修改记录是需要验证对象长度
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
	 * 修改记录时验证ID不为空及字段长度
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserDO(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO || sysUserDO.getId() == null||sysUserDO.getId()<1) {
			throw new IllegalArgumentException("updateSysUserDO Parameter verify not correct,sysUserDO:" + sysUserDO);
		}
		if(sysUserDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("updateSysUserDO Parameter verify not correct,sysUserDO:" + sb.toString());
		}
		sysUserDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 批量修改记录时验证IDList不为空及字段长度
	 * @param sysUserDO
	 * @throws IllegalArgumentException
	 */
	public static void updateSysUserDOList(SysUserDO sysUserDO) throws IllegalArgumentException{
		if (null == sysUserDO || sysUserDO.getIdList() == null||sysUserDO.getIdList().isEmpty()) {
			throw new IllegalArgumentException("batch updateSysUserDO Parameter verify not correct,sysUserDO:" + sysUserDO);
		}
		if(sysUserDO.isValidate()) return;	//已经校验过了
		//校验主体
		StringBuffer sb = new StringBuffer();
		sb = validateSysUserDOFieldLength(sysUserDO, sb);
		//校验结尾
		if(!"".equals(sb.toString())){
			throw new IllegalArgumentException("batch updateSysUserDOList Parameter verify not correct,sysUserDO:" + sb.toString());
		}
		sysUserDO.setValidate(true);	//设置成校验过了。
	}
	
	/**
	 * 单个删除记录时验证ID不为空
	 * @param id
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserDO(Long id) throws IllegalArgumentException{
		if (null == id || id < 1) {
			throw new IllegalArgumentException("deleteSysUserDO Parameter verify not correct,id:" + id);
		}
	}
	
	/**
	 * 批量删除记录时验证IDList不为空
	 * @param sysUserQuery
	 * @throws IllegalArgumentException
	 */
	public static void deleteSysUserDOList(SysUserQuery sysUserQuery) throws IllegalArgumentException{
		if (null == sysUserQuery || sysUserQuery.getIdList() == null||sysUserQuery.getIdList().isEmpty()) {
			throw new IllegalArgumentException("deleteSysUserDO Parameter verify not correct,sysUserQuery:" + sysUserQuery);
		}
	}
	
}
