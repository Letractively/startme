/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.web;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.web.ContentParam;
import org.yehongyu.websale.business.WebManager;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.db.MyBusinessFactory;


/**
 * 【类说明】网站管理，处理新增、修改页面提交的请求
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:02:10
 */
public class WebAction extends SecureAction {

    /**
     * 处理页面提交
     */
    public void doPerform(RunData runData, Context context) throws Exception {
    	
	}

    public void doEditboard(RunData runData,Context context)throws Exception{
    	ContentParam cp = new ContentParam();
    	runData.getParameters().setProperties(cp);
    	//过滤换行回车
    	cp.setContent(cp.getContent().replaceAll("\r",""));
    	cp.setContent(cp.getContent().replaceAll("\n",""));

        WebManager webManager = (WebManager) MyBusinessFactory
        .getManager(AppConstants.WebManager);
        String result = webManager.editBoard(cp);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,web,boardset.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "更新成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,web,boardset.html");
        }
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_WEB_BOARD;
    }

}
