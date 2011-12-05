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
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.po.mydb.SUser;


/**
 * ����˵�����û�ת��ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:29:14
 */
public class user_trans extends SecureScreen {

    @Override
    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        runData.setLayoutTemplate("NoLayout.html");
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
        String sourceaccount = runData.getParameters().getString("sourceaccount","");
        String destaccount = runData.getParameters().getString("destaccount","");

        context.put("destaccount",destaccount);
        context.put("sourceaccount",sourceaccount);
        if(sourceaccount.equals("")){
            context.put("error","δ����Դ�˺�");
        }else{
            if(!"".equals(destaccount)){
                SUser ml = permissionManager.getSUserListForTransfer(sourceaccount,destaccount);
                if(ml!=null){
                    context.put("destSUser",ml);
                }else{
                    context.put("error","��ѯ����"+destaccount+"�˺ţ�����˺�Ϊ"+sourceaccount+"���˺�,����˺�Ȩ��С��"+sourceaccount+"�˺�"); 
                }
            }else{
                List<SUser> destaccounts = permissionManager.getSUserListForTransfer(sourceaccount);
                if(destaccounts!=null&&destaccounts.size()>0){
                    context.put("dests",destaccounts); 
                }else{
                    context.put("error","δ�鵽��ת�Ƶ�Ŀ���˺�");
                }
            }
        }
    }
    

    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_USER;
    }

}
