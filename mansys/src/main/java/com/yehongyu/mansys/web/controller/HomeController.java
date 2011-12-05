package com.yehongyu.mansys.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.ibatis.SysUserDAO;

/**
 * Handles requests for the application home page.
 */
@Component
public class HomeController implements Controller {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Resource
	SysUserDAO sysUserDAO;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv;
		String module = getModule(request);
		if(module!=null&&!"home".equals(module)){
			return handleModuleRequest(request,response);
		}
		if("true".equals(request.getParameter("test"))){
			mv = new ModelAndView("test");
			List<SysUserDO> users = sysUserDAO.getSysUserDOList(null);
			mv.addObject("users", users);
			mv.addObject("users", "hao的");
			logger.info(users);
		}else{
			mv = new ModelAndView("home");
		}
		return mv;
	}
	
	protected ModelAndView handleModuleRequest(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//子类重载此方法
		return null;
	}
	
	private String getModule(HttpServletRequest request){
		if(request.getRequestURI()==null||request.getContextPath()==null){
			return null;
		}
		String uri = request.getRequestURI();
		logger.warn(uri);
		logger.warn("contextpath:" + request.getContextPath());
		uri = uri.substring(uri.indexOf(request.getContextPath()));
		logger.warn(uri);
		uri = uri.substring(0,uri.lastIndexOf(".htm"));
		logger.warn(uri);
		return uri;
	}
	

}
