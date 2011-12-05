/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import org.apache.turbine.modules.screens.VelocitySecureScreen;
import org.apache.turbine.services.velocity.TurbineVelocity;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.util.Convert;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.PageBean;

/**
 * 【类说明】所有需要权限验证的页面类都要继承的抽象类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:00:36
 */
public abstract class SecureScreen extends VelocitySecureScreen {

    protected boolean isAuthorized(RunData runData) throws Exception {
    	 
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
        Context context = TurbineVelocity.getContext(runData);

        UserSession userSession = CommonBean.getUserSession(runData);
        String moduleId = Convert.getString(this.getModuleId());
        // 把ButtonBean设置到Context中,以方便前台页面判断是否有权限
        ButtonBean buttonBean = new ButtonBean(userSession, moduleId);
        context.put("button", buttonBean);
        context.put(AppConstants.VO_NAME_MENU_NAV,permissionManager.getModuleLevelName(moduleId));
        if (userSession == null) {
            context.put(AppConstants.VO_NAME_RELOGIN, "无法取到UserSession对象,可能是您的会话已过期！");
            runData.setScreenTemplate("manage,permission,loginagain.html");
            return false;
        } else {
            // 判断是否有这个模块的权限,没有直接退到没有权限页面
            boolean authened = permissionManager.isModuleAuthend(moduleId, userSession);
            if (!authened) {
                context.put(AppConstants.VO_NAME_RELOGIN, "您访问的页面没有权限,请联系管理员开通模块"+moduleId+"（"+context.get(AppConstants.VO_NAME_MENU_NAV)+"）的访问权限！");
                runData.setScreenTemplate("manage,permission,nopermission.html");
                return false;
            }
        }
        return true;
    }
    
    protected Object getParamSession(RunData runData){
        return runData.getSession().getAttribute(AppConstants.VO_NAME_PARAM + getModuleId());
    }
    
    protected void setParamSession(Object paramObj, RunData runData) {
        runData.getSession().setAttribute(AppConstants.VO_NAME_PARAM + getModuleId(), paramObj);
    }
    
    protected PageBean getParamPageSession(RunData runData){
        return (PageBean)runData.getSession().getAttribute(AppConstants.VO_NAME_PAGE + getModuleId());
    }
    
    protected void setParamPageSession(PageBean pageBean, RunData runData) {
        runData.getSession().setAttribute(AppConstants.VO_NAME_PAGE + getModuleId(), pageBean);
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
