/*
* ����String��ԭ�ͻ�������ȥ���ַ������ߵĿո�
*/
String.prototype.trim = function(){ var r = /^\s+|\s+$/g;return this.replace(r,'');};
/* 
* �ж��Ƿ�Ϊ��Ȼ�����Ƿ���true,�񷵻�false 
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
* �ж��Ƿ�ΪӢ����ĸ�����֣����򷵻�true,���򷵻�false 
*/ 

function f_check_letter_num(obj)   
{          
    if (/^[A-Za-z0-9][\w_]+$/.test( obj.value ))    
    {   
       return true;   
    }     
    return false;   
}  