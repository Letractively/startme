/*
 * $Id: HtmlCode.java,v 1.3 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils;

import org.yehongyu.websale.common.utils.bo.OptionParam;


/**
 * HTML编码功能实现类
 * @author zhaosy
 * @version 4.0 2007-4-3
 * @since 4.0
 */
public class HtmlCode {
	/** HTML编码模版---OPTION */
	private static final String TEMPLATE_OPTION = "<option value=\"%%1%%\"%%2%%>%%3%%</option>";
	
	/**
	 * (空)
	 */
	private HtmlCode() {}
	
	/**
	 * 根据传入参数组合&lt;select&gt;标签的&lt;option&gt;部分HTML编码。
	 * <p><pre>
	 * 例：
	 *   OptionParam param = new OptionParam("", "---请选择---");
	 *   param.addOption("010", "北京市");
	 *   param.addOption("021", "上海市");
	 *   param.setSelVal("010");
	 *   System.out.println(getOption(param));
	 * 将输出：
	 *   &lt;option value=""&gt;---请选择---&lt;/option&gt;
	 *   &lt;option value="010" selected&gt;北京市&lt;/option&gt;
	 *   &lt;option value="021"&gt;上海市&lt;/option&gt;
	 * </pre></p>
	 * @param param 传入参数
	 * @return &lt;option&gt;部分HTML编码
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
	 * 根据内容组合成一个&lt;option&gt;部分的HTML编码
	 * @param value &lt;option&gt;标签value部分值
	 * @param text &lt;option&gt;标签显示内容
	 * @param selected 是否选中，true：选中、false：不选中
	 * @return 一个&lt;option&gt;部分的HTML编码
	 */
	private static String getOption(String value, String text, boolean selected) {
		value = (value == null) ? "" : value;
		text = (text == null) ? "" : text;
		return TEMPLATE_OPTION.replaceAll("%%1%%", value)
		                      .replaceAll("%%2%%", (selected) ? " selected" : "")
		                      .replaceAll("%%3%%", text);
	}

}
