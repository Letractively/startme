/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.permission.UserParam;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.SRole;

/**
 * ����˵�����û��б�ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:29:06
 */
public class user_list extends SecureScreen {

    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");
//    	���ò���
        UserParam p = new UserParam();
        if(getParamSession(data)!=null){
            p = (UserParam)getParamSession(data); 
        }
        data.getParameters().setProperties(p);
        context.put(AppConstants.VO_NAME_PARAM, p);
        setParamSession(p,data);
        
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        if(getParamPageSession(data)!=null){
            pageBean = getParamPageSession(data); 
            pageBean.setActionType(0);
        }
        data.getParameters().setProperties(pageBean);
        context.put(AppConstants.VO_NAME_PAGE, pageBean);
        setParamPageSession(pageBean,data);
        
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
        UserSession us = CommonBean.getUserSession(data);
        
        Page page = permissionManager.getUserList(p.clone(), us, pageBean);
        context.put("results", page.getLstResult());
        List<SRole> partlist = permissionManager.getSRoleList();
        context.put("partlist", partlist);
    }

    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_USER;
    }
}
