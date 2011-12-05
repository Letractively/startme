<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
		//String target = "/main.jsp";
		boolean success = false;
		String oldpwd = request.getParameter("oldpwd");
		String dbpwd = "";
		String newpwd = request.getParameter("newpwd");
		UserSession userSession = (UserSession)session.getAttribute("usersession");
		ConnectDB cdb = new ConnectDB();
		Connection con = cdb.getConnectionFromDS();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sys_user where userid="+userSession.getUserid()+" and status=1");
			if(rs.next()){
				dbpwd = rs.getString("password");
			}
			if(!MyMD5.MD5Encode(oldpwd).equals(dbpwd)){
				success = false;
			}else{
				int i = stmt.executeUpdate("update sys_user set password='"+MyMD5.MD5Encode(newpwd)+"' where userid="+userSession.getUserid()+"");
				if(i>=1){
					success = true;
				}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(success==true){
			//target = "/main.jsp";
			%>
			<script>
			alert("修改成功！");
			window.location = "./../login/main.jsp";
			</script>
			<%
		}else{
			//target = "/pwdupdate.jsp";
			%>
			<script>
			alert("旧密码不对，请重新输入!");
			window.location = "./pwdupdate.jsp";
			</script>
			<%
		}
		//target = request.getContextPath()+target;
		//Forward the request to the target named
		//ServletContext context = this.getServletContext();
		//RequestDispatcher dispatcher = context.getRequestDispatcher(target);
		//dispatcher.forward(request,response);
%>