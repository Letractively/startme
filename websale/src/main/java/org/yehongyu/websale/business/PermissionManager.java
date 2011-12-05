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
 * ����˵����Ȩ�޹���Manager�ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:09:45
 */
public interface PermissionManager {

	/**
	 * ���������ܡ��û���¼,�����¼�ɹ�,����һ��UserSession����
	 * @param username ǰ̨�ύ���û���
	 * @param password �û�����
	 * @return �ɹ�����һ���Ự����, ʧ���򷵻�null
	 * @exception �ڵ�¼ʧ�ܵ�ʱ���׳��쳣,�쳣������ϸ�Ĵ�����Ϣ
	 */
	public abstract UserSession loginUser(String username, String password)
			throws MyException;

	/**
	 * ���������ܡ����ݼ���͸��˵�ȡ�Ӳ˵�
	 * @param session
	 * @param level
	 * @param parentModid
	 * @throws MyException
	 * @return Map
	 */
	public abstract List<MenuItemValue> getMenuByLevel(UserSession session,
			int level, String parentModid) throws MyException;

	/**
	 * ���������ܡ����ص�ǰ��¼�û��Ƿ��moduleIdָ���ģ����в���Ȩ��
	 * @param moduleId ģ����,���ģ����ͨ��ģ��,moduleId=""
	 * @param session ��¼�Ự
	 * @return ����и�ģ��Ĳ���Ȩ��,����true,����false
	 */
	public abstract boolean isModuleAuthend(String moduleId, UserSession session);

	/**
	 * ���������ܡ����ص�ǰ��¼�û��Ƿ��ĳ����ť�в���Ȩ�� �����actionId��int�͵İ�ť���,��1��ʼ���,1��ʾ��һ����ť
	 * @param moduleId ģ����
	 * @param actionId ��ť��� �������Ĳ�������,ֱ�Ӿͷ���false
	 * @param session �û��Ự
	 * @return true or false
	 */
	public abstract boolean isButtonAuthend(String moduleId, String actionId,
			UserSession session);

	/**
	 * ���������ܡ�����moduleId��ȡģ������м�����������
	 * @param moduleId
	 * @throws MyException
	 * @return String
	 */
	public abstract String getModuleLevelName(String moduleId)
			throws MyException;

	/**
	 * ���������ܡ��޸��û�����Ϊ������
	 * @param userName Ҫ�޸ĵ��û���
	 * @param newPasswd ������
	 * @param usrsession ��ǰ�����Ự
	 * @return true or false
	 */
	public abstract String changeUserPasswd(String userName, String newPasswd,
			UserSession usrsession) throws MyException;

	/**
	 * ���������ܡ������û��������û��־������
	 * @param userName �û���
	 * @return �û��־������,���û�з���null
	 */
	public abstract SUser getSUserByUsername(String userName)
			throws MyException;

	/**
	 * ���������ܡ������û�Id�����û��־������
	 * @param userName �û���
	 * @return �û��־������,���û�з���null
	 */
	public abstract SUser getSUserById(long userid) throws MyException;

	/**
	 * ���������ܡ����������û�����,ֻ�ܲ�ѯ�Լ��˺��µ����˺š����򷵻ؿ�
	 * @param pa ��ѯ����
	 * @return �����б�
	 */
	public abstract Page getUserList(UserParam pa, UserSession us,
			PageBean pageBean) throws MyException;

	/**
	 * ���������ܡ�����һ����ɫ
	 * @param roleid ��ɫid
	 * @return SRole�־������
	 */
	public abstract SRole getSRole(long roleid) throws MyException;

	/**
	 * ���������ܡ��������н�ɫ
	 * @return ��ɫ�����б�
	 */
	public abstract List<SRole> getSRoleList() throws MyException;

	/**
	 * ���������ܡ����ط�ҳ�Ľ�ɫ����
	 * @param pa ��ѯ���û�Bo����
	 * @param pageBean ��ҳ����
	 * @return ��ҳ���û�����
	 */
	public abstract Page getSRoleList(PartParam pa, PageBean pageBean)
			throws MyException;

	/**
	 * ���������ܡ�����һ���û�
	 * @param user Ҫ���ӵ��û�����
	 * @param usrsession ��¼�û�����
	 * @return String ���ص���Ϣ
	 */
	public abstract String AddUser(UserValue user) throws MyException;

	/**
	 * ���������ܡ�����һ���û�����Ϣ Ҫ�Ȱ��û���Ȩ����գ����²���
	 * @param user �û�����
	 * @return String ���ص���Ϣ
	 */
	public abstract String UpdateUser(UserValue user) throws MyException;

