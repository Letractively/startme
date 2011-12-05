/**
 * @author Administrator
 */
if(window.addEventListener){
	FixPrototypeForGecko();
}
function FixPrototypeForGecko(){
	HTMLElement.prototype.__defineGetter__("runtimeStyle",element_prototype_get_runtimeStyle);
	window.constructor.prototype.__defineGetter__("event",window_prototype_get_event);
	Event.prototype.__defineGetter__("srcElement",event_prototype_get_srcElement);
}
function element_prototype_get_runtimeStyle(){
	//return style instead...
	return this.style;
}
function window_prototype_get_event(){
	var ev = SearchEvent();
	return ev;
}
function event_prototype_get_srcElement(){
	return this.target;
}
function SearchEvent(){
	//IE
	if(document.all)
		return window.event;
		
	func=SearchEvent.caller;
	while(func!=null){
		var arg0=func.arguments[0];
		if(arg0){
			if(arg0.constructor==Event|| arg0.constructor==MouseEvent)
				return arg0;
		}
		func=func.caller;
	}
	return null;
}

/* 
iMouseDown represents the current mouse button state: up or down
lMouseState represents the previous mouse button state so that we can
check for button clicks and button releases:
if(iMouseDown && !lMouseState) // button just clicked!
if(!iMouseDown && lMouseState) // button just released! 
*/
var mouseOffset = null;	//�����ƶ������ƫ����
var iMouseDown  = false;	//�����µ�״̬
var lMouseState = false;	//ǰһ�������µ�״̬

var DragDrops   = [];	//��ק������
var curTarget   = null;	//��ǰ��ק����
var lastTarget  = null;	//��һ����ק����
var dragHelper  = null;	//��ǰ��ק����ĸ�����,��������ƶ�

var rootParent  = null;	//��ǰ��ק����ĸ�����(����)
var rootSibling = null;	//��ǰ��ק����ĺ�һ�ֵܶ���

document.onmousemove = mouseMove;
document.onmousedown = mouseDown;
document.onmouseup   = mouseUp;

//��ʼ����ק����
function init(con){
	//��ȡ��ק�������飬���û���򷵻�
	var containers = con;
	if(containers==null || typeof(containers)=="undefined" || containers.length <=0){
		return false;
	}
	//������ק������
	CreateDragContainer(containers);
	//������ק�����Ӱ���ƿ�
	dragHelper = document.createElement('DIV');
	dragHelper.style.cssText = 'position:absolute;display:none;';
	document.body.appendChild(dragHelper);
}

//���������飬���������Ϊ�����������飬ģ����������������϶�
function CreateDragContainer(cons){
	/*
	Create a new "Container Instance" so that items from one "Set" can not
	be dragged into items from another "Set"
	*/
	var cDrag        = DragDrops.length;	//��ǰ��ק������������
	DragDrops[cDrag] = [];
	/*
	Each item passed to this function should be a "container".  Store each
	of these items in our current container
	*/
	//���õ�ǰ�����������������
	for(var i=0; i<cons.length; i++){
		var cObj = cons[i];
		DragDrops[cDrag].push(cObj);
		cObj.setAttribute('DropObj', cDrag);	//��������Ϊ���϶�

		/*
		Every top level item in these containers should be draggable.  Do this
		by setting the DragObj attribute on each item and then later checking
		this attribute in the mouseMove function
		*/
		//���õ�ǰ����������ӽڵ�Ϊ��קģ��
		for(var j=0; j<cObj.childNodes.length; j++){

			// Firefox puts in lots of #text nodes...skip these
			if(cObj.childNodes[j].nodeName=='#text') continue;	//�����ı��ڵ�

			cObj.childNodes[j].setAttribute('DragObj', cDrag);	//����ģ��Ϊ���϶�
			cObj.childNodes[j].childNodes[0].setAttribute('DragObj', cDrag);	//����ģ����Ϊ���϶�
			//����ģ�����Ϊ���϶�
			cObj.childNodes[j].childNodes[0].childNodes[0].setAttribute('DragObj', cDrag);
			cObj.childNodes[j].childNodes[0].childNodes[0].setAttribute('DragObjTitle', cDrag);
		}
	}
}

//��¼��ק��ʷ
function writeHistory(object, message){
	if(!object || !object.parentNode || !object.parentNode.getAttribute) return;
	var historyDiv = object.parentNode.getAttribute('history');
	if(historyDiv){
		historyDiv = GE(historyDiv);
		historyDiv.appendChild(document.createTextNode(object.id+': '+message));
		historyDiv.appendChild(document.createElement('BR'));

		historyDiv.scrollTop += 50;
	}
}

//��ȡ����Ŀ���ƫ����
function getMouseOffset(target, ev){
	ev = ev || window.event;

	var docPos    = getPosition(target);
	var mousePos  = mouseCoords(ev);
	return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
}

