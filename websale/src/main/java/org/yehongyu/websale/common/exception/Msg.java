/*
 * $Id: Msg.java,v 1.11 2007/04/13 08:29:17 sunbm Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.exception;



/**
 * 
 * 业务层使用的消息类.在<code>com.hc360.b2b.exception.messages_zh_CN.properties</code>
 * 中定义相应的错误编码，并由工具生成相应的信息编码常量类<br/>
 * 
 * 编码规则:<br/>
 * 
 *[编码原则]:编码应该体现工程名字、子模块。我们用mmt_[模块（工程简写）]_[子模块]_[数字编号] 的格式<br/>
 * 如mmt_my_product_00001 . 表示发生在my工程product子模块的一个信息。<br/>
 * 各个模块维护自己各个模块的错误信息编码。<br/>
 *
 *其中[模块]还是遵循错误代码库规范里的模块定义。<br/>
 *其它的子模块没有要求，由各个工程里具体再定义。<br/>
 *[数字编号]:从000001开始的5位数字增长的序列<br/>
 * 
 * 生成常量类定义:<br/>
 * Usage: java com.hc360.b2b.exception.tools.MsgCreator ouputfile <br/>
 * java com.hc360.b2b.exception.tools.MsgCreator D:\projects\workspace4\b2b\src\com\hc360\b2b\exception\MsgCode.java<br/>
 * 
 * 返回中文字符串:<br/>
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
	 * 返回编号对应的提示信息,不带占位符的
	 * @param infoCode 具体的消息编码
	 * @return 编码对应的提示信息，没有找到对应的，返回""
	 */
	public static String get(String infoCode){
		
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode);		
	}
	
	/**
	 * 返回信息编码对应的中文提示信息串，带一个占位符
	 * @param infoCode 具体的消息编码
	 * @param obj 占位符参数
	 * @return 格式化后的字符串
	 */
	public static String get(String infoCode, Object obj){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj);
	}
	
	/**
	 * 返回信息编码对应的中文提示信息串，带一个占位符
	 * @param infoCode 具体的消息编码
	 * @param obj1 占位符参数1
	 * @param obj2 占位符参数2
	 * @return 格式化后的字符串
	 */
	public static String get(String infoCode, Object obj1,Object obj2){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj1,obj2);
	}
	
	
	/**
	 * 返回信息编码对应的中文提示信息串，带一个占位符
	 * @param infoCode 具体的消息编码
	 * @param obj 占位符参数数组
	 * @return 格式化后的字符串
	 */
	public static String get(String infoCode, Object [] obj){
		MessageResource mr = MessageResourceFactory.get();
		return mr.getMessage(infoCode,obj);
	}
	
}

