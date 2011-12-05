/** 
* ȡ���ַ������ֽڳ��� 
*/ 


����
function strlen(str)   
{   
    var i;   
    var len;   
       
    len = 0;   
    for (i=0;i<str.length;i++)   
    {   
        if (str.charCodeAt(i)>255) len+=2; else len++;   
    }   
    return len;   
}   
   


/* 
* �ж��Ƿ�Ϊ���֣����򷵻�true,���򷵻�false 
*/ 

����
function f_check_number(obj)   
{          
    if (/^\d+$/.test(obj.value))   
    {   
       return true;   
    }    
    else    
    {   
       f_alert(obj,"����������");   
       return false;   
    }   
}   
   


/* 
* �ж��Ƿ�Ϊ��Ȼ�������򷵻�true,���򷵻�false 
*/ 

����
function f_check_naturalnumber(obj)   
{          
    var s = obj.value;   
    if (/^[0-9]+$/.test( s ) && (s > 0))   
    {   
       return true;   
    }    
    else    
    {   
        f_alert(obj,"��������Ȼ��");   
        return false;   
    }   
}   
   


/* 
* �ж��Ƿ�Ϊ���������򷵻�true,���򷵻�false 
*/ 

����
function f_check_integer(obj)   
{          
    if (/^(\+|-)?\d+$/.test( obj.value ))    
    {   
       return true;   
    }    
    else    
    {   
        f_alert(obj,"����������");   
        return false;   
    }   
}   
   


/* 
* �ж��Ƿ�Ϊʵ�������򷵻�true,���򷵻�false 
*/ 

����
function f_check_float(obj)   
{          
    if (/^(\+|-)?\d+($|\.\d+$)/.test( obj.value ))    
    {   
       return true;   
    }    
    else    
    {   
        f_alert(obj,"������ʵ��");   
       return false;   
    }   
}   
   


/* 
* У�����ֵĳ��Ⱥ;��� 
*/ 

����
function f_check_double(obj){   
    var numReg;   
    var value = obj.value;   
    var strValueTemp, strInt, strDec;      
    var dtype = obj.eos_datatype;   
    var pos_dtype = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")")).split(",");   
    var len = pos_dtype[0], prec = pos_dtype[1];   
    try  
    {          
        numReg =/[\-]/;   
        strValueTemp = value.replace(numReg, "");   
        numReg =/[\+]/;   
        strValueTemp = strValueTemp.replace(numReg, "");   
        //����   
        if(prec==0){   
            numReg =/[\.]/;   
            if(numReg.test(value) == true){   
                f_alert(obj, "�������Ϊ��������");   
                return false;      
            }              
        }          
        if(strValueTemp.indexOf(".") < 0 ){   
            if(strValueTemp.length >( len - prec)){   
                f_alert(obj, "����λ���ܳ���"+ (len - prec) +"λ");   
                return false;   
            }          
        }else{   
            strInt = strValueTemp.substr( 0, strValueTemp.indexOf(".") );          
            if(strInt.length >( len - prec)){   
                f_alert(obj, "����λ���ܳ���"+ (len - prec) +"λ");   
                return false;   
            }   
            strDec = strValueTemp.substr( (strValueTemp.indexOf(".")+1), strValueTemp.length );    
            if(strDec.length > prec){   
                f_alert(obj, "С��λ���ܳ���"+  prec +"λ");   
                return false;   
            }          
        }          
        return true;   
    }catch(e){   
        alert("in f_check_double = " + e);   
        return false;   
    }      
}   
   


/* 
* У�����ֵ���С���ֵ 
* ����bool 
*/ 

