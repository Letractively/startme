/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.business.ProductManager;
import org.yehongyu.websale.business.WebManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.NoSecureScreen;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * ����˵����
 * @author yehongyu.org
 * @version 1.0 2007-11-11 ����12:00:02
 */
public class list extends NoSecureScreen {

	public void doBuildTemplate(RunData runData, Context context)
			throws Exception {
		runData.setLayoutTemplate("Shop.html");

        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        
        List<BCategory> list = productManager.getCategoryList();
        context.put("catlist", list);
        
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        runData.getParameters().setProperties(pageBean);
        context.put(AppConstants.VO_NAME_PAGE, pageBean);
        
        String catid = runData.getParameters().getString("catid","0");
        ProductParam param = new ProductParam();
        param.setState(1);	//�����Ƿ�����
        param.setCatid(catid);
        Page p = productManager.getProductList(param, pageBean);
        
        context.put("cat", productManager.getCatnameMap());
        context.put("catid", catid);
        context.put("result", p.getLstResult());
        
        

	}
	
}
