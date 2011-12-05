<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>用户菜单权限保存</title>
	</head>
<%
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	
	String userid = request.getParameter("userid");
	String menuitemstr = request.getParameter("menuitemstr");
	String username = request.getParameter("username");
	if(username==null) username = "";
	else username = new String(username.getBytes("ISO-8859-1"),"GB2312");
	
	
	stmt.executeUpdate("delete from sys_user_menu where userid="+Long.parseLong(userid));
	if(menuitemstr!=null&&!menuitemstr.equals("")){
		String[] menuitem = menuitemstr.split(",");
		for(int i=0;i<menuitem.length;i++){
			stmt.executeUpdate("insert into sys_user_menu(pmid,userid,menuid) "+
			"values(sys_user_menu_seq.nextval,"+Long.parseLong(userid)+","+Long.parseLong(menuitem[i])+")");
		}
	}
	stmt.close();
	con.close();
	if(true){
		%>
		<script>
			alert("用户 <<%=username%>> 菜单权限设置成功！");
			window.location = "./userlist.jsp";
		</script>
		<%
	}
%>