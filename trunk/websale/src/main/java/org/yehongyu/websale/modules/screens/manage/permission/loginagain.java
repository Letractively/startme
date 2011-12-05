/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.common.secure.NoSecureScreen;

/**
 * 【类说明】重新登录页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:28:35
 */
public class loginagain extends NoSecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
        context.put("isfirst", runData.getParameters().getString("isfirst",""));
        context.put("reLoginError", runData.getParameters().getString("reLoginError",""));
    }

}
