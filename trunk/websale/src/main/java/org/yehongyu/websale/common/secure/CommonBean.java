/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.secure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.turbine.util.RunData;
import org.yehongyu.websale.AppConstants;

/**
 * ����˵�����������ڻ�ȡUserSession���󣬲����һЩ�����ĳ��÷�����
 * ���಻�����̳У����Ե��ñ���ľ�̬������
 * Ҳ����ͨ��getInstance()��ȡ����Ψһʵ�����ñ���Ǿ�̬������
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:01:03
 */
public final class CommonBean {
    /** ����Ψһʵ�� */
    private static CommonBean commonBean = new CommonBean();

    /**
     * ˽�й��췽������ֹ�ⲿ��������ʵ��
     */
    private CommonBean() {
    }

    /**
     * ��ȡ����Ψһʵ��
     * @return CommonBeanʵ��
     */
    public static CommonBean getInstance() {
        return commonBean;
    }

    /**
     * ��Request�еõ�HttpSession��ȡUserSession
     * @param request HttpServletRequest����
     * @return UserSession
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session
                .getAttribute(AppConstants.SESSIONKEY);
        return userSession;
    }

    /**
     * ��RunData�л�ȡRequest��Ȼ���Request�еõ�HttpSession��ȡUserSession
     * @param data Turbine��RunData����
     * @return UserSession
     */
    public static UserSession getUserSession(RunData data) {
        HttpServletRequest req = data.getRequest();
        return getUserSession(req);
    }
    
    /**
     * ���������ܡ�
     * @param runData
     * @return ��ƷͼƬ·��
     */
    public static String getProductImgpath(RunData runData){
    	return runData.getServletContext().getRealPath("/fileupload/productimg")+"/";
    }
    
    public static String getProductImgContextpath(RunData runData){
    	return runData.getContextPath()+"/fileupload/productimg"+"/";
    }
    

}
