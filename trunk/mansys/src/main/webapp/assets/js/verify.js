/*
* 增加String的原型化方法，去除字符串两边的空格。
*/
String.prototype.trim = function(){ var r = /^\s+|\s+$/g;return this.replace(r,'');};
/* 
* 判断是否为自然数，是返回true,否返回false 
*/ 
function f_check_naturalnumber(obj)   
{          
    var s = obj.value;   
    if (/^[0-9]+$/.test( s ) && (s > 0))   
    {   
       return true;   
    }    
    else    
    {    
        return false;   
    }   
}  

/* 
* 判断是否为英文字母或数字，是则返回true,否则返回false 
*/ 

function f_check_letter_num(obj)   
{          
    if (/^[A-Za-z0-9][\w_]+$/.test( obj.value ))    
    {   
       return true;   
    }     
    return false;   
}  