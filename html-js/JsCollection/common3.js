/**
 * JavaScript ����
 * @author Jarry.li
 * @version 2.01
 * @last modify time 2003/09/11
 * #@ validate.js
 * ��һ�� ��鲢���ؼ����(true or false)
 * a-1. ifDigit(str,allowNull)  �Ƿ�Ϊ�Ϸ��Ǹ�����(examples/ifDigit.html)
 * a-2. ifLetter(str,allowNull) �Ƿ�ΪӢ����ĸ
 * a-3. ifExist(arr,element)    ĳ��Ԫ���Ƿ������ĳ��������
 * a-4. ifDay(str,allowNull)    �Ƿ�Ϊ����
 * a-5. ifMonth(str,allowNull)  �Ƿ�Ϊ�·�
 * a-6. ifYear(str,allowNull)   �Ƿ�Ϊ���
 * a-7. ifYearInRange(str,min_year,max_year,allowNull)  �Ƿ�Ϊָ����Χ�ڵĺϷ����
 * a-8. ifDate(String,allowNull)   �Ƿ�Ϊ����
 * a-9. ifEmail(String,allowNull)  �Ƿ�Ϊ�ʼ���ַ
 * a-10. ifPhone(String,allowNull)  �Ƿ�Ϊ�绰����
 * a-11. ifGBK(String,allowNull)    �Ƿ���������ַ�
 * a-12.ifMoney(String,allowNull)  �Ƿ�Ϊ�Ϸ���������
 * a-13 ifMoneyInRange(String,int,int,allowNull) �ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��,���Ƿ񳬹��޶���Χ
 * a-14. ifAvildDate(year,month,day) �ж��Ƿ�Ϊ�Ϸ�����
 * a-15. ifSelectedButton(buttonGroup) �ж�buttongroupΪ����һ��radio�����ޱ�ѡ�е���

 * �ڶ��� ����ֱ�ӱ���
 * b-1. isDigit(Object,allowNull)  �Ƿ�Ϊ����
 * b-2. isDigitInMaxLen(object,maxlength,allowNull) �Ƿ�Ϊ�Ǹ��������ҳ�����ָ����Χ��
 * b-3. isDigitInRange(object,minValue,maxValue,allowNull) �Ƿ�Ϊ�Ǹ��������Ҵ�С��ָ����Χ��
 * b-4. isLetter(Object,allowNull) �Ƿ�Ϊ��ĸ
 * b-5. isDay(Object,allowNull)    �Ƿ�Ϊ����
 * b-6. isMonth(Object,allowNull)  �Ƿ�Ϊ�·�
 * b-7. isYear(Object,allowNull)   �Ƿ�Ϊ���
 * b-8. isYearInRange(object,min_year,max_year,allowNull) �Ƿ�Ϊָ����Χ�ڵĺϷ����
 * b-9. isDate(Object,allowNull)   �Ƿ�Ϊ����
 * b-10. isEmail(Object,allowNull)  �Ƿ�Ϊ�ʼ���ַ
 * b-11. isPhone(Object,allowNull)  �Ƿ�Ϊ�绰����
 * b-12. isGBK(Object,allowNull)    �Ƿ�Ϊ�����ַ�
 * b-13. isMoney(Object,allowNull)  �Ƿ�Ϊ�Ϸ���������
 * b-14. isMoneyInRange(object,minValue,maxValue,allowNull) �ж��Ƿ�Ϸ�Ǯ�����Ƿ񳬹��޶����
 * b-15. isLengthInRange(Object, min, max,allowNull) �ַ��������Ƿ���ָ�����ȷ�Χ��
 * b-16. checkValidDate(yyObject,mmObject,ddObject,allowNull) �����ڽ���ȫ��ļ��
 * b-17. checkValidDateRange(yyObject1,mmObject1,ddObject1,allowNull1,yyObject2,mmObject2,ddObject2,allowNull2)
 *  �����ʼ���ڼ���ֹ����
 * b-15. showMsg(String, Object)  ��ʾ��ʾ��ϢString,��꽹������Object��,����false

 * ������ ���ܺ�������������
 * c-1. getLength(String)  ��ȡ�ַ����ȣ�ÿ�������ַ�Ϊ2���ַ���
 * c-2. trim(String)  ȥ���ַ���ǰ��Ŀո񲢷���
 * c-3. textsTrim(formname) ��form�����е�text�ı�����trim������

 */

