Dragged = function() {
      this.init.apply(this, arguments);
}
Dragged.prototype = {
  init: function(element, elementDesc, garbageBin) {
    this.element      = element;
    this.cloneElement = null;
    this.elementDesc  = elementDesc;
    this.garbageBin   = garbageBin;
    this.offsetX      = 0;
    this.offsetY      = 0;
    this.originalLeft = this.currentLeft();
    this.originalTop  = this.currentTop();
    this.originalX    = element.offsetLeft;
    this.originalY    = element.offsetTop;
    this.originalZ    = parseInt(this.element.style.zIndex || "0");
    this.active       = false;
    this.dragging     = false;
    this.hotKey       = false;
    this.fillMoveBlank = handleImagePlace;//填补空白位置
  
    /*event 加入firefox中的各控件事件里;
      ie可以不用(event在ie中的各控件事件里是默认的存在)*/
    Event.observe(this.element, "mousedown", this.onMouseDown.bindAsEventListener(this));
    Event.observe(document, "mouseup", this.onMouseUp.bindAsEventListener(this));
    Event.observe(document, "mousemove", this.onMouseMove.bindAsEventListener(this));
    Event.observe(document, "keydown", this.onKeyDown.bindAsEventListener(this));
    Event.observe(document, "keyup", this.onKeyUp.bindAsEventListener(this));
  },
  currentLeft: function() {
    return parseInt(this.element.style.left || '0');
  },
  currentTop: function() {
    return parseInt(this.element.style.top || '0');
  },
  onKeyDown: function(event) {
      if (event.keyCode==65 || event.which==65) {
      	  this.hotKey = true;
      	  Event.stop(event);
      	  return;
      }
      Event.stop(event);
  },
  onKeyUp: function(event) {
      //按"a"键增加
      if (event.keyCode==65 || event.which==65) {
      	  this.hotKey = false;
      	  Event.stop(event);
      	  return;
      }
      Event.stop(event);
  },
  onMouseDown: function(event) {
    if(Event.isLeftClick(event)) {
      this.active = true;
      var style = this.element.style;
      this.originalY = this.element.offsetTop  - this.currentTop()  - this.originalTop;
      this.originalX = this.element.offsetLeft - this.currentLeft() - this.originalLeft;
      this.offsetY =  event.clientY - this.originalY - this.originalTop;
      this.offsetX =  event.clientX - this.originalX - this.originalLeft;
    }
    Event.stop(event);//fireFox中一定要
  },
  onMouseUp: function(event) {
    if(this.active && this.dragging) {
      //移动在外的对象自动返回空白处
      this.element.style.top = -this.originalTop + "px";;
      this.element.style.left = -this.originalLeft + "px";
      this.element.style.zIndex = this.originalZ;
      this.fillMoveBlank(event);
    }
    this.active = false;
    this.dragging = false;
    if(Event.isLeftClick(event)) {
       //ie是srcElement;firefox是target
       if (((event.target && event.target.id==this.element.id)||
       		(event.srcElement && event.srcElement.id==this.element.id))&&this.hotKey){
   	  	   	 var elements = this.elementDesc.childNodes;
   	  	   	 for (i=0; i<elements.length; i++) {
			    if(elements[i].id == this.element.id){
			    	this.hotKey = false;
			    	Event.stop(event);
			    	return;
			    }
			  }
   	  	   	  this.cloneElement = document.createElement('IMG');
			  this.cloneElement.id = this.element.id;
			  this.cloneElement.src = this.element.src;
			  this.cloneElement.style.position = "relative";
   	  		  this.elementDesc.appendChild(this.cloneElement);
   	  		  new Draggable(this.cloneElement, this.elementDesc, this.garbageBin);
   	  		  this.hotKey = false;
   	  }
    }
     Event.stop(event);
  },
  onMouseMove: function(event) {
   if(this.active) {
      if(!this.dragging) {
       var pX = Event.pointerX(event);
	   var pY = Event.pointerY(event);
   	  this.cloneElement = document.createElement('IMG');
	  this.cloneElement.id = this.element.id;
	  this.cloneElement.src = this.element.src;
	  this.cloneElement.style.position = "relative";

        var style = this.element.style;
        this.dragging = true;
        style.zIndex = 10;
      }
      this.moveDraw(event);
      Event.stop(event);
   }
  },//被拖拽对象跟着鼠标移动
  moveDraw: function(event) {
    var style = this.element.style;
    this.originalX = this.element.offsetLeft - this.currentLeft() - this.originalLeft;
    this.originalY = this.element.offsetTop  - this.currentTop()  - this.originalTop;
    style.left = ((event.clientX - this.originalX) - this.offsetX) + "px";
    style.top  = ((event.clientY - this.originalY) - this.offsetY) + "px";
  }
}
  
  function handleImagePlace(event) {
	  var pX = Event.pointerX(event);
	  var pY = Event.pointerY(event);
	  var elements = this.elementDesc.childNodes;
	  if(elements && elements.length!=0) {
		  for (i=0; i<elements.length; i++) {
		    if(elements[i].id == this.element.id){
		    	if((this.cloneElement)&&(this.cloneElement.parentNode)){
	       			this.cloneElement.parentNode.removeChild(this.cloneElement);
					this.cloneElement = null;
			}
		    	return;
		    }
		  }
		  for (i=0; i<elements.length; i++) {
		    if(elements[i] != this.element && pY >= elements[i].offsetTop &&
	             pY <  elements[i].offsetTop + elements[i].offsetHeight && pX >= elements[i].offsetLeft
	             && pX < elements[i].offsetLeft + elements[i].offsetWidth) {
			  if((((elements[i].offsetLeft + elements[i].offsetWidth) - pX) / elements[i].offsetWidth)>0.5) {
		         elements[i].parentNode.insertBefore(this.cloneElement, elements[i]);
		         new Draggable(this.cloneElement, this.elementDesc, this.garbageBin);
		         return;
			  } else {
			    var nextElement = elements[i].nextSibling;
		        if(elements[i] != this.element) {
		        	elements[i].parentNode.insertBefore(this.cloneElement, nextElement);
		        	new Draggable(this.cloneElement, this.elementDesc, this.garbageBin);
	  				return;
		        }
			  }
		   }
		}
	  } 
	  if(pY >= this.elementDesc.offsetTop && pY <  this.elementDesc.offsetTop + this.elementDesc.offsetHeight 
	   	   && pX >= this.elementDesc.offsetLeft && pX < this.elementDesc.offsetLeft + this.elementDesc.offsetWidth){
		   	   this.elementDesc.appendChild(this.cloneElement);
		   	   new Draggable(this.cloneElement, this.elementDesc, this.garbageBin);
		   	   return;
	 }
	 if((this.cloneElement)&&(this.cloneElement.parentNode)){
	       	this.cloneElement.parentNode.removeChild(this.cloneElement);
			this.cloneElement = null;
	}
  }
  
