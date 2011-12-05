/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;


import javax.servlet.http.HttpSession;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.NoSecureScreen;

/**
 * 【类说明】登录页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:28:26
 */
public class login extends NoSecureScreen {

    public void doBuildTemplate(RunData data, Context context) throws Exception {
        data.setLayoutTemplate("NoLayout.html");
		HttpSession httpSession = data.getSession();
        if ("1".equals(data.getParameters().getString("logout"))) {
            httpSession.removeAttribute(AppConstants.SESSIONKEY);
            httpSession.invalidate();
        }

    }
}