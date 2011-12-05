/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.dao.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.permission.PartParam;
import org.yehongyu.websale.bo.manage.permission.UserParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.common.util.StringUtils;
import org.yehongyu.websale.dao.PermissionDao;
import org.yehongyu.websale.db.MyDaoMydb;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.bean.PageRecordBean;
import org.yehongyu.websale.db.po.mydb.SModule;
import org.yehongyu.websale.db.po.mydb.SRole;
import org.yehongyu.websale.db.po.mydb.SRoleModule;
import org.yehongyu.websale.db.po.mydb.SUser;
import org.yehongyu.websale.db.po.mydb.SUserRole;

/**
 * ����˵����Ȩ�޹���DAOʵ����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:15:53
 */
public class PermissionDaoImpl extends MyDaoMydb implements PermissionDao {  
    /** 
     * ˽�й��췽������ֹ�ⲿ��������ʵ����ֻ�ܹ�����������ʵ�� 
     */
    private PermissionDaoImpl(){}

    
    /**
     * �����û�ID����Ψһ�û�
     * @param userid
     * @return �û�����
     * @throws MyException
     */
    public SUser getSUserById(long userid) throws MyException{
        return (SUser)super.find(SUser.class, userid);
    }
    
    /**
     * �����û�������Ψһ�û����û���Ϊ�ջ��ѯ�����ڻ����������¼���򷵻ؿ�
     * @param username
     * @return �û�����
     * @throws MyException
     */
    public SUser getSUserByUsername(String username){
        //������֤
        if (username ==null || "".equals(username)) return null;
        
        String sql = "from SUser a where 1=1 and a.username = :username";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        SUser ml = null;
        try {
            ml = (SUser)uniqueQuery(sql,map);
        } catch (MyException e) {
            log.info("��ѯ�û���Ϊ"+username+"��Ψһ�û�ʱ�����쳣���û�����Ψһ");
        }
        return ml;
    }
    
    /**
     * ���������û�����
     * @return �����û�����
     * @throws MyException
     */
    public List<SUser> getSUsers() throws MyException{
        String queryString = "from SUser a where 1=1 order by username";
        return super.query(queryString);
    }
    
    /**
     * ���ݸ��˺ŷ�����ֱ�����˺Ŷ���
     * @param admin
     * @throws MyException
     * @return List<SUser>
     */
    public List<SUser> getSUserNext(String admin)throws MyException{
        //��֤�����Ϸ���
        if(admin==null||admin.equals("")) return null;
        
        StringBuffer queryString = new StringBuffer();
        queryString.append("from SUser a where 1=1 and a.admin = :admin");
        Map<String,Object> param =new HashMap<String,Object>();
        param.put("admin", admin);
        queryString.append(" order by username");
        return query(queryString.toString(),param);
    }
    
    /**
     * ����ĳ�û��µ������û�����
     * @param admin ���˺�
     * @return �����û�����
     * @throws MyException
     */
    public Map<String,SUser> getSUserAll(String admin)throws MyException{
        StringBuffer queryString = new StringBuffer();
        queryString.append("select * from s_user a where 1=1 ");
        List<Object> params = new ArrayList<Object>();
        //ֻ��ѯ���˺����ӵ���Ա 
        queryString.append(" start with username=?");
        params.add(admin);  
        queryString.append(" connect by prior username=admin order siblings  by username ");
        List<PageRecordBean> userList = queryByJDBC(queryString.toString(),params);
        Map<String,SUser> users = new LinkedHashMap<String,SUser>();
        SUser su = null;
        if(userList!=null&&userList.size()>0){
        	for(PageRecordBean prb:userList){
                if(((String)prb.getValue("username")).equals(admin)){   //���˺��Լ�����ʾ
                    continue;
                }
                su = new SUser();
                su.setId(((BigDecimal)prb.getValue("userid")).longValue());
                su.setUsername((String)prb.getValue("username"));
                su.setAdmin((String)prb.getValue("admin"));
                su.setDepartment(((String)prb.getValue("department")));
                su.setEmail((String)prb.getValue("email"));
                su.setMemo((String)prb.getValue("memo"));
                su.setName((String)prb.getValue("name"));
                su.setPassword((String)prb.getValue("password"));
                su.setPhone((String)prb.getValue("phone"));
                users.put(su.getUsername(), su);
            }
        }
        return users;
    }
    
