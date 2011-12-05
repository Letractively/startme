/*
 * $Id: HtmlCode.java,v 1.3 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils;

import org.yehongyu.websale.common.utils.bo.OptionParam;


/**
 * HTML���빦��ʵ����
 * @author zhaosy
 * @version 4.0 2007-4-3
 * @since 4.0
 */
public class HtmlCode {
	/** HTML����ģ��---OPTION */
	private static final String TEMPLATE_OPTION = "<option value=\"%%1%%\"%%2%%>%%3%%</option>";
	
	/**
	 * (��)
	 */
	private HtmlCode() {}
	
	/**
	 * ���ݴ���������&lt;select&gt;��ǩ��&lt;option&gt;����HTML���롣
	 * <p><pre>
	 * ����
	 *   OptionParam param = new OptionParam("", "---��ѡ��---");
	 *   param.addOption("010", "������");
	 *   param.addOption("021", "�Ϻ���");
	 *   param.setSelVal("010");
	 *   System.out.println(getOption(param));
	 * �������
	 *   &lt;option value=""&gt;---��ѡ��---&lt;/option&gt;
	 *   &lt;option value="010" selected&gt;������&lt;/option&gt;
	 *   &lt;option value="021"&gt;�Ϻ���&lt;/option&gt;
	 * </pre></p>
	 * @param param �������
	 * @return &lt;option&gt;����HTML����
	 */
	public static String getOptions(OptionParam param) {
		if (param.getLstValue() == null || param.getLstText() == null) return "";
		StringBuffer options = new StringBuffer();
		
		if (param.getHeadText() != null) {
			options.append(getOption(param.getHeadValue(), param.getHeadText(), false));
		}
		
		for (int i = 0; i < param.getLstValue().size() && i < param.getLstText().size(); i++) {
			options.append(
				getOption(param.getLstValue().get(i), param.getLstText().get(i)
						, param.getLstValue().get(i).equals(param.getSelVal())));
		}
		
		return options.toString();
	}
	
	/**
	 * ����������ϳ�һ��&lt;option&gt;���ֵ�HTML����
	 * @param value &lt;option&gt;��ǩvalue����ֵ
	 * @param text &lt;option&gt;��ǩ��ʾ����
	 * @param selected �Ƿ�ѡ�У�true��ѡ�С�false����ѡ��
	 * @return һ��&lt;option&gt;���ֵ�HTML����
	 */
	private static String getOption(String value, String text, boolean selected) {
		value = (value == null) ? "" : value;
		text = (text == null) ? "" : text;
		return TEMPLATE_OPTION.replaceAll("%%1%%", value)
		                      .replaceAll("%%2%%", (selected) ? " selected" : "")
		                      .replaceAll("%%3%%", text);
	}

}
