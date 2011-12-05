/**
 * JavaScript 检查库
 * @author Jarry.li
 * @version 2.01
 * @last modify time 2003/09/11
 * #@ validate.js
 * 第一类 检查并返回检查结果(true or false)
 * a-1. ifDigit(str,allowNull)  是否为合法非负整数(examples/ifDigit.html)
 * a-2. ifLetter(str,allowNull) 是否为英文字母
 * a-3. ifExist(arr,element)    某个元素是否存在于某个数组中
 * a-4. ifDay(str,allowNull)    是否为天数
 * a-5. ifMonth(str,allowNull)  是否为月份
 * a-6. ifYear(str,allowNull)   是否为年份
 * a-7. ifYearInRange(str,min_year,max_year,allowNull)  是否为指定范围内的合法年份
 * a-8. ifDate(String,allowNull)   是否为日期
 * a-9. ifEmail(String,allowNull)  是否为邮件地址
 * a-10. ifPhone(String,allowNull)  是否为电话号码
 * a-11. ifGBK(String,allowNull)    是否包含中文字符
 * a-12.ifMoney(String,allowNull)  是否为合法货币数字
 * a-13 ifMoneyInRange(String,int,int,allowNull) 判断字符串是否为合法钱数,且是否超过限定范围
 * a-14. ifAvildDate(year,month,day) 判断是否为合法日期
 * a-15. ifSelectedButton(buttonGroup) 判断buttongroup为名的一组radio中有无被选中的项

 * 第二类 检查后直接报错
 * b-1. isDigit(Object,allowNull)  是否为数字
 * b-2. isDigitInMaxLen(object,maxlength,allowNull) 是否为非负整数，且长度在指定范围内
 * b-3. isDigitInRange(object,minValue,maxValue,allowNull) 是否为非负整数，且大小在指定范围内
 * b-4. isLetter(Object,allowNull) 是否为字母
 * b-5. isDay(Object,allowNull)    是否为天数
 * b-6. isMonth(Object,allowNull)  是否为月份
 * b-7. isYear(Object,allowNull)   是否为年份
 * b-8. isYearInRange(object,min_year,max_year,allowNull) 是否为指定范围内的合法年份
 * b-9. isDate(Object,allowNull)   是否为日期
 * b-10. isEmail(Object,allowNull)  是否为邮件地址
 * b-11. isPhone(Object,allowNull)  是否为电话号码
 * b-12. isGBK(Object,allowNull)    是否为中文字符
 * b-13. isMoney(Object,allowNull)  是否为合法货币数字
 * b-14. isMoneyInRange(object,minValue,maxValue,allowNull) 判断是否合法钱数且是否超过限定额度
 * b-15. isLengthInRange(Object, min, max,allowNull) 字符串长度是否在指定长度范围内
 * b-16. checkValidDate(yyObject,mmObject,ddObject,allowNull) 对日期进行全面的检查
 * b-17. checkValidDateRange(yyObject1,mmObject1,ddObject1,allowNull1,yyObject2,mmObject2,ddObject2,allowNull2)
 *  检查起始日期及截止日期
 * b-15. showMsg(String, Object)  显示提示信息String,光标焦点落在Object上,返回false

 * 第三类 功能函数，并不报错
 * c-1. getLength(String)  获取字符长度（每个中文字符为2个字符）
 * c-2. trim(String)  去掉字符串前后的空格并返回
 * c-3. textsTrim(formname) 将form中所有的text文本进行trim操作。

 */

//日期数组
arr_day=new Array("1","2","3","4","5","6","7","8","9","10",
                  "11","12","13","14","15","16","17","18","19","20",
                  "21","22","23","24","25","26","27","28","29","30","31",
                  "01","02","03","04","05","06","07","08","09");

//月份数组
arr_mon=new Array("1","2","3","4","5","6","7","8","9","10","11","12",
                  "01","02","03","04","05","06","07","08","09");

//建立者:jiarry@hotmail.com
//判断字符串是否为合法非负整数
// a-1 ifDigit(str,allowNull)
function ifDigit(str,allowNull){
 slen=str.length;
 if(slen==0) return allowNull;
 for (i=0; i<slen; i++){
  cc = str.charAt(i);
  if (cc <"0" || cc >"9"){
   return false;
  }
 }
 return true;
}

//建立者:jiarry@hotmail.com
//功能:判断字符串是否都是英文字母
// a-2 ifLetter(str,allowNull)
function ifLetter(str,allowNull ){ 
 slen=getLength(str);
 if (slen==0) return allowNull;
  
 str = str.toUpperCase();
 for ( var i = 0 ; i < slen; i ++ ){
  if ( str.charAt(i) < "A" || str.charAt(i) > "Z" )
   return false;
 }
 return true;
}

//建立者:jiarry@hotmail.com
//功能:判断某个元素是否存在于数组中
//a-3 ifExist(arr,element) arr:数组；element:某个元素
function ifExist(arr,element){
  for(var i=0;i<arr.length;i++){
    if(element==arr[i]) return true;
  }
  return false;
}

//建立者:jiarry@hotmail.com
//功能:判断是否为合法天数
//a-4 ifDay(str,allowNull)
function ifDay(str,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 return ifExist(arr_day,str);
}

