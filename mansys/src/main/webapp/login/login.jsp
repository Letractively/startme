<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="com.yehongyu.mansys.ao.vo.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<script src="./../assets/js/verify.js" type="text/javascript"></script>
		<title>��̨����ϵͳ</title>
		<style>
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		A:link,A:visited,A:hover,A:active{
			text-decoration:none;
			color:#666;
			font-size:12px;
			line-height:22px;
		}
		.font{font-size:12px;color:#666;line-height:22px}
		.nav1{margin-top:90px;}
		.nav2{border-left:  1px solid #CBCBCB;border-right:  1px solid #CBCBCB;}
		.nav3{margin-top:35px;}
		/*��¼����*/
		.inp {
			border:1px solid #84a1bd;
			width:135px;
			height:20px; 
			padding:2px 2px 2px 2px ; 
			background-position: -70px -424px
		}
		.STYLE2 {line-height:22px; font-size: 12px;}
		</style>
	</head>
	<%
	  	String name = "";
	  	String info = "";
		if(!session.isNew()){
	    	UserSession us = (UserSession)session.getAttribute("usersession");
	    	if(us!=null) name = us.getUsername();
	    	info = (String)session.getAttribute("info");
	    	if(info==null)info="";
		}
		session.invalidate();
	%>
	<script>
	var info = "<%=info%>";
	if(info!=""){
		alert(info);
	}
	function checkInput(){
		if(fm.username.value.trim()==""){
			alert("�������û�����");
			fm.username.focus();
			return false;
		}
		if(fm.password.value.trim()==""){
			alert("���������룡");
			fm.password.focus();
			return false;
		}
		if(fm.savecookie.checked==true){
			var username = fm.username.value.trim();
			var password = fm.password.value.trim();
			var nextyear = new Date();
			nextyear.setFullYear(nextyear.getFullYear()+1);
			var namecookie = "username="+escape(username)+";expires="+nextyear.toGMTString();
			document.cookie = namecookie;
			var pwdcookie = "password="+escape(password)+";expires="+nextyear.toGMTString();
			document.cookie = pwdcookie;
		}else{
			var namecookie = "username=;";
			document.cookie = namecookie;
			var pwdcookie = "password=;";
			document.cookie = pwdcookie;
		}
		return true;
	}
	</script>
	<body>
		<form name="fm" action="./../loginsubmit" method="post" onSubmit="return checkInput();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="nav1">
			<tr>
				<td>
					<table width="742" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="469">&nbsp;</td>
							<td width="224">
							<img src="./../images/web_04.jpg" width="224" height="35" />
							</td>
							<td width="49">&nbsp;</td>
						</tr>
					</table>
					<table width="742" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="232">
							<img src="./../images/web_07.jpg" width="232" height="218" />
							</td>
							<td width="237"><img src="./../images/web_08.jpg" width="237" height="218" /></td>
							<td width="224">
								<table width="224" height="218" border="0" cellpadding="0" cellspacing="0" class="nav2">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0" cellpadding="0" id="LoginBox-input-table">
												<tr>
													<td width="54" height="33" align="right" class="font">
														�û���
													</td>
													<td align="center">
														<label>
															<input type="text" name="username" class="inp" onmouseover="this.style.borderColor='#9ecc00'" onmouseout="this.style.borderColor='#84a1bd'" />
														</label>
													</td>
												</tr>
												<tr>
													<td height="33" align="right" class="font">
														��&nbsp;&nbsp;��
													</td>
													<td align="center">
														<input type="password" name="password" class="inp" onmouseover="this.style.borderColor='#9ecc00'" onmouseout="this.style.borderColor='#84a1bd'" />
													</td>
												</tr>
												<tr>
													<td height="20" colspan="2" align="center" class="font">
														<input type="checkbox" name="savecookie" id="forsavecookie"><label for="forsavecookie">�����û���������</label>
													</td>
												</tr>
												<tr>
													<td height="32">
													</td>
													<td height="30" align="right" valign="bottom" id="LoginBox-check">
														<input type="reset" value="��  ��" title="��λ"/>
														<input type="submit" value="��  ½" title="��½ϵͳ"/>
														&nbsp;
													</td>
												</tr>
												<tr style="display:none">
													<td height="36" colspan="2" align="center">
														<input name="���汾���û�������" type="button" id="btn2" value="���汾���û�������" onclick="setCookie('ntes_mail_firstpage','netfolder')" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="49"><img src="./../images/web_10.jpg" width="49" height="218" /></td>
						</tr>
					</table>
					<table width="742" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="469">
								&nbsp;
							</td>
							<td width="224" height="25">
								<table width="224" height="30" border="0" cellpadding="0" cellspacing="0" class="nav2">
									<tr>
										<td align="center">
											<table width="90%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<a href="http://www.media.hc360.com" target="_blank">ǰ̨��ҳ</a>
													</td>
													<td>
														<a href="http://www.research.hc360.com" target="_blank">��ҵ�о���ҳ</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="224" height="7" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td><img src="./../images/web_17_15.jpg" width="224" height="7" /></td>
									</tr>
								</table>
							</td>
							<td width="49">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="742" border="0" align="center" cellpadding="0" cellspacing="0" class="nav3">
						<tr>
							<td height="1" bgcolor="#838383"></td>
						</tr>
					</table>
					<table width="742" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" align="center">
											<a href="http://www.media.hc360.com/lxwm/cmjj.htm" target="_blank">��������</a> <span class="font">|</span> <a href="http://www.hc360.com/ad/ad.htm" target="_blank">������</a> <span class="font">|</span> <a
												href="http://www.research.hc360.com/hyyj_job.shtml" target="_blank">��ƸӢ��</a> <span class="font">|</span> <a href="http://www.hc360.com/law.htm" target="_blank">��������</a> <span class="font">|</span> <a href="http://www.hc360.com/about/tousu.htm"
												target="_blank">�������</a> <span class="font">|</span> <a href="http://www.hc360.com/help.htm" target="_blank">��������</a> <span class="font">|</span> <a href="http://www.research.hc360.com/friends.shtml" target="_blank">��������</a>
										</td>
									</tr>
									<tr>
										<td height="30" align="center">
											<span class="font">
											�ͷ����棺010-82297075 �ܻ���010-82211822 ���棺010-82211922<br>
											Copyright&copy;2000-2005 goodideals.com. All Rights Reserved
											</span>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</form>
		<script type="text/script">
		function readCookie(){
			var allcookies = document.cookie;
			var pos = allcookies.indexOf("username=");
			if(pos!=-1){
				var start = pos + 9;
				var end = allcookies.indexOf(";",start);
				if(end==-1) end = allcookies.length;
				var username = allcookies.substring(start,end);
				fm.username.value = username;
			}
			pos = allcookies.indexOf("password=");
			if(pos!=-1){
				var start = pos + 9;
				var end = allcookies.indexOf(";",start);
				if(end==-1) end = allcookies.length;
				var password = allcookies.substring(start,end);
				fm.password.value = password;
			}
		}
		readCookie();
		</script>
	</body>
</html>