	/**
	 * ���������ܡ�����ɾ���û�,��ɾ����ص�Ȩ�����ݡ�ִ�и÷���������ɾ�����ݿ�
	 * @param userids �û���id
	 * @return String ���ص���Ϣ
	 */
	public abstract String RemoveUser(long[] userids) throws MyException;

	/**
	 * ���������ܡ�����һ����ɫ
	 * @param part ��ɫ����
	 * @param checkedmodules �û�ѡ�е�ģ��
	 * @return ������Ϣ
	 */
	public abstract String AddPart(PartValue part, Map<String,String> checkedmodules)throws MyException;
    

	/**
	 * ���������ܡ����±���һ���û���ɫ����Ϣ �����û���ʱ��,Ҫ�Ȱ��û���Ȩ�����,���²����µ�Ȩ�� �û�ѡ�е�ģ���б�, �Լ���ѡ�����ģ��Ĳ�����ť
	 * @param part ��ɫ�־������
	 * @param checkedmodules
	 *            ѡ�е�ģ�� Ԫ��Ϊһ��mapֵ���� key ===>modid; //ģ���� value ===> actionids
	 *            //���ģ�����û���ѡ�İ�ť
	 * @return true ���³ɹ�
	 */
	public abstract String UpdatePart(PartValue part, Map<String,String> checkedmodules)throws MyException;

	/**
	 * ���������ܡ�����ɾ����ɫ
	 * @param partids Ҫɾ���Ľ�ɫ����
	 * @return String ���ص���Ϣ
	 */
	public abstract String RemovePart(long[] partids)throws MyException;

	/**
	 * ���������ܡ��������ͣ����ظ��˺�ӵ�еĽ�ɫ�б�,���û����еĽ�ɫ,checked����Ϊtrue Ԫ��ΪPartValue
	 * @param userid ���˺��û�Id,���Ϊ0�򷵻ظ��˺ŵ�����δchecked��ɫ��
	 * @param admin ���˺��û���
	 * @return List Ԫ��ΪPartValue ����û����иý�ɫ,checked=true
	 */
	public abstract List<PartValue> getUserPartWithChecked(long userid,
			String admin) throws MyException;

	/**
	 * ���������ܡ�ת���˺ţ���Դ�˺�ֱ�����˺ŵĸ��˺����Ը�ΪĿ���˺�
	 * @param source
	 * @param dest
	 * @throws MyException
	 * @return boolean
	 */
	public abstract boolean transAccount(String source, String dest)
			throws MyException;

	/**
	 * ���������ܡ�����Դ�˺�ȡ���п�ת�Ƶ��˺ţ���ΪԴ�˺ŵ����˺ţ���Ȩ��Ҫ����Դ�˺ţ�
	 * @param sourceAccount
	 * @throws MyException
	 * @return List<SUser>
	 */
	public abstract List<SUser> getSUserListForTransfer(String sourceAccount)
			throws MyException;

	/**
	 * ���������ܡ�����Դ�˺Ų�ѯָ��Ŀ���˺��Ƿ�����ҿ�ת��(��ΪԴ�˺ŵ����˺���Ȩ�޴���Դ�˺�)������Ŀ���˺ŵĶ��󣬷��򷵻�null
	 * @param sourceAccount
	 * @param destAccount
	 * @throws MyException
	 * @return SUser
	 */
	public abstract SUser getSUserListForTransfer(String sourceAccount,
			String destAccount) throws MyException;

	/**
	 * ���������ܡ�����һ����ɫӵ�е�ģ��,��������᷵������ģ��,��Ȩ�޵�ģ��checked����Ϊtrue
	 * ���������ť��Ȩ��,checked����Ϊtrue,����Ϊfalse
	 * @param roleid ��ɫ���
	 * @param withchecked �Ƿ񷵻�checked������,true ���ش���checkedֵ��ģ���б� false ��������ģ��
	 * @return List�����Ԫ��ΪModuleValue
	 */
	public abstract List<ModuleValue> getModules(long roleid,
			boolean withchecked) throws MyException;

	/**
	 * ���������ܡ�ˢ�»������ݣ�����ģ�顢��ɫ��ģ���б������Ǽ��ض���Ϊ���´η���ʱ������ȡ���ݿ⡣
	 * @throws MyException
	 * @return boolean
	 */
	public abstract boolean refreshCacheData() throws MyException;
}
