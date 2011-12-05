/**
 * $Id$
 * Copyright(c) 2007-	yehongyu.org,All Rights Reserved.
 */
package org.yehongyu.websale.business.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.Hibernate;
import org.yehongyu.websale.AppConstants;
import org.yehongyu.websale.bo.manage.product.ProductParam;
import org.yehongyu.websale.business.ProductManager;
import org.yehongyu.websale.common.secure.CommonBean;
import org.yehongyu.websale.common.util.ConvertImage;
import org.yehongyu.websale.common.util.MyException;
import org.yehongyu.websale.common.util.StringUtils;
import org.yehongyu.websale.dao.ProductDao;
import org.yehongyu.websale.db.MyBaseBusiness;
import org.yehongyu.websale.db.MyDaoFactory;
import org.yehongyu.websale.db.bean.Page;
import org.yehongyu.websale.db.bean.PageBean;
import org.yehongyu.websale.db.po.mydb.BCategory;
import org.yehongyu.websale.db.po.mydb.BProduct;
import org.yehongyu.websale.vo.manage.product.ProductValue;

/**
 * ����˵������Ʒ����Managerʵ����
 * @author yehongyu.org
 * @version 1.0 2007-11-30 ����02:05:59
 */
public class ProductManagerImpl extends MyBaseBusiness implements ProductManager {
	

