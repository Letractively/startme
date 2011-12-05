/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.permission.PartParam;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;


/**
 * ����˵������ɫ�б�ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:28:50
 */
public class part_list extends SecureScreen{

	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setLayoutTemplate("Manage.html");
		PartParam pa = new PartParam();
        if(getParamSession(data)!=null){
            pa = (PartParam)getParamSession(data); 
        }
        data.getParameters().setProperties(pa);
        context.put(AppConstants.VO_NAME_PARAM,pa);
        setParamSession(pa,data);
        
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        if(getParamPageSession(data)!=null){
            pageBean = getParamPageSession(data); 
        }
        data.getParameters().setProperties(pageBean);
        context.put(AppConstants.VO_NAME_PAGE, pageBean);
        setParamPageSession(pageBean,data);
        
		PermissionManager permissionManager = (PermissionManager)
	    MyBusinessFactory.getManager(AppConstants.PermissionManager);	
		
		Page page = permissionManager.getSRoleList(pa,pageBean);
		context.put("results",page.getLstResult());
	}
    
    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
	protected String getModuleId() {
		return AppConstants.MOD_PUBLIC_PART;
	}
	
	
}
