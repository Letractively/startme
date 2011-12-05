/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.common.networker;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 【类说明】根据传入URL得到网页原码信息的类
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 11:56:51 PM
 */
public class UrlSession {

    /**
     * 【函数功能】获得页面源码(对输入流按ISO-8859-1编码处理)
     * @param urlString 页面地址
     * @return String 页面源码
     */
    public String getHtmlCode(String urlString) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			URL url = new URL((urlString));
			URLConnection con = url.openConnection();
			in = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"ISO-8859-1"));
			String line = null;
			while ((line = in.readLine()) != null) {
				result.append(line + "\r\n");
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("Unable to connect to URL: " + urlString);
		} catch (IOException e) {
			System.out.println("IOException when connecting to URL: "
					+ urlString);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					System.out
							.println("Exception throws at finally close reader when connecting to URL: "
									+ urlString);
				}
			}
		}
		return result.toString();
	}

    /**
     * 【函数功能】获得页面源码(不对输入流进行字符编码处理）
     * @param urlString 页面地址
     * @return String 页面源码
     */
    public String getHtmlCodeUnCharset(String urlString) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			URL url = new URL((urlString));
			URLConnection con = url.openConnection();
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				result.append(line + "\r\n");
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("Unable to connect to URL: " + urlString);
		} catch (IOException e) {
			System.out.println("IOException when connecting to URL: "
					+ urlString);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					System.out
							.println("Exception throws at finally close reader when connecting to URL: "
									+ urlString);
				}
			}
		}
		return result.toString();
	}
    
    public static void main(String[] args){
    	UrlSession us = new UrlSession();
    	String rs = us.getHtmlCodeUnCharset("http://auction1.taobao.com/auction/item_detail-db1-80172301829364fa966ad1c4b34605f4.jhtml");
    	System.out.println(rs);
    	
    }
}
