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
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	
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
	if(username!=null&&!username.equals("")){
		String querySQL = "select * from sys_user where username='"+username.trim()+"' and status=1";
		ResultSet rs = stmt.executeQuery(querySQL);
		if(rs.next()){
			%>
			<script>
				alert("�û� <<%=username%>> �Ѵ��ڣ�");
			</script>
			<%
		}else{
			String strSQL = "insert into sys_user(userid,username,password,name,issys,status,memo)" +
		 				" values(sys_user_seq.nextval,'"+username+"','"+MyMD5.MD5Encode(password)+"','"+name+"',0,1,'"+memo+"')";
			i = stmt.executeUpdate(strSQL);
		}
		rs.close();
	}
	stmt.close();
	con.close();
	if(i==1){
		%>
		<script>
			alert("�û� <<%=username%>> ��ӳɹ���");
			window.location = "./userlist.jsp";
		</script>
		<%
	}
%>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
	<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
		<tr bgcolor="#efefef">
			<td>
			<strong>����û�</strong>
			</td>
		</tr>
	</table>
	<form action="userinsert.jsp" method="post" target="_self" onSubmit="return checkInput();">
	<table align="center" width="300" cellspacing="1" cellpadding="3" bgcolor="gray">
			<tr bgcolor="white">
				<td width="300">
					<table align="center" width="250" cellspacing="0" cellpadding="3" bgcolor="blue">
						<tr bgcolor="white">
							<td width="100">
								�û���¼����
							</td>
							<td width="150">
								<input type="text" name="username" value="">
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
								<input type="text" name="name" value="">
							</td>
						</tr>
						<tr bgcolor="white">
							<td>
								��ע��
							</td>
							<td>
								<input type="text" name="memo" value="">
							</td>
						</tr>
					</table>
				</td>
			<tr bgcolor="white">
				<td align="center">
					<input type="submit" value=" �ύ ">
					<input type="reset" value=" ���� ">
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