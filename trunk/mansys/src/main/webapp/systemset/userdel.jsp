<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>�û��б�</title>
	</head>
<%
	String getuserid = "";
	getuserid = request.getParameter("userid");
	String getusername = "";
	getusername = request.getParameter("username");
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	String strSQL = "delete from sys_user where userid="+getuserid;
	int i = stmt.executeUpdate(strSQL);
	strSQL = "delete from sys_user_menu where userid="+getuserid;
	int j = stmt.executeUpdate(strSQL);
	stmt.close();
	con.close();
	if(i==1){
		%>
		<script>
			alert("�û� <<%=getusername%>> ɾ���ɹ���");
			window.location = "./userlist.jsp";
		</script>
		<%
	}else{
		%>
		<script>
			alert("�û� <<%=getusername%>> ɾ��ʧ�ܣ�");
			window.location = "./userlist.jsp";
		</script>
		<%
	}
%>
<body>
</body>
</html>