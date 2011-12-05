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
public class start extends NoSecureScreen {

	public void doBuildTemplate(RunData runData, Context context)
			throws Exception {
		runData.setLayoutTemplate("Shop.html");

        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        BProduct popBook = productManager.getPopulateProduct();
        if(popBook!=null){
        	ProductValue pv = getProductValue(popBook,runData);
            context.put("popBook",pv);
        }
        BProduct po = productManager.getCommendProduct();
        if(po!=null){
        	ProductValue pv = getProductValue(po,runData);
            context.put("commendBook",pv);
        }
        ProductParam p = new ProductParam();
        p.setState(1);
        PageBean pageBean = new PageBean();
        pageBean.setPage(1);
        pageBean.setPageSize(8);
        Page page = productManager.getProductList(p, pageBean);
        List<BProduct> newBook = page.getLstResult();
        if(newBook!=null&&newBook.size()>0){
        	List<ProductValue> vo = new ArrayList<ProductValue>();
    		ProductValue pv = null;
        	for(BProduct bp:newBook){
        		pv = getProductValue(bp,runData);
        		vo.add(pv);
        	}
        	context.put("newbooks", vo);
        }
        List<BCategory> list = productManager.getCategoryList();
        context.put("catlist", list);

        WebManager webManager = (WebManager) MyBusinessFactory
                .getManager(AppConstants.WebManager);
        context.put("board", webManager.getBoard());
        
		context.put("sessioninfo", runData.getRequest().getSession().getId());
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