//建立者:jiarry@hotmail.com
//功能:判断是否为合法月份
//a-5 ifMonth(str,allowNull)
function ifMonth(str,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 return ifExist(arr_mon,str);
}

//建立者:jiarry@hotmail.com
//功能:判断是否为合法年份(要求是四位整数，且数字>=1800 并且<=2050)
//a-6 ifYear(str,allowNull)
function ifYear(str,allowNull){
  return ifYearInRange(str,1800,2050,allowNull);
}

//建立者:jiarry@hotmail.com
//功能:判断是否为合法年份(要求是四位整数，且数字>=min_year 并且<=max_year)
//a-7 ifYearInRange(str,min_year,max_year,allowNull)
function ifYearInRange(str,min_year,max_year,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 if (slen!=4) return false;
 if (!ifDigit(str,allowNull)) return false;
 if (parseInt(str)<min_year || parseInt(str)>max_year) return false;
 return true;
}

//建立者:jiarry@hotmail.com
//功能:判断是否为合法日期格式(要求是八位整数，格式为"yyyymmdd")
//a-8 ifDate(str,allowNull)
function ifDate(str,allowNull){
 /*
 slen=getLength(str);
 if(slen==0) return allowNull;
 if(slen!=8) return false;
 if(!ifDigit(str,false)) return false;
 
 var year = str.substr(0,4);
 if(!ifYear(year,false)) return false;
 
 var month= str.substr(4,2);
 if(!ifMonth(month,false)) return false;
 
 var day= str.substr(6,2);
 if(!ifDay(day,false)) return false;

  if(!ifAvildDate(year,month,day)) return false;
 return true;
 */
 /*slen=getLength(str);
   if(slen==0) return allowNull;
   if(slen!=10) return false;
   
   var year = str.substr(0,4);
   if(!ifYear(year,false)) return false;
 var s = str.substr(4,1);
        if(s!="-") return false; 
     
        var month= str.substr(5,2);
        if(!ifMonth(month,false)) return false;
     
        var s = str.substr(7,1);
        if(s!="-") return false; 
    
        var day= str.substr(8,2);
        if(!ifDay(day,false)) return false;

 if(!ifAvildDate(year,month,day)) return false;
   return true; */
 slen=getLength(str);
   if(slen==0) return allowNull;
   if(slen<8 || slen>10) return false;
 var year = str.substr(0,4);
   if(!ifYear(year,false)) return false;
 var s = str.substr(4,1);
        if(s!="-") return false;
 str = str.substr(5);
 var pos = str.indexOf("-");
 if(pos == -1) return false;
 var month = str.substring(0,pos);
 if(!ifMonth(month,false)) return false;
 var day = str.substr(pos + 1);
 if(!ifDay(day,false)) return false;
 var yearint = parseInt(year);
 var monthint = parseInt(month);
 var dayint = parseInt(day);
 if(yearint%4 == 0)
 {  
    if(monthint==2 && dayint>29) return false;
 }
 else 
 {
    if(monthint==2 && dayint>28) return false;
 }

 return true;
}

//建立者:jiarry@hotmail.com
//判断字符串是否为合法邮件地址
// a-9 ifEmail(str,allowNull)
function ifEmail(str,allowNull){
 if(str.length==0) return allowNull;
 i=str.indexOf("@");
 j=str.lastIndexOf(".");
 if (i == -1 || j == -1 || i > j) return false;
 return true;
}

//建立者:jiarry@hotmail.com
//判断url是否为正确的恶地址;
//0-9,a-z
function validateURL(url){
 //validateURL
 if(url.search(/^[A-Za-z0-9 -]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/)!=0){
 return false;
 //}else if(url.substring(0,4)=="www."){
 //网址不要前面的www.，如果没用的话可以注释掉;
 //return false;
 }else{
 return true;
 }
}

//建立者:jiarry@hotmail.com
//判断字符串是否为合法电话号码
// a-10 ifPhone(str,allowNull)
function ifPhone(str,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 for (i=0; i<slen; i++){
  cc = str.charAt(i);
  if ((cc <"0" || cc >"9") && cc != "-" && cc!="+" && cc!="(" && cc !=")" && cc !="/"){
   return false;
  }
 }
 return true;
}

//建立者：jiarry@hotmail.com
//判断字符串是否为合法手机号码
function ifMobile(str,allowNull){
  slen=getLength(str);
  if(slen==0) return allowNull;
  if(slen!=11) return false;
  if(!ifDigit(str,allowNull))
   return false;
  if(str.substring(0,2) != '13')
   return false;
  if(str.charAt(2)=='2')
   return false;
  return true;
}

/*
 建立者：lichunping at 2004-03-24  
 
判断字符串是否为中国移动手机号码
*/
function ifChinaMobilePhone(PhoneNumber,allowNull)
{
    if ( ifMobile(PhoneNumber,allowNull) )
    {
      if(getLength(PhoneNumber) == 0 ) return allowNull;
  if( PhoneNumber.charAt(2)>='4' && PhoneNumber.charAt(2)<='9')   return true;
    }
    return false ; 
}

//建立者:jiarry@hotmail.com
//判断字符串是否包含中文字符
// a-11 ifGBK(str,allowNull)
function ifGBK(str,allowNull){
 for(var i=0;i<str.length;i++){
      var rstr=escape(str.substring(i,i+1)); 
      if (rstr.substring(0,2)=="%u"){ 
          return true;
      } 
   }
 return false;
}

