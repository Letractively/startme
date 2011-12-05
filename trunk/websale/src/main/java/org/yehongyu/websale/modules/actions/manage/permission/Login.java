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
 * ����˵���� ��̨��¼����Action
 * �û�������Լ����
 * �û������볤�Ȳ��ܳ���20λ
 * �û��������в��ó���" and"��"and "�������ַ���
 * ���ֱ���Ϊ�ǷǷ�Sqlע��,�ܾ���¼
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:22:49
 */
public class Login extends NoSecureAction {
    
    /**
     * �û���¼У������
     */
    public void doPerform(RunData runData, Context context) throws MyException {
        PermissionManager permissionManager = (PermissionManager) MyBusinessFactory
                .getManager(AppConstants.PermissionManager);
//        HttpServletRequest request = runData.getRequest();
//        HttpSession httpSession = request.getSession();
        
//        //����������Ҫ��֤��
//        int loginnumforyz = 3;  //�������β�������֤�롣
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

            // ��֤��У��
//            if (!yz.equals(verifyCode)) {
//                context.put("yzOK", "0"); // δͨ��������Ϊ0
//                setTemplate(runData, "manage,permission,login.html");
//                return;
//            }
//        }
        
        //����У��
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
            // ���session������,���û���������,���벻��ȷ��
            context.put("iserror", "1");
            context.put("errorinfo", "�û���+���벻��ȷ");
            context.put("username", username);
            context.put("password", password);
            setTemplate(runData, "manage,permission,login.html");
            return;
        }
        // ��¼�ɹ�,��¼Session,ת��index.htmlҳ��
        runData.getSession().setAttribute(AppConstants.SESSIONKEY, userSession);
        context.put(AppConstants.VO_NAME_OPTINFO, "login seccessfull!");
        setTemplate(runData, "manage,permission,index.html");
    }

    /**
     *  �ԷǷ���¼������һЩ�жϺʹ���,���Ӷ��û�����������⴦��,�Է�ֹsql����
     */
    private boolean valid(RunData runData) {
        // ���Ӷ��û�����������⴦��,�Է�ֹsql����
        int Max_Length = 20;
        String username = runData.getParameters().getString("username","");
        String password = runData.getParameters().getString("password","");
        // ���ȼ��
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
