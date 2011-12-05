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
 * 【类说明】密码修改页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:26:44
 */
public class changepwd extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context)
            throws Exception {
    	runData.setLayoutTemplate("Manage.html");
        context.put(AppConstants.VO_NAME_MENU_NAV,AppConstants.SPLIT_NAV + "首页" + AppConstants.SPLIT_NAV + "修改密码");
        UserSession us = CommonBean.getUserSession(runData);
        context.put("user", us);
    }
    
    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