//��������
arr_day=new Array("1","2","3","4","5","6","7","8","9","10",
                  "11","12","13","14","15","16","17","18","19","20",
                  "21","22","23","24","25","26","27","28","29","30","31",
                  "01","02","03","04","05","06","07","08","09");

//�·�����
arr_mon=new Array("1","2","3","4","5","6","7","8","9","10","11","12",
                  "01","02","03","04","05","06","07","08","09");

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ��Ǹ�����
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

//������:jiarry@hotmail.com
//����:�ж��ַ����Ƿ���Ӣ����ĸ
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

//������:jiarry@hotmail.com
//����:�ж�ĳ��Ԫ���Ƿ������������
//a-3 ifExist(arr,element) arr:���飻element:ĳ��Ԫ��
function ifExist(arr,element){
  for(var i=0;i<arr.length;i++){
    if(element==arr[i]) return true;
  }
  return false;
}

//������:jiarry@hotmail.com
//����:�ж��Ƿ�Ϊ�Ϸ�����
//a-4 ifDay(str,allowNull)
function ifDay(str,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 return ifExist(arr_day,str);
}

//������:jiarry@hotmail.com
//����:�ж��Ƿ�Ϊ�Ϸ��·�
//a-5 ifMonth(str,allowNull)
function ifMonth(str,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 return ifExist(arr_mon,str);
}

//������:jiarry@hotmail.com
//����:�ж��Ƿ�Ϊ�Ϸ����(Ҫ������λ������������>=1800 ����<=2050)
//a-6 ifYear(str,allowNull)
function ifYear(str,allowNull){
  return ifYearInRange(str,1800,2050,allowNull);
}

//������:jiarry@hotmail.com
//����:�ж��Ƿ�Ϊ�Ϸ����(Ҫ������λ������������>=min_year ����<=max_year)
//a-7 ifYearInRange(str,min_year,max_year,allowNull)
function ifYearInRange(str,min_year,max_year,allowNull){
 slen=getLength(str);
 if (slen==0) return allowNull;
 if (slen!=4) return false;
 if (!ifDigit(str,allowNull)) return false;
 if (parseInt(str)<min_year || parseInt(str)>max_year) return false;
 return true;
}

//������:jiarry@hotmail.com
//����:�ж��Ƿ�Ϊ�Ϸ����ڸ�ʽ(Ҫ���ǰ�λ��������ʽΪ"yyyymmdd")
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

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ��ʼ���ַ
// a-9 ifEmail(str,allowNull)
function ifEmail(str,allowNull){
 if(str.length==0) return allowNull;
 i=str.indexOf("@");
 j=str.lastIndexOf(".");
 if (i == -1 || j == -1 || i > j) return false;
 return true;
}

//������:jiarry@hotmail.com
//�ж�url�Ƿ�Ϊ��ȷ�Ķ��ַ;
//0-9,a-z
function validateURL(url){
 //validateURL
 if(url.search(/^[A-Za-z0-9 -]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/)!=0){
 return false;
 //}else if(url.substring(0,4)=="www."){
 //��ַ��Ҫǰ���www.�����û�õĻ�����ע�͵�;
 //return false;
 }else{
 return true;
 }
}

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ��绰����
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

//�����ߣ�jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ��ֻ�����
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
 �����ߣ�lichunping at 2004-03-24  
 
�ж��ַ����Ƿ�Ϊ�й��ƶ��ֻ�����
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

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ���������ַ�
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

//������:jiarry@hotmail.com
//�жϵ�˫�ֽڵĳ���
// ��strĳ������ڵ�ֵ������˫�ֽ��򳤶ȶ�����һ��;
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

