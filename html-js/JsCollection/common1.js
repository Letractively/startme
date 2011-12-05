/*判断用户的浏览器是否支持cookie*/
function checkCookieSupport(){ var isSupport = false; if(typeof(navigator.cookieEnabled) != 'undefined')
 isSupport = navigator.cookieEnabled; else{ document.cookie = 'test'; isSupport = document.cookie == 'test'; document.cookie = ''; } if(!isSupport){ alert('您的浏览器禁用了Cookie。\n使用美娃~西西需要您的浏览器支持Cookie。'); }}function setInTopTarget(){ if(top.location.href != self.location.href)top.location.href = top.location.href;}var COOKIE_DOMAIN = 'meiwa.cc';var COOKIE_EXPIRES = 10 * 365;var ID = {i:-1,get:function(){this.i++;return this.i;}};var Class = { create: function() { return function() { this.initialize.apply(this, arguments) ;};}};Function.prototype.bind = function(o) { var __method = this; return function() { return __method.apply(o, arguments) ;};};function doNothing(){}
window.onerror = doNothing;
function ie(){ return navigator.appName == 'Microsoft Internet Explorer';}function ns(){ return navigator.appName == 'Netscape';}function hasDotNetFramework(){ return navigator.userAgent.indexOf(".NET CLR") > -1;}
function watch(o){ if(o){ var str = ''; var k = 0; for(var a in o){ k++; str += a + '--' + o[a] + '\n'; if(k == 10){ alert(str); k = 0; str = ''; } } }else{ alert(o + 'is null or undefined or false.'); }}

function crt(tag,attributes,parent){ var o = document.createElement(tag); if(attributes){ for(var att in attributes){ if(att == 'style'){ o.style.cssText = attributes[att]; continue; } o[att]=attributes[att]; } } if(parent && parent.appendChild){ parent.appendChild(o); } return o;}
function getCookie( name ) { var start = document.cookie.indexOf( name + "=" ); var len = start + name.length + 1; if ( ( !start ) && ( name != document.cookie.substring( 0, name.length ) ) ) { return null; } if(start == -1) return null; var end = document.cookie.indexOf( ";", len ); if ( end == -1 ) end = document.cookie.length; return unescape( document.cookie.substring( len, end ) );}
function setCookie( name, value, expires, path, domain, secure) { var today = new Date(); today.setTime( today.getTime() ); if ( expires ) { expires = expires * 1000 * 60 * 60 * 24; }else{ expires = COOKIE_EXPIRES * 1000 * 60 * 60 * 24; } var expires_date = new Date( today.getTime() + (expires) ); document.cookie = name+"="+escape( value ) +
 ( ( expires ) ? ";expires="+expires_date.toGMTString() : "" ) + 
 ( ( path ) ? ";path=" + path :"") +
 ( ( domain ) ? ";domain=" + domain : "domain=" + COOKIE_DOMAIN) +
 ( ( secure ) ? ";secure" : "" );} 
function deleteCookie( name, path, domain ) { if ( getCookie( name ) ) document.cookie = name + "=" +
 ( ( path ) ? ";path=" + path : ";path=" + COOKIE_PATH) +
 ( ( domain ) ? ";domain=" + domain : "domain=" + COOKIE_DOMAIN) +
 ";expires=Thu, 01-Jan-1970 00:00:01 GMT";}
function windowHeight() { return self.innerHeight || document.documentElement.clientHeight || document.body.clientHeight || 0; }
function windowWidth() { return self.innerWidth || document.documentElement.clientWidth || document.body.clientWidth || 0; }

function getY(o){ var y = 0;
 if (o.offsetParent) while (o.offsetParent) { y += o.offsetTop; o = o.offsetParent; }
 return y;
}
function getX(o){ var x = 0
 if (o.offsetParent) while (o.offsetParent) { x += o.offsetLeft; o = o.offsetParent ;}
 return x;
}

function displayShift(o){
 o.style.display = (o.style.display=='none'?'':'none');
}

