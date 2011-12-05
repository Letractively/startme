/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.secure.UserSession;

/**
 * ����˵���������޸�ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:26:44
 */
public class changepwd extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context)
            throws Exception {
    	runData.setLayoutTemplate("Manage.html");
        context.put(AppConstants.VO_NAME_MENU_NAV,AppConstants.SPLIT_NAV + "��ҳ" + AppConstants.SPLIT_NAV + "�޸�����");
        UserSession us = CommonBean.getUserSession(runData);
        context.put("user", us);
    }
    
    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