//������:jiarry@hotmail.com
//��������ַ����룬�����������ַ����ȳ�������ʾ
//�жϵ�˫�ֽ�;
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
     msg = ("���" + maxLen + "�ַ�( "+ (maxLen/2) +"������)����������" + overLen + "���ַ������޸ġ�");
  }
  return msg;
}
//������:jiarry@hotmail.com
//��������ַ����룬�����������ַ����ȳ�������ʾ
//�жϵ�˫�ֽ�;
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
  alert("ע�⣬��Ϣ���"+maxLen+"�ַ���\n��������"+lNum+"���ַ������ܼ������룡");
  }
}
//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
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

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��,���Ƿ����޶���Χ��
// a-13 ifMoneyInRange(str,minValue,maxValue,allowNull)
function ifMoneyInRange(str,minValue,maxValue,allowNull){
 if (str.length==0) return allowNull;
 if(!ifMoney(str)) return false;
 if(parseFloat(str)>maxValue) return false;
 if(parseFloat(str)<minValue) return false;
 return true;
}

//������:jiarry@hotmail.com
//�ж��Ƿ�Ϊ�Ϸ�����
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

//������:jiarry@hotmail.com
//�ж�buttongroupΪ����һ��radio,checkbox�����ޱ�ѡ�е���
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

//������:jiarry@hotmail.com
//����: ����ֶγ����Ƿ���ָ����Χ��
//ʾ��: ifLengthInRange(form1.t1, 4,10,false)
//�������: ��Ҫ���ı��������ƣ���С���ȣ���󳤶�
//�������: true or false
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


//������:jiarry@hotmail.com
//����: ����Ƿ�Ϊ�Ǹ�����
//ʾ��: isDigit(String,allowNull)
//�������: ��Ҫ���ı���������
//�������: true�������Ϣ
// b-1 isDigit(Object,allowNull)
function isDigit(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ����С��0������.",obj);
  return true;
 }
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if (cc <"0" || cc >"9") return showMsg("���벻��Ҫ�󣬱���Ϊ��С��0������.",obj);
 }
 return true;
}

//������:jiarry@hotmail.com
//����: ����Ƿ�Ϊ�Ǹ����������ҳ��Ȳ�����ָ������
//ʾ��: isDigitInMaxLen(String obj,int length,boolean allowNull)
//�������: ��Ҫ���ı���������,�������󳤶�
//�������: true�������Ϣ
// b-2 isDigitInMaxLen(Object,maxlength,allowNull)
function isDigitInMaxLen(obj,maxlength,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) 
   return showMsg("��������һ�����Ȳ�����"+maxlength+"������.",obj);
  return true;
 }
 if(slen>maxlength) return showMsg("���ȳ������ƣ���󳤶�Ϊ"+maxlength+".",obj);
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if (cc < "0" || cc > "9") return showMsg("���벻��Ҫ�󣬱���Ϊ��С��0������.",obj);
 }
 return true;
}

//b-3 isDigitInRange(object,minValue,maxValue,allowNull)
function isDigitInRange(obj,minVal,maxVal,allowNull){
  obj.value=trim(obj.value);
  if(obj.value.length==0){
    if(!allowNull)
      return showMsg("��������һ����С��"+minVal+" - "+maxVal+"֮�������.",obj);
    return true;
  }
  if(!isDigit(obj,false)) return false;
  if(obj.value<minVal)
    return showMsg("�������ֵ̫С����СֵΪ"+minVal,obj);
  if(obj.value>maxVal)
    return showMsg("�������ֵ̫�����ֵΪ"+maxVal,obj);
  return true;
}

//������:jiarry@hotmail.com
//����:�ж��ַ����Ƿ�����ĸ
//b-4 isLetter(Object,allowNull)
function isLetter(obj,allowNull){
 obj.value=trim(obj.value);
 str = obj.value;
 slen=getLength(str);
 if ( slen == 0 ){
  if(!allowNull) return showMsg("����������ֵ.",obj);
  return true;
 }
 str = str.toUpperCase();
 for ( var i = 0 ; i < slen; i ++ ){
  if ( str.charAt(i) < "A" || str.charAt(i) > "Z" ) return showMsg("����ı��붼��Ӣ����ĸ.",obj);
 }
 return true;
}

