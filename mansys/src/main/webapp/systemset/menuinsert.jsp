<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<script src="./../assets/js/verify.js" type="text/javascript"></script>
		<title>增加菜单</title>
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
				alert("菜单不存在！");
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
			alert("第三级菜单只能为叶子菜单!");
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
			<strong>增加菜单</strong>
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
							菜单编码：
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
							菜单名称：
						</td>
						<td>
							<input type="text" name="menuname" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							显示顺序：
						</td>
						<td>
							<input type="text" name="displayorder" value="">
						</td>
					</tr>
					<tr bgcolor="white">
						<td>
							是否叶子菜单：
						</td>
						<td>
							<select name="isleaf" onChange="chooseLeaf();" style="width:100;">
							<option value="1" selected>是</option>
							<option value="0">否</option>
							</select>
						</td>
					</tr>
					<tr bgcolor="white" id="murl" style="display:'';">
						<td>
							菜单地址：
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
					<input type="submit" value=" 提交 ">
					<input type="button" value=" 取消 " onClick="history.back();">
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
		alert("菜单编码不能为空！");
		menucode.focus();
		return false;
	}
	if(!f_check_letter_num(menucode)){
		alert("菜单编码必须为字母或数字和下划线的组合！");
		menucode.focus();
		return false;
	}
	var menuname = document.getElementsByName("menuname")[0];
	if(menuname.value.trim()==""){
		alert("菜单名称不能为空！");
		menuname.focus();
		return false;
	}
	var displayorder = document.getElementsByName("displayorder")[0];
	if(displayorder.value.trim()==""){
		alert("显示顺序不能为空！");
		displayorder.focus();
		return false;
	}
	if(!f_check_naturalnumber(displayorder)){
		alert("显示顺序必须写大于0的数字！");
		displayorder.focus();
		return false;
	}
	document.getElementsByName("menuurl")[0].value = escape(document.getElementsByName("menuurl")[0].value);
	return true;
}
</script>