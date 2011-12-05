Function.prototype.bindAsEventListener = function(object) {
  var __method = this;
  return function(event) {
    __method.call(object, event);
  }
}

Event = {
  isLeftClick: function(event) {
    //��ie��event.button; ��firefox��event.which
    return (((event.which) && (event.which == 1)) ||
            ((event.button) && (event.button == 1)));
  },//ie������pageX��pageY����
  pointerX: function(event) {
    return event.pageX || (event.clientX + 
      (document.documentElement.scrollLeft || document.body.scrollLeft));
  },
  pointerY: function(event) {
    return event.pageY || (event.clientY + 
      (document.documentElement.scrollTop || document.body.scrollTop));
  },
  stop: function(event) {
    if (event.preventDefault) { 
      event.preventDefault();//��fireFox��һ��Ҫ
      event.stopPropagation();
    } else {
      event.returnValue = false;//��ie��һ��Ҫ
    }
  },
  observe: function(element, name, observer) {
    if(element.addEventListener) {
      element.addEventListener(name, observer,false);//"false"��fireFox��һ��Ҫ
    } else if (element.attachEvent) {
      element.attachEvent('on'+name, observer);
    }
  }
};