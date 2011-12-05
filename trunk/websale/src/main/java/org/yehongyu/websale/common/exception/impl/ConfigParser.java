package org.yehongyu.websale.common.exception.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Node;
import org.yehongyu.websale.common.utils.dom4jXmlParseMgr;

public class ConfigParser {

	private static ConfigParser cp = new ConfigParser();
	
	/**异常配置列表*/
	private static Map<String,ExceptionItem> eil;
	
	private ConfigParser(){}
	
	public static ConfigParser getParser(){
		return cp;
	}
	
	/**
	 * 返回exception.xml配置文件里的ExceptionItem列表
	 * @return
	 */
	public static Map<String,ExceptionItem> getExceptionItemList(){
		if(eil==null){
			//init it
			eil = new HashMap<String,ExceptionItem>();
			ClassLoader cl = getParser().getClass().getClassLoader();
			InputStream is = cl.getResourceAsStream("com/hc360/b2b/exception/exception.xml");
			
			dom4jXmlParseMgr dpr=new dom4jXmlParseMgr(is);
			List nodeList=dpr.getMuliElementNode("/Exception/ModeName/ExceptionItem");
			
			for (Iterator it = nodeList.iterator(); it.hasNext();) {
				Node n = (Node) it.next();
				
				String code=dpr.getElementAttributeValue(n,"code");
				String pm=dpr.getElementAttributeValue(n,"PageMessage");
				String dm=dpr.getElementAttributeValue(n,"DebugMessage");
				String isto=dpr.getElementAttributeValue(n,"IsTopage");
				String top=dpr.getElementAttributeValue(n, "TopageURL");
				
				ExceptionItem ei=new ExceptionItem();
				ei.setCode(code);
				ei.setPageMessage(pm);
				ei.setDebugMessage(dm);
				if ("Y".equals(isto) || "y".equals(isto)){
					ei.setIstopage(true);
				}else{
					ei.setIstopage(false);
				}
				
				ei.setTopageURL(top);
				
				eil.put(code, ei);
				
			}
						
			return eil;
		}else{			
			return eil;
		}
	}
	
	
}