    /** ������ʱ����һ��˽��Dao����ֻ�ڱ�������� */
    private final static ProductDao productDao;
    /** ��ʼ��Dao���󣬴�Dao������ȡ�����п����׳��쳣 */
    static {
        try {
        	productDao = (ProductDao) MyDaoFactory
                    .getDaoManager(AppConstants.ProductDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("����Dao����ʧ�ܣ�", e);
        }
    }

    /**
     * ˽�й��췽������ֹ�ⲿ��������ʵ����ֻ�ܹ�����������ʵ��
     */
    private ProductManagerImpl() {
    }
    
    /**
     * ���������ܡ���ȡ��Ʒ�б�
     * @param p
     * @param pageBean
     * @return Page����
     * @throws MyException
     */
    public Page getProductList(ProductParam p,PageBean pageBean)throws MyException{
    	return productDao.getProductList(p,pageBean);
    }
    
    /**
     * ���������ܡ����ݲ�ƷID��ȡ��Ʒ����
     * @param productid
     * @return BProduct
     * @throws MyException
     */
    public BProduct getProductById(long productid)throws MyException{
    	return productDao.getProductById(productid);
    }
    
    private static Map<String,String> cat = null;
    
    /**
     * ���������ܡ���ȡ��Ʒ����Map
     * @return Map
     * @throws MyException
     */
    public Map<String,String> getCatnameMap()throws MyException{
    	if(cat!=null) return cat;
    	cat = new HashMap<String,String>();
    	List<BCategory> list = productDao.getCategoryList();
    	for(BCategory bcat:list){
    		cat.put(bcat.getId(), bcat.getCatname());
    	}
        return cat;
    }
    
	/**
	 * ���������ܡ���ȡ�����б�
	 * @return Page����
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException{
		return productDao.getCategoryList();
	}
	
	/**
	 * ���������ܡ��ϴ�ͼƬ�ļ�
	 * @param fi �ļ�����
	 * @param picname �ļ���
	 * @param imgpath �ļ�·��
	 * @return ���ص���Ϣ
	 */
	public String uploadImg(FileItem fi,String picname,String imgpath,BProduct item){
		//��֤����
		if (fi == null || StringUtils.isNullorSpace(picname)
				|| StringUtils.isNullorSpace(imgpath))
			return "������ļ�Ϊ��";
		//��������ļ�д�������·��
		String filename = new File(fi.getName()).getName();	//������ļ���
		File file = new File(imgpath+filename);	//������ļ�
		String extname = filename.substring(filename.lastIndexOf("."));
		if(AppConstants.UPLOAD_IMAGE_EXTNAME.indexOf(extname.toLowerCase())<0){
			return "�ϴ����ļ���չ����"+extname+"���ԣ�";
		}
		picname += extname;
		try {
			fi.write(file);
		} catch (Exception e) {
			log.error("�ϴ�ͼƬʧ�ܣ��ļ����ǣ�"+picname,e);
			return "�ϴ�ͼƬʧ��";
		}
		//ȡ��Ҫ���ɵ��ļ��������ϴ����ļ�������
		File picfile = new File(imgpath + picname);
		if(picfile.exists())picfile.delete();
		file.renameTo(picfile);
		//����Сͼ
		ConvertImage ci = new ConvertImage();
        ci.setFileInput(imgpath+picname);
        ci.setFileOutput(imgpath+"s"+picname);
        ci.convert();
        //����ͼƬ�ļ�����
        item.setPicname(picname);
        return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	/**
	 * ���������ܡ�ɾ��ͼƬ
	 * @param picname
	 * @param imgpath
	 * @return ���سɹ���Ϣ
	 */
	public String deleteImg(String picname,String imgpath){
		File picfile = new File(imgpath + picname);
		if(picfile.exists())picfile.delete();
		return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * ��Ӳ�Ʒ
	 */
	public String addProduct(ProductValue bo) throws MyException {
		//��֤�����ĺϷ���
        if (bo == null) return "����Ĳ�Ʒ��ϢΪ��";
        if(StringUtils.isNullorSpace(bo.getBookname())) return "������鼮���Ʋ���Ϊ��!";
        BProduct obj = productDao.getProductByBooknameAndCatid(bo.getBookname(),bo.getCatid());
        if(obj!=null) return "�˷���("+bo.getCatid()+")���Ѿ����ڱ��鼮����("+bo.getBookname()+")";


        //д��Ʒ���¼
        BProduct item = new BProduct();
        //�ϴ�ͼƬ
        if(bo.getFipic()!=null){
//        	String picname = bo.getCatid()+bo.getBookname();	//ͼƬ��Ϊ����ID������
        	String picname = StringUtils.getRandomFileName();	//ͼƬ���������
        	String upinfo = uploadImg(bo.getFipic(),picname,bo.getImgpath(),item);
            if(!AppConstants.MANAGER_BACK_SUCCESS.equals(upinfo)){
            	return upinfo;
            }
        }
        
        item.setBookname(bo.getBookname());
        if(bo.getBookpubdate()!=null){
        	item.setBookpubdate(new Timestamp(bo.getBookpubdate().getTime()));
        }
        item.setBookpublish(bo.getBookpublish());
        item.setBookwriter(bo.getBookwriter());
        item.setCatid(bo.getCatid());
//        item.setIntroduce(Hibernate.createClob(bo.getIntroduce()!=null?bo.getIntroduce():""));
        item.setIntroduce(bo.getIntroduce());
        item.setIscommend(bo.getIscommend());
        item.setIspopulate(bo.getIspopulate());
        item.setNum(bo.getNum());
        item.setPrice(bo.getPrice());
        item.setPubdate(new Timestamp(System.currentTimeMillis()));
        item.setPubman(bo.getPubman());
        item.setPubtimes(1);
        item.setSaveindate(new Timestamp(System.currentTimeMillis()));
        item.setState(bo.getState());
        productDao.save(item);
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	/**
	 * �޸Ĳ�Ʒ
	 */
	public String editProduct(ProductValue bo) throws MyException {
		//��֤�����ĺϷ���
        if (bo == null||bo.getId()==0) return "����Ĳ�Ʒ��ϢΪ��!";
        if(StringUtils.isNullorSpace(bo.getBookname())) return "������鼮���Ʋ���Ϊ��!";
        BProduct obj = productDao.getProductByBooknameAndCatid(bo.getBookname(),bo.getCatid());
        if(obj!=null&&obj.getId()!=bo.getId()) return "�˷���("+bo.getCatid()+")���Ѿ����ڱ��鼮����("+bo.getBookname()+")";

        BProduct item = productDao.getProductById(bo.getId());
        if(item==null) return "�����ID��ѯ������Ʒ��Ϣ";
        
        //�ϴ�ͼƬ
        if(bo.getFipic()!=null){
//        	String picname = bo.getCatid()+bo.getBookname();	//ͼƬ��Ϊ����ID������
        	String picname = StringUtils.getRandomFileName();	//ͼƬ���������
        	String upinfo = uploadImg(bo.getFipic(),picname,bo.getImgpath(),item);
            if(!AppConstants.MANAGER_BACK_SUCCESS.equals(upinfo)){
            	return upinfo;
            }
        }
        
        //д��Ʒ���¼
        item.setBookname(bo.getBookname());
        if(bo.getBookpubdate()!=null){
            item.setBookpubdate(new Timestamp(bo.getBookpubdate().getTime()));
        }
        item.setBookpublish(bo.getBookpublish());
        item.setBookwriter(bo.getBookwriter());
        item.setCatid(bo.getCatid());
        item.setIntroduce(bo.getIntroduce());
        item.setIscommend(bo.getIscommend());
        item.setIspopulate(bo.getIspopulate());
        item.setNum(bo.getNum());
        item.setPrice(bo.getPrice());
        item.setPubdate(new Timestamp(System.currentTimeMillis()));
        item.setPubman(bo.getPubman());
        item.setPubtimes(item.getPubtimes()+1);
        item.setState(bo.getState());
        productDao.update(item);
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * ɾ����Ʒ
	 */
	public String deleteProduct(long[] ids,String imgpath) throws MyException {
		//��֤�����Ϸ���
        if(ids==null||ids.length==0) return "����Ĳ�ƷIDΪ��";
        
        //ѭ��ɾ��ÿ����Ʒ
        StringBuffer info = new StringBuffer();   //ɾ����Ϣ
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("ɾ���Ĳ�ƷId[").append(ids[m]).append("]������!\\r\\n");
                continue;   //���ɾ���Ĳ�Ʒ�����ڣ�����һ��
            }else{
//                Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
//                if(!map.entrySet().isEmpty()){
//                    info.append("ɾ�����û�Id[").append(userids[m]).append("]�������˺ţ�����ɾ����\\r\\n");
//                    continue;   //���ɾ�����û��´������˺ţ�����һ��
//                }
            }
            this.deleteImg(po.getPicname(), imgpath);
            productDao.delete(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * ��������������Ʒ
	 */
	public String pubProduct(long[] ids,long isPub) throws MyException {
		//��֤�����Ϸ���
        if(ids==null||ids.length==0) return "����Ĳ�ƷIDΪ��";
        
        //ѭ������ÿ����Ʒ
        StringBuffer info = new StringBuffer();   //������Ϣ
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("�����Ĳ�ƷId[").append(ids[m]).append("]������!\\r\\n");
                continue;   //��������Ĳ�Ʒ�����ڣ�����һ��
            }
            po.setState(isPub==1?1:2);
            if(isPub==1){
            	po.setPubdate(new Timestamp(System.currentTimeMillis()));
            }
            productDao.update(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * ��Ϊ���ǣ�������Ʒ
	 */
	public String popProduct(long[] ids,long isPop) throws MyException {
		//��֤�����Ϸ���
        if(ids==null||ids.length==0) return "����Ĳ�ƷIDΪ��";
        
        //ѭ������ÿ����Ʒ
        StringBuffer info = new StringBuffer();   //������Ϣ
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("�����Ĳ�ƷId[").append(ids[m]).append("]������!\\r\\n");
                continue;   //��������Ĳ�Ʒ�����ڣ�����һ��
            }
            po.setIspopulate(isPop);
            productDao.update(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * �Ƽ���ȡ���Ƽ���Ʒ
	 */
	public String commendProduct(long[] ids,long isCommend) throws MyException {
		//��֤�����Ϸ���
        if(ids==null||ids.length==0) return "����Ĳ�ƷIDΪ��";
        
        //ѭ������ÿ����Ʒ
        StringBuffer info = new StringBuffer();   //������Ϣ
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("�����Ĳ�ƷId[").append(ids[m]).append("]������!\\r\\n");
                continue;   //��������Ĳ�Ʒ�����ڣ�����һ��
            }
            po.setIscommend(isCommend);
            productDao.update(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	/**
	 * ���������ܡ���ȡһ������ͼ��
	 * @return����Ʒ����
	 * @throws MyException
	 */
	public BProduct getPopulateProduct()throws MyException{
		ProductParam param = new ProductParam();
		param.setIspopulate(1);
		param.setState(1);
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1);
		Page p = productDao.getProductList(param, pageBean);
		List<BProduct> products = p.getLstResult();
		if(products!=null&&products.size()>0){
			return products.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * ���������ܡ���ȡһ���Ƽ�ͼ��
	 * @return����Ʒ����
	 * @throws MyException
	 */
	public BProduct getCommendProduct()throws MyException{
		ProductParam param = new ProductParam();
		param.setIscommend(1);
		param.setState(1);
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(1);
		Page p = productDao.getProductList(param, pageBean);
		List<BProduct> products = p.getLstResult();
		if(products!=null&&products.size()>0){
			return products.get(0);
		}else{
			return null;
		}
	}
	
	
	
}
