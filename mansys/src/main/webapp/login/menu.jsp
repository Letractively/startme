<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="com.yehongyu.mansys.dao.domain.*"%>
<%@ page import="com.yehongyu.mansys.ao.vo.*"%>
<%@ page import="com.yehongyu.mansys.ao.*"%>
<%@ page import="com.yehongyu.mansys.ao.impl.*"%>
<%@ page import="javax.annotation.*"%>
<%@ page import="java.util.*"%>

<%
	PermissionAO permissionAO = new PermissionAOImpl();
	List<SysMenuDO> menulist = permissionAO.getAllMenu();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<title>menu show</title>
		<style type="text/css">
		<!--
		A.sys:hover {
			text-decoration: none;
			font-size:14;
			color: #AD071B;
		}
		tr{
			height:20;
		}
		body {
			background-color: #CDE6FA;
			margin-left: 10px;
		}
		-->
		</style>
		<%
			String name= null;
			UserSession us = null;
			if(session==null){
				%>
				<script>
					alert("Session为空，请重新登录！");
					top.location = "login.jsp";
				</script>
				<%
				return;
			}else{
				if(session.getAttribute("usersession")==null){
					%>
				<script>
					alert("用户Session不存在或已过期，请重新登录！");
					top.location = "login.jsp";
				</script>
				<%
				return;
				}else{
					us = (UserSession)session.getAttribute("usersession");
					name = us.getName().toString();
				}
			}
		%>
		<script>
		function showSecLevel(level){
			var subdiv = document.getElementById("follow"+level);
			var imgobj = document.getElementById("img"+level);
			var imgfoldobj = document.getElementById("imgfold"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
				imgobj.src = "./../images/open.gif";
				imgfoldobj.src = "./../images/foldoff.gif";
			}else{
				subdiv.style.display="";
				imgobj.src = "./../images/close.gif";
				imgfoldobj.src = "./../images/foldon.gif";
			} 
		}
		function showThirdLevel(level){
			var subdiv = document.getElementById("followThird"+level);
			var imgobj = document.getElementById("imgThird"+level);
			var imgfoldobj = document.getElementById("imgfoldThird"+level);
			if(subdiv.style.display==""){
				subdiv.style.display="none";
				imgobj.src = "./../images/open.gif";
				imgfoldobj.src = "./../images/foldoff.gif";
			}else{
				subdiv.style.display="";
				imgobj.src = "./../images/close.gif";
				imgfoldobj.src = "./../images/foldon.gif";
			} 
		}
		function openLink(url){
			url = unescape(url);
			var p_start_date = document.getElementsByName("p_start_date")[0].value;
			var p_end_date = document.getElementsByName("p_end_date")[0].value;
			var p_start_date_h = document.getElementsByName("p_start_date_h")[0].value;
			var p_end_date_h = document.getElementsByName("p_end_date_h")[0].value;
			var p_start_date_t = document.getElementsByName("p_start_date_t")[0].value;
			var p_end_date_t = document.getElementsByName("p_end_date_t")[0].value;
			if(url.indexOf("?")==-1){
				url += "?p_start_date="+p_start_date+"&p_end_date="+p_end_date+"&p_start_date_h="+p_start_date_h+"&p_end_date_h="+p_end_date_h+"&p_start_date_t="+p_start_date_t+"&p_end_date_t="+p_end_date_t;
			}else{
				url += "&p_start_date="+p_start_date+"&p_end_date="+p_end_date+"&p_start_date_h="+p_start_date_h+"&p_end_date_h="+p_end_date_h+"&p_start_date_t="+p_start_date_t+"&p_end_date_t="+p_end_date_t;
			}
			parent.mainFrame.location = url;  
		}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">

		<table border="0" cellspacing="0" cellpadding="3" width="100%">
			<tr>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;当前用户为：<%=name%>
				</td>
			</tr>
			<tr>
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="./../common/timeset.jsp" target="mainFrame" class="sys"><strong>设置时间</strong></a>
				<input name="p_start_date" type="hidden" />
    			<input name="p_end_date" type="hidden" />
    			<input name="p_start_date_t" type="hidden" />
    			<input name="p_end_date_t" type="hidden" />
    			<input name="p_start_date_h" type="hidden" />
    			<input name="p_end_date_h" type="hidden" />
				</td>
			</tr>
