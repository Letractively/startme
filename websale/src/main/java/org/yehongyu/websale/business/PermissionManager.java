/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business;

import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.permission.PartParam;
import org.yehongyu.websale.bo.manage.permission.UserParam;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.SRole;
import org.yehongyu.websale.db.po.mydb.SUser;
import org.yehongyu.websale.vo.manage.permission.MenuItemValue;
import org.yehongyu.websale.vo.manage.permission.ModuleValue;
import org.yehongyu.websale.vo.manage.permission.PartValue;
import org.yehongyu.websale.vo.manage.permission.UserValue;



/**
 * 【类说明】权限管理Manager接口
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:09:45
 */
public interface PermissionManager {

	/**
	 * 【函数功能】用户登录,如果登录成功,返回一个UserSession对象
	 * @param username 前台提交的用户名
	 * @param password 用户密码
	 * @return 成功返回一个会话对象, 失败则返回null
	 * @exception 在登录失败的时候抛出异常,异常里有详细的错误信息
	 */
	public abstract UserSession loginUser(String username, String password)
			throws MyException;

	/**
	 * 【函数功能】根据级别和父菜单取子菜单
	 * @param session
	 * @param level
	 * @param parentModid
	 * @throws MyException
	 * @return Map
	 */
	public abstract List<MenuItemValue> getMenuByLevel(UserSession session,
			int level, String parentModid) throws MyException;

	/**
	 * 【函数功能】返回当前登录用户是否对moduleId指向的模块具有操作权限
	 * @param moduleId 模块编号,如果模块是通用模块,moduleId=""
	 * @param session 登录会话
	 * @return 如果有该模块的操作权限,返回true,否则false
	 */
	public abstract boolean isModuleAuthend(String moduleId, UserSession session);

	/**
	 * 【函数功能】返回当前登录用户是否对某个按钮有操作权限 传入的actionId是int型的按钮编号,从1开始编号,1表示第一个按钮
	 * @param moduleId 模块编号
	 * @param actionId 按钮编号 如果传入的不是数字,直接就返回false
	 * @param session 用户会话
	 * @return true or false
	 */
	public abstract boolean isButtonAuthend(String moduleId, String actionId,
			UserSession session);

	/**
	 * 【函数功能】根据moduleId获取模块的所有级别连接名称
	 * @param moduleId
	 * @throws MyException
	 * @return String
	 */
	public abstract String getModuleLevelName(String moduleId)
			throws MyException;

	/**
	 * 【函数功能】修改用户密码为新密码
	 * @param userName 要修改的用户名
	 * @param newPasswd 新密码
	 * @param usrsession 当前操作会话
	 * @return true or false
	 */
	public abstract String changeUserPasswd(String userName, String newPasswd,
			UserSession usrsession) throws MyException;

	/**
	 * 【函数功能】根据用户名返回用户持久类对象
	 * @param userName 用户名
	 * @return 用户持久类对象,如果没有返回null
	 */
	public abstract SUser getSUserByUsername(String userName)
			throws MyException;

	/**
	 * 【函数功能】根据用户Id返回用户持久类对象
	 * @param userName 用户名
	 * @return 用户持久类对象,如果没有返回null
	 */
	public abstract SUser getSUserById(long userid) throws MyException;

	/**
	 * 【函数功能】返回所有用户对象,只能查询自己账号下的子账号。否则返回空
	 * @param pa 查询参数
	 * @return 对象列表
	 */
	public abstract Page getUserList(UserParam pa, UserSession us,
			PageBean pageBean) throws MyException;

	/**
	 * 【函数功能】返回一个角色
	 * @param roleid 角色id
	 * @return SRole持久类对象
	 */
	public abstract SRole getSRole(long roleid) throws MyException;

	/**
	 * 【函数功能】返回所有角色
	 * @return 角色对象列表
	 */
	public abstract List<SRole> getSRoleList() throws MyException;

	/**
	 * 【函数功能】返回分页的角色对象
	 * @param pa 查询的用户Bo对象
	 * @param pageBean 分页对象
	 * @return 分页的用户对象
	 */
	public abstract Page getSRoleList(PartParam pa, PageBean pageBean)
			throws MyException;

