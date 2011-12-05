/*
 * $Id: MmtEncrypt.java,v 1.1 2007/05/03 06:44:08 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.yehongyu.websale.common.util.MyException;


/**
 * ��ͼ���������
 * @author zhaosy
 * @version 4.0 2007-4-15
 * @since 4.0
 */
public class MmtEncrypt {
	
	/**
	 * (��)
	 */
	private MmtEncrypt() {}
	
	/**
	 * ������������л��󲢼���
	 * @param obj �����л�����
	 * @return ���л��󲢼��ܽ��
	 * @throws MyException �����쳣
	 */
	public static String encryptSerializeObject(Object obj) throws MyException {
		byte[] serialize = serializeObject(obj);
		String encrypt;
		try {
			encrypt = Des.byteArr2HexStr(serialize);
		} catch (Exception e) {
			throw new MyException("�޷��������л����", e);
		}
		return encrypt;
	}
	
	/**
	 * ������������л�
	 * @param obj �����л�����
	 * @return ���л����
	 * @throws MyException �����쳣
	 */
	public static byte[] serializeObject(Object obj) throws MyException {
		ByteArrayOutputStream os = null;
		ObjectOutputStream oos = null;
		byte[] ret;
		try {
			os = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			ret = os.toByteArray();
		} catch (IOException e) {
			throw new MyException("", e);
		} finally {
			try {
				if (oos != null) oos.close();
			} catch (IOException e) {
				throw new MyException("", e);
			}
			try {
				if (os != null) os.close();
			} catch (IOException e) {
				throw new MyException("", e);
			}
		}
		return ret;
	}
	
	/**
	 * ���������ֵ���ܺ����л�Ϊ����
	 * @param enSerialize �����ַ���
	 * @return �����л������
	 * @throws MyException �����쳣
	 */
	public static Object decryptSerializeObject(String enSerialize) throws MyException {
		byte[] str;
		try {
			str = Des.hexStr2ByteArr(enSerialize);
		} catch (Exception e) {
			throw new MyException("�������л�ֵ�����쳣", e);
		}
		return unSerializeObject(str);
	}
	
	/**
	 * ���������л�ֵ�����л�
	 * @param strSerialize ���л����
	 * @return �����л������
	 * @throws MyException �����쳣
	 */
	public static Object unSerializeObject(byte[] serialize) throws MyException {
		Object ret;
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new ByteArrayInputStream(serialize);
			ois = new ObjectInputStream(is);
			ret = ois.readObject();
		} catch (Exception e) {
			throw new MyException("��ȡ���л��������쳣", e);
		} finally {
			try {
				if (ois != null) ois.close();
			} catch (IOException e) {
				throw new MyException("", e);
			}
			try {
				if (is != null) is.close();
			} catch (IOException e) {
				throw new MyException("", e);
			}
		}

		return ret;
	}
}
