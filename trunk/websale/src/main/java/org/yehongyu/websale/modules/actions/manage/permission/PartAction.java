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
 * ����˵������ɫ���������������޸�ҳ���ύ������
 * @author yehongyu.org
 * @version 1.0 2007-11-28 ����07:11:41
 */
public class PartAction extends SecureAction {

    /**
     * ����ҳ���ύ
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
     * ���������ܡ���ӽ�ɫ
     * @param data
     * @param context
     * @throws Exception
     */
    public void doAdd(RunData data, Context context) throws Exception {
    	PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
//    	 ��Ϻò���,ģ��Ͱ�ť��Ȩ������
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
            context.put(AppConstants.VO_NAME_OPTINFO, "���ӳɹ���");
            data.getParameters().clear();
            data.setScreenTemplate("manage,permission,part_list.html");
        }
    }

    /**
     * �޸Ľ�ɫ
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doUpdate(RunData runData, Context context) throws Exception {
    	PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
        .getManager(AppConstants.PermissionManager);
//    	 ��Ϻò���,ģ��Ͱ�ť��Ȩ������
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
            context.put(AppConstants.VO_NAME_OPTINFO, "���³ɹ���");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,permission,part_list.html");
        }
    }

    /**
     * ɾ����ɫ�Ĳ���
     * @param data
     * @param context
     * @throws Exception
     */
    public void doDelete(RunData data, Context context) throws Exception {
        //���ò���֤�����Ϸ���
        long[] ids = data.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;

        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
        String result = permissionManager.RemovePart(ids);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "ɾ���ɹ���");
        }
        setTemplate(data, "manage,permission,part_list.html");
    }
    
    /**
     * ���������ܡ������ݿ����޸���ģ�飬����ͨ���˲���ˢ��ģ�黺��
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
     * ��map���Ӧ��key����action��ŵ�ǰ��ģ���ŵ�ֵ,�ñ�־λΪ1,���map������ݽṹ����
     * <p>
     * 001===>1010200001000(32λ) 002===>1100000000000(32λ)
     * </p>
     * 
     * @param map
     * @param actionid
     *            ��ť��� ģ����+3λ��ť��� ���еı�Ŷ��Ǵ�1��ʼ
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
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PART;
    }
}
