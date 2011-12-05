/**
 * @author Administrator
 */

//��ȡ����ľ���λ��
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

//��ȡ����¼���λ��
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
//����IDȡ��ҳ��Ԫ��
function GE(idname){return document.getElementById(idname);}

//��ʼ��ҳ��
function initpage(){
	//����¼״̬ʱ����ʾҳ����������
	if(loginstatus=="1"){
		GE("pageset").style.display = "";
		GE('start').click();
	}
	//������ҵ��ҳ�棨��ͬʱ���ò��֣�
	showSetWidth();
	//������ҵ��������ʽ
	showStyle();
	//��ʾҳ�汳��
	showBackground();
	//���������ʽ
	showMouseStyle();
	//��ʼ��ģ�鵽������
	initcon();
}
//��ʼ������
function initcon(){
	//���������
	smcon.innerHTML = "";
	bgcon.innerHTML = "";
	//�����ģ��
	for(var i=0; i<modpool.childNodes.length; i++){
		if(modpool.childNodes[i].nodeName=='#text') continue;	//����TextNode����
		if(modpool.childNodes[i].nodeName=='#comment') continue;	//����Comment����
		modincon(modpool.childNodes[i]);
	}
	//����ģ��λ��(ģ������ʱû�а�˳�����)
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
	//��¼ʱ��ʼ����ק��������������ID����ÿ�������µĵ�һ����Ԫ����Ϊһ����ק�
	if(loginstatus=="1"){
		var con = new Array();
		con.push(smcon);
		con.push(bgcon);
		init(con);
	}
}

//ģ����뵽�����У�mod(ģ�����)
function modincon(mod){
	//��֤ģ�����
	if(mod==null||typeof(mod)=="undefined") {alert("�����ģ��Ϊ��!");return false;}
	if(mod.getAttribute("isshow")==null||typeof(mod.getAttribute("isshow"))=="undefined"){alert("ģ����Ƿ���ʾ����δ����!");return false;}
	if(mod.id==null||typeof(mod.id)=="undefined"){alert("���λ�ȡģ���ID��");return false;}
	if(mod.getAttribute("contype")==null||typeof(mod.getAttribute("contype"))=="undefined"){alert("ģ��δָ��������");return false;}
	if(mod.getAttribute("order")==null||typeof(mod.getAttribute("order"))=="undefined"){alert("ģ���˳�����δ����!");return false;}
	if(mod.getAttribute("modname")==null||typeof(mod.getAttribute("modname"))=="undefined"){alert("ģ������δ���壡");return false;}
	if(mod.getAttribute("isshow")!="1") return;
	//����ģ��DIV����
	var moddiv = document.createElement('DIV');
	moddiv.setAttribute("class","DragBox");
	moddiv.className = "DragBox";
	moddiv.setAttribute("overclass","OverDragBox");
	moddiv.setAttribute("dragclass","DragDragBox");
	moddiv.setAttribute("history","hisdiv");
	moddiv.setAttribute("id","item"+mod.id);
	//����ģ�������
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
	//��ģ���������ݼ���ģ����
	moddiv.appendChild(modtitle);
	var modcon = document.createElement('DIV');
	modcon.innerHTML = GE("tiploading").outerHTML;
	moddiv.appendChild(modcon);
	//��ģ���������������
	if(mod.getAttribute("contype")!="sm"){
		bgcon.appendChild(moddiv);
	}else{
		smcon.appendChild(moddiv);
	}
	if(loginstatus=="1"){	//��¼״̬������ʱ����ģ��Ϊ���϶�
		var cDrag = moddiv.parentNode.getAttribute('DropObj');
		moddiv.setAttribute('DragObj', cDrag);	//����ģ��Ϊ���϶�
		moddiv.childNodes[0].setAttribute('DragObj', cDrag);	//����ģ����Ϊ���϶�
		//����ģ�����Ϊ���϶�
		moddiv.childNodes[0].childNodes[0].setAttribute('DragObj', cDrag);
		moddiv.childNodes[0].childNodes[0].setAttribute('DragObjTitle', cDrag);
	}
}
	
//��վ���֡�����ʾ����ҳ����
function showSetWidth(){
	var wid = GE("fieldwidth").value;
	//�������ֵ��ͬ�򷵻أ���Լ����Ч��
	if(parseInt(allcon.style.width)==wid) return false;
	allcon.style.width = wid;
	GE("fieldwidthid").value = wid;
	
	showLayout();
}
//��վ���֡�����ʾȥ�����в���
function removeLayout(){
	//������Ԫ�ع黹
	for(;allcon.childNodes.length>0;){
		elepool.appendChild(allcon.childNodes[0]);
	}
	elepool.appendChild(menu);
	elepool.appendChild(infomenu);
}
//��վ���֡�����ʾ�����趨����,laytype(1�����2������3���Ҳ�)
function showLayout(){
	var laytype = parseInt(GE("laytype").value);
	if(laytype!=1&&laytype!=2&&laytype!=3){alert("����"+laytype+"������");return false;}
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
	//��ʾ����
	setNavItem();
}


//�������_��ʾҳ�汳��
function showStyle(){
	GE('stylesheet').href = GE('themestyle').value;
	setNavItemh();	//�л�����ʱ���ŵ��������������⵼����Ȳ�һ��
}
//�������_��ʾҳ�汳��
function showBackground(){
	document.body.style.background = GE("background").value;
}
//�������_��ʾ�趨ҳ�����
function showMouseStyle(){
	document.body.style.cursor = "url('"+GE('mousestyle').value+"')";
}

//�����˵�_��ʾ���õ����˵�
function setNavItem(){
	//���õ����˵�˳��
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
//�����˵�_��ʾ���ú��򵼺��˵�
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
	navtitle.appendChild(document.createTextNode("��Ŀ����"));
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
					a.appendChild(document.createTextNode("����"));
					nav.appendChild(li);
				}
			}
		}
	}
}
//�����˵�_��ʾ���ú������������˵�
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
//�����˵�_��ʾ�������򵼺��˵�
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
	
//�������и������Ʋ˵�
function closeControl(ev){
	ev = ev || window.event;
	var mousePos = mouseCoords(ev);
	var divs = document.getElementsByTagName("DIV");
	var len = 0;
	for(var i=0;i<divs.length;i++){
		if(divs[i].getAttribute("type")=="modfloatc"){
			if(mousePos.x >divs[i].offsetLeft&&mousePos.x<divs[i].offsetLeft+divs[i].offsetWidth
			&&mousePos.y>divs[i].offsetTop&&mousePos.y<divs[i].offsetTop+divs[i].offsetHeight){
				//�ڵ�ǰ����
			}else{
				divs[i].style.display="none";
				len++;
			}
		}
	}
}
	
//ҳ��ȫ��������ʱ���ģ������
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
	//����λ��д��ģ������
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
