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
import org.yehongyu.websale.db.po.mydb.SRole;
import org.yehongyu.websale.vo.manage.permission.ModuleValue;
import org.yehongyu.websale.vo.manage.permission.PartValue;


/**
 * 【类说明】角色新增和修改页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:28:43
 */
public class part_edit extends SecureScreen {

    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);

        long partid = data.getParameters().getLong("roleid",0);

        // 返回一个角色拥有的所有模块,如果是新建用户,模块按钮的checked属性为false
        List<ModuleValue> mods;
        PartValue part = new PartValue();
        data.getParameters().setProperties(part);
        if (partid==0) {
            context.put(AppConstants.VO_NAME_NAV, AppConstants.SPLIT_NAV + "新增角色");
            mods = permissionManager.getModules(0, false);
        } else {
            context.put(AppConstants.VO_NAME_NAV, AppConstants.SPLIT_NAV + "修改角色");
            mods = permissionManager.getModules(partid, true);
            SRole role = permissionManager.getSRole(partid);
            part.setRoleid(role.getId());
            part.setRolename(role.getRolename());
            part.setMemo(role.getMemo());
            part.setType(role.getType());
        }
        context.put("modules", mods);
        context.put("part", part);
    }
    
    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PART;
    }

}
