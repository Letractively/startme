<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<link href="./../assets/css/style.css" rel="stylesheet" type="text/css">
		<script src="./../assets/js/calendar.js" type=text/javascript></script>
		  <script language="javascript">
   var CalendarWebControl = new atCalendarControl();

   function createMenu(){
    var menubar=new Array("文件","编 辑","查查找","文档");
    var menupad=new Array();
    menupad[0]=new Array();
    menupad[0][0]=new Array("打开","http://agetime.joo.cn");
    menupad[0][1]=new Array("--");
    menupad[0][2]=new Array("保存","http://agetime.joo.cn","_blank");

    menupad[1]=new Array();
    menupad[1][0]=new Array("复制","http://agetime.joo.cn","aa",true);
    menupad[1][1]=new Array("选择");
    menupad[1][2]=new Array("--");
    menupad[1][3]=new Array("删除");

 menupad[2]=new Array();
    menupad[2][0]=new Array("替换");
    menupad[2][1]=new Array("--");
    menupad[2][2]=new Array("无间道");
    menupad[2][3]=new Array("无孔不入"); 

 
    var menu=new atMenu(menubar,menupad);
   }
  </script>
		<title>menu show</title>
		</head>
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" bgcolor="#ffffff">
	<br><br>
	<div>
	<font size="5pt" color="#124D78">请设置数据分析的时间范围：</font>
	</div>
	<form name="fm">
	<table align="center" >
	<tr>
	<td height="10">
	</td>
	</tr>
	<tr>
	<td>
	本期-开始日期: 
    <input name="p_start_date" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_start_date,'',this);" title="显示日历" /> 
    </td>
    <td>
    本期-开始日期: 
    <input name="p_end_date" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_end_date,'',this);" title="显示日历" /> 
    </td>
    </tr>
    <tr>
    <td>
    环比-开始日期: 
    <input name="p_start_date_h" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_start_date_h,'',this);" title="显示日历" /> 
    </td>
    <td>
    环比-开始日期: 
    <input name="p_end_date_h" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_end_date_h,'',this);" title="显示日历" /> 
	</td>
    </tr>
    <tr>
    <td>
	同比-开始日期: 
    <input name="p_start_date_t" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_start_date_t,'',this);" title="显示日历" /> 
    </td>
    <td>
    同比-开始日期: 
    <input name="p_end_date_t" type="text" id="dateInput" size="12" maxlength="12" readonly />
    <img src="./../images/date.gif" style="cursor:hand;" onClick="CalendarWebControl.show(fm.p_end_date_t,'',this);" title="显示日历" /> 
    </td>
    </tr>
    <tr>
    </table>
	<p align="center"><input type="button" onClick="saveTime();" value=" 确 定 "></p>
	</form>
	<br><br><br><br><br><br><br><br><br><br>
	</body>
</html>
<script type="text/javascript">
function saveTime(){
	parent.leftFrame.p_start_date.value = fm.p_start_date.value;
	parent.leftFrame.p_end_date.value = fm.p_end_date.value;
	parent.leftFrame.p_start_date_t.value = fm.p_start_date_t.value;
	parent.leftFrame.p_end_date_t.value = fm.p_end_date_t.value;
	parent.leftFrame.p_start_date_h.value = fm.p_start_date_h.value;
	parent.leftFrame.p_end_date_h.value = fm.p_end_date_h.value;
	alert("保存成功！");
	window.location="./../login/main.jsp";
}
init();
function init(){
	fm.p_start_date.value = parent.leftFrame.p_start_date.value;
	fm.p_end_date.value = parent.leftFrame.p_end_date.value;
	fm.p_start_date_t.value = parent.leftFrame.p_start_date_t.value;
	fm.p_end_date_t.value = parent.leftFrame.p_end_date_t.value;
	fm.p_start_date_h.value = parent.leftFrame.p_start_date_h.value;
	fm.p_end_date_h.value = parent.leftFrame.p_end_date_h.value;
}
</script>
