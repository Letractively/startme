Function.prototype.bindAsEventListener = function(object) {
  var __method = this;
  return function(event) {
    __method.call(object, event);
  }
}

Event = {
  isLeftClick: function(event) {
    //在ie中event.button; 在firefox中event.which
    return (((event.which) && (event.which == 1)) ||
            ((event.button) && (event.button == 1)));
  },//ie不承认pageX和pageY属性
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
      event.preventDefault();//在fireFox中一定要
      event.stopPropagation();
    } else {
      event.returnValue = false;//在ie中一定要
    }
  },
  observe: function(element, name, observer) {
    if(element.addEventListener) {
      element.addEventListener(name, observer,false);//"false"在fireFox中一定要
    } else if (element.attachEvent) {
      element.attachEvent('on'+name, observer);
    }
  }
};