//����ƶ����¼����������϶���ʵ�֣���¼ʱʹ�ã�
function mouseMove(ev){
	ev = ev || window.event;

	/*
	We are setting target to whatever item the mouse is currently on
	Firefox uses event.target here, MSIE uses event.srcElement
	*/
	//��ȡ�¼��Ķ���
	var target   = ev.target || ev.srcElement;

	// mouseOut event - fires if the item the mouse is on has changed
	//��ԭ��һ����ק�����ʽ(��û��,����ȥ��)
	if(lastTarget && (target!==lastTarget)){
		writeHistory(lastTarget, 'Mouse Out Fired');

		// reset the classname for the target element
		var origClass = lastTarget.getAttribute('origClass');
		if(origClass) lastTarget.className = origClass;
	}

	/*
	dragObj is the grouping our item is in (set from the createDragContainer function).
	if the item is not in a grouping we ignore it since it can't be dragged with this
	script.
	*/
	//���Ŀ���Ƿ�Ϊ����ק
	var dragObj = target.getAttribute('DragObj');

	// if the mouse was moved over an element that is draggable
	if(dragObj!=null){	//����Ϊ����קʱ(������ק��)

		// mouseOver event - Change the item's class if necessary
		//���õ�ǰ��קĿ�������ʱ��ʽ(��û��,����ȥ��)
		if(target!=lastTarget){
			writeHistory(target, 'Mouse Over Fired');

			var oClass = target.getAttribute('overClass');
			if(oClass){
				target.setAttribute('origClass', target.className);
				target.className = oClass;
			}
		}

		// if the user is just starting to drag the element
		//���²��ƶ�(˲��)�������ÿ�ʼ��ק
		if(iMouseDown && !lMouseState){
			writeHistory(target, 'Start Dragging');

			// mouseDown target
			//�����ק�ڱ���������,���ƶ���Ŀ��Ϊ����һ��(������)
			if(target.getAttribute("DragObjTitle")!=null){
				target = target.parentNode;
			}
			//���ƶ���Ŀ������Ϊ����������һ��(����ģ��DIV)
			curTarget     = target.parentNode;

			// Record the mouse x and y offset for the element
			//�����µ�ǰ��ק���λ��,�Ա��Ƴ�����ʱ��ԭ��λ��
			rootParent    = curTarget.parentNode;
			rootSibling   = curTarget.nextSibling;

			//��ȡ���ƫ����
			mouseOffset   = getMouseOffset(target, ev);

			//���ø���������ק���Ʒ
			// We remove anything that is in our dragHelper DIV so we can put a new item in it.
			for(var i=0; i<dragHelper.childNodes.length; i++) dragHelper.removeChild(dragHelper.childNodes[i]);
			// Make a copy of the current item and put it in our drag helper.
			dragHelper.appendChild(curTarget.cloneNode(true));
			dragHelper.style.display = 'block';
			//������ק���Ʒ����קʱ��ʽ(��û��,����ȥ��)
			// set the class on our helper DIV if necessary
			var dragClass = curTarget.getAttribute('dragClass');
			if(dragClass){
				dragHelper.firstChild.className = dragClass;
			}

			// disable dragging from our helper DIV (it's already being dragged)
			//ȥ����ק���������ק����
			dragHelper.firstChild.removeAttribute('DragObj');

			/*
			Record the current position of all drag/drop targets related
			to the element.  We do this here so that we do not have to do
			it on the general mouse move event which fires when the mouse
			moves even 1 pixel.  If we don't do this here the script
			would run much slower.
			*/
			var dragConts = DragDrops[dragObj];	//��ȡ��ק������

			/*
			first record the width/height of our drag item.  Then hide it since
			it is going to (potentially) be moved out of its parent.
			*/
			//���õ�ǰ��ק�����Ӧ�������ص�ǰ��ק��
			curTarget.setAttribute('startWidth',  parseInt(curTarget.offsetWidth));
			curTarget.setAttribute('startHeight', parseInt(curTarget.offsetHeight));
			curTarget.style.display  = 'none';

			// loop through each possible drop container
			//ѭ�������������������Ӧ������������קģ�����Ӧ��
			for(var i=0; i<dragConts.length; i++){
				with(dragConts[i]){
					var pos = getPosition(dragConts[i]);

					/*
					save the width, height and position of each container.

					Even though we are saving the width and height of each
					container back to the container this is much faster because
					we are saving the number and do not have to run through
					any calculations again.  Also, offsetHeight and offsetWidth
					are both fairly slow.  You would never normally notice any
					performance hit from these two functions but our code is
					going to be running hundreds of times each second so every
					little bit helps!

					Note that the biggest performance gain here, by far, comes
					from not having to run through the getPosition function
					hundreds of times.
					*/
					setAttribute('startWidth',  parseInt(offsetWidth));
					//����ק������Ӧ�߶�����6000���أ������˰�
					setAttribute('startHeight', parseInt(offsetHeight)+6000);
					setAttribute('startLeft',   pos.x);
					setAttribute('startTop',    pos.y);
				}

				// loop through each child element of each container
				for(var j=0; j<dragConts[i].childNodes.length; j++){
					with(dragConts[i].childNodes[j]){
						if((nodeName=='#text') || (dragConts[i].childNodes[j]==curTarget)) continue;

						var pos = getPosition(dragConts[i].childNodes[j]);

						// save the width, height and position of each element
						setAttribute('startWidth',  parseInt(offsetWidth));
						setAttribute('startHeight', parseInt(offsetHeight));
						setAttribute('startLeft',   pos.x);
						setAttribute('startTop',    pos.y);
					}
				}
			}
		}
	}

	// If we get in here we are dragging something
	if(curTarget){	//���е�ǰ����ʱ(����ִ����ק����)
		//��ȡ���λ��
		var mousePos = mouseCoords(ev);
		//move our helper div to wherever the mouse is (adjusted by mouseOffset)
		//������ק�������λ��
		dragHelper.style.top  = mousePos.y - mouseOffset.y;
		dragHelper.style.left = mousePos.x - mouseOffset.x;
		dragHelper.style.width = curTarget.startWidth;
		dragHelper.style.height = curTarget.startHeight;

		var dragConts  = DragDrops[curTarget.getAttribute('DragObj')];
		var activeCont = null;

		//var xPos = mousePos.x - mouseOffset.x + (parseInt(curTarget.getAttribute('startWidth')) /2);
		//var yPos = mousePos.y - mouseOffset.y + (parseInt(curTarget.getAttribute('startHeight'))/2);
		var xPos = mousePos.x;
		var yPos = mousePos.y;

		// check each drop container to see if our target object is "inside" the container
		//ѭ��ָ���������е�����,�ҳ�ģ����뵽������
		for(var i=0; i<dragConts.length; i++){
			with(dragConts[i]){
				if((parseInt(getAttribute('startLeft'))                                           < xPos) &&
					(parseInt(getAttribute('startTop'))                                            < yPos) &&
					((parseInt(getAttribute('startLeft')) + parseInt(getAttribute('startWidth')))  > xPos) &&
					((parseInt(getAttribute('startTop'))  + parseInt(getAttribute('startHeight'))) > yPos)){

						/*
						our target is inside of our container so save the container into
						the activeCont variable and then exit the loop since we no longer
						need to check the rest of the containers
						*/
						activeCont = dragConts[i];
						// exit the for loop
						break;
				}
			}
		}

		// Our target object is in one of our containers.  Check to see where our div belongs
		//������ק���λ�ú���ʾ
		if(activeCont){	//���뵽������
			if(activeCont!=curTarget.parentNode){
				writeHistory(curTarget, 'Moved into '+activeCont.id);
			}

			// beforeNode will hold the first node AFTER where our div belongs
			//�鿴��ǰ��ק�����������Ƿ���ĳ���ڵ�֮ǰ
			var beforeNode = null;
			// loop through each child node (skipping text nodes).
			for(var i=activeCont.childNodes.length-1; i>=0; i--){
				with(activeCont.childNodes[i]){
					if(nodeName=='#text') continue;

					// if the current item is "After" the item being dragged
					if(curTarget != activeCont.childNodes[i]                                                  &&
						((parseInt(getAttribute('startLeft')) + parseInt(getAttribute('startWidth')))  > xPos) &&
						((parseInt(getAttribute('startTop'))  + parseInt(getAttribute('startHeight'))) > yPos)){
							beforeNode = activeCont.childNodes[i];
					}
				}
			}

			// the item being dragged belongs before another item
			//����ק����(�ƶ�Ԫ��)
			if(beforeNode){
				if(beforeNode!=curTarget.nextSibling){
					writeHistory(curTarget, 'Inserted Before '+beforeNode.id);

					activeCont.insertBefore(curTarget, beforeNode);
				}

			// the item being dragged belongs at the end of the current container
			} else {
				if((curTarget.nextSibling) || (curTarget.parentNode!=activeCont)){
					writeHistory(curTarget, 'Inserted at end of '+activeCont.id);

					activeCont.appendChild(curTarget);
				}
			}
			//�����������������޸����ݡ�
			var mod = GE(curTarget.getAttribute("id").substring(4));
			mod.setAttribute("contype",curTarget.parentNode.getAttribute("contype"));
			setContent(mod);
				
			
			// the timeout is here because the container doesn't "immediately" resize
			//����������������Ӧ��
			setTimeout(function(){
			var contPos = getPosition(activeCont);
			activeCont.setAttribute('startWidth',  parseInt(activeCont.offsetWidth));
			//����ק������Ӧ�߶�����6000���أ������˰�
			activeCont.setAttribute('startHeight', parseInt(activeCont.offsetHeight)+6000);
			activeCont.setAttribute('startLeft',   contPos.x);
			activeCont.setAttribute('startTop',    contPos.y);}, 5);

			// make our drag item visible
			//������ק��Ϊ��ק��
			if(curTarget.style.display!=''){
				writeHistory(curTarget, 'Made Visible');
				curTarget.style.display    = '';
				curTarget.style.border = "#000 2px dotted";
				curTarget.firstChild.style.visibility = 'hidden';
				curTarget.lastChild.style.visibility = 'hidden';
				curTarget.style.filter = 'alpha(opacity=20)';
			}
		} else {	//����������������ק��

			// our drag item is not in a container, so hide it.
			if(curTarget.style.display!='none'){
				writeHistory(curTarget, 'Hidden');
				curTarget.style.display  = 'none';
			}
		}
	}

	// mouseMove target
	lastTarget  = target;

	// track the current mouse state so we can compare against it next time
	lMouseState = iMouseDown;

	// this prevents items on the page from being highlighted while dragging
	//��ֹ��ק�ƶ�ʱ���ݱ�ѡ�и�����ʾ
	if(curTarget) return false;
}

