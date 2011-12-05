/*================================================================
calendar.js
================================================================*/
document.write('<iframe id=CalFrame name=CalFrame frameborder=0 src="/websale/js/calendarframe.html" style=display:none;position:absolute;z-index:100></iframe>');
document.onmousedown=hideCalendar;

function DatePicker(imgName, divName, formAndText, initYear, initMonth) {
    var cf = document.getElementById("CalFrame");
    var wcf = window.frames.CalFrame;
    
    if (!wcf.alreadyLoad) {
      alert("<iframe not loaded>");
      return;
    }
    
    if (cf.style.display == "block") cf.style.display = "none";
    
    var eT=0,eL=0,p=document.getElementById(imgName);
	var sT=document.body.scrollTop,sL=document.body.scrollLeft;
	var eH=p.height,eW=p.width;
	while(p&&p.tagName!="BODY"){eT+=p.offsetTop;eL+=p.offsetLeft;p=p.offsetParent;}
	cf.style.top=(document.body.clientHeight-(eT-sT)-eH>=cf.height)?eT+eH:eT-cf.height;
	cf.style.left=(document.body.clientWidth-(eL-sL)>=cf.width)?eL:eL+eW-cf.width;
	
	var objImg = document.getElementById(imgName);
	var idx = formAndText.indexOf(".");
	var objTxt = document.getElementById((idx >= 0) ? formAndText.substring(idx + 1) : formAndText);
	cf.style.display="block";
	
	wcf.initCalendar(objImg, objTxt, initYear, initMonth);
	
}

function hideCalendar() {
    var cf = document.getElementById("CalFrame");
    cf.style.display = "none";
}
