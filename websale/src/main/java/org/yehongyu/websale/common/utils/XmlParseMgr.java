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
 * class description:������Ҫ��Ϊ���XML�ļ��Ľ������⣻�ṩ��һЩ��XML�ļ��õ�ָ���Ľڵ�ĸ���ֵ
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
     * ����ĳ�����־
     */
    private static boolean isInitFlag = false;

    int fileNumber = 0;

    //ArrayList[] attr=null;
    /**
     * ���캯��
     * 
     * @param strXMLFile
     */
    public XmlParseMgr(String strXMLFile) {
        init(strXMLFile);
    }

    /**
     * 
     * 
     * ����˵��:���Ǳ����һ����������������һЩXML�ļ������ĳ�����֤
     * 
     * ����:
     * 
     * @param strXMLFile--xml�ļ���������·����
     * @return�Ƿ�����ɹ��ı�ʶ ����ֵ����: boolean
     *  
     */
    public boolean init(String strXMLFile) {
        if (strXMLFile == null || strXMLFile.equals("")) {
            System.out.println("û��ָ���ļ���");
            isInitFlag = false;
            return isInitFlag;
        }
        try {
            File xmlFile = new File(strXMLFile);

            if (!xmlFile.exists()) {
                System.out.println("�ļ�������");
                isInitFlag = false;
                return isInitFlag;
            }
            //��֤XML�ļ�
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            //������dtd��֤
            factory.setValidating(false);
            //����ע��
            factory.setIgnoringComments(true);
            factory.setCoalescing(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new xmlErrorHandler());
            // �����ļ�
            docDOM = builder.parse(new File(strXMLFile));
        } catch (SAXException e) {
            isInitFlag = false;
        } catch (Exception parseExce) {
            isInitFlag = false;
            System.out.println("���������!" + parseExce.getMessage());
        }
        return isInitFlag;
    }

    /**
     * 
     * 
     * ����˵��:��������Ҫ�Ƕ�XML�ļ����ظ�Ԫ�صĽ�����ͨ��ָ��Ҫ����Ԫ�ص����ּ����ڱ��ڵ����� �Ŷ��õ���ӦԪ�ص�ֵ
     * 
     * 
     * ����:
     * 
     * @param strElementName---Ҫ������Ԫ������
     * @param item--xml�ڵ�Ԫ�ص����
     * @return ����ֵ����: String
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
     * ����˵��:��������Ҫ�Ƕ�XML�ļ��е�һԪ�صĽ�����ֻ��ָ���ڵ����ƶ��õ��ýڵ��ֵ
     * 
     * 
     * ����:
     * 
     * @param strElementName---Ҫ������Ԫ������
     * @return ����ֵ����: String
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
     * ����˵��:��������Ҫ�Ƕ�XML�ļ���ָ��Ԫ��(�����Ԫ���ж����ֻ���ص�һ��Ԫ�ص�ָ������)��ָ����������������ȡ������ֵ
     * 
     * 
     * ����:
     * 
     * @param strElementName--Ԫ������
     * @param strElementAttr--Ԫ�ص���������
     * @return ����ֵ����: String
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
     * ����˵��:��������Ҫ�Ƕ�XML�ļ���ָ��Ԫ��(�����Ԫ���ж���򷵻�����Ԫ�ص�ָ������)��ָ����������������ȡ������ֵ
     * 
     * @param strElementNameԪ������
     * @param strElementAttr��������
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
     * ����˵��:��������Ҫ�Ƕ�XML�ļ����ظ�Ԫ�صĽ�����ͨ��ָԪ�����ƶ��õ���Ԫ�ص�����
     * 
     * 
     * ����:
     * 
     * @param strElementName---Ҫ������Ԫ������
     * 
     * @return ����ֵ����: int
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
     * ����˵��:��������Ҫ�Ƕ�XML�ļ����ظ�Ԫ�صĽ���,ͨ��ָ���ڵ���õ��ýڵ������ֵ�����Ұ� ��Щ�Ŵ�һ��ArrayLis��
     * 
     * 
     * ����:
     * 
     * @param tagName--�ڵ�����
     * @return ����ֵ����: ArrayList
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
     * ����˵��:��������Ҫ��Ϊ�˽��xml�ļ���ĳ��Ԫ��ͬʱ�ж�����Ե������ͨ��ָ����Ԫ���� ��������������������õ�һ���������������Ӧ������ֵ
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
     * ����˵��:��������Ҫ��ͨ��ָдĳһԪ�صľ���ĳһ�����������Ƽ���ֵ�����õ���һԪ�ص���������
     * 
     * @param strElementName
     *            Ҫ������Ԫ�ص�����
     * @param strElementAttr
     *            Ҫ������Ԫ�ص���������
     * @param attrNa
     *            ָ����������
     * @param attVa
     *            ָ������ֵ
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
     * �����ļ��еĺ���
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
        //System.out.println("�� "+fileNumber+"��");
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
 * class description:������һ���ڲ��࣬��Ҫ�Ƕ�����һЩXML�����Ĵ�������
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
     * xml������������Ϣ��ʾ��һ�֣�����
     */
    public void warning(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    /**
     * xml������������Ϣ��ʾ��һ�֣�����
     */
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }

    /**
     * xml������������Ϣ��ʾ��һ�֣���������
     */
    public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
        throw e;
    }
}
