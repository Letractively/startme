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
 * class description:����һ��Ϊ��ȡ�ü�Ⱥ�������ڸ��ڵ��������IP����, Ŀ����Ϊ���ڳ��˴���� ������ܿ��ٵض�λ����Ӧ�ڵ������
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

	// �˳�Ϊb2b������е������ļ���·��
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
	 * ���������ܡ��õ���ǰclass�ļ���·��
	 * @return ���ص�����classes�����ĸ�Ŀ¼·��
	 * @throws Exception
	 */
	private static String getClassFilePath() throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL sourceUrl = loader.getResource("");// �����""ϵͳ���ص�����classes�����ĸ�Ŀ¼·��
		return URLDecoder.decode(sourceUrl.getFile(), "GBK");
	}

	/**
	 * ���������ܡ��õ�ʵ�ʵķ������ڵ��IP
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
	 * ���캯��
	 * 
	 */
	private GetServerIp() {
	}

	/**
	 * ���������ܡ�ȡ�õ�ǰ��������IP
	 * @return currentIp
	 */
	public static String getLastSevenCharacter() {
		return currentIp;
	}

	 public static void main(String[] args) {
	 System.out.println(GetServerIp.getLastSevenCharacter());
	 }
}