//建立者:jiarry@hotmail.com
//判断单双字节的长度
// 设str某输入框内的值，若是双字节则长度多增加一个;
function bytes(str){
 if(typeof(str)!='string'){
  str = str.value;
 }
 var len = 0;
 for(var i = 0; i < str.length; i++){
  if(str.charCodeAt(i) > 127){
   len++;
  }
  len++;
 }
 return len;
}

//建立者:jiarry@hotmail.com
//限制最大字符输入，若输入框里的字符长度超出则提示
//判断单双字节;
// countLength(object)
function countLength(maxLen,obj)
{
 var dLen = 0,sLen = 0, msg = "";
 for(i=0; i< obj.length; i++ ){
   if(obj.charCodeAt(i) > "0" && obj.charCodeAt(i) < "128"){
     sLen+=1;
     }else{
      dLen+=2;
   }
 }
 totalLen = sLen+dLen;
 overLen = totalLen-maxLen;
 if( totalLen > maxLen){
     msg = ("最多" + maxLen + "字符( "+ (maxLen/2) +"个汉字)，您超出了" + overLen + "个字符，请修改。");
  }
  return msg;
}
//建立者:jiarry@hotmail.com
//限制最大字符输入，若输入框里的字符长度超出则提示
//判断单双字节;
// countLength(object)
maxLen=100;
function countLength(o)
{
var dLen;
var sLen;
dLen=0;sLen=0;
 for(i=0;i<o.value.length;i++){
   if(o.value.charCodeAt(i) > "0" && o.value.charCodeAt(i) < "128"){
     sLen+=1;
    }else{
     dLen+=2;
   }
 }
 tLen=sLen+dLen;
 lNum=tLen-maxLen;
 if(tLen>maxLen){
  alert("注意，消息最多"+maxLen+"字符！\n您超出了"+lNum+"个字符，不能继续输入！");
  }
}
//建立者:jiarry@hotmail.com
//判断字符串是否为合法钱数
// a-12 ifMoney(String)
function ifMoney(str,allowNull){
 if (str.length==0) return allowNull;

 if ( ( pos = str.indexOf( "." ) ) != -1 ){
    if (str.length==1)
      return false;
      
    if ( ( pos = str.indexOf(".", pos + 1) )  != -1 )
      return false;
 }

 for ( var i = 0 ; i < str.length; i ++ ){
   if (( str.charAt(i) < "0" || str.charAt(i) > "9" )&&(str.charAt(i)!="."))
     return false;
 }
 return true;
}

//建立者:jiarry@hotmail.com
//判断字符串是否为合法钱数,且是否在限定范围内
// a-13 ifMoneyInRange(str,minValue,maxValue,allowNull)
function ifMoneyInRange(str,minValue,maxValue,allowNull){
 if (str.length==0) return allowNull;
 if(!ifMoney(str)) return false;
 if(parseFloat(str)>maxValue) return false;
 if(parseFloat(str)<minValue) return false;
 return true;
}

//建立者:jiarry@hotmail.com
//判断是否为合法日期
// a-14 ifAvildDate(year,month,day)
function ifAvildDate(year,month,day){
  if((month==4||month==6||month==9||month==11) && day>30){
    return false;
  }else if(month==2){
    if(year % 4 >0 && day>28){
      return false;
    }else if(day>29){
      return false;
    }
  }else if(day>31){
    return false;
  }
  return true;  
}

//建立者:jiarry@hotmail.com
//判断buttongroup为名的一组radio,checkbox中有无被选中的项
// a-15 ifSelectedButton(buttonGroup)
function ifSelectedButton(buttonGroup){
    if(typeof buttonGroup=="undefined") return false;
    
    if(typeof buttonGroup.length=="undefined"){
        if(buttonGroup.checked) return true;
        return false;
    }
    
    for (var i=0;i<buttonGroup.length;i++){
        if (buttonGroup[i].checked) return true;
    }
    return false;
}

//建立者:jiarry@hotmail.com
//功能: 检查字段长度是否在指定范围内
//示例: ifLengthInRange(form1.t1, 4,10,false)
//输入参数: 需要检查的表单对象名称，最小长度，最大长度
//输出参数: true or false
// a-16 ifLengthInRange(obj, min, max,allowNull)
function ifLengthInRange(obj, min, max,allowNull){
 //obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return false;
  return true;
 }

 if (slen < min) return false;
 if (slen > max) return false;
 return true;
}


//建立者:jiarry@hotmail.com
//功能: 检查是否为非负整数
//示例: isDigit(String,allowNull)
//输入参数: 需要检查的表单对象名称
//输出参数: true或出错信息
// b-1 isDigit(Object,allowNull)
function isDigit(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个不小于0的整数.",obj);
  return true;
 }
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if (cc <"0" || cc >"9") return showMsg("输入不合要求，必须为不小于0的整数.",obj);
 }
 return true;
}

