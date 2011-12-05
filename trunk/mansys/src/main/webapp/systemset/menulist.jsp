<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<%
	ConnectDB cdb = new ConnectDB();
	Connection con = cdb.getConnectionFromDS();
	Statement stmt = con.createStatement();
	Statement stmt2 = con.createStatement();
	Statement stmt3 = con.createStatement();
	String strSQL1 = "select menucode,menuname,menuurl,isleaf,menuid,issys from sys_menu where menulevel=1 and status=1 order by displayorder";
	ResultSet rs = stmt.executeQuery(strSQL1);
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>menu show</title>
		<style type="text/css">
		<!--
		A:link,A:active,A:visited {
			text-decoration: none;
			color: #F0081E;
		}
		
		A:hover {
			text-decoration: none;
			color: #0808F0;
		}
		tr{
			height:20;
		}
		.sysmenu{
			color:yellow;
		}
		-->
		</style>
		<script>
		function showSecLevel(level){
			var subdiv = document.getElementById("follow"+level);
			var imgobj = document.getElementById("img"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
			}else{
				subdiv.style.display="";
			} 
		}
		function showThirdLevel(level){
			var subdiv = document.getElementById("followThird"+level);
			var imgobj = document.getElementById("imgThird"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
			}else{
				subdiv.style.display="";
			} 
		}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
		<table border="0" cellspacing="0" cellpadding="3" width="100%" bgcolor="#e9e9e9">
			<tr bgcolor="#efefef">
				<td>
				<strong>菜单管理</strong>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="3" width="100%">
			<tr height="20">
				<td>
				</td>
			</tr>
		</table>
		<table width="80%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#676767">
			<tr>
				<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
