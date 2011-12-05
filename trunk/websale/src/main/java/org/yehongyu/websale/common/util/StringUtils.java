package org.yehongyu.websale.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 【类说明】字符串处理类
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 11:12:19 AM
 */
public class StringUtils {
	
    private static StringUtils instance = new StringUtils();
    
    /**
     * 这是一个单例模式的设计方式,在这个法中主要是得到本类的实例方法
     */
    public static StringUtils getInstance() {
        return instance;
    }
    
    /** 
     * 私有构造方法，防止外部创建本类实例 
     */
    private StringUtils(){}

	/**
	 * 【函数功能】判断输入字符串是否为Null或空串
	 * @param str
	 * @return true or false
	 */
	public static boolean isNullorSpace(String str){
		return str==null||str.trim().equals("")?true:false;
	}

	/**
	 * 【函数功能】验证是否为指定匹配的字符串
	 * @param str
	 * @return true or false
	 */
	public static boolean isPattern(String str,String pattern) {
		if(isNullorSpace(str)) return true;
		return Pattern.compile(pattern).matcher(str).matches();
	}
	
	/**
	 * 【函数功能】验证是否由数字组成的字符串
	 * @param str
	 * @return true or false
	 */
	public static boolean isNumber(String str) {
		return isPattern(str,RegExpress.PATTERN_INTEGER);
	}
	
	/**
	 * 【函数功能】验证是否Double类型数据
	 * @param emailstr
	 * @return true or false
	 */
	public static boolean isDouble(String str){
		return isPattern(str,RegExpress.PATTERN_DOUBLE);
	}
	
	/**
	 * 【函数功能】验证是否由字母或数字组成的字符串
	 * @param str
	 * @return true or false
	 */
	public static boolean isNumberOrLetter(String str) {
		return isPattern(str,RegExpress.PATTERN_LETTER_NUMBER);
	}
	
	/**
	 * 【函数功能】验证是否由字母、数字、下划线组成，且只能以字母或下划线开始（用于用户名验证）
	 * @param str
	 * @return true or false
	 */
	public static boolean isAccount(String str) {
		return isPattern(str,RegExpress.PATTERN_ACCOUNT);
	}
	
	/**
	 * 【函数功能】验证是否是合法的邮箱地址，正确值应包括“@”和“.”符号
	 * @param str
	 * @return true or false
	 */
	public static boolean isEmail(String str) {
		return isPattern(str,RegExpress.PATTERN_EMAIL);
	}

	/**
	 * 【函数功能】验证是否是手机号
	 * @param str
	 * @return true or false
	 */
	public static boolean isMobile(String str) {
		return isPattern(str,RegExpress.PATTERN_MOBILE);
	}

	/**
	 * 【函数功能】验证是否是固定电话
	 * @param str
	 * @return true or false
	 */
	public static boolean isPhone(String str) {
		return isPattern(str,RegExpress.PATTERN_PHONE);
	}

	/**
	 * 【函数功能】判断是否包含特殊字符
	 * @param str
	 * @return true(不包含)
	 */
	public static boolean isValidInput(String str) {
		return !isPattern(str,RegExpress.PATTERN_INVALIDINPUT);
	}
    
