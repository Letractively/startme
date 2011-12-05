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
 * ����˵������Ʒ���������������޸�ҳ���ύ������
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:02:10
 */
public class ProductAction extends SecureAction {

    /**
     * ����ҳ���ύ
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
	 * ���������ܡ������Ŀ
	 * @param runData
	 * @param context
	 * @throws Exception
	 */
    public void doAdd(RunData runData, Context context) throws Exception {
    	//���ò���֤�����Ϸ���
    	ProductValue param = new ProductValue();
        setParam(runData, param);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
                .getManager(AppConstants.ProductManager);
        String result = productManager.addProduct(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,product,product_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "���ӳɹ���");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,product,product_list.html");
        }
    }

    /**
     * ���������ܡ��޸���Ŀ
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doEdit(RunData runData, Context context) throws Exception {
        //���ò���֤�����Ϸ���
    	ProductValue param = new ProductValue();
        setParam(runData, param);
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.editProduct(param);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
            runData.setScreenTemplate("manage,product,product_edit.html");
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "���³ɹ���");
            runData.getParameters().clear();
            runData.setScreenTemplate("manage,product,product_list.html");
        }
    }

    /**
     * ���������ܡ�ɾ����Ŀ
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doDelete(RunData runData, Context context) throws Exception {
        //���ò���֤�����Ϸ���
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.deleteProduct(ids,CommonBean.getProductImgpath(runData));
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "ɾ���ɹ���");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * ���������ܡ�������������
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doPub(RunData runData, Context context) throws Exception {
        //���ò���֤�����Ϸ���
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isPub = runData.getParameters().getLong("isPub");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.pubProduct(ids,isPub);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "�����ɹ���");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * ���������ܡ���Ϊ���ǣ�������Ʒ
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doPop(RunData runData, Context context) throws Exception {
        //���ò���֤�����Ϸ���
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isPop = runData.getParameters().getLong("isPop");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.popProduct(ids,isPop);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "�����ɹ���");
        }
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * ���������ܡ��Ƽ���ȡ���Ƽ�
     * @param runData
     * @param context
     * @throws Exception
     */
    public void doCommend(RunData runData, Context context) throws Exception {
        //���ò���֤�����Ϸ���
        long[] ids = runData.getParameters().getLongs("ids");
        if(ids==null||ids.length==0)return;
        long isCommend = runData.getParameters().getLong("isCommend");
        
        ProductManager productManager = (ProductManager) MyBusinessFactory
        .getManager(AppConstants.ProductManager);
        String result = productManager.commendProduct(ids,isCommend);
        if (!result.equals(AppConstants.MANAGER_BACK_SUCCESS)) {
            context.put(AppConstants.VO_NAME_OPTINFO, result);
        } else {
            context.put(AppConstants.VO_NAME_OPTINFO, "�����ɹ���");
        }
        runData.getParameters().clear();
        runData.setScreenTemplate("manage,product,product_list.html");
    }

    /**
     * ����޸�ʱ�����ö��������
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
     * ʵ�ָ���ĳ��󷽷��� ����ҪȨ�޿��Ƶ�ģ����,������ж��û��Ƿ��д�ģ��Ĳ���Ȩ�ޡ�
     */
    protected String getModuleId() {
        return AppConstants.MOD_PUBLIC_PRODUCT;
    }

}
