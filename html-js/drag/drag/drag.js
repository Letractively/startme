
/* 
iMouseDown represents the current mouse button state: up or down
lMouseState represents the previous mouse button state so that we can
check for button clicks and button releases:
if(iMouseDown && !lMouseState) // button just clicked!
if(!iMouseDown && lMouseState) // button just released! 
*/
var mouseOffset = null;
var iMouseDown  = false;
var lMouseState = false;
//var dragObject  = null;

// Demo 0 variables
var DragDrops   = [];
var curTarget   = null;
var lastTarget  = null;
var dragHelper  = null;
//var tempDiv     = null;
var rootParent  = null;
var rootSibling = null;

Number.prototype.NaN0=function(){return isNaN(this)?0:this;}

function CreateDragContainer(cons){
	/*
	Create a new "Container Instance" so that items from one "Set" can not
	be dragged into items from another "Set"
	*/
	var cDrag        = DragDrops.length;
	DragDrops[cDrag] = [];
	/*
	Each item passed to this function should be a "container".  Store each
	of these items in our current container
	*/
	for(var i=0; i<cons.length; i++){
		var cObj = cons[i];
		DragDrops[cDrag].push(cObj);
		cObj.setAttribute('DropObj', cDrag);

		/*
		Every top level item in these containers should be draggable.  Do this
		by setting the DragObj attribute on each item and then later checking
		this attribute in the mouseMove function
		*/
		for(var j=0; j<cObj.childNodes.length; j++){

			// Firefox puts in lots of #text nodes...skip these
			if(cObj.childNodes[j].nodeName=='#text') continue;

			cObj.childNodes[j].setAttribute('DragObj', cDrag);
			cObj.childNodes[j].childNodes[0].setAttribute('DragObj', cDrag);
		}
	}
}

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

function mouseCoords(ev){
	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y:ev.clientY + document.body.scrollTop  - document.body.clientTop
	};
}

function writeHistory(object, message){
	if(!object || !object.parentNode || !object.parentNode.getAttribute) return;
	var historyDiv = object.parentNode.getAttribute('history');
	if(historyDiv){
		historyDiv = document.getElementById(historyDiv);
		historyDiv.appendChild(document.createTextNode(object.id+': '+message));
		historyDiv.appendChild(document.createElement('BR'));

		historyDiv.scrollTop += 50;
	}
}

function getMouseOffset(target, ev){
	ev = ev || window.event;

	var docPos    = getPosition(target);
	var mousePos  = mouseCoords(ev);
	return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
}

