package com.yehongyu.mansys.web.module;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yehongyu.mansys.ao.vo.UserSession;
import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.ibatis.SysUserDAO;
import com.yehongyu.mansys.dao.ibatis.impl.SysUserDAOImpl;
import com.yehongyu.mansys.dao.query.SysUserQuery;
import com.yehongyu.mansys.util.MyMD5;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	SysUserDAO sysUserDAO = new SysUserDAOImpl();

	private String target = "/frame.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// If it is a get request forward to doPost()
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null)
			username = "";
		else
			username = new String(username.getBytes("ISO-8859-1"), "GB2312");
		HttpSession session = request.getSession();
		UserSession userSession = null;
		// ConnectDB cdb = new ConnectDB();
		// Connection con = cdb.getConnectionFromMysql();
		// try {
		// String sql = "select * from sys_user where username=? and status=1";
		// PreparedStatement pstmt = con.prepareStatement(sql);
		// pstmt.setString(1,username);
		// ResultSet rs = pstmt.executeQuery();
		// if(rs.next()){
		// userSession = new UserSession();
		// userSession.setUserid(rs.getLong("userid"));
		// userSession.setUsername(rs.getString("username"));
		// userSession.setPassword(rs.getString("password"));
		// userSession.setPwdtxt(password);
		// userSession.setName(rs.getString("name"));
		// userSession.setIssys(rs.getLong("issys"));
		// userSession.setStatus(rs.getLong("status"));
		// userSession.setMemo(rs.getString("memo"));
		// }
		// rs.close();
		// pstmt.close();
		// con.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		SysUserQuery sysUserQuery = new SysUserQuery();
		sysUserQuery.setStatus(1);
		sysUserQuery.setUsername(username);
		SysUserDO sysUserDO = sysUserDAO.getSysUserDO(sysUserQuery);
		if (sysUserDO == null) {
			session.setAttribute("info", "用户名不存在！");
			this.target = "/index.htm";
		} else {
			userSession = new UserSession();
			userSession.setUserid(sysUserDO.getId());
			userSession.setUsername(sysUserDO.getUsername());
			userSession.setPassword(sysUserDO.getPassword());
			userSession.setPwdtxt(password);
			userSession.setName(sysUserDO.getName());
			userSession.setIssys(sysUserDO.getIssys());
			userSession.setStatus(sysUserDO.getStatus());
			userSession.setMemo(sysUserDO.getMemo());
			password = MyMD5.MD5Encode(password);
			session.setAttribute("usersession", userSession);
			if (!password.equals(userSession.getPassword())) {
				session.setAttribute("info", "用户密码不对！");
				this.target = "/index.htm";
			} else {
				this.target = "/frame.jsp";
				// this.target="/login/loginforward.jsp";
				// this.target="http://192.168.14.211/cognos8/cgi-bin/cognos.cgi?"
				// +
				// "b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&"
				// +
				// "ui.object=%2fcontent%2fpackage%5b%40name%3d%27hcmedia%27%5d%2freport%5b%40name%3d%27main%27%5d&"
				// +
				// "cv.ccurl=1&ui.backURL=index01.html&nh=1&tb=0&CAMUsername="+userSession.getUsername()+
				// "&CAMPassword="+userSession.getPassword();
			}
		}
		// Forward the request to the target named
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		dispatcher.forward(request, response);
	}

	public void destroy() {

	}
}
