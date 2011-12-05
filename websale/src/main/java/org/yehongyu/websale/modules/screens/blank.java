package org.yehongyu.websale.modules.screens;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.SecureScreen;

/**
 * �����ʱ����ת�Ŀհ�ҳ
 * ***��˵��***
 * @author yehy
 * @version 4.0 2007-4-13 17:25:17
 * @since 4.0
 */
public class blank extends SecureScreen {

    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
    }
    
    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

}
