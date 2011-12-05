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
 * 【类说明】头部显示页
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:27:48
 */
public class index_top extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
        UserSession userSession = CommonBean.getUserSession(runData);
        context.put("usersession", userSession);
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }
}