	/**
	 * 【函数功能】增加一个用户
	 * @param user 要增加的用户对象
	 * @param usrsession 登录用户对象
	 * @return String 返回的信息
	 */
	public abstract String AddUser(UserValue user) throws MyException;

	/**
	 * 【函数功能】更新一个用户的信息 要先把用户的权限清空，重新插入
	 * @param user 用户对象
	 * @return String 返回的信息
	 */
	public abstract String UpdateUser(UserValue user) throws MyException;

	/**
	 * 【函数功能】批量删除用户,并删除相关的权限数据。执行该方法会物理删除数据库
	 * @param userids 用户的id
	 * @return String 返回的信息
	 */
	public abstract String RemoveUser(long[] userids) throws MyException;

	/**
	 * 【函数功能】增加一个角色
	 * @param part 角色对象
	 * @param checkedmodules 用户选中的模块
	 * @return 返回信息
	 */
	public abstract String AddPart(PartValue part, Map<String,String> checkedmodules)throws MyException;
    

	/**
	 * 【函数功能】更新保存一个用户角色的信息 保存用户的时候,要先把用户的权限清空,重新插入新的权限 用户选中的模块列表, 以及勾选的这个模块的操作按钮
	 * @param part 角色持久类对象
	 * @param checkedmodules
	 *            选中的模块 元素为一个map值对象 key ===>modid; //模块编号 value ===> actionids
	 *            //这个模块里用户勾选的按钮
	 * @return true 更新成功
	 */
	public abstract String UpdatePart(PartValue part, Map<String,String> checkedmodules)throws MyException;

	/**
	 * 【函数功能】批量删除角色
	 * @param partids 要删除的角色数组
	 * @return String 返回的信息
	 */
	public abstract String RemovePart(long[] partids)throws MyException;

	/**
	 * 【函数功能】根据类型，返回父账号拥有的角色列表,本用户已有的角色,checked属性为true 元素为PartValue
	 * @param userid 本账号用户Id,如果为0则返回父账号的所有未checked角色。
	 * @param admin 父账号用户名
	 * @return List 元素为PartValue 如果用户已有该角色,checked=true
	 */
	public abstract List<PartValue> getUserPartWithChecked(long userid,
			String admin) throws MyException;

	/**
	 * 【函数功能】转移账号，将源账号直接子账号的父账号属性改为目标账号
	 * @param source
	 * @param dest
	 * @throws MyException
	 * @return boolean
	 */
	public abstract boolean transAccount(String source, String dest)
			throws MyException;

	/**
	 * 【函数功能】根据源账号取所有可转移的账号（不为源账号的子账号，且权限要大于源账号）
	 * @param sourceAccount
	 * @throws MyException
	 * @return List<SUser>
	 */
	public abstract List<SUser> getSUserListForTransfer(String sourceAccount)
			throws MyException;

	/**
	 * 【函数功能】根据源账号查询指定目标账号是否存在且可转移(不为源账号的子账号且权限大于源账号)，返回目标账号的对象，否则返回null
	 * @param sourceAccount
	 * @param destAccount
	 * @throws MyException
	 * @return SUser
	 */
	public abstract SUser getSUserListForTransfer(String sourceAccount,
			String destAccount) throws MyException;

	/**
	 * 【函数功能】返回一个角色拥有的模块,这个方法会返回所有模块,有权限的模块checked属性为true
	 * 如果操作按钮有权限,checked属性为true,否则为false
	 * @param roleid 角色编号
	 * @param withchecked 是否返回checked的属性,true 返回带有checked值的模块列表 false 返回所有模块
	 * @return List里面的元素为ModuleValue
	 */
	public abstract List<ModuleValue> getModules(long roleid,
			boolean withchecked) throws MyException;

	/**
	 * 【函数功能】刷新缓存数据，包括模块、角色、模块列表，将他们加载都置为否，下次访问时将重新取数据库。
	 * @throws MyException
	 * @return boolean
	 */
	public abstract boolean refreshCacheData() throws MyException;
}