function mouseMove(ev){
	ev         = ev || window.event;

	/*
	We are setting target to whatever item the mouse is currently on

	Firefox uses event.target here, MSIE uses event.srcElement
	*/
	var target   = ev.target || ev.srcElement;
	var mousePos = mouseCoords(ev);

	//yehyhidden if(Demos[0] || Demos[4]){
		// mouseOut event - fires if the item the mouse is on has changed
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
		var dragObj = target.getAttribute('DragObj');

		 // if the mouse was moved over an element that is draggable
		if(dragObj!=null){

			// mouseOver event - Change the item's class if necessary
			if(target!=lastTarget){
				writeHistory(target, 'Mouse Over Fired');

				var oClass = target.getAttribute('overClass');
				if(oClass){
					target.setAttribute('origClass', target.className);
					target.className = oClass;
				}
			}

			// if the user is just starting to drag the element
			if(iMouseDown && !lMouseState){
				writeHistory(target, 'Start Dragging');

				// mouseDown target
				curTarget     = target.parentNode;

				// Record the mouse x and y offset for the element
				rootParent    = curTarget.parentNode;
				rootSibling   = curTarget.nextSibling;

				mouseOffset   = getMouseOffset(target, ev);

				// We remove anything that is in our dragHelper DIV so we can put a new item in it.
				for(var i=0; i<dragHelper.childNodes.length; i++) dragHelper.removeChild(dragHelper.childNodes[i]);

				// Make a copy of the current item and put it in our drag helper.
				dragHelper.appendChild(curTarget.cloneNode(true));
				dragHelper.style.display = 'block';

				// set the class on our helper DIV if necessary
				var dragClass = curTarget.getAttribute('dragClass');
				if(dragClass){
					dragHelper.firstChild.className = dragClass;
				}

				// disable dragging from our helper DIV (it's already being dragged)
				dragHelper.firstChild.removeAttribute('DragObj');

				/*
				Record the current position of all drag/drop targets related
				to the element.  We do this here so that we do not have to do
				it on the general mouse move event which fires when the mouse
				moves even 1 pixel.  If we don't do this here the script
				would run much slower.
				*/
				var dragConts = DragDrops[dragObj];

				/*
				first record the width/height of our drag item.  Then hide it since
				it is going to (potentially) be moved out of its parent.
				*/
				curTarget.setAttribute('startWidth',  parseInt(curTarget.offsetWidth));
				curTarget.setAttribute('startHeight', parseInt(curTarget.offsetHeight));
				curTarget.style.display  = 'none';

				// loop through each possible drop container
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
		if(curTarget){
			// move our helper div to wherever the mouse is (adjusted by mouseOffset)
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
			if(activeCont){
				if(activeCont!=curTarget.parentNode){
					writeHistory(curTarget, 'Moved into '+activeCont.id);
				}

				// beforeNode will hold the first node AFTER where our div belongs
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

				//修改模块所在的容器(这边要修改一下，除了要设置本对象的容器类型外，还要设置所有对象的排序，并记录Ｃｏｏｋｉｅ和Ａｊａｘ入库。
				//..todo setCookie()
				curTarget.lastChild.setAttribute("contype",activeCont.getAttribute("contype"));
				
				
				// the timeout is here because the container doesn't "immediately" resize
				setTimeout(function(){
				var contPos = getPosition(activeCont);
				activeCont.setAttribute('startWidth',  parseInt(activeCont.offsetWidth));
				//将拖拽容器响应高度增加6000像素，不少了吧
				activeCont.setAttribute('startHeight', parseInt(activeCont.offsetHeight)+6000);
				activeCont.setAttribute('startLeft',   contPos.x);
				activeCont.setAttribute('startTop',    contPos.y);}, 5);

				// make our drag item visible
				if(curTarget.style.display!=''){
					writeHistory(curTarget, 'Made Visible');
					curTarget.style.display    = '';
					//curTarget.style.visibility = 'hidden';
					curTarget.style.filter = 'alpha(opacity=20)';
				}
			} else {

				// our drag item is not in a container, so hide it.
				if(curTarget.style.display!='none'){
					writeHistory(curTarget, 'Hidden');
					curTarget.style.display  = 'none';
				}
			}
		}

		// track the current mouse state so we can compare against it next time
		lMouseState = iMouseDown;

		// mouseMove target
		lastTarget  = target;
	//yehyhidden }
	//yehyhidden if(Demos[2]){
	//	document.getElementById('MouseXPosition').value = mousePos.x;
	//	document.getElementById('MouseYPosition').value = mousePos.y;
	//yehyhidden }

	/*if(dragObject){
		dragObject.style.position = 'absolute';
		dragObject.style.top      = mousePos.y - mouseOffset.y;
		dragObject.style.left     = mousePos.x - mouseOffset.x;
	}*/

	// track the current mouse state so we can compare against it next time
	lMouseState = iMouseDown;

	// this prevents items on the page from being highlighted while dragging
	if(curTarget) return false;
}

function mouseUp(ev){

	//yehyhidden if(Demos[0] || Demos[4]){
		if(curTarget){
			writeHistory(curTarget, 'Mouse Up Fired');

			dragHelper.style.display = 'none';
			if(curTarget.style.display == 'none'){
				if(rootSibling){
					rootParent.insertBefore(curTarget, rootSibling);
				} else {
					rootParent.appendChild(curTarget);
				}
			}
			
			curTarget.style.display    = '';
			//curTarget.style.visibility = 'visible';
			curTarget.style.filter = 'alpha(opacity=100)';
		}
		curTarget  = null;
	//yehyhidden }
	/*if(Demos[6] && dragObject){
		ev           = ev || window.event;
		var mousePos = mouseCoords(ev);

		var dT = dragObject.getAttribute('droptarget');
		if(dT){
			var targObj = document.getElementById(dT);
			var objPos  = getPosition(targObj);
			if((mousePos.x > objPos.x) && (mousePos.y > objPos.y) && (mousePos.x<(objPos.x+parseInt(targObj.offsetWidth))) && (mousePos.y<(objPos.y+parseInt(targObj.offsetHeight)))){
				var nSrc = targObj.getAttribute('newSrc');
				if(nSrc){
					dragObject.src = nSrc;
					setTimeout(function(){
						if(!dragObject || !dragObject.parentNode) return;
						dragObject.parentNode.removeChild(dragObject);
						dragObject = null;
					}, parseInt(targObj.getAttribute('timeout')));
				} else {
					dragObject.parentNode.removeChild(dragObject);
				}
			}
		}
	}*/

	//dragObject = null;

	iMouseDown = false;
}

function mouseDown(ev){
	ev         = ev || window.event;
	var target = ev.target || ev.srcElement;

	iMouseDown = true;
	//yehyhidden if(Demos[0] || Demos[4]){
		if(lastTarget){
			writeHistory(lastTarget, 'Mouse Down Fired');
		}
	//yehyhidden }
	if(target.onmousedown || target.getAttribute('DragObj')){
		return false;
	}
}


document.onmousemove = mouseMove;
document.onmousedown = mouseDown;
document.onmouseup   = mouseUp;

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

//初始化容器
	function initcon(){
		//加入各模块
		for(var i=0; i<modpool.childNodes.length; i++){
			if(modpool.childNodes[i].nodeName=='#text') continue;	//跳过TextNode对象
			if(modpool.childNodes[i].nodeName=='#comment') continue;	//跳过Comment对象
			modincon(modpool.childNodes[i]);
		}
		setcon();
	}
	//设置模块到拖拽容器（供加入模块时用）
	function setcon(){
		//初始化拖拽容器（传入容器ID名，每个容器下的第一级子元素做为一个拖拽项）
		var con = new Array();
		con.push(smcon);
		con.push(bgcon);
		init(con);
		//设置容器时重新排一下序
		for(var i=0; i<bgcon.childNodes.length; i++){
			for(var j=0;j<bgcon.childNodes.length-1;j++){
				if(bgcon.childNodes[j].lastChild.getAttribute("order")>bgcon.childNodes[j+1].lastChild.getAttribute("order")){
					bgcon.insertBefore(bgcon.childNodes[j+1],bgcon.childNodes[j]);
				}
			}
		}
	}
	function setSetWidth(){
		var wid = document.getElementsByName("fieldwidth")[0].value;
		if(allcon.offsetWidth-wid>250){
			alert("一次调整宽度减少不能超过250像素范围!");
			return false;
		}
		if(wid<780||wid>1024){
			alert("所设置值必须在780——1024像素之间!");
			return false;
		}
		allcon.style.width = wid;
		setLayout(document.getElementsByName("laytype")[0].value);
	}
	//去除所有布局
	function removeLayout(){
		//将布局元素归还
		for(var i=0; i<allcon.childNodes.length; i++){
			elepool.appendChild(allcon.childNodes[i]);
		}
	}
	//重新设定布局,laytype(1、左侧2、顶部3、右侧)
	function setLayout(laytype){
		laytype = parseInt(laytype);
		if(laytype!=1&&laytype!=2&&laytype!=3){alert("布局"+laytype+"不存在");return false;}
		removeLayout();
		allcon.appendChild(pageset);
		allcon.appendChild(pagehead);
		allcon.appendChild(pagetitle);
		switch(laytype){
			case 1:
				allcon.appendChild(pagestatus);
				allcon.appendChild(smdiv);
				smdiv.insertBefore(menu, smdiv.firstChild);
				allcon.appendChild(mddiv);
				allcon.appendChild(bgdiv);
				menu.setAttribute("class","menu");
				menu.className = "menu";
				bgdiv.style.width = parseInt(allcon.offsetWidth)
				-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
				break;
			case 2:
				allcon.appendChild(menu);
				allcon.appendChild(pagestatus);
				allcon.appendChild(smdiv);
				allcon.appendChild(mddiv);
				allcon.appendChild(bgdiv);
				menu.setAttribute("class","menutop");
				menu.className = "menutop";
				bgdiv.style.width = parseInt(allcon.offsetWidth)
				-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
				break;
			case 3:
				allcon.appendChild(pagestatus);
				allcon.appendChild(bgdiv);
				allcon.appendChild(mddiv);
				allcon.appendChild(smdiv);
				smdiv.insertBefore(menu, smdiv.firstChild);
				menu.setAttribute("class","menu");
				menu.className = "menu";
				bgdiv.style.width = parseInt(allcon.offsetWidth)
				-parseInt(smdiv.offsetWidth)-parseInt(mddiv.offsetWidth);
				break;
			default:
				break;
		}
		allcon.appendChild(pagetail);
		document.getElementsByName("laytype")[0].value = laytype;
		setCookie();
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
		modtitle.className = "boxtitle";
		var modtitletext = document.createElement('DIV');
		modtitletext.appendChild(document.createTextNode(mod.getAttribute("modname")));
		modtitletext.setAttribute("class","boxtitleleft");
		modtitletext.className = "boxtitleleft";
		modtitle.appendChild(modtitletext);
		var modmanage = document.createElement('DIV');
		if(mod.style.display==""){
		modmanage.innerHTML = "<span onClick=\"javascript:maxminMod('"+mod.id+"');if(this.innerHTML=='—'){ this.title='最大化';this.innerHTML='＋';} else {this.title='最小化';this.innerHTML='—';}\" title='最小化'>—</span> ";
		}else{
		modmanage.innerHTML = "<span onClick=\"javascript:maxminMod('"+mod.id+"');if(this.innerHTML=='—'){ this.title='最大化';this.innerHTML='＋';} else {this.title='最小化';this.innerHTML='—';}\" title='最小化'>＋</span> ";
		}
		modmanage.innerHTML += "<span onClick=\"javascript:delMod('"+mod.id+"');\" title='删除'><font size='2'><b>×</b></font></span>";
		modmanage.setAttribute("class","boxtitleright");
		modmanage.className = "boxtitleright";
		modtitle.appendChild(modmanage);
		//将模块标题和内容加入模块项
		moddiv.appendChild(modtitle);
		moddiv.appendChild(mod);
		//将模块项加入所定容器
		if(mod.getAttribute("contype")!="sm"){
			bgcon.appendChild(moddiv);
		}else{
			smcon.appendChild(moddiv);
		}
	}
	//最小化模块
	function maxminMod(modcode){
		var mod = document.getElementById(modcode);
		if(mod.style.display == ""){
			mod.style.display = "none";
		}else{
			mod.style.display = "";
		}
	}
	//最大化模块
	function maxMod(modcode,ev){
		ev         = ev || window.event;
		var mod = document.getElementById(modcode);
		mod.style.display = "";
		var target   = ev.target || ev.srcElement;
		target.setAttribute("href","javascript:minMod('"+modcode+"');");
		target.innerHTML = "-";
	}
	//删除模块
	function delMod(modcode){
		var mod = document.getElementById(modcode);
		mod.setAttribute("isshow","0");
		modpool.appendChild(mod);
		if(mod.getAttribute("contype")=="bg"){
			bgcon.removeChild(document.getElementById("item"+mod.id));
		}else{
			smcon.removeChild(document.getElementById("item"+mod.id));
		}
		setCookie();
	}
	//显示增加的模块
	function showAddMod(){
		var adddiv = document.getElementById("addable");
		if(adddiv.style.display==""){
			hiddenAddableMod();
			return;
		}
		adddiv.style.display="";
		for(var i=0; i<modpool.childNodes.length; i++){
			var isshow = modpool.childNodes[i].getAttribute("isshow");
			var contype = modpool.childNodes[i].getAttribute("contype");
			var id = modpool.childNodes[i].getAttribute("id");
			var modname = modpool.childNodes[i].getAttribute("modname");
			if(isshow=="0"){
				var mod = document.createElement('A');
				mod.appendChild(document.createTextNode(modname));
				mod.setAttribute("href","javascript:addMod('"+id+"');");
				adddiv.appendChild(mod);
				adddiv.appendChild(document.createTextNode(" "));
			}
		}
	}
	//隐藏可增加的模块
	function hiddenAddableMod(){
		var adddiv = document.getElementById("addable");
		adddiv.innerHTML = "";
		adddiv.style.display="none";
	}
	//增加模块
	function addMod(modid){
		var mod = document.getElementById(modid);
		mod.setAttribute("isshow","1");
		//默认增加到大容器中
		mod.setAttribute("contype","bg");
		modincon(mod);
		setcon();
		hiddenAddableMod();
		setCookie();
	}
	
	//设置Cookie(增加、删除、移动模块时要记录信息（模块的是否显示isshow、位置contype和顺序order，调整页面宽度fieldwidth和布局laytype时也要记录信息)
	function setCookie(){
		var fieldwidth = document.getElementsByName("fieldwidth")[0].value;
		var laytype = document.getElementsByName("laytype")[0].value;
		
		
	}