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
 * ����˵������վ���������������޸�ҳ���ύ������
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:02:10
 */
public class WebAction extends SecureAction {

    /**
     * ����ҳ���ύ
     */
    public void doPerform(RunData runData, Context context) throws Exception {
    	
	}

    public void doEditboard(RunData runData,Context context)throws Exception{
    	ContentParam cp = new ContentParam();
    	runData.getParameters().setProperties(cp);
    	//���˻��лس�
    	cp.setContent(cp.getContent().replaceAll("\r",""));
    	cp.setContent(cp.getContent().replaceAll("\n",""));

        WebManager webManager = (WebManager) MyBusinessFactory
        .getManager(AppConstants.WebManager);
        String result = webManager.editBoard(cp);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,web,boardset.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "���³ɹ���");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,web,boardset.html");
        }
    }

    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_WEB_BOARD;
    }

}
