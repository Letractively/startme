/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.web;

import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.WebManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.util.DateUtils;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * 【类说明】修改首页最新公告
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:29:00
 */
public class boardset extends SecureScreen {
    
    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");

        
        WebManager webManager = (WebManager) MyBusinessFactory
                .getManager(AppConstants.WebManager);
        context.put("item", webManager.getBoard());
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_WEB_BOARD;
    }
}
