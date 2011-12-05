/**
 * @author Administrator
 */

//获取对象的绝对位置
function getPosition(e){
	var left = 0;
	var top  = 0;
	while (e.offsetParent){
		left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
		top  += e.offsetTop  + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);
		e     = e.offsetParent;
	}

	left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
	top  += e.offsetTop  + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);

	return {x:left, y:top};
}

//获取鼠标事件的位置
function mouseCoords(ev){

	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop
	};
}

Number.prototype.NaN0=function(){return isNaN(this)?0:this;}
//根据ID取得页面元素
function GE(idname){return document.getElementById(idname);}

//初始化页面
function initpage(){
	//当登录状态时，显示页面设置区域
	if(loginstatus=="1"){
		GE("pageset").style.display = "";
		GE('start').click();
	}
	//设置企业的页面（会同时设置布局）
	showSetWidth();
	//加载企业的主题样式
	showStyle();
	//显示页面背景
	showBackground();
	//加载鼠标样式
	showMouseStyle();
	//初始化模块到容器中
	initcon();
}
//初始化容器
function initcon(){
	//先清空容器
	smcon.innerHTML = "";
	bgcon.innerHTML = "";
	//加入各模块
	for(var i=0; i<modpool.childNodes.length; i++){
		if(modpool.childNodes[i].nodeName=='#text') continue;	//跳过TextNode对象
		if(modpool.childNodes[i].nodeName=='#comment') continue;	//跳过Comment对象
		modincon(modpool.childNodes[i]);
	}
	//重排模块位置(模块载入时没有按顺序加入)
	for(var i=0; i<bgcon.childNodes.length; i++){
		for(var j=0;j<bgcon.childNodes.length-1;j++){
			var mod = GE(bgcon.childNodes[j].getAttribute("id").substring(4));
			var modnext = GE(bgcon.childNodes[j+1].getAttribute("id").substring(4));
			if(parseInt(mod.getAttribute("order"))>parseInt(modnext.getAttribute("order"))){
				bgcon.insertBefore(bgcon.childNodes[j+1],bgcon.childNodes[j]);
			}
		}
	}
	for(var i=0; i<smcon.childNodes.length; i++){
		for(var j=0;j<smcon.childNodes.length-1;j++){
			var mod = GE(smcon.childNodes[j].getAttribute("id").substring(4));
			var modnext = GE(smcon.childNodes[j+1].getAttribute("id").substring(4));
			if(parseInt(mod.getAttribute("order"))>parseInt(modnext.getAttribute("order"))){
				smcon.insertBefore(smcon.childNodes[j+1],smcon.childNodes[j]);
			}
		}
	}
	//登录时初始化拖拽容器（传入容器ID名，每个容器下的第一级子元素做为一个拖拽项）
	if(loginstatus=="1"){
		var con = new Array();
		con.push(smcon);
		con.push(bgcon);
		init(con);
	}
}