    /** 字母和数字的数组,用于生成随机的字符串 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	/** 随机生成类 */
	private static Random randGen = new Random();
    /**
     * 【函数功能】获取固定长度的字母和数字组合的随机字符串
     * @param length
     * @return 指定长度的字符串
     */
    public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}
    
    /**
	 * 【函数功能】将Clob类型转换为String类型
	 * @param cl
	 * @return String
	 */
    public static String ClobToString(Clob cl) {
        if (cl == null)
            return "";
        StringBuffer strOut = new StringBuffer();
        String aux;
        // We access to stream, as this way we don't have to use the
        // CLOB.length() which is slower...
        BufferedReader br;
        try {
            br = new BufferedReader(cl.getCharacterStream());
            while ((aux = br.readLine()) != null)
                strOut.append(aux);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strOut.toString();
    }

	/**
	 * 【函数功能】把字符串从GBK转换到ISO8859-1
	 * @param str 输入的GBK编码的字符串
	 * @return ISO8859-1编码的字符串
	 * @throws Exception
	 */
	public static final String GBKToUnicode(String str)
			throws UnsupportedEncodingException {
		return getCharSet(str, "GBK", "ISO8859-1");
	}

	/**
	 * 【函数功能】把字符串从ISO8859-1转换到GBK
	 * @param str 输入的ISO8859-1编码的字符串
	 * @return GBK编码的字符串
	 * @throws Exception
	 */
	public static final String UnicodeToGBK(String str)
			throws UnsupportedEncodingException {
		return getCharSet(str, "ISO8859-1", "GBK");
	}
	
    /**
     * 【函数功能】转换字符编码
     * @param str 输入字符串
     * @param sourceSet 源字符集
     * @param destSet 目标字符集
     * @return 输出字符串
     * @throws UnsupportedEncodingException
     */
    public static String getCharSet(String str, String sourceSet,
			String destSet) throws UnsupportedEncodingException {
		if (isNullorSpace(str))
			return "";
		return new String(str.getBytes(sourceSet), destSet);
	}
	
	/**
	 * 【函数功能】全角字符转换成半角字符
	 * @param str 输入字符串(可能包含全角字符)
	 * @return String 输出字符串(全角字符全部转换成了半角字符)
	 */
	public static String SbcToDbc(String str) {
		if(str == null) return "";
		String outStr = "";
		for (int i = 0; i < str.length(); i++) {
			int j = str.charAt(i);
			if (j >= 65281 && j < 65373) {
				outStr += (char) (j - 65248);
			} else {
				outStr += str.charAt(i);
			}
		}
		return outStr;
	}
	
	/**
	 * 【函数功能】判断是否由连续的字符串或数字组成，如123456、abcdef则返回true
	 * @param str
	 * @return true or false
	 */
	public static boolean isContinueInput(String str) {
		boolean flag = false;
		if (str == null || "".equals(str) || str.length() <= 1) return false;
		int ascendNumber = 0;
		int descendNumber = 0;
		for(int i = 1; i < str.length(); i++) {
			if(str.charAt(i) != str.charAt(i - 1) + 1) {
				ascendNumber = 1;
				break;
			}
		}
		for(int i = 0; i < str.length() - 1; i++) {
			if(str.charAt(i) != str.charAt(i + 1) + 1) {
				descendNumber = 1;
				break;
			}
		}
		if(ascendNumber == 0 || descendNumber == 0) {
			flag = true;
		}
		return flag;
	}

    /**
     * 【函数功能】将一个数组按照指定的分隔组合成一个字符串
     * @param objs 需要转换的数组
     * @param delimiter 指定的分隔符
     * @return String 组合后的字符串
     */
    public static String arrayToString(Object[] objs, String delimiter) {
		String res = "";
		for (int i = 0; i < objs.length; i++) {
			res = res + objs[i].toString();
			if (i < (objs.length - 1)) {
				res = res + delimiter;
			}
		}
		return res;
	}
    
    /**
     * 【函数功能】将输入的字符串过滤掉其中的HTML内容
     * @param inputStr
     * @return 替换后的字符串
     */
    public static String filterHtml(String inputStr) {
		String htmlStr = inputStr; // 含html标签的字符串
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式 或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式 或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			Pattern p_script = Pattern.compile(regEx_script,
					Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			Pattern p_style = Pattern.compile(regEx_style,
					Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			Pattern p_html = Pattern.compile(regEx_html,
					Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("yjxHtml2Text().Html2Text: " + e.getMessage());
			return "";
		}
		return textStr;// 返回文本字符串
	}
    
    /**
     * 【函数功能】随机的日期文件名
     * @return 日期文件名字符串
     */
    public static String getRandomFileName(){
		Date date = new Date();
		DateFormat parser = new SimpleDateFormat("yyyyMMdd-HHmmss-SS");
		String filename = parser.format(date);
		Random ran = new Random();
		int i = ran.nextInt(11);
		
		StringBuffer sb = new StringBuffer();
		sb.append(filename).append("-").append(i);
		
		return sb.toString();
	}
    
    /**
     * 【函数功能】获取随机字符串
     * @return 六位字符串
     */
    public static String randomString(){
        String str=new  String("QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR");
        StringBuilder sb=new StringBuilder();
        Random r = new Random();
        int te=0;
        for(int i=1;i<=6;i++){
            te=r.nextInt(62);
            sb.append(str.charAt(te));
        }
        return sb.toString();
    }

    /**
	 * 【函数功能】main方法
	 * 
	 * @param args
     * @throws UnsupportedEncodingException 
	 */
    public static void main(String[] args) throws UnsupportedEncodingException{
    	System.out.println(StringUtils.getCharSet("%E7%99%BE%E5%BA%A6", "UTF-8", "GBK"));
    }
    
}
