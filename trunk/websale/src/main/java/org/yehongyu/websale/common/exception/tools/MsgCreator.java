/*
 * $Id: MsgCreator.java,v 1.2 2007/05/10 03:16:22 zengjm Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.exception.tools;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.yehongyu.websale.common.exception.MessageResource;
import org.yehongyu.websale.common.exception.MessageResourceFactory;

/**
 * 根据配置文件生成错误常量类Err
 * Usages:<br/>
 * java MsgCreator.class outputpath <br/>
 * 
 * outputpath  生成的java类输出路径<br/>
 * [prefix]    包名的前缀，如果没有使用默认的com.hc360.b2b.exception
 *   
 * @author sunbaoming@hc360.com
 *
 */
public class MsgCreator {

	/**
	 * TODO 未实现
	 * @param args
	 */
	public static void main(String[] args)throws Exception {

		MessageResource mr = MessageResourceFactory.get();

		List nodeList = mr.getAllKeys();
		
		StringBuffer s= new StringBuffer();
		
		MsgCreator ec=new MsgCreator();
		
		String prefix=null;
		if (args.length>1){
			 prefix = args[1];
		}
		ec.head(s,prefix);
		
		
		for (Iterator it = nodeList.iterator(); it.hasNext();) {
			String element = (String) it.next();
			ec.processNode(element,mr,s);
		}
		
		ec.tail(s);
		
		ec.writefile(s,args[0]);
		System.out.println("create sucess!!!!");
		
	}
	
	protected void head(StringBuffer s,String packageprefix){
		if (packageprefix!=null){
			s.append("package ").append(packageprefix);
		}else{
			s.append("package org.yehongyu.websale.common.exception");
		}
		
		s.append(";\r\n");
		
		s.append("/**").append("\r\n");
		s.append("  *").append("各个应用工程信息常量定义类,由messages_zh_CN.properties中定义并由工具自动生成.!!!!请不要修改这个文件!!!!").append("\r\n");
		s.append("  */").append("\r\n");
		
		s.append("  public class MsgCode {").append("\r\n");
	}
	
	protected void tail(StringBuffer s){
		s.append("  }").append("\r\n");
	}
	
	
	/**
	 * 处理xml生成字段
	 * @param dpr
	 * @param n
	 * @param s
	 */
	protected void processNode(String nodeValue,MessageResource mr,StringBuffer s){
				
		s.append("/**");
		
		String value = mr.getMessage(nodeValue);
		
		s.append(value);
		s.append(" */").append("\r\n");
		
		s.append("    public static final String ").append(nodeValue.toUpperCase()).append("=\"").append(nodeValue.toLowerCase()).append("\";\r\n");
		
	}
	
	/**
	 * 写输出文件
	 * @param s 生成的文件内容buffer
	 * @param outfilename 输出文件名
	 * @throws IOException 
	 */
	protected void writefile(StringBuffer s,String outfilename) throws IOException{
		
	
		OutputStreamWriter  fw=new OutputStreamWriter (new FileOutputStream(outfilename),"gb2312");		
		fw.write(s.toString());
		fw.close();
		
		
	}
	

}
