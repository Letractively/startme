/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

/**
 * ����˵����
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 12:15:46 PM
 */
public interface RegExpress {
	/**
	\ ����һ���ַ����Ϊһ�������ַ�����һ��ԭ���ַ�����һ���������á���һ���˽���ת�����
	^ ƥ�������ַ����Ŀ�ʼλ�á���������� RegExp �����Multiline ���ԣ�\^ Ҳƥ�� '\n' �� '\r' ֮���λ�á�
	$ ƥ�������ַ����Ľ���λ�á���������� RegExp �����Multiline ���ԣ�$ Ҳƥ�� '\n' �� '\r' ֮ǰ��λ�á�
	\* ƥ��ǰ����ӱ��ʽ��λ��Ρ�
	+ ƥ��ǰ����ӱ��ʽһ�λ��Ρ�\+ �ȼ��� {1,}��
	? ƥ��ǰ����ӱ��ʽ��λ�һ�Ρ�? �ȼ��� {0,1}��

	{n} n ��һ���Ǹ�������ƥ��ȷ����n �Ρ�
	{n,} n ��һ���Ǹ�����������ƥ��n �Ρ�
	{n,m} m �� n ��Ϊ�Ǹ�����������n <= m������ƥ�� n �������ƥ�� m �Ρ��ڶ��ź�������֮�䲻���пո�

	? �����ַ��������κ�һ���������Ʒ� (*, \+, ?, {n}, {n,}, {n,m}) ����ʱ��ƥ��ģʽ�Ƿ�̰���ġ���̰��ģʽ�������ٵ�ƥ�����������ַ�������Ĭ�ϵ�̰��ģʽ�򾡿��ܶ��ƥ�����������ַ�����
	. ƥ��� "\n" ֮����κε����ַ���Ҫƥ����� '\n' ���ڵ��κ��ַ�����ʹ���� '\[.\n\]' ��ģʽ��

	(pattern) ƥ��pattern ����ȡ��һƥ�䡣
	(?:pattern) ƥ��pattern ������ȡƥ������Ҳ����˵����һ���ǻ�ȡƥ�䣬�����д洢���Ժ�ʹ�á�
	(?=pattern) ����Ԥ�飬���κ�ƥ�� pattern ���ַ�����ʼ��ƥ������ַ���������һ���ǻ�ȡƥ�䣬Ҳ����˵����ƥ�䲻��Ҫ��ȡ���Ժ�ʹ�á�
	(?\!pattern) ����Ԥ�飬��(?=pattern)�����෴

	x\|y ƥ�� x �� y��
	\[xyz\] �ַ����ϡ�
	\[^xyz\] ��ֵ�ַ����ϡ�
	\[a-z\] �ַ���Χ��ƥ��ָ����Χ�ڵ������ַ���
	\[^a-z\] ��ֵ�ַ���Χ��ƥ���κβ���ָ����Χ�ڵ������ַ���

	\b ƥ��һ�����ʱ߽磬Ҳ����ָ���ʺͿո���λ�á�
	\B ƥ��ǵ��ʱ߽硣
	\cx ƥ����xָ���Ŀ����ַ���
	\d ƥ��һ�������ַ����ȼ��� \[0-9\]��
	\D ƥ��һ���������ַ����ȼ��� \[^0-9\]��
	\f ƥ��һ����ҳ�����ȼ��� \x0c �� \cL��
	\n ƥ��һ�����з����ȼ��� \x0a �� \cJ��
	\r ƥ��һ���س������ȼ��� \x0d �� \cM��
	\s ƥ���κοհ��ַ��������ո��Ʊ������ҳ���ȵȡ��ȼ���\[ \f\n\r\t\v\]��
	\S ƥ���κηǿհ��ַ����ȼ��� \[\^ \f\n\r\t\v\]��
	\t ƥ��һ���Ʊ�����ȼ��� \x09 �� \cI��
	\v ƥ��һ����ֱ�Ʊ�����ȼ��� \x0b �� \cK��
	\w ƥ������»��ߵ��κε����ַ����ȼ���'\[A-Za-z0-9_\]'��
	\W ƥ���κηǵ����ַ����ȼ��� '\[^A-Za-z0-9_\]'��

	\xn ƥ�� n������ n Ϊʮ������ת��ֵ��ʮ������ת��ֵ����Ϊȷ�����������ֳ���
	\num ƥ�� num������num��һ����������������ȡ��ƥ������á�
	\n ��ʶһ���˽���ת��ֵ��һ���������á���� \n ֮ǰ���� n ����ȡ���ӱ��ʽ���� n Ϊ�������á�������� n Ϊ�˽������� (0-7)���� n Ϊһ���˽���ת��ֵ��
	\nm ��ʶһ���˽���ת��ֵ��һ���������á���� \nm ֮ǰ������is preceded by at least nm ����ȡ���ӱ��ʽ���� nm Ϊ�������á���� \nm ֮ǰ������ n ����ȡ���� n Ϊһ��������� m �ĺ������á����ǰ��������������㣬�� n �� m ��Ϊ�˽������� (0-7)���� \nm ��ƥ��˽���ת��ֵ nm��
	\nml ��� n Ϊ�˽������� (0-3)���� m �� l ��Ϊ�˽������� (0-7)����ƥ��˽���ת��ֵ nml��
	\\un ƥ�� n������ n ��һ�����ĸ�ʮ���������ֱ�ʾ��Unicode�ַ���

	ƥ�������ַ���������ʽ�� \[u4e00-u9fa5\]
	ƥ��˫�ֽ��ַ�(������������)��\[^x00-xff\]
	ƥ����е�������ʽ��n\[s\| \]*r
	ƥ��HTML��ǵ�������ʽ��/<(.*)>.*</1>\|<(.*) />/
	ƥ����β�ո��������ʽ��(^s*)\|(s*$)
	ƥ��Email��ַ��������ʽ��w+(\[-+.\]w+)*@w+(\[-.\]w+)*.w+(\[-.\]w+)\*
	ƥ����ַURL��������ʽ��http://(\[w-\]+.)+\[w-\]+(/\[w\- ./?%&=\]*)?
	*/
	
