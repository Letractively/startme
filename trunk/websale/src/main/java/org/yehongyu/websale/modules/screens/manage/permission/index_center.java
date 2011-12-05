/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.SecureScreen;


/**
 * 【类说明】进入缺省首页
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:27:16
 */
public class index_center extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("Manage.html");
        context.put(AppConstants.VO_NAME_MENU_NAV, AppConstants.SPLIT_NAV + "首页");
    }

    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