����
function f_check_interval(obj)   
{   
    var value = parseFloat(obj.value);   
  
    var dtype = obj.eos_datatype;   
    var pos_dtype = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")")).split(",");   
       
    var minLimit = pos_dtype[0];   
    var maxLimit = pos_dtype[1];   
    var minVal = parseFloat(pos_dtype[0]);   
    var maxVal = parseFloat(pos_dtype[1]);    
       
    if(isNaN(value))   
    {   
        f_alert(obj, "ֵ����Ϊ����");   
        return false;   
    }   
    if((isNaN(minVal) && (minLimit != "-")) || (isNaN(maxVal) && (maxLimit != "+")))   
    {   
        f_alert(obj, "�߽�ֵ����Ϊ���ֻ�-��+");   
        return false;   
    }   
  
    if(minLimit == "-" && !isNaN(maxVal))   
    {   
        if(value > maxVal)   
        {   
            f_alert(obj, "ֵ���ܳ���" + maxVal);   
            return false;   
        }   
    }   
       
    if(!isNaN(minVal) && maxLimit == "+")   
    {          
        if(value < minVal)   
        {   
            f_alert(obj, "ֵ����С��" + minVal);   
            return false;   
        }   
    }   
       
    if(!isNaN(minVal) && !isNaN(maxVal))   
    {   
        if(minVal > maxVal)   
        {   
            f_alert(obj, "��ʼֵ" + minVal + "���ܴ�����ֵֹ" + maxVal);   
        }else  
        {   
            if(!(value <= maxVal && value >= minVal))   
            {   
                f_alert(obj, "ֵӦ����" + minVal + "��" + maxVal + "֮��");   
                return false;   
            }   
        }   
    }   
    return true;   
}   
   


/* 
��;����������ַ����Ƿ�ֻ�ɺ������ 
���ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_zh(obj){   
    if (/^[\u4e00-\u9fa5]+$/.test(obj.value)) {   
      return true;   
    }   
    f_alert(obj,"�����뺺��");   
    return false;   
}   
   


/* 
* �ж��Ƿ�ΪСдӢ����ĸ�����򷵻�true,���򷵻�false 
*/ 

