/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business.impl;

import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.web.ContentParam;
import org.yehongyu.websale.business.WebManager;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.dao.WebDao;
import org.yehongyu.websale.db.MyBaseBusiness;
import org.yehongyu.websale.db.MyDaoFactory;
import org.yehongyu.websale.db.po.mydb.AContent;

/**
 * 【类说明】网站管理Manager实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:05:59
 */
public class WebManagerImpl extends MyBaseBusiness implements WebManager {
	

    /** 加载类时声明一个私有Dao对象，只在本类可以用 */
    private final static WebDao webDao;
    /** 初始化Dao对象，从Dao工厂中取出，有可能抛出异常 */
    static {
        try {
        	webDao = (WebDao) MyDaoFactory
                    .getDaoManager(AppConstants.WebDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("创建Dao对象失败！", e);
        }
    }

    /**
     * 私有构造方法，防止外部创建本类实例，只能工厂创建本类实例
     */
    private WebManagerImpl() {
    }

	/**
	 * 【函数功能】获取有效的最新公告内容对象
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException{
    	return webDao.getBoard();
    }
	
	/**
	 * 【函数功能】编辑公告信息
	 * @param cp
	 * @return
	 * @throws MyException
	 */
	public String editBoard(ContentParam cp)throws MyException{
		if(cp.getConid()==0) return "传入的ID有误";
		AContent obj = webDao.getContentById(cp.getConid());
		if(obj==null) return "传入的ID不能查到对象";
		
		obj.setContent(cp.getContent());
		obj.setState(cp.getState());
		webDao.update(obj);
		
		return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	
}