//模块加入到容器中，mod(模块对象)
function modincon(mod){
	//验证模块对象
	if(mod==null||typeof(mod)=="undefined") {alert("传入的模块为空!");return false;}
	if(mod.getAttribute("isshow")==null||typeof(mod.getAttribute("isshow"))=="undefined"){alert("模块的是否显示参数未定义!");return false;}
	if(mod.id==null||typeof(mod.id)=="undefined"){alert("不参获取模块的ID！");return false;}
	if(mod.getAttribute("contype")==null||typeof(mod.getAttribute("contype"))=="undefined"){alert("模块未指定容器！");return false;}
	if(mod.getAttribute("order")==null||typeof(mod.getAttribute("order"))=="undefined"){alert("模块的顺序参数未定义!");return false;}
	if(mod.getAttribute("modname")==null||typeof(mod.getAttribute("modname"))=="undefined"){alert("模块名称未定义！");return false;}
	if(mod.getAttribute("isshow")!="1") return;
	//创建模块DIV对象
	var moddiv = document.createElement('DIV');
	moddiv.setAttribute("class","DragBox");
	moddiv.className = "DragBox";
	moddiv.setAttribute("overclass","OverDragBox");
	moddiv.setAttribute("dragclass","DragDragBox");
	moddiv.setAttribute("history","hisdiv");
	moddiv.setAttribute("id","item"+mod.id);
	//创建模块标题栏
	var modtitle = document.createElement('DIV');
	modtitle.setAttribute("class","boxtitle");
	if(loginstatus=="1") modtitle.style.cursor="move";
	else modtitle.style.cursor="default";
	modtitle.className = "boxtitle";
	var modtitleleft = document.createElement('DIV');
	modtitleleft.setAttribute("class","boxtitleleft");
	modtitleleft.className = "boxtitleleft";
	modtitle.appendChild(modtitleleft);
	modtitleleft.appendChild(document.createTextNode(mod.getAttribute("modname")));
	var modfloatc = document.createElement('DIV');
	modfloatc.className="condown";
	modfloatc.setAttribute("id","modfloatc"+mod.id);
	modfloatc.setAttribute("type","modfloatc");
	modfloatc.id="modfloatc"+mod.id;
	modfloatc.innerHTML = "&nbsp;";
	modtitle.appendChild(modfloatc);
	var modmanage = document.createElement('DIV');
	if(loginstatus=="1")
		modmanage.innerHTML = GE("boxcon").innerHTML;
	else
		modmanage.innerHTML = "<ul><li style='margin:1px 20px 0 0px; cursor:hand;border:#000 0px solid;'></li></ul>";
	modmanage.className = "boxtitlec";
	modtitle.appendChild(modmanage);
	//将模块标题和内容加入模块项
	moddiv.appendChild(modtitle);
	var modcon = document.createElement('DIV');
	modcon.innerHTML = GE("tiploading").outerHTML;
	moddiv.appendChild(modcon);
	//将模块项加入所定容器
	if(mod.getAttribute("contype")!="sm"){
		bgcon.appendChild(moddiv);
	}else{
		smcon.appendChild(moddiv);
	}
	if(loginstatus=="1"){	//登录状态插入版块时重置模块为可拖动
		var cDrag = moddiv.parentNode.getAttribute('DropObj');
		moddiv.setAttribute('DragObj', cDrag);	//设置模块为可拖动
		moddiv.childNodes[0].setAttribute('DragObj', cDrag);	//设置模块栏为可拖动
		//设置模块标题为可拖动
		moddiv.childNodes[0].childNodes[0].setAttribute('DragObj', cDrag);
		moddiv.childNodes[0].childNodes[0].setAttribute('DragObjTitle', cDrag);
	}
}
	
//网站布局——显示设置页面宽度
function showSetWidth(){
	var wid = GE("fieldwidth").value;
	//如果设置值相同则返回，节约布局效率
	if(parseInt(allcon.style.width)==wid) return false;
	allcon.style.width = wid;
	GE("fieldwidthid").value = wid;
	
	showLayout();
}
//网站布局——显示去除所有布局
function removeLayout(){
	//将布局元素归还
	for(;allcon.childNodes.length>0;){
		elepool.appendChild(allcon.childNodes[0]);
	}
	elepool.appendChild(menu);
	elepool.appendChild(infomenu);
}
//网站布局——显示重新设定布局,laytype(1、左侧2、顶部3、右侧)
function showLayout(){
	var laytype = parseInt(GE("laytype").value);
	if(laytype!=1&&laytype!=2&&laytype!=3){alert("布局"+laytype+"不存在");return false;}
	removeLayout();
	allcon.appendChild(pagehead);
	allcon.appendChild(pagetitle);
	var pagewidth = allcon.offsetWidth;
	switch(laytype){
		case 1:
			menu = GE("menu");
			allcon.appendChild(pagestatus);
			allcon.appendChild(smdiv);
			smdiv.insertBefore(menu, smdiv.firstChild);
			allcon.appendChild(mddiv);
			allcon.appendChild(bgdiv);
			bgdiv.style.width = parseInt(pagewidth)
			-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
			break;
		case 2:
			menu = GE("menuh");
			allcon.appendChild(menu);
			allcon.appendChild(pagestatus);
			allcon.appendChild(smdiv);
			smdiv.insertBefore(infomenu, smdiv.firstChild);
			allcon.appendChild(mddiv);
			allcon.appendChild(bgdiv);
			bgdiv.style.width = parseInt(pagewidth)
			-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
			break;
		case 3:
			menu = GE("menu");
			allcon.appendChild(pagestatus);
			allcon.appendChild(bgdiv);
			allcon.appendChild(mddiv);
			allcon.appendChild(smdiv);
			smdiv.insertBefore(menu, smdiv.firstChild);
			bgdiv.style.width = parseInt(pagewidth)
			-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
			break;
		default:
			break;
	}
	allcon.appendChild(pagetail);
	//显示导航
	setNavItem();
}


