/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.permission;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.PermissionManager;
import org.yehongyu.websale.common.exception.Msg;
import org.yehongyu.websale.common.exception.MsgCode;
import org.yehongyu.websale.common.secure.NoSecureAction;
import org.yehongyu.websale.common.secure.UserSession;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.MyBusinessFactory;


/**
 * 【类说明】 后台登录处理Action
 * 用户名密码约束：
 * 用户名密码长度不能超过20位
 * 用户名密码中不得出来" and"、"and "这样的字符串
 * 出现被认为是非法Sql注入,拒绝登录
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:22:49
 */
public class Login extends NoSecureAction {
    
    /**
     * 用户登录校验请求
     */
    public void doPerform(RunData runData, Context context) throws MyException {
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
//        HttpServletRequest request = runData.getRequest();
//        HttpSession httpSession = request.getSession();
        
//        //三次以上需要验证码
//        int loginnumforyz = 3;  //允许三次不输入验证码。
//        long yzcount = runData.getParameters().getLong("yzcount",0);
//        yzcount++;
//        context.put("yzcount", yzcount);
//        if(yzcount>=loginnumforyz){
//            context.put("showyz", "true");
//        }else{
//            context.put("showyz", "false");
//        }
//        if(yzcount>loginnumforyz){
//            String verifyCode = (String) httpSession
//                    .getAttribute(AppConstants.VERIFYCODE);
//            String yz = runData.getParameters().getString("yzcode", "");

            // 验证码校验
//            if (!yz.equals(verifyCode)) {
//                context.put("yzOK", "0"); // 未通过，则置为0
//                setTemplate(runData, "manage,permission,login.html");
//                return;
//            }
//        }
        
        //参数校验
        if (!valid(runData)) {
            context.put("iserror", "1");
            context.put("errorinfo", Msg.get(MsgCode.MMT_MA_COMMON_00002));
            setTemplate(runData, "login.html");
            return;
        }

        String username = runData.getParameters().getString("username","");
        String password = runData.getParameters().getString("password","");
        UserSession userSession = permissionManager.loginUser(username, password);
        if (userSession == null) {
            // 如果session不存在,如用户名不存在,密码不正确等
            context.put("iserror", "1");
            context.put("errorinfo", "用户名+密码不正确");
            context.put("username", username);
            context.put("password", password);
            setTemplate(runData, "manage,permission,login.html");
            return;
        }
        // 登录成功,记录Session,转到index.html页面
        runData.getSession().setAttribute(AppConstants.SESSIONKEY, userSession);
        context.put(AppConstants.VO_NAME_OPTINFO, "login seccessfull!");
        setTemplate(runData, "manage,permission,index.html");
    }

    /**
     *  对非法登录参数的一些判断和处理,增加对用户名密码的特殊处理,以防止sql入侵
     */
    private boolean valid(RunData runData) {
        // 增加对用户名密码的特殊处理,以防止sql入侵
        int Max_Length = 20;
        String username = runData.getParameters().getString("username","");
        String password = runData.getParameters().getString("password","");
        // 长度检查
        if (username != null) {
            if (username.length() > Max_Length)
                return false;
        }
        if (password != null) {
            if (password.length() > Max_Length)
                return false;
        }
        if (username.indexOf(" and") > -1 || username.indexOf("and ") > -1)
            return false;
        if (password.indexOf(" and") > -1 || password.indexOf("and ") > -1)
            return false;
        return true;
    }

}
