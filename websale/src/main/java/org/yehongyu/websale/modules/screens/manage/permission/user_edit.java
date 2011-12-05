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
import org.yehongyu.websale.db.po.mydb.SUser;
import org.yehongyu.websale.vo.manage.permission.PartValue;
import org.yehongyu.websale.vo.manage.permission.UserValue;

/**
 * 【类说明】用户新增和修改调用的页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:29:00
 */
public class user_edit extends SecureScreen {
    
    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");
        //获取参数
        long userid = data.getParameters().getLong("userid",0);
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);

        UserSession us = CommonBean.getUserSession(data);
        UserValue user = new UserValue();
        List<PartValue> part;
        data.getParameters().setProperties(user);
        
        if (userid==0) {
            context.put(AppConstants.VO_NAME_NAV,AppConstants.SPLIT_NAV + "新增人员");
            user.setAdmin(us.getUserName());
            part = permissionManager.getUserPartWithChecked(0,us.getUserName());
            user.setPart(part);
        } else {
            context.put(AppConstants.VO_NAME_NAV,AppConstants.SPLIT_NAV + "修改人员");
            SUser userpo = permissionManager.getSUserById(userid);
            user.setUserid(userpo.getId());
            user.setUsername(userpo.getUsername());
            user.setPassword(userpo.getPassword());
            user.setName(userpo.getName());
            user.setDepname(userpo.getDepartment());
            user.setPhone(userpo.getPhone());
            user.setEmail(userpo.getEmail());
            user.setMemo(userpo.getMemo());
            user.setAdmin(userpo.getAdmin());
            part = permissionManager.getUserPartWithChecked(userid,userpo.getAdmin());
            user.setPart(part);
        }
        context.put("user",user);
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_USER;
    }
}
