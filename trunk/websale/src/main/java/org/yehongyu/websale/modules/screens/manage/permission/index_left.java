/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.vo.manage.permission.MenuItemValue;

/**
 * 【类说明】左侧导航菜单
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:27:24
 */
public class index_left extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        
        runData.setLayoutTemplate("NoLayout.html");

        UserSession userSession = CommonBean.getUserSession(runData);
       
            PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
            .getManager(AppConstants.PermissionManager);
            List<MenuItemValue> list = permissionManager.getMenuByLevel(userSession,1,"0");
            context.put("menu", list);
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }
}