//b-5 isDay(Object,allowNull)
function isDay(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ���Ϸ�����(01~31).",obj);
  return true;
 }
 if (!ifDigit(obj.value)) return false;
 if (obj.value < "01" || obj.value > "31"){
  return showMsg("�ո�ʽ������ȷ�ĸ�ʽΪ��DD(01~31),��:02",obj);
 }
 return true;
}

// b-6 isMoneth(Object,allowNull)
function isMonth(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ���Ϸ��·�(01~12).",obj);
  return true;
 }
 if (!ifDigit(obj.value)) return false;
 if(slen!=2) return showMsg("����������λ����(01~12).",obj);
 if (obj.value < "01" || obj.value > "12"){
  return showMsg("�·ݸ�ʽ������ȷ�ĸ�ʽΪ��MM(01~12),��:01",obj);
 }
 return true;
}

//�����ߣ�Ĭ��
//���ܣ�����Ƿ�Ϸ����
//ʾ����isYear(Object,allowNull)
//���������������ַ���
//���������true �� ������Ϣ
// b-7 isYear(Object,allowNull)
function isYear(obj,allowNull){
  return isYearInRange(obj,1800,2050,allowNull);
}


// b-8 isYearInRange(object,min_year,max_year,allowNull)
function isYearInRange(obj,min_year,max_year,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ���Ϸ����("+min_year+"~"+max_year+").",obj);
  return true;
 }
 if (slen!=4) return showMsg("�������ݲ���Ҫ��,������һ���������("+min_year+"~"+max_year+").",obj);
 if (!ifDigit(obj.value,false)) return showMsg("�������ݲ���Ҫ��,������һ���������("+min_year+"~"+max_year+").",obj);
 if (parseInt(obj.value)<min_year || parseInt(obj.value)>max_year) 
   return showMsg("�������ݲ���Ҫ��,������һ���������("+min_year+"~"+max_year+").",obj);
 return true;
}

//�����ߣ�jiarry@hotmail.com
//���ܣ�����Ƿ�Ϸ�����
//ʾ����isDate(Object)
//���������������ַ���
//���������true �� ������Ϣ
// b-9 isDate(Object)
function isDate(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ���Ϸ����ڣ���ʽΪYYYYMMDD,��19990102",obj);
  return true;
 }
 
 if (!ifDigit(obj.value)){
  return showMsg("���������ʽ���󣬲��ܺ��з����ֵ��ַ�.",obj);
 }else if (slen < 8){
  return showMsg("���������ʽ������ȷ�ĸ�ʽΪ��YYYYMMDD,��:19990102",obj);
 }
 year = obj.value.substr(0,4);
 if(!ifYear(year,false)) return showMsg("������������벻����Ӧ��1800~2050֮��.",obj);
 month = obj.value.substr(4,2);
 if(!ifMonth(month,false)) return showMsg("�������·����벻����Ӧ��01~12֮��.",obj);
 day = obj.value.substr(6,2);
 if(!ifDay(day,false)) return showMsg("�������������벻����Ӧ��01~31֮��.",obj);
 if(!ifAvildDate(year,month,day)) return showMsg("��������ڲ�����.",obj);
 return true;
}

//b-10 isEmail(Object)
function isEmail(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ��������ʼ���ַ.",obj);
  return true;
 }

 i=obj.value.indexOf("@");
 j=obj.value.lastIndexOf(".");
 // if (! ifGBK(obj)) i = -1;
 if (i == -1 || j == -1 || i > j) return showMsg("�ʼ���ַ���벻����.",obj);
 return true;
}

// b-11 isPhone(Object)
function isPhone(obj,allwoNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allwoNull) return showMsg("��������һ������ĵ绰����.",obj);
  return true;
 }
 
 for (i=0; i<slen; i++){
  cc = obj.value.charAt(i);
  if ((cc <"0" || cc >"9") && cc != "-" && cc!="+" && cc!="(" && cc !=")" && cc !="/"){
   return showMsg("�绰���뺬�зǷ��ַ�.",obj);
  }
 }
 return true;
}

