document.write('<div id="mw_header">');document.write('<table cellpadding="0" cellspacing="0">');document.write('<tr>');document.write('<td><a href="/" title="welcom to meiwa.cc —— 美娃~西西"><img src="/image/logo.gif" border="0" alt="welcom to meiwa.cc"/></a></td>');document.write('<td valign="bottom" style="border-bottom:1px solid gray" width="100%">');document.write('<ul>');var url = document.URL;var root = 'http://meiwa.cc/';String.prototype.startWith = function(str){var t=this;return t.indexOf(str) == 0;};var isEmail = (url == root + 'email')||(url == root + 'email/');var isFavorite = (url == root + 'favorite' || url == root + 'favorite/');var isEssential = (url == root + 'essential' || url == root + 'essential/');
if(isEssential)
 document.write('<li title="保存从网上浏览到的精华内容">精华库</li>');else{ if(typeof isViewOthers != 'undefined' && isViewOthers && userName)
 document.write('<li><a id="header_nav_essential_other" href="/essential/user.aspx/' + userName + '" title="保存从网上浏览到的精华内容">精华库</a></li>'); else
 document.write('<li><a id="header_nav_essential" href="/essential/" title="方便快捷的保存从网上浏览到的精华内容，用标签整理分类内容">精华库</a></li>');}if(isFavorite)
 document.write('<li title="保存精华网址">收藏夹</li>');else{ if(typeof isViewOthers != 'undefined' && isViewOthers && userName)
 document.write('<li><a href="/favorite/user.aspx/' + userName + '" title="保存精华网址">收藏夹</a></li>'); else
 document.write('<li><a href="/favorite" id="header_nav_favorite" title="网络收藏夹，保存精华网址，随时随地上网就可以查看">收藏夹</a></li>');}if(isEmail)
 document.write('<li title="将不方便保存到精华库的内容直接发送到邮箱中">网页邮</li>');else{ if(typeof isViewOthers != 'undefined' && isViewOthers && userName)
 document.write('<li><a href="/email/?others=true&userName=' + userName + '" title="将不方便保存到精华库的内容直接发送到邮箱中">网页邮</a></li>'); else
 document.write('<li><a href="/email/" title="基于Email的收藏夹，将选定内容直接发送到邮箱中">网页邮</a></li>');}document.write('</ul>');document.write('</td>');document.write('</tr>');document.write('</table>');document.write('</div>');
