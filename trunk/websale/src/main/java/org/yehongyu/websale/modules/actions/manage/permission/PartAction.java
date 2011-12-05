/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.permission;

import java.util.HashMap;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.vo.manage.permission.PartValue;

/**
 * 【类说明】角色管理，处理新增、修改页面提交的请求
 * @author yehongyu.org
 * @version 1.0 2007-11-28 下午07:11:41
 */
public class PartAction extends SecureAction {

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
     * 【函数功能】添加角色
     * @param data
     * @param context
     * @throws Exception
     */
    public void doAdd(RunData data, Context context) throws Exception {
    	PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
//    	 组合好参数,模块和按钮的权限设置
        PartValue part = new PartValue();
        data.getParameters().setProperties(part);

        String[] moduleids = data.getParameters().getStrings("moduleid");
        String[] actionids = data.getParameters().getStrings("actionid");

        Map<String, String> map = new HashMap<String, String>();
        if (moduleids != null) {
            for (int i = 0; i < moduleids.length; i++) {
                map.put(moduleids[i], "00000000000000000000000000000000");
            }
        }

        if (actionids != null) {
            for (int i = 0; i < actionids.length; i++) {
                checkAction(map, actionids[i]);
            }
        }
        
        String result = permissionManager.AddPart(part,map);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            data.setScreenTemplate("manage,permission,part_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "增加成功！");
            data.getParameters().clear();
            data.setScreenTemplate("manage,permission,part_list.html");
        }
    }

    /**
     * 修改角色
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doUpdate(RunData runData, Context context) throws Exception {
    	PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
//    	 组合好参数,模块和按钮的权限设置
    	PartValue part = new PartValue();
    	runData.getParameters().setProperties(part);

        String[] moduleids = runData.getParameters().getStrings("moduleid");
        String[] actionids = runData.getParameters().getStrings("actionid");

        Map<String, String> map = new HashMap<String, String>();
        if (moduleids != null) {
            for (int i = 0; i < moduleids.length; i++) {
                map.put(moduleids[i], "00000000000000000000000000000000");
            }
        }

        if (actionids != null) {
            for (int i = 0; i < actionids.length; i++) {
                checkAction(map, actionids[i]);
            }
        }
        
        String result = permissionManager.UpdatePart(part,map);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,permission,part_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "更新成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,permission,part_list.html");
        }
    }

    /**
     * 删除角色的操作
     * @param data
     * @param context
     * @throws Exception
     */
    public void doDelete(RunData data, Context context) throws Exception {
        //设置并验证参数合法性
        long[] ids = data.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;

        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
        String result = permissionManager.RemovePart(ids);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "删除成功！");
        }
        setTemplate(data, "manage,permission,part_list.html");
    }
    
    /**
     * 【函数功能】在数据库中修改完模块，可以通过此操作刷新模块缓存
     * @param data
     * @param context
     * @throws Exception
     */
    public void doRefresh(RunData data,Context context)throws Exception{
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
            .getManager(AppConstants.PermissionManager);
        boolean isrefresh = permissionManager.refreshCacheData();
        if(isrefresh){
            context.put("refresh", "true");
        }
        data.setScreenTemplate("manage,permission,part_edit.html");
    }

    /**
     * 把map里对应的key等于action编号的前面模块编号的值,置标志位为1,这个map里的数据结构如下
     * <p>
     * 001===>1010200001000(32位) 002===>1100000000000(32位)
     * </p>
     * 
     * @param map
     * @param actionid
     *            按钮编号 模块编号+3位按钮编号 所有的编号都是从1开始
     * @return
     */
    private Map<String, String> checkAction(Map<String, String> map, String actionid) {
        if (actionid == null || "".equals(actionid))
            return map;
        String modid, actionshortid;
        int shortid;
        modid = actionid.substring(0, actionid.length() - 3);
        actionshortid = actionid.substring(actionid.length() - 3);
        shortid = Integer.parseInt(actionshortid) - 1;
        if (map.containsKey(modid)) {
            String s = (String) map.get(modid);
            char[] chs = s.toCharArray();
            chs[shortid] = '1';
            s = new String(chs);
            map.put(modid, s);
        } else {
            char[] chs = new char[32];
            for (int i = 0; i < 32; i++) {
                chs[i] = '0';
            }
            chs[shortid] = '1';
            map.put(modid, new String(chs));
        }
        return map;
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PART;
    }
}