// b-12 isGBK(Object,allowNull)
function isGBK(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ���ִ�.",obj);
  return true;
 }
 for(var i=0;i<obj.value.length;i++){
   var rstr=escape(obj.value.substring(i,i+1)); 
    if (rstr.substring(0,2)=="%u"){ 
      return true;
    } 
  }
 return showMsg("������ִ���û�������ַ�",obj);
}

//������:jiarry@hotmail.com
//�ж��ַ����Ƿ�Ϊ�Ϸ�Ǯ��
// b-13 isMoney(Object,allowNull)
function isMoney(obj,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ������Ǯ��.",obj);
  return true;
 }

 if (ifMoney(obj.value,false)){
  return true;
 }else{
  return showMsg("���Ǻ����Ǯ��.",obj);
 } 
 return true;
}

//������:jiarry@hotmail.com
//�ж��Ƿ�Ϸ�Ǯ�����Ƿ񳬹��޶����
//b-14 isMoneyInRange(obj,minValue,maxValue)
function isMoneyInRange(obj,minValue,maxValue,allowNull){
 obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("��������һ������Ǯ��.",obj);
  return true;
 }

 if(!ifMoneyInRange(obj.value,minValue,maxValue,false)){
   return showMsg("�����Ǯ��������Ҫ��ֵ��"+minValue+"~"+maxValue+"֮��.",obj);
 }
 return true;
}

//������:jiarry@hotmail.com
//����: ����ֶγ����Ƿ���ָ����Χ��
//ʾ��: isLengthInRange(form1.t1, 4,10)
//�������: ��Ҫ���ı��������ƣ���С���ȣ���󳤶�
//�������: true
// b-15 isLengthInRange(obj, min, max,allowNull)
function isLengthInRange(obj, min, max,allowNull){
 //obj.value=trim(obj.value);
 slen=getLength(obj.value);
 if(slen==0){
  if(!allowNull) return showMsg("����������ֵ.",obj);
  return true;
 }

 if (slen < min) return showMsg("���������� " + min + " ���ַ�.",obj);
 if (slen > max) return showMsg("��������� " + max + " ���ַ�.",obj);
 return true;
}


