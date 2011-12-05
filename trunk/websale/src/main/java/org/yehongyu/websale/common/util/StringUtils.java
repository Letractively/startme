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
 * ����˵�����ַ���������
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 11:12:19 AM
 */
public class StringUtils {
	
    private static StringUtils instance = new StringUtils();
    
    /**
     * ����һ������ģʽ����Ʒ�ʽ,�����������Ҫ�ǵõ������ʵ������
     */
    public static StringUtils getInstance() {
        return instance;
    }
    
    /** 
     * ˽�й��췽������ֹ�ⲿ��������ʵ�� 
     */
    private StringUtils(){}

	/**
	 * ���������ܡ��ж������ַ����Ƿ�ΪNull��մ�
	 * @param str
	 * @return true or false
	 */
	public static boolean isNullorSpace(String str){
		return str==null||str.trim().equals("")?true:false;
	}

	/**
	 * ���������ܡ���֤�Ƿ�Ϊָ��ƥ����ַ���
	 * @param str
	 * @return true or false
	 */
	public static boolean isPattern(String str,String pattern) {
		if(isNullorSpace(str)) return true;
		return Pattern.compile(pattern).matcher(str).matches();
	}
	
	/**
	 * ���������ܡ���֤�Ƿ���������ɵ��ַ���
	 * @param str
	 * @return true or false
	 */
	public static boolean isNumber(String str) {
		return isPattern(str,RegExpress.PATTERN_INTEGER);
	}
	
	/**
	 * ���������ܡ���֤�Ƿ�Double��������
	 * @param emailstr
	 * @return true or false
	 */
	public static boolean isDouble(String str){
		return isPattern(str,RegExpress.PATTERN_DOUBLE);
	}
	
	/**
	 * ���������ܡ���֤�Ƿ�����ĸ��������ɵ��ַ���
	 * @param str
	 * @return true or false
	 */
	public static boolean isNumberOrLetter(String str) {
		return isPattern(str,RegExpress.PATTERN_LETTER_NUMBER);
	}
	
	/**
	 * ���������ܡ���֤�Ƿ�����ĸ�����֡��»�����ɣ���ֻ������ĸ���»��߿�ʼ�������û�����֤��
	 * @param str
	 * @return true or false
	 */
	public static boolean isAccount(String str) {
		return isPattern(str,RegExpress.PATTERN_ACCOUNT);
	}
	
	/**
	 * ���������ܡ���֤�Ƿ��ǺϷ��������ַ����ȷֵӦ������@���͡�.������
	 * @param str
	 * @return true or false
	 */
	public static boolean isEmail(String str) {
		return isPattern(str,RegExpress.PATTERN_EMAIL);
	}

	/**
	 * ���������ܡ���֤�Ƿ����ֻ���
	 * @param str
	 * @return true or false
	 */
	public static boolean isMobile(String str) {
		return isPattern(str,RegExpress.PATTERN_MOBILE);
	}

	/**
	 * ���������ܡ���֤�Ƿ��ǹ̶��绰
	 * @param str
	 * @return true or false
	 */
	public static boolean isPhone(String str) {
		return isPattern(str,RegExpress.PATTERN_PHONE);
	}

	/**
	 * ���������ܡ��ж��Ƿ���������ַ�
	 * @param str
	 * @return true(������)
	 */
	public static boolean isValidInput(String str) {
		return !isPattern(str,RegExpress.PATTERN_INVALIDINPUT);
	}
    
    /** ��ĸ�����ֵ�����,��������������ַ��� */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	/** ��������� */
	private static Random randGen = new Random();
    /**
     * ���������ܡ���ȡ�̶����ȵ���ĸ��������ϵ�����ַ���
     * @param length
     * @return ָ�����ȵ��ַ���
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
	 * ���������ܡ���Clob����ת��ΪString����
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
	 * ���������ܡ����ַ�����GBKת����ISO8859-1
	 * @param str �����GBK������ַ���
	 * @return ISO8859-1������ַ���
	 * @throws Exception
	 */
	public static final String GBKToUnicode(String str)
			throws UnsupportedEncodingException {
		return getCharSet(str, "GBK", "ISO8859-1");
	}

	/**
	 * ���������ܡ����ַ�����ISO8859-1ת����GBK
	 * @param str �����ISO8859-1������ַ���
	 * @return GBK������ַ���
	 * @throws Exception
	 */
	public static final String UnicodeToGBK(String str)
			throws UnsupportedEncodingException {
		return getCharSet(str, "ISO8859-1", "GBK");
	}
	
    /**
     * ���������ܡ�ת���ַ�����
     * @param str �����ַ���
     * @param sourceSet Դ�ַ���
     * @param destSet Ŀ���ַ���
     * @return ����ַ���
     * @throws UnsupportedEncodingException
     */
    public static String getCharSet(String str, String sourceSet,
			String destSet) throws UnsupportedEncodingException {
		if (isNullorSpace(str))
			return "";
		return new String(str.getBytes(sourceSet), destSet);
	}
	
	/**
	 * ���������ܡ�ȫ���ַ�ת���ɰ���ַ�
	 * @param str �����ַ���(���ܰ���ȫ���ַ�)
	 * @return String ����ַ���(ȫ���ַ�ȫ��ת�����˰���ַ�)
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
	 * ���������ܡ��ж��Ƿ����������ַ�����������ɣ���123456��abcdef�򷵻�true
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
     * ���������ܡ���һ�����鰴��ָ���ķָ���ϳ�һ���ַ���
     * @param objs ��Ҫת��������
     * @param delimiter ָ���ķָ���
     * @return String ��Ϻ���ַ���
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
     * ���������ܡ���������ַ������˵����е�HTML����
     * @param inputStr
     * @return �滻����ַ���
     */
    public static String filterHtml(String inputStr) {
		String htmlStr = inputStr; // ��html��ǩ���ַ���
		String textStr = "";
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // ����script��������ʽ ��<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // ����style��������ʽ ��<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

			Pattern p_script = Pattern.compile(regEx_script,
					Pattern.CASE_INSENSITIVE);
			Matcher m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // ����script��ǩ

			Pattern p_style = Pattern.compile(regEx_style,
					Pattern.CASE_INSENSITIVE);
			Matcher m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // ����style��ǩ

			Pattern p_html = Pattern.compile(regEx_html,
					Pattern.CASE_INSENSITIVE);
			Matcher m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // ����html��ǩ

			textStr = htmlStr;
		} catch (Exception e) {
			System.err.println("yjxHtml2Text().Html2Text: " + e.getMessage());
			return "";
		}
		return textStr;// �����ı��ַ���
	}
    
    /**
     * ���������ܡ�����������ļ���
     * @return �����ļ����ַ���
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
     * ���������ܡ���ȡ����ַ���
     * @return ��λ�ַ���
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
	 * ���������ܡ�main����
	 * 
	 * @param args
     * @throws UnsupportedEncodingException 
	 */
    public static void main(String[] args) throws UnsupportedEncodingException{
    	System.out.println(StringUtils.getCharSet("%E7%99%BE%E5%BA%A6", "UTF-8", "GBK"));
    }
    
}
