/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.velocity.TurbineVelocity;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.util.Convert;
import org.yehongyu.websale.db.MyBusinessFactory;

/**
 * ����˵����������ҪȨ����֤�Ķ����඼Ҫ�̳еĳ�����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:59:50
 */
public abstract class SecureAction extends VelocitySecureAction {

    protected boolean isAuthorized(RunData runData) throws Exception {
    	Context context = TurbineVelocity.getContext(runData);
        // ����ע�͵���Ϊ��ȥ��session��֤
        UserSession userSession = CommonBean.getUserSession(runData);

        if (userSession == null) {
            context.put(AppConstants.VO_NAME_RELOGIN, "�޷�ȡ��UserSession����,��������ĻỰ�ѹ���");
            runData.setScreenTemplate("manage,permission,loginagain.html");
            return false;
        } else {
            String moduleId = Convert.getString(this.getModuleId());
            // �ж��Ƿ������ģ���Ȩ��,û��ֱ���˵�û��Ȩ��ҳ��
            PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                    .getManager(AppConstants.PermissionManager);
            boolean authened = permissionManager.isModuleAuthend(moduleId, userSession);

            if (!authened) {
                context.put(AppConstants.VO_NAME_RELOGIN, "�����ʵ�ҳ��û��Ȩ��,����ϵ����Ա��ͨģ��"+moduleId+"��"+context.get(AppConstants.VO_NAME_MENU_NAV)+"���ķ���Ȩ�ޣ�");
                runData.setScreenTemplate("manage,permission,nopermission.html");
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * ����һ������ģ��ı��,ÿ��ģ��ľ��������ѯm_module�� 
     * ҪȨ�޿��Ƶ�ģ����뷵�����ģ����
     * <b>�̳�����������ظ÷���,���ص�ǰģ��Ȩ��</b>
     * @return ����ҪȨ�޿��Ƶ�ģ����
     */
    protected abstract String getModuleId();
}