//������:jiarry@hotmail.com
//����:ȫѡ��ȡ��ȫѡform���checkbox������1��Ϊȫѡ,����ȡ��;
function checkall(obj,parm){//ȫѡorȡ��ȫѡ;
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

//������:jiarry@hotmail.com
//����:��ѡform���checkbox;
function reverseCheck(obj){
   for(var i=0;i<obj.elements.length;i++){
    if(obj.elements[i].tagName.toLowerCase()=="input" && obj.elements[i].type=="checkbox"){
      obj.elements[i].checked = obj.elements[i].checked ? false : true;
    }
  } 
}

//������:jiarry@hotmail.com
//����:�ж�form���Ԫ���Ƿ��Ѿ���child������ֵ;
//�������ƿ���ͨ��element��tagName,type,name�����ж�;
function isExistElement(child,obj){ 
   for(var i=0;i<obj.elements.length;i++){
    //if(obj.elements[i].tagName.toLowerCase()=="input" && obj.elements[i].type=="checkbox"){
       if(obj.elements[i].value==child)return true;
    //}
   }
   return false;
}

//������:jiarry@hotmail.com
//����:�����ڽ���ȫ��ļ��
//�������:yyObject:���object;mmObject:�µ�object;ddObject:�յ�object;
//�������:allowNull:true��������Ϊ��;false:����ѡ������
//�������:ture of false;
// b-16 checkValiDate(mmObject,ddObject,yyObject,allowNull)
function checkValidDate(yyObject,mmObject,ddObject,allowNull){
   if(allowNull){
    if(!(((!yyObject.options[0].selected)&&(!mmObject.options[0].selected)&&(!ddObject.options[0].selected)) || ((yyObject.options[0].selected)&&(mmObject.options[0].selected)&&(ddObject.options[0].selected))))
     return showMsg("���ڱ���ȫ��ѡ�����ȫ����ѡ��!",yyObject);
   }else{
    if(yyObject.options[0].selected){
     return showMsg("��ѡ�����ڵ���!",yyObject);
    }
    if(mmObject.options[0].selected){
     return showMsg("��ѡ�����ڵ���!",mmObject);
    }
    if(ddObject.options[0].selected){
     return showMsg("��ѡ�����ڵ���!",ddObject);
    }
   }
   
   if(!yyObject.options[0].selected){
    var my_year=yyObject[yyObject.selectedIndex].value;
    var my_month=mmObject[mmObject.selectedIndex].value;
    var my_day=ddObject[ddObject.selectedIndex].value;
    
    if(!ifAvildDate(my_year,my_month,my_day))
     return showMsg("ѡ������ڲ��Ϸ�!",ddObject);
   }
   return true;
}


//������:jiarry@hotmail.com
//����:�����ڽ���ȫ��ļ��
//�������:yyObject1:��ʼ���object;mmObject1:��ʼ�µ�object;ddObject1:��ʼ�յ�object;
//�������:allowNull1:��ʼ����true��������Ϊ��;false:����ѡ������
//�������:yyObject2:��ֹ���object;mmObject2:��ֹ�µ�object;ddObject2:��ֹ�յ�object;
//�������:allowNull2:��ֹ����true��������Ϊ��;false:����ѡ������
//�������:ture of false;
// b-17 checkValidDateRange(mmObject1,ddObject1,yyObject1,allowNull1,mmObject2,ddObject2,yyObject2,allowNull2)
function checkValidDateRange(yyObject1,mmObject1,ddObject1,allowNull1,yyObject2,mmObject2,ddObject2,allowNull2){
 if(!checkValidDate(mmObject1,ddObject1,yyObject1,allowNull1)) return false;
 if(!checkValidDate(mmObject2,ddObject2,yyObject2,allowNull2)) return false;
 
 if((!yyObject1.options[0].selected) && (!yyObject2.options[0].selected)){
  date1=new Date(yyObject1-1900,mmObject1-1,ddObject1);
  date2=new Date(yyObject2-1900,mmObject2-1,ddObject2);
  if(date1>date2){
   return showMsg("��ʼ���ڲ��ܴ��ڽ�ֹ����.",yyObject1);
  }
 }
 return true;
}


//������:jiarry@hotmail.com
//����:��ʾ��ʾ��ϢMsg,��꽹������Obj��,����false
//     ��Ҫ���ڼ���Ҫ�ֶ��Ƿ���ȷ
//ʾ��:showMsg("�û�������Ϊ��.",myform.username)
//�������:Msg(��ʾ��Ϣ) Obj(��꽹�����)
//�������:false
// b-18 showMsg(String, Object)
function showMsg(Msg, Obj){
 alert( Msg );
 Obj.focus();
 return false;
}

//������:jiarry@hotmail.com
//����:��ʾ��ʾ��ϢMsg,��꽹������Obj��
//     ��Ҫ���ڼ���Ҫ�ֶ��Ƿ���ȷ
//ʾ��:showMsg2("�û�������Ϊ��.",myform.username)
//�������:Msg(��ʾ��Ϣ) Obj(��꽹�����)
//�������:false
// b-19 showMsg2(String, Object)
function showMsg2(Msg, Obj){
 alert( Msg );
 Obj.focus();
 return;
}

/**
�����ǵ�����
*/
//������:jiarry@hotmail.com
//�����˺��ֵĳ����ж�
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

//������:jiarry@hotmail.com
//����:ȥ���ַ���ǰ��Ŀո񲢷���
//�������:inputStr(��������ַ���)
//�������:inputStr(�������ַ���)
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

//������:jiarry@hotmail.com
//����:ȥ���ַ���ǰ��Ŀո񲢷���
//�������:inputStr(��������ַ���)
//�������:inputStr(�������ַ���)
// c-2 string.trim();
String.prototype.trim = function()
{
return this.replace(/(^\s*)|(\s*$)/g, "");
}

//Trim,����String
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
        if (s.charAt(i) == ' ' || s.charAt(i) == '��')
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
         // if (s.charAt(i) == ' ' || s.charAt(i) == '��' || s.charAt(i)=='\r' || s.charAt(i)=='\n')

        if (s.charAt(i) == ' ' || s.charAt(i) == '��')
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
//������:jiarry@hotmail.com
//����:��form�����е�text�ı�����trim������
//�������:myform(form��)
//�������:��
// c-3 textTrim(form����)
function textsTrim(myform){
  for(var i=0;i<myform.elements.length;i++){
    var etype=myform.elements[i].type;
   if(etype = "text"){
     myform.elements[i].value=trim(myform.elements[i].value);
   }
  }
}


//������:jiarry@hotmail.com
//����:��formĳһinput���textarea�������copy��ճ����
//�������:myform(input��������)
//�������:��
function copyCode(obj) {
 var rng = document.body.createTextRange();
 rng.moveToElementText(obj);
 rng.scrollIntoView();
 rng.select();
 rng.execCommand("Copy");
 rng.collapse(false);
}

//������:jiarry@hotmail.com
//����:��HTMLҳ�淽ʽ����ĳһinput��textarea�������//firefox�¿��ܲ�֧��
//�������:myform(input��������)
//�������:��
function runCode(obj) {
 var winname = window.open('', "_blank", '');
 winname.document.open('text/html', 'replace');
 winname.document.writeln(obj.value);
 winname.document.close();
}

//������:jiarry@hotmail.com
//����:��formĳһinput���textarea����������Ϊ//firefox�¿��ܲ�֧��
//�������:myform(input��������)
//�������:��
function saveCode(obj) {
 var winname = window.open('', '_blank', 'top=10000');
 winname.document.open('text/html', 'replace');
 winname.document.writeln(obj.value);
 winname.document.execCommand('saveas','','code.htm');
 winname.close();
}


//������:jiarry@hotmail.com
//����:�൱��java���replaceAll�������滻�ַ���İ�����ĳ��ȫ���ַ���
//�������:���滻���ַ�����Ҫ�滻���ַ���
//�������:��
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
//������ʽȫ���滻�ַ�����tempStr.replaceAll()�൱;

//������:jiarry@hotmail.com
//����:��formĳһtextarea����������br���;���س��滻��<br />֮��
//�������:myform(input��������)
//�������:��
function cleanBR() {
 var area = document.getElementsByTagName('TEXTAREA');
 for (var i=0; i<area.length; i++) {
  area[i].value = area[i].value.replace(/<br \/>/ig, '');
  area[i].value = area[i].value.replace(/(\xA0\x20){4}/ig, '\t');
  area[i].value = area[i].value.replace(/\xA0\x20\xA0/ig, '   ');
  area[i].value = area[i].value.replace(/\xA0\xA0/ig, '  ');
 }
}

//������:jiarry@hotmail.com
//����:������relΪblank��������window.open()�ķ�ʽ�򿪡�
//����<a href="#" rel="blank"></a>
//�������:myform(input��������)
//�������:��
function tBlank(){
 var aLinks=document.getElementsByTagName("a");
 for (var i=0;i<aLinks.length;i++){
   if(aLinks[i].getAttribute("rel")=="blank"){
   //alert(aLinks[i].getAttribute("href"));
   aLinks[i].target="_blank";
   }
 }
}


//������:jiarry@hotmail.com
//����:������������
//����<a href="#" rel="nubmer"></a>
//�������:myform(input��������)
//�������:��
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

//������:jiarry@hotmail.com
//����:��֤�Ƿ�Ϊ������isNaN()�෴
//�������:����true����false
function isNum(obj){
    for(var i=0; i < obj.value.length;i++){
     if(obj.value.charAt(i)<"0" || obj.value.charAt(i)>"9"){
     return false; 
     }
    }     
  return true; 
}

//������:jiarry@hotmail.com
//������ʽȥ�����ҿո�
//����ȥ������ַ���
function trim(s) {
 return s.replace( /^\s*/, "" ).replace( /\s*$/, "" );
}

//������:jiarry@hotmail.com
//������ʽEmail����֤
//��֤�Ƿ���@.��
function validateEmail(email){
   var myReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
    if(myReg.test(email)==false){
       return false;
   }
   return true;
}

//������:jiarry@hotmail.com
//������ʽ�绰����֤
//��֤�Ƿ���@.��
function validatePhone(phone){
   var myReg = /(^[0-9]{3,4}\-[0-9]{5,8}$)|(^[0-9]{5,12}$)|(^\([0-9]{3,4}\)[0-9]{5,8}$)|(^0{0,1}13[0-9]{9}$)/;
    if(myReg.test(phone)==false){
       return false;
   }
   return true;
}

//������:jiarry@hotmail.com
//��֤�ַ������Ƿ񳬳�ĳ��ֵ
//����true��false
function validateMaxLength(v, maxlen){
   if (trim(v).length > maxlen){
     return false;
   }
   return true;
}

/*
���18λ���֤���һλ��У�����Ƿ���ȷ
Author:lichunping@baidu.com;jarryli@gmail.com;
homepage:http://jiarry.126.com;
bolg:http://jiarry.blogchina.com;
1����(a[i]*W[i]) mod 11 ( i = 2, 3, ..., 18 ) (1) ;
2����Ȩ���ӷֱ�Ϊ Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ��
3����ǰ17λ����ֱ���Ը��Եļ�Ȩ����,����ͳ���11,��ȡ������
4����������Ӧ�·���У����
5��Y����:  0 1 2 3 4 5 6 7 8 9 10 ;
6��У����: 1 0 X 9 8 7 6 5 4 3 2 ;
*/
var ai=new Array();//�û������֤��������;
var wi=[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2];//17λ����Ӧ�ļ�Ȩ���ӣ���������;
var yi=[0,1,2,3,4,5,6,7,8,9,10];//����;
var vi=["1","0","X","9","8","7","6","5","4","3","2"];//������Ӧ��У����;
var r;//���յ�У����;
var sum;//��
var y;//����

//�÷�:getVerifyCode(this.form.number)
//������ȷ����֤��;
function getVerifyCode(num){//17λ�ĺ�����������Է��ؼ�����;
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
   alert("������벻��ȷ");return;
   }
  for(var i=0;i<vl;i++){
        ai[i]=v.charAt(i); 
        sum+=ai[i]*wi[i];//���֤����ֱ���Լ�Ȩ���������;  
  }
   y=sum%11;     
   r=vi[y];//У�����еĵ�y����ʱУ����;
   return r;//����18λ���֤��У����;
}