����
function f_check_lowercase(obj)   
{          
    if (/^[a-z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"������СдӢ����ĸ");   
    return false;   
}   
   


/* 
* �ж��Ƿ�Ϊ��дӢ����ĸ�����򷵻�true,���򷵻�false 
*/ 

����
function f_check_uppercase(obj)   
{          
    if (/^[A-Z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"�������дӢ����ĸ");   
    return false;   
}   
   


/* 
* �ж��Ƿ�ΪӢ����ĸ�����򷵻�true,���򷵻�false 
*/ 

����
function f_check_letter(obj)   
{          
    if (/^[A-Za-z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"������Ӣ����ĸ");   
    return false;   
}   
   


/* 
��;����������ַ����Ƿ�ֻ�ɺ��֡���ĸ��������� 
���룺 
value���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_ZhOrNumOrLett(obj){    //�ж��Ƿ��Ǻ��֡���ĸ���������   
    var regu = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";      
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"�����뺺�֡���ĸ������");   
    return false;   
}   
   


/* 
��;��У��ip��ַ�ĸ�ʽ 
���룺strIP��ip��ַ 
���أ����ͨ����֤����true,���򷵻�false�� 
*/ 

����
function f_check_IP(obj)    
{    
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; //ƥ��IP��ַ��������ʽ   
    if(re.test( obj.value ))   
    {   
        if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;   
    }   
    f_alert(obj,"������Ϸ��ļ����IP��ַ");   
    return false;    
}   
   


/* 
��;�������������ֵ�Ƿ���϶˿ںŸ�ʽ 
���룺str ������ַ��� 
���أ����ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_port(obj)   
{   
    if(!f_check_number(obj))   
        return false;   
    if(obj.value < 65536)   
        return true;   
    f_alert(obj,"������Ϸ��ļ����IP��ַ�˿ں�");   
    return false;    
}   
   


/* 
��;�������������ֵ�Ƿ������ַ��ʽ 
���룺str ������ַ��� 
���أ����ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_URL(obj){     
    var myReg = /^((http:[/][/])?\w+([.]\w+|[/]\w*)*)?$/;    
    if(myReg.test( obj.value )) return true;    
    f_alert(obj,"������Ϸ�����ҳ��ַ");   
    return false;    
}   
   


/* 
��;�������������ֵ�Ƿ����E-Mail��ʽ 
���룺str ������ַ��� 
���أ����ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_email(obj){     
    var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;    
    if(myReg.test( obj.value )) return true;    
    f_alert(obj,"������Ϸ��ĵ����ʼ���ַ");   
    return false;    
}   
   


/* 
Ҫ��һ���ƶ��绰����Ϊ11��12λ�����Ϊ12λ,��ô��һλΪ0 
����11λ�ƶ��绰����ĵ�һλ�͵ڶ�λΪ"13" 
����12λ�ƶ��绰����ĵڶ�λ�͵���λΪ"13" 
��;����������ֻ������Ƿ���ȷ 
���룺 
s���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_mobile(obj){      
    var regu =/(^[1][3][0-9]{9}$)|(^0[1][3][0-9]{9}$)/;   
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"��������ȷ���ֻ�����");   
    return false;      
}   
   


/* 
Ҫ��һ���绰���������֡�"("��")"��"-"���� 
�����绰����Ϊ3��8λ 
��������绰�����а��������ţ���ô����Ϊ��λ����λ 
�ġ�������"("��")"��"-"���������ָ��� 
��;���������ĵ绰�����ʽ�Ƿ���ȷ 
���룺 
strPhone���ַ��� 
���أ� 
���ͨ����֤����true,���򷵻�false 
*/ 

����
function f_check_phone(obj)    
{   
    var regu =/(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/;    
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"��������ȷ�ĵ绰����");   
    return false;   
}   
   


/* �ж��Ƿ�Ϊ�������� */ 

����
function f_check_zipcode(obj)   
{   
    if(!f_check_number(obj))   
        return false;   
    if(obj.value.length!=6)   
    {   
        f_alert(obj,"�������볤�ȱ�����6λ");   
        return false;   
    }   
    return true;   
}   
   


/* 
�û�ID������Ϊ���֡���ĸ���»��ߵ���ϣ� 
��һ���ַ�����Ϊ����,���ܳ��Ȳ��ܳ���20�� 
*/ 

����
function f_check_userID(obj)   
{   
    var userID = obj.value;   
    if(userID.length > 20)   
    {   
        f_alert(obj,"ID���Ȳ��ܴ���20");   
        return false;   
    }   
  
    if(!isNaN(userID.charAt(0)))   
    {   
        f_alert(obj,"ID��һ���ַ�����Ϊ����");   
        return false;   
    }   
    if(!/^\w{1,20}$/.test(userID))    
    {   
        f_alert(obj,"IDֻ�������֡���ĸ���»�����϶���");   
        return false;   
    }   
    return true;   
}   
   


/* 
���ܣ���֤���֤�����Ƿ���Ч 
��ʾ��Ϣ��δ������������֤�Ų���ȷ�� 
ʹ�ã�f_check_IDno(obj) 
���أ�bool 
*/ 

����
function f_check_IDno(obj)   
{    
    var aCity={11:"����",12:"���",13:"�ӱ�",14:"ɽ��",15:"���ɹ�",21:"����",22:"����",23:"������",31:"�Ϻ�",32:"����",33:"�㽭",34:"����",35:"����",36:"����",37:"ɽ��",41:"����",42:"����",43:"����",44:"�㶫",45:"����",46:"����",50:"����",51:"�Ĵ�",52:"����",53:"����",54:"����",61:"����",62:"����",63:"�ຣ",64:"����",65:"�½�",71:"̨��",81:"���",82:"����",91:"����"};   
    
    var iSum = 0;   
    var info = "";   
    var strIDno = obj.value;   
    var idCardLength = strIDno.length;     
    if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))    
    {   
        f_alert(obj,"�Ƿ����֤��");   
        return false;   
    }   
    
    //�ں����������x�൱������10,����ת����a   
    strIDno = strIDno.replace(/x$/i,"a");   
  
    if(aCity[parseInt(strIDno.substr(0,2))]==null)   
    {   
        f_alert(obj,"�Ƿ�����");   
        return false;   
    }   
       
    if (idCardLength==18)   
    {   
        sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));   
        var d = new Date(sBirthday.replace(/-/g,"/"))   
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))   
        {          
            f_alert(obj,"�Ƿ�����");   
            return false;   
        }   
  
        for(var i = 17;i>=0;i --)   
            iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);   
  
        if(iSum%11!=1)   
        {   
            f_alert(obj,"�Ƿ����֤��");   
            return false;   
        }   
    }   
    else if (idCardLength==15)   
    {   
        sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));   
        var d = new Date(sBirthday.replace(/-/g,"/"))   
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();      
        if(sBirthday != dd)   
        {   
            f_alert(obj,"�Ƿ�����");   
            return false;   
        }   
    }   
    return true;    
}   
   


