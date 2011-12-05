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
 * ����˵����Ȩ�޹���Managerʵ����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:06:54
 */
public class PermissionManagerImpl extends MyBaseBusiness implements
        PermissionManager {
    /** ������ʱ����һ��˽��Dao����ֻ�ڱ�������� */
    private final static PermissionDao permissionDao;
    /** ��ʼ��Dao���󣬴�Dao������ȡ�����п����׳��쳣 */
    static {
        try {
            permissionDao = (PermissionDao) MyDaoFactory
                    .getDaoManager(AppConstants.PermissionDao);
        } catch (MyException e) {
            throw new IllegalArgumentException(Msg.get(MsgCode.MMT_MA_COMMON_00005), e);
        }
    }

    /**
     * ˽�й��췽������ֹ�ⲿ��������ʵ����ֻ�ܹ�����������ʵ��
     */
    private PermissionManagerImpl() {
    }

    /** Ȩ�޻������ */
    private static JCS permissionCache;

    /** Ȩ��ģ��ֻ�ڵ�¼ʱ����һ�Σ�֮��ӻ��������ȡ */
    private static boolean modloaded = false;

    /* (non-Javadoc)
	 * @see org.yehongyu.websale.business.PermissionManager#loginUser(java.lang.String, java.lang.String)
	 */
    public UserSession loginUser(String username, String password)
            throws MyException {
        //��֤��¼�û��������Ƿ����
        if(username==null||username.equals("")||password==null||password.equals("")) return null;
        SUser login = permissionDao.getSUserByUsername(username);
        if (login == null) return null;// �û�������
        if (!MyMD5.MD5Encode(password).equals(login.getPassword())) return null;// ���벻��ȷ
        
        //��¼ͨ������¼�û�Session
        UserSession userSession = new UserSession();
        userSession.setUserid(login.getId());
        userSession.setUserName(login.getUsername());
        userSession.setPassword(login.getPassword());
        userSession.setName(login.getName());
        userSession.setPhone(login.getPhone());
        userSession.setAdmin(login.getAdmin());
        userSession.setDepartment(login.getDepartment());
        userSession.setEmail(login.getEmail());

        //���õ�¼�û��Ľ�ɫ
        List<SUserRole> SUserRoles = permissionDao.getSUserRoleByUserid(login.getId());// �û��еĽ�ɫ
        StringBuffer sb = new StringBuffer(100);
        if (SUserRoles != null && SUserRoles.size() > 0) {
            for (Object SUserRole:SUserRoles) {
                SUserRole mpl = (SUserRole) SUserRole;
                String ptid = Convert.getString(mpl.getRoleid());
                sb.append(ptid).append(AppConstants.SPLIT_DEFAULT);
            }
        }
        //�����ϵͳ���峬���û�ӵ�����еĽ�ɫ
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
        //��ѯ������ģ��
        List<SModule> moduleList = getCacheModuleList();//permissionDao.getModules();
        for(SModule mod:moduleList){
            //��ÿ��ģ�����жϣ������û�ӵ�еĽ�ɫ�Ƿ��д�ģ��ķ���Ȩ��
            for (int i = 0; i < sroles.length; i++) {
                Map<String,String> map = this.getCachePermission(Long.valueOf(sroles[i]));
                if (map != null && map.containsKey(mod.getId())) {
                    if (mod.getId().trim().length() / 3 == level
                            && mod.getId().trim().indexOf(parentModid)==0) {
                        String modname = mod.getModulename();
                        String url = mod.getUrl()!=null?mod.getUrl():"#";
                        // ����ģ������
                        MenuItemValue menuitem = new MenuItemValue();
                        menuitem.setText(modname);
                        menuitem.setUrl(url);
                        menuitem.setId(mod.getId());
                        
                        menu.add(menuitem);
                        if(level<3){
                        	int temp = level+1;
                            menuitem.setSubMenu(this.getMenuByLevel(session,temp,menuitem.getId()));
                        }
                        break;  //���˵��Ѿ����ϣ�������ɫѭ����������һ��ģ���ж�
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
            // ֻҪһ����ɫ��Ȩ��,����Ȩ��
            if (isModuleAuthend(moduleId, Long.valueOf(sroles[i]))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * ����ĳ����ɫ��ĳ��ģ���Ƿ��в���Ȩ�� ֱ�Ӵӻ�����ȡ,�����Ȩ�޾���Ȩ��,û�о���û��
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
                            if (array[id - 1] == '1') { // ��ʾ�������ť��Ȩ��
                                retValue = true;
                                break; // ��Ȩ��ֱ������,��������ж�
                            }
                        }
                    }
                }
            } catch (MyException e) {
                log.error(e);
            }
        } // ֻҪ��һ����ɫ�и�ģ��,�ð�ťȨ��,�˳�ѭ��,����true

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
                mlName += AppConstants.SPLIT_NAV + "��ģ��δ����";
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
        	return "����ľ����벻��ȷ��";
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
//        //���ø��˺�
//        String admin = pa.getAdmin();
//        if(admin==null||admin.equals("")){
//            admin = us.getUserName();
//        }
//        pa.setAdmin(admin);
//        //���˺ű����ǵ�¼�û��������˺ţ������ò�ѯ
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
        //��֤�����ĺϷ���
        if (user == null) return "������û���ϢΪ��";
        if (user.getUsername() == null||user.getUsername().equals("")) return "������û���Ϊ��";
        Object o = permissionDao.getSUserByUsername(user.getUsername());
        if (o != null) return "�Ѿ����ڴ��û���:"+user.getUsername();
        
        //�����û���ɫ
        List<PartValue> partids = user.getPart();
        //д�û����¼
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
        
        //д�û���ɫ���¼
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
        //��֤�����ĺϷ���
        if (user == null||user.getUserid()==0) return "������û���ϢΪ��";
        if (user.getUsername() == null||user.equals("")) return "������û���Ϊ��";
        SUser o = permissionDao.getSUserByUsername(user.getUsername());
        if (o != null&&o.getId()!=user.getUserid()) return "�Ѿ����ڴ��û���:"+user.getUsername();
        
        List<SUserRole> k = permissionDao.getSUserRoleByUserid(user.getUserid());
        for (int i = 0; i < k.size(); i++) {
            permissionDao.delete((SUserRole) k.get(i));
        }
        
        //�����û���ɫ
        List<PartValue> partids = user.getPart();
        
        //�޸��û���
        SUser login = permissionDao.getSUserById(user.getUserid());
        if(!login.getUsername().equals(user.getUsername())){    //����û����޸ģ�����������˺ŵĸ��˺���Ϣ
            List<SUser> nlogin = permissionDao.getSUserNext(login.getUsername());
            for(SUser ml:nlogin){
                ml.setAdmin(user.getUsername());
                permissionDao.update(ml);
            }
            //ͬʱ�����µ��û���
            login.setUsername(user.getUsername());
        }
        login.setName(user.getName());
        login.setDepartment(user.getDepname());
        login.setPhone(user.getPhone());
        login.setEmail(user.getEmail());
        login.setMemo(user.getMemo());
        login.setAdmin(user.getAdmin());
        // �������⴦��,���ǰ̨�ύ������ͺ�̨�����벻��Ӧ,˵���û��޸�������
        String bpasswd = login.getPassword();
        String fpasswd = user.getPassword();
        if (!fpasswd.equals(bpasswd)) {
            login.setPassword(MyMD5.MD5Encode(user.getPassword()));
        }
        permissionDao.update(login);

        //��ѯ���˺ţ��������˺ŵĽ�ɫ����̳й�ϵ�����˺���û�еĽ�ɫ,���˺�Ҳ��Ӧ�ô��ڣ�
        //��ΪMysql��֧�������ѯ,����ע�͵�
        /*List<Long> adminparts = new ArrayList<Long>();
        for(PartValue pv:partids){  //���ñ��˺ŵĽ�ɫString�б�
            adminparts.add(pv.getRoleid());
        }
        Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
        for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
            String key = it.next();
            SUser cldlogin = map.get(key);
            //ɾ�����˺��в����ڵĽ�ɫ
            List<SUserRole> mpl = permissionDao.getSUserRoleByUserid(cldlogin.getId());
            for(Object obj:mpl){
                SUserRole mp = (SUserRole)obj;
                if(!adminparts.contains(mp.getRoleid())){
                    permissionDao.delete(mp);
                }
            }
        }*/
        
        //д�û���ɫ��
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
        //��֤�����Ϸ���
        if(userids==null||userids.length==0) return "������û�IDΪ��";
        
        //ѭ��ɾ��ÿ���û�
        StringBuffer info = new StringBuffer();   //ɾ����Ϣ
        for (int m = 0; m < userids.length; m++) {
            SUser login = permissionDao.getSUserById(userids[m]);
            if(login==null) {
                info.append("ɾ�����û�Id[").append(userids[m]).append("]������!\\r\\n");
                continue;   //���ɾ�����û������ڣ�����һ��
            }else{
//                Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
//                if(!map.entrySet().isEmpty()){
//                    info.append("ɾ�����û�Id[").append(userids[m]).append("]�������˺ţ�����ɾ����\\r\\n");
//                    continue;   //���ɾ�����û��´������˺ţ�����һ��
//                }
                List<SUser> l = permissionDao.getSUserNext(login.getUsername());
                if(l!=null&&l.size()>0){
                  info.append("ɾ�����û�Id[").append(userids[m]).append("]�������˺ţ�����ɾ����\\r\\n");
                  continue;   //���ɾ�����û��´������˺ţ�����һ��
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
        //��֤�����ĺϷ���
        if (part == null) return "����Ľ�ɫ��ϢΪ��";
        if (part.getRolename() == null||part.getRolename().trim().equals("")) return "����Ľ�ɫ����Ϊ��";
        Object o = permissionDao.getSRoleByRolename(part.getRolename());
        if (o != null) return "�Ѿ����ڴ˽�ɫ��:"+part.getRolename();
        
        SRole role = new SRole();
        role.setRolename(part.getRolename());
        role.setMemo(part.getMemo());
        role.setType(0);	//��ͨ��ɫ

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
    	//��֤�����ĺϷ���
        if (part == null) return "����Ľ�ɫ��ϢΪ��";
        if (part.getRolename() == null||part.getRolename().trim().equals("")) return "����Ľ�ɫ����Ϊ��";
        Object o = permissionDao.getSRoleByRolename(part.getRolename());
        if (o != null&&((SRole)o).getId()!=part.getRoleid()) return "�Ѿ����ڴ˽�ɫ��:"+part.getRolename();
        
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
        // ����һ�»���
        this.refreshCacheData();
        this.reloadCachePermission(role.getId());

        return AppConstants.MANAGER_BACK_SUCCESS;
    }

	/**
	 * ����ɾ����ɫ
	 * @param partids Ҫɾ���Ľ�ɫ����
	 * @return String ���ص���Ϣ
	 */
	public String RemovePart(long[] partids)throws MyException{
    	//��֤�����Ϸ���
        if(partids==null||partids.length==0) return "����Ľ�ɫIDΪ��";

        StringBuffer info = new StringBuffer();
        for (int i = 0; i < partids.length; i++) {
            List<SUserRole> sUserRole = permissionDao.getSUserRoleByRoleid(partids[i]);
            if (sUserRole != null && sUserRole.size() != 0) {
            	info.append("ɾ���Ľ�ɫId[").append(partids[i]).append("]�ѱ�����������ɾ����\\r\\n");
                continue;   //���ɾ�����û��´������˺ţ�����һ��
            }
            // ��ɾ�������ɫ�µ����в˵���ɫ����
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
        //��֤�����Ϸ��ԣ������useridΪ0�������userid�ĵ�¼�˺ţ������admin��Ϊnull��"",�ұ������admin�ĵ�¼�˺�
        if(admin==null||admin.equals("")) return null;
        SUser upuser = permissionDao.getSUserByUsername(admin);
        if(upuser==null) return null;
        if(userid!=0){
            SUser user = permissionDao.getSUserById(userid);
            if(user==null) return null;
        }
        List<PartValue> re = new ArrayList<PartValue>();
        // �Ƿ�Ҫ�ж��ѹ�ѡ�Ľ�ɫ,check=true��ʾҪ�ж�
        boolean check = true;
        if (userid == 0)
            check = false;
        List<Long> l = null;
        if (check) {
            //��ѯ���˺�ӵ�еĽ�ɫId
            List<SUserRole> mpl = permissionDao.getSUserRoleByUserid(userid);
            if(mpl!=null&&mpl.size()>0){
                l = new ArrayList<Long>();
                for(SUserRole mp:mpl){
                    l.add(mp.getRoleid());
                }
            }
        }
        // �����еĽ�ɫ
        List<SRole> allpart;
        if(admin.equals(AppConstants.DEF_SYSADMIN)){    //����Ǹ��˺�ϵͳ����Աӵ�����еĽ�ɫ
            allpart = permissionDao.getSRoles();
        }else{  //������˺�����ͨ�˻���ֻӵ�и��˺������еĽ�ɫ
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
        //��ѯԴ�˺ź�Ŀ���˺��Ƿ���ڣ�Ϊ�շ���false
        SUser sourceLogin = permissionDao.getSUserByUsername(source);
        if(sourceLogin==null) return false;
        SUser destLogin = permissionDao.getSUserByUsername(dest);
        if(destLogin==null) return false;
        //��ѯĿ���˺�Ȩ���Ƿ����Դ�˺�,С�ڷ���false;
        if(!this.comparePermission(destLogin,sourceLogin)) return false;
        //��ʼת���˺ţ���ֱ�����˺ŵĸ��˺����Ը�ΪĿ���˺š�
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
        //��֤����
        if(sourceAccount==null||"".equals(sourceAccount)) return null;
        //��ѯԴ�˺Ŷ���
        SUser sourceLogin = permissionDao.getSUserByUsername(sourceAccount);
        if(sourceLogin==null) return null;
        //��ѯԴ�˺ŵ����˺Ŷ���
        Map<String,SUser> map = permissionDao.getSUserAll(sourceAccount);
        //��ѯ�����˺ţ���������Դ�˺ŵ����˺ţ���Ȩ�޴���Դ�˺ŵ��˺Ŷ�����뷵���б�
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
        //�ж�Դ�˺��Ƿ����
        SUser source = permissionDao.getSUserByUsername(sourceAccount);
        if(source==null) return null;
        //�ж�Ŀ���˺��Ƿ����
        SUser dest = permissionDao.getSUserByUsername(destAccount);
        if(dest==null) return null;
        //�ж�Ŀ���˺�������Դ�˺ŵ����˺�
        Map<String,SUser> map = permissionDao.getSUserAll(sourceAccount);
        if(map.values().contains(dest)) return null;
        //�ж�Ŀ���˺�Ȩ���Ƿ����Դ�˺�
        if(!this.comparePermission(dest,source)) return null;
        
        return dest;
    }
    
    /**
     * �Ƚ��˻�A���˻�B��Ȩ�ޣ�A>B�򷵻�True
     * @param a
     * @param b
     * @throws MyException,��������Ϊ�����׳��쳣��
     * @return boolean
     */
    private boolean comparePermission(SUser a,SUser b)throws MyException{
        if(a==null||b==null)
            throw new MyException("�Ƚϵ������û������벻��Ϊ�գ�");
        //���A�˻���ϵͳ�����˻���������е��˻�Ȩ��
        if(a.getUsername().equals(AppConstants.DEF_SYSADMIN))
            return true;
        //���A�˻��Ƿ����B�˻��Ľ�ɫ
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
     * ����һ����ɫ�е�����Ȩ�޹�������,��һ��map���� keyΪģ���� , value
     * Ϊactionids,����ǵ�һ����ģ��,actionidsΪnull
     * @param roleid ��ɫ���
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

            // ֻ���Ӵ��ڵ�ģ��
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

                // �ж��Ƿ���ģ��Ȩ��
                if (withchecked) {
                    mv.setChecked(permissons == null ? false : permissons
                            .containsKey(mm.getId()));
                }

                Set actions = mm.getActions();
                List<ModuleActionValue> actionValue = new ArrayList<ModuleActionValue>();
                for (Iterator itt = actions.iterator(); itt.hasNext();) {
                    SModuleAction act = (SModuleAction) itt.next();
                    ModuleActionValue mav = new ModuleActionValue();

                    // �ж��Ƿ�����ѡ�е�ģ�鰴ť
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
     * �����Ƿ��ĳ����ť��checked���� �������������Ҫ����ǰ̨��ɫ����ҳ��Ҫ��ʾһ�����Ϲ���checkbox
     * @param permissons ĳ����ɫ��Ȩ�޼���
     * @param moduleId Ҫ����ģ��
     * @param actionId Ҫ���İ�ť�Ķ̱�� ��ֻ��1,2,3,4 ����001,002,003
     * @return true,��ʾ��Ȩ��,false ��ʾû��
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
     * ����cached��ĳ����ɫ�µ�Ȩ�޼��� ���������û������,�ȴ����ݿ���ȡ,Ȼ���ٷŵ�������
     * ���������ݽṹΪ���£� ptid==>Map {k=modid,value=actions}
     * ��ÿ����ɫ��Ӧ�ɲ�����ģ��,�Լ�ģ�鰴ťȨ��,�����������ݽṹ����һ��map��,Ȼ���ٰ����map���浽 ������
     * @param roleid ��ɫ���
     * @return Map һ�� Map {k=modid,value=actions}�����ݽṹ
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
     * ���»����Ȩ��,�ڽ�ɫ��Ȩ�����޸�ʱ,Ҫ���»���ĳ����ɫ��Ȩ������ ����������һ�����ݿ�
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
     * ɾ��ĳ����ɫ��cache,��ɾ����ɫʱ������������,��ս�ɫ�Ļ�������
     * @param roleid ��ɫ���
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
     * ȡ�û���Ĳ˵�ģ���б����Ϊ�����ȴ����ݿ⻺�浽JCS
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
                     * ѭ��ȡÿ��ģ���Actions��Module��ActionsΪһ�Զ������
                     * Ϊ�˷�ֹ����ȡModule������Actionsʱ�����ӳټ����쳣��
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
     * �ӻ�����ȡ��ģ�� �����ڲ��������Ϊ"mod"+xxx, xxxΪģ����
     * @param modid ģ����
     * @return SModule �־���ģ��
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
    
    /** ֻ����һ�Σ�֮��ӻ����л�ȡSRole */
    private static boolean partload = false;
    
    /**
     * ����ptid�ӻ����л�ȡSRole����
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
