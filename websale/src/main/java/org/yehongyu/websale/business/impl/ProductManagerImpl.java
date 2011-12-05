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
 * 【类说明】产品管理Manager实现类
 * @author yehongyu.org
 * @version 1.0 2007-11-30 上午02:05:59
 */
public class ProductManagerImpl extends MyBaseBusiness implements ProductManager {
	

    /** 加载类时声明一个私有Dao对象，只在本类可以用 */
    private final static ProductDao productDao;
    /** 初始化Dao对象，从Dao工厂中取出，有可能抛出异常 */
    static {
        try {
        	productDao = (ProductDao) MyDaoFactory
                    .getDaoManager(AppConstants.ProductDao);
        } catch (MyException e) {
            throw new IllegalArgumentException("创建Dao对象失败！", e);
        }
    }

    /**
     * 私有构造方法，防止外部创建本类实例，只能工厂创建本类实例
     */
    private ProductManagerImpl() {
    }
    
    /**
     * 【函数功能】获取产品列表
     * @param p
     * @param pageBean
     * @return Page对象
     * @throws MyException
     */
    public Page getProductList(ProductParam p,PageBean pageBean)throws MyException{
    	return productDao.getProductList(p,pageBean);
    }
    
    /**
     * 【函数功能】根据产品ID获取产品对象
     * @param productid
     * @return BProduct
     * @throws MyException
     */
    public BProduct getProductById(long productid)throws MyException{
    	return productDao.getProductById(productid);
    }
    
    private static Map<String,String> cat = null;
    
    /**
     * 【函数功能】获取产品分类Map
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
	 * 【函数功能】获取分类列表
	 * @return Page对象
	 * @throws MyException
	 */
	public List<BCategory> getCategoryList()throws MyException{
		return productDao.getCategoryList();
	}
	