//���ſ����¼����������϶���ʵ�֣���¼ʱʹ�ã�
function mouseUp(ev){
	//����е�ǰ��ק����,�����������ק���
	if(curTarget){
		writeHistory(curTarget, 'Mouse Up Fired');
		//�����ǰ��ק������������,��ԭ��ק���λ��
		if(curTarget.style.display == 'none'){
			if(rootSibling){
				rootParent.insertBefore(curTarget, rootSibling);
			} else {
				rootParent.appendChild(curTarget);
			}
			curTarget.style.display = '';
		}
		//�޸�ģ�����ڵ�����(���Ҫ�޸�һ�£�����Ҫ���ñ���������������⣬��Ҫ�������ж�������򣬲���¼�ã�����ͣ�������⡣
		//�����������������޸����ݡ�
		var mod = GE(curTarget.getAttribute("id").substring(4));
		mod.setAttribute("contype",curTarget.parentNode.getAttribute("contype"));
		setContent(mod);
		setCookie();
		//��λ����
		modinconani(dragHelper,curTarget);
		
		//������������ק����Ʒ����
		dragHelper.style.display = 'none';
		
		
	}
		//�õ�ǰ��ק��Ϊ��
		curTarget  = null;
	//ȥ��������״̬
	iMouseDown = false;
}

