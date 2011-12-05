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
 * ����˵����Ȩ�޹���DAO�ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:15:20
 */
public interface PermissionDao extends MyDaoManager {

    /**
     * �����û�ID����Ψһ�û�
     * @param userid
     * @return �û�����
     * @throws MyException
     */
    public SUser getSUserById(long userid) throws MyException;
    
    /**
     * �����û�������Ψһ�û����û���Ϊ�ջ��ѯ�����ڻ����������¼���򷵻ؿ�
     * @param username
     * @return �û�����
     * @throws MyException
     */
    public SUser getSUserByUsername(String username);
    
    /**
     * ���������û�����
     * @return �����û�����
     * @throws MyException
     */
    public List<SUser> getSUsers() throws MyException;
    
    /**
     * ���ݸ��˺ŷ�����ֱ�����˺Ŷ���
     * @param admin
     * @throws MyException
     * @return List<SUser>
     */
    public List<SUser> getSUserNext(String admin)throws MyException;
    
    /**
     * ����ĳ�û��µ������û�����
     * @param admin ���˺�
     * @return �����û�����
     * @throws MyException
     */
    public Map<String,SUser> getSUserAll(String admin)throws MyException;
    
    /**
     * ͨ��JDBCȡ��Ա��ȥ���˸����û����鲻�����˺ŵ������
     * @param pa
     * @param pageBean
     * @throws MyException
     * @return Page
     */
    public Page getSUserList(UserParam pa,PageBean pageBean)throws MyException;

    /**
     * �����û�Id��ȡ�û���ɫȨ���б��û���Ϊ�գ��򷵻ؿ�
     * @param userid
     * @return �û�����Ȩ���б�
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByUserid(long userid) throws MyException;
    
    /**
     * ���ݽ�ɫId��ȡ��ɫ�û�Ȩ���б���ɫΪ�գ��򷵻ؿ�
     * @param roleid
     * @return �û���ɫȨ���б�
     * @throws MyException
     */
    public List<SUserRole> getSUserRoleByRoleid(long roleid) throws MyException;

    /**
     * ����Id��ȡΨһ�Ľ�ɫ����
     * @param partId
     * @return ��ɫ����
     * @throws MyException
     */
    public SRole getSRoleById(long roleid) throws MyException;
    
    /**
     * ���ݽ�ɫ������Ψһ��ɫ����ɫ��Ϊ�ջ��ѯ�����ڻ����������¼���򷵻ؿ�
     * @param username
     * @return ��ɫ����
     * @throws MyException
     */
    public SRole getSRoleByRolename(String rolename);
    
    /**
     * ���ؽ�ɫ�б�
     * @return ���ؽ�ɫ�б�
     * @throws MyException
     */
    public List<SRole> getSRoles() throws MyException;
    
    /**
     * �����û�����ȡ�û����еĽ�ɫ�б�
     * @param username �û���
     * @throws MyException
     * @return List<SRole>
     */
    public List<SRole> getSRolesByUsername(String username)throws MyException;
    
    /**
     * ���ط�ҳ�Ľ�ɫ����
     * @param pa ��ѯ���û�Bo����
     * @param pageBean ��ҳ����
     * @return ��ҳ���û�����
     * @throws MyException
     */
    public Page getSRoleList(PartParam pa,PageBean pageBean) throws MyException;
    
	/**
	 * ���ݽ�ɫId��ѯģ��Ͱ�ťȨ�ޣ���ɫΪ�գ��򷵻ؿ�
	 * @param roleid
	 * @return Ȩ�޶����б�
	 * @throws MyException
	 */
	public List<SRoleModule> getSRoleModule(long roleid) throws MyException;
    
    /**
     * ����Id��ȡΨһ��ģ�����
     * @param moduleId
     * @return ģ�����
     * @throws MyException
     */
    public SModule getSModuleById(String moduleId) throws MyException;
    
	/**
	 * ��ȡģ���б�
	 * @return ģ���б�
	 * @throws MyException
	 */
	public List<SModule> getModules() throws MyException;
	
}
