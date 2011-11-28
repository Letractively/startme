package com.taobao.yourproject.web.module.screen;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.citrus.turbine.Context;

/**
 * Welcome Screen
 * @author yingyang
 *
 */
public class Welcome {
	
	@Resource 
	HttpServletRequest request;
	
    /**
     * display welcome screen
     * @param context
     */
	public void execute(Context context)  {
		
        context.put("name", "webx3");
    }
}