/* 
* �ж��ַ����Ƿ����ָ����������ʽ 
*/ 

����
function f_check_formatStr(obj)   
{   
    var str = obj.value;   
    var dtype = obj.eos_datatype;   
    var regu = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));    //ָ����������ʽ   
    var re = new RegExp(regu);   
    if(re.test(str))   
        return true;   
    f_alert(obj , "������ָ����������ʽҪ��");   
    return false;      
}   
   


/* 
���ܣ��ж��Ƿ�Ϊ����(��ʽ:yyyy��MM��dd��,yyyy-MM-dd,yyyy/MM/dd,yyyyMMdd) 
��ʾ��Ϣ��δ�������������ڸ�ʽ���� 
ʹ�ã�f_check_date(obj) 
���أ�bool 
*/ 

����
function f_check_date(obj)   
{   
    var date = Trim(obj.value);   
    var dtype = obj.eos_datatype;   
    var format = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));  //���ڸ�ʽ   
    var year,month,day,datePat,matchArray;   
  
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2})$/.test(format))   
        datePat = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/;   
    else if(/^(y{4})(��)(M{1,2})(��)(d{1,2})(��)$/.test(format))   
        datePat = /^(\d{4})��(\d{1,2})��(\d{1,2})��$/;   
    else if(format=="yyyyMMdd")   
        datePat = /^(\d{4})(\d{2})(\d{2})$/;   
    else  
    {   
        f_alert(obj,"���ڸ�ʽ����");   
        return false;   
    }   
    matchArray = date.match(datePat);   
    if(matchArray == null)    
    {   
        f_alert(obj,"���ڳ��Ȳ���,���������з����ַ���");   
        return false;   
    }   
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2})$/.test(format))   
    {   
        year = matchArray[1];   
        month = matchArray[3];   
        day = matchArray[4];   
    } else  
    {   
        year = matchArray[1];   
        month = matchArray[2];   
        day = matchArray[3];   
    }   
    if (month < 1 || month > 12)   
    {                
        f_alert(obj,"�·�Ӧ��Ϊ1��12������");   
        return false;   
    }   
    if (day < 1 || day > 31)   
    {   
        f_alert(obj,"ÿ���µ�����Ӧ��Ϊ1��31������");   
        return false;   
    }        
    if ((month==4 || month==6 || month==9 || month==11) && day==31)   
    {   
        f_alert(obj,"���²�����31��");   
        return false;   
    }        
    if (month==2)   
    {   
        var isleap=(year % 4==0 && (year % 100 !=0 || year % 400==0));   
        if (day>29)   
        {                  
            f_alert(obj,"2�������29��");   
            return false;   
        }   
        if ((day==29) && (!isleap))   
        {                  
            f_alert(obj,"����2�²���29��");   
            return false;   
        }   
    }   
    return true;   
}   
   


/* 
���ܣ�У��ĸ�ʽΪyyyy��MM��dd��HHʱmm��ss��,yyyy-MM-dd HH:mm:ss,yyyy/MM/dd HH:mm:ss,yyyyMMddHHmmss 
��ʾ��Ϣ��δ����������ʱ���ʽ���� 
ʹ�ã�f_check_time(obj) 
���أ�bool 
*/ 

