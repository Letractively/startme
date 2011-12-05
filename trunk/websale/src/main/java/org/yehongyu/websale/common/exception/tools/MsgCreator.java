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
 * ���������ļ����ɴ�������Err
 * Usages:<br/>
 * java MsgCreator.class outputpath <br/>
 * 
 * outputpath  ���ɵ�java�����·��<br/>
 * [prefix]    ������ǰ׺�����û��ʹ��Ĭ�ϵ�com.hc360.b2b.exception
 *   
 * @author sunbaoming@hc360.com
 *
 */
public class MsgCreator {

	/**
	 * TODO δʵ��
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
		s.append("  *").append("����Ӧ�ù�����Ϣ����������,��messages_zh_CN.properties�ж��岢�ɹ����Զ�����.!!!!�벻Ҫ�޸�����ļ�!!!!").append("\r\n");
		s.append("  */").append("\r\n");
		
		s.append("  public class MsgCode {").append("\r\n");
	}
	
	protected void tail(StringBuffer s){
		s.append("  }").append("\r\n");
	}
	
	
	/**
	 * ����xml�����ֶ�
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
	 * д����ļ�
	 * @param s ���ɵ��ļ�����buffer
	 * @param outfilename ����ļ���
	 * @throws IOException 
	 */
	protected void writefile(StringBuffer s,String outfilename) throws IOException{
		
	
		OutputStreamWriter  fw=new OutputStreamWriter (new FileOutputStream(outfilename),"gb2312");		
		fw.write(s.toString());
		fw.close();
		
		
	}
	

}