//ģ���λʱ�Ķ�����fromNode����ĳ�ڵ㡣toNode����ĳ�ڵ㡣��
function modinconani(fromNode,toNode){
	var movNode = toNode.cloneNode(true);
	var frompos = getPosition(fromNode);

	fromNode.style.display = 'none';
	
	var movdiv = GE("movdiv");
	movdiv.appendChild(movNode);
	
	movNode.setAttribute("id",toNode.id+"copy");
	
	movNode.style.position = "absolute";
	movNode.style.top = frompos.y;
	movNode.style.left = frompos.x;
	movNode.style.width = toNode.offsetWidth;
	
	movNode.style.display = '';
	movNode.style.visibility = "visible";
	movNode.style.border = "#000 0px dotted";
	movNode.firstChild.style.visibility = "visible";
	movNode.lastChild.style.visibility = "visible";
	movNode.style.filter = 'alpha(opacity=100)';
	toNode.style.border = "#000 0px dotted";
	var pos = getPosition(toNode);
	var posy = Math.abs(parseInt(pos.y-parseInt(movNode.style.top))/40);
	var posx = Math.abs(parseInt(pos.x-parseInt(movNode.style.left))/40);
	modinconanimation(movNode.getAttribute("id"),toNode.id,posy>posx?posy:posx);
}
//�������_�����ƶ�ģ��ʱ�Ķ���Ч��
function modinconanimation(movNodeid,toNodeid,times){
	var movNode = GE(movNodeid);
	var toNode = GE(toNodeid);
	
	var pos = getPosition(toNode);
	movNode.style.top = parseInt(movNode.style.top)+parseInt((pos.y-parseInt(movNode.style.top))/times);
	movNode.style.left = parseInt(movNode.style.left)+parseInt((pos.x-parseInt(movNode.style.left))/times);
	if(times>1){
		setTimeout("modinconanimation('"+movNodeid+"','"+toNodeid+"',"+(times-1)+")",10);
	}else{
		var movdiv = GE("movdiv");
		movdiv.removeChild(movNode);
		//���õ�ǰ��ק�����ʾ��ʽΪ��ק���
		toNode.style.display = '';
		toNode.firstChild.style.visibility = 'visible';
		toNode.lastChild.style.visibility = 'visible';
		toNode.style.filter = 'alpha(opacity=100)';
	}
}

//�����µ��¼����������϶���ʵ�֣���¼ʱʹ�ã�
function mouseDown(ev){
	ev = ev || window.event;
	var target = ev.target || ev.srcElement;
	//��������״̬
	iMouseDown = true;
	
	if(lastTarget){
		writeHistory(lastTarget, 'Mouse Down Fired');
	}	
	
	if(target.onmousedown || target.getAttribute('DragObj')){
		return false;
	}

}

