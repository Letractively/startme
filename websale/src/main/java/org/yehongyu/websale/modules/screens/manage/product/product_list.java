/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.screens.manage.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.business.ProductManager;
import org.yehongyu.websale.common.secure.SecureScreen;
import org.yehongyu.websale.common.util.DateUtils;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;

/**
 * ����˵������Ʒ�б�ҳ��
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����01:42:20
 */
public class product_list extends SecureScreen {

    protected void doBuildTemplate(RunData data, Context context)
            throws Exception {
    	data.setLayoutTemplate("Manage.html");
    	//���ò���
        ProductParam p = new ProductParam();
        if(getParamSession(data)!=null){
            p = (ProductParam)getParamSession(data); 
        }
        data.getParameters().setProperties(p);
        context.put(AppConstants.VO_NAME_PARAM, p);
        setParamSession(p,data);
        
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(10);
        if(getParamPageSession(data)!=null){
            pageBean = getParamPageSession(data); 
            pageBean.setActionType(0);
        }
        data.getParameters().setProperties(pageBean);
        context.put(AppConstants.VO_NAME_PAGE, pageBean);
        setParamPageSession(pageBean,data);
        //��֤����
        if (p.getStartdate() != null && p.getEnddate() != null
				&& p.getStartdate().after(p.getEnddate())) {
			context.put("queryfail", "��ʼ���ڴ��ڽ������ڣ�");
		}
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        
        Page page = productManager.getProductList(p, pageBean);
        context.put(AppConstants.VO_NAME_COMMON, page.getLstResult());
        context.put(AppConstants.VO_NAME_DATE, DateUtils.getInstance());
        context.put("cat", productManager.getCatnameMap());
        context.put("catlist", productManager.getCategoryList());
    }

    /**
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PRODUCT;
    }
}
