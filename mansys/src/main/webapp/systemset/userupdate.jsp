<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>�û��б�</title>
	</head>
<%
	String userid = request.getParameter("userid");
	String username = request.getParameter("username");
	String name = request.getParameter("name");
	String memo = request.getParameter("memo");
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
	<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
		<tr bgcolor="#efefef">
			<td>
			<strong>�޸��û�</strong>
			</td>
		</tr>
	</table>
	<form action="userupdsave.jsp" method="post" target="_self" onSubmit="return checkInput();">
		<table align="center" width="300" cellspacing="1" cellpadding="3" bgcolor="gray">
			<tr bgcolor="white">
				<td width="300">
					<table align="center" width="250" cellspacing="0" cellpadding="3" bgcolor="blue">
					<tr bgcolor="white">
						<td width="100">
							�û���¼����
							<input type="hidden" name="userid" value="<%=userid%>">
						</td>
						<td width="150">
							<input type="text" name="username" value="<%=username%>">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�û����룺
						</td>
						<td>
							<input type="password" name="password" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							������һ�����룺
						</td>
						<td>
							<input type="password" name="pwdagain" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�û�������
						</td>
						<td>
							<input type="text" name="name" value="<%=name%>">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							��ע��
						</td>
						<td>
							<input type="text" name="memo" value="<%=memo%>">
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
	var username = document.getElementsByName("username")[0];
	if(username.value==""){
		alert("�û���¼������Ϊ�գ�");
		username.focus();
		return false;
	}
	var name = document.getElementsByName("name")[0];
	if(name.value==""){
		alert("�������û�������");
		name.focus();
		return false;
	}
	var password = document.getElementsByName("password")[0];
	if(password.value==""){
		alert("�û����벻��Ϊ�գ�");
		password.focus();
		return false;
	}
	var pwdagain = document.getElementsByName("pwdagain")[0];
	if(password.value!=pwdagain.value){
		alert("�û����벻һ�£����������룡");
		password.value="";
		pwdagain.value="";
		password.focus();
		return false;
	}
	return true;
}
</script>