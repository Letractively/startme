package org.yehongyu.websale.modules.screens;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.SecureScreen;

/**
 * 出错的时候中转的空白页
 * ***类说明***
 * @author yehy
 * @version 4.0 2007-4-13 17:25:17
 * @since 4.0
 */
public class blank extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
    }
    
    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
