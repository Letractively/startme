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
 * 【类说明】读取配置文件信息的基础类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:01:58
 */
public class ConfigUtil {

	/** 日志对象 */
	private static final Logger log = Logger.getLogger(ConfigUtil.class);
	
    private static final Map<String,Configuration> configs = new HashMap<String,Configuration>();

    private static Configuration config;

    /** 加载类时声明一个私有Dao对象 */
    private final static ConfigUtil configDeal = new ConfigUtil();
    
    private ConfigUtil() {}
    
    /**
     * 【函数功能】读取配置文件，存放在本类Map中。
     * @param path 相对路径
     * @return 本类实例
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
	 * 【函数功能】读取配置文件，存放在本类Map中。
	 * @param path 绝对路径
	 * @return 本类实例
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
	 * 【函数功能】从配置文件取得指定关键字的属性字段的值
	 * 
	 * @param key
	 *            指定关键字
	 * @return String 属性字段的值
	 */
    public String getPropValue(String key) {
        return config.getString(key);
    }

    /**
     * 【函数功能】释放文件
     * @param path 配置文件路径
     * @return true or false
     */
    public static synchronized boolean release(String path) {
        if (configs.get(path) != null) {
            configs.remove(path);
            log.info("释放文件（"+path+")成功!");
            return true;
        } else {
            log.warn("文件("+path+")不存在,不需要释放!");
            return false;
        }
    }

    /**
     * 【函数功能】重新加载文件
     * @param path 配置文件的全路径
     * @return true or false
     */
    public static synchronized boolean reload(String path) {
        if (configs.get(path) == null) {
                InputStream in = null;
				try {
					in = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					log.error("文件（"+path+"）没有找到！",e);
					return false;
				}
                if (path.contains("properties")) {
                    Configuration nconfig = ConfigUtil.getPropertiesConfig(in);
                    configs.put(path, nconfig);
                } else if (path.contains("xml")) {
                    Configuration nconfig = ConfigUtil.getXMLConfig(in);
                    configs.put(path, nconfig);
                }
                log.info("文件:" + path + "重新加载成功!");
        } else {
        	log.info("文件("+path+")还在内存中无须重新加载!");
        }
        return true;
    }

    /**
     * 【函数功能】根据输入流(属性文件格式的)得到Configuration对象
     * @param in 输入流
     * @return Configuration对象
     * @throws ConfigurationException 
     */
    private static Configuration getPropertiesConfig(InputStream in) {
        FileConfiguration fc = new PropertiesConfiguration();
        try {
            fc.load(in);
        } catch (ConfigurationException ex) {
        	log.error("读取属性配置文件失败！",ex);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            	log.error("关闭属性配置文件输入流失败！",e);
            }
        }
        return fc;
    }

    /**
     * 【函数功能】根据输入流(XML文件格式的)得到Configuration对象
     * @param in 输入流
     * @return Configuration对象
     * @throws ConfigurationException 
     */
    private static Configuration getXMLConfig(InputStream in){
        FileConfiguration fc = new XMLConfiguration();
        try {
            fc.load(in);
        } catch (ConfigurationException ex) {
        	log.error("读取XML配置文件失败！",ex);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            	log.error("关闭XML配置文件输入流失败！",e);
            }
        }
        return fc;
    }

    /**
     * 【函数功能】调试主函数
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