//��վ����_�趨Layout
function setLayout(laytype){
	GE("laytype").value = laytype;
	showLayout();
	
	setCookie();
}
//��վ����_�趨ҳ����
function setSetWidth(wid){
	if(wid<780||wid>1024){
		alert("������ֵ������780����1024����֮��!");
		return false;
	}
	GE("fieldwidth").value = wid;	//���ÿ��ֵ
	showSetWidth();
	
	setCookie();
}


//�������_�趨��ͨ��������
function setStyleType(stype){
	var scolor = GE('stylecolor').value;
	var theme = "./templet/style_"+stype+"_"+scolor+".css";
	GE('themestyle').value = theme;
	GE('styletype').value = stype;
	showStyle();
}
//�������_�趨��ͨ������ɫ
function setStyleColor(scolor){
	GE("stylecolor"+GE('stylecolor').value).className = "";	//��ԭ������ɫ�߿�ȥ��
	
	var stype = GE('styletype').value;
	var theme = "./templet/style_"+stype+"_"+scolor+".css";
	GE('themestyle').value = theme;
	GE('stylecolor').value = scolor;
	showStyle();
}
//�������_�趨�߼�����
function setStyle(theme){
	GE('themestyle').value = theme;
	showStyle(theme);
}
//�������_�趨ҳ�汳��
function setBackground(color){
	GE("background").value = color;
	showBackground(color);
}
//�������_�趨ҳ�����
function setMouseStyle(pic){
	GE('mousestyle').value = pic;
	showMouseStyle(pic);
}
	
	
//��������_��ʾ�趨�����˵�
function showNavItem(){
	var adddiv = GE("nav_tr");

	for(;adddiv.childNodes.length>2;){
		adddiv.removeChild(adddiv.childNodes[2]);
	}
	var navitem = GE("navitem");
	for(var i=0;i<navitem.childNodes.length;i++){
		var menuitem = navitem.childNodes[i];
		
		if(menuitem.getAttribute("order")=="1") continue;
		
		var item = document.createElement("TR");
		item.setAttribute("align","center");
		item.setAttribute("navid",menuitem.getAttribute("id"));
		
		var itemname = document.createElement("TD");
		var itemnamehtml = "<input type='text' maxlength='4' value='"+menuitem.getAttribute("navname")+"' class='lmmc'";
		if(menuitem.getAttribute("ischangename")!="1")
			itemnamehtml += " disabled />";
		else 
			itemnamehtml += " />";
		itemname.innerHTML = itemnamehtml;
		item.appendChild(itemname);
		
		var itemshow = document.createElement("TD");
		var itemshowhtml = "<input type='checkbox' value='1'";
		if(menuitem.getAttribute("ischangeshow")!="1")
			itemshowhtml += " disabled ";
		if(menuitem.getAttribute("isshow")=="1")
			itemshowhtml += " checked ";
		itemshowhtml += " />��ʾ";
		itemshow.innerHTML = itemshowhtml;
		item.appendChild(itemshow);
		
		var itemmove = document.createElement("TD");
		itemmovehtml = "<input type='button' value='����' onClick='moveupitem(this);'/>";
		itemmovehtml += "<input type='button' value='����' onClick='movedownitem(this);'/>";
		itemmove.innerHTML = itemmovehtml;
		item.appendChild(itemmove);
		
		adddiv.appendChild(item);
	}
}
//��������_�趨�����˵�����
function moveupitem(obj){
	var nowitem = obj.offsetParent.parentNode;
	var upitem = nowitem.previousSibling;
	var con = nowitem.parentNode;
	if(nowitem == con.childNodes[2]){
		alert("�����������ˣ�");
		return false;
	}
	con.insertBefore(nowitem,upitem);
}
//��������_�趨�����˵�����
function movedownitem(obj){
	var nowitem = obj.offsetParent.parentNode;
	var con = nowitem.parentNode;
	if(nowitem == con.lastChild){
		alert("�����������ˣ�");
		return false;
	}
	var downitem = nowitem.nextSibling;
	con.insertBefore(downitem,nowitem);
}
//��������_�����趨�����˵�
function saveNavItem(){
	var adddiv = GE("nav_tr");
	//alert(adddiv.childNodes[1].childNodes[0].firstChild.value);
	//alert(adddiv.childNodes[2].childNodes[1].firstChild.checked);
	for(var i=2;i<adddiv.childNodes.length;i++){
		var navtr = adddiv.childNodes[i];
		var navid = navtr.getAttribute("navid");
		var navname = navtr.childNodes[0].firstChild.value;
		var isshow = navtr.childNodes[1].firstChild.checked?1:0;
		var order = i;
		var navli = GE(navid);
		navli.setAttribute("navname",navname);
		navli.setAttribute("isshow",isshow);
		navli.setAttribute("order",order);
	}
	GE('anidiv').style.display='none';
	setNavItem();
}

