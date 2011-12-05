/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.modules.actions.manage.product;

import org.apache.commons.fileupload.FileItem;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.business.ProductManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.secure.SecureAction;
import org.yehongyu.websale.db.MyBusinessFactory;
import org.yehongyu.websale.vo.manage.product.ProductValue;


/**
 * 【类说明】产品管理，处理新增、修改页面提交的请求
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:02:10
 */
public class ProductAction extends SecureAction {

    /**
     * 处理页面提交
     */
    public void doPerform(RunData runData, Context context) throws Exception {
		String dowhat = runData.getParameters().getString("do", "");

		if ("add".equals(dowhat)) {
			doAdd(runData, context);
		} else if ("edit".equals(dowhat)) {
			doEdit(runData, context);
		}
	}

    /**
	 * 【函数功能】添加项目
	 * @param runData
	 * @param context
	 * @throws Exception
	 */
    public void doAdd(RunData runData, Context context) throws Exception {
    	//设置并验证参数合法性
    	ProductValue param = new ProductValue();
        setParam(runData, param);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        String result = productManager.addProduct(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,product,product_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "增加成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,product,product_list.html");
        }
    }

    /**
     * 【函数功能】修改项目
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doEdit(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
    	ProductValue param = new ProductValue();
        setParam(runData, param);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.editProduct(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,product,product_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "更新成功！");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,product,product_list.html");
        }
    }

    /**
     * 【函数功能】删除项目
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doDelete(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.deleteProduct(ids,CommonBean.getProductImgpath(runData));
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "删除成功！");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * 【函数功能】发布或撤销发布
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doPub(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isPub = runData.getParameters().getLong("isPub");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.pubProduct(ids,isPub);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "操作成功！");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * 【函数功能】置为（非）畅销产品
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doPop(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isPop = runData.getParameters().getLong("isPop");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.popProduct(ids,isPop);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "操作成功！");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * 【函数功能】推荐或取消推荐
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doCommend(RunData runData, Context context) throws Exception {
        //设置并验证参数合法性
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isCommend = runData.getParameters().getLong("isCommend");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.commendProduct(ids,isCommend);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "操作成功！");
        }
        runData.getParameters().clear();
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * 添加修改时，设置对象参数。
     * @param runData
     * @param param
     */
    private void setParam(RunData runData, ProductValue param)throws Exception {
    	runData.getParameters().setProperties(param);
        FileItem fi = runData.getParameters().getFileItem("picname");
        if(fi.getName()!=null&&!fi.getName().equals("")){
        	param.setFipic(fi);
        }
        param.setImgpath(CommonBean.getProductImgpath(runData));
        param.setPubman(CommonBean.getUserSession(runData).getUserName());
    }

    /**
     * 实现父类的抽象方法， 返回要权限控制的模块编号,父类会判断用户是否有此模块的操作权限。
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PRODUCT;
    }

}
