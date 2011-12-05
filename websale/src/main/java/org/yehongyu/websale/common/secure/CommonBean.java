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
 * 【类说明】本类用于获取UserSession对象，并添加一些其他的常用方法。
 * 本类不允许被继承，可以调用本类的静态方法，
 * 也可以通过getInstance()获取本类唯一实例调用本类非静态方法。
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:01:03
 */
public final class CommonBean {
    /** 该类唯一实例 */
    private static CommonBean commonBean = new CommonBean();

    /**
     * 私有构造方法，防止外部创建本类实例
     */
    private CommonBean() {
    }

    /**
     * 获取本类唯一实例
     * @return CommonBean实例
     */
    public static CommonBean getInstance() {
        return commonBean;
    }

    /**
     * 从Request中得到HttpSession获取UserSession
     * @param request HttpServletRequest对象
     * @return UserSession
     */
    public static UserSession getUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session
                .getAttribute(AppConstants.SESSIONKEY);
        return userSession;
    }

    /**
     * 从RunData中获取Request，然后从Request中得到HttpSession获取UserSession
     * @param data Turbine的RunData对象
     * @return UserSession
     */
    public static UserSession getUserSession(RunData data) {
        HttpServletRequest req = data.getRequest();
        return getUserSession(req);
    }
    
    /**
     * 【函数功能】
     * @param runData
     * @return 产品图片路径
     */
    public static String getProductImgpath(RunData runData){
    	return runData.getServletContext().getRealPath("/fileupload/productimg")+"/";
    }
    
    public static String getProductImgContextpath(RunData runData){
    	return runData.getContextPath()+"/fileupload/productimg"+"/";
    }
    

}
