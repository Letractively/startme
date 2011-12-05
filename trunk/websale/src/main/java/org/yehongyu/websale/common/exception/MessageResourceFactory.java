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
	 * @return ���ص�ǰϵͳȱʡ�������Ե���Ϣ��Դ
	 */
	public static MessageResource getDefault(){
		return get(Locale.getDefault());
	}
	/**
	 * @return ���������ַ�����Ϣ��Դ
	 */
	public static MessageResource get(){
		return get(Locale.CHINA);
	}
	/**
	 * @param locale ָ���Ĺ�������
	 * @return ����ָ���������Ե���Ϣ��Դ
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
	 * @param locale ָ���Ĺ������ԣ�����zh_CN(��������),en_US(����Ӣ��)
	 * @return ����ָ���������Ե���Ϣ��Դ
	 */
	public static MessageResource get(String locale){
		Locale l=new Locale(locale);
		return get(l);
	}

}