	/**
	 * 【函数功能】上传图片文件
	 * @param fi 文件对象
	 * @param picname 文件名
	 * @param imgpath 文件路径
	 * @return 返回的信息
	 */
	public String uploadImg(FileItem fi,String picname,String imgpath,BProduct item){
		//验证参数
		if (fi == null || StringUtils.isNullorSpace(picname)
				|| StringUtils.isNullorSpace(imgpath))
			return "传入的文件为空";
		//将传入的文件写入服务器路径
		String filename = new File(fi.getName()).getName();	//传入的文件名
		File file = new File(imgpath+filename);	//传入的文件
		String extname = filename.substring(filename.lastIndexOf("."));
		if(AppConstants.UPLOAD_IMAGE_EXTNAME.indexOf(extname.toLowerCase())<0){
			return "上传的文件扩展名："+extname+"不对！";
		}
		picname += extname;
		try {
			fi.write(file);
		} catch (Exception e) {
			log.error("上传图片失败，文件名是："+picname,e);
			return "上传图片失败";
		}
		//取得要生成的文件名，将上传的文件重命名
		File picfile = new File(imgpath + picname);
		if(picfile.exists())picfile.delete();
		file.renameTo(picfile);
		//生成小图
		ConvertImage ci = new ConvertImage();
        ci.setFileInput(imgpath+picname);
        ci.setFileOutput(imgpath+"s"+picname);
        ci.convert();
        //设置图片文件名称
        item.setPicname(picname);
        return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	/**
	 * 【函数功能】删除图片
	 * @param picname
	 * @param imgpath
	 * @return 返回成功信息
	 */
	public String deleteImg(String picname,String imgpath){
		File picfile = new File(imgpath + picname);
		if(picfile.exists())picfile.delete();
		return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * 添加产品
	 */
	public String addProduct(ProductValue bo) throws MyException {
		//验证参数的合法性
        if (bo == null) return "传入的产品信息为空";
        if(StringUtils.isNullorSpace(bo.getBookname())) return "传入的书籍名称不能为空!";
        BProduct obj = productDao.getProductByBooknameAndCatid(bo.getBookname(),bo.getCatid());
        if(obj!=null) return "此分类("+bo.getCatid()+")下已经存在本书籍名称("+bo.getBookname()+")";


        //写产品表记录
        BProduct item = new BProduct();
        //上传图片
        if(bo.getFipic()!=null){
//        	String picname = bo.getCatid()+bo.getBookname();	//图片名为分类ID＋书名
        	String picname = StringUtils.getRandomFileName();	//图片名随机生成
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
	 * 修改产品
	 */
	public String editProduct(ProductValue bo) throws MyException {
		//验证参数的合法性
        if (bo == null||bo.getId()==0) return "传入的产品信息为空!";
        if(StringUtils.isNullorSpace(bo.getBookname())) return "传入的书籍名称不能为空!";
        BProduct obj = productDao.getProductByBooknameAndCatid(bo.getBookname(),bo.getCatid());
        if(obj!=null&&obj.getId()!=bo.getId()) return "此分类("+bo.getCatid()+")下已经存在本书籍名称("+bo.getBookname()+")";

        BProduct item = productDao.getProductById(bo.getId());
        if(item==null) return "传入的ID查询不到产品信息";
        
        //上传图片
        if(bo.getFipic()!=null){
//        	String picname = bo.getCatid()+bo.getBookname();	//图片名为分类ID＋书名
        	String picname = StringUtils.getRandomFileName();	//图片名随机生成
        	String upinfo = uploadImg(bo.getFipic(),picname,bo.getImgpath(),item);
            if(!AppConstants.MANAGER_BACK_SUCCESS.equals(upinfo)){
            	return upinfo;
            }
        }
        
        //写产品表记录
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
	 * 删除产品
	 */
	public String deleteProduct(long[] ids,String imgpath) throws MyException {
		//验证参数合法性
        if(ids==null||ids.length==0) return "传入的产品ID为空";
        
        //循环删除每个产品
        StringBuffer info = new StringBuffer();   //删除信息
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("删除的产品Id[").append(ids[m]).append("]不存在!\\r\\n");
                continue;   //如果删除的产品不存在，则下一个
            }else{
//                Map<String,SUser> map = permissionDao.getSUserAll(login.getUsername());
//                if(!map.entrySet().isEmpty()){
//                    info.append("删除的用户Id[").append(userids[m]).append("]下有子账号，不能删除！\\r\\n");
//                    continue;   //如果删除的用户下存在子账号，则下一个
//                }
            }
            this.deleteImg(po.getPicname(), imgpath);
            productDao.delete(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * 发布或撤销发布产品
	 */
	public String pubProduct(long[] ids,long isPub) throws MyException {
		//验证参数合法性
        if(ids==null||ids.length==0) return "传入的产品ID为空";
        
        //循环操作每个产品
        StringBuffer info = new StringBuffer();   //操作信息
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("操作的产品Id[").append(ids[m]).append("]不存在!\\r\\n");
                continue;   //如果操作的产品不存在，则下一个
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
	 * 置为（非）畅销产品
	 */
	public String popProduct(long[] ids,long isPop) throws MyException {
		//验证参数合法性
        if(ids==null||ids.length==0) return "传入的产品ID为空";
        
        //循环操作每个产品
        StringBuffer info = new StringBuffer();   //操作信息
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("操作的产品Id[").append(ids[m]).append("]不存在!\\r\\n");
                continue;   //如果操作的产品不存在，则下一个
            }
            po.setIspopulate(isPop);
            productDao.update(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}

	/**
	 * 推荐或取消推荐产品
	 */
	public String commendProduct(long[] ids,long isCommend) throws MyException {
		//验证参数合法性
        if(ids==null||ids.length==0) return "传入的产品ID为空";
        
        //循环操作每个产品
        StringBuffer info = new StringBuffer();   //操作信息
        for (int m = 0; m < ids.length; m++) {
            BProduct po = productDao.getProductById(ids[m]);
            if(po==null) {
                info.append("操作的产品Id[").append(ids[m]).append("]不存在!\\r\\n");
                continue;   //如果操作的产品不存在，则下一个
            }
            po.setIscommend(isCommend);
            productDao.update(po);
        }
       
        if(!"".equals(info.toString())) return info.toString();
        
        return AppConstants.MANAGER_BACK_SUCCESS;
	}
	
	/**
	 * 【函数功能】获取一条畅销图书
	 * @return　产品对象
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
	 * 【函数功能】获取一条推荐图书
	 * @return　产品对象
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
