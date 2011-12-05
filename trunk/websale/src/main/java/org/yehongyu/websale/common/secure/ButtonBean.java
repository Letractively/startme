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
 * ����˵�����ж��û��Ƿ��д�ģ��İ�ťȨ�޵���
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:53:57
 */
public class ButtonBean {

    /** �û��Ự���� */
    private UserSession userSession;

    /** ҪȨ���жϵ�ģ���� */
    private String moduleId;

    public ButtonBean(UserSession userSession, String moduleId) {
		this.userSession = userSession;
		this.moduleId = moduleId;
	}

    /**
	 * ���������ܡ��Ƿ���Ȩ��
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
	 * ���������ܡ��Ƿ���Ȩ�� 
	 * ���ж�Ŀ¼��¼�û��Ƿ������ģ���Ȩ��,û�з���false 
	 * ��ģ��Ȩ�����ж���Ӧ�İ�ťλ�Ƿ���Ȩ��
	 * @param buttonId
	 * @return true or false
	 * @throws MyException
	 */
    public boolean canclick(int buttonId) throws MyException {
		return canclick(String.valueOf(buttonId));
	}
}