function pageScrollHeight() { if(ie())return document.body.scrollHeight;return document.documentElement.scrollHeight || document.body.scrollHeight || 0 ;}
function pageScrollWidth() { if(ie())return document.body.scrollWidth;return document.documentElement.scrollWidth || document.body.scrollWidth || 0 ;}

function pageScrollY() { return self.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0 ;}
function pageScrollX() { return self.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0 ;}
function remove(o){
 o.parentNode.removeChild(o);
}

function confirmIt(link,yesfunc,nofunc){ var yesText = 'yes'; var noText = 'no'; var confrimText = '&nbsp;?&nbsp;'; var linkParent = link.parentNode; 
 var nextObj = link.nextSibling; if(nextObj && nextObj.tagName == 'SPAN' && nextObj.innerHTML == confrimText)return false; 
 var questionMark = document.createElement('SPAN'); questionMark.innerHTML = confrimText; questionMark.style.color = 'red'; if(nextObj)
 linkParent.insertBefore(questionMark,nextObj); else
 linkParent.appendChild(questionMark); 
 var yes = document.createElement('a'); yes.innerHTML = yesText; yes.href = link.href; if(yesfunc){ yes.onclick = function(){ if((!link.href.include('#')) && (!link.href.include('javascript:'))){ new Ajax({url:link.href,method:'post'}); } yesfunc(link); return false; }; } yes.target = '_top'; yes.style.color = 'red'; yes.style.fontSize = '8pt'; yes.style.textDecoration = 'none'; if(nextObj)
 linkParent.insertBefore(yes,nextObj); else
 linkParent.appendChild(yes); 
 var separator = document.createElement('span'); separator.innerHTML = '&nbsp;/&nbsp;'; separator.style.color = 'red'; if(nextObj)
 linkParent.insertBefore(separator,nextObj); else
 linkParent.appendChild(separator);

 var no = document.createElement('a'); no.innerHTML = noText; no.href = 'javascript:;'; no.style.color = 'red'; no.style.fontSize='8pt'; no.style.paddingRight = '6px'; no.style.textDecoration = 'none'; var noClick = false; if(nofunc){ noClick = function(a){doNo(a);nofunc();}; }else{ noClick = function(a){doNo(a);}; } no.onclick = function(){noClick(this)}; if(nextObj)
 linkParent.insertBefore(no,nextObj); else
 linkParent.appendChild(no); return false;}function doNo(link){ var linkParent = link.parentNode; linkParent.removeChild(link.previousSibling.previousSibling.previousSibling); linkParent.removeChild(link.previousSibling.previousSibling); linkParent.removeChild(link.previousSibling); linkParent.removeChild(link);}
function $id(id){return document.getElementById(id);}function $tags(t,o){ o=o||document; return o.getElementsByTagName(t); }
function $tag(t,o,i) { o=o||document; return o.getElementsByTagName(t)[i||0] ;}

String.prototype.include = function(t) { return this.indexOf(t) >= 0 ? true : false; };
String.prototype.trim = function(){ var r = /^\s+|\s+$/g;return this.replace(r,'');};
String.prototype.unescHtml = function(){ var i,e={'&lt;':'<','&gt;':'>','&amp;':'&','&quot;':'"'},t=this; for(i in e) t=t.replace(new RegExp(i,'g'),e[i]); return t;};
String.prototype.escHtml = function(){ var i,e={'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;'},t=this; for(i in e) t=t.replace(new RegExp(i,'g'),e[i]); return t;};
String.prototype.escAttr = function(){var t = this; t = t.replace('"',"&quot;");return t;};
String.prototype.encodeURI = function(){var t = this;return encodeURIComponent(t);};
String.prototype.decodeURI = function(){var t = this;var t1 = decodeURIComponent(t); while(t != t1){t=t1;t1=decodeURIComponent(t);}return t;};
String.prototype.format = function(){var t=this;for(var i=0;i<arguments.length;i++){t = t.replace('{' + (i) + '}',arguments[i]);}return t;};
String.prototype.isEmail = function(){return /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(this);};
String.prototype.startWith = function(str){var t=this;return t.indexOf(str) == 0;};
String.prototype.endWith = function(str){var t = this;return t.substring(t.length-str.length,t.length) == str;};
String.prototype.isUrl = function(){var reg = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/; 
var t = this; return reg.test(t);};

