/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao;

import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.permission.PartParam;
import org.yehongyu.websale.bo.manage.permission.UserParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyDaoManager;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.SModule;
import org.yehongyu.websale.db.po.mydb.SRole;
import org.yehongyu.websale.db.po.mydb.SRoleModule;
import org.yehongyu.websale.db.po.mydb.SUser;
import org.yehongyu.websale.db.po.mydb.SUserRole;

/**
 * 【类说明】权限管理DAO接口
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:15:20
 */
public interface PermissionDao extends MyDaoManager {

    /**
     * 根据用户ID返回唯一用户
     * @param userid
     * @return 用户对象
     * @throws MyException
     */
    public SUser getSUserById(long userid) throws MyException;
    
    /**
     * 根据用户名返回唯一用户，用户名为空或查询不存在或多于两条记录，则返回空
     * @param username
     * @return 用户对象
     * @throws MyException
     */
    public SUser getSUserByUsername(String username);
    
    /**
     * 返回所有用户对象
     * @return 所有用户对象
     * @throws MyException
     */
    public List<SUser> getSUsers() throws MyException;
    
    /**
     * 根据父账号返回其直接子账号对象
     * @param admin
     * @throws MyException
     * @return List<SUser>
     */
    public List<SUser> getSUserNext(String admin)throws MyException;
    
    /**
     * 返回某用户下的所有用户对象
     * @param admin 父账号
     * @return 所有用户对象
     * @throws MyException
     */
    public Map<String,SUser> getSUserAll(String admin)throws MyException;
    
    /**
     * 通过JDBC取人员，去除了根据用户名查不到子账号的情况。
     * @param pa
     * @param pageBean
     * @throws MyException
     * @return Page
     */
    public Page getSUserList(UserParam pa,PageBean pageBean)throws MyException;

    /**
     * 根据用户Id获取用户角色权限列表，用户名为空，则返回空
     * @param userid
     * @return 用户分类权限列表
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByUserid(long userid) throws MyException;
    
    /**
     * 根据角色Id获取角色用户权限列表，角色为空，则返回空
     * @param roleid
     * @return 用户角色权限列表
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByRoleid(long roleid) throws MyException;

    /**
     * 根据Id获取唯一的角色对象
     * @param partId
     * @return 角色对象
     * @throws MyException
     */
    public SRole getSRoleById(long roleid) throws MyException;
    
    /**
     * 根据角色名返回唯一角色，角色名为空或查询不存在或多于两条记录，则返回空
     * @param username
     * @return 角色对象
     * @throws MyException
     */
    public SRole getSRoleByRolename(String rolename);
    
    /**
     * 返回角色列表
     * @return 返回角色列表
     * @throws MyException
     */
    public List<SRole> getSRoles() throws MyException;
    
    /**
     * 根据用户名获取用户具有的角色列表
     * @param username 用户名
     * @throws MyException
     * @return List<SRole>
     */
    public List<SRole> getSRolesByUsername(String username)throws MyException;
    
    /**
     * 返回分页的角色对象
     * @param pa 查询的用户Bo对象
     * @param pageBean 分页对象
     * @return 分页的用户对象
     * @throws MyException
     */
    public Page getSRoleList(PartParam pa,PageBean pageBean) throws MyException;
    
	/**
	 * 根据角色Id查询模块和按钮权限，角色为空，则返回空
	 * @param roleid
	 * @return 权限对象列表
	 * @throws MyException
	 */
	public List<SRoleModule> getSRoleModule(long roleid) throws MyException;
    
    /**
     * 根据Id获取唯一的模块对象
     * @param moduleId
     * @return 模块对象
     * @throws MyException
     */
    public SModule getSModuleById(String moduleId) throws MyException;
    
	/**
	 * 获取模块列表
	 * @return 模块列表
	 * @throws MyException
	 */
	public List<SModule> getModules() throws MyException;
	
}
