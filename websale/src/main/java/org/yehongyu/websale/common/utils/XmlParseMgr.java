/*
 * $Id: XmlParseMgr.java,v 1.2 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * class description:本类主要是为解决XML文件的解析问题；提供了一些从XML文件得到指定的节点的各类值
 * 
 * @author: zengjuemin
 * 
 * @modifyauthor:
 * 
 * @version:v1.0 Date:2007-3-16
 *  
 */
public class XmlParseMgr {

    /**
     * Document class
     */
    private Document docDOM = null;

    /**
     * 本类的初化标志
     */
    private static boolean isInitFlag = false;

    int fileNumber = 0;

    //ArrayList[] attr=null;
    /**
     * 构造函数
     * 
     * @param strXMLFile
     */
    public XmlParseMgr(String strXMLFile) {
        init(strXMLFile);
    }

    /**
     * 
     * 
     * 方法说明:这是本类的一个初化方法，进行一些XML文件解析的初步验证
     * 
     * 参数:
     * 
     * @param strXMLFile--xml文件名（包括路径）
     * @return是否初华成功的标识 返回值类型: boolean
     *  
     */
    public boolean init(String strXMLFile) {
        if (strXMLFile == null || strXMLFile.equals("")) {
            System.out.println("没有指定文件名");
            isInitFlag = false;
            return isInitFlag;
        }
        try {
            File xmlFile = new File(strXMLFile);

            if (!xmlFile.exists()) {
                System.out.println("文件不存在");
                isInitFlag = false;
                return isInitFlag;
            }
            //验证XML文件
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            //不进行dtd验证
            factory.setValidating(false);
            //忽略注释
            factory.setIgnoringComments(true);
            factory.setCoalescing(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new xmlErrorHandler());
            // 解析文件
            docDOM = builder.parse(new File(strXMLFile));
        } catch (SAXException e) {
            isInitFlag = false;
        } catch (Exception parseExce) {
            isInitFlag = false;
            System.out.println("类初化错误!" + parseExce.getMessage());
        }
        return isInitFlag;
    }

    /**
     * 
     * 
     * 方法说明:本方法主要是对XML文件中重复元素的解析，通过指定要解析元素的名字及它在本节点中序 号而得到相应元素的值
     * 
     * 
     * 参数:
     * 
     * @param strElementName---要解析的元素名称
     * @param item--xml节点元素的序号
     * @return 返回值类型: String
     *  
     */
    public String getFileElementValue(String strElementName, int item) {
        String strValue = null;
        Node nodeElement = docDOM.getElementsByTagName(strElementName).item(
                item);
        if (nodeElement.hasChildNodes() == true
                && nodeElement.getFirstChild().getNodeType() == Node.TEXT_NODE) {
            strValue = nodeElement.getFirstChild().getNodeValue();
        } else {
            strValue = "";
        }
        return strValue;
    }

    /**
     * 
     * 
     * 方法说明:本方法主要是对XML文件中单一元素的解析，只需指定节点名称而得到该节点的值
     * 
     * 
     * 参数:
     * 
     * @param strElementName---要解析的元素名称
     * @return 返回值类型: String
     *  
     */
    public String getFileElementValue(String strElementName) {
        String strValue = null;
        Node nodeElement = docDOM.getElementsByTagName(strElementName).item(0);
        if (nodeElement.hasChildNodes() == true
                && nodeElement.getFirstChild().getNodeType() == Node.TEXT_NODE) {
            strValue = nodeElement.getFirstChild().getNodeValue();
        } else {
            strValue = "";
        }
        return strValue;
    }

    /**
     * 
     * 
     * 方法说明:本方法主要是对XML文件中指定元素(如果该元素有多个则只返回第一个元素的指定属性)的指定属性名，进行提取其属性值
     * 
     * 
     * 参数:
     * 
     * @param strElementName--元素名称
     * @param strElementAttr--元素的属性名称
     * @return 返回值类型: String
     *  
     */
    public String getUFileElementAttributeValue(String strElementName,
            String strElementAttr) {
        String strValue = null;
        Node nodeElement = docDOM.getElementsByTagName(strElementName).item(0);
        Element lElement = (Element) nodeElement;
        strValue = lElement.getAttribute(strElementAttr);
        return strValue;
    }

    /**
     * 方法说明:本方法主要是对XML文件中指定元素(如果该元素有多个则返回所有元素的指定属性)的指定属性名，进行提取其属性值
     * 
     * @param strElementName元素名称
     * @param strElementAttr属性名称
     * @return String[]
     */
    public String[] getFileElementAttributeValue(String strElementName,
            String strElementAttr) {
        int allElementAttrNum = this.getFileElementNumber(strElementName);
        String[] strValue = new String[allElementAttrNum];
        for (int i = 0; i < allElementAttrNum; i++) {
            Node nodeElement = docDOM.getElementsByTagName(strElementName)
                    .item(i);
            Element lElement = (Element) nodeElement;
            strValue[i] = lElement.getAttribute(strElementAttr);
        }
        return strValue;
    }

    /**
     * 
     * 
     * 方法说明:本方法主要是对XML文件中重复元素的解析，通过指元素名称而得到此元素的总数
     * 
     * 
     * 参数:
     * 
     * @param strElementName---要解析的元素名称
     * 
     * @return 返回值类型: int
     *  
     */
    public int getFileElementNumber(String strElementName) {
        int ElementNumber = 0;
        ElementNumber = docDOM.getElementsByTagName(strElementName).getLength();
        return ElementNumber;
    }