//风格主题_显示页面背景
function showStyle(){
	GE('stylesheet').href = GE('themestyle').value;
	setNavItemh();	//切换主题时重排导航条，个别主题导航宽度不一样
}
//风格主题_显示页面背景
function showBackground(){
	document.body.style.background = GE("background").value;
}
//风格主题_显示设定页面鼠标
function showMouseStyle(){
	document.body.style.cursor = "url('"+GE('mousestyle').value+"')";
}

//导航菜单_显示设置导航菜单
function setNavItem(){
	//设置导航菜单顺序
	var navitem = GE("navitem");
	for(var i=0; i<navitem.childNodes.length; i++){
		for(var j=0;j<navitem.childNodes.length-1;j++){
			if(parseInt(navitem.childNodes[j].getAttribute("order"))>parseInt(navitem.childNodes[j+1].getAttribute("order"))){
				navitem.insertBefore(navitem.childNodes[j+1],navitem.childNodes[j]);
			}
		}
	}
	if(document.getElementsByName("laytype")[0].value=="2") setNavItemh();
	else setNavItemv();
}
//导航菜单_显示设置横向导航菜单
function setNavItemh(){
	var navh = GE("menuh");
	navh.innerHTML = "";
	
	
	var ldiv = document.createElement("DIV");
	ldiv.setAttribute("class","menuh_l");
	ldiv.className = "menuh_l";
	navh.appendChild(ldiv);
	
	var nav = document.createElement("UL");
	navh.appendChild(nav);
	
	var rdiv = document.createElement("DIV");
	rdiv.setAttribute("class","menuh_r");
	rdiv.className = "menuh_r";
	navh.appendChild(rdiv);
	
	var navitem = GE("navitem");
	
	isoverflow = false;
	var otherdiv = GE("otherdiv");
	otherdiv.className="condown";
	otherdiv.innerHTML = "";
	var pulldown = document.createElement("DIV");
	pulldown.className = "nav_othe";
	otherdiv.appendChild(pulldown);
	var navtitle = document.createElement("DIV");
	/*navtitle.setAttribute("class","nav_othe_title");
	navtitle.className = "nav_othe_title";
	navtitle.appendChild(document.createTextNode("栏目名称"));
	pulldown.appendChild(navtitle);*/
	var pulldown_ul = document.createElement("UL");
	pulldown.appendChild(pulldown_ul);
	for(var i=0;i<navitem.childNodes.length;i++){
		var item = navitem.childNodes[i];
		if(item.getAttribute("isshow")=="1"){
			if(isoverflow==true){
				var li =  document.createElement("LI");
				
				var a = document.createElement("A");
				li.appendChild(a);
				a.setAttribute("href",item.getAttribute("navurl"));
				a.appendChild(document.createTextNode(item.getAttribute("navname")));
				pulldown_ul.appendChild(li);
			}else{
				if(nav.firstChild == nav.lastChild||(navh.offsetWidth-ldiv.offsetWidth-rdiv.offsetWidth-nav.offsetWidth)>((parseInt(nav.firstChild.nextSibling.offsetLeft)-ldiv.offsetWidth)*3)){
					if(item.getAttribute("ischosed")=="1"){
						var li = GE("tmpnavitemnow").cloneNode(true);
					}else{
						var li = GE("tmpnavitem").cloneNode(true);
					}
					li.innerHTML="<a href='"+item.getAttribute("navurl")+"'>"+item.getAttribute("navname")+"</a>";
					nav.appendChild(li);
				}else{
					isoverflow = true;
					i--;
					var li = document.createElement("LI");
					//li.style.marginLeft=parseInt(nav.firstChild.offsetWidth);
					var a = document.createElement("A");
					li.appendChild(a);
					a.setAttribute("href","javascript:openOther();");
					a.setAttribute("id","otherbtn");
					a.appendChild(document.createTextNode("其他"));
					nav.appendChild(li);
				}
			}
		}
	}
}
//导航菜单_显示设置横向其他导航菜单
function openOther(){
	var otherdiv = GE("otherdiv");
	var obj = GE("otherbtn");

	var pos = getPosition(obj);

	otherdiv.style.visibility = "visible";
	otherdiv.style.display = "";
	var pos = getPosition(obj);
	otherdiv.style.left = pos.x;
	otherdiv.style.top = pos.y+20;
}
//导航菜单_显示设置竖向导航菜单
function setNavItemv(){
	var navs = GE("menu");
	
	var nav = document.createElement("UL");
	navs.innerHTML = "";
	navs.appendChild(nav);
	var navitem = GE("navitem");
	for(var i=0;i<navitem.childNodes.length;i++){
		var item = navitem.childNodes[i];
		if(item.getAttribute("isshow")=="1"){
			var li = document.createElement("LI");

			if(item.getAttribute("ischosed")=="1"){
				li.setAttribute("class","menu_nownav");
				li.className = "menu_nownav";
			}
			
			var a = document.createElement("A");
			li.appendChild(a);
			a.setAttribute("href",item.getAttribute("navurl"));
			a.appendChild(document.createTextNode(item.getAttribute("navname")));
			nav.appendChild(li);
		}
	}
}
	
