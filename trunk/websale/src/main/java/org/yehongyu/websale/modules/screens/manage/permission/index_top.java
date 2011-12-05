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
 * ����˵����ͷ����ʾҳ
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:27:48
 */
public class index_top extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
        UserSession userSession = CommonBean.getUserSession(runData);
        context.put("usersession", userSession);
    }

    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }
}