<!--			<tr>-->
<!--				<td>-->
<!--				&nbsp;&nbsp;&nbsp;&nbsp;<a href="./../common/pwdupdate.jsp" target="mainFrame" class="sys"><strong>修改密码</strong></a>-->
<!--				</td>-->
<!--			</tr>-->
<!--			<tr>-->
<!--				<td>-->
<!--				&nbsp;&nbsp;&nbsp;&nbsp;<a href="logout.jsp" target="_top" class="sys"><strong>退出系统</strong></a>-->
<!--				</td>-->
<!--			</tr>-->
			
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td valign="top">
					<table border="0" cellpadding="0" cellspacing="0">
<%
	for(SysMenuDO menu:menulist){
		if(us.getIssys()!=1 && !permissionAO.isAuthenMenu(us.getUserid(),menu.getId())){
			continue;
		}
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr valign="middle">
										<td>
											<img src="./../images/arrow.gif" align="middle" border="0">
											<a href="#" onClick="openLink('<%=menu.getMenuurl()%>');"><%=menu.getMenuname()%></a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="300">
									<tr valign="middle" onClick="showSecLevel('<%=menu.getMenucode()%>');" style="cursor:hand;">
										<td background="./../images/foldoff1.gif" style="background-repeat:no-repeat;">
											<img id="img<%=menu.getMenucode()%>" src="./../images/open.gif" border="0">
											<img id="imgfold<%=menu.getMenucode()%>" src="./../images/foldoff.gif" border="0">
											<%=menu.getMenuname()%>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr style="display:'none'" id="follow<%=menu.getMenucode()%>">
							<td>
								<table width='100%' border="0" align=center cellpadding='0' cellspacing='0'>
<%
					List<SysMenuDO> menu2list = menu.getSysMenuDOList();
					for(SysMenuDO m2:menu2list){
						if(us.getIssys()!=1 && !permissionAO.isAuthenMenu(us.getUserid(),m2.getId())){
							continue;
						}
%>
						<tr>
							<td>
								<table border="0" cellspacing="0" cellpadding="0" width="100%">
									<tr valign="middle">
										<td>
											&nbsp;&nbsp;
											<img src="./../images/arrow.gif" align="middle" border="0">
											<a href="#" onClick="openLink('<%=m2.getMenuurl()%>');"><%=m2.getMenuname()%></a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
									<tr>
										<td>
											<table border="0" cellspacing="0" cellpadding="0" width="100%">
												<tr onClick="showThirdLevel('<%=m2.getMenucode()%>');" style="cursor:hand;">
													<td>
														&nbsp;&nbsp;
														<img id="imgThird<%=m2.getMenucode()%>" src="./../images/open.gif" border="0">
														<img id="imgfoldThird<%=m2.getMenucode()%>" src="./../images/foldoff.gif" border="0">
														<%=m2.getMenuname()%>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr style="display:'none'" id="followThird<%=m2.getMenucode()%>">
										<td>
											<table width='100%' border="0" cellpadding='0' cellspacing='0'>
<%
	List<SysMenuDO> menu3list = m2.getSysMenuDOList();
	for(SysMenuDO m3:menu3list){
		if(us.getIssys()!=1 && !permissionAO.isAuthenMenu(us.getUserid(),m3.getId())){
			continue;
		}
%>
												<tr>
													<td>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<img src="./../images/arrow.gif" align="middle" border="0">
														<a href="#" onClick="openLink('<%=m3.getMenuurl()%>');"><%=m3.getMenuname()%></a>
													</td>
												</tr>
<%
	}
%>
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
}
%>
					</table>
				</td>
			</tr>
		</table>
		
	</body>
</html>