    /**
     * 
     * 
     * 方法说明:本方法主要是对XML文件中重复元素的解析,通过指定节点而得到该节点的所有值，并且把 这些放处一个ArrayLis中
     * 
     * 
     * 参数:
     * 
     * @param tagName--节点名称
     * @return 返回值类型: ArrayList
     *  
     */
    public ArrayList getValuesByTagName(String tagName) {
        ArrayList al = new ArrayList();
        NodeList list = docDOM.getElementsByTagName(tagName);
        int i;
        Node node;
        for (i = 0; i < list.getLength(); i++) {
            node = list.item(i);
            //Element testE=(Element)node;
            al.add(node.getFirstChild().getNodeValue());
        }
        return al;
    }

    /**
     * 方法说明:本方法主要是为了解决xml文件中某个元素同时有多个属性的情况，通过指定该元素名 及其所有素名称数组而得到一个与属性名称相对应的属性值
     * 
     * @param strElementName
     * @param strElementAttr
     * @return
     */
    public ArrayList getAllFileElementAttributeValue(String strElementName,
            String strElementAttr[]) {
        ArrayList al = new ArrayList();
        NodeList list = docDOM.getElementsByTagName(strElementName);

        int i;
        Node node;
        for (i = 0; i < list.getLength(); i++) {
            node = list.item(i);
            Element lElement = (Element) node;
            String strValue[] = null;
            int number = strElementAttr.length;
            strValue = new String[number];
            for (int k = 0; k < number; k++) {
                strValue[k] = lElement.getAttribute(strElementAttr[k]);
            }
            //Element testE=(Element)node;
            al.add(strValue);
        }
        return al;
    }

    /**
     * 方法说明:本方法主要是通过指写某一元素的具体某一具体属性名称及其值，而得到这一元素的所有属性
     * 
     * @param strElementName
     *            要解析的元素的名称
     * @param strElementAttr
     *            要解析的元素的属性名称
     * @param attrNa
     *            指定属性名称
     * @param attVa
     *            指定属性值
     * @return
     */
    public String[] getElAttrValsByAtrrNameAndValue(String strElementName,
            String strElementAttr[], String attrName, String attVa) {
        ArrayList itemattr = this.getAllFileElementAttributeValue(
                strElementName, strElementAttr);

        int byAttrNamePlace = -1;
        for (int k = 0; k < strElementAttr.length; k++) {
            if (strElementAttr[k].equals(attrName)) {
                byAttrNamePlace = k;
                break;
            }
        }

        String[] Rattr = null;
        for (int i = 0; i < itemattr.size(); i++) {
            String[] at1 = (String[]) itemattr.get(i);
            if (at1[byAttrNamePlace].equals(attVa)) {
                Rattr = at1;
                break;
            }
        }
        return Rattr;
    }

    /**
     * 
     * @param rootName
     * @return
     */
    public Node getNode(String rootName) {
        return docDOM.getElementsByTagName(rootName).item(0);
    }

    /**
     * 创建文件夹的函数
     * 
     * @param sourcepathName
     * @param pathName
     */
    public void CreateFilePath(String sourcepathName, String pathName) {

        try {
            if (!(new File(sourcepathName + pathName).isDirectory())) {
                new File(sourcepathName + pathName).mkdir();
                System.out.println("run this is");
            }

            else {
                new File(sourcepathName + pathName + "2003-04-14" + fileNumber)
                        .mkdir();
            }

        } catch (SecurityException e) {
            System.out.println("can not make directory");
        }
        fileNumber++;
    }

    public ArrayList testXML(Node node) {
        ArrayList lsnode = new ArrayList();

        String lsStr = "";
        ArrayList attr = new ArrayList();

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {

            if (node.getChildNodes().item(i).getNodeType() != 3) {
                lsnode.add(node.getChildNodes().item(i));
                Element lElement = (Element) node.getChildNodes().item(i);
                attr.add(lElement.getAttribute("title") + "/");

                //Element lElement_x = (Element) node.getParentNode();
                //System.out.println("X_title==="+lElement.getAttribute("title"));
                //System.out.println(attr);
            }
        }

        for (int i = 0; i < attr.size(); i++) {
            System.out.println((String) attr.get(i));
        }

        //System.out.println("number==="+lsnode.size());

        for (int i = 0; i < lsnode.size(); i++) {
            //System.out.println(i);
            testXML((Node) lsnode.get(i));
        }
        //System.out.println("第 "+fileNumber+"次");
        //fileNumber++;
        return lsnode;

    }

    public static void main(String args[]) {
        XmlParseMgr EXML = new XmlParseMgr(
                "D://work//e_work//BJCS//work//menu.xml"); 
    }

}

/**
 * 
 * class description:本类是一个内部类，主要是定义了一些XML解析的错误名柄
 *
 * @author: zengjuemin
 * 
 * @modifyauthor:
 *
 * @version:v1.0  Date:2007-3-16
 *
 */

class xmlErrorHandler implements org.xml.sax.ErrorHandler {

    /**
     * xml解析过程中信息提示的一种：警告
     */
    public void warning(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    /**
     * xml解析过程中信息提示的一种：错误
     */
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    /**
     * xml解析过程中信息提示的一种：致命错误
     */
    public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }
}
