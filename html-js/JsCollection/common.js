/*
       ���֣�Common.js
       ���ܣ�ͨ��JavaScript�ű�������
       ������
                     1.Trim(str)����ȥ���ַ������ߵĿո�
                     2.XMLEncode(str)�������ַ�������XML����
            3.ShowLabel(str,str)���������ʾ���ܣ���ʾ�ַ�����ʾ�ַ���
                     4.IsEmpty(obj)������֤������Ƿ�Ϊ��
                     5.IsInt(objStr,sign,zero)������֤�Ƿ�Ϊ����
                     6.IsFloat(objStr,sign,zero)������֤�Ƿ�Ϊ������
                     7.IsEnLetter(objStr,size)������֤�Ƿ�Ϊ26����ĸ
 
    ���ߣ�����
    ���ڣ�2004/04/14
*/
 
/*
==================================================================
�ַ�������
Trim(string):ȥ���ַ������ߵĿո�
==================================================================
*/
 
/*
==================================================================
LTrim(string):ȥ����ߵĿո�
==================================================================
*/
function LTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    
    if (whitespace.indexOf(s.charAt(0)) != -1)
    {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
        {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}
 
/*
==================================================================
RTrim(string):ȥ���ұߵĿո�
==================================================================
*/
function RTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
 
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
    {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
        {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}
 
/*
==================================================================
Trim(string):ȥ��ǰ��ո�
==================================================================
*/
function Trim(str)
{
    return RTrim(LTrim(str));
}
 
 
 
/*
================================================================================
XMLEncode(string):���ַ�������XML����
================================================================================
*/
function XMLEncode(str)
{
       str=Trim(str);
       str=str.replace("&","&amp;");
       str=str.replace("<","&lt;");
       str=str.replace(">","&gt;");
       str=str.replace("'","&apos;");
       str=str.replace("\"","&quot;");
       return str;
}
 
/*
================================================================================
��֤�ຯ��
================================================================================
*/
 
function IsEmpty(obj)
{
    obj=document.getElementsByName(obj).item(0);
    if(Trim(obj.value)=="")
    {
        alert("�ֶβ���Ϊ�ա�");        
        if(obj.disabled==false && obj.readOnly==false)
        {
            obj.focus();
        }
    }
}
 
/*
IsInt(string,string,int or string):(�����ַ���,+ or - or empty,empty or 0)
���ܣ��ж��Ƿ�Ϊ����������������������������+0��������+0
*/
function IsInt(objStr,sign,zero)
{
    var reg;    
    var bolzero;    
    
    if(Trim(objStr)=="")
    {
        return false;
    }
    else
    {
        objStr=objStr.toString();
    }    
    
    if((sign==null)||(Trim(sign)==""))
    {
        sign="+-";
    }
    
    if((zero==null)||(Trim(zero)==""))
    {
        bolzero=false;
    }
    else
    {
        zero=zero.toString();
        if(zero=="0")
        {
            bolzero=true;
        }
        else
        {
            alert("����Ƿ����0������ֻ��Ϊ(�ա�0)");
        }
    }
    
    switch(sign)
    {
        case "+-":
            //����
            reg=/(^-?|^\+?)\d+$/;            
            break;
        case "+": 
            if(!bolzero)           
            {
                //������
                reg=/^\+?[0-9]*[1-9][0-9]*$/;
            }
            else
            {
                //������+0
                //reg=/^\+?\d+$/;
                reg=/^\+?[0-9]*[0-9][0-9]*$/;
            }
            break;
        case "-":
            if(!bolzero)
            {
                //������
                reg=/^-[0-9]*[1-9][0-9]*$/;
            }
            else
            {
                //������+0
                //reg=/^-\d+$/;
                reg=/^-[0-9]*[0-9][0-9]*$/;
            }            
            break;
        default:
            alert("�����Ų�����ֻ��Ϊ(�ա�+��-)");
            return false;
            break;
    }
    
    var r=objStr.match(reg);
    if(r==null)
    {
        return false;
    }
    else
    {        
        return true;     
    }
}
 
/*
IsFloat(string,string,int or string):(�����ַ���,+ or - or empty,empty or 0)
���ܣ��ж��Ƿ�Ϊ������������������������������������+0����������+0
*/
function IsFloat(objStr,sign,zero)
{
    var reg;    
    var bolzero;    
    
    if(Trim(objStr)=="")
    {
        return false;
    }
    else
    {
        objStr=objStr.toString();
    }    
    
    if((sign==null)||(Trim(sign)==""))
    {
        sign="+-";
    }
    
    if((zero==null)||(Trim(zero)==""))
    {
        bolzero=false;
    }
    else
    {
        zero=zero.toString();
        if(zero=="0")
        {
            bolzero=true;
        }
        else
        {
            alert("����Ƿ����0������ֻ��Ϊ(�ա�0)");
        }
    }
    
    switch(sign)
    {
        case "+-":
            //������
            reg=/^((-?|\+?)\d+)(\.\d+)?$/;
            break;
        case "+": 
            if(!bolzero)           
            {
                //��������
                reg=/^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //��������+0
                reg=/^\+?\d+(\.\d+)?$/;
            }
            break;
        case "-":
            if(!bolzero)
            {
                //��������
                reg=/^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //��������+0
                reg=/^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
            }            
            break;
        default:
            alert("�����Ų�����ֻ��Ϊ(�ա�+��-)");
            return false;
            break;
    }
    
    var r=objStr.match(reg);
    if(r==null)
    {
        return false;
    }
    else
    {        
        return true;     
    }
}
 
/*

IsEnLetter(string,string):�����ַ�������Сд(UL,U,L or ul,u,l)

*/

function IsEnLetter(objStr,size)

{

    var reg;

    

    if(Trim(objStr)=="")

    {

        return false;

    }

    else

    {

        objStr=objStr.toString();

    }    

    

    if((size==null)||(Trim(size)==""))

    {

        size="UL";

    }

    else

    {

        size=size.toUpperCase();

    }

    

    switch(size)

    {

        case "UL":

            //��Сд

            reg=/^[A-Za-z]+$/;

            break;

        case "U": 

            //��д

            reg=/^[A-Z]+$/;

            break;

        case "L":

            //Сд

            reg=/^[a-z]+$/;

            break;

        default:

            alert("����Сд������ֻ��Ϊ(�ա�UL��U��L)");

            return false;

            break;

    }

    

    var r=objStr.match(reg);

    if(r==null)

    {

        return false;

    }

    else

    {        

        return true;     

    }

}

 

 

/*

================================================================================

���ܣ����С��ʾ

���ߣ�����

���ڣ�2004/04/15

================================================================================

*/

 

//�������������Ĭ��ֵ

var LabelFontFace="����,arial,Verdana";

var LabelFontColor="#000000";

var LabelFontSize="9pt";

var LabelFontStyle="Font.PLAIN";

var LabelBorderColor="#000000";

var LabelBackColor="#FFFFE1";
LabelBackColor="#FFFF00";
 

//���ø�������

function SetLabelFontFace(obj)

{

       obj=Trim(obj);

       if(obj==null || obj=="")

       {

              obj="����,arial,Verdana";

       }

       LabelFontFace=obj;

}

 

function SetLabelFontColor(obj)

{

    obj=Trim(obj);

       if(obj==null || obj=="")

       {

              obj="#000000";

       }

       LabelFontColor=obj;

}

 

function SetLabelFontSize(obj)

{

    obj=Trim(obj);

       if(obj==null || obj=="")

       {

              obj="9pt";

       }

       LabelFontSize=obj;

}

 

function SetLabelFontStyle(obj)

{

    obj=Trim(obj);

       if(obj==null || obj=="")

       {

              obj="Font.PLAIN";

       }

       LabelFontStyle=obj;

}

 

function SetLabelBorderColor(obj)

{

    obj=Trim(obj);

    if(obj==null || obj=="")

    {

        obj="#000000";

    }

    LabelBorderColor=obj;

}

 

function SetLabelBackColor(obj)

{

    obj=Trim(obj);

    if(obj==null || obj=="")

    {

        obj="#FFFFE1";

    }

    LabelBackColor=obj;

}

 

//�ϳ�������ʽ

function SetTextStyle(str)

{

    var strRet="";

    

    var strStyle="";

    

    strStyle="font-family:"+LabelFontFace+";";

    strStyle+="color:"+LabelFontColor+";";

    strStyle+="font-size:"+LabelFontSize+";";

    

    switch(LabelFontStyle.toLowerCase())

    {

        case "font.plain":

            strStyle+="font-weight: normal;";

            strStyle+="font-style: normal;";

            break;

        case "font.bold":

            strStyle+="font-weight: bold;";

            strStyle+="font-style: normal;";

            break;

        case "font.italic":

            strStyle+="font-weight: normal;";

            strStyle+="font-style: italic;";

            break;

        case "font.italicbold":

        case "font.bolditalic":

            strStyle+="font-weight: bold;";

            strStyle+="font-style: italic;";

            break;

        default:

            strStyle+="font-weight: bold;";

            strStyle+="font-style: italic;";

            break;

    }

    

    strRet="<font style='"+strStyle+"'>";

    strRet+="&nbsp;"+str+"&nbsp;";

    strRet+="</font>";

    

    return strRet;

}

 

//�ϳɱ����ʽ

function SetTableStyle()

{

    var strRet="";

    

    strRet+="border-right: "+LabelBorderColor+" 1px solid;";

    strRet+="border-top: "+LabelBorderColor+" 1px solid;";

    strRet+="border-left: "+LabelBorderColor+" 1px solid;";

    strRet+="border-bottom: "+LabelBorderColor+" 1px solid;";

    strRet+="background-color:"+LabelBackColor;    

    

    return strRet;

}

 

//��ʾ��ʾ

function ShowNote(str)

{

       var strHtml;

       

       strHtml="";

       strHtml+="<table height=1px width=200px border='0'cellspacing='0' cellpadding='0' style='" + SetTableStyle() + "'>";

       strHtml+="<tr>";

       strHtml+="<td>"+SetTextStyle(str)+"</td>";

       strHtml+="</tr>";

       strHtml+="</table>";                           

       

       if (document.all&&document.readyState=="complete")

       {                                        

              document.all.div_Note.innerHTML=strHtml;

              document.all.div_Note.style.pixelLeft=event.clientX+document.body.scrollLeft+10

              document.all.div_Note.style.pixelTop=event.clientY+document.body.scrollTop+10

              document.all.div_Note.style.visibility="visible"

       }     

}

 

//������ʾ

function HideNote()

{

       if (document.all)

       {

              document.all.div_Note.style.visibility="hidden";

       }

       else

       {

              if (document.layers)

              {

                     clearInterval(currentscroll)

                     document.div_Note.visibility="hidden";

              }

       }                                 

}

 

//��ʼ��

function Init()

{

    window.document.write("<div id=\"div_Note\" style=\"VISIBILITY:hidden; POSITION:absolute; HEIGHT:13px;z-index:1\"></div>");

}

Init();

 

//������ʾ�ַ�

function ShowLabel(text,note,bclick)

{

       if(bclick!=null)

       {

              return "<a href=\"#\" onMouseOver=\"ShowNote('" + note + "')\" onMouseOut=\"HideNote()\" onClick=\"JavaScript:DoSomeThing(this);\">" + text + "</a>";

       }

       else

       {

           return "<a href=\"#\" onMouseOver=\"ShowNote('" + note + "')\" onMouseOut=\"HideNote()\">" + text + "</a>";

       }

}