function drag(dragObj,fireDragObj,range,whenDragFun){
 fireDragObj.onmousedown=function(ev){
 var d=document;if(!ev)ev=window.event;
 var x=ev.layerX?ev.layerX:ev.offsetX,y=ev.layerY?ev.layerY:ev.offsetY;
 if(dragObj.setCapture)
 dragObj.setCapture();
 else if(window.captureEvents)
 window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);

 d.onmousemove=function(ev){
 if(!ev)ev=window.event;
 if(!ev.pageX)ev.pageX=ev.clientX;
 if(!ev.pageY)ev.pageY=ev.clientY;
 var tx= d.body.scrollLeft + (ev.pageX-x),ty = d.body.scrollTop + (ev.pageY-y);
 
 if(range){
 tx = tx<range[0]?range[0]:tx>range[1]?range[1]:tx
 ty = ty<range[2]?range[2]:ty>range[3]?range[3]:ty;
 }
 dragObj.style.left = tx;
 dragObj.style.top = ty;
 
 if(whenDragFun)
 whenDragFun(dragObj);
 };

 d.onmouseup=function(ev){
 if(!ev)ev=window.event;
 if(ev.srcElement)if(ev.srcElement.tagName=='IMG'){return;}
 if(dragObj.releaseCapture)
 dragObj.releaseCapture();
 else if(window.captureEvents)
 window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
 
 d.onmousemove=null;
 d.onmouseup=null;
 };
 };
}

function previousElement(o) {
 do{o = o.previousSibling;}while(o && o.nodeType != 1)
 return o;
}

function nextElement(o) {
 do{o = o.nextSibling;} while(o && o.nodeType != 1)
 return o;
}

function addLoadEvent(f) { 
 var old = window.onload;
 if (typeof old != 'function') window.onload = f;
 else { window.onload = function() { old(); f() ;};}
}


function addUnloadEvent(f) { var old = window.onunload;
 if (typeof old != 'function') window.onunload = f;
 else { window.onunload = function() { old(); f();}}
}

function addEvent(elm, evType, fn, useCapture) { if (elm.addEventListener) { elm.addEventListener(evType, fn, useCapture); return true; } else if (elm.attachEvent) { var r = elm.attachEvent('on' + evType, fn); return r; } else { elm['on' + evType] = fn; }}

function getSrcElement(ev){
 return ev.srcElement || ev.target || ev.relatedTarget;
}

function setQuickSubmit(oArray,fun){
 if((!oArray.length) || oArray.length == 0)return;
 var f = function(e){
 e = e || window.event;
 if(e.keyCode == 13 && (e.altKey || e.ctrlKey))
 fun();
 };
 for(var i=0;i<oArray.length;i++){
 var o = oArray[i];
 addEvent(o,'keyup',f,true);
 }
}

function setEscCancel(o,fun){
 if(typeof o.onkeyup != 'undefined'){
 var f = function(e){
 e = e || window.event;
 if(e.keyCode == 27)
 fun();
 }
 
 addEvent(o,'keyup',f,true);
 
 }
}

function setFocus(o){
 if(typeof o.focus != 'undefined')
 o.focus();
}

function getQueryParam(paramName){ var oRegex = new RegExp( '[\?&]' + paramName + '=([^&]+)', 'i' ) ; var oMatch = oRegex.exec( window.top.location.search ) ; 
 if ( oMatch && oMatch.length > 1 )
 return decodeURIComponent( oMatch[1] ) ; else
 return '' ;}

