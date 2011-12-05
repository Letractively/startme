<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="javax.annotation.*" %>
<%
	@Resource
	PermissionAO permissionAO;
	String userid = request.getParameter("userid");
	String username = request.getParameter("username");
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	Statement stmt2 = con.createStatement();
	Statement stmt3 = con.createStatement();
	String strSQL1 = "select menucode,menuname,menuurl,isleaf,menuid from sys_menu where menulevel=1 and status=1 order by displayorder";
	ResultSet rs = stmt.executeQuery(strSQL1);
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>menu show</title>
		<script>
		function showSecLevel(level){
			var subdiv = document.getElementById("follow"+level);
			var imgobj = document.getElementById("img"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
				imgobj.src = "./../images/open.gif";
			}else{
				subdiv.style.display="";
				imgobj.src = "./../images/close.gif";
			} 
		}
		function showThirdLevel(level){
			var subdiv = document.getElementById("followThird"+level);
			var imgobj = document.getElementById("imgThird"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
				imgobj.src = "./../images/open.gif";
			}else{
				subdiv.style.display="";
				imgobj.src = "./../images/close.gif";
			} 
		}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
		<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#a0a0a0">
			<tr bgcolor="#efefef">
				<td>
				<strong>设置用户菜单权限</strong>
				</td>
			</tr>
		</table>
		<table width="50%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr align="center" height="30">
				<td>
				所设置的用户为：<%=username%>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0">
<%
	while(rs.next()){
	
if(rs.getInt(4)==1){
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr valign="middle">
										<td>
										<%
											if(Utility.isAuthenMenu(Long.parseLong(userid),rs.getLong(5))){
										%>
											<input type="checkbox" name="menuitem" checked value="<%=rs.getLong(5)%>">
										<%
											}else{
										%>
											<input type="checkbox" name="menuitem" value="<%=rs.getLong(5)%>">
										<%
											}
										%>
											<font style="color:blue;"><%=rs.getString(2)%></font>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<%
						continue;
						}
						%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr valign="middle">
										<td>
											<a href="javascript:showSecLevel('<%=rs.getString(1)%>');">
											<img id="img<%=rs.getString(1)%>" src="./../images/close.gif" border="0"></a>
											<%
											if(Utility.isAuthenMenu(Long.parseLong(userid),rs.getLong(5))){
											%>
												<input type="checkbox" name="menuitem" checked value="<%=rs.getLong(5)%>">
											<%
												}else{
											%>
												<input type="checkbox" name="menuitem" value="<%=rs.getLong(5)%>">
											<%
												}
											%>
											<%=rs.getString(2)%>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="display:''" id="follow<%=rs.getString(1)%>">
							<td>
								<table width='100%' border=0 align=center cellpadding='0' cellspacing='1'>
									<%ResultSet rs2 = stmt2
						.executeQuery("select menucode,menuname,menuurl,isleaf,menuid from sys_menu where menulevel=2 and status=1 and parentscode ='"
								+ rs.getString(1) + "' order by displayorder ");
				while (rs2.next()) {
				
				if(rs2.getInt(4)==1){
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr valign="middle">
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<%
												if(Utility.isAuthenMenu(Long.parseLong(userid),rs2.getLong(5))){
											%>
												<input type="checkbox" name="menuitem" checked value="<%=rs2.getLong(5)%>">
											<%
												}else{
											%>
												<input type="checkbox" name="menuitem" value="<%=rs2.getLong(5)%>">
											<%
												}
											%>
											<font style="color:blue;"><%=rs2.getString(2)%></font>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<%
						continue;
						}
						%>
									<tr>
										<td>
											<table border="0" cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<td>
														&nbsp;&nbsp;
														<a href="javascript:showThirdLevel('<%=rs2.getString(1)%>');">
														<img id="imgThird<%=rs2.getString(1)%>" src="./../images/close.gif" border="0"></a>
														<%
															if(Utility.isAuthenMenu(Long.parseLong(userid),rs2.getLong(5))){
														%>
															<input type="checkbox" name="menuitem" checked value="<%=rs2.getLong(5)%>">
														<%
															}else{
														%>
															<input type="checkbox" name="menuitem" value="<%=rs2.getLong(5)%>">
														<%
															}
														%>
														<%=rs2.getString(2)%>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr style="display:''" id="followThird<%=rs2.getString(1)%>">
										<td>
											<table width='100%' border=0 cellpadding='0' cellspacing='1'>
												<%ResultSet rs3 = stmt3
							.executeQuery("select menucode,menuname,menuurl,isleaf,menuid from sys_menu where menulevel=3 and status=1 and parentscode='"
									+ rs2.getString(1) + "' order by displayorder ");
					while (rs3.next()) {
					
%>
												<tr>
													<td>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<%
															if(Utility.isAuthenMenu(Long.parseLong(userid),rs3.getLong(5))){
														%>
															<input type="checkbox" name="menuitem" checked value="<%=rs3.getLong(5)%>">
														<%
															}else{
														%>
															<input type="checkbox" name="menuitem" value="<%=rs3.getLong(5)%>">
														<%
															}
														%>
														<font style="color:blue;"><%=rs3.getString(2)%></font>
													</td>
												</tr>
												<%
												}
												%>
											</table>
										</td>
									</tr>
									<%rs3.close();
									}
									%>
								</table>
							</td>
						</tr>
						<%
								rs2.close();
							}
							rs.close();
							stmt3.close();
							stmt2.close();
							stmt.close();
							if (con != null) {
								con.close();
							}
						%>
					</table>
				</td>
			</tr>
		</table>
		<form action="usermenusetsave.jsp" method="post" target="_self" onSubmit="return checkInput();">
		<table border="0" cellspacing="0" cellpadding="3" width="100%">
			<tr align="center">
				<td>
				<input type="hidden" name="menuitemstr" value="">
				<input type="hidden" name="userid" value="<%=userid%>">
				<input type="hidden" name="username" value="<%=username%>">
				<input type="submit" value=" 保存 ">
				<input type="button" value=" 取消 " onClick="history.back();">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
<script type="text/javascript">
<!--
	function checkInput(){
		var menuitemstr = "";
		var menuitem = document.getElementsByName("menuitem");
		for(var i=0;i<menuitem.length;i++){
			if(menuitem[i].checked==true){
				if(menuitemstr!="") menuitemstr += ",";
				menuitemstr += menuitem[i].value;
			}
		}
		document.getElementsByName("menuitemstr")[0].value=menuitemstr;
	}
//-->
</script>