    /**
     * ͨ��JDBCȡ��Ա��ȥ���˸����û����鲻�����˺ŵ������
     * @param pa
     * @param pageBean
     * @throws MyException
     * @return Page
     */
//    public Page getSUserList(UserParam pa,PageBean pageBean)throws MyException{
//        StringBuffer queryString = new StringBuffer();
//        queryString.append("select * from s_user a where 1=1 ");
//        List<Object> params = new ArrayList<Object>();
//        String username = pa.getUsername();
//        String name = pa.getName();
//        String depname = pa.getDepname();
//        String admin = pa.getAdmin();
//        admin = admin != null && !"".equals(admin.trim())?admin:"";
//        if (username != null && !"".equals(username.trim())&&!pa.getAdmin().equals(username.trim())) {
//            queryString.append(" and a.username=? ");
//            params.add(username);
//        }else{
//        	queryString.append(" and a.username<>? ");
//            params.add(pa.getAdmin());
//        }
//        if (name != null && !"".equals(name.trim())) {
//            queryString.append(" and  a.name like ? ");
//            params.add(name + "%");
//        }
//        if (depname != null && !"".equals(depname.trim())) {
//            queryString.append(" and  a.department like ? ");
//            params.add(depname + "%");
//        }
//        //ֻ��ѯ���˺����ӵ���Ա 
//        queryString.append(" start with username=?");
//        params.add(admin);  
//        queryString.append(" connect by prior username=admin order siblings  by username ");
//        Page p = getPageQueryByJDBC(queryString.toString(),params,pageBean);
//        List<PageRecordBean> userList = p.getLstResult();
//        List<SUser> users = new ArrayList<SUser>();
//        SUser su = null;
//        if(userList!=null&&userList.size()>0){
//        	for(PageRecordBean prb:userList){
//                if(((String)prb.getValue("username")).equals(admin)){   //���˺��Լ�����ʾ
//                    continue;
//                }
//                su = new SUser();
//                su.setId(((BigDecimal)prb.getValue("userid")).longValue());
//                su.setUsername((String)prb.getValue("username"));
//                su.setAdmin((String)prb.getValue("admin"));
//                su.setDepartment(((String)prb.getValue("department")));
//                su.setEmail((String)prb.getValue("email"));
//                su.setMemo((String)prb.getValue("memo"));
//                su.setName((String)prb.getValue("name"));
//                su.setPassword((String)prb.getValue("password"));
//                su.setPhone((String)prb.getValue("phone"));
//                users.add(su);
//            }
//        }
//        p.setLstResult(users);
//        return p;
//    }

    /**
     * �����û�Id��ȡ�û���ɫȨ���б��û���Ϊ�գ��򷵻ؿ�
     * @param userid
     * @return �û�����Ȩ���б�
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByUserid(long userid) throws MyException {
        if (userid == 0) return null;
        String sql = "from SUserRole a where 1=1 and a.userid = :userid";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        return super.query(sql, map);
    }
    
    /**
     * ���ݽ�ɫId��ȡ��ɫ�û�Ȩ���б���ɫΪ�գ��򷵻ؿ�
     * @param roleid
     * @return �û���ɫȨ���б�
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByRoleid(long roleid) throws MyException {
        if (roleid == 0) return null;
        String queryString = "from SUserRole mp where mp.roleid=:roleid";
        Map<String, Object> bean = new HashMap<String, Object>();
        bean.put("roleid", roleid);
        return super.query(queryString, bean);
    }

    /**
     * ����Id��ȡΨһ�Ľ�ɫ����
     * @param partId
     * @return ��ɫ����
     * @throws MyException
     */
    public SRole getSRoleById(long roleid) throws MyException{
        return (SRole)super.find(SRole.class, roleid);
    }
    
    /**
     * ���ݽ�ɫ������Ψһ��ɫ����ɫ��Ϊ�ջ��ѯ�����ڻ����������¼���򷵻ؿ�
     * @param username
     * @return ��ɫ����
     * @throws MyException
     */
    public SRole getSRoleByRolename(String rolename){
        //������֤
        if (rolename ==null || "".equals(rolename)) return null;
        
        String sql = "from SRole a where 1=1 and a.rolename = :rolename";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rolename", rolename);
        SRole ml = null;
        try {
            ml = (SRole)uniqueQuery(sql,map);
        } catch (MyException e) {
            log.info("��ѯ�û���Ϊ"+rolename+"��Ψһ�û�ʱ�����쳣���û����������û�����Ψһ");
        }
        return ml;
    }
    
    /**
     * ���ؽ�ɫ�б�
     * @return ���ؽ�ɫ�б�
     * @throws MyException
     */
    public List<SRole> getSRoles() throws MyException {
        StringBuffer sql = new StringBuffer();
        sql.append("from SRole order by id");
        return query(sql.toString());
    }
    
