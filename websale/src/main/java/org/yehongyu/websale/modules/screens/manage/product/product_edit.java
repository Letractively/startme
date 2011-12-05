/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.product;

import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.ProductManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.util.DateUtils;
import org.yehongyu.websale.common.util.StringUtils;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * 【类说明】产品新增和修改调用的页面
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:29:00
 */
public class product_edit extends SecureScreen {
    
    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");
        //获取参数
        long id = data.getParameters().getLong("id",0);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);

        ProductValue item = new ProductValue();
        data.getParameters().setProperties(item);
        
        if (id==0) {
            context.put(AppConstants.VO_NAME_NAV,AppConstants.SPLIT_NAV + "新增产品");
        } else {
            context.put(AppConstants.VO_NAME_NAV,AppConstants.SPLIT_NAV + "修改产品");
            BProduct po = productManager.getProductById(id);
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
        }
        List<BCategory> list = productManager.getCategoryList();
        context.put("catlist", list);
        context.put("item",item);
        context.put("date", DateUtils.getInstance());
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PRODUCT;
    }
}