����
function f_check_time(obj)   
{   
    var time = Trim(obj.value);   
    var dtype = obj.eos_datatype;   
    var format = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));  //���ڸ�ʽ   
    var datePat,matchArray,year,month,day,hour,minute,second;   
  
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2}) (HH:mm:ss)$/.test(format))   
        datePat = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;   
    else if(/^(y{4})(��)(M{1,2})(��)(d{1,2})(��)(HHʱmm��ss��)$/.test(format))   
        datePat = /^(\d{4})��(\d{1,2})��(\d{1,2})��(\d{1,2})ʱ(\d{1,2})��(\d{1,2})��$/;   
    else if(format == "yyyyMMddHHmmss")   
        datePat = /^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/;   
    else  
    {   
        f_alert(obj,"���ڸ�ʽ����");   
        return false;   
    }   
    matchArray = time.match(datePat);   
    if(matchArray == null)    
    {   
        f_alert(obj,"���ڳ��Ȳ���,���������з����ַ���");   
        return false;   
    }   
    if(/^(y{4})(-|\/)(M{1,2})\2(d{1,2}) (HH:mm:ss)$/.test(format))   
    {   
        year = matchArray[1];   
        month = matchArray[3];   
        day = matchArray[4];   
        hour = matchArray[5];   
        minute = matchArray[6];   
        second = matchArray[7];   
    } else  
    {   
        year = matchArray[1];   
        month = matchArray[2];   
        day = matchArray[3];   
        hour = matchArray[4];   
        minute = matchArray[5];   
        second = matchArray[6];   
    }   
    if (month < 1 || month > 12)   
    {                
        f_alert(obj,"�·�Ӧ��Ϊ1��12������");   
        return false;   
    }   
    if (day < 1 || day > 31)   
    {              
        f_alert(obj,"ÿ���µ�����Ӧ��Ϊ1��31������");   
        return false;   
    }        
    if ((month==4 || month==6 || month==9 || month==11) && day==31)   
    {            
        f_alert(obj,"���²�����31��");   
        return false;   
    }        
    if (month==2)   
    {   
        var isleap=(year % 4==0 && (year % 100 !=0 || year % 400==0));   
        if (day>29)   
        {                  
            f_alert(obj,"2�������29��");   
            return false;   
        }   
        if ((day==29) && (!isleap))   
        {                  
            f_alert(obj,"����2�²���29��");   
            return false;   
        }   
    }   
    if(hour<0 || hour>23)   
    {   
        f_alert(obj,"СʱӦ����0��23������");   
        return false;   
    }   
    if(minute<0 || minute>59)   
    {   
        f_alert(obj,"��Ӧ����0��59������");   
        return false;   
    }   
    if(second<0 || second>59)   
    {   
        f_alert(obj,"��Ӧ����0��59������");   
        return false;   
    }   
    return true;   
}   
   


/*�жϵ�ǰ�����Ƿ�ɼ�*/ 

����
function isVisible(obj){   
    var visAtt,disAtt;   
    try{   
        disAtt=obj.style.display;   
        visAtt=obj.style.visibility;   
    }catch(e){}   
    if(disAtt=="none" || visAtt=="hidden")   
        return false;   
    return true;   
}   
   


/*�жϵ�ǰ�����丸�����Ƿ�ɼ�*/ 

����
function checkPrVis(obj){   
    var pr=obj.parentNode;   
    do{   
        if(pr == undefined || pr == "undefined") return true;   
        else{   
            if(!isVisible(pr)) return false;   
        }   
    }while(pr=pr.parentNode);   
    return true;   
}   
   


/* ��������Ի����û���ȷ���󽫹�����ڳ����ı����ϣ� ���ҽ�ԭ����������ѡ�С�*/ 

����
function f_alert(obj,alertInfo)   
{   
    var caption = obj.getAttribute("eos_displayname");   
    if(caption == null)   
        caption = "";   
    alert(caption + "��" + alertInfo + "��");    
    obj.select();   
    if(isVisible(obj) && checkPrVis(obj))   
        obj.focus();   
}   
   


