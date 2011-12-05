package org.yehongyu.websale.common.exception;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

/**
 * 【类说明】
 * @author yehongyu.org
 * @version 1.0 Dec 4, 2007 6:31:00 PM
 */
public class MessageResource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MessageResource.class);
	private ResourceBundle rb;
	private boolean isEscape=false;
	
	MessageResource(){
		this(Locale.getDefault(),null);
		
	}
	
	MessageResource(Locale locale,String path){
		rb=ResourceBundle.getBundle(path,locale);
	}
	/**
	 * @return 对当前的消息资源对象进行javascript转义处理
	 */
	public MessageResource escape(){
		isEscape=true;
		return this;
	}
	/**
	 * @return 撤销对当前的消息资源对象的javascript转义处理
	 */
	public MessageResource unescape(){
		isEscape=false;
		return this;
	}
	/**
	 * @param key 消息键，一般来源于接口@see b2b.resource.Messages中定义的常量
	 * @return 返回@param key所对应的消息字符串
	 */
	public String getMessage(String key){
		String msg=rb.getString(key);
		if(isEscape && msg!=null)
			return StringEscapeUtils.escapeJavaScript(msg);
		return msg;
	}
	/**
	 * @param key 消息键，一般来源于接口@see b2b.resource.Messages中定义的常量
	 * @return 返回所有消息键为@param key所对应的消息
	 */
	public String[] getMessages(String key){
		String[] msgs=rb.getStringArray(key);
		if(isEscape&&msgs.length>0){
			for(int i=0;i<msgs.length;i++){
				msgs[i]=StringEscapeUtils.escapeJavaScript(msgs[i]);
			}
		}
		return msgs;
	}
	/**
	 * @param key1 消息键
	 * @param key2 占位符所对应的消息键
	 * @return key1所对应消息，其占位符刚好是key2所对应的消息
	 */
	public String getBothFromKey(String key1,String key2){
		return getMessage(key1,rb.getString(key2));
	}
    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will be returned by
     * this method if no resource bundle has been configured.
     *
     *  for the system default Locale
     * @param key The message key to look up
     * @param args An array of replacement parameters for placeholders
     */
    public String getMessage(String key, Object args[]) {
        String formatString = getMessage(key);
        MessageFormat format = new MessageFormat(formatString);
        format.setLocale(getLocale());
        return format.format(args);        
    }
    /**
     * @param key 消息键
     * @param arg 占位符
     * @return 返回key所对应的消息，其中的占位符用arg代替
     */
    public String getMessage(String key, Object arg) {
    	return getMessage(key,new Object[]{arg});        
    }
    public String getMessage(String key, Object arg0,Object arg1) {
    	return getMessage(key,new Object[]{arg0,arg1});        
    }
    public Locale getLocale(){
    	return rb.getLocale();
    }
    /**
     * @return
     */
    public List<String> getAllKeys(){
    	return Collections.list(rb.getKeys());
    }
    public void log(String message) {
        log.debug(message);
    }

    /**
     * Log a message and exception to the Writer that has been configured
     * for our use.
     *
     * @param message The message to be logged
     * @param throwable The exception to be logged
     */
    public void log(String message, Throwable throwable) {
        log.debug(message, throwable);
    }
    
}

