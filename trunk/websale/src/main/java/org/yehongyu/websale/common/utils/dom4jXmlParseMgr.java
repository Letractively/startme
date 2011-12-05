/*
 * $Id: dom4jXmlParseMgr.java,v 1.5 2007/04/20 10:33:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.yehongyu.websale.common.util.Convert;

/**
 * class description:����һ������dom4j����xml�ļ������Ļ�����,��Ҫ�ṩ������xmlpath��ص���Ϣ
 *
 * @author: zengjuemin
 * 
 * @modifyauthor:
 *
 * @version:v1.0  Date:2007-3-19
 * 
 */
public class dom4jXmlParseMgr {

    /**
     * ȫ�ֵ�Document����
     */
    public Document document;

//    public static void main(String[] args) {
//        //dom4jXmlParseMgr dpr=new dom4jXmlParseMgr("F:/e30work/b2b/bin/hc360/b2b/netWorker/Hhc360URLXML.xml");
//        //System.out.println(dpr.getElementValue("/commonURLXML/picture_URL/pictureType[1]"));
//        //System.out.println(dpr.getElementAttributeValue("/commonURLXML/page_URL/pageType[1]","firstpageUrl"));
//        //dpr.getMuliElementNode("/commonURLXML/page_URL/pageType");
//        //dpr.getMuliElementNodeValue("/commonURLXML/page_URL/pageType");
//        
//        dom4jXmlParseMgr dpr=new dom4jXmlParseMgr("d:/ExceptionXML.xml");
//        List nodeList=dpr.getMuliElementNode("/Exception/ModeName/ExceptionItem");
//        String tAttributeValue=dpr.getElementAttributeValue((Node)nodeList.get(0),"code");
//        System.out.println("run this is==="+tAttributeValue);
//        //dpr.getMuliElementNodeValue("/Exception/ModeName/ExceptionItem");
//        //dpr.getMuliElementAttribute("/Exception/ModeName/ExceptionItem","code");
//        //dpr.getMuliElementAttributeValue("/Exception/ModeName[1]/ExceptionItem[1]","code");
//    }

    /**
     * ���캯��,���ṩxml�ļ�·���ķ�ʽ���ñ���
     *
     */
    public dom4jXmlParseMgr(String xmlFile){
        try {
			init(new FileInputStream(new File(xmlFile)));
		} catch (FileNotFoundException e) {
			//TODO...
		}
    }
    
    public dom4jXmlParseMgr(InputStream is) {
    	init(is);
    }
    
    /**
     * ���캯�� ���ṩDocument�ĵ��ķ�ʽ���ñ���
     *
     */
    public dom4jXmlParseMgr(Document _document){
        document=_document;
    }
    
    /**
     *  
     * Methods Descrip:��ʼ������
     * @param xmlFile:xml�ļ�·��
     *
     */
    private void init(InputStream is) {
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     *  
     * Methods Descrip:��������xmlpath·���õ��ýڵ��ֵ
     * <pre>���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML>  
     * </pre>           
     * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType[1]
     * @return String:�ڵ�ֵ ��busin
     *
     */
    public String getElementValue(String xmlpath){
    	//System.out.println("run this is===="+xmlpath);
        Node node = document.selectSingleNode(xmlpath);
        //String res = node.getText();
        //System.out.println("run this is res===="+res);
        return (node == null) ? null : node.getText();
    }
    
    /**
     *  
     * Methods Descrip:����xmlPath·���õ���·���µ����нڵ�
     * <pre>
     * ���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML>  
     * </pre>           
     * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType
     * @return List:�ڵ������ NodeList
     *
     */
    public List getMuliElementNode(String xmlpath){
        List NodeList=new ArrayList();
        NodeList=document.selectNodes(xmlpath);
       return NodeList; 
    }
    
    /**
     *  
     * Methods Descrip:����xmlPath·���õ���·���µ����нڵ��ֵ
     * <pre>
     * ���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML>
     * </pre>             
     * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType
     * @return List:�ڵ������ NodeList
     *
     */
    public List getMuliElementNodeValue(String xmlpath){
        List NodeValueList=new ArrayList();
        List NodeList=getMuliElementNode( xmlpath);
        for(int i=0;i<NodeList.size();i++){
            Node node=(Node)NodeList.get(i);
            String value=node.getText();
            NodeValueList.add(value);
        }
        return NodeValueList;
    }
    
    
   /**
    *  
    * Methods Descrip:��������xmlpath·���õ��ýڵ�ָ���������Ƶ�����ֵ
    * <pre>
    * ���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML>  
     *</pre>
    * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType[1]
    * @param attribueName:ָ������������ ��:typeid
    * @return String:����ֵ �� 0
    *
    */
    public String getElementAttributeValue(String xmlpath,String attribueName){
        Attribute attribute  =(Attribute) document.selectSingleNode(xmlpath+"/@"+attribueName);
        String res=attribute.getValue();
        return res;
    }
    
    /**
     *  
     * Methods Descrip:���ݴ���Ľڵ����,ȡ�øö����ָ���������Ƶ�����ֵ
     * @param node:�������ԵĽڵ����
     * @param attribueName:ָ����˾������
     * @return String:����ֵ
     *
     */
    public String getElementAttributeValue(Node node,String attribueName){
        Attribute attribute  =(Attribute)node.selectSingleNode("@"+attribueName);
        String res=attribute.getValue();
        return res;
    }
    
    /**
     *  
     * Methods Descrip:��������xmlpath·���õ���·�������нڵ�ָ���������Ƶ����Խڵ�
    * ���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML>  
    * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType
    * @param attribueName:ָ������������ ��:typeid
    * @return List:���Խڵ� �� AttributeList
     *
     */
    public List getMuliElementAttribute(String xmlpath,String attribueName){
       List  AttributeList=document.selectNodes(xmlpath+"/@"+attribueName);
       return AttributeList;
    }
 
    /**
     *  
     * Methods Descrip:��������xmlpath·���õ���·�������нڵ�ָ���������Ƶ����Խڵ��ֵ
    * <pre>���� xml�ļ�����:
     *                <?xml version="1.0" encoding="UTF-8"?>
     *                    <commonURLXML>
     *                                  <picture_URL>
     *                                             <pictureType typeid="0" typedescr="�̻�ͼƬ" serverName="imgserver">busin</pictureType>
     *                                             <pictureType typeid="1" typedescr="��ƷͼƬ" serverName="imgserver">product</pictureType>
     *                                  <picture_URL>
     *                    <commonURLXML> 
     * </pre> 
    * @param xmlpath:ָ���ڵ��xmlpath·�� ��:/commonURLXML/picture_URL/pictureType
    * @param attribueName:ָ������������ ��:typeid
    * @return List:���Խڵ� �� AttributeListValue
     *
     */
    public List getMuliElementAttributeValue(String xmlpath,String attribueName){
        List  AttributeList=getMuliElementAttribute(xmlpath,attribueName);
        List  AttributeListValue=new ArrayList();
        for(int i=0;i<AttributeList.size();i++){
            Attribute attribute  =(Attribute)AttributeList.get(i);
            String res=attribute.getValue();
            AttributeListValue.add(res);
        }
        return AttributeListValue;
     }
    
    public int getIntValue(String xmlPath) {
    	return Convert.getInt(this.getElementValue(xmlPath), 0);
    }
    public long getLongValue(String xmlPath) {
    	return Convert.getLong(this.getElementValue(xmlPath), 0);
    }
    public String getStringValue(String xmlPath) {
    	return this.getElementValue(xmlPath);
    }
}