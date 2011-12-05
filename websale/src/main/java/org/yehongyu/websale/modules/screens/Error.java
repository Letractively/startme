package org.yehongyu.websale.modules.screens;

import org.apache.log4j.Logger;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.common.exception.Err;
import org.yehongyu.websale.common.exception.impl.ConfigParser;
import org.yehongyu.websale.common.exception.impl.ExceptionItem;
import org.yehongyu.websale.common.secure.NoSecureScreen;
import org.yehongyu.websale.common.util.MyException;

/**
 * 
 * 框架提供的处理异常的公共类 在定制的Error.html页面里，可以通过${exceptionmsg}取得友好的错误提示
 * 
 * @author sbm
 * @version 4.0 Apr 16, 2007
 * @since 4.0
 */
public class Error extends NoSecureScreen {

    Logger log = Logger.getLogger(Error.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.turbine.modules.screens.VelocityScreen#doBuildTemplate(org.apache.turbine.util.RunData,
     *      org.apache.velocity.context.Context)
     */
    @Override
    protected void doBuildTemplate(RunData runData, Context context) throws Exception {
        
        runData.setLayoutTemplate("NoLayout.html");

        Throwable it = runData.getStackTraceException();

        if (it instanceof MyException) {

            log.error((MyException) it);

            ExceptionItem ei = ConfigParser.getExceptionItemList().get(
                    ((MyException) it).getErrorcode());
            if (ei != null) {
                context.put("exceptionmsg", ei.getPageMessage());
            } else {
                context.put("exceptionmsg", "null");
            }

        } else {
            log.error(Err.mmt_pu_co_00003, it);
        }

    }

}
