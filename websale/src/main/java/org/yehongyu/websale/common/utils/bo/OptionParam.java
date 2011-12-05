/*
 * $Id: OptionParam.java,v 1.2 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * ���&lt;select&gt;��ǩ��&lt;option&gt;����HTML�����������
 * @author zhaosy
 * @version 4.0 2007-4-3
 * @since 4.0
 */
public class OptionParam {
	/** Value�����б� */
	private List<String> lstValue;
	/** Text�����б� */
	private List<String> lstText;
	/** ѡ��ֵ */
	private String selVal;
	/** ͷ��Ϣֵ */
	private String headValue;
	/** ͷ��Ϣ��ʾ���� */
	private String headText;
	
	/**
	 * (��)
	 */
	public OptionParam(){}
	
	/**
	 * ����ͷ��Ϣ���ݹ����������
	 * @param headValue ͷ��Ϣֵ
	 * @param headText ͷ��Ϣ��ʾ����
	 */
	public OptionParam(String headValue, String headText) {
		this.headValue = headValue;
		this.headText = headText;
	}
	
	/**
	 * ȡ��ͷ��Ϣ��ʾ����
	 * @return ͷ��Ϣ��ʾ����
	 */
	public String getHeadText() {
		return headText;
	}
	/**
	 * ����ͷ��Ϣ��ʾ����
	 * @param headText ͷ��Ϣ��ʾ����
	 */
	public void setHeadText(String headText) {
		this.headText = headText;
	}
	
	/**
	 * ȡ��ͷ��Ϣֵ
	 * @return ͷ��Ϣֵ
	 */
	public String getHeadValue() {
		return headValue;
	}
	/**
	 * ����ͷ��Ϣֵ
	 * @param headValue ͷ��Ϣֵ
	 */
	public void setHeadValue(String headValue) {
		this.headValue = headValue;
	}
	
	/**
	 * ȡ��Text�����б�
	 * @return Text�����б�
	 */
	public List<String> getLstText() {
		return lstText;
	}
	/**
	 * ����Text�����б�
	 * @param lstText Text�����б�
	 */
	public void setLstText(List<String> lstText) {
		this.lstText = lstText;
	}
	
	/**
	 * ȡ��Value�����б�
	 * @return Value�����б�
	 */
	public List<String> getLstValue() {
		return lstValue;
	}
	/**
	 * ����Value�����б�
	 * @param lstValue Value�����б�
	 */
	public void setLstValue(List<String> lstValue) {
		this.lstValue = lstValue;
	}
	
	/**
	 * ȡ��ѡ��ֵ
	 * @return ѡ��ֵ
	 */
	public String getSelVal() {
		return selVal;
	}
	/**
	 * ����ѡ��ֵ
	 * @param selVal ѡ��ֵ
	 */
	public void setSelVal(String selVal) {
		this.selVal = selVal;
	}
	
	/**
	 * ���һ��option��������
	 * @param value valueֵ
	 * @param text ��ʾ����
	 */
	public void addOption(String value, String text) {
		if (value == null || text == null) return;
		if (lstValue == null) lstValue = new ArrayList<String>();
		lstValue.add(value);
		if (lstText == null) lstText = new ArrayList<String>();
		lstText.add(text);
	}
}