<%
	while(rs.next()){
	
if(rs.getInt(4)==1){
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%" bgcolor="#676767">
									<tr valign="middle">
										<td>
											<font style="color:blue;"><%=rs.getString(2)%></font>
											<font style="color:yellow;">[一级叶子菜单]</font>
										</td>
										<td align="right">
										<%
											if(rs.getInt(6)==1){
										%>
											<font class="sysmenu">[系统菜单]</font>
										<%
											}
											else{
										%>
											<a href="menuupdate.jsp?menuid=<%=rs.getInt(5)%>" class="menuopt">修改</a>
											<a href="menudel.jsp?menuid=<%=rs.getInt(5)%>&menucode=<%=rs.getString(1)%>" class="menuopt" onClick="return confirm('确定删除此菜单吗？');">删除</a>
										<%
											}
										%>
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
								<table border="0" cellspacing="0" cellpadding="0" width="100%" bgcolor="#676767">
									<tr valign="middle">
										<td onClick="javascript:showSecLevel('<%=rs.getString(1)%>');" style="cursor:hand;">
											<%=rs.getString(2)%>
											<font style="color:yellow;">[一级菜单]</font>
										</td>
										<td align="right">
										<%
											if(rs.getInt(6)==1){
										%>
											<font class="sysmenu">[系统菜单]</font>
										<%
											}
											else{
										%>
											<a href="menuupdate.jsp?menuid=<%=rs.getInt(5)%>" class="menuopt">修改</a>
											<a href="menudel.jsp?menuid=<%=rs.getInt(5)%>&menucode=<%=rs.getString(1)%>" class="menuopt" onClick="return confirm('确定删除此菜单及其子菜单吗？');">删除</a>
											<a href="menuinsert.jsp?menuid=<%=rs.getInt(5)%>&menulevel=1" class="menuopt">增加子菜单</a>
										<%
											}
										%> 
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="display:''" id="follow<%=rs.getString(1)%>">
							<td>
								<table width='100%' border="0" align=center cellpadding='0' cellspacing='0' bgcolor="#A6A6A6">
									<%ResultSet rs2 = stmt2
						.executeQuery("select menucode,menuname,menuurl,isleaf,menuid,issys from sys_menu where menulevel=2 and status=1 and parentscode ='"
								+ rs.getString(1) + "' order by displayorder ");
								boolean has2Next = false;
				while (rs2.next()) {
					has2Next = true;
				if(rs2.getInt(4)==1){
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%" bgcolor="#A6A6A6">
									<tr valign="middle">
										<td>
											&nbsp;&nbsp;
											<font style="color:blue;"><%=rs2.getString(2)%></font>
											<font style="color:yellow;">[二级叶子菜单]</font>
										</td>
										<td align="right">
										<%
											if(rs2.getInt(6)==1){
										%>
											<font class="sysmenu">[系统菜单]</font>
										<%
											}
											else{
										%>
											<a href="menuupdate.jsp?menuid=<%=rs2.getInt(5)%>" class="menuopt">修改</a>
											<a href="menudel.jsp?menuid=<%=rs2.getInt(5)%>&menucode=<%=rs2.getString(1)%>" class="menuopt" onClick="return confirm('确定删除此菜单吗？');">删除</a>
										<%
											}
										%>
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
											<table border="0" cellspacing="0" cellpadding="0" width="100%" bgcolor="#A6A6A6">
												<tr>
													<td onClick="javascript:showThirdLevel('<%=rs2.getString(1)%>');" style="cursor:hand;">
														&nbsp;&nbsp;
														<%=rs2.getString(2)%>
														<font style="color:yellow;">[二级菜单]</font>
													</td>
													<td align="right">
													<%
														if(rs2.getInt(6)==1){
													%>
														<font class="sysmenu">[系统菜单]</font>
													<%
														}
														else{
													%>
														<a href="menuupdate.jsp?menuid=<%=rs2.getInt(5)%>" class="menuopt">修改</a>
														<a href="menudel.jsp?menuid=<%=rs2.getInt(5)%>&menucode=<%=rs2.getString(1)%>" class="menuopt" onClick="return confirm('确定删除此菜单及其子菜单吗？');">删除</a>
														<a href="menuinsert.jsp?menuid=<%=rs2.getInt(5)%>&menulevel=2" class="menuopt">增加子菜单</a>
													<%
														}
													%>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr style="display:''" id="followThird<%=rs2.getString(1)%>">
										<td>
											<table width='100%' border="0" cellpadding='0' cellspacing='0' bgcolor="DAD9DE">
												<%ResultSet rs3 = stmt3
							.executeQuery("select menucode,menuname,menuurl,isleaf,menuid,issys from sys_menu where menulevel=3 and status=1 and parentscode='"
									+ rs2.getString(1) + "' order by displayorder ");
									boolean has3Next = false;
					while (rs3.next()) {
						has3Next = true;
%>
												<tr>
													<td>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<font style="color:blue;"><%=rs3.getString(2)%></font>
														<font style="color:yellow;">[三级叶子菜单]</font>
													</td>
													<td align="right">
													<%
														if(rs3.getInt(6)==1){
													%>
														<font class="sysmenu">[系统菜单]</font>
													<%
														}
														else{
													%>
														<a href="menuupdate.jsp?menuid=<%=rs3.getInt(5)%>" class="menuopt">修改</a>
														<a href="menudel.jsp?menuid=<%=rs3.getInt(5)%>&menucode=<%=rs3.getString(1)%>" class="menuopt" onClick="return confirm('确定删除此菜单吗？');">删除</a>
													<%
														}
													%>
													</td>
												</tr>
												<%
												}if(has3Next==false){
													%>
													<tr>
														<td>
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<font style="color:yellow;">[无子菜单]</font>
														</td>
														<td align="right">
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
									if(has2Next==false){
										%>
										<tr>
											<td>
												<table border="0" cellspacing="0" cellpadding="0" width="100%" bgcolor="#A6A6A6">
													<tr valign="middle">
														<td>
															&nbsp;&nbsp;
															<font style="color:yellow;">[无子菜单]</font>
														</td>
														<td align="right">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<%
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
		<table border="0" cellspacing="0" cellpadding="3" width="80%" align="center">
			<tr height="40">
				<td align="center">
				<a href="menuinsert.jsp?menuid=0&menulevel=0" class="menuopt">增加一级菜单</a>
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript">
<!--
//-->
</script>