/** 
* ����ַ����Ƿ�Ϊ�� 
*/ 

����
function isnull(str)   
{   
    var i;   
    if(str.length == 0)   
        return true;   
    for (i=0;i<str.length;i++)   
    {   
        if (str.charAt(i)!=' ')    
            return false;   
    }   
    return true;   
}   
   


/** 
* ���ָ���ı��������Ƿ�Ϸ��� 
* ����û�����������д��򵯳���ʾ�Ի��� 
* ͬʱ���������ڸ��ı����ϣ����Ҹ��ı���ǰ�� 
* �����һ������ͼ��(������ȷ����Զ�ȥ��)�� 
*/ 

����
function checkInput(object)   
{   
    var image;   
    var i;   
    var length;   
  
    if(object.eos_maxsize + "" != "undefined") length = object.eos_maxsize;   
    else length = 0;   
  
    if (object.eos_isnull=="true" && isnull(object.value))  return true;   
  
    /* ����У�� */  
    if(length != 0 && strlen(object.value) > parseInt(length)) {   
            f_alert(object, "������󳤶�" + length);   
            return false;   
    }    
    /* ��������У�� */  
    else {   
        if (object.eos_datatype + "" != "undefined")   
        {          
  
            var dtype = object.eos_datatype;   
            var objName = object.name;   
            //�������������������ţ���������ǰ����ַ���ΪУ������   
            if(dtype.indexOf("(") != -1)   
                dtype = dtype.substring(0,dtype.indexOf("("));   
            //����ҳ��Ԫ�ص�У�����ͽ���У��   
            try{   
                if(eval("f_check_" + dtype + "(object)") != true)   
                    return false;   
            }catch(e){return true;}   
            /*  ���form�д���nameǰ�벿����ͬ������ͬʱ������"min"��"max"��β�ı���  
                ��ô��Ϊ�������ѯ����"min"��β�ı����ֵҪС�ڵ���"max"��β�ı����ֵ�� */  
            if(objName.substring((objName.length-3),objName.length)=="min")   
            {   
                var objMaxName = objName.substring(0, (objName.length-3)) + "max";   
                if(document.getElementById(objMaxName) != undefined && document.getElementById(objMaxName) != "undefined" )   
                {   
                    if(checkIntervalObjs(object, document.getElementById(objMaxName)) != true)   
                        return false;                      
                }   
            }              
        }   
    }   
    return true;   
}   
   


/* �������������������ȷ�ԣ�һ�����ڱ���onsubmit�¼� */ 

����
function checkForm(myform)   
{   
    var i;   
    for (i=0;i<myform.elements.length;i++)   
    {   
        /* ���Զ������Ե�Ԫ�ز������ */        
        if (myform.elements[i].eos_displayname + "" == "undefined") continue;   
        /* �ǿ�У�� */  
        if (myform.elements[i].eos_isnull=="false" && isnull(myform.elements[i].value)){   
            f_alert(myform.elements[i],"����Ϊ��");   
            return false;   
        }          
        /* ��������У�� */  
        if (checkInput(myform.elements[i])==false)   
            return false;                  
    }   
    return true;   
}   
   


/** 
* У�������������ݵĴ�С��Ŀǰֻ����Ƚ����ں����֡� 
* @param obj1 Сֵ���� 
* @param obj2 ��ֵ���� 
*/ 