//建立者:jiarry@hotmail.com
//功能: 检查是否为非负整数，并且长度不超过指定限制
//示例: isDigitInMaxLen(String obj,int length,boolean allowNull)
//输入参数: 需要检查的表单对象名称,允许的最大长度
//输出参数: true或出错信息
// b-2 isDigitInMaxLen(Object,maxlength,allowNull)
function isDigitInMaxLen(obj,maxlength,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) 
   return showMsg("必须输入一个长度不超过"+maxlength+"的整数.",obj);
  return true;
 }
 if(slen>maxlength) return showMsg("长度超过限制，最大长度为"+maxlength+".",obj);
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if (cc < "0" || cc > "9") return showMsg("输入不合要求，必须为不小于0的整数.",obj);
 }
 return true;
}

//b-3 isDigitInRange(object,minValue,maxValue,allowNull)
function isDigitInRange(obj,minVal,maxVal,allowNull){
  obj.value=trim(obj.value);
  if(obj.value.length==0){
    if(!allowNull)
      return showMsg("必须输入一个大小在"+minVal+" - "+maxVal+"之间的整数.",obj);
    return true;
  }
  if(!isDigit(obj,false)) return false;
  if(obj.value<minVal)
    return showMsg("输入的数值太小，最小值为"+minVal,obj);
  if(obj.value>maxVal)
    return showMsg("输入的数值太大，最大值为"+maxVal,obj);
  return true;
}

//建立者:jiarry@hotmail.com
//功能:判断字符串是否都是字母
//b-4 isLetter(Object,allowNull)
function isLetter(obj,allowNull){
 obj.value=trim(obj.value);
 str = obj.value;
 slen=getLength(str);
 if ( slen == 0 ){
  if(!allowNull) return showMsg("必须有输入值.",obj);
  return true;
 }
 str = str.toUpperCase();
 for ( var i = 0 ; i < slen; i ++ ){
  if ( str.charAt(i) < "A" || str.charAt(i) > "Z" ) return showMsg("输入的必须都是英文字母.",obj);
 }
 return true;
}

//b-5 isDay(Object,allowNull)
function isDay(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合法日期(01~31).",obj);
  return true;
 }
 if (!ifDigit(obj.value)) return false;
 if (obj.value < "01" || obj.value > "31"){
  return showMsg("日格式有误，正确的格式为：DD(01~31),如:02",obj);
 }
 return true;
}

// b-6 isMoneth(Object,allowNull)
function isMonth(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合法月份(01~12).",obj);
  return true;
 }
 if (!ifDigit(obj.value)) return false;
 if(slen!=2) return showMsg("必须输入两位数字(01~12).",obj);
 if (obj.value < "01" || obj.value > "12"){
  return showMsg("月份格式有误，正确的格式为：MM(01~12),如:01",obj);
 }
 return true;
}

//建立者：默认
//功能：检查是否合法年份
//示例：isYear(Object,allowNull)
//输入参数：被检查字符串
//输出参数：true 或 错误信息
// b-7 isYear(Object,allowNull)
function isYear(obj,allowNull){
  return isYearInRange(obj,1800,2050,allowNull);
}


// b-8 isYearInRange(object,min_year,max_year,allowNull)
function isYearInRange(obj,min_year,max_year,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合法年份("+min_year+"~"+max_year+").",obj);
  return true;
 }
 if (slen!=4) return showMsg("输入的年份不合要求,请输入一个合理年份("+min_year+"~"+max_year+").",obj);
 if (!ifDigit(obj.value,false)) return showMsg("输入的年份不合要求,请输入一个合理年份("+min_year+"~"+max_year+").",obj);
 if (parseInt(obj.value)<min_year || parseInt(obj.value)>max_year) 
   return showMsg("输入的年份不合要求,请输入一个合理年份("+min_year+"~"+max_year+").",obj);
 return true;
}

//建立者：jiarry@hotmail.com
//功能：检查是否合法日期
//示例：isDate(Object)
//输入参数：被检查字符串
//输出参数：true 或 错误信息
// b-9 isDate(Object)
function isDate(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合法日期，格式为YYYYMMDD,如19990102",obj);
  return true;
 }
 
 if (!ifDigit(obj.value)){
  return showMsg("日期输入格式有误，不能含有非数字的字符.",obj);
 }else if (slen < 8){
  return showMsg("日期输入格式有误，正确的格式为：YYYYMMDD,如:19990102",obj);
 }
 year = obj.value.substr(0,4);
 if(!ifYear(year,false)) return showMsg("日期中年份输入不合理，应在1800~2050之间.",obj);
 month = obj.value.substr(4,2);
 if(!ifMonth(month,false)) return showMsg("日期中月份输入不合理，应在01~12之间.",obj);
 day = obj.value.substr(6,2);
 if(!ifDay(day,false)) return showMsg("日期中日子输入不合理，应在01~31之间.",obj);
 if(!ifAvildDate(year,month,day)) return showMsg("输入的日期不存在.",obj);
 return true;
}

//b-10 isEmail(Object)
function isEmail(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合理的邮件地址.",obj);
  return true;
 }

 i=obj.value.indexOf("@");
 j=obj.value.lastIndexOf(".");
 // if (! ifGBK(obj)) i = -1;
 if (i == -1 || j == -1 || i > j) return showMsg("邮件地址输入不合理.",obj);
 return true;
}

// b-11 isPhone(Object)
function isPhone(obj,allwoNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allwoNull) return showMsg("必须输入一个合理的电话号码.",obj);
  return true;
 }
 
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if ((cc <"0" || cc >"9") && cc != "-" && cc!="+" && cc!="(" && cc !=")" && cc !="/"){
   return showMsg("电话号码含有非法字符.",obj);
  }
 }
 return true;
}

