package com.yehongyu.mansys.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.yehongyu.mansys.BaseDAOTestCase;
import com.yehongyu.mansys.dao.domain.SysMenuDO;
import com.yehongyu.mansys.dao.ibatis.SysMenuDAO;
import com.yehongyu.mansys.dao.query.SysMenuQuery;

/**
 * sys_menu测试类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysMenuDAOTest extends BaseDAOTestCase {
	@Resource
	private SysMenuDAO sysMenuDAO;

	@Test
	public final void testInsertUpdateQueryDeleteSysMenuDO() {
		//准备测试数据
		SysMenuDO sysMenuDO = new SysMenuDO();
		sysMenuDO.setMenucode("s2");	/*menucode*/
		sysMenuDO.setMenuname("s3");	/*menuname*/
		sysMenuDO.setMenuurl("s4");	/*menuurl*/
		sysMenuDO.setMenulevel(5);	/*menulevel*/
		sysMenuDO.setIsleaf(6);	/*isleaf*/
		sysMenuDO.setParentscode("s7");	/*parentscode*/
		sysMenuDO.setRootcode("s8");	/*rootcode*/
		sysMenuDO.setDisplayorder("s9");	/*displayorder*/
		sysMenuDO.setStatus(10);	/*status*/
		sysMenuDO.setIssys(11);	/*issys*/
		Long id = null;
		try {
			//插入数据
			id = sysMenuDAO.insertSysMenuDO(sysMenuDO);
			if(id!=null){
				//更新数据测试
				sysMenuDO = new SysMenuDO();
				sysMenuDO.setId(id);
				sysMenuDO.setMenucode("ms2");	/*menucode*/
				sysMenuDO.setMenuname("ms3");	/*menuname*/
				sysMenuDO.setMenuurl("ms4");	/*menuurl*/
				sysMenuDO.setMenulevel(15);	/*menulevel*/
				sysMenuDO.setIsleaf(16);	/*isleaf*/
				sysMenuDO.setParentscode("ms7");	/*parentscode*/
				sysMenuDO.setRootcode("ms8");	/*rootcode*/
				sysMenuDO.setDisplayorder("ms9");	/*displayorder*/
				sysMenuDO.setStatus(110);	/*status*/
				sysMenuDO.setIssys(111);	/*issys*/
				Integer rsUpdate = sysMenuDAO.updateSysMenuDO(sysMenuDO);
				Assert.assertEquals(1, rsUpdate.intValue());
				//查询测试
				SysMenuQuery sysMenuQuery = new SysMenuQuery();
				sysMenuQuery.setId(id);
				SysMenuDO queryRs = sysMenuDAO.getSysMenuDO(sysMenuQuery);
				Assert.assertEquals("ms2", queryRs.getMenucode());	/*menucode*/
				Assert.assertEquals("ms3", queryRs.getMenuname());	/*menuname*/
				Assert.assertEquals("ms4", queryRs.getMenuurl());	/*menuurl*/
				Assert.assertEquals(15, queryRs.getMenulevel().intValue());	/*menulevel*/
				Assert.assertEquals(16, queryRs.getIsleaf().intValue());	/*isleaf*/
				Assert.assertEquals("ms7", queryRs.getParentscode());	/*parentscode*/
				Assert.assertEquals("ms8", queryRs.getRootcode());	/*rootcode*/
				Assert.assertEquals("ms9", queryRs.getDisplayorder());	/*displayorder*/
				Assert.assertEquals(110, queryRs.getStatus().intValue());	/*status*/
				Assert.assertEquals(111, queryRs.getIssys().intValue());	/*issys*/
				//删除数据测试
				Integer rsDelete = sysMenuDAO.deleteSysMenuDO(id);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryDeleteSysMenuDO() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally {
			if(id!=null){
				try {
					sysMenuDAO.deleteSysMenuDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryDeleteSysMenuDO() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

	@Test
	public final void testInsertUpdateQueryWithPageDeleteSysMenuDOList() {
		//准备测试数据
		SysMenuDO sysMenuDO = new SysMenuDO();
		sysMenuDO.setMenucode("s2");	/*menucode*/
		sysMenuDO.setMenuname("s3");	/*menuname*/
		sysMenuDO.setMenuurl("s4");	/*menuurl*/
		sysMenuDO.setMenulevel(5);	/*menulevel*/
		sysMenuDO.setIsleaf(6);	/*isleaf*/
		sysMenuDO.setParentscode("s7");	/*parentscode*/
		sysMenuDO.setRootcode("s8");	/*rootcode*/
		sysMenuDO.setDisplayorder("s9");	/*displayorder*/
		sysMenuDO.setStatus(10);	/*status*/
		sysMenuDO.setIssys(11);	/*issys*/
		Long id = null;
		try {
			//插入数据
			id = sysMenuDAO.insertSysMenuDO(sysMenuDO);
			if(id!=null){
				//批量更新数据测试
				sysMenuDO = new SysMenuDO();
				sysMenuDO.addIdList(id);
				sysMenuDO.setMenucode("ms2");	/*menucode*/
				sysMenuDO.setMenuname("ms3");	/*menuname*/
				sysMenuDO.setMenuurl("ms4");	/*menuurl*/
				sysMenuDO.setMenulevel(15);	/*menulevel*/
				sysMenuDO.setIsleaf(16);	/*isleaf*/
				sysMenuDO.setParentscode("ms7");	/*parentscode*/
				sysMenuDO.setRootcode("ms8");	/*rootcode*/
				sysMenuDO.setDisplayorder("ms9");	/*displayorder*/
				sysMenuDO.setStatus(110);	/*status*/
				sysMenuDO.setIssys(111);	/*issys*/
				Integer rs = sysMenuDAO.updateSysMenuDOList(sysMenuDO);
				Assert.assertEquals(1, rs.intValue());
				//批量查询测试
				SysMenuQuery sysMenuQuery = new SysMenuQuery();
				sysMenuQuery.addIdList(id);
				sysMenuQuery.orderbyId(false);
				sysMenuQuery.orderbyMenucode(false);
				sysMenuQuery.orderbyMenuname(false);
				sysMenuQuery.orderbyMenuurl(false);
				sysMenuQuery.orderbyMenulevel(false);
				sysMenuQuery.orderbyIsleaf(false);
				sysMenuQuery.orderbyParentscode(false);
				sysMenuQuery.orderbyRootcode(false);
				sysMenuQuery.orderbyDisplayorder(false);
				sysMenuQuery.orderbyStatus(false);
				sysMenuQuery.orderbyIssys(false);
				sysMenuQuery.orderbyGmtCreate(false);
				sysMenuQuery.orderbyGmtModified(false);
				List<SysMenuDO> queryRs = sysMenuDAO.getSysMenuDOList(sysMenuQuery);
				Assert.assertEquals(1,queryRs.size());
				Assert.assertEquals("ms2", queryRs.get(0).getMenucode());	/*menucode*/
				Assert.assertEquals("ms3", queryRs.get(0).getMenuname());	/*menuname*/
				Assert.assertEquals("ms4", queryRs.get(0).getMenuurl());	/*menuurl*/
				Assert.assertEquals(15, queryRs.get(0).getMenulevel().intValue());	/*menulevel*/
				Assert.assertEquals(16, queryRs.get(0).getIsleaf().intValue());	/*isleaf*/
				Assert.assertEquals("ms7", queryRs.get(0).getParentscode());	/*parentscode*/
				Assert.assertEquals("ms8", queryRs.get(0).getRootcode());	/*rootcode*/
				Assert.assertEquals("ms9", queryRs.get(0).getDisplayorder());	/*displayorder*/
				Assert.assertEquals(110, queryRs.get(0).getStatus().intValue());	/*status*/
				Assert.assertEquals(111, queryRs.get(0).getIssys().intValue());	/*issys*/
				//分页查询测试
				sysMenuQuery = new SysMenuQuery();
				sysMenuQuery.addIdList(id);
				sysMenuQuery.orderbyId(true);
				sysMenuQuery.orderbyMenucode(true);
				sysMenuQuery.orderbyMenuname(true);
				sysMenuQuery.orderbyMenuurl(true);
				sysMenuQuery.orderbyMenulevel(true);
				sysMenuQuery.orderbyIsleaf(true);
				sysMenuQuery.orderbyParentscode(true);
				sysMenuQuery.orderbyRootcode(true);
				sysMenuQuery.orderbyDisplayorder(true);
				sysMenuQuery.orderbyStatus(true);
				sysMenuQuery.orderbyIssys(true);
				sysMenuQuery.orderbyGmtCreate(true);
				sysMenuQuery.orderbyGmtModified(true);
				List<SysMenuDO> queryRsWithPage = sysMenuDAO.getSysMenuDOListWithPage(sysMenuQuery);
				Assert.assertEquals(1,queryRsWithPage.size());
				Assert.assertEquals("ms2", queryRs.get(0).getMenucode());	/*menucode*/
				Assert.assertEquals("ms3", queryRs.get(0).getMenuname());	/*menuname*/
				Assert.assertEquals("ms4", queryRs.get(0).getMenuurl());	/*menuurl*/
				Assert.assertEquals(15, queryRs.get(0).getMenulevel().intValue());	/*menulevel*/
				Assert.assertEquals(16, queryRs.get(0).getIsleaf().intValue());	/*isleaf*/
				Assert.assertEquals("ms7", queryRs.get(0).getParentscode());	/*parentscode*/
				Assert.assertEquals("ms8", queryRs.get(0).getRootcode());	/*rootcode*/
				Assert.assertEquals("ms9", queryRs.get(0).getDisplayorder());	/*displayorder*/
				Assert.assertEquals(110, queryRs.get(0).getStatus().intValue());	/*status*/
				Assert.assertEquals(111, queryRs.get(0).getIssys().intValue());	/*issys*/
				//删除数据测试
				sysMenuQuery = new SysMenuQuery();
				sysMenuQuery.addIdList(id);
				Integer rsDelete = sysMenuDAO.deleteSysMenuDOList(sysMenuQuery);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryWithPageDeleteSysMenuDOList() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally{
			if(id!=null){
				try {
					sysMenuDAO.deleteSysMenuDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryWithPageDeleteSysMenuDOList() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

}
