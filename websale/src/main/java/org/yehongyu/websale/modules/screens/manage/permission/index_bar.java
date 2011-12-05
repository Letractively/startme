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
 * 【类说明】切换栏页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:26:53
 */
public class index_bar extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
    }

    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