// b-12 isGBK(Object,allowNull)
function isGBK(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个字串.",obj);
  return true;
 }
 for(var i=0;i<obj.value.length;i++){
   var rstr=escape(obj.value.substring(i,i+1)); 
    if (rstr.substring(0,2)=="%u"){ 
      return true;
    } 
  }
 return showMsg("输入的字串中没有中文字符",obj);
}

//建立者:jiarry@hotmail.com
//判断字符串是否为合法钱数
// b-13 isMoney(Object,allowNull)
function isMoney(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合理钱数.",obj);
  return true;
 }

 if (ifMoney(obj.value,false)){
  return true;
 }else{
  return showMsg("不是合理的钱数.",obj);
 } 
 return true;
}

//建立者:jiarry@hotmail.com
//判断是否合法钱数且是否超过限定额度
//b-14 isMoneyInRange(obj,minValue,maxValue)
function isMoneyInRange(obj,minValue,maxValue,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须输入一个合理钱数.",obj);
  return true;
 }

 if(!ifMoneyInRange(obj.value,minValue,maxValue,false)){
   return showMsg("输入的钱数不合理，要求值在"+minValue+"~"+maxValue+"之间.",obj);
 }
 return true;
}

//建立者:jiarry@hotmail.com
//功能: 检查字段长度是否在指定范围内
//示例: isLengthInRange(form1.t1, 4,10)
//输入参数: 需要检查的表单对象名称，最小长度，最大长度
//输出参数: true
// b-15 isLengthInRange(obj, min, max,allowNull)
function isLengthInRange(obj, min, max,allowNull){
 //obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("必须有输入值.",obj);
  return true;
 }

 if (slen < min) return showMsg("请至少输入 " + min + " 个字符.",obj);
 if (slen > max) return showMsg("请最多输入 " + max + " 个字符.",obj);
 return true;
}


//建立者:jiarry@hotmail.com
//功能:全选或取消全选form里的checkbox，参数1即为全选,否则即取消;
function checkall(obj,parm){//全选or取消全选;
   for(var i=0;i<obj.elements.length;i++){
    if(obj.elements[i].tagName.toLowerCase()=="input" && obj.elements[i].type=="checkbox"){
      if(parm==1){//check all checkbox input;
      obj.elements[i].checked=true;
      }else{
       obj.elements[i].checked=false;
      }
         
    }
  } 
}

//建立者:jiarry@hotmail.com
//功能:反选form里的checkbox;
function reverseCheck(obj){
   for(var i=0;i<obj.elements.length;i++){
    if(obj.elements[i].tagName.toLowerCase()=="input" && obj.elements[i].type=="checkbox"){
      obj.elements[i].checked = obj.elements[i].checked ? false : true;
    }
  } 
}

//建立者:jiarry@hotmail.com
//功能:判断form里的元素是否已经有child这样的值;
//更多限制可以通过element的tagName,type,name等来判断;
function isExistElement(child,obj){ 
   for(var i=0;i<obj.elements.length;i++){
    //if(obj.elements[i].tagName.toLowerCase()=="input" && obj.elements[i].type=="checkbox"){
       if(obj.elements[i].value==child)return true;
    //}
   }
   return false;
}

//建立者:jiarry@hotmail.com
//功能:对日期进行全面的检查
//输入参数:yyObject:年的object;mmObject:月的object;ddObject:日的object;
//输入参数:allowNull:true允许日期为空;false:必须选择日期
//输出参数:ture of false;
// b-16 checkValiDate(mmObject,ddObject,yyObject,allowNull)
function checkValidDate(yyObject,mmObject,ddObject,allowNull){
   if(allowNull){
    if(!(((!yyObject.options[0].selected)&&(!mmObject.options[0].selected)&&(!ddObject.options[0].selected)) || ((yyObject.options[0].selected)&&(mmObject.options[0].selected)&&(ddObject.options[0].selected))))
     return showMsg("日期必须全部选择或者全部不选择!",yyObject);
   }else{
    if(yyObject.options[0].selected){
     return showMsg("请选择日期的年!",yyObject);
    }
    if(mmObject.options[0].selected){
     return showMsg("请选择日期的月!",mmObject);
    }
    if(ddObject.options[0].selected){
     return showMsg("请选择日期的日!",ddObject);
    }
   }
   
   if(!yyObject.options[0].selected){
    var my_year=yyObject[yyObject.selectedIndex].value;
    var my_month=mmObject[mmObject.selectedIndex].value;
    var my_day=ddObject[ddObject.selectedIndex].value;
    
    if(!ifAvildDate(my_year,my_month,my_day))
     return showMsg("选择的日期不合法!",ddObject);
   }
   return true;
}


