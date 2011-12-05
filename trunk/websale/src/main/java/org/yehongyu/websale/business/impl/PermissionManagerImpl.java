/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.permission.PartParam;
import org.yehongyu.websale.bo.manage.permission.UserParam;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.exception.Msg;
import org.yehongyu.websale.common.exception.MsgCode;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.common.util.Convert;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.common.util.MyMD5;
import org.yehongyu.websale.dao.PermissionDao;
import org.yehongyu.websale.db.MyBaseBusiness;
import org.yehongyu.websale.db.MyDaoFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.SModule;
import org.yehongyu.websale.db.po.mydb.SModuleAction;
import org.yehongyu.websale.db.po.mydb.SRole;
import org.yehongyu.websale.db.po.mydb.SRoleModule;
import org.yehongyu.websale.db.po.mydb.SUser;
import org.yehongyu.websale.db.po.mydb.SUserRole;
import org.yehongyu.websale.vo.manage.permission.MenuItemValue;
import org.yehongyu.websale.vo.manage.permission.ModuleActionValue;
import org.yehongyu.websale.vo.manage.permission.ModuleValue;
import org.yehongyu.websale.vo.manage.permission.PartValue;
import org.yehongyu.websale.vo.manage.permission.UserValue;

/**
 * 【类说明】权限管理Manager实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:06:54
 */