    /**
     * �����û�����ȡ�û����еĽ�ɫ�б�
     * @param username �û���
     * @throws MyException
     * @return List<SRole>
     */
    public List<SRole> getSRolesByUsername(String username)throws MyException{
        if(username==null||"".equals(username)) return null;
        long userid = ((SUser)this.getSUserByUsername(username)).getId();
        List<SUserRole> allmpl = this.getSUserRoleByUserid(userid);
        List<SRole> allpart = new ArrayList<SRole>();
        for(SUserRole pl:allmpl){
            allpart.add(this.getSRoleById(pl.getRoleid()));
        }
        return allpart;
    }
    
    /**
     * ���ط�ҳ�Ľ�ɫ����
     * @param pa ��ѯ���û�Bo����
     * @param pageBean ��ҳ����
     * @return ��ҳ���û�����
     * @throws MyException
     */
    public Page getSRoleList(PartParam pa,PageBean pageBean) throws MyException {
        StringBuilder queryString = new StringBuilder();
        queryString.append("from SRole where 1=1");
        String rolename = pa.getPtname();
        HashMap<String,Object> param =new HashMap<String,Object>();
        if (rolename != null && !"".equals(rolename.trim())) {
            queryString.append(" and rolename like :rolename");
            param.put("rolename", rolename + "%");
        }
        queryString.append(" order by type desc,id asc ");
        return getPageQuery(queryString.toString(),param,pageBean);
    }

	/**
	 * ���ݽ�ɫId��ѯģ��Ͱ�ťȨ�ޣ���ɫΪ�գ��򷵻ؿ�
	 * @param roleid
	 * @return Ȩ�޶����б�
	 * @throws MyException
	 */
	public List<SRoleModule> getSRoleModule(long roleid) throws MyException {
        if (roleid == 0) return null;
        HashMap<String, Object> map = new HashMap<String, Object>();
        String sql = "from SRoleModule a where 1=1 and a.roleid = :roleid";
        map.put("roleid", roleid);
        return query(sql, map);
    }
    
    /**
     * ����Id��ȡΨһ��ģ�����
     * @param moduleId
     * @return ģ�����
     * @throws MyException
     */
    public SModule getSModuleById(String moduleId) throws MyException {
        return (SModule)super.find(SModule.class, moduleId);
    }
    
	/**
	 * ��ȡģ���б�
	 * @return ģ���б�
	 * @throws MyException
	 */
	public List<SModule> getModules() throws MyException{
		StringBuffer sql = new StringBuffer();
		sql.append("from SModule order by sortnum");
		return query(sql.toString());
	}
    
    /**
     * ���ط�ҳ���û�����
     * @param pa ��ѯ���û�Bo����
     * @param us �û�Session
     * @param pageBean ��ҳ����
     * @return ��ҳ���û�����
     * @throws MyException
     */
    public Page getSUserList(UserParam pa,PageBean pageBean)throws MyException{
        StringBuffer queryString = new StringBuffer();
        queryString.append("from SUser a where 1=1 ");
        HashMap<String,Object> param =new HashMap<String,Object>();
        String username = pa.getUsername();
        String name = pa.getName();
        String depname = pa.getDepname();
        if (username != null && !"".equals(username.trim())) {
            queryString.append(" and  a.username like :username ");
            param.put("username", username + "%");
        }
        if (name != null && !"".equals(name.trim())) {
            queryString.append(" and  a.name like :name ");
            param.put("name", name + "%");
        }
        if (depname != null && !"".equals(depname.trim())) {
            queryString.append(" and  a.depname like :depname ");
            param.put("depname", depname + "%");
        }
        if (!StringUtils.isNullorSpace(pa.getAdmin())) {
            queryString.append(" and  a.admin like :admin ");
            param.put("admin", pa.getAdmin() + "%");
        }
        return getPageQuery(queryString.toString(),param,pageBean);
    }
    
    /**
     * �ж��û��Ƿ��д˽�ɫ
     * @param userid
     * @param roleid
     * @throws MyException
     * @return boolean
     */
    private boolean hasSRole(long userid,String roleid)throws MyException{
        String sql = "from SUserRole a where 1=1 and a.userid = :userid and a.roleid = :roleid";
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        map.put("roleid", roleid);
        List<SUserRole> mpl = super.query(sql, map);
        if(mpl!=null&&mpl.size()>0){
            return true;
        }else{
            return false;
        }
    }
    
    public static void main(String[] args) {
    	String a = null;
		File picfile = new File(a);
		if(picfile.exists())picfile.delete();
    }
}