function welcome(){ var html = ''; if(window.mw && window.mw.IS_LOGIN)
 { html += '欢迎您：<a href="/essential/my/">' + window.mw.LOGIN_USER_NAME + '</a>'; html += '&nbsp; | &nbsp;'; html += '<a href="/account/logout.aspx">退出</a>'; html += '&nbsp; | &nbsp;'; html += '<a href="/account/update.pwd.aspx">修改密码</a>'; crtInvite(); } else
 { if(document.location.pathname != '/account/login.aspx'){ if(document.location.pathname == '/account/logout.aspx')
 html += '<a href="/account/login.aspx">登录</a>'; else
 html += '<a href="/account/login.aspx?returnUrl=' + document.URL.encodeURI() + '">登录</a>'; html += '&nbsp; | &nbsp;'; } html += '<a href="/account/register.htm">注册</a>'; } html += '&nbsp; | &nbsp;'; html += '<a href="/howto/" title="需要帮助请点我" id="anchorHelp"><img align="baseline" src="/image/help.gif" border="0"/></a>'; var odiv = document.createElement('div'); odiv.id = 'divWelcome'; odiv.align = 'right'; odiv.style.cssText = 'position:absolute;top:17px;right:10px;'; odiv.innerHTML = html; document.body.appendChild(odiv); crtFeedbackLink(); 
 var footTop = getY($id('mwfooter')); 
 var f = function(){ footTop = getY($id('mwfooter')); if($id('anchorInvite'))$id('anchorInvite').style.top = footTop - $id('anchorInvite').offsetHeight; if($id('anchorFeedback')){ $id('anchorFeedback').style.top = footTop - 35; } }; if(footTop < 200)
 setTimeout(f,3000); else
 f();}function crtInvite(){ if(url != 'http://meiwa.cc/account/invite.htm')
 if(!$id('anchorInvite'))crt('a',{href:'/account/invite.htm',title:'把meiwa.cc推荐给您的朋友',id:'anchorInvite',innerHTML:'推荐'},document.body);}function crtFeedbackLink(){ if(!$id('anchorFeedback'))crt('a',{href:'javascript:Feedback.show();',title:'给meiwa.cc反馈，您的反馈对我们非常重要',id:'anchorFeedback',innerHTML:'反馈'},document.body);}addLoadEvent(welcome);var Feedback = {id:'divMWFeedback',userNameId:'txtMWFUserName',emailId:'txtMWFEmail',contentId:'txtMWFBody',show:function(){ var ofeed = $id(this.id); if(ofeed){ ofeed.style.display = ''; }else{ var html = '<form onsubmit="return false;">'+
 '<table border="0" cellspacing="1" cellpadding="2">'+
 ' <tr>'+
 ' <td colspan="2" align="center" id="tdMWFeedbackTitle">'+
 ' <font color="green">您的反馈对我们非常重要</font>'+
 ' </td>'+
 ' </tr>'+
 ' <tr>'+
 ' <td>'+
 ' 用户名：'+
 ' </td>'+
 ' <td>'+
 ' <input type="text" id="txtMWFUserName" value="' + (window.mw.IS_LOGIN?window.mw.LOGIN_USER_NAME:'GUEST') + '" size="50" maxlength="50"/>'+
 ' </td>'+
 ' </tr>'+
 ' <tr>'+
 ' <td>Email：</td>'+
 ' <td>'+
 ' <input type="text" id="txtMWFEmail" value="' + (window.mw.IS_LOGIN?window.mw.LOGIN_USER_EMAIL:'') + '" size="50" maxlength="50"/>'+
 ' </td>'+
 ' </tr>'+
 ' <tr>'+
 ' <td>反馈内容：</td>'+
 ' <td>'+
 ' <textarea id="txtMWFBody" cols="' + (ie()?33:31) + '" rows="5"></textarea><font color="red">*</font>'+
 ' </td>'+
 ' </tr>'+
 ' <tr>'+
 ' <td>&nbsp;</td>'+
 ' <td>'+
 ' <input type="submit" onclick="return Feedback.submit()" value="提交"/>'+
 ' &nbsp;'+
 ' <input type="button" onclick="Feedback.cancle()" value="取消"/>'+
 ' </td>'+
 ' </tr>'+
 '</table>'+
 '</form>'; 
 var odiv = crt('div',{id:this.id,innerHTML:html},document.body); if(document.body.scrollTop > 0)odiv.style.top = getY(odiv) + document.body.scrollTop; drag(odiv,$id('tdMWFeedbackTitle'),false,false); } 
},submit:function(){ var userName = $id(this.userNameId).value; var email = $id(this.emailId).value; var body = $id(this.contentId).value; var postBody = 'userName=' + userName.encodeURI() + '&email=' + email.encodeURI() + '&content=' + body.encodeURI() + '&pageUrl=' + url.encodeURI(); if(body.trim() == ''){ alert('请输入反馈的内容。'); $id(this.contentId).focus(); return false; } 
 var f = function(){ var osuccess = crt('div',{id:'divAddFeedbackSuccess',innerHTML:'谢谢您的反馈。<hr noshadow="true" /><br/><input type="button" onclick="remove(this.parentNode)" value="关闭"/>',align:'center'},document.body); if(document.body.scrollTop > 0)osuccess.style.top = getY(osuccess) + document.body.scrollTop; window.setTimeout(function(){if($id('divAddFeedbackSuccess'))remove($id('divAddFeedbackSuccess'));},2000); }; new Ajax({url:'/ajax/userAccount/feedback.aspx',method:'post',postBody:postBody,onComplete:f }); this.cancle(); return false;},cancle:function(){ var ofeed = $id(this.id); if(ofeed){ remove(ofeed); }}};

/*在非首页的页面上面显示最新的收藏精华链接*/
if(url != 'http://meiwa.cc/' && url != 'http://meiwa.cc/' && (!url.startWith('http://meiwa.cc/?')) && (!url.startWith('http://meiwa.cc?'))){ var newHtml = '<TABLE BORDER="0" CELLSPACING="1" CELLPADDING="0">' +
 ' <TR>' +
 ' <TD align="center">' +
 ' <a href="/essential/recent.aspx" title="美娃~西西最新精华">' +
 ' <img src="/image/essentialbox.gif" border="0"/>' +
 ' </a>' +
 ' </TD>' +
 ' <td width="5"><img width="1" height="1"/></td>'+
 ' <TD align="center">' +
 ' <a href="/favorite/recent.aspx" title="美娃~西西最新收藏">' +
 ' <img src="/image/favoritefolder.gif" border="0"/>' +
 ' </a>' +
 ' </TD>' +
 ' </TR>' +
 ' <TR>' +
 ' <TD align="center">' +
 ' <a href="/essential/recent.aspx" title="美娃~西西最新精华">' +
 ' 最新精华' +
 ' </a>' +
 ' </TD>' +
 ' <td width="5"><img width="1" height="1"/></td>'+
 ' <TD align="center">' +
 ' <a href="/favorite/recent.aspx" title="美娃~西西最新收藏">' +
 ' 最新收藏' +
 ' </a>' +
 ' </TD>' +
 ' </TR>' +
 '</TABLE>'; var odiv = document.createElement('div'); odiv.id = 'divMWNew'; odiv.align = 'center'; odiv.style.cssText = 'position:absolute;top:4px;right:240px;width:200px'; odiv.innerHTML = newHtml; document.body.appendChild(odiv);}
var odiv = document.createElement('div');odiv.id = 'divMWVersion';odiv.align = 'right';odiv.style.cssText = 'position:absolute;top:10px;font-size:8px;left:72px;color:green;width:100px;font-weight:light';odiv.innerHTML = '内部测试版';document.body.appendChild(odiv);