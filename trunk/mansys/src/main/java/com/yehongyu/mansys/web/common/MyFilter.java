/**
 * $Id$
 * Copyright(c) 2007-	GoodIdeals.com,All Rights Reserved.
 */
package com.yehongyu.mansys.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 【类说明】
 * @author goodideals.com
 * @version 1.0 2007-8-29 上午11:53:47
 */
public class MyFilter implements Filter {
	
	Logger logger = Logger.getLogger(getClass());

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("into MyFilter");
		logger.info("request.getRemoteHost()"+request.getRemoteHost());
		logger.info("request.getRemoteAddr()"+request.getRemoteAddr());
		logger.info("request.getRemotePort()"+request.getRemotePort());
		HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        logger.info("req.getSession().getId()"+req.getSession().getId());
		chain.doFilter(req, res);
		logger.info("exit MyFilter");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
