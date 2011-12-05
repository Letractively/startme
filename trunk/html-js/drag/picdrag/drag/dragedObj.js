Draggable = function() {
      this.init.apply(this, arguments);
}
Draggable.prototype = {
  init: function(element, elements) {
    this.element      = element;
    this.elements     = elements;
    this.offsetX      = 0;
    this.offsetY      = 0;
    this.originalLeft = this.currentLeft();
    this.originalTop  = this.currentTop();
    this.originalX    = element.offsetLeft;
    this.originalY    = element.offsetTop;
    this.originalZ    = parseInt(this.element.style.zIndex || "0");
    this.active       = false;
    this.dragging     = false;
    /*event 加入firefox中的各控件事件里;
      ie可以不用(event在ie中的各控件事件里是默认的存在)*/
    Event.observe(this.element, "mousedown", this.onMouseDown.bindAsEventListener(this));
    Event.observe(document, "mouseup", this.onMouseUp.bindAsEventListener(this));
    Event.observe(document, "mousemove", this.onMouseMove.bindAsEventListener(this));
  },
  currentLeft: function() {
    return parseInt(this.element.style.left || '0');
  },
  currentTop: function() {
    return parseInt(this.element.style.top || '0')
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
  onMouseUp: function() {
    if(this.active && this.dragging) {
      //移动在外的对象自动返回空白处
      this.element.style.top = -this.originalTop + "px";;
      this.element.style.left = -this.originalLeft + "px";
      this.element.style.zIndex = this.originalZ;
    }
    this.active = false;
    this.dragging = false;
  },
  onMouseMove: function(event) {
   if(this.active) {
      if(!this.dragging) {
        var style = this.element.style;
        this.dragging = true;
        style.zIndex = 10;
      }
      this.fillMoveBlank(event, this.element);
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
	  
	  var i = this.elements.length-1;
	  do {
	    if((this.elements[i]!=element) && pY >= this.elements[i].offsetTop &&
             pY <  this.elements[i].offsetTop + this.elements[i].offsetHeight && pX >= this.elements[i].offsetLeft
             && pX < this.elements[i].offsetLeft + this.elements[i].offsetWidth) {
		  if((((this.elements[i].offsetLeft + this.elements[i].offsetWidth) - pX) / this.elements[i].offsetWidth)>0.5) {
		     if(this.elements[i].previousSibling != element) {
		         this.elements[i].parentNode.insertBefore(element, this.elements[i]);
		     }
		  } else {
		    var nextElement = this.elements[i].nextSibling;
		    if(nextElement != element) {
		         this.elements[i].parentNode.insertBefore(element, nextElement);
		    }
		  }
	   }
	} while (i--);
  }
}