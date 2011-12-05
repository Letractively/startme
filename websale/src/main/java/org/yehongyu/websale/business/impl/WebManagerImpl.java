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
 * ����˵������վ����Managerʵ����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:05:59
 */
public class WebManagerImpl extends MyBaseBusiness implements WebManager {
	

    /** ������ʱ����һ��˽��Dao����ֻ�ڱ�������� */
    private final static WebDao webDao;
    /** ��ʼ��Dao���󣬴�Dao������ȡ�����п����׳��쳣 */
    static {
        try {
        	webDao = (WebDao) MyDaoFactory
                    .getDaoManager(AppConstants.WebDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("����Dao����ʧ�ܣ�", e);
        }
    }

    /**
     * ˽�й��췽������ֹ�ⲿ��������ʵ����ֻ�ܹ�����������ʵ��
     */
    private WebManagerImpl() {
    }

	/**
	 * ���������ܡ���ȡ��Ч�����¹������ݶ���
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException{
    	return webDao.getBoard();
    }
	
	/**
	 * ���������ܡ��༭������Ϣ
	 * @param cp
	 * @return
	 * @throws MyException
	 */
	public String editBoard(ContentParam cp)throws MyException{
		if(cp.getConid()==0) return "�����ID����";
		AContent obj = webDao.getContentById(cp.getConid());
		if(obj==null) return "�����ID���ܲ鵽����";
		
		obj.setContent(cp.getContent());
		obj.setState(cp.getState());
		webDao.update(obj);
		
		return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	
}
