/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.vo.manage.permission.PartValue;
import org.yehongyu.websale.vo.manage.permission.UserValue;


/**
 * 
 * 【类说明】用户管理，处理新增、修改页面提交的请求
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:23:34
 */
public class UserAction extends SecureAction {

    /**
     * 处理页面提交
     */
    public void doPerform(RunData runData, Context context) throws Exception {
        String dowhat = runData.getParameters().getString("do");

        if (dowhat == null || "".equals(dowhat))
            return;

        if ("add".equals(dowhat)) {
            doAdd(runData, context);
        } else if ("update".equals(dowhat)) {
            doUpdate(runData, context);
        } else if ("delete".equals(dowhat)) {
            doDelete(runData, context);
        }
    }

    /**
     * 添加用户
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doAdd(RunData runData, Context context) throws Exception {
    	//设置并验证参数合法性
    	UserValue param = new UserValue();
        setParam(runData, param);
        UserSession us = CommonBean.getUserSession(runData);
        param.setAdmin(us.getUserName());
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
        String result = permissionManager.AddUser(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,permission,user_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "增加成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,permission,user_list.html");
        }
    }

    /**
     * 修改用户
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doUpdate(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        UserValue param = new UserValue();
        setParam(runData, param);
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
        String result = permissionManager.UpdateUser(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,permission,user_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "更新成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,permission,user_list.html");
        }
    }
    
    /**
     * 转移账号
     * @param runData
     * @param context
     * @throws Exception
     * @return void
     */
    public void doTransfer(RunData runData, Context context) throws Exception{
        String dest = runData.getParameters().getString("destml","");
        String source = runData.getParameters().getString("sourceaccount","");
        if("".equals(dest)||"".equals(source)){
            context.put("transerror","转移失败，源账号或目标账号传入为空！");
        }else{
            PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
            .getManager(AppConstants.PermissionManager);
            if(!permissionManager.transAccount(source,dest)){
                context.put("transerror","转移失败，源账号或目标账号不存在，或目标账号权限小于源账号"); 
            }else{
                context.put("transerror","转移成功"); 
            }
        }
        runData.setScreenTemplate("manage,permission,user_trans.html");
    }

    /**
     * 删除用户
     * 
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doDelete(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        long[] userids = runData.getParameters().getLongs("ids");
        if(userids==null||userids.length==0)return;
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
        String result = permissionManager.RemoveUser(userids);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "删除成功！");
        }
        runData.setScreenTemplate("manage,permission,user_list.html");
    }

    /**
     * 添加修改用户时，设置用户参数。
     * @param runData
     * @param param
     */
    private void setParam(RunData runData, UserValue param) {
        param.setUserid(runData.getParameters().getLong("userid",0));
        param.setUsername(runData.getParameters().getString("username",""));
        param.setPassword(runData.getParameters().getString("password",""));
        param.setName(runData.getParameters().getString("name",""));
        param.setDepname(runData.getParameters().getString("depname",""));
        param.setPhone(runData.getParameters().getString("phone",""));
        param.setEmail(runData.getParameters().getString("email",""));
        param.setMemo(runData.getParameters().getString("memo",""));
        param.setAdmin(runData.getParameters().getString("admin",""));
        long[] partid = runData.getParameters().getLongs("partid");
        // 处理角色
        List<PartValue> l = new ArrayList<PartValue>();
        l = new ArrayList<PartValue>();
            if (partid != null) {
                for (int i = 0; i < partid.length; i++) {
                    PartValue pv = new PartValue();
                    pv.setRoleid(partid[i]);
                    l.add(pv);
                }
                param.setPart(l);
        }
        
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_USER;
    }

}
