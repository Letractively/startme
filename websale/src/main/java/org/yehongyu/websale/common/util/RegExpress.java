/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

/**
 * 【类说明】
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 12:15:46 PM
 */
public interface RegExpress {
	/**
	\ 将下一个字符标记为一个特殊字符、或一个原义字符、或一个后向引用、或一个八进制转义符。
	^ 匹配输入字符串的开始位置。如果设置了 RegExp 对象的Multiline 属性，\^ 也匹配 '\n' 或 '\r' 之后的位置。
	$ 匹配输入字符串的结束位置。如果设置了 RegExp 对象的Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置。
	\* 匹配前面的子表达式零次或多次。
	+ 匹配前面的子表达式一次或多次。\+ 等价于 {1,}。
	? 匹配前面的子表达式零次或一次。? 等价于 {0,1}。

	{n} n 是一个非负整数，匹配确定的n 次。
	{n,} n 是一个非负整数，至少匹配n 次。
	{n,m} m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。在逗号和两个数之间不能有空格。

	? 当该字符紧跟在任何一个其他限制符 (*, \+, ?, {n}, {n,}, {n,m}) 后面时，匹配模式是非贪婪的。非贪婪模式尽可能少的匹配所搜索的字符串，而默认的贪婪模式则尽可能多的匹配所搜索的字符串。
	. 匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用象 '\[.\n\]' 的模式。

	(pattern) 匹配pattern 并获取这一匹配。
	(?:pattern) 匹配pattern 但不获取匹配结果，也就是说这是一个非获取匹配，不进行存储供以后使用。
	(?=pattern) 正向预查，在任何匹配 pattern 的字符串开始处匹配查找字符串。这是一个非获取匹配，也就是说，该匹配不需要获取供以后使用。
	(?\!pattern) 负向预查，与(?=pattern)作用相反

	x\|y 匹配 x 或 y。
	\[xyz\] 字符集合。
	\[^xyz\] 负值字符集合。
	\[a-z\] 字符范围，匹配指定范围内的任意字符。
	\[^a-z\] 负值字符范围，匹配任何不在指定范围内的任意字符。

	\b 匹配一个单词边界，也就是指单词和空格间的位置。
	\B 匹配非单词边界。
	\cx 匹配由x指明的控制字符。
	\d 匹配一个数字字符。等价于 \[0-9\]。
	\D 匹配一个非数字字符。等价于 \[^0-9\]。
	\f 匹配一个换页符。等价于 \x0c 和 \cL。
	\n 匹配一个换行符。等价于 \x0a 和 \cJ。
	\r 匹配一个回车符。等价于 \x0d 和 \cM。
	\s 匹配任何空白字符，包括空格、制表符、换页符等等。等价于\[ \f\n\r\t\v\]。
	\S 匹配任何非空白字符。等价于 \[\^ \f\n\r\t\v\]。
	\t 匹配一个制表符。等价于 \x09 和 \cI。
	\v 匹配一个垂直制表符。等价于 \x0b 和 \cK。
	\w 匹配包括下划线的任何单词字符。等价于'\[A-Za-z0-9_\]'。
	\W 匹配任何非单词字符。等价于 '\[^A-Za-z0-9_\]'。

	\xn 匹配 n，其中 n 为十六进制转义值。十六进制转义值必须为确定的两个数字长。
	\num 匹配 num，其中num是一个正整数。对所获取的匹配的引用。
	\n 标识一个八进制转义值或一个后向引用。如果 \n 之前至少 n 个获取的子表达式，则 n 为后向引用。否则，如果 n 为八进制数字 (0-7)，则 n 为一个八进制转义值。
	\nm 标识一个八进制转义值或一个后向引用。如果 \nm 之前至少有is preceded by at least nm 个获取得子表达式，则 nm 为后向引用。如果 \nm 之前至少有 n 个获取，则 n 为一个后跟文字 m 的后向引用。如果前面的条件都不满足，若 n 和 m 均为八进制数字 (0-7)，则 \nm 将匹配八进制转义值 nm。
	\nml 如果 n 为八进制数字 (0-3)，且 m 和 l 均为八进制数字 (0-7)，则匹配八进制转义值 nml。
	\\un 匹配 n，其中 n 是一个用四个十六进制数字表示的Unicode字符。

	匹配中文字符的正则表达式： \[u4e00-u9fa5\]
	匹配双字节字符(包括汉字在内)：\[^x00-xff\]
	匹配空行的正则表达式：n\[s\| \]*r
	匹配HTML标记的正则表达式：/<(.*)>.*</1>\|<(.*) />/
	匹配首尾空格的正则表达式：(^s*)\|(s*$)
	匹配Email地址的正则表达式：w+(\[-+.\]w+)*@w+(\[-.\]w+)*.w+(\[-.\]w+)\*
	匹配网址URL的正则表达式：http://(\[w-\]+.)+\[w-\]+(/\[w\- ./?%&=\]*)?
	*/
	
