<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<script src="./../assets/js/verify.js" type="text/javascript"></script>
		<title>�˵��޸�</title>
	</head>
<%
	String menuid = request.getParameter("menuid");
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	String menucode = "";
	String menuname = "";
	String menuurl = "";
	int displayorder = 0;
	if(menuid==null||menuid.equals("")){
		%>
		<script>
			alert("�˵������ڣ�");
			history.back();
		</script>
		<%
		return;
	}
	String strSQL = "select * from sys_menu where menuid="+menuid;
	ResultSet rs = stmt.executeQuery(strSQL);
	if(rs.next()){
		menucode = rs.getString("menucode");
		menuname = rs.getString("menuname");
		menuurl = rs.getString("menuurl");
		displayorder = rs.getInt("displayorder");
	}
	rs.close();
	stmt.close();
	con.close();
	if(menuurl==null){
		menuurl = "";
	}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff"
	onload="document.getElementsByName('menuurl')[0].value = unescape(document.getElementsByName('menuurl')[0].value);">
	<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
		<tr bgcolor="#efefef">
			<td>
			<strong>�޸Ĳ˵�</strong>
			</td>
		</tr>
	</table>
	<form action="menuupdsave.jsp" method="post" target="_self" onSubmit="return checkInput();">
		<table align="center" width="300" cellspacing="1" cellpadding="3" bgcolor="gray">
			<tr bgcolor="white">
				<td width="300">
					<table align="center" width="250" cellspacing="0" cellpadding="3" bgcolor="blue">
					<tr bgcolor="white">
						<td width="100">
							�˵����룺
							<input type="hidden" name="menuid" value="<%=menuid%>">
						</td>
						<td width="150">
							<input type="text" name="menucode" value="<%=menucode%>" readonly style="color:gray;">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�˵����ƣ�
						</td>
						<td>
							<input type="text" name="menuname" value="<%=menuname%>">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�˵���ַ��
						</td>
						<td>
							<input type="text" name="menuurl" value="<%=menuurl%>">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							��ʾ˳��
						</td>
						<td>
							<input type="text" name="displayorder" value="<%=displayorder%>">
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr bgcolor="white">
				<td align="center">
					<input type="submit" value=" �ύ ">
					<input type="button" value=" ȡ�� " onClick="history.back();">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
function checkInput(){
	var menuname = document.getElementsByName("menuname")[0];
	if(menuname.value.trim()==""){
		alert("�˵����Ʋ���Ϊ�գ�");
		menuname.focus();
		return false;
	}
	var displayorder = document.getElementsByName("displayorder")[0];
	if(displayorder.value.trim()==""){
		alert("��ʾ˳����Ϊ�գ�");
		displayorder.focus();
		return false;
	}
	if(!f_check_naturalnumber(displayorder)){
		alert("��ʾ˳�����д����0�����֣�");
		displayorder.focus();
		return false;
	}
	document.getElementsByName("menuurl")[0].value = escape(document.getElementsByName("menuurl")[0].value);
	return true;
}
</script>