	/** ƥ���ʺ��Ƿ�Ϸ�(��ĸ��ͷ������4-16�ֽڣ�������ĸ�����»���) */
	public static final String PATTERN_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{3,15}$";
	/** ƥ��Email��ַ */
	public static final String PATTERN_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)\\*$";
	/** ƥ����ڵ绰����(�̶��绰) */
	public static final String PATTERN_PHONE = "^(((\\d{3}|\\d{4})-(\\d{7}|\\d{8}))|((\\d{3}|\\d{4})-(\\d{7}|\\d{8})-(\\d{4})))$";
	/** ƥ������ֻ����� */
	public static final String PATTERN_MOBILE = "^1[3|5]\\d{9}$";
	/** ƥ�������ַ� */ 
	public static final String PATTERN_INVALIDINPUT = "[\\p{Space}��\\p{Punct}_]";
	
	/** ƥ�������ַ� */ 
	public static final String PATTERN_CHINESE = "[\u4e00-\u9fa5]";
	/** ƥ��˫�ֽ��ַ�(������������) */
	public static final String PATTERN_DOUBLEBYTE = "[^\\x00-\\xff]";
	/** ƥ����� */
	public static final String PATTERN_SPACELINE = "\n[\\s| ]*\r";
	/** ƥ��HTML��� */
	public static final String PATTERN_HTMLTAG = "<(.*)>.*</\\1>|<(.*)\\/>";
	/** ƥ����β�ո� */
	public static final String PATTERN_SPACE_HEAD_TAIL = "(^\\s*)|(\\s*$)";
	/** ƥ����ַURL */
	public static final String PATTERN_WEBADDRESS = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	/** ƥ��������֤���� */
	public static final String PATTERN_IDENTITYCARD = "\\d{14}[0-9X]|\\d{17}[0-9X]";
	/** ƥ���������� */
	public static final String PATTERN_POSTCODE = "^(\\d{6})+$";
	/** ƥ����ѶQQ�� */
	public static final String PATTERN_QQ = "^[1-9]*[1-9][0-9]*$";
	/** ƥ��IP��ַ */
	public static final String PATTERN_IP = "^(\\d{1,2}|1\\d\\d|2[0-4]d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5]).(d{1,2}|1\\d\\d|2[0-4]d|25[0-5]).(\\d{1,2}|1\\d\\d|2[0-4]d|25[0-5])$";

	
	/** �Ǹ������������� + 0�� */
	public static final String PATTERN_INTEGER_POSITIVE_ZERO = "^\\d+$";
	/** ������ */
	public static final String PATTERN_INTEGER_POSITIVE = "^[0-9]*[1-9][0-9]*$";
	/** ���������������� + 0�� */
	public static final String PATTERN_INTEGER_NEGATIVE_ZERO = "^((-\\d+)|(0+))$";
	/** ������ */
	public static final String PATTERN_INTEGER_NEGATIVE = "^-[0-9]*[1-9][0-9]*$";
	/** ���� */
	public static final String PATTERN_INTEGER = "^-?\\d+$";
	/** �Ǹ����������������� + 0��*/
	public static final String PATTERN_DOUBLE_POSITIVE_ZERO = "^\\d+(\\.\\d+)?$";
	/** �������� */
	public static final String PATTERN_DOUBLE_POSITIVE = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
	/** �������������������� + 0�� */
	public static final String PATTERN_DOUBLE_NEGATIVE_ZERO = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
	/** �������� */
	public static final String PATTERN_DOUBLE_NEGATIVE = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
	/** ������ */
	public static final String PATTERN_DOUBLE = "^(-?\\d+)(\\.\\d+)?$";
	/** ��26��Ӣ����ĸ��ɵ��ַ��� */
	public static final String PATTERN_LETTER = "^[A-Za-z]+$";
	/** ��26��Ӣ����ĸ�Ĵ�д��ɵ��ַ��� */
	public static final String PATTERN_LETTER_UPPER = "^[A-Z]+$";
	/** ��26��Ӣ����ĸ��Сд��ɵ��ַ��� */
	public static final String PATTERN_LETTER_LOWER = "^[a-z]+$";
	/** �����ֺ�26��Ӣ����ĸ��ɵ��ַ��� */
	public static final String PATTERN_LETTER_NUMBER = "^[A-Za-z0-9]+$";
	/** �����֡�26��Ӣ����ĸ�����»�����ɵ��ַ��� */
	public static final String PATTERN_LETTER_NUMBER_UNDELINE = "^\\w+$";
	/** ����-��-����ɵ��ַ���,�����ϰ������2�µȵ���������ǽ�ȥ�� */
	public static final String PATTERN_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|"
                + "[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])"
                + "-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]"
                + "|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])"
                + "|((16|[2468][048]|[3579][26])00))-0?2-29-))$";

}
