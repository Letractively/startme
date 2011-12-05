/**
 * $Id$
 * Copyright(c) 2007-	GoodIdeals.com,All Rights Reserved.
 */
package com.yehongyu.mansys.web.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 【类说明】
 * 
 * @author goodideals.com
 * @version 1.0 2007-8-24 下午05:16:17
 */
public class MyServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(getClass());

	public final void init() throws ServletException {
		if (logger.isInfoEnabled()) {
			logger.info("Initializing servlet '" + getServletName() + "'");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Servlet '" + getServletName()
					+ "' configured successfully");
		}
	}

	protected final void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect("http://www.google.com");
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context
				.getRequestDispatcher("/test.jsp");
		// dispatcher.include(request, response);
		dispatcher.forward(request, response);
		// response.sendRedirect("/springapp/test.jsp");
	}

	/**
	 * Delegate POST requests to processRequest/doService.
	 * 
	 * @see #doService
	 */
	protected final void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Delegate PUT requests to processRequest/doService.
	 * 
	 * @see #doService
	 */
	protected final void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Delegate DELETE requests to processRequest/doService.
	 * 
	 * @see #doService
	 */
	protected final void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Close the WebApplicationContext of this servlet.
	 * 
	 * @see org.springframework.context.ConfigurableApplicationContext#close
	 */
	public void destroy() {
		getServletContext().log("Closing MyServlet '" + getServletName() + "'");

	}
}
