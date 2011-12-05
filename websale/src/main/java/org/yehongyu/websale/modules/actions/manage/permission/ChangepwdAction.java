/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.common.util.MyMD5;
import org.yehongyu.websale.db.MyBusinessFactory;

/**
 * ����˵���������޸����������
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:22:24
 */
public class ChangepwdAction extends SecureAction {
    
    public void doPerform(RunData data, Context context) throws Exception {

    }

    /**
     * �޸�����
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doChangepass(RunData runData, Context context) throws Exception {
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
        String oldpasswd = runData.getParameters().getString("oldpassword", "");
        String newpasswd = runData.getParameters().getString("newpassword", "");

        UserSession us = CommonBean.getUserSession(runData);
        String result = permissionManager.changeUserPasswd(oldpasswd, newpasswd, us);
        if(AppConstants.MANAGER_BACK_SUCCESS.equals(result)) {
            context.put(AppConstants.VO_NAME_OPTINFO, AppConstants.MANAGER_BACK_SUCCESS);
            us.setPassword(MyMD5.MD5Encode(newpasswd));
        } else {
        	context.put(AppConstants.VO_NAME_OPTINFO, result);
        }
        setTemplate(runData, "manage,permission,changepwd.html");
    }
    
    /**
     * ʵ�ָ���ĳ��󷽷���
     * ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }
}
