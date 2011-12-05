/**
 * $Id$
 * Copyright(c) 2007-	GoodIdeals.com,All Rights Reserved.
 */
package com.yehongyu.mansys.web.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 【类说明】
 * @author goodideals.com
 * @version 1.0 2007-8-29 下午12:05:33
 */
public class MyListener implements ServletContextListener {
	
	Log log = LogFactory.getLog(getClass());

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("exit MyListener");

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		log.info("into MyListener");

	}

}