function getPageInfoHtml(pageCount,pageNo,onClickFunFormat){ if(pageCount == 0){ return '没有记录，了解如何使用，请点击<a href="/howto/" target="_blank">此处</a>。'; } 
 var pageHtml = '共' + pageCount + '页，第' + pageNo + '页 &nbsp; '; if(pageNo > 1){ pageHtml += '<a href="javascript:;" title="上一页" onclick="' + onClickFunFormat.format(pageNo-1) + '">上一页</a>'; pageHtml += '&nbsp;&nbsp;&nbsp;'; } var pageStart = 1; var pageEnd = pageCount; if(pageCount > 10 && pageNo > 5){ pageStart = pageNo - 5; } if(pageCount > 10){ pageEnd = pageStart + 9 > pageCount ? pageCount : pageStart + 9; } for(var i=pageStart;i<= pageEnd;i++){ var anchorHtml = ''; if(i == pageNo)
 anchorHtml = i; else
 anchorHtml = '<a href="javascript:;" title="第' + i + '页" onclick="' + onClickFunFormat.format(i) + '">' + i + '</a>'; 
 if(i < pageEnd)
 anchorHtml += '&nbsp;|&nbsp;'
 pageHtml = pageHtml + anchorHtml; } if(pageNo < pageCount){ pageHtml += '&nbsp;&nbsp;&nbsp;'; pageHtml += '<a href="javascript:;" title="下一页" onclick="' + onClickFunFormat.format(pageNo+1) + '">下一页</a>'; } pageHtml = '<nobr>' + pageHtml + '</nobr>'
 return pageHtml;}
function friendlyDate(date){ var now = new Date(); var nY = now.getFullYear(); var dY = date.getFullYear(); var nM = now.getMonth(); var dM = date.getMonth(); var nD = now.getDate(); var dD = date.getDate(); var nH = now.getHours(); var dH = date.getHours(); var nMM = now.getMinutes(); var dMM = date.getMinutes(); if(nY > dY){ return dY + '年' + dM + '月' + dM + '日'; } if(nM > dM){ return dM + '月' + dD + '日' + dH + '点'; } if(nD > dD && nD - dD < 10){ if(nD - dD == 1){ return '昨天' + dH + '点' + dMM + '分'; } if(nD - dD == 2){ return '前天' + dH + '点' + dMM + '分'; } return (nD - dD) + '天前'; } if(nD > dD && nD - dD > 10){ return dM + '月' +dD + '日' + dH + '点'; } if(nH > dH && nH - dH > 5){ return '今天' + dH + '点' + dMM + '分'; } if(nH != dH){ return (nH - dH) + '小时前'; } if(nMM == dMM){ var nS = now.getSeconds(); var dS = date.getSeconds(); return (nS - dS) +'秒钟之前'; } return nMM - dMM + '分钟之前';}