//�������_�����С�����
function maxminMod(obj){
	var modcon = obj.parentNode.parentNode.parentNode.parentNode.lastChild;
	if(modcon.style.display == ""){
		modcon.style.display = "none";
		obj.setAttribute("class","boxtitlec_max");
		obj.className="boxtitlec_max";
		obj.setAttribute("title","��󻯰��");
	}else{
		modcon.style.display = "";
		obj.setAttribute("class","boxtitlec_min");
		obj.className="boxtitlec_min";
		obj.setAttribute("title","��С�����");
	}
}
//�������_ɾ�����
function delMod(obj){
	var item = obj.parentNode.parentNode.parentNode.parentNode;
	var con = item.parentNode;
	if(con.childNodes.length==1){
		alert("��ǰλ�ñ���Ҫ��һ����Ŀ��");
		return false;
	}
	//������Ϣ
	var mod = GE(item.getAttribute("id").substring(4));
	mod.setAttribute("isshow","0");
	mod.style.display="";	//��ԭ������ʾģ�飨�����ʱ����ʾ����󻯵�ģ�飩
	setCookie();
	item.parentNode.removeChild(item);
	//setTimeout("hideMod('"+item.getAttribute("id")+"',5)");
}
function hideMod(itemid,times){
	var item = GE(itemid);
	var fil = 'alpha(opacity='+times*20+')';
	item.style.filter = fil;
	//hidealldiv(item,fil);
	if(times>0){
		setTimeout("hideMod('"+itemid+"',"+(times-1)+")",1);
	}
	else
	item.parentNode.removeChild(item);
}
function hidealldiv(item,fil){
	item.style.filter = fil;
	for(var i=0;i<item.childNodes.length;i++){
		if(item.childNodes[i].style!=null)
		item.childNodes[i].style.filter = fil;
		if(item.childNodes[i].childNodes.length>0) hidealldiv(item.childNodes[i],fil);
	}
}
//�������_ɾ�����(�˵�)
function deleteMod(obj){
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var con = item.parentNode;
	con.removeChild(item);
	
	var mod = GE(item.getAttribute("id").substring(4));
	mod.setAttribute("isshow","0");
	mod.style.display="";	//��ԭ������ʾģ�飨�����ʱ����ʾ����󻯵�ģ�飩
	setCookie();	
}
//�������_�༭�������
function changeNameMod(obj){
	obj.offsetParent.offsetParent.style.display="none";
	
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var modid = item.getAttribute("id").substring(4);
	
	GE("edtingmod").value = modid;
	GE("edtingmodname").value = GE(modid).modname;
	showFloat(item,'editname');
}
//�������_����������
function setModName(){
	GE('anidiv').style.display='none';
	var modid = GE("edtingmod").value;
	var modname = GE("edtingmodname").value;
	GE(modid).modname = modname;
	var nowNode = GE("item"+modid);
	nowNode.childNodes[0].childNodes[0].innerHTML = modname;
	setCookie();
}
//�������_�༭������ݣ�ת����������ҳ��
function editModContent(obj){
	obj.offsetParent.offsetParent.style.display="none";
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	window.open(GE(item.getAttribute("id").substring(4)).getAttribute("modurl"));
}
//�������_��ʾ�����Ʋ˵�
function showControl(obj){
	var item = obj.parentNode.parentNode.parentNode.parentNode;
	var modid = item.getAttribute("id").substring(4);

	var modfloatc = GE("modfloatc"+modid);
	modfloatc.style.border="1px solid #FA8178";
	modfloatc.innerHTML = "&nbsp;";
	modfloatc.style.visibility = "visible";
	modfloatc.style.display = "block";
	var pos = getPosition(obj);
	modfloatc.style.left = pos.x;
	modfloatc.style.top = pos.y+9;
	setTimeout("movecondiv('"+modid+"',6)",100);
}
//�������_��ʾ�����Ʋ˵�(����)
function movecondiv(modname,times){
	var modfloatc = GE("modfloatc"+modname);
	for(var i=0;i<times-2;i++){
		for(var i=0;i<times;i++){
			modfloatc.innerHTML += "&nbsp;";
		}
		modfloatc.innerHTML += "&nbsp;<br>";
	}
	modfloatc.style.left = parseInt(modfloatc.style.left)-4; 
	modfloatc.style.top = parseInt(modfloatc.style.top)+1; 
	if(times>0){
		setTimeout("movecondiv('"+modname+"',"+(times-1)+")",(times-1));
	}else{
		modfloatc.innerHTML = "";
		modfloatc.style.border="0px solid #FA8178";
		var pulldown = document.createElement("DIV");
		pulldown.className = "pull_down";
		var ul = document.createElement("UL");
		pulldown.appendChild(ul);
		var nowNode = GE("item"+modname);
		var con = nowNode.offsetParent;
		if(con.firstChild != nowNode){
			ul.appendChild(GE("pulldown_upmod").cloneNode(true));
		}
		if(con.lastChild !=nowNode){
			ul.appendChild(GE("pulldown_dnmod").cloneNode(true));
		}
		ul.appendChild(GE("pulldown_edmodname").cloneNode(true));
		ul.appendChild(GE("pulldown_edmodcon").cloneNode(true));
		ul.appendChild(GE("pulldown_inmod").cloneNode(true));
		if(con.childNodes.length>1)
			ul.appendChild(GE("pulldown_dlmod").cloneNode(true));
		modfloatc.appendChild(pulldown);
	}
}
//�������_���ư��
function MoveupMod(obj){
	obj.offsetParent.offsetParent.style.display="none";
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	
	var nowNode = item;
	var preNode = item.previousSibling;
	var conobj = item.parentNode;
	nowNode.style.visibility = "hidden";
	preNode.style.visibility = "hidden";
	
	var pos = getPosition(nowNode);
	var pospre = getPosition(preNode);
	conobj.insertBefore(nowNode,preNode);

	setCookie();
	
	animation(nowNode,preNode,pos,pospre);
}
//�������_���ư��
function MovedownMod(obj){
	obj.offsetParent.offsetParent.style.display="none";
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	
	var nowNode = item;
	var nextNode = item.nextSibling;
	var conobj = nowNode.parentNode;
	nowNode.style.visibility = "hidden";
	nextNode.style.visibility = "hidden";
	
	var pos = getPosition(nextNode);
	var pospre = getPosition(nowNode);
	conobj.insertBefore(nextNode,nowNode);
	
	setCookie();
	
	animation(nextNode,nowNode,pos,pospre);
}
//�������_�����ƶ�ģ��ʱ�Ķ�����nowNode�����ƺ��ģ�顣preNode�����ƺ��ģ�顣pos������ǰ��ģ���λ�á�pospre������ǰ��ģ��λ�á���
function animation(nowNode,preNode,pos,pospre){
	nowNodecopy = nowNode.cloneNode(true);
	preNodecopy = preNode.cloneNode(true);

	nowNode.lastChild.style.visibility = "hidden";
	preNode.lastChild.style.visibility = "hidden";
	
	var movdiv = GE("movdiv");
	movdiv.appendChild(preNodecopy);
	movdiv.appendChild(nowNodecopy);
	
	nowNodecopy.setAttribute("id",nowNode.id+"copy");
	preNodecopy.setAttribute("id",preNode.id+"copy");
	
	nowNodecopy.style.position = "absolute";
	nowNodecopy.style.top = pos.y;
	nowNodecopy.style.left = pos.x;
	nowNodecopy.style.width = nowNode.offsetWidth;
	preNodecopy.style.position = "absolute";
	preNodecopy.style.top = pospre.y;
	preNodecopy.style.left = pospre.x;
	preNodecopy.style.width = preNode.offsetWidth;
	
	nowNodecopy.style.visibility = "visible";
	preNodecopy.style.visibility = "visible";
	setTimeout("modani('"+nowNode.getAttribute("id")+"','"+preNode.id+"',10)",10);
}
//�������_�����ƶ�ģ��ʱ�Ķ���Ч��
function modani(nowNodeid,preNodeid,times){
	var nowNodecopy = GE(nowNodeid+"copy");
	var preNodecopy = GE(preNodeid+"copy");
	nowNodecopy.style.top = parseInt(nowNodecopy.style.top)-(preNodecopy.offsetHeight+5)/10;
	preNodecopy.style.top = parseInt(preNodecopy.style.top)+(nowNodecopy.offsetHeight+5)/10;
	if(times>0){
		setTimeout("modani('"+nowNodeid+"','"+preNodeid+"',"+(times-1)+")",10);
	}else{
		var movdiv = GE("movdiv");
		movdiv.removeChild(nowNodecopy);
		movdiv.removeChild(preNodecopy);
		var nowNode = GE(nowNodeid);
		var preNode = GE(preNodeid);
		nowNode.style.visibility = "visible";
		nowNode.lastChild.style.visibility = "visible";
		preNode.style.visibility = "visible";
		preNode.lastChild.style.visibility = "visible";
	}
}
//�������_������(��ʾ)
function showAddInMod(obj){
	obj.offsetParent.offsetParent.style.display="none";
	var moditem = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var modid = moditem.getAttribute("id").substring(4);
	
	var charu = GE("charu");
	var adddiv = GE("charu_tr");
	for(;adddiv.childNodes.length>1;){
		adddiv.removeChild(adddiv.childNodes[1]);
	}

	for(var i=0; i<modpool.childNodes.length; i++){
		if(modpool.childNodes[i].nodeName=='#text') continue;	//����TextNode����
		if(modpool.childNodes[i].nodeName=='#comment') continue;	//����Comment����
		
		var isshow = modpool.childNodes[i].getAttribute("isshow");
		var id = modpool.childNodes[i].getAttribute("id");
		var modname = modpool.childNodes[i].getAttribute("modname");
		if(isshow=="0"){
			var item = document.createElement("TR");
			item.setAttribute("align","center");
			var itemname = document.createElement("TD");
			itemname.appendChild(document.createTextNode(modname));
			item.appendChild(itemname);
			var itemopt = document.createElement("TD");
			itemopt.innerHTML = "<input type='button' value='������' onClick=\"addInMod('"+id+"','"+modid+"');\">";
			
			item.appendChild(itemopt);
			adddiv.appendChild(item);
		}
	}
	if(adddiv.childNodes.length==1){
		var item = document.createElement("TR");
		item.setAttribute("align","center");
		var tipinfo = document.createElement("TD");
		tipinfo.appendChild(document.createTextNode("û�пɲ���İ�飡"));
		item.appendChild(tipinfo);
		adddiv.appendChild(item);
	}
	showFloat(moditem,'charu');
}
//�������_������(����)
function addInMod(modid,modidnow){
	GE("anidiv").style.display="none";
	
	var nowNode = GE("item"+modidnow);
	var conobj = nowNode.parentNode;
	var mod = GE(modid);
	mod.setAttribute("isshow","1");
	mod.setAttribute("contype",conobj.getAttribute("contype"));
	modincon(mod);
	setContent(mod);
	conobj.insertBefore(conobj.lastChild,nowNode);
	
	setCookie();
}

