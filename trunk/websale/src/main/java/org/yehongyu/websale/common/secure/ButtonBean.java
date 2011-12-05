/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyBusinessFactory;

/**
 * 【类说明】判断用户是否有此模块的按钮权限的类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午10:53:57
 */
public class ButtonBean {

    /** 用户会话对象 */
    private UserSession userSession;

    /** 要权限判断的模块编号 */
    private String moduleId;

    public ButtonBean(UserSession userSession, String moduleId) {
		this.userSession = userSession;
		this.moduleId = moduleId;
	}

    /**
	 * 【函数功能】是否有权限
	 * @param buttonId
	 * @return true or false
	 * @throws MyException
	 */
    public boolean canclick(String buttonId) throws MyException {
		PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
				.getManager(AppConstants.PermissionManager);
		return permissionManager.isButtonAuthend(this.moduleId, buttonId,
				this.userSession);
	}

    /**
	 * 【函数功能】是否有权限 
	 * 先判断目录登录用户是否有这个模块的权限,没有返回false 
	 * 有模块权限再判断相应的按钮位是否有权限
	 * @param buttonId
	 * @return true or false
	 * @throws MyException
	 */
    public boolean canclick(int buttonId) throws MyException {
		return canclick(String.valueOf(buttonId));
	}
}