var Ajax = Class.create();
Ajax.onLoad = function(){ if($id('ajaxLoading') == null || !$id('ajaxLoading')){ var img = new Image(32,15); img.src = '/image/indicator.gif'; var div = crt('div',{id:'ajaxLoading',style:'display:none;height:15px;width:32px;z-index:10;position:absolute;left:' + (windowWidth()/2) + 'px;top:' + (windowHeight()/2) + 'px'},document.body); div.appendChild(img); }};Ajax.level = 0;addLoadEvent(Ajax.onLoad);Ajax.onLoading = function(){ Ajax.level ++; $id('ajaxLoading').style.display = '';};Ajax.onLoaded = function(){ Ajax.level --; if(Ajax.level == 0)$id('ajaxLoading').style.display = 'none';};Ajax.onError = function(transport){ var xml = transport.responseXML; if(typeof xml == 'undefined' || xml == null)return false; var errorNode = false; if(typeof xml.selectNodes != 'undefined'){ errorNode = xml.selectNodes("/mw_error")[0]; }else{ errorNode = xml.getElementsByTagName('mw_error')[0]; } if(errorNode){ alert("对不起，系统发生错误，请稍候再试。"); return true; } return false;};Ajax.checkUserNameAndPass = function(){ if($id('txtUserName')){ if($id('txtUserName').value.trim().length < 5){ alert('您输入的用户名无效，用户名的最小长度为5。'); $id('txtUserName').focus(); return false; } } if($id('txtPassword')){ if($id('txtPassword').value.trim().length < 5){ alert('您输入的用户名无效，用户名的最小长度为5。'); $id('txtPassword').focus(); return false; } } return true;};Ajax.onNotLogin = function(transport){ var xml = transport.responseXML; if(typeof xml == 'undefined' || xml == null)return false; var noLoginTag = false; if(typeof xml.selectNodes != 'undefined'){ noLoginTag = xml.selectNodes("//nologin")[0]; }else{ noLoginTag = xml.getElementsByTagName('nologin')[0]; } if(noLoginTag){ if(!$id('ajaxNotLogin')){ var html = '<h2 style="text-align:center">请您登录或<a href="/account/register.htm" target="_top" style="font-size:1.1em">注册</a></h2>'
 + '<form name="formLogin" onsubmit="return Ajax.checkUserNameAndPass()" action="/account/login.aspx?target=_self&returnUrl=' + document.URL.encodeURI() + '" method="post" target="_self">'
 + '<table width="100%" border="0">'
 + ' <tr>'
 + ' <td width="20%">'
 + ' 用户名：'
 + ' </td>'
 + ' <td>'
 + ' <input type="text" name="userName" size="50" tabindex="1" id="txtUserName"/>'
 + ' </td>'
 + ' </tr>'
 + ' <tr>'
 + ' <td>密 &nbsp; 码：</td>'
 + ' <td>'
 + ' <input type="password" name="password" size="50" tabindex="2" id="txtPassword"/>'
 + ' </td>'
 + ' </tr>'
 + ' <tr>'
 + ' <td colspan="2" height="40">'
 + ' <input tabindex="3" type="submit" value="&raquo;&nbsp;登录" />&nbsp;'
 + ' <input tabindex="4" type="button" onclick="remove($id(\'ajaxNotLogin\'))" value="&raquo;&nbsp;取消" />'
 + ' </td>'
 + ' </tr>'
 + '</table>'
 + '</form>'; var left = (windowWidth() - 400)/2; left = left < 0?0:left; crt('div',{innerHTML:html,style:'padding:4px;background-color:#f0f0f0;border:1pt ridge;left:'+left+'px;width:400px;position:absolute;top:100px;',id:'ajaxNotLogin'},document.body); window.setTimeout(function(){try{$id('txtUserName').focus();}catch(err){}},200); } return true; } if($id('ajaxNotLogin'))remove($id('ajaxNotLogin')); return false;};
Ajax.prototype = { initialize: function(options){ this.transport = this.getTransport(); this.postBody = options.postBody || ''; this.method = options.method || 'post'; this.onComplete = options.onComplete || null; this.update = document.getElementById(options.update) || null; this.request(options.url); },
 request: function(url){ Ajax.onLoading(); this.transport.open(this.method, url, true); this.transport.onreadystatechange = this.onStateChange.bind(this); if (this.method == 'post') { this.transport.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); if (this.transport.overrideMimeType) this.transport.setRequestHeader('Connection', 'close'); } this.transport.send(this.postBody); },
 onStateChange: function(){ if (this.transport.readyState == 4 && this.transport.status == 200) { Ajax.onLoaded(); if(Ajax.onNotLogin(this.transport))return; if(Ajax.onError(this.transport))return; 
 if (this.onComplete)
 setTimeout(function(){this.onComplete(this.transport)}.bind(this), 10); if (this.update)
 setTimeout(function(){this.update.innerHTML = this.transport.responseText}.bind(this), 10); this.transport.onreadystatechange = doNothing; }},
 getTransport: function(){ if (window.ActiveXObject) return new ActiveXObject('Microsoft.XMLHTTP'); else if (window.XMLHttpRequest) return new XMLHttpRequest(); else return false;}};