//ҳ������_��ʾ����������
function showFloat(obj,floatname){
	//����������������ݹ黹
	var modfloatc = GE("anidiv");
	var layobj = modfloatc.firstChild;
	if(layobj!=null){
		elepool.appendChild(layobj);
	}
	//��ʾ�����㲢��λ
	var pos;
	if(obj==null){
		var ev = ev || window.event;
		pos = mouseCoords(ev);
	}else{
		pos = getPosition(obj);
	}
	
	modfloatc.style.left = pos.x-3;
	modfloatc.style.top = pos.y+9;
	modfloatc.style.border="2px solid gray";
	//������ʾ�����㲢�滻����
	setTimeout("anvate('"+floatname+"',6);",100);
}
//ҳ������_��ʾ����������(����)
function anvate(modname,times){
	var modfloatc = GE("anidiv");
	modfloatc.style.display = "block";
	for(var i=0;i<times;i++){
		for(var i=0;i<times;i++){
			modfloatc.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		modfloatc.innerHTML += "&nbsp;<br>";
	}
	modfloatc.style.left = parseInt(modfloatc.style.left)+1; 
	modfloatc.style.top = parseInt(modfloatc.style.top)+3; 
	if(times>0){
		setTimeout("anvate('"+modname+"',"+(times-1)+")",(times-1));
	}else{
		modfloatc.innerHTML = "";
		modfloatc.style.border="0px solid #000";
		modfloatc.appendChild(GE(modname+"_con"));
	}
}
//ҳ������_��ʾ��������������
function showHelpFloat(obj,floatname){
	//����������������ݹ黹
	var modfloatc = GE("helpanidiv");
	var layobj = modfloatc.firstChild;
	if(layobj!=null)
		elepool.appendChild(layobj);
	//��ʾ�����㲢��λ
	var pos;
	if(obj==null){
		var ev = ev || window.event;
		pos = mouseCoords(ev);
	}else{
		pos = getPosition(obj);
	}
	
	modfloatc.style.left = pos.x-20;
	modfloatc.style.top = pos.y+10;
	modfloatc.style.border="1px solid blue";
	//������ʾ�����㲢�滻����
	setTimeout("anvateHelp('"+floatname+"',6);",100);
}
//ҳ������_��ʾ��������������(����)
function anvateHelp(modname,times){
	var modfloatc = GE("helpanidiv");
	modfloatc.style.display = "block";
	
	for(var j=0;j<10-times;j++){
		modfloatc.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	modfloatc.innerHTML += "&nbsp;<br>";
		
	modfloatc.style.left = parseInt(modfloatc.style.left)-10; 
	modfloatc.style.top = parseInt(modfloatc.style.top)+3; 
	if(times>0){
		setTimeout("anvateHelp('"+modname+"',"+(times-1)+")",(times-1));
	}else{
		modfloatc.innerHTML = "";
		modfloatc.style.border="0px solid #000";
		modfloatc.appendChild(GE(modname+"_con"));
	}
}

//����Cookie(���ӡ�ɾ�����ƶ�ģ��ʱҪ��¼��Ϣ��ģ����Ƿ���ʾisshow��λ��contype��˳��order������ҳ����fieldwidth�Ͳ���laytypeʱҲҪ��¼��Ϣ)
function setCookie(){
	var fieldwidth = GE("fieldwidth").value;
	var laytype = GE("laytype").value;
	for(var i=0;i<smcon.childNodes.length;i++){
		var modid = smcon.childNodes[i].getAttribute("id").substring(4);
		GE(modid).setAttribute("order",i+1);
	}
	for(var i=0;i<bgcon.childNodes.length;i++){
		var modid = bgcon.childNodes[i].getAttribute("id").substring(4);
		GE(modid).setAttribute("order",i+1);
	}
}
