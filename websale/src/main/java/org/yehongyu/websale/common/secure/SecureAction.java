/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import org.apache.turbine.modules.actions.VelocitySecureAction;
import org.apache.turbine.services.velocity.TurbineVelocity;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.util.Convert;
import org.yehongyu.websale.db.MyBusinessFactory;

/**
 * 【类说明】所有需要权限验证的动作类都要继承的抽象类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午10:59:50
 */
public abstract class SecureAction extends VelocitySecureAction {

    protected boolean isAuthorized(RunData runData) throws Exception {
    	Context context = TurbineVelocity.getContext(runData);
        // 以下注释掉的为了去掉session验证
        UserSession userSession = CommonBean.getUserSession(runData);

        if (userSession == null) {
            context.put(AppConstants.VO_NAME_RELOGIN, "无法取到UserSession对象,可能是你的会话已过期");
            runData.setScreenTemplate("manage,permission,loginagain.html");
            return false;
        } else {
            String moduleId = Convert.getString(this.getModuleId());
            // 判断是否有这个模块的权限,没有直接退到没有权限页面
            PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                    .getManager(AppConstants.PermissionManager);
            boolean authened = permissionManager.isModuleAuthend(moduleId, userSession);

            if (!authened) {
                context.put(AppConstants.VO_NAME_RELOGIN, "您访问的页面没有权限,请联系管理员开通模块"+moduleId+"（"+context.get(AppConstants.VO_NAME_MENU_NAV)+"）的访问权限！");
                runData.setScreenTemplate("manage,permission,nopermission.html");
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * 返回一个具体模块的编号,每个模块的具体编号请查询m_module表 
     * 要权限控制的模块必须返回这个模块编号
     * <b>继承子类必须重载该方法,返回当前模块权限</b>
     * @return 返回要权限控制的模块编号
     */
    protected abstract String getModuleId();
}
