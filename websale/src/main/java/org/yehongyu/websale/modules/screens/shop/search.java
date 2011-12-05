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
 * 【类说明】
 * @author yehongyu.org
 * @version 1.0 2007-11-11 下午12:00:02
 */
public class search extends NoSecureScreen {

	public void doBuildTemplate(RunData runData, Context context)
			throws Exception {
		runData.setLayoutTemplate("Shop.html");

        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        runData.getParameters().setProperties(pageBean);
        context.put(AppConstants.VO_NAME_PAGE, pageBean);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        
        List<BCategory> list = productManager.getCategoryList();
        context.put("catlist", list);
        
        String searchtype = runData.getParameters().getString("searchtype","1");
        String keyword = runData.getParameters().getString("keyword","");
        
        ProductParam param = new ProductParam();
        if(searchtype.equals("2")){	//作者
        	param.setBookwriter(keyword);
        	context.put("typename", "作者");
        }else{	//书名
        	param.setBookname(keyword);
        	context.put("typename", "书名");
        }
        param.setState(1);	//必须是发布的
        Page p = productManager.getProductList(param, pageBean);
        
        context.put("result", p.getLstResult());
        context.put("searchtype", searchtype);
        context.put("keyword", keyword);

	}
	
}
