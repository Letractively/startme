<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="com.yehongyu.mansys.ao.vo.*"%>
<html>
	<head>
		<title>登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<%
	  	UserSession us = (UserSession)session.getAttribute("usersession");
	    if(us!=null){
	    	%>
	    	<script>
	    	window.location = "http://192.168.14.211/cognos8/cgi-bin/cognos.cgi?" +
						"b_action=xts.run&m=portal/report-viewer.xts&ui.action=run&" +
						"ui.object=%2fcontent%2fpackage%5b%40name%3d%27hcmedia%27%5d%2freport%5b%40name%3d%27main%27%5d&" +
						"cv.ccurl=1&ui.backURL=index01.html&nh=1&tb=0&CAMUsername=<%=us.getUsername()%>"+
						"&CAMPassword=<%=us.getPwdtxt()%>";
	    	</script>
	    	<%
	    }else{
	    	%>
	    	<script>
	    	alert("登录失败！");
	    	window.location = "./../index.htm";
	    	</script>
	    	<%
	    }
	%>
</html>
