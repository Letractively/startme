/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business;

import java.util.List;
import java.util.Map;

import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.bo.manage.web.ContentParam;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.AContent;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * ����˵������վ����Manager�ӿ�
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����10:53:22
 */
public interface WebManager {
	
	/**
	 * ���������ܡ���ȡ��Ч�����¹������ݶ���
	 * @return
	 * @throws MyException
	 */
	public AContent getBoard()throws MyException;
	
	/**
	 * ���������ܡ��༭������Ϣ
	 * @param cp
	 * @return
	 * @throws MyException
	 */
	public String editBoard(ContentParam cp)throws MyException;
}
