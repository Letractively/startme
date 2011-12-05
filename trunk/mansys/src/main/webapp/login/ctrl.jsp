<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<html>
	<head>
		<title>Frames Test</title>
		<script language="JavaScript">
		function showHideFrame(){
			try{
				if(parent.homeFrame.cols=="200,8,*"){
					parent.homeFrame.cols = "0,8,*";
					parent.ctrlFrame.menuPowerImage.src = "./../images/menuon.gif";
					parent.ctrlFrame.menuPowerImage.alt = "µã»÷ÏÔÊ¾";
				}
				else if(parent.homeFrame.cols=="0,8,*"){
					parent.homeFrame.cols = "200,8,*";
					parent.ctrlFrame.menuPowerImage.src = "./../images/menuoff.gif";
					parent.ctrlFrame.menuPowerImage.alt = "µã»÷Òþ²Ø";
				}
			}
			catch(re){
			}
		}
		</script>
	</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#e1e6ee">
	<div style="position:absolute;top:0;left:0;">
	<img src="./../images/menuoff.gif" style="cursor:hand;" alt="µã»÷Òþ²Ø" name="menuPowerImage" onClick="showHideFrame();" >
	</div>
	</body>
</html>

