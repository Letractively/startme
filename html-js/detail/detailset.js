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
var mouseOffset = null;	//鼠标和移动对象的偏移量
var iMouseDown  = false;	//鼠标点下的状态
var lMouseState = false;	//前一次鼠标点下的状态

var DragDrops   = [];	//拖拽容器组
var curTarget   = null;	//当前拖拽对象
var lastTarget  = null;	//上一次拖拽对象
var dragHelper  = null;	//当前拖拽对象的复制器,跟随鼠标移动

var rootParent  = null;	//当前拖拽对象的父对象(容器)
var rootSibling = null;	//当前拖拽对象的后一兄弟对象

document.onmousemove = mouseMove;
document.onmousedown = mouseDown;
document.onmouseup   = mouseUp;

//初始化拖拽容器
function init(con){
	//获取拖拽容器数组，如果没有则返回
	var containers = con;
	if(containers==null || typeof(containers)=="undefined" || containers.length <=0){
		return false;
	}
	//创建拖拽容器组
	CreateDragContainer(containers);
	//创建拖拽块的阴影复制块
	dragHelper = document.createElement('DIV');
	dragHelper.style.cssText = 'position:absolute;display:none;';
	document.body.appendChild(dragHelper);
}

//创建容器组，传入的数组为容器对象数组，模块可以在容器组内拖动
function CreateDragContainer(cons){
	/*
	Create a new "Container Instance" so that items from one "Set" can not
	be dragged into items from another "Set"
	*/
	var cDrag        = DragDrops.length;	//当前拖拽容器数组序列
	DragDrops[cDrag] = [];
	/*
	Each item passed to this function should be a "container".  Store each
	of these items in our current container
	*/
	//设置当前容器数组的容器对象
	for(var i=0; i<cons.length; i++){
		var cObj = cons[i];
		DragDrops[cDrag].push(cObj);
		cObj.setAttribute('DropObj', cDrag);	//设置容器为可拖动

		/*
		Every top level item in these containers should be draggable.  Do this
		by setting the DragObj attribute on each item and then later checking
		this attribute in the mouseMove function
		*/
		//设置当前容器对象的子节点为拖拽模块
		for(var j=0; j<cObj.childNodes.length; j++){

			// Firefox puts in lots of #text nodes...skip these
			if(cObj.childNodes[j].nodeName=='#text') continue;	//过滤文本节点

			cObj.childNodes[j].setAttribute('DragObj', cDrag);	//设置模块为可拖动
			cObj.childNodes[j].childNodes[0].setAttribute('DragObj', cDrag);	//设置模块栏为可拖动
			//设置模块标题为可拖动
			cObj.childNodes[j].childNodes[0].childNodes[0].setAttribute('DragObj', cDrag);
			cObj.childNodes[j].childNodes[0].childNodes[0].setAttribute('DragObjTitle', cDrag);
		}
	}
}

//记录拖拽历史
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

//获取鼠标和目标的偏移量
function getMouseOffset(target, ev){
	ev = ev || window.event;

	var docPos    = getPosition(target);
	var mousePos  = mouseCoords(ev);
	return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
}

