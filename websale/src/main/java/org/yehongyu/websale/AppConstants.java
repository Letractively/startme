/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale;

/**
 * 【类说明】应用常量类
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午11:12:35
 */
public interface AppConstants {
	
	/** VO对象名——页面公共 */
	public static final String VO_NAME_COMMON = "vo";
    /** VO对象名——参数对象 */
    public static final String VO_NAME_PARAM = "param";
	/** VO对象名——分页对象 */
	public static final String VO_NAME_PAGE = "pageBean";
    /** VO对象名——操作信息对象 */
    public static final String VO_NAME_OPTINFO = "optinfo";
    /** VO对象名——日期格式化对象 */
    public static final String VO_NAME_DATE = "date";
    /** VO对象名——操作导航(显示返回按钮) */
    public static final String VO_NAME_NAV = "opstr";
    /** VO对象名——栏目导航(不显示返回按钮) */
    public static final String VO_NAME_MENU_NAV = "navstr";
    /** VO对象名——重新登录错误信息 */
    public static final String VO_NAME_RELOGIN = "reLoginError";
	/** Manager类返回成功 */
	public static final String MANAGER_BACK_SUCCESS = "ok";
	/** 日期格式符——yyyy-MM-dd */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    /** 日期格式符——yyyy-MM-dd HH:mm:ss */
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/** 上传文件的扩展名 */
	public static final String UPLOAD_IMAGE_EXTNAME = ".gif|.jpg|.jpeg|.png|.bmp";
	
	/** 验证码常量 */
	public static final String VERIFYCODE = "yehyVerifyCode";
	/** 用户Session常量 */
	public static final String SESSIONKEY = "yehySession";
	/** 分隔符号 */
	public static final String SPLIT_DEFAULT = ",";
	/** 导航分隔符号 */
	public static final String SPLIT_NAV = " ＞ ";
	/** 超级用户(拥有所有的角色) */
	public static final String DEF_SYSADMIN = "superadmin";
	
	/** 所有以MOD_打头的变量表示是模块编号 */
	public static final String MOD_COMMON = ""; // 公共模块,表示所有的都有权限,但必须登录
	public static final String MOD_PUBLIC_PART = "001001001";
	public static final String MOD_PUBLIC_USER = "001001002";
	public static final String MOD_PUBLIC_PRODUCT = "002001";
	public static final String MOD_WEB_BOARD = "005003";
	

	
	/** 属性文件中管理类头标记 */
	public static final String MANAGER_PREFIX = "business";
	// 属性文件(WEB-INF/conf/AppConfig.properties)管理类头常量添加到这里
	public static final String TestManager = MANAGER_PREFIX + ".TestManager";
	public static final String PermissionManager = MANAGER_PREFIX + ".PermissionManager";
	public static final String ProductManager = MANAGER_PREFIX + ".ProductManager";
	public static final String WebManager = MANAGER_PREFIX + ".WebManager";
		
	/** 属性文件中DAO类头标记 */
	public static final String DAO_PREFIX = "dao";
	// 属性文件(WEB-INF/conf/AppConfig.properties)DAO类头常量添加到这里
	public static final String TestDao = DAO_PREFIX + ".TestDao";
	public static final String PermissionDao = DAO_PREFIX + ".PermissionDao";
	public static final String ProductDao = DAO_PREFIX + ".ProductDao";
	public static final String WebDao = DAO_PREFIX + ".WebDao";
	


}
