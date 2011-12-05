<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>用户列表</title>
	</head>
<%
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	
	String userid = request.getParameter("userid");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String memo = request.getParameter("memo");
	if(username==null) username = "";
	else username = new String(username.getBytes("ISO-8859-1"),"GB2312");
	if(name==null) name = "";
	else name = new String(name.getBytes("ISO-8859-1"),"GB2312");
	if(memo==null) memo = "";
	else memo = new String(memo.getBytes("ISO-8859-1"),"GB2312");
	int i = 0;
	if(userid!=null&&!userid.equals("")){
		String querySQL = "select * from sys_user where username='"+username.trim()+"' and userid<>"+Long.parseLong(userid)+" and status=1";
		ResultSet rs = stmt.executeQuery(querySQL);
		if(rs.next()){
			i = 2;
		}else{
			String strSQL = "update sys_user set username='"+username+"',password='"
			+MyMD5.MD5Encode(password)+"',name='"+name+"',memo='"+memo+"' where userid="+Long.parseLong(userid);
			i = stmt.executeUpdate(strSQL);
		}
		rs.close();
	}
	
	stmt.close();
	con.close();
	if(i==1){
		%>
		<script>
			alert("用户 <<%=username%>> 修改成功！");
			window.location = "./userlist.jsp";
		</script>
		<%
	}
	if(i==2){
		%>
		<script>
			alert("用户 <<%=username%>> 已存在！");
			history.back();
		</script>
		<%
	}
%>