//鼠标移动的事件处理函数，拖动的实现（登录时使用）
function mouseMove(ev){
	ev = ev || window.event;

	/*
	We are setting target to whatever item the mouse is currently on
	Firefox uses event.target here, MSIE uses event.srcElement
	*/
	//获取事件的对象
	var target   = ev.target || ev.srcElement;

	// mouseOut event - fires if the item the mouse is on has changed
	//还原上一个拖拽项的样式(先没用,可以去除)
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
	//检查目标是否为可拖拽
	var dragObj = target.getAttribute('DragObj');

	// if the mouse was moved over an element that is draggable
	if(dragObj!=null){	//对象为可拖拽时(设置拖拽项)

		// mouseOver event - Change the item's class if necessary
		//设置当前拖拽目标的悬浮时样式(先没用,可以去除)
		if(target!=lastTarget){
			writeHistory(target, 'Mouse Over Fired');

			var oClass = target.getAttribute('overClass');
			if(oClass){
				target.setAttribute('origClass', target.className);
				target.className = oClass;
			}
		}

		// if the user is just starting to drag the element
		//点下并移动(瞬间)进行设置开始拖拽
		if(iMouseDown && !lMouseState){
			writeHistory(target, 'Start Dragging');

			// mouseDown target
			//如果拖拽在标题文字上,则移动的目标为其上一级(标题栏)
			if(target.getAttribute("DragObjTitle")!=null){
				target = target.parentNode;
			}
			//将移动的目标设置为标题栏的上一级(整个模块DIV)
			curTarget     = target.parentNode;

			// Record the mouse x and y offset for the element
			//设置下当前拖拽项的位置,以便移出容器时还原其位置
			rootParent    = curTarget.parentNode;
			rootSibling   = curTarget.nextSibling;

			//获取鼠标偏移量
			mouseOffset   = getMouseOffset(target, ev);

			//设置跟随鼠标的拖拽项复制品
			// We remove anything that is in our dragHelper DIV so we can put a new item in it.
			for(var i=0; i<dragHelper.childNodes.length; i++) dragHelper.removeChild(dragHelper.childNodes[i]);
			// Make a copy of the current item and put it in our drag helper.
			dragHelper.appendChild(curTarget.cloneNode(true));
			dragHelper.style.display = 'block';
			//设置拖拽项复制品的拖拽时样式(先没用,可以去除)
			// set the class on our helper DIV if necessary
			var dragClass = curTarget.getAttribute('dragClass');
			if(dragClass){
				dragHelper.firstChild.className = dragClass;
			}

			// disable dragging from our helper DIV (it's already being dragged)
			//去除拖拽项复制器的拖拽属性
			dragHelper.firstChild.removeAttribute('DragObj');

			/*
			Record the current position of all drag/drop targets related
			to the element.  We do this here so that we do not have to do
			it on the general mouse move event which fires when the mouse
			moves even 1 pixel.  If we don't do this here the script
			would run much slower.
			*/
			var dragConts = DragDrops[dragObj];	//获取拖拽容器组

			/*
			first record the width/height of our drag item.  Then hide it since
			it is going to (potentially) be moved out of its parent.
			*/
			//设置当前拖拽项的响应区并隐藏当前拖拽项
			curTarget.setAttribute('startWidth',  parseInt(curTarget.offsetWidth));
			curTarget.setAttribute('startHeight', parseInt(curTarget.offsetHeight));
			curTarget.style.display  = 'none';

			// loop through each possible drop container
			//循环处理容器组的容器响应区和容器内拖拽模块的响应区
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
					//将拖拽容器响应高度增加6000像素，不少了吧
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
	if(curTarget){	//具有当前对象时(正在执行拖拽操作)
		//获取鼠标位置
		var mousePos = mouseCoords(ev);
		//move our helper div to wherever the mouse is (adjusted by mouseOffset)
		//设置拖拽项复制器的位置
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
		//循环指定容器组中的容器,找出模块进入到的容器
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
		//设置拖拽项的位置和显示
		if(activeCont){	//进入到容器内
			if(activeCont!=curTarget.parentNode){
				writeHistory(curTarget, 'Moved into '+activeCont.id);
			}

			// beforeNode will hold the first node AFTER where our div belongs
			//查看当前拖拽项在容器内是否在某个节点之前
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
			//做拖拽操作(移动元素)
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
			//先设置容器参数，修改内容。
			var mod = GE(curTarget.getAttribute("id").substring(4));
			mod.setAttribute("contype",curTarget.parentNode.getAttribute("contype"));
			setContent(mod);
				
			
			// the timeout is here because the container doesn't "immediately" resize
			//重新设置容器的响应区
			setTimeout(function(){
			var contPos = getPosition(activeCont);
			activeCont.setAttribute('startWidth',  parseInt(activeCont.offsetWidth));
			//将拖拽容器响应高度增加6000像素，不少了吧
			activeCont.setAttribute('startHeight', parseInt(activeCont.offsetHeight)+6000);
			activeCont.setAttribute('startLeft',   contPos.x);
			activeCont.setAttribute('startTop',    contPos.y);}, 5);

			// make our drag item visible
			//设置拖拽项为拖拽中
			if(curTarget.style.display!=''){
				writeHistory(curTarget, 'Made Visible');
				curTarget.style.display    = '';
				curTarget.style.border = "#000 2px dotted";
				curTarget.firstChild.style.visibility = 'hidden';
				curTarget.lastChild.style.visibility = 'hidden';
				curTarget.style.filter = 'alpha(opacity=20)';
			}
		} else {	//在容器外则隐藏拖拽项

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
	//防止拖拽移动时内容被选中高亮显示
	if(curTarget) return false;
}

//鼠标放开的事件处理函数，拖动的实现（登录时使用）
function mouseUp(ev){
	//如果有当前拖拽对象,则进行设置拖拽结果
	if(curTarget){
		writeHistory(curTarget, 'Mouse Up Fired');
		//如果当前拖拽对象不在容器内,则还原拖拽项的位置
		if(curTarget.style.display == 'none'){
			if(rootSibling){
				rootParent.insertBefore(curTarget, rootSibling);
			} else {
				rootParent.appendChild(curTarget);
			}
			curTarget.style.display = '';
		}
		//修改模块所在的容器(这边要修改一下，除了要设置本对象的容器类型外，还要设置所有对象的排序，并记录Ｃｏｏｋｉｅ和Ａｊａｘ入库。
		//先设置容器参数，修改内容。
		var mod = GE(curTarget.getAttribute("id").substring(4));
		mod.setAttribute("contype",curTarget.parentNode.getAttribute("contype"));
		setContent(mod);
		setCookie();
		//归位动画
		modinconani(dragHelper,curTarget);
		
		//将跟随鼠标的拖拽复制品隐藏
		dragHelper.style.display = 'none';
		
		
	}
		//置当前拖拽项为空
		curTarget  = null;
	//去除鼠标点下状态
	iMouseDown = false;
}

//模块归位时的动画（fromNode：从某节点。toNode：到某节点。）
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
//版块设置_上下移动模块时的动画效果
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
		//设置当前拖拽项的显示样式为拖拽完毕
		toNode.style.display = '';
		toNode.firstChild.style.visibility = 'visible';
		toNode.lastChild.style.visibility = 'visible';
		toNode.style.filter = 'alpha(opacity=100)';
	}
}

//鼠标点下的事件处理函数，拖动的实现（登录时使用）
function mouseDown(ev){
	ev = ev || window.event;
	var target = ev.target || ev.srcElement;
	//置鼠标点下状态
	iMouseDown = true;
	
	if(lastTarget){
		writeHistory(lastTarget, 'Mouse Down Fired');
	}	
	
	if(target.onmousedown || target.getAttribute('DragObj')){
		return false;
	}

}

//网站布局_设定Layout
function setLayout(laytype){
	GE("laytype").value = laytype;
	showLayout();
	
	setCookie();
}
//网站布局_设定页面宽度
function setSetWidth(wid){
	if(wid<780||wid>1024){
		alert("所设置值必须在780——1024像素之间!");
		return false;
	}
	GE("fieldwidth").value = wid;	//设置宽度值
	showSetWidth();
	
	setCookie();
}


//风格主题_设定普通主题类型
function setStyleType(stype){
	var scolor = GE('stylecolor').value;
	var theme = "./templet/style_"+stype+"_"+scolor+".css";
	GE('themestyle').value = theme;
	GE('styletype').value = stype;
	showStyle();
}
//风格主题_设定普通主题颜色
function setStyleColor(scolor){
	GE("stylecolor"+GE('stylecolor').value).className = "";	//将原来的颜色边框去掉
	
	var stype = GE('styletype').value;
	var theme = "./templet/style_"+stype+"_"+scolor+".css";
	GE('themestyle').value = theme;
	GE('stylecolor').value = scolor;
	showStyle();
}
//风格主题_设定高级主题
function setStyle(theme){
	GE('themestyle').value = theme;
	showStyle(theme);
}
//风格主题_设定页面背景
function setBackground(color){
	GE("background").value = color;
	showBackground(color);
}
//风格主题_设定页面鼠标
function setMouseStyle(pic){
	GE('mousestyle').value = pic;
	showMouseStyle(pic);
}
	
	
//导航设置_显示设定导航菜单
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
		itemshowhtml += " />显示";
		itemshow.innerHTML = itemshowhtml;
		item.appendChild(itemshow);
		
		var itemmove = document.createElement("TD");
		itemmovehtml = "<input type='button' value='上移' onClick='moveupitem(this);'/>";
		itemmovehtml += "<input type='button' value='下移' onClick='movedownitem(this);'/>";
		itemmove.innerHTML = itemmovehtml;
		item.appendChild(itemmove);
		
		adddiv.appendChild(item);
	}
}
//导航设置_设定导航菜单上移
function moveupitem(obj){
	var nowitem = obj.offsetParent.parentNode;
	var upitem = nowitem.previousSibling;
	var con = nowitem.parentNode;
	if(nowitem == con.childNodes[2]){
		alert("不能再上移了！");
		return false;
	}
	con.insertBefore(nowitem,upitem);
}
//导航设置_设定导航菜单下移
function movedownitem(obj){
	var nowitem = obj.offsetParent.parentNode;
	var con = nowitem.parentNode;
	if(nowitem == con.lastChild){
		alert("不能再下移了！");
		return false;
	}
	var downitem = nowitem.nextSibling;
	con.insertBefore(downitem,nowitem);
}
//导航设置_保存设定导航菜单
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