//建立者:jiarry@hotmail.com
//功能:对日期进行全面的检查
//输入参数:yyObject1:其始年的object;mmObject1:其始月的object;ddObject1:其始日的object;
//输入参数:allowNull1:起始日期true允许日期为空;false:必须选择日期
//输入参数:yyObject2:截止年的object;mmObject2:截止月的object;ddObject2:截止日的object;
//输入参数:allowNull2:截止日期true允许日期为空;false:必须选择日期
//输出参数:ture of false;
// b-17 checkValidDateRange(mmObject1,ddObject1,yyObject1,allowNull1,mmObject2,ddObject2,yyObject2,allowNull2)
function checkValidDateRange(yyObject1,mmObject1,ddObject1,allowNull1,yyObject2,mmObject2,ddObject2,allowNull2){
 if(!checkValidDate(mmObject1,ddObject1,yyObject1,allowNull1)) return false;
 if(!checkValidDate(mmObject2,ddObject2,yyObject2,allowNull2)) return false;
 
 if((!yyObject1.options[0].selected) && (!yyObject2.options[0].selected)){
  date1=new Date(yyObject1-1900,mmObject1-1,ddObject1);
  date2=new Date(yyObject2-1900,mmObject2-1,ddObject2);
  if(date1>date2){
   return showMsg("起始日期不能大于截止日期.",yyObject1);
  }
 }
 return true;
}


//建立者:jiarry@hotmail.com
//功能:显示提示信息Msg,光标焦点落在Obj上,返回false
//     主要用于检查必要字段是否正确
//示例:showMsg("用户名不能为空.",myform.username)
//输入参数:Msg(提示信息) Obj(光标焦点对象)
//输出参数:false
// b-18 showMsg(String, Object)
function showMsg(Msg, Obj){
 alert( Msg );
 Obj.focus();
 return false;
}

//建立者:jiarry@hotmail.com
//功能:显示提示信息Msg,光标焦点落在Obj上
//     主要用于检查必要字段是否正确
//示例:showMsg2("用户名不能为空.",myform.username)
//输入参数:Msg(提示信息) Obj(光标焦点对象)
//输出参数:false
// b-19 showMsg2(String, Object)
function showMsg2(Msg, Obj){
 alert( Msg );
 Obj.focus();
 return;
}

/**
以下是第三类
*/
//建立者:jiarry@hotmail.com
//加入了汉字的长度判断
// c-1 getLength(String)
function getLength(str){
 var templen=str.length;
 if(navigator.appName=='Netscape') return templen;
 for(var i=0;i<str.length;i++){
      var rstr=escape(str.substring(i,i+1)); 
      if (rstr.substring(0,2)=="%u"){ 
          templen++;
      } 
   }
 return templen;
}

//建立者:jiarry@hotmail.com
//功能:去掉字符串前后的空格并返回
//输入参数:inputStr(待处理的字符串)
//输出参数:inputStr(处理后的字符串)
// c-2 trim(String)
function trim(inputStr){
 var result = inputStr;
 while (result.substring(0,1) == " ") {
  result = result.substring(1,result.length);
 }
 
 while (result.substring(result.length-1, result.length) == " ") {
  result = result.substring(0, result.length-1);
 }
  
 return result;
}

//建立者:jiarry@hotmail.com
//功能:去掉字符串前后的空格并返回
//输入参数:inputStr(待处理的字符串)
//输出参数:inputStr(处理后的字符串)
// c-2 string.trim();
String.prototype.trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}

//Trim,参数String
function trim(myStr)
{
  var pos1=-1;
  var pos2=-1;
  for(var i=0;i<myStr.length;i++){
     if(myStr.charAt(i)!=' ' && pos1<0) pos1=i;
     if(myStr.charAt(myStr.length-i-1)!=' ' && pos2<0) pos2=myStr.length-i;
  }
  if(pos1<0 || pos2 <0) return("");
  return(myStr.substring(pos1,pos2));
}

function trim(s)
{
    if (s == null)
    {
        return s;
    }
    var i;
    var beginIndex = 0;
    var endIndex = s.length - 1;

    for (i=0; i<s.length; i++)
    {
        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            beginIndex++;
        }
        else
        {
            break;
        }
    }
    for (i = s.length - 1; i >= 0; i--)
    {
         // if (s.charAt(i) == ' ' || s.charAt(i) == '　' || s.charAt(i)=='\r' || s.charAt(i)=='\n')

        if (s.charAt(i) == ' ' || s.charAt(i) == '　')
        {
            endIndex--;
        }
        else
        {
            break;
        }
    }
    if (endIndex < beginIndex)
    {
        return "";
    }
    return s.substring(beginIndex, endIndex + 1);
}
//建立者:jiarry@hotmail.com
//功能:将form中所有的text文本进行trim操作。
//输入参数:myform(form名)
//输出参数:无
// c-3 textTrim(form名称)
function textsTrim(myform){
  for(var i=0;i<myform.elements.length;i++){
    var etype=myform.elements[i].type;
   if(etype = "text"){
     myform.elements[i].value=trim(myform.elements[i].value);
   }
  }
}


//建立者:jiarry@hotmail.com
//功能:将form某一input框或textarea里的内容copy至粘贴板
//输入参数:myform(input对象名称)
//输出参数:无
function copyCode(obj) {
 var rng = document.body.createTextRange();
 rng.moveToElementText(obj);
 rng.scrollIntoView();
 rng.select();
 rng.execCommand("Copy");
 rng.collapse(false);
}

//建立者:jiarry@hotmail.com
//功能:以HTML页面方式运行某一input或textarea里的内容//firefox下可能不支持
//输入参数:myform(input对象名称)
//输出参数:无
function runCode(obj) {
 var winname = window.open('', "_blank", '');
 winname.document.open('text/html', 'replace');
 winname.document.writeln(obj.value);
 winname.document.close();
}

