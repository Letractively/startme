/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.testapp;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.common.util.ConvertImage;


/**
 * 【类说明】
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:24:13
 */
public class Upload extends SecureAction {

    /* (non-Javadoc)
     * @see com.hc360.manage.common.secure.SecureAction#getModuleId()
     */
    @Override
    protected String getModuleId() {
        return AppConstants.MOD_COMMON;
    }

    /* (non-Javadoc)
     * @see org.apache.turbine.modules.actions.VelocitySecureAction#doPerform(org.apache.turbine.util.RunData, org.apache.velocity.context.Context)
     */
    @Override
    public void doPerform(RunData runData, Context context) throws Exception {
        String serverfilepath = runData.getServletContext().getRealPath("/fileupload")+"/";
        //图片上传
        FileItem item = runData.getParameters().getFileItem("filepath");
        String name = item.getName();
        System.out.println(name);
        String filename = new File(item.getName()).getName();
        System.out.println("=="+filename+"==");
        System.out.println(filename.equals(""));
        item.write(new File(serverfilepath+filename));
            
        //图片转换
        ConvertImage ci = new ConvertImage();
        ci.setFileInput(serverfilepath+filename);
        ci.setFileOutput(serverfilepath+"s"+filename);
        ci.convert();
        context.put("source", "/fileupload"+"/"+filename);
        context.put("imgpath", runData.getContextPath()+"/fileupload"+"/"+"s"+filename);

        runData.setScreenTemplate("testapp,fileupload.html");

    }

}
