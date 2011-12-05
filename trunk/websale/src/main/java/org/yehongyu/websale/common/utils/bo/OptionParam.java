/*
 * $Id: OptionParam.java,v 1.2 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合&lt;select&gt;标签的&lt;option&gt;部分HTML编码参数对象
 * @author zhaosy
 * @version 4.0 2007-4-3
 * @since 4.0
 */
public class OptionParam {
	/** Value部分列表 */
	private List<String> lstValue;
	/** Text部分列表 */
	private List<String> lstText;
	/** 选中值 */
	private String selVal;
	/** 头信息值 */
	private String headValue;
	/** 头信息显示内容 */
	private String headText;
	
	/**
	 * (空)
	 */
	public OptionParam(){}
	
	/**
	 * 根据头信息内容构造参数对象
	 * @param headValue 头信息值
	 * @param headText 头信息显示内容
	 */
	public OptionParam(String headValue, String headText) {
		this.headValue = headValue;
		this.headText = headText;
	}
	
	/**
	 * 取得头信息显示内容
	 * @return 头信息显示内容
	 */
	public String getHeadText() {
		return headText;
	}
	/**
	 * 设置头信息显示内容
	 * @param headText 头信息显示内容
	 */
	public void setHeadText(String headText) {
		this.headText = headText;
	}
	
	/**
	 * 取得头信息值
	 * @return 头信息值
	 */
	public String getHeadValue() {
		return headValue;
	}
	/**
	 * 设置头信息值
	 * @param headValue 头信息值
	 */
	public void setHeadValue(String headValue) {
		this.headValue = headValue;
	}
	
	/**
	 * 取得Text部分列表
	 * @return Text部分列表
	 */
	public List<String> getLstText() {
		return lstText;
	}
	/**
	 * 设置Text部分列表
	 * @param lstText Text部分列表
	 */
	public void setLstText(List<String> lstText) {
		this.lstText = lstText;
	}
	
	/**
	 * 取得Value部分列表
	 * @return Value部分列表
	 */
	public List<String> getLstValue() {
		return lstValue;
	}
	/**
	 * 设置Value部分列表
	 * @param lstValue Value部分列表
	 */
	public void setLstValue(List<String> lstValue) {
		this.lstValue = lstValue;
	}
	
	/**
	 * 取得选中值
	 * @return 选中值
	 */
	public String getSelVal() {
		return selVal;
	}
	/**
	 * 设置选中值
	 * @param selVal 选中值
	 */
	public void setSelVal(String selVal) {
		this.selVal = selVal;
	}
	
	/**
	 * 添加一个option部分内容
	 * @param value value值
	 * @param text 显示内容
	 */
	public void addOption(String value, String text) {
		if (value == null || text == null) return;
		if (lstValue == null) lstValue = new ArrayList<String>();
		lstValue.add(value);
		if (lstText == null) lstText = new ArrayList<String>();
		lstText.add(text);
	}
}
