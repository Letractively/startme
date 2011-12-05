/*
 * $Id: GetServerIp.java,v 1.2 2007/04/03 06:05:33 zhaosy Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package org.yehongyu.websale.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import org.yehongyu.websale.common.util.ConfigUtil;

/**
 * class description:这是一个为了取得集群服务器内各节点服务器的IP的类, 目的是为了在出了错误的 情况下能快速地定位到相应节点服器上
 * 
 * @author:
 * 
 * @modifyauthor:zengjuemin
 * 
 * @version:v1.0 Date:2007-3-13
 * 
 */
public class GetServerIp {
	private static String currentIp;

	// 此常为b2b这个包中的属性文件的路径
	private static String B2BFROPERTIESFILEPATH = "";
	static {
		try {
			B2BFROPERTIESFILEPATH = getClassFilePath()
					+ "hc360/b2b/config/b2b.properties";
		} catch (Exception e) {
			// TODO
		}
		currentIp = getIP();
		if (currentIp != null && currentIp.length() > 7) {
			currentIp = currentIp.substring(currentIp.length() - 7, currentIp
					.length());
		} else {
			currentIp = "";
		}
	}

	/**
	 * 【函数功能】得到当前class文件的路径
	 * @return 返回的是你classes环境的根目录路径
	 * @throws Exception
	 */
	private static String getClassFilePath() throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL sourceUrl = loader.getResource("");// 如果是""系统返回的是你classes环境的根目录路径
		return URLDecoder.decode(sourceUrl.getFile(), "GBK");
	}

	/**
	 * 【函数功能】得到实际的服务器节点的IP
	 * @return currentIP
	 */
	private static String getIP() {
		// String IpFilePathName="";
		String currentIP = "";
		// get OS type
		String osType = System.getProperty("os.name");
		if (osType != null && !osType.equals("")
				&& osType.toLowerCase().indexOf("windows") < 0) {
			String curIpFile = ConfigUtil.getConfig(B2BFROPERTIESFILEPATH)
					.getPropValue("B2B_CURRENT_IP_FILE_PATH");

			if (curIpFile != null && !curIpFile.equals("")) {
				File file = new File(curIpFile);
				StringBuffer result = new StringBuffer();
				if (file.exists()) {
					BufferedReader in = null;

					try {
						in = new BufferedReader(new InputStreamReader(
								new FileInputStream(file), "ISO-8859-1"));

						String line = null;

						while ((line = in.readLine()) != null) {
							result.append(line);
						}
						in.close();

					} catch (MalformedURLException me) {

					} catch (IOException ie) {

					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (Exception ex) {

							}
						}
					}

				}
				currentIP = result.toString();

			}

		}

		return currentIP;
	}

	/**
	 * 构造函数
	 * 
	 */
	private GetServerIp() {
	}

	/**
	 * 【函数功能】取得当前服务器的IP
	 * @return currentIp
	 */
	public static String getLastSevenCharacter() {
		return currentIp;
	}

	 public static void main(String[] args) {
	 System.out.println(GetServerIp.getLastSevenCharacter());
	 }
}
