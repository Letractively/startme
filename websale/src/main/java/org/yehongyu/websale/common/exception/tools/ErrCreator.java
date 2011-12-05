/*
 * $Id: ErrCreator.java,v 1.3 2007/04/16 08:17:53 sunbm Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.exception.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Node;
import org.yehongyu.websale.common.utils.dom4jXmlParseMgr;

/**
 * ���������ļ����ɴ�������Err
 * Usages:<br/>
 * java ErrCreator.class errfilename outputpath [prefix] <br/>
 * errfilename �쳣�����ļ����ļ���
 * outputpath  ���ɵ�java�����·��
 * [prefix]    ������ǰ׺�����û��ʹ��Ĭ�ϵ�hc360.b2b.exception
 *   
 * @author sunbaoming@hc360.com
 *
 */
public class ErrCreator {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		File f = new File(args[0]);
		if (!f.isFile()) throw new Exception("�ļ�������");
		
		
		dom4jXmlParseMgr dpr=new dom4jXmlParseMgr(args[0]);
		List nodeList=dpr.getMuliElementNode("/Exception/ModeName/ExceptionItem");
		
		StringBuffer s= new StringBuffer();
		
		ErrCreator ec=new ErrCreator();
		
		String prefix=null;
		if (args.length>2){
			 prefix = args[2];
		}
		ec.head(s,prefix);
		
		
		for (Iterator it = nodeList.iterator(); it.hasNext();) {
			Node element = (Node) it.next();
			ec.processNode(dpr,element,s);
		}
		
		ec.tail(s);
		
		ec.writefile(s,args[1]);
		
		
	}
	
	protected void head(StringBuffer s,String packageprefix){
		if (packageprefix!=null){
			s.append("package ").append(packageprefix);
		}else{
			s.append("package com.hc360.b2b.exception");
		}
		
		s.append(";\r\n");
		
		s.append("/**").append("\r\n");
		s.append("  *").append("����Ӧ�ù��̴�������,��exception.xml�ж��岢�ɹ����Զ�����.!!!!�벻Ҫ�޸�����ļ�!!!!").append("\r\n");
		s.append("  */").append("\r\n");
		
		s.append("  public class Err {").append("\r\n");
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
	protected void processNode(dom4jXmlParseMgr dpr,Node n,StringBuffer s){
		
		/**
		 * code="MMT_MY_OR_0000" PageMessage="{dbName} ,{username},fgggg" DebugMessage="" IsTopage="Y" TopageURL=""
		 */
		
		String code=dpr.getElementAttributeValue(n,"code");
		String pm=dpr.getElementAttributeValue(n,"PageMessage");
		String dm=dpr.getElementAttributeValue(n,"DebugMessage");
		String isto=dpr.getElementAttributeValue(n,"IsTopage");
		String top=dpr.getElementAttributeValue(n, "TopageURL");
		
		s.append("/**").append("\r\n");
		s.append("  *pageMsg:");
		
		System.out.println(pm);
		s.append(pm).append("\r\n");
		s.append("  *debugMsg:");
		s.append(dm).append("\r\n");
		s.append("  *isJump:");
		s.append(isto).append("\r\n");
		s.append("  *toPage:");
		s.append(top).append("\r\n");
		s.append("*/").append("\r\n");
		
		s.append("        public static final String ").append(code).append("=\"").append(code).append("\";\r\n");
		
		
		
		
	}
	
	/**
	 * д����ļ�
	 * @param s
	 * @param outfilename ����ļ���
	 * @throws IOException 
	 */
	protected void writefile(StringBuffer s,String outfilename) throws IOException{
		
	
		OutputStreamWriter  fw=new OutputStreamWriter (new FileOutputStream(outfilename),"gb2312");		
		fw.write(s.toString());
		fw.close();
		
		
	}
	

}
