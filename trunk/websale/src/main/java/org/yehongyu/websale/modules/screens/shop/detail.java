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
import org.yehongyu.websale.common.util.DateUtils;
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
public class detail extends NoSecureScreen {

	public void doBuildTemplate(RunData runData, Context context)
			throws Exception {
		runData.setLayoutTemplate("Shop.html");

        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        
        List<BCategory> list = productManager.getCategoryList();
        context.put("catlist", list);
        
        long id = runData.getParameters().getLong("id",0);
        BProduct po = productManager.getProductById(id);
        
        if(po!=null){
        	ProductValue pv = getProductValue(po,runData);
        	context.put("book", pv);
            context.put("catid", pv.getCatid());
        }
        context.put("cat", productManager.getCatnameMap());
        context.put("date", DateUtils.getInstance());
	}
	
	private ProductValue getProductValue(BProduct po,RunData data){
		ProductValue item = new ProductValue();
		item.setId(po.getId());
        item.setBookname(po.getBookname());
        if(po.getBookpubdate()!=null){
            item.setBookpubdate(new Date(po.getBookpubdate().getTime()));
        }
        item.setBookpublish(po.getBookpublish());
        item.setBookwriter(po.getBookwriter());
        item.setCatid(po.getCatid());
        item.setIntroduce(po.getIntroduce());
        item.setIscommend(po.getIscommend());
        item.setIspopulate(po.getIspopulate());
        item.setNum(po.getNum());
        String picname = CommonBean.getProductImgContextpath(data) + po.getPicname();
        item.setPicname(picname);
        item.setPrice(po.getPrice());
        item.setPubdate(po.getPubdate());
        item.setPubman(po.getPubman());
        item.setState(po.getState());
		return item;
	}
}
