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
 * 【类说明】用户转移页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:29:14
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
            context.put("error","未传入源账号");
        }else{
            if(!"".equals(destaccount)){
                SUser ml = permissionManager.getSUserListForTransfer(sourceaccount,destaccount);
                if(ml!=null){
                    context.put("destSUser",ml);
                }else{
                    context.put("error","查询不到"+destaccount+"账号，或此账号为"+sourceaccount+"子账号,或此账号权限小于"+sourceaccount+"账号"); 
                }
            }else{
                List<SUser> destaccounts = permissionManager.getSUserListForTransfer(sourceaccount);
                if(destaccounts!=null&&destaccounts.size()>0){
                    context.put("dests",destaccounts); 
                }else{
                    context.put("error","未查到可转移的目标账号");
                }
            }
        }
    }
    

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_USER;
    }

}
