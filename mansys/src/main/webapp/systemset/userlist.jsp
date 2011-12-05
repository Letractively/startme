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
	String getusername = request.getParameter("username");
	String getname = request.getParameter("name");
	if(getusername==null) getusername = "";
	else getusername = new String(getusername.getBytes("ISO-8859-1"),"GB2312");
	if(getname==null) getname = "";
	else getname = new String(getname.getBytes("ISO-8859-1"),"GB2312");
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	String strSQL = "select userid,username,name,memo from sys_user where issys=0 and status=1";
	if(getusername!=null&&!getusername.equals("")){
		strSQL += " and username like '%" + getusername + "%'";
	}
	if(getname!=null&&!getname.equals("")){
		strSQL += " and name like '%" + getname + "%'";
	}
	strSQL += " order by userid";
	ResultSet rs = stmt.executeQuery(strSQL);
	
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
	<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
		<tr bgcolor="#efefef">
			<td>
			<strong>查询用户</strong>
			</td>
		</tr>
	</table>
	<form action="userlist.jsp" method="post" target="_self">
		<table align="center" width="500" cellspacing="0" cellpadding="3" bgcolor="blue">
			<tr bgcolor="white">
				<td>
					用户登录名：<input type="text" name="username" value="<%=getusername%>">
					用户姓名：<input type="text" name="name" value="<%=getname%>">
					<input type="submit" value=" 查 询 ">
				</td>
			</tr>
		</table>
	</form>
	<table align="center" width="80%" cellspacing="1" cellpadding="3" bgcolor="blue">
		<tr bgcolor="#efefef">
			<td align="center">
				序号
			</td>
			<td align="center" style="display:none;">
				用户ID
			</td>
			<td align="center">
				用户登录名
			</td>
			<td align="center">
				用户姓名
			</td>
			<td align="center">
				备注
			</td>
			<td align="center">
				操作
			</td>
		</tr>
		<%
			int i = 0;
			long userid = 0;
			String username = "";
			String name = "";
			String memo = "";
			while(rs.next()){
				i++;
				userid = rs.getLong("userid");
				username = rs.getString("username");
				name = rs.getString("name");
				memo = rs.getString("memo");
		%>
		<tr bgcolor="white">
			<td>
				<%=i%>
			</td>
			<td style="display:none;">
				<%=userid%>
			</td>
			<td>
				<%=username%>
			</td>
			<td>
				<%=name%>
			</td>
			<td>
				<%=memo%>
			</td>
			<td>
				<a href="./userupdate.jsp?userid=<%=userid%>&username=<%=username%>&name=<%=name%>&memo=<%=memo%>">修改</a>
				<a href="./userdel.jsp?userid=<%=userid%>&username=<%=username%>" onClick="if(!confirm('确定删除此用户及其对应的权限吗？')) return false;")>删除</a>
				<a href="./usermenuset.jsp?userid=<%=userid%>&username=<%=username%>">设置用户权限</a>
			</td>
		</tr>
		<%
			}
			rs.close();
			stmt.close();
			con.close();
		%>
	</table>
</body>
</html>