/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale;

/**
 * ����˵����Ӧ�ó�����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����11:12:35
 */
public interface AppConstants {
	
	/** VO����������ҳ�湫�� */
	public static final String VO_NAME_COMMON = "vo";
    /** VO������������������ */
    public static final String VO_NAME_PARAM = "param";
	/** VO������������ҳ���� */
	public static final String VO_NAME_PAGE = "pageBean";
    /** VO����������������Ϣ���� */
    public static final String VO_NAME_OPTINFO = "optinfo";
    /** VO�������������ڸ�ʽ������ */
    public static final String VO_NAME_DATE = "date";
    /** VO������������������(��ʾ���ذ�ť) */
    public static final String VO_NAME_NAV = "opstr";
    /** VO������������Ŀ����(����ʾ���ذ�ť) */
    public static final String VO_NAME_MENU_NAV = "navstr";
    /** VO�������������µ�¼������Ϣ */
    public static final String VO_NAME_RELOGIN = "reLoginError";
	/** Manager�෵�سɹ� */
	public static final String MANAGER_BACK_SUCCESS = "ok";
	/** ���ڸ�ʽ������yyyy-MM-dd */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /** ���ڸ�ʽ������yyyy-MM-dd HH:mm:ss */
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/** �ϴ��ļ�����չ�� */
	public static final String UPLOAD_IMAGE_EXTNAME = ".gif|.jpg|.jpeg|.png|.bmp";
	
	/** ��֤�볣�� */
	public static final String VERIFYCODE = "yehyVerifyCode";
	/** �û�Session���� */
	public static final String SESSIONKEY = "yehySession";
	/** �ָ����� */
	public static final String SPLIT_DEFAULT = ",";
	/** �����ָ����� */
	public static final String SPLIT_NAV = " �� ";
	/** �����û�(ӵ�����еĽ�ɫ) */
	public static final String DEF_SYSADMIN = "superadmin";
	
	/** ������MOD_��ͷ�ı�����ʾ��ģ���� */
	public static final String MOD_COMMON = ""; // ����ģ��,��ʾ���еĶ���Ȩ��,�������¼
	public static final String MOD_PUBLIC_PART = "001001001";
	public static final String MOD_PUBLIC_USER = "001001002";
	public static final String MOD_PUBLIC_PRODUCT = "002001";
	public static final String MOD_WEB_BOARD = "005003";
	

	
	/** �����ļ��й�����ͷ��� */
	public static final String MANAGER_PREFIX = "business";
	// �����ļ�(WEB-INF/conf/AppConfig.properties)������ͷ������ӵ�����
	public static final String TestManager = MANAGER_PREFIX + ".TestManager";
	public static final String PermissionManager = MANAGER_PREFIX + ".PermissionManager";
	public static final String ProductManager = MANAGER_PREFIX + ".ProductManager";
	public static final String WebManager = MANAGER_PREFIX + ".WebManager";
		
	/** �����ļ���DAO��ͷ��� */
	public static final String DAO_PREFIX = "dao";
	// �����ļ�(WEB-INF/conf/AppConfig.properties)DAO��ͷ������ӵ�����
	public static final String TestDao = DAO_PREFIX + ".TestDao";
	public static final String PermissionDao = DAO_PREFIX + ".PermissionDao";
	public static final String ProductDao = DAO_PREFIX + ".ProductDao";
	public static final String WebDao = DAO_PREFIX + ".WebDao";
	


}