public class PermissionManagerImpl extends MyBaseBusiness implements
        PermissionManager {
    /** 加载类时声明一个私有Dao对象，只在本类可以用 */
    private final static PermissionDao permissionDao;
    /** 初始化Dao对象，从Dao工厂中取出，有可能抛出异常 */
    static {
        try {
            permissionDao = (PermissionDao) MyDaoFactory
                    .getDaoManager(AppConstants.PermissionDao);
        } catch (MyException e) {
            throw new IllegalArgumentException(Msg.get(MsgCode.MMT_MA_COMMON_00005), e);
        }
    }

    /**
     * 私有构造方法，防止外部创建本类实例，只能工厂创建本类实例
     */
    private PermissionManagerImpl() {
    }

    /** 权限缓存对象 */
    private static JCS permissionCache;

    /** 权限模块只在登录时加载一次，之后从缓存对象中取 */
    private static boolean modloaded = false;

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#loginUser(java.lang.String, java.lang.String)
	 */
    public UserSession loginUser(String username, String password)
            throws MyException {
        //验证登录用户名密码是否存在
        if(username==null||username.equals("")||password==null||password.equals("")) return null;
        SUser login = permissionDao.getSUserByUsername(username);
        if (login == null) return null;// 用户不存在
        if (!MyMD5.MD5Encode(password).equals(login.getPassword())) return null;// 密码不正确
        
        //登录通过，记录用户Session
        UserSession userSession = new UserSession();
        userSession.setUserid(login.getId());
        userSession.setUserName(login.getUsername());
        userSession.setPassword(login.getPassword());
        userSession.setName(login.getName());
        userSession.setPhone(login.getPhone());
        userSession.setAdmin(login.getAdmin());
        userSession.setDepartment(login.getDepartment());
        userSession.setEmail(login.getEmail());

        //设置登录用户的角色
        List<SUserRole> SUserRoles = permissionDao.getSUserRoleByUserid(login.getId());// 用户有的角色
        StringBuffer sb = new StringBuffer(100);
        if (SUserRoles != null && SUserRoles.size() > 0) {
            for (Object SUserRole:SUserRoles) {
                SUserRole mpl = (SUserRole) SUserRole;
                String ptid = Convert.getString(mpl.getRoleid());
                sb.append(ptid).append(AppConstants.SPLIT_DEFAULT);
            }
        }
        //如果是系统定义超级用户拥有所有的角色
        if(login.getUsername().equals(AppConstants.DEF_SYSADMIN)){
            List<SRole> allpart = permissionDao.getSRoles();
            sb = new StringBuffer(100);
            for (Iterator<SRole> it = allpart.iterator(); it.hasNext();) {
                SRole p = it.next();
                sb.append(p.getId()).append(AppConstants.SPLIT_DEFAULT);
            }
        }
        userSession.setSroles(sb.toString());

        return userSession;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getMenuByLevel(org.yehongyu.websale.common.util.UserSession, int, java.lang.String)
	 */
    public List<MenuItemValue> getMenuByLevel(UserSession session,int level,String parentModid ) throws MyException {
    	List<MenuItemValue> menu = new ArrayList<MenuItemValue>();
        if (session == null || session.getSroles() == null || session.getSroles().equals(""))
            return menu;
        String[] sroles = session.getSroles().split(AppConstants.SPLIT_DEFAULT);
        //查询出所有模块
        List<SModule> moduleList = getCacheModuleList();//permissionDao.getModules();
        for(SModule mod:moduleList){
            //对每个模块做判断，看此用户拥有的角色是否有此模块的访问权限
            for (int i = 0; i < sroles.length; i++) {
                Map<String,String> map = this.getCachePermission(Long.valueOf(sroles[i]));
                if (map != null && map.containsKey(mod.getId())) {
                    if (mod.getId().trim().length() / 3 == level
                            && mod.getId().trim().indexOf(parentModid)==0) {
                        String modname = mod.getModulename();
                        String url = mod.getUrl()!=null?mod.getUrl():"#";
                        // 增加模块名字
                        MenuItemValue menuitem = new MenuItemValue();
                        menuitem.setText(modname);
                        menuitem.setUrl(url);
                        menuitem.setId(mod.getId());
                        
                        menu.add(menuitem);
                        if(level<3){
                        	int temp = level+1;
                            menuitem.setSubMenu(this.getMenuByLevel(session,temp,menuitem.getId()));
                        }
                        break;  //本菜单已经加上，跳出角色循环，进行下一个模块判断
                    }
                }
            }
        }
        return menu;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#isModuleAuthend(java.lang.String, org.yehongyu.websale.common.util.UserSession)
	 */
    public boolean isModuleAuthend(String moduleId, UserSession session) {
        if (AppConstants.MOD_COMMON.equals(moduleId))
            return true;
        String parts = session.getSroles();
        if (parts == null)
            return false;
        String[] sroles = parts.split(AppConstants.SPLIT_DEFAULT);

        for (int i = 0; i < sroles.length; i++) {
            // 只要一个角色有权限,即有权限
            if (isModuleAuthend(moduleId, Long.valueOf(sroles[i]))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 返回某个角色对某个模块是否有操作权限 直接从缓存里取,如果有权限就有权限,没有就是没有
     * @param moduleId
     * @param roleid
     * @return true or false
     */
    private boolean isModuleAuthend(String moduleId, long roleid) {
        try {
            Map<String,String> m = this.getCachePermission(roleid);
            return m.containsKey(moduleId);
        } catch (MyException e) {
            return false;
        }
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#isButtonAuthend(java.lang.String, java.lang.String, org.yehongyu.websale.common.util.UserSession)
	 */
    public boolean isButtonAuthend(String moduleId, String actionId,
            UserSession session) {

        if (session == null || actionId == null || moduleId == null)
            return false;
        
        int id = 0;
        try {
            id = Integer.parseInt(actionId);
        } catch (Exception e) {
            return false;
        }
        String parts = session.getSroles();
        if (parts == null)
            return false;
        String[] sroles = parts.split(AppConstants.SPLIT_DEFAULT);

        boolean retValue = false;

        for (int i = 0; i < sroles.length; i++) {
            String ptid = sroles[i];
            try {
                Map<String,String> map = getCachePermission(Long.valueOf(ptid));
                if (map != null) {
                    String actionids = map.get(moduleId);
                    if (actionids != null) {
                        char[] array = actionids.toCharArray();

                        if (array.length >= id) {
                            if (array[id - 1] == '1') { // 表示有这个按钮的权限
                                retValue = true;
                                break; // 有权限直接跳出,否则继续判断
                            }
                        }
                    }
                }
            } catch (MyException e) {
                log.error(e);
            }
        } // 只要有一个角色有该模块,该按钮权限,退出循环,返回true

        return retValue;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getModuleLevelName(java.lang.String)
	 */
    public String getModuleLevelName(String moduleId) throws MyException {
        String mlName = "";
        int i = 1;
        while (moduleId.length() >= (3 * i)) {
            SModule mod = (SModule) this.getCacheModule(moduleId
                    .substring(0, 3 * i));
            if(mod==null){
                mlName += AppConstants.SPLIT_NAV + "此模块未定义";
                break;
            }
            mlName += AppConstants.SPLIT_NAV + mod.getModulename();
            i++;
        }
        return mlName;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#changeUserPasswd(java.lang.String, java.lang.String, org.yehongyu.websale.common.util.UserSession)
	 */
    public String changeUserPasswd(String oldpasswd, String newpasswd,
            UserSession usrsession) throws MyException {
        SUser login = permissionDao.getSUserById(usrsession.getUserid());
        if(!login.getPassword().equals(MyMD5.MD5Encode(oldpasswd))){
        	return "输入的旧密码不正确！";
        }
        login.setPassword(MyMD5.MD5Encode(newpasswd));
        permissionDao.update(login);
        return AppConstants.MANAGER_BACK_SUCCESS;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSUserByUsername(java.lang.String)
	 */
    public SUser getSUserByUsername(String userName) throws MyException {
        return permissionDao.getSUserByUsername(userName);
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSUserById(long)
	 */
    public SUser getSUserById(long userid) throws MyException {
        return permissionDao.getSUserById(userid);
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getUserList(org.yehongyu.websale.bo.manage.permission.UserParam, org.yehongyu.websale.common.util.UserSession, org.yehongyu.websale.db.bean.PageBean)
	 */
    public Page getUserList(UserParam pa, UserSession us,PageBean pageBean) throws MyException {
    	return permissionDao.getSUserList(pa, pageBean);
//        //设置父账号
//        String admin = pa.getAdmin();
//        if(admin==null||admin.equals("")){
//            admin = us.getUserName();
//        }
//        pa.setAdmin(admin);
//        //父账号必须是登录用户的子孙账号，否则不让查询
//        Map<String,SUser> users = permissionDao.getSUserAll(us.getUserName());
//        if(us.getUserName().equals(admin)||users.containsKey(admin)){
//            return permissionDao.getSUserList(pa, pageBean);
//        }else{
//            return new Page(null,pageBean);
//        }
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSRole(long)
	 */
    public SRole getSRole(long roleid) throws MyException {
        return this.getCacheSRole(roleid);
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSRoleList()
	 */
    public List<SRole> getSRoleList() throws MyException {
        return permissionDao.getSRoles();
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSRoleList(org.yehongyu.websale.bo.manage.permission.PartParam, org.yehongyu.websale.db.bean.PageBean)
	 */
    public Page getSRoleList(PartParam pa, PageBean pageBean)
            throws MyException {
        return permissionDao.getSRoleList(pa, pageBean);
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#AddUser(org.yehongyu.websale.vo.manage.permission.UserValue)
	 */
    public String AddUser(UserValue user) throws MyException {
        //验证参数的合法性
        if (user == null) return "传入的用户信息为空";
        if (user.getUsername() == null||user.getUsername().equals("")) return "传入的用户名为空";
        Object o = permissionDao.getSUserByUsername(user.getUsername());
        if (o != null) return "已经存在此用户名:"+user.getUsername();
        
        //设置用户角色
        List<PartValue> partids = user.getPart();
        //写用户表记录
        SUser login = new SUser();
        login.setUsername(user.getUsername());
        login.setPassword(MyMD5.MD5Encode(user.getPassword()));
        login.setName(user.getName());
        login.setDepartment(user.getDepname());
        login.setPhone(user.getPhone());
        login.setEmail(user.getEmail());
        login.setMemo(user.getMemo());
        login.setAdmin(user.getAdmin());
        permissionDao.save(login);
        
        //写用户角色表记录
        if (partids != null) {
            for (Iterator<PartValue> it = partids.iterator(); it.hasNext();) {
                PartValue pv = (PartValue) it.next();
                SUserRole mp = new SUserRole();
                mp.setRoleid(pv.getRoleid());
                mp.setUserid(login.getId());
                permissionDao.save(mp);
            }
        }
        return AppConstants.MANAGER_BACK_SUCCESS;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#UpdateUser(org.yehongyu.websale.vo.manage.permission.UserValue)
	 */
    public String UpdateUser(UserValue user) throws MyException {
        //验证参数的合法性
        if (user == null||user.getUserid()==0) return "传入的用户信息为空";
        if (user.getUsername() == null||user.equals("")) return "传入的用户名为空";
        SUser o = permissionDao.getSUserByUsername(user.getUsername());
        if (o != null&&o.getId()!=user.getUserid()) return "已经存在此用户名:"+user.getUsername();
        
        List<SUserRole> k = permissionDao.getSUserRoleByUserid(user.getUserid());
        for (int i = 0; i < k.size(); i++) {
            permissionDao.delete((SUserRole) k.get(i));
        }
        
        //设置用户角色
        List<PartValue> partids = user.getPart();
        
        //修改用户表
        SUser login = permissionDao.getSUserById(user.getUserid());
        if(!login.getUsername().equals(user.getUsername())){    //如果用户名修改，则更新它子账号的父账号信息
            List<SUser> nlogin = permissionDao.getSUserNext(login.getUsername());
            for(SUser ml:nlogin){
                ml.setAdmin(user.getUsername());
                permissionDao.update(ml);
            }
            //同时设置新的用户名
            login.setUsername(user.getUsername());
        }
        login.setName(user.getName());
        login.setDepartment(user.getDepname());
        login.setPhone(user.getPhone());
        login.setEmail(user.getEmail());
        login.setMemo(user.getMemo());
        login.setAdmin(user.getAdmin());
        // 密码特殊处理,如果前台提交的密码和后台的密码不对应,说明用户修改了密码
        String bpasswd = login.getPassword();
        String fpasswd = user.getPassword();
        if (!fpasswd.equals(bpasswd)) {
            login.setPassword(MyMD5.MD5Encode(user.getPassword()));
        }
        permissionDao.update(login);

        //查询子账号，设置子账号的角色（因继承关系，父账号中没有的角色,子账号也不应该存在）
        //因为Mysql不支持子孙查询,所以注释掉
        /*List<Long> adminparts = new ArrayList<Long>();
        for(PartValue pv:partids){  //设置本账号的角色String列表
            adminparts.add(pv.getRoleid());
        }
        Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
        for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
            String key = it.next();
            SUser cldlogin = map.get(key);
            //删除父账号中不存在的角色
            List<SUserRole> mpl = permissionDao.getSUserRoleByUserid(cldlogin.getId());
            for(Object obj:mpl){
                SUserRole mp = (SUserRole)obj;
                if(!adminparts.contains(mp.getRoleid())){
                    permissionDao.delete(mp);
                }
            }
        }*/
        
        //写用户角色表
        if (partids != null) {
            for (Iterator<PartValue> it = partids.iterator(); it.hasNext();) {
                PartValue pv = (PartValue) it.next();
                SUserRole mp = new SUserRole();
                mp.setRoleid(pv.getRoleid());
                mp.setUserid(login.getId());
                permissionDao.save(mp);
            }
        }
        return AppConstants.MANAGER_BACK_SUCCESS;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#RemoveUser(long[])
	 */
    public String RemoveUser(long[] userids) throws MyException {
        //验证参数合法性
        if(userids==null||userids.length==0) return "传入的用户ID为空";
        
        //循环删除每个用户
        StringBuffer info = new StringBuffer();   //删除信息
        for (int m = 0; m < userids.length; m++) {
            SUser login = permissionDao.getSUserById(userids[m]);
            if(login==null) {
                info.append("删除的用户Id[").append(userids[m]).append("]不存在!\\r\\n");
                continue;   //如果删除的用户不存在，则下一个
            }else{
//                Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
//                if(!map.entrySet().isEmpty()){
//                    info.append("删除的用户Id[").append(userids[m]).append("]下有子账号，不能删除！\\r\\n");
//                    continue;   //如果删除的用户下存在子账号，则下一个
//                }
                List<SUser> l = permissionDao.getSUserNext(login.getUsername());
                if(l!=null&&l.size()>0){
                  info.append("删除的用户Id[").append(userids[m]).append("]下有子账号，不能删除！\\r\\n");
                  continue;   //如果删除的用户下存在子账号，则下一个
              }
            }
            List<SUserRole> k = permissionDao.getSUserRoleByUserid(userids[m]);
            for (int i = 0; i < k.size(); i++) {
                permissionDao.delete((SUserRole) k.get(i));
            }
            permissionDao.delete(login);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#AddPart(org.yehongyu.websale.db.po.mydb.SRole, java.util.Map, org.yehongyu.websale.common.util.UserSession)
	 */
    public String AddPart(PartValue part, Map<String,String> checkedmodules)throws MyException {
        //验证参数的合法性
        if (part == null) return "传入的角色信息为空";
        if (part.getRolename() == null||part.getRolename().trim().equals("")) return "传入的角色名称为空";
        Object o = permissionDao.getSRoleByRolename(part.getRolename());
        if (o != null) return "已经存在此角色名:"+part.getRolename();
        
        SRole role = new SRole();
        role.setRolename(part.getRolename());
        role.setMemo(part.getMemo());
        role.setType(0);	//普通角色

        permissionDao.save(role);
        if (checkedmodules != null) {
            for (Iterator<String> it = checkedmodules.keySet().iterator(); it.hasNext();) {
                String modid = (String) it.next();
                String actionids = (String) checkedmodules.get(modid);

                SRoleModule mp = new SRoleModule();
                mp.setRoleid(role.getId());
                mp.setModuleid(modid);
                mp.setActionids(actionids);
                permissionDao.save(mp);
            }
        }
        return AppConstants.MANAGER_BACK_SUCCESS;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#UpdatePart(org.yehongyu.websale.db.po.mydb.SRole, java.util.Map, org.yehongyu.websale.common.util.UserSession)
	 */
    public String UpdatePart(PartValue part, Map<String,String> checkedmodules)throws MyException {
    	//验证参数的合法性
        if (part == null) return "传入的角色信息为空";
        if (part.getRolename() == null||part.getRolename().trim().equals("")) return "传入的角色名称为空";
        Object o = permissionDao.getSRoleByRolename(part.getRolename());
        if (o != null&&((SRole)o).getId()!=part.getRoleid()) return "已经存在此角色名:"+part.getRolename();
        
        SRole role = permissionDao.getSRoleById(part.getRoleid());
        if(role.getType()!=1){
        	role.setRolename(part.getRolename());
        }
        role.setMemo(part.getMemo());
        
        List<SRoleModule> list = permissionDao.getSRoleModule(part.getRoleid());
        for(Object obj : list){
            permissionDao.delete((SRoleModule)obj);
        }
        if (checkedmodules != null) {
            for (Iterator<String> it = checkedmodules.keySet().iterator(); it.hasNext();) {
                String modid = (String) it.next();
                String actionids = (String) checkedmodules.get(modid);

                SRoleModule mp = new SRoleModule();
                mp.setRoleid(part.getRoleid());
                mp.setModuleid(modid);
                mp.setActionids(actionids);
                permissionDao.save(mp);
            }
        }
        permissionDao.update(role);
        // 更新一下缓存
        this.refreshCacheData();
        this.reloadCachePermission(role.getId());

        return AppConstants.MANAGER_BACK_SUCCESS;
    }

	/**
	 * 批量删除角色
	 * @param partids 要删除的角色数组
	 * @return String 返回的信息
	 */
	public String RemovePart(long[] partids)throws MyException{
    	//验证参数合法性
        if(partids==null||partids.length==0) return "传入的角色ID为空";

        StringBuffer info = new StringBuffer();
        for (int i = 0; i < partids.length; i++) {
            List<SUserRole> sUserRole = permissionDao.getSUserRoleByRoleid(partids[i]);
            if (sUserRole != null && sUserRole.size() != 0) {
            	info.append("删除的角色Id[").append(partids[i]).append("]已被关联，不能删除！\\r\\n");
                continue;   //如果删除的用户下存在子账号，则下一个
            }
            // 先删除这个角色下的所有菜单角色关联
            List<SRoleModule> list = permissionDao.getSRoleModule(partids[i]);
            for(SRoleModule obj : list){
                permissionDao.delete(obj);
            }
            SRole sRole = permissionDao.getSRoleById(partids[i]);
            permissionDao.delete(sRole);
            removeCachePermission(sRole.getId());
        }
        if(!"".equals(info.toString())) return info.toString();
        return AppConstants.MANAGER_BACK_SUCCESS;
    }
 
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getUserPartWithChecked(long, java.lang.String)
	 */
    public List<PartValue> getUserPartWithChecked(long userid,String admin)
            throws MyException {
        //验证参数合法性，传入的userid为0，或存在userid的登录账号，传入的admin不为null和"",且必须存在admin的登录账号
        if(admin==null||admin.equals("")) return null;
        SUser upuser = permissionDao.getSUserByUsername(admin);
        if(upuser==null) return null;
        if(userid!=0){
            SUser user = permissionDao.getSUserById(userid);
            if(user==null) return null;
        }
        List<PartValue> re = new ArrayList<PartValue>();
        // 是否要判断已勾选的角色,check=true表示要判断
        boolean check = true;
        if (userid == 0)
            check = false;
        List<Long> l = null;
        if (check) {
            //查询本账号拥有的角色Id
            List<SUserRole> mpl = permissionDao.getSUserRoleByUserid(userid);
            if(mpl!=null&&mpl.size()>0){
                l = new ArrayList<Long>();
                for(SUserRole mp:mpl){
                    l.add(mp.getRoleid());
                }
            }
        }
        // 所具有的角色
        List<SRole> allpart;
        if(admin.equals(AppConstants.DEF_SYSADMIN)){    //如果是父账号系统管理员拥有所有的角色
            allpart = permissionDao.getSRoles();
        }else{  //如果父账号是普通账户，只拥有父账号所具有的角色
            allpart = permissionDao.getSRolesByUsername(admin);
        }
        for (Iterator<SRole> it = allpart.iterator(); it.hasNext();) {
            SRole p = (SRole) it.next();
            PartValue pv = new PartValue();
            pv.setRolename(p.getRolename());
            pv.setRoleid(p.getId());
            long id = p.getId();
            if (check && l != null) {
                if (l.contains(id)) {
                    pv.setChecked(true);
                } else {
                    pv.setChecked(false);
                }
            }
            re.add(pv);
        }
        return re;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#transAccount(java.lang.String, java.lang.String)
	 */
    public boolean transAccount(String source,String dest)throws MyException{
        //查询源账号和目标账号是否存在，为空返回false
        SUser sourceLogin = permissionDao.getSUserByUsername(source);
        if(sourceLogin==null) return false;
        SUser destLogin = permissionDao.getSUserByUsername(dest);
        if(destLogin==null) return false;
        //查询目标账号权限是否大于源账号,小于返回false;
        if(!this.comparePermission(destLogin,sourceLogin)) return false;
        //开始转移账号，将直接子账号的父账号属性改为目标账号。
        List<SUser> nlogin = permissionDao.getSUserNext(source);
        for(SUser ml:nlogin){
            ml.setAdmin(dest);
            permissionDao.update(ml);
        }
        return true;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSUserListForTransfer(java.lang.String)
	 */
    public List<SUser> getSUserListForTransfer(String sourceAccount) throws MyException{
        //验证参数
        if(sourceAccount==null||"".equals(sourceAccount)) return null;
        //查询源账号对象
        SUser sourceLogin = permissionDao.getSUserByUsername(sourceAccount);
        if(sourceLogin==null) return null;
        //查询源账号的子账号对象
        Map<String,SUser> map = permissionDao.getSUserAll(sourceAccount);
        //查询所有账号，将不属于源账号的子账号，且权限大于源账号的账号对象加入返回列表
        List<SUser> list = permissionDao.getSUsers();
        List<SUser> forTrans = new ArrayList<SUser>();
        for(SUser l:list){
            if(map.values().contains(l)||l.getUsername().equals(sourceAccount)){
                continue;
            }
            if(this.comparePermission(l, sourceLogin)){
                forTrans.add(l);
            }
        }
        return forTrans;
    }
    
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getSUserListForTransfer(java.lang.String, java.lang.String)
	 */
    public SUser getSUserListForTransfer(String sourceAccount,String destAccount) throws MyException{
        //判断源账号是否存在
        SUser source = permissionDao.getSUserByUsername(sourceAccount);
        if(source==null) return null;
        //判断目标账号是否存在
        SUser dest = permissionDao.getSUserByUsername(destAccount);
        if(dest==null) return null;
        //判断目标账号是属于源账号的子账号
        Map<String,SUser> map = permissionDao.getSUserAll(sourceAccount);
        if(map.values().contains(dest)) return null;
        //判断目标账号权限是否大于源账号
        if(!this.comparePermission(dest,source)) return null;
        
        return dest;
    }
    
    /**
     * 比较账户A和账户B的权限，A>B则返回True
     * @param a
     * @param b
     * @throws MyException,如果Ａ或Ｂ为空则抛出异常。
     * @return boolean
     */
    private boolean comparePermission(SUser a,SUser b)throws MyException{
        if(a==null||b==null)
            throw new MyException("比较的两个用户对象传入不能为空！");
        //如果A账户是系统定义账户则大于所有的账户权限
        if(a.getUsername().equals(AppConstants.DEF_SYSADMIN))
            return true;
        //检查A账户是否包括B账户的角色
        List<SUserRole> apart = permissionDao.getSUserRoleByUserid(a.getId());
        List<Long> ap = new ArrayList<Long>();
        for(SUserRole mp:apart){
            ap.add(mp.getRoleid());
        }
        
        List<SUserRole> bpart = permissionDao.getSUserRoleByUserid(b.getId());
        for(SUserRole mp:bpart){
            if(!ap.contains(mp.getRoleid())){
                return false;
            }
        }
        
        return true;
    }

    /**
     * 返回一个角色有的所有权限关联对象,是一个map对象 key为模块编号 , value
     * 为actionids,如果是第一级的模块,actionids为null
     * @param roleid 角色编号
     * @return
     */
    private Map<String,String> getPermissons(long roleid) throws MyException {
        Map<String, String> map = new HashMap<String, String>();
        List<SRoleModule> li = permissionDao.getSRoleModule(roleid);
        for (Iterator<SRoleModule> it = li.iterator(); it.hasNext();) {
            SRoleModule mp = it.next();
            String modid = mp.getModuleid();
            String actionids = mp.getActionids();
            map.put(modid, actionids);
        }
        return map;
    }
    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#getModules(long, boolean)
	 */
    public List<ModuleValue> getModules(long roleid, boolean withchecked)
            throws MyException {

        List<SModule> li = getCacheModuleList();//permissionDao.getModules();//
        Map<String,String> permissons = null;

        if (roleid==0) {
            withchecked = false;
        }
        if (withchecked)
            permissons = this.getPermissons(roleid);
        List<ModuleValue> ret = new ArrayList<ModuleValue>();
        for (Iterator<SModule> it = li.iterator(); it.hasNext();) {
            SModule mm = (SModule) it.next();

            // 只增加存在的模块
            if (mm != null) {
                ModuleValue mv = new ModuleValue();
                mv.setId(mm.getId());
                mv.setName(mm.getModulename());
                mv.setUrl(mm.getUrl());
                if(mm.getUrl()!=null&&!mm.getUrl().trim().equals("")){
                	mv.setIsleaf("1");
                }else{
                	mv.setIsleaf("0");
                }

                int idlenth = mm.getId().length();
                if (idlenth == 3) {
                    mv.setLevel(1);
                } else if (idlenth == 6) {
                    mv.setLevel(2);
                } else if (idlenth == 9) {
                    mv.setLevel(3);
                }

                // 判断是否有模块权限
                if (withchecked) {
                    mv.setChecked(permissons == null ? false : permissons
                            .containsKey(mm.getId()));
                }

                Set actions = mm.getActions();
                List<ModuleActionValue> actionValue = new ArrayList<ModuleActionValue>();
                for (Iterator itt = actions.iterator(); itt.hasNext();) {
                    SModuleAction act = (SModuleAction) itt.next();
                    ModuleActionValue mav = new ModuleActionValue();

                    // 判断是否是已选中的模块按钮
                    if (withchecked) {
                        int shortid = Integer.parseInt(Long.toString(act
                                .getActionshortid()));
                        mav.setChecked(this.isModuleActionChecked(permissons,
                                mm.getId(), shortid));
                    }
                    mav.setActionname(act.getActionname());
                    mav.setActionid(act.getId());
                    mav.setModid(mm.getId());

                    actionValue.add(mav);
                }
                mv.setActions(actionValue);
                ret.add(mv);
            }
        }
        return ret;
    }
    
    /**
     * 返回是否对某个按钮有checked属性 这个方法调用主要用于前台角色管理页面要显示一个打上钩的checkbox
     * @param permissons 某个角色的权限集合
     * @param moduleId 要检查的模块
     * @param actionId 要检查的按钮的短编号 即只是1,2,3,4 不是001,002,003
     * @return true,表示有权限,false 表示没有
     */
    private boolean isModuleActionChecked(Map<String,String> permissons, String moduleId,
            int actionShortId) {
        if (permissons == null)
            return false;
        String actionids = (String) permissons.get(moduleId);
        if (actionids == null)
            return false;

        if (actionShortId <= 0)
            return false;

        if (actionids.charAt(actionShortId - 1) == '1') {
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#refreshCacheData()
	 */
    public boolean refreshCacheData() throws MyException {
        modloaded = false;
        partload = false;
        try {
            permissionCache = JCS.getInstance("permissionCacheNew");
            permissionCache.remove("modulelist");
        } catch (CacheException e) {
            throw new MyException("getCachePermission", e);
        }
        this.getCacheModuleList();
        return true;
    }
    
    /**
     * 返回cached的某个角色下的权限集合 如果缓存里没有数据,先从数据库里取,然后再放到缓存中
     * 缓存块的数据结构为如下： ptid==>Map {k=modid,value=actions}
     * 即每个角色对应可操作的模块,以及模块按钮权限,是以上述数据结构放在一个map里,然后再把这个map缓存到 缓存中
     * @param roleid 角色编号
     * @return Map 一个 Map {k=modid,value=actions}的数据结构
     * @throws MyException
     */
    private Map<String,String> getCachePermission(long roleid) throws MyException {
        if (roleid == 0)
            return null;
        Map<String,String> map = null;
        // get it from cache
        try {
            permissionCache = JCS.getInstance("permissionCacheNew");
            map = (Map<String,String>) permissionCache.get(roleid);
            if (map == null) {
                map = getPermissons(roleid);
                permissionCache.put(roleid, map);
            }
        } catch (CacheException e) {
            throw new MyException("getCachePermission", e);
        }
        return map;
    }

    /**
     * 更新缓存的权限,在角色的权限有修改时,要更新缓存某个角色的权限数据 这个方法会读一次数据库
     * @param roleid
     * @throws MyException
     */
    private void reloadCachePermission(long roleid) throws MyException {
        if (roleid == 0) return;
        Map<String,String> map = null;
        // get it from cache
        try {
            permissionCache = JCS.getInstance("permissionCacheNew");
            map = (Map<String,String>) permissionCache.get(roleid);
            if (map != null) {
                permissionCache.remove(roleid);
            }
            map = getPermissons(roleid);
            permissionCache.put(roleid, map);

        } catch (CacheException e) {
            throw new MyException("getCachePermission", e);
        }
    }

    /**
     * 删除某个角色的cache,在删除角色时会调用这个方法,清空角色的缓存数据
     * @param roleid 角色编号
     * @throws MyException
     */
    private void removeCachePermission(long roleid) throws MyException {
        if (roleid == 0)
            return;
        Map<String,String> map = null;
        // get it from cache
        try {
            permissionCache = JCS.getInstance("permissionCacheNew");
            map = (Map<String,String>) permissionCache.get(roleid);
            if (map != null) {
                permissionCache.remove(roleid);
            }
        } catch (CacheException e) {
            throw new MyException("removeCachePermission", e);
        }
    }
    
    /**
     * 取得缓存的菜单模块列表，如果为空则先从数据库缓存到JCS
     * @throws MyException
     * @return List
     */
    private List<SModule> getCacheModuleList() throws MyException{
        List<SModule> moduleList = null;
        try{
            permissionCache = JCS.getInstance("permissionCacheNew");
            if(permissionCache.get("modulelist")==null){
                List<SModule> mods = permissionDao.getModules();
                for(Object obj:mods){
                    SModule mod = (SModule)obj;
                    /*
                     * 循环取每个模块的Actions，Module和Actions为一对多关联，
                     * 为了防止出现取Module关联的Actions时发生延迟加载异常。
                     */
                    mod.getActions().size();    
                }
                permissionCache.put("modulelist",mods);
            }
            moduleList = (List<SModule>)permissionCache.get("modulelist");
        }catch(CacheException e){
            throw new MyException("getCacheModuleList",e);
        }
        return moduleList;
    }

    /**
     * 从缓存里取得模块 缓存内部储存规则为"mod"+xxx, xxx为模块编号
     * @param modid 模块编号
     * @return SModule 持久类模块
     */
    private SModule getCacheModule(String modid) throws MyException {
        if (modid == null)
            return null;
        SModule mod = null;
        // get it from cache
        try {
            permissionCache = JCS.getInstance("permissionCacheNew");
            if (!modloaded) {
                List<SModule> l = getCacheModuleList();
                for (Iterator<SModule> it = l.iterator(); it.hasNext();) {
                    SModule el = it.next();
                    if (el != null) {
                        permissionCache.put("mod" + el.getId(), el);
                    }
                }
                modloaded = true;
            }
            mod = (SModule) permissionCache.get("mod" + modid);
        } catch (CacheException e) {
            throw new MyException("getCacheModule", e);
        }
        return mod;
    }
    
    /** 只加载一次，之后从缓存中获取SRole */
    private static boolean partload = false;
    
    /**
     * 根据ptid从缓存中获取SRole对象
     * @param ptid
     * @throws MyException
     * @return SRole
     */
    private SRole getCacheSRole(long roleid)throws MyException{
        if(roleid == 0){
            return null;
        }
        SRole sRole = null;
        try{
            permissionCache = JCS.getInstance("permissionCacheNew");
            if(!partload){
                List<SRole> SRoles = permissionDao.getSRoles();
                for(Iterator<SRole> it = SRoles.iterator();it.hasNext();){
                    SRole mp = it.next();
                    permissionCache.put("part"+mp.getId(),mp);
                }
                partload = true;
            }
            sRole = (SRole) permissionCache.get("part"+roleid);
            if(sRole==null){
            	sRole = permissionDao.getSRoleById(roleid);
                permissionCache.put("part"+roleid,sRole);
            }
        }catch(CacheException e){
            throw new MyException("getCacheSRole",e);
        }
        return sRole;
    }
}