//版块设置_最大化最小化版块
function maxminMod(obj){
	var modcon = obj.parentNode.parentNode.parentNode.parentNode.lastChild;
	if(modcon.style.display == ""){
		modcon.style.display = "none";
		obj.setAttribute("class","boxtitlec_max");
		obj.className="boxtitlec_max";
		obj.setAttribute("title","最大化版块");
	}else{
		modcon.style.display = "";
		obj.setAttribute("class","boxtitlec_min");
		obj.className="boxtitlec_min";
		obj.setAttribute("title","最小化版块");
	}
}
//版块设置_删除版块
function delMod(obj){
	var item = obj.parentNode.parentNode.parentNode.parentNode;
	var con = item.parentNode;
	if(con.childNodes.length==1){
		alert("当前位置必须要有一个栏目！");
		return false;
	}
	//保存信息
	var mod = GE(item.getAttribute("id").substring(4));
	mod.setAttribute("isshow","0");
	mod.style.display="";	//还原设置显示模块（当添加时会显示出最大化的模块）
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
//版块设置_删除版块(菜单)
function deleteMod(obj){
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var con = item.parentNode;
	con.removeChild(item);
	
	var mod = GE(item.getAttribute("id").substring(4));
	mod.setAttribute("isshow","0");
	mod.style.display="";	//还原设置显示模块（当添加时会显示出最大化的模块）
	setCookie();	
}
//版块设置_编辑版块名称
function changeNameMod(obj){
	obj.offsetParent.offsetParent.style.display="none";
	
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	var modid = item.getAttribute("id").substring(4);
	
	GE("edtingmod").value = modid;
	GE("edtingmodname").value = GE(modid).modname;
	showFloat(item,'editname');
}
//版块设置_保存版块名称
function setModName(){
	GE('anidiv').style.display='none';
	var modid = GE("edtingmod").value;
	var modname = GE("edtingmodname").value;
	GE(modid).modname = modname;
	var nowNode = GE("item"+modid);
	nowNode.childNodes[0].childNodes[0].innerHTML = modname;
	setCookie();
}
//版块设置_编辑版块内容（转到商务中心页）
function editModContent(obj){
	obj.offsetParent.offsetParent.style.display="none";
	var item = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
	window.open(GE(item.getAttribute("id").substring(4)).getAttribute("modurl"));
}
//版块设置_显示版块控制菜单
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
//版块设置_显示版块控制菜单(动画)
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
//版块设置_上移版块
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
//版块设置_下移版块
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
//版块设置_上下移动模块时的动画（nowNode：上移后的模块。preNode：下移后的模块。pos：上移前的模块的位置。pospre：下移前的模块位置。）
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
//版块设置_上下移动模块时的动画效果
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
//版块设置_插入版块(显示)
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
		if(modpool.childNodes[i].nodeName=='#text') continue;	//跳过TextNode对象
		if(modpool.childNodes[i].nodeName=='#comment') continue;	//跳过Comment对象
		
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
			itemopt.innerHTML = "<input type='button' value='插入版块' onClick=\"addInMod('"+id+"','"+modid+"');\">";
			
			item.appendChild(itemopt);
			adddiv.appendChild(item);
		}
	}
	if(adddiv.childNodes.length==1){
		var item = document.createElement("TR");
		item.setAttribute("align","center");
		var tipinfo = document.createElement("TD");
		tipinfo.appendChild(document.createTextNode("没有可插入的版块！"));
		item.appendChild(tipinfo);
		adddiv.appendChild(item);
	}
	showFloat(moditem,'charu');
}
//版块设置_插入版块(保存)
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

//页面整体_显示浮动控制栏
function showFloat(obj,floatname){
	//将浮动动画层的内容归还
	var modfloatc = GE("anidiv");
	var layobj = modfloatc.firstChild;
	if(layobj!=null){
		elepool.appendChild(layobj);
	}
	//显示动画层并定位
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
	//动画显示浮动层并替换内容
	setTimeout("anvate('"+floatname+"',6);",100);
}
//页面整体_显示浮动控制栏(动画)
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
//页面整体_显示帮助浮动控制栏
function showHelpFloat(obj,floatname){
	//将浮动动画层的内容归还
	var modfloatc = GE("helpanidiv");
	var layobj = modfloatc.firstChild;
	if(layobj!=null)
		elepool.appendChild(layobj);
	//显示动画层并定位
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
	//动画显示浮动层并替换内容
	setTimeout("anvateHelp('"+floatname+"',6);",100);
}
//页面整体_显示帮助浮动控制栏(动画)
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

//设置Cookie(增加、删除、移动模块时要记录信息（模块的是否显示isshow、位置contype和顺序order，调整页面宽度fieldwidth和布局laytype时也要记录信息)
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