	/** 匹配帐号是否合法(字母开头，允许4-16字节，允许字母数字下划线) */
	public static final String PATTERN_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{3,15}$";
	/** 匹配Email地址 */
	public static final String PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)\\*$";
	/** 匹配国内电话号码(固定电话) */
	public static final String PATTERN_PHONE = "^(((\\d{3}|\\d{4})-(\\d{7}|\\d{8}))|((\\d{3}|\\d{4})-(\\d{7}|\\d{8})-(\\d{4})))$";
	/** 匹配国内手机号码 */
	public static final String PATTERN_MOBILE = "^1[3|5]\\d{9}$";
	/** 匹配特殊字符 */ 
	public static final String PATTERN_INVALIDINPUT = "[\\p{Space}　\\p{Punct}_]";
	
	/** 匹配中文字符 */ 
	public static final String PATTERN_CHINESE = "[\u4e00-\u9fa5]";
	/** 匹配双字节字符(包括汉字在内) */
	public static final String PATTERN_DOUBLEBYTE = "[^\\x00-\\xff]";
	/** 匹配空行 */
	public static final String PATTERN_SPACELINE = "\n[\\s| ]*\r";
	/** 匹配HTML标记 */
	public static final String PATTERN_HTMLTAG = "<(.*)>.*</\\1>|<(.*)\\/>";
	/** 匹配首尾空格 */
	public static final String PATTERN_SPACE_HEAD_TAIL = "(^\\s*)|(\\s*$)";
	/** 匹配网址URL */
	public static final String PATTERN_WEBADDRESS = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	/** 匹配国内身份证号码 */
	public static final String PATTERN_IDENTITYCARD = "\\d{14}[0-9X]|\\d{17}[0-9X]";
	/** 匹配邮政编码 */
	public static final String PATTERN_POSTCODE = "^(\\d{6})+$";
	/** 匹配腾讯QQ号 */
	public static final String PATTERN_QQ = "^[1-9]*[1-9][0-9]*$";
	/** 匹配IP地址 */
	public static final String PATTERN_IP = "^(\\d{1,2}|1\\d\\d|2[0-4]d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(d{1,2}|1\\d\\d|2[0-4]d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]d|25[0-5])$";

	
	/** 非负整数（正整数 + 0） */
	public static final String PATTERN_INTEGER_POSITIVE_ZERO = "^\\d+$";
	/** 正整数 */
	public static final String PATTERN_INTEGER_POSITIVE = "^[0-9]*[1-9][0-9]*$";
	/** 非正整数（负整数 + 0） */
	public static final String PATTERN_INTEGER_NEGATIVE_ZERO = "^((-\\d+)|(0+))$";
	/** 负整数 */
	public static final String PATTERN_INTEGER_NEGATIVE = "^-[0-9]*[1-9][0-9]*$";
	/** 整数 */
	public static final String PATTERN_INTEGER = "^-?\\d+$";
	/** 非负浮点数（正浮点数 + 0）*/
	public static final String PATTERN_DOUBLE_POSITIVE_ZERO = "^\\d+(\\.\\d+)?$";
	/** 正浮点数 */
	public static final String PATTERN_DOUBLE_POSITIVE = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
	/** 非正浮点数（负浮点数 + 0） */
	public static final String PATTERN_DOUBLE_NEGATIVE_ZERO = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
	/** 负浮点数 */
	public static final String PATTERN_DOUBLE_NEGATIVE = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
	/** 浮点数 */
	public static final String PATTERN_DOUBLE = "^(-?\\d+)(\\.\\d+)?$";
	/** 由26个英文字母组成的字符串 */
	public static final String PATTERN_LETTER = "^[A-Za-z]+$";
	/** 由26个英文字母的大写组成的字符串 */
	public static final String PATTERN_LETTER_UPPER = "^[A-Z]+$";
	/** 由26个英文字母的小写组成的字符串 */
	public static final String PATTERN_LETTER_LOWER = "^[a-z]+$";
	/** 由数字和26个英文字母组成的字符串 */
	public static final String PATTERN_LETTER_NUMBER = "^[A-Za-z0-9]+$";
	/** 由数字、26个英文字母或者下划线组成的字符串 */
	public static final String PATTERN_LETTER_NUMBER_UNDELINE = "^\\w+$";
	/** 由年-月-日组成的字符串,基本上把闰年和2月等的情况都考虑进去了 */
	public static final String PATTERN_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|"
                + "[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])"
                + "-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]"
                + "|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])"
                + "|((16|[2468][048]|[3579][26])00))-0?2-29-))$";

}