����
function checkIntervalObjs(obj1 , obj2)   
{      
    var caption1 = obj1.getAttribute("eos_displayname");   
    var caption2 = obj2.getAttribute("eos_displayname");   
    var val1 = parseFloat(obj1.value);   
    var val2 = parseFloat(obj2.value);   
    // ���Զ������Ե�Ԫ�ز������   
    if (obj1.eos_displayname + "" == "undefined" || obj2.eos_displayname + "" == "undefined") {   
        return false;   
    }   
    // �������͵ıȽ�   
    if(f_check_date(obj1) == true && f_check_date(obj2) == true){   
        var dtype = obj1.eos_datatype;   
        var format = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));  //���ڸ�ʽ   
        val1 = getDateByFormat(obj1.value, format);   
        dtype = obj2.eos_datatype;   
        format = dtype.substring(dtype.indexOf("(")+1,dtype.indexOf(")"));  //���ڸ�ʽ   
        val2 = getDateByFormat(obj2.value, format);   
        if(val1 > val2){   
        obj2.select();   
        if(isVisible(obj) && checkPrVis(obj))   
            obj2.focus();   
        alert(caption1 + "����ʼ���ڲ��ܴ�������ֹ���ڣ�");   
        return false;   
        }   
    }   
    // �������͵ıȽ�   
    if((isNaN(val1) && !isnull(val1)) || (isNaN(val2) && !isnull(val2))){   
        alert(caption1 + "��ֵ��ȫΪ�������ܱȽϣ�");   
        return false;   
    }   
    if(val1 > val2){   
        obj2.select();   
        if(isVisible(obj) && checkPrVis(obj))   
            obj2.focus();   
        alert(caption1 + "����ʼֵ���ܴ�������ֵֹ��");   
        return false;   
    }   
    return true;   
}   
   


/*�������ڸ�ʽ�����ַ���ת����Date���� 
��ʽ��yyyy-�꣬MM-�£�dd-�գ�HH-ʱ��mm-�֣�ss-�롣 
����ʽ����дȫ������:yy-M-d���ǲ�����ģ����򷵻�null����ʽ��ʵ�����ݲ���Ҳ����null���� 
Ĭ�ϸ�ʽ��yyyy-MM-dd HH:mm:ss,yyyy-MM-dd��*/ 


����
function getDateByFormat(str){   
    var dateReg,format;   
    var y,M,d,H,m,s,yi,Mi,di,Hi,mi,si;   
    if((arguments[1] + "") == "undefined") format = "yyyy-MM-dd HH:mm:ss";   
    else format = arguments[1];   
    yi = format.indexOf("yyyy");   
    Mi = format.indexOf("MM");   
    di = format.indexOf("dd");   
    Hi = format.indexOf("HH");   
    mi = format.indexOf("mm");   
    si = format.indexOf("ss");   
    if(yi == -1 || Mi == -1 || di == -1) return null;   
    else{   
        y = parseInt(str.substring(yi, yi+4));   
        M = parseInt(str.substring(Mi, Mi+2));   
        d = parseInt(str.substring(di, di+2));   
    }   
    if(isNaN(y) || isNaN(M) || isNaN(d)) return null;   
    if(Hi == -1 || mi == -1 || si == -1) return new Date(y, M-1, d);   
    else{   
        H = str.substring(Hi, Hi+4);   
        m = str.substring(mi, mi+2);   
        s = str.substring(si, si+2);   
    }   
    if(isNaN(parseInt(y)) || isNaN(parseInt(M)) || isNaN(parseInt(d))) return new Date(y, M-1, d);   
    else return new Date(y, M-1, d,H, m, s);   
}   
   


/*LTrim(string):ȥ����ߵĿո�*/ 

����
function LTrim(str){   
    var whitespace = new String(" \t\n\r");   
    var s = new String(str);      
  
    if (whitespace.indexOf(s.charAt(0)) != -1){   
        var j=0, i = s.length;   
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1){   
            j++;   
        }   
        s = s.substring(j, i);   
    }   
    return s;   
}   
   


/*RTrim(string):ȥ���ұߵĿո�*/ 

����
function RTrim(str){   
    var whitespace = new String(" \t\n\r");   
    var s = new String(str);   
    
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1){   
        var i = s.length - 1;   
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1){   
            i--;   
        }   
        s = s.substring(0, i+1);   
    }   
    return s;   
}   
   


/*Trim(string):ȥ���ַ������ߵĿո�*/ 

����
function Trim(str){   
    return RTrim(LTrim(str));   
}    