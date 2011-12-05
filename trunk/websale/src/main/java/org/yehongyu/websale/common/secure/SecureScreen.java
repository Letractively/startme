/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.services.velocity.TurbineVelocity;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.util.Convert;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.PageBean;

/**
 * ����˵����������ҪȨ����֤��ҳ���඼Ҫ�̳еĳ�����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:00:36
 */
public abstract class SecureScreen extends VelocitySecureScreen {

    protected boolean isAuthorized(RunData runData) throws Exception {
    	 
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
        Context context = TurbineVelocity.getContext(runData);

        UserSession userSession = CommonBean.getUserSession(runData);
        String moduleId = Convert.getString(this.getModuleId());
        // ��ButtonBean���õ�Context��,�Է���ǰ̨ҳ���ж��Ƿ���Ȩ��
        ButtonBean buttonBean = new ButtonBean(userSession, moduleId);
        context.put("button", buttonBean);
        context.put(AppConstants.VO_NAME_MENU_NAV,permissionManager.getModuleLevelName(moduleId));
        if (userSession == null) {
            context.put(AppConstants.VO_NAME_RELOGIN, "�޷�ȡ��UserSession����,���������ĻỰ�ѹ��ڣ�");
            runData.setScreenTemplate("manage,permission,loginagain.html");
            return false;
        } else {
            // �ж��Ƿ������ģ���Ȩ��,û��ֱ���˵�û��Ȩ��ҳ��
            boolean authened = permissionManager.isModuleAuthend(moduleId, userSession);
            if (!authened) {
                context.put(AppConstants.VO_NAME_RELOGIN, "�����ʵ�ҳ��û��Ȩ��,����ϵ����Ա��ͨģ��"+moduleId+"��"+context.get(AppConstants.VO_NAME_MENU_NAV)+"���ķ���Ȩ�ޣ�");
                runData.setScreenTemplate("manage,permission,nopermission.html");
                return false;
            }
        }
        return true;
    }
    
    protected Object getParamSession(RunData runData){
        return runData.getSession().getAttribute(AppConstants.VO_NAME_PARAM + getModuleId());
    }
    
    protected void setParamSession(Object paramObj, RunData runData) {
        runData.getSession().setAttribute(AppConstants.VO_NAME_PARAM + getModuleId(), paramObj);
    }
    
    protected PageBean getParamPageSession(RunData runData){
        return (PageBean)runData.getSession().getAttribute(AppConstants.VO_NAME_PAGE + getModuleId());
    }
    
    protected void setParamPageSession(PageBean pageBean, RunData runData) {
        runData.getSession().setAttribute(AppConstants.VO_NAME_PAGE + getModuleId(), pageBean);
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