//�÷�:checkIdVerifyCode(this.form.number)
//����true��false;
function checkIdVerifyCode(o){
 var v = o;
 if( typeof(o) != "undefined" && typeof(o) != "string" && o.type == "text" )v=o.value;
  var num18 = v.substr(17,1);
/*//������֤��һ����Ҫ;
 var num17 = v.substring(0,17);
 if(v.length != 18){
    //alert("������18λ���֤����");
 return false;
  }else if(!isNaN(v)){
    //alert("18λȫ������");
  }else if( num18.toUpperCase() == "X" && !isNaN( num17 )){
    //alert("��18λ������X��ǰ17λ������");
  }else{
    //alert("��������Ч�����֤����");
 return false;
 }*/
 if(getVerifyCode(v) == num18)
   return true;
 else
  return false;
}

// get absolute Left position
//������:jiarry@hotmail.com
//���ض���λ�ڴ��ڵľ�����߾���
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
//������:jiarry@hotmail.com
//���ض���λ�ڴ��ڵľ����ϱ߾���
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
//������:jiarry@hotmail.com
//�����ɫֵ�Ƿ�Ϊ��;
//�÷�: if( isAllNum16("#000000") )alert("�Ϸ���ɫ");
function isNum16(ch)
{
  if (ch >= '0' && ch <= '9')return true;
  if (ch >= 'A' && ch <= 'F')return true;
  if (ch >= 'a' && ch <= 'f')return true;
  return false;
}
function isAllNum16(str1)
{//�ж���ɫֵ������һ���ַ�#�����һ��ֵ�Ƿ���ڵ�a,A,0,С�ڵ���f,F,9�����򱨴�
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
{//ʱ�䣬������·���ȿ��Ը�����Ҫ���趨
    //var path="/";
 //var domain="baidu.com"; 
 var expdate = new Date();
 expdate.setTime(expdate.getTime() + (365*24*120));//��Ϊ1��ʱЧ
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