Draggable = function() {
      this.init.apply(this, arguments);
}
Draggable.prototype = {
  init: function(element, elements, garbageBin) {
    this.element      = element;
    this.elementDesc  = elements;
    this.garbageBin   = garbageBin;
    this.offsetX      = 0;
    this.offsetY      = 0;
    this.originalLeft = this.currentLeft();
    this.originalTop  = this.currentTop();
    this.originalX    = element.offsetLeft;
    this.originalY    = element.offsetTop;
    this.originalZ    = parseInt(this.element.style.zIndex || "0");
    this.active       = false;
    this.dragging     = false;
    this.hotKey       = false;
    /*event 加入firefox中的各控件事件里;
      ie可以不用(event在ie中的各控件事件里是默认的存在)*/
    Event.observe(this.element, "mousedown", this.onMouseDown.bindAsEventListener(this));
    Event.observe(document, "mouseup", this.onMouseUp.bindAsEventListener(this));
    Event.observe(document, "mousemove", this.onMouseMove.bindAsEventListener(this));
    Event.observe(document, "keydown", this.onKeyDown.bindAsEventListener(this));
    Event.observe(document, "keyup", this.onKeyUp.bindAsEventListener(this));
  },
  currentLeft: function() {
    return parseInt(this.element.style.left || '0');
  },
  currentTop: function() {
    return parseInt(this.element.style.top || '0')
  },
  onKeyDown: function(event) {
      //按"r"键删除
      if (event.keyCode==82 || event.which==82) {
      	  this.hotKey = true;
      	  Event.stop(event);
      	  return;
      }
      Event.stop(event);
  },
  onKeyUp: function(event) {
      if (event.keyCode==82 || event.which==82) {
      	  this.hotKey = false;
      	  Event.stop(event);
      	  return;
      }
      Event.stop(event);
  },
  onMouseDown: function(event) {
    if(Event.isLeftClick(event)) {
      this.active = true;
      var style = this.element.style;
      this.originalY = this.element.offsetTop  - this.currentTop()  - this.originalTop;
      this.originalX = this.element.offsetLeft - this.currentLeft() - this.originalLeft;
      this.offsetY =  event.clientY - this.originalY - this.originalTop;
      this.offsetX =  event.clientX - this.originalX - this.originalLeft;
    }
    Event.stop(event);//fireFox中一定要
  },
  onMouseUp: function(event) {
    if(this.active && this.dragging) {
      //移动在外的对象自动返回空白处
      this.element.style.top = -this.originalTop + "px";;
      this.element.style.left = -this.originalLeft + "px";
      this.element.style.zIndex = this.originalZ;
      this.fillMoveBlank(event, this.element);
    }
    this.active = false;
    this.dragging = false;
    if(Event.isLeftClick(event)) {
       if (((event.target && event.target.id==this.element.id)||
       		(event.srcElement && event.srcElement.id==this.element.id))&&this.hotKey){
   	  		  this.elementDesc.removeChild(this.element);
   	  		  this.hotKey = false;
   	  }
    }
    Event.stop(event);
  },
  onMouseMove: function(event) {
   if(this.active) {
      if(!this.dragging) {
        var style = this.element.style;
        this.dragging = true;
        style.zIndex = 10;
      }
      this.moveDraw(event);
      Event.stop(event);
   }
  },//被拖拽对象跟着鼠标移动
  moveDraw: function(event) {
    var style = this.element.style;
    this.originalX = this.element.offsetLeft - this.currentLeft() - this.originalLeft;
    this.originalY = this.element.offsetTop  - this.currentTop()  - this.originalTop;
    style.left = ((event.clientX - this.originalX) - this.offsetX) + "px";
    style.top  = ((event.clientY - this.originalY) - this.offsetY) + "px";
  },//填补空白位置
  fillMoveBlank: function(event, element) {
	  var pX = Event.pointerX(event);
	  var pY = Event.pointerY(event);
	  var elements = this.elementDesc.childNodes;
	  if(elements && elements.length!=0) {
		for (i=0; i<elements.length; i++) {
		    if((elements[i]!=this.element) && pY >= elements[i].offsetTop &&
	             pY <  elements[i].offsetTop + elements[i].offsetHeight && pX >= elements[i].offsetLeft
	             && pX < elements[i].offsetLeft + elements[i].offsetWidth) {
			  if((((elements[i].offsetLeft + elements[i].offsetWidth) - pX) / elements[i].offsetWidth)>0.5) {
			     if(elements[i].previousSibling != this.element) {
			         elements[i].parentNode.insertBefore(this.element, elements[i]);
			     }
			  } else {
			    var nextElement = elements[i].nextSibling;
			    if(nextElement != element) {
			         elements[i].parentNode.insertBefore(this.element, nextElement);
			    }
			  }
		   }
		}
		  if(pY >= this.garbageBin.offsetTop && pY <  this.garbageBin.offsetTop + this.garbageBin.offsetHeight 
	   	   && pX >= this.garbageBin.offsetLeft && pX < this.garbageBin.offsetLeft + this.garbageBin.offsetWidth){
		   	   if(confirm("是否把这张图片从图集中删除?")){
		   	   		this.elementDesc.removeChild(this.element);
		   	   }
		   	   return;
	 		}
	}
   }
}