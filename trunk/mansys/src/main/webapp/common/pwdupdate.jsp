<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<html>
	<head>
		<title>登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
	<script>
	function checkInput(){
		if(fm.newpwd.value!=fm.newpwdagain.value){
			alert("两次输入的新密码不一致！");
			fm.reset();
			return false;
		}
		return true;
	}
	</script>
	</head>
	<body leftmargin="0" topmargin="0" onLoad="document.fm.oldpwd.focus()">
	<form name="fm" action="pwdsave.jsp" method="post" onSubmit="return checkInput();">
		<div style="position:absolute;left:200px;top:100px;">
		<table cellSpacing="0" cellPadding="3" border="0" bgcolor="blue">
			<tr bgcolor="white" align="center">
				<td>
					旧密码：
				</td>
				<td>
					<input type="password" maxLength="20" name="oldpwd" value="">
				</td>
			</tr>
			<tr bgcolor="white" align="center">
				<td>
					新密码：
				</td>
				<td>
					<input type="password" maxLength="20" name="newpwd" value="">
				</td>
			</tr>
			<tr bgcolor="white" align="center">
				<td>
					再输一遍新密码：
				</td>
				<td>
					<input type="password" maxLength="20" name="newpwdagain" value="">
				</td>
			</tr>
			<tr bgcolor="white" align="center" height="40">
				<td colspan="2">
					<input type=submit value=" 保存 ">
				</td>
			</tr>
		</table>
		</div>
	</form>
	</body>
</html>