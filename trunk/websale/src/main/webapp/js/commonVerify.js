/*================================================================
commonVerify.js
================================================================*/
/* String.prototype.trim() */
String.prototype.trim = function(){return this.replace(/^\s+|\s+$/g,'');};
/* String.prototype.bytelength() */
String.prototype.bytelength = function(){return this.replace(/[^\x00-\xff]/g,"**").length;};
/* check(fm) */
function check(fm){
	objs = fm.elements;
	for(var i=0;i<objs.length;i++){
		if(typeof(objs[i].verify)!="undefined"){
			if(objs[i].verify.indexOf("notnull")>=0){
				if(isEmpty(objs[i].value.trim())){
					alert(objs[i].vname + "输入不能为空!");
					objs[i].focus();
					return false;
				}
			}
			if(objs[i].verify.indexOf("num")>=0){
				if(!isNumber(objs[i].value.trim())){
					alert(objs[i].vname + "输入必须为整数!");
					objs[i].focus();
					return false;
				}
			}
			if(objs[i].verify.indexOf("double")>=0){
				if(!isDouble(objs[i].value.trim())){
					alert(objs[i].vname + "输入必须为数字!");
					objs[i].focus();
					return false;
				}
			}
			if(objs[i].verify.indexOf("email")>=0){
				if(!isEmail(objs[i].value.trim())){
					alert(objs[i].vname + "Email格式不对!");
					objs[i].focus();
					return false;
				}
			}
		}
	}
	return true;
}
/* isEmpty(str) */
function isEmpty(str) {
	return str==null||str.trim()==""?true:false;
}
/* isNumber(str) var pattern_number = /^-?\d+$/gi; */
function isNumber(str){
    if (isEmpty(str)) return true;
    return (/^-?\d+$/gi.test(str))?true:false;
}
/* isDouble(str) var pattern_double = /^(-?\d+)(\.\d+)?$/gi; */
function isDouble(str){
    if (isEmpty(str)) return true;
    return (/^(-?\d+)(\.\d+)?$/gi.test(str))?true:false;
}
/* isEmail(str) var pattern_email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/gi; */
function isEmail(str){
    if (isEmpty(str)) return true;
    return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/gi.test(str))?true:false;
}
/* isChinese(str) var pattern_chinese = /[\u4E00-\u9FA5]/gi; */
function isChinese(str){
    if (isEmpty(str)) return true;
    return (/[\u4E00-\u9FA5]/gi.test(str))?true:false;
}
/** isDate(str) var pattern_date = /^(\d{2}|\d{4})-((0([1-9]{1}))|(1[1|2]))-((0([1-9]{1}))|([1-2]([0-9]{1}))|(3[0|1]))$/gi; */
function isDate(str){
    if (isEmpty(str)) return true;
    return (/^(\d{2}|\d{4})-((0([1-9]{1}))|(1[1|2]))-((0([1-9]{1}))|([1-2]([0-9]{1}))|(3[0|1]))$/gi.test(str))?true:false;
}

/*
==================================================================
removeLeadingAndTrailingChar
==================================================================
*/
function removeLeadingAndTrailingChar (inputString, removeChar) {
	var returnString = inputString; 
	if (removeChar.length) { 
		while(''+returnString.charAt(0)==removeChar) { 						 
	   		returnString=returnString.substring(1,returnString.length); 
		}
		while(''+returnString.charAt(returnString.length-1)==removeChar) { 
			returnString=returnString.substring(0,returnString.length-1); 
		}
	} 
	return returnString;
}
/*
==================================================================
compareDate
==================================================================
*/
function compareDate(DateOne,DateTwo){
 	var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ("-"));
 	var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ("-")+1);
 	var OneYear = DateOne.substring(0,DateOne.indexOf ("-"));
 	var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ("-"));
 	var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ("-")+1);
 	var TwoYear = DateTwo.substring(0,DateTwo.indexOf ("-"));
 	if (Date.parse(OneMonth+"/"+OneDay+"/"+OneYear) > Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear)){
  		return true;
 	}
 	else{
  		return false;
 	}
}
/*
==================================================================
isDate(theStr)
==================================================================
*/
function isDateStr(theStr) {
    var Find1st = theStr.indexOf("-");
    var Find2st = theStr.lastIndexOf("-");
    var YearPart, MonthPart, DayPart, isLeapYear;
    var MonthFormat = ".1.01.3.03.5.05.7.07.8.08.10.12.";
    isLeapYear = false;
    if (Find1st == Find2st) {
        return false;
    } else {
        YearPart = theStr.substring(0, Find1st);
        MonthPart = theStr.substring(Find1st + 1, Find2st);
        DayPart = theStr.substring(Find2st + 1, theStr.length);
        if ((MonthPart.length == 2) && (MonthPart < 10)) {
            MonthPart = MonthPart.substring(2, 1);
        }
        if ((DayPart.length == 2) && (DayPart < 10)) {
            DayPart = DayPart.substring(2, 1);
        }
        if (!(isInt(YearPart)) | !(isInt(MonthPart)) | !(isInt(DayPart))) {
            return false;
        }
        if (YearPart.length != 4) {
            return false;
        } else {
            if ((parseInt(YearPart) % 400) == 0 | (parseInt(YearPart) % 100) == 0) {
                isLeapYear = true;
            }
        }
        if (MonthPart.length < 1 | MonthPart.length > 2 | parseInt(MonthPart) < 1 | parseInt(MonthPart) > 12) {
            return false;
        }
        if (DayPart.length < 1 | DayPart.length > 2) {
            return false;
        } else {
            if (parseInt(MonthPart) == 2) {
                if (!(isLeapYear)) {
                    if (parseInt(DayPart) < 1 | parseInt(DayPart) > 28) {
                        return false;
                    }
                } else {
                    if (parseInt(DayPart) < 1 | parseInt(DayPart) > 29) {
                        return false;
                    }
                }
            } else {
                if (MonthFormat.indexOf(MonthPart) == -1) {
                    if (parseInt(DayPart) < 1 | parseInt(DayPart) > 30) {
                        return false;
                    }
                } else {
                    if (parseInt(DayPart) < 1 | parseInt(DayPart) > 31) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
}
/** 校验日期 isDateDep(2002-01-31) */
function isDateDep(str){
var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
if(r==null)return false; var d = new Date(r[1], r[3]-1, r[4]);
return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}
/** 校验日期加时间 isTime("2002-1-31 12:34:56") */
function isTime(str){
var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
if(r==null)return false; var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
}