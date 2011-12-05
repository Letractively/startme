/*
 * $Id: Msg.java,v 1.11 2007/04/13 08:29:17 sunbm Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.exception;



/**
 * 
 * ҵ���ʹ�õ���Ϣ��.��<code>com.hc360.b2b.exception.messages_zh_CN.properties</code>
 * �ж�����Ӧ�Ĵ�����룬���ɹ���������Ӧ����Ϣ���볣����<br/>
 * 
 * �������:<br/>
 * 
 *[����ԭ��]:����Ӧ�����ֹ������֡���ģ�顣������mmt_[ģ�飨���̼�д��]_[��ģ��]_[���ֱ��] �ĸ�ʽ<br/>
 * ��mmt_my_product_00001 . ��ʾ������my����product��ģ���һ����Ϣ��<br/>
 * ����ģ��ά���Լ�����ģ��Ĵ�����Ϣ���롣<br/>
 *
 *����[ģ��]������ѭ��������淶���ģ�鶨�塣<br/>
 *��������ģ��û��Ҫ���ɸ�������������ٶ��塣<br/>
 *[���ֱ��]:��000001��ʼ��5λ��������������<br/>
 * 
 * ���ɳ����ඨ��:<br/>
 * Usage: java com.hc360.b2b.exception.tools.MsgCreator ouputfile <br/>
 * java com.hc360.b2b.exception.tools.MsgCreator D:\projects\workspace4\b2b\src\com\hc360\b2b\exception\MsgCode.java<br/>
 * 
 * ���������ַ���:<br/>
 * <code>
 * 		String infostr=Msg.get(MsgCode.MMT_MY_PRODUCT_000001);
 * </code>
 * 
 * 
 * @author sbm
 * @version 4.0 Apr 13, 2007
 * @since 4.0
 */
public class Msg {

	/**
	 * ���ر�Ŷ�Ӧ����ʾ��Ϣ,����ռλ����
	 * @param infoCode �������Ϣ����
	 * @return �����Ӧ����ʾ��Ϣ��û���ҵ���Ӧ�ģ�����""
	 */
	public static String get(String infoCode){
		
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode);		
	}
	
	/**
	 * ������Ϣ�����Ӧ��������ʾ��Ϣ������һ��ռλ��
	 * @param infoCode �������Ϣ����
	 * @param obj ռλ������
	 * @return ��ʽ������ַ���
	 */
	public static String get(String infoCode, Object obj){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj);
	}
	
	/**
	 * ������Ϣ�����Ӧ��������ʾ��Ϣ������һ��ռλ��
	 * @param infoCode �������Ϣ����
	 * @param obj1 ռλ������1
	 * @param obj2 ռλ������2
	 * @return ��ʽ������ַ���
	 */
	public static String get(String infoCode, Object obj1,Object obj2){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj1,obj2);
	}
	
	
	/**
	 * ������Ϣ�����Ӧ��������ʾ��Ϣ������һ��ռλ��
	 * @param infoCode �������Ϣ����
	 * @param obj ռλ����������
	 * @return ��ʽ������ַ���
	 */
	public static String get(String infoCode, Object [] obj){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj);
	}
	
}

