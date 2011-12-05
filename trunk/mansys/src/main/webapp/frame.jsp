<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>后台管理系统</title>
	</head>
	<frameset rows="95,*" frameborder="no" border="0" framespacing="0">
		<frame src="./login/top.jsp" name="topFrame" scrolling="No" noresize/>
		<frameset name="homeFrame" cols="200,8,*" frameborder="no" border="1" framespacing="1">
			<frame src="./login/menu.jsp" name="leftFrame" scrolling="yes" noresize />
			<frame src="./login/ctrl.jsp" name="ctrlFrame" noresize />
			<frame src="./login/main.jsp" name="mainFrame" noresize />
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
