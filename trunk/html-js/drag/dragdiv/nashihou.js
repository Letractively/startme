/**��ȡ�����е���ֵ*/
function stripPx(value){
 if(value == "") return 0;
 return parseFloat(value.substring(0,value.length - 2));
}

/**��ק��*/
function Drag(tagName,className){
 /**�ֶ�*/
  this.tagName=tagName;
  this.className=className;
  var dragDiv;
  var dragging = false;
  var _X;//��굽��ǰ�϶���������ߵľ���
  var _Y;//��굽��ǰ�϶��������ϱߵľ���
  
  /**����*/
  this.startMove;
  this.stopMove;
  this.processMove;
  
  /**��ʼ*/
  this.startMove = function (event,id){
    if(!event)event=window.event;//IE����
   
    //������classΪclassName��tagName����Ϊstyle.zIndex=0 Ŀ���ǽ���ЩtagName���ڵײ�
    var elements = document.getElementsByTagName(this.tagName);
    for(var i=0;i<elements.length;i++){
      if(elements[i].className==this.className){
        elements[i].style.zIndex=0;
      } 
    }
    
    dragDiv = document.getElementById(id);//��ǰ�϶�����
    dragDiv.style.zIndex = 1;//����div����ǰ��
    
    //��ʼ������ڵ�ǰ�϶���������λ��
    _X = event.clientX- stripPx(dragDiv.style.left);
    _Y = event.clientY- stripPx(dragDiv.style.top);
    
    //�ڴ˶����ϵļ����¼�  firefoxʱֻ�ڴ˶�����Ч
    //Drag.dragDiv.onmousemove = this.processMove;
    //Drag.dragDiv.onmouseup = this.stopMove;
    
    //��window�����ϵļ����¼�  IE/firefoxʱ�ڴ�����Ч
    window.document.onmousemove = this.processMove;
    window.document.onmouseup = this.stopMove;
    
    dragging = true;
  }
  
  /**ֹͣ*/
  this.stopMove = function (){
    dragging = false;
  }
  
  /**�ƶ�����*/
  this.processMove = function (event){
    if(dragging){
     if(!event)event = window.event;//IE����
     var y = event.clientY; 
     var x = event.clientX; 
     
       //firefox�������px
     dragDiv.style.top = (y - _Y)+"px";
     dragDiv.style.left = (x - _X)+"px";
    } 
  }
}
