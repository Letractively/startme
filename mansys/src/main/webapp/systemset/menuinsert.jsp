<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<script src="./../assets/js/verify.js" type="text/javascript"></script>
		<title>���Ӳ˵�</title>
	</head>
<%
	String parentscode = "";
	String rootcode = "";
	
	String menuid = request.getParameter("menuid");
	String menulevel = request.getParameter("menulevel");
	if(menulevel.equals("0")){
		parentscode = "0";
		rootcode = "0";
	}else{
		ConnectDB cdb = new ConnectDB();
		Connection con = cdb.getConnectionFromDS();
		Statement stmt = con.createStatement();
		
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
			parentscode = rs.getString("menucode");
			if(menulevel.equals("1")){
				rootcode = rs.getString("menucode");
			}else{
				rootcode = rs.getString("parentscode");
			}
		}
		rs.close();
		stmt.close();
		con.close();
	}
	int mlevel = Integer.parseInt(menulevel)+1;
	
%>
<script>
var mlevel = "<%=mlevel%>";
function chooseLeaf(){
	var isleaf = document.getElementsByName("isleaf")[0];
	var murl = document.getElementById("murl");
	if(mlevel==3){
		if(isleaf.value=="0"){
			alert("�������˵�ֻ��ΪҶ�Ӳ˵�!");
			isleaf.selectedIndex = 0;
			murl.style.display = "";
			return false;
		}
	}else{
		if(isleaf.value=="0"){
			murl.style.display = "none";
		}else{
			murl.style.display = "";
		}
	}
}
</script>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
	<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
		<tr bgcolor="#efefef">
			<td>
			<strong>���Ӳ˵�</strong>
			</td>
		</tr>
	</table>
	<form action="menuinssave.jsp" method="post" target="_self" onSubmit="return checkInput();">
		<table align="center" width="300" cellspacing="1" cellpadding="3" bgcolor="gray">
			<tr bgcolor="white">
				<td width="300">
					<table align="center" width="250" cellspacing="0" cellpadding="3" bgcolor="blue">
					<tr bgcolor="white">
						<td width="100">
							�˵����룺
							<input type="hidden" name="parentscode" value="<%=parentscode%>">
							<input type="hidden" name="rootcode" value="<%=rootcode%>">
							<input type="hidden" name="menulevel" value="<%=mlevel%>">
						</td>
						<td width="150">
							<input type="text" name="menucode" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�˵����ƣ�
						</td>
						<td>
							<input type="text" name="menuname" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							��ʾ˳��
						</td>
						<td>
							<input type="text" name="displayorder" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							�Ƿ�Ҷ�Ӳ˵���
						</td>
						<td>
							<select name="isleaf" onChange="chooseLeaf();" style="width:100;">
							<option value="1" selected>��</option>
							<option value="0">��</option>
							</select>
						</td>
					</tr>
					<tr bgcolor="white" id="murl" style="display:'';">
						<td>
							�˵���ַ��
						</td>
						<td>
							<input type="text" name="menuurl" value="">
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
	var menucode = document.getElementsByName("menucode")[0];
	if(menucode.value.trim()==""){
		alert("�˵����벻��Ϊ�գ�");
		menucode.focus();
		return false;
	}
	if(!f_check_letter_num(menucode)){
		alert("�˵��������Ϊ��ĸ�����ֺ��»��ߵ���ϣ�");
		menucode.focus();
		return false;
	}
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