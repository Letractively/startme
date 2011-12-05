package org.yehongyu.websale.common.exception;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * @author ZhanKai
 *
 */
public class MessageResourceFactory{
	private static Map<String,MessageResource> map=Collections.synchronizedMap(new WeakHashMap<String,MessageResource>());
	/**
	 * @return 返回当前系统缺省国家语言的消息资源
	 */
	public static MessageResource getDefault(){
		return get(Locale.getDefault());
	}
	/**
	 * @return 返回中文字符的消息资源
	 */
	public static MessageResource get(){
		return get(Locale.CHINA);
	}
	/**
	 * @param locale 指定的国家语言
	 * @return 返回指定国家语言的消息资源
	 */
	public static MessageResource get(Locale locale){
		MessageResource mr=(MessageResource)map.get(locale.toString());
		if(mr==null){
			synchronized(map){
				mr=new MessageResource(locale, "org/yehongyu/websale/common/exception/messages");
				map.put(locale.toString(),mr);
			}
		}
		return mr;
	}
	/**
	 * @param locale 指定的国家语言，例如zh_CN(简体中文),en_US(美国英语)
	 * @return 返回指定国家语言的消息资源
	 */
	public static MessageResource get(String locale){
		Locale l=new Locale(locale);
		return get(l);
	}

}

