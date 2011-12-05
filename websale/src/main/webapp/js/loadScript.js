/*================================================================
loadScript.js
================================================================*/
window.onload=function(){
	setTableListClass();
	setTextAreaLength();
	setInputClass();
}
//设置列表悬浮和选中样式
function setTableListClass(){
	var check;
	var tab = document.getElementById("tabls");
	if(tab==null) return;
	var tr=tab.getElementsByTagName("tr");
	for(var i=1;i<tr.length;i++){
		check=tr[i].getElementsByTagName("input")[0];
		if(check&&(check.type=='checkbox'||check.type=='radio')){
			check.fu=tr[i];
			if(check.checked){
				tr[i].style.backgroundColor="#fffbe4";
			}else{
				tr[i].style.backgroundColor="";
				tr[i].onmouseover=function(){
					if(this.style.backgroundColor!="#fffbe4") this.style.backgroundColor="#f3f3f3";
				}
				tr[i].onmouseout=function(){
					if(this.style.backgroundColor!="#fffbe4") this.style.backgroundColor="";
				}
			}
			check.onclick=function(){
				if(this.type=='radio'){
					cleartr=document.getElementById("tabls").getElementsByTagName("tr");
					for(var j=1;j<cleartr.length;j++){
						cleartr[j].style.backgroundColor="";
					}
				}
				if(this.checked){
					this.fu.style.backgroundColor="#fffbe4";
				}else{
					this.fu.style.backgroundColor="";
				}
			}
		}else{
			tr[i].onmouseover=function(){
				if(this.style.backgroundColor!="#fffbe4") this.style.backgroundColor="#f3f3f3";
			}
			tr[i].onmouseout=function(){
				if(this.style.backgroundColor!="#fffbe4") this.style.backgroundColor=""
			}
			
		}
	}
}
//设置TextArea元素的长度限制，必须指定maxlength属性。
function setTextAreaLength(){
	var taobj = document.getElementsByTagName("textarea");
	for(var i=0;i<taobj.length;i++){
		if(typeof(taobj[i].maxlength)!="undefined"){
			taobj[i].onkeyup=function(){if(this.value.length>=this.maxlength) this.value=this.value.substring(0,this.maxlength);}
			taobj[i].onbeforepaste=function(){clipboardData.setData('text',clipboardData.getData('text').substring(0,this.maxlength-this.value.length));}
		}
		taobj[i].style.border="1px solid #8D8D8D";
		taobj[i].style.backgroudcolor="1px solid #8D8D8D";
		taobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
		taobj[i].onmouseout=function(){this.style.borderColor='#84a1bd';}
	}
}
//按INPUT类型，初始化各元素样式
function setInputClass(){	
	var inputobj = document.getElementsByTagName("input");
	for(var i=0;i<inputobj.length;i++){
		if(inputobj[i].type=="button"){
			inputobj[i].style.border="1px solid #aaaaaa";
			inputobj[i].style.background="url('/websale/images/main/button.gif')";
			inputobj[i].style.color="#000000";
			inputobj[i].style.paddingtop="2px";
			inputobj[i].style.cursor="hand";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#aaaaaa';} 
		}else if(inputobj[i].type=="submit"){
			inputobj[i].style.border="1px solid #aaaaaa";
			inputobj[i].style.background="url('/websale/images/main/button.gif')";
			inputobj[i].style.color="#000000";
			inputobj[i].style.paddingtop="2px";
			inputobj[i].style.cursor="hand";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#aaaaaa';} 
		}else if(inputobj[i].type=="reset"){
			inputobj[i].style.border="1px solid #aaaaaa";
			inputobj[i].style.background="url('/websale/images/main/button.gif'))";
			inputobj[i].style.color="#000000";
			inputobj[i].style.paddingtop="2px";
			inputobj[i].style.cursor="hand";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#aaaaaa';} 
		}else if(inputobj[i].type=="image"){
			inputobj[i].style.border="1px solid #aaaaaa";
		}else if(inputobj[i].type=="text"){
			inputobj[i].style.border="1px solid #8D8D8D";
			inputobj[i].style.backgroudcolor="1px solid #8D8D8D";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#84a1bd';}
		}else if(inputobj[i].type=="file"){
			inputobj[i].style.border="1px solid #8D8D8D";
			inputobj[i].style.backgroudcolor="1px solid #8D8D8D";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#84a1bd';}
		}else if(inputobj[i].type=="password"){
			inputobj[i].style.border="1px solid #8D8D8D";
			inputobj[i].style.backgroudcolor="1px solid #8D8D8D";
			inputobj[i].onmouseover=function(){this.style.borderColor='#00ff00';}
			inputobj[i].onmouseout=function(){this.style.borderColor='#84a1bd';}
		}else if(inputobj[i].type=="radio"){
			
		}else if(inputobj[i].type=="checkbox"){
			
		}
	}
}
/**
1、用正则表达式限制只能输入中文：onkeyup="value="/value.replace(/\["^u4E00-u9FA5\]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\[^u4E00-u9FA5\]/g,''))"

2、用正则表达式限制只能输入全角字符： onkeyup="value="/value.replace(/\["^uFF00-uFFFF\]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\[^uFF00-uFFFF\]/g,''))"

3、用正则表达式限制只能输入数字：onkeyup="value="/value.replace(/\["^d\]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\[^d\]/g,''))"

4、用正则表达式限制只能输入数字和英文：onkeyup="value="/value.replace(/\[W\]/g,"'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace
*/