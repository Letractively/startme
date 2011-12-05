<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="com.yehongyu.mansys.ao.vo.*"%>
<%
	String name = "";
	if(session.getAttribute("usersession")!=null){
		name = ((UserSession)session.getAttribute("usersession")).getName();
	}
	session.invalidate();
%>
<html>
	<head>
		<title>logout</title>
	</head>
	<body>
		<p align="center">
			<%=name%>
			,再见！
		</p>
		<p align="center">
			<a href="login.jsp">正转到登录页&gt;&gt;&gt;&gt;</a>&nbsp;&nbsp;&nbsp;
		</p>
		<script type="text/script">
			function gotoLogin(){
				window.location="login.jsp";
			}
			setTimeout("gotoLogin();",0*1000);
		</script>
	</body>
</html>