//建立者:jiarry@hotmail.com
//功能:将form某一input框或textarea里的内容另存为//firefox下可能不支持
//输入参数:myform(input对象名称)
//输出参数:无
function saveCode(obj) {
 var winname = window.open('', '_blank', 'top=10000');
 winname.document.open('text/html', 'replace');
 winname.document.writeln(obj.value);
 winname.document.execCommand('saveas','','code.htm');
 winname.close();
}


//建立者:jiarry@hotmail.com
//功能:相当于java里的replaceAll，可以替换字符里的包含的某个全部字符串
//输入参数:被替换的字符串与要替换的字符串
//输出参数:无
String.prototype.replaceAll = strReplace;
function strReplace(findText, replaceText) {
  var str = new String(this);
  while (str.indexOf(findText)!=-1) {
    str = str.replace(findText, replaceText);
  }
  return str;
}
String.prototype.replaceAll = strreplace;
function  strreplace( str1, str2 ){
    return this.replace( new RegExp( str1,"g" ), str2);
}
//正则表达式全部替换字符，与tempStr.replaceAll()相当;

//建立者:jiarry@hotmail.com
//功能:将form某一textarea里的内容里的br清除;将回车替换成<br />之类
//输入参数:myform(input对象名称)
//输出参数:无
function cleanBR() {
 var area = document.getElementsByTagName('TEXTAREA');
 for (var i=0; i<area.length; i++) {
  area[i].value = area[i].value.replace(/<br \/>/ig, '');
  area[i].value = area[i].value.replace(/(\xA0\x20){4}/ig, '\t');
  area[i].value = area[i].value.replace(/\xA0\x20\xA0/ig, '   ');
  area[i].value = area[i].value.replace(/\xA0\xA0/ig, '  ');
 }
}

//建立者:jiarry@hotmail.com
//功能:将属性rel为blank的链接用window.open()的方式打开。
//比如<a href="#" rel="blank"></a>
//输入参数:myform(input对象名称)
//输出参数:无
function tBlank(){
 var aLinks=document.getElementsByTagName("a");
 for (var i=0;i<aLinks.length;i++){
   if(aLinks[i].getAttribute("rel")=="blank"){
   //alert(aLinks[i].getAttribute("href"));
   aLinks[i].target="_blank";
   }
 }
}


//建立者:jiarry@hotmail.com
//功能:检查和设置数字
//比如<a href="#" rel="nubmer"></a>
//输入参数:myform(input对象名称)
//输出参数:无
function setNum(){
  var inp= document.getElementsByTagName("input");
  //alert(inp.length);
  for(var i=0;i<inp.length;i++){
   if(inp[i].getAttribute("rel")=="number"){
     // checkNum(inp[i]);
      //inp[i].onmouseover=
      eval("checkNum(inp[i])");
       //alert(inp[i].value);
       //inp[i].onblur=eval (alert("d"));
      }
   }
}

//建立者:jiarry@hotmail.com
//功能:验证是否为数字与isNaN()相反
//输出参数:返回true或者false
function isNum(obj){
    for(var i=0; i < obj.value.length;i++){
     if(obj.value.charAt(i)<"0" || obj.value.charAt(i)>"9"){
     return false; 
     }
    }     
  return true; 
}

//建立者:jiarry@hotmail.com
//正则表达式去除左右空格。
//返回去除后的字符串
function trim(s) {
 return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

//建立者:jiarry@hotmail.com
//正则表达式Email的验证
//验证是否含有@.等
function validateEmail(email){
   var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
    if(myReg.test(email)==false){
       return false;
   }
   return true;
}

//建立者:jiarry@hotmail.com
//正则表达式电话的验证
//验证是否含有@.等
function validatePhone(phone){
   var myReg = /(^[0-9]{3,4}\-[0-9]{5,8}$)|(^[0-9]{5,12}$)|(^\([0-9]{3,4}\)[0-9]{5,8}$)|(^0{0,1}13[0-9]{9}$)/;
    if(myReg.test(phone)==false){
       return false;
   }
   return true;
}

//建立者:jiarry@hotmail.com
//验证字符长度是否超出某个值
//返回true或false
function validateMaxLength(v, maxlen){
   if (trim(v).length > maxlen){
     return false;
   }
   return true;
}

/*
检查18位身份证最后一位即校验码是否正确
Author:lichunping@baidu.com;jarryli@gmail.com;
homepage:http://jiarry.126.com;
bolg:http://jiarry.blogchina.com;
1，∑(a[i]*W[i]) mod 11 ( i = 2, 3, ..., 18 ) (1) ;
2，加权因子分别为 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ；
3，将前17位号码分别乘以各自的加权因子,再求和除以11,再取余数。
4，用余数对应下方的校验码
5，Y余数:  0 1 2 3 4 5 6 7 8 9 10 ;
6，校验码: 1 0 X 9 8 7 6 5 4 3 2 ;
*/
var ai=new Array();//用户的身份证号码数组;
var wi=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];//17位数对应的加权因子，自左至右;
var yi=[0,1,2,3,4,5,6,7,8,9,10];//余数;
var vi=["1","0","X","9","8","7","6","5","4","3","2"];//余数对应的校验码;
var r;//最终的校验码;
var sum;//和
var y;//余数