//隐藏所有浮动控制菜单
function closeControl(ev){
	ev = ev || window.event;
	var mousePos = mouseCoords(ev);
	var divs = document.getElementsByTagName("DIV");
	var len = 0;
	for(var i=0;i<divs.length;i++){
		if(divs[i].getAttribute("type")=="modfloatc"){
			if(mousePos.x >divs[i].offsetLeft&&mousePos.x<divs[i].offsetLeft+divs[i].offsetWidth
			&&mousePos.y>divs[i].offsetTop&&mousePos.y<divs[i].offsetTop+divs[i].offsetHeight){
				//在当前区域
			}else{
				divs[i].style.display="none";
				len++;
			}
		}
	}
}
	
//页面全部下载完时填充模块内容
function setAllContent(){
	for(var i=0;i<smcon.childNodes.length;i++){
		var mod = GE(smcon.childNodes[i].getAttribute("id").substring(4));
		setContent(mod);
	}
	for(var i=0;i<bgcon.childNodes.length;i++){
		var item = bgcon.childNodes[i].lastChild.parentNode;
		var mod = GE(item.getAttribute("id").substring(4));
		setContent(mod);
	}
}
function setContent(mod){
	//根据位置写入模块内容
	if(mod.getAttribute("contype")=="bg"){
		var item = GE("item"+mod.id);
		item.lastChild.innerHTML = GE(mod.id.substring(3)).outerHTML;
		item.firstChild.setAttribute("class","boxtitle");
		item.firstChild.className = "boxtitle";
		item.firstChild.firstChild.className = "boxtitleleft";
		item.firstChild.lastChild.className = "boxtitlec";
	}else{
		var item = GE("item"+mod.id);
		item.lastChild.innerHTML = GE(mod.id.substring(3)+"l").outerHTML;
		item.firstChild.setAttribute("class","boxtitle_left");
		item.firstChild.className = "boxtitle_left";
		item.firstChild.firstChild.className = "boxtitleleft_left";
		item.firstChild.lastChild.className = "boxtitlec_left";
	}
}
