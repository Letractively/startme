/**
 * $Id$
 * Copyright(c) 2007-	GoodIdeals.com,All Rights Reserved.
 */
package org.yehongyu.websale.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

/**
 * ����˵������ȡ�����ļ���Ϣ�Ļ�����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:01:58
 */
public class ConfigUtil {

	/** ��־���� */
	private static final Logger log = Logger.getLogger(ConfigUtil.class);
	
    private static final Map<String,Configuration> configs = new HashMap<String,Configuration>();

    private static Configuration config;

    /** ������ʱ����һ��˽��Dao���� */
    private final static ConfigUtil configDeal = new ConfigUtil();
    
    private ConfigUtil() {}
    
    /**
     * ���������ܡ���ȡ�����ļ�������ڱ���Map�С�
     * @param path ���·��
     * @return ����ʵ��
     * @throws ConfigurationException
     */
	public static ConfigUtil getConfig(String path) {
		if (configs.get(path) == null) {
			InputStream in = ConfigUtil.class.getResourceAsStream(path);
			if (path.contains("properties")) {
				config = ConfigUtil.getPropertiesConfig(in);
				configs.put(path, config);
			} else if (path.contains("xml")) {
				config = ConfigUtil.getXMLConfig(in);
				configs.put(path, config);
			}
		} else {
			config = (Configuration) configs.get(path);
		}
		return configDeal;
	}
    
	/**
	 * ���������ܡ���ȡ�����ļ�������ڱ���Map�С�
	 * @param path ����·��
	 * @return ����ʵ��
	 * @throws FileNotFoundException
	 * @throws ConfigurationException
	 */
    public static ConfigUtil getConfigByRealPath(String path) {
		if (configs.get(path) == null) {
			try {
				InputStream in = new FileInputStream(path);
				if (path.contains("properties")) {
					config = ConfigUtil.getPropertiesConfig(in);
					configs.put(path, config);
				} else if (path.contains("xml")) {
					config = ConfigUtil.getXMLConfig(in);
					configs.put(path, config);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			config = (Configuration) configs.get(path);
		}
		return configDeal;
	}

    /**
	 * ���������ܡ��������ļ�ȡ��ָ���ؼ��ֵ������ֶε�ֵ
	 * 
	 * @param key
	 *            ָ���ؼ���
	 * @return String �����ֶε�ֵ
	 */
    public String getPropValue(String key) {
        return config.getString(key);
    }

    /**
     * ���������ܡ��ͷ��ļ�
     * @param path �����ļ�·��
     * @return true or false
     */
    public static synchronized boolean release(String path) {
        if (configs.get(path) != null) {
            configs.remove(path);
            log.info("�ͷ��ļ���"+path+")�ɹ�!");
            return true;
        } else {
            log.warn("�ļ�("+path+")������,����Ҫ�ͷ�!");
            return false;
        }
    }

    /**
     * ���������ܡ����¼����ļ�
     * @param path �����ļ���ȫ·��
     * @return true or false
     */
    public static synchronized boolean reload(String path) {
        if (configs.get(path) == null) {
                InputStream in = null;
				try {
					in = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					log.error("�ļ���"+path+"��û���ҵ���",e);
					return false;
				}
                if (path.contains("properties")) {
                    Configuration nconfig = ConfigUtil.getPropertiesConfig(in);
                    configs.put(path, nconfig);
                } else if (path.contains("xml")) {
                    Configuration nconfig = ConfigUtil.getXMLConfig(in);
                    configs.put(path, nconfig);
                }
                log.info("�ļ�:" + path + "���¼��سɹ�!");
        } else {
        	log.info("�ļ�("+path+")�����ڴ����������¼���!");
        }
        return true;
    }

    /**
     * ���������ܡ�����������(�����ļ���ʽ��)�õ�Configuration����
     * @param in ������
     * @return Configuration����
     * @throws ConfigurationException 
     */
    private static Configuration getPropertiesConfig(InputStream in) {
        FileConfiguration fc = new PropertiesConfiguration();
        try {
            fc.load(in);
        } catch (ConfigurationException ex) {
        	log.error("��ȡ���������ļ�ʧ�ܣ�",ex);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            	log.error("�ر����������ļ�������ʧ�ܣ�",e);
            }
        }
        return fc;
    }

    /**
     * ���������ܡ�����������(XML�ļ���ʽ��)�õ�Configuration����
     * @param in ������
     * @return Configuration����
     * @throws ConfigurationException 
     */
    private static Configuration getXMLConfig(InputStream in){
        FileConfiguration fc = new XMLConfiguration();
        try {
            fc.load(in);
        } catch (ConfigurationException ex) {
        	log.error("��ȡXML�����ļ�ʧ�ܣ�",ex);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            	log.error("�ر�XML�����ļ�������ʧ�ܣ�",e);
            }
        }
        return fc;
    }

    /**
     * ���������ܡ�����������
     * @param arg
     */
    public static void main(String[] arg) {
        //String path = "/hc360/b2b/config/b2b.properties";
    	//D:\yehy\workspace\googideals\src\com\goodideals\admin\common\\util\test.properties
    	String path = "D:\\yehy\\workspace\\googideals\\src\\com\\goodideals\\admin\\common\\util\\test.properties";
        ConfigUtil config = ConfigUtil.getConfigByRealPath(path);

        String value = config.getPropValue("mypr");
        System.out.println("run this is====" + value);
        ConfigUtil.release(path);
    }
}