//用法:getVerifyCode(this.form.number)
//返回正确的验证码;
function getVerifyCode(num){//17位的号码请求，则可以返回检验码;
 r=0;y=0;sum=0;
 var v=num;
 if( typeof(num) != "undefined" && typeof(num) != "string" && num.type == "text" ) v = num.value;
  v=v.toUpperCase();var vl;
  if(v.length==18)
      vl=v.length-1;
  else
      vl=v.length;
  if(vl!=17)
   {
   alert("请求号码不正确");return;
   }
  for(var i=0;i<vl;i++){
        ai[i]=v.charAt(i); 
        sum+=ai[i]*wi[i];//身份证号码分别乘以加权因子再求和;  
  }
   y=sum%11;     
   r=vi[y];//校验码中的第y个即时校验码;
   return r;//返回18位身份证的校验码;
}

//用法:checkIdVerifyCode(this.form.number)
//返回true或false;
function checkIdVerifyCode(o){
 var v = o;
 if( typeof(o) != "undefined" && typeof(o) != "string" && o.type == "text" )v=o.value;
  var num18 = v.substr(17,1);
/*//号码验证不一定需要;
 var num17 = v.substring(0,17);
 if(v.length != 18){
    //alert("请输入18位身份证号码");
 return false;
  }else if(!isNaN(v)){
    //alert("18位全是数字");
  }else if( num18.toUpperCase() == "X" && !isNaN( num17 )){
    //alert("第18位数字是X，前17位是数字");
  }else{
    //alert("请输入有效的身份证号码");
 return false;
 }*/
 if(getVerifyCode(v) == num18)
   return true;
 else
  return false;
}

// get absolute Left position
//建立者:jiarry@hotmail.com
//返回对象位于窗口的绝对左边距离
function getAbsoluteLeft( ob ){
 if(!ob){return null;}
   var obj = ob;
   var objLeft = obj .offsetLeft;
   while( obj != null && obj .offsetParent != null && obj .offsetParent.tagName != "BODY" ){
     objLeft += obj .offsetParent.offsetLeft;
     obj = obj .offsetParent;
   }
 return objLeft ;
}

// get absolute TOP position
//建立者:jiarry@hotmail.com
//返回对象位于窗口的绝对上边距离
function getAbsoluteTop( ob ){
 if(!ob){return null;}
 var obj = ob;
 var objTop = obj .offsetTop;
 while( obj != null && obj .offsetParent != null && obj .offsetParent.tagName != "BODY" ){
   objTop += obj .offsetParent.offsetTop;
   obj = obj .offsetParent;
 }
 return objTop ;
}

// get absolute TOP position
//建立者:jiarry@hotmail.com
//检查颜色值是否为真;
//用法: if( isAllNum16("#000000") )alert("合法颜色");
function isNum16(ch)
{
  if (ch >= '0' && ch <= '9')return true;
  if (ch >= 'A' && ch <= 'F')return true;
  if (ch >= 'a' && ch <= 'f')return true;
  return false;
}
function isAllNum16(str1)
{//判断颜色值。除第一个字符#外的任一个值是否大于等a,A,0,小于等于f,F,9，否则报错。
  for (i=1; i<str1.length; i++) {
   if (!isNum16(str1.charAt(i)))
   {
    return false;
   }
  }
  return true;
}

/**
 * Read the JavaScript cookies tutorial at:
 *   http://www.netspade.com/articles/javascript/cookies.xml
 */

/**
 * Sets a Cookie with the given name and value.
 *
 * name       Name of the cookie
 * value      Value of the cookie
 * [expires]  Expiration date of the cookie (default: end of current session)
 * [path]     Path where the cookie is valid (default: path of calling document)
 * [domain]   Domain where the cookie is valid
 *              (default: domain of calling document)
 * [secure]   Boolean value indicating if the cookie transmission requires a
 *              secure transmission
 */
function setCookie(name, value, expires, path, domain, secure)
{//时间，域名，路径等可以根据需要来设定
    //var path="/";
 //var domain="baidu.com"; 
 var expdate = new Date();
 expdate.setTime(expdate.getTime() + (365*24*120));//设为1年时效
    expires = expdate;

    document.cookie= name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
}

function readCookie(name) {
 //http://www.quirksmode.org/js/cookies.html#link7
 var nameEQ = name + "=";
 var ca = document.cookie.split(';');
 for(var i=0;i < ca.length;i++) {
  var c = ca[i];
  while (c.charAt(0)==' ') c = c.substring(1,c.length);
  if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
 }
 return null;
}
/**
 * Gets the value of the specified cookie.
 *
 * name  Name of the desired cookie.
 *
 * Returns a string containing value of specified cookie,
 *   or null if cookie does not exist.
 */
function getCookie(name)
{
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1)
    {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    }
    else
    {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1)
    {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}

/**
 * Deletes the specified cookie.
 *
 * name      name of the cookie
 * [path]    path of the cookie (must be same as path used to create cookie)
 * [domain]  domain of the cookie (must be same as domain used to create cookie)
 */
function deleteCookie(name, path, domain)
{
    if (getCookie(name))
    {
        document.cookie = name + "=" + 
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}
