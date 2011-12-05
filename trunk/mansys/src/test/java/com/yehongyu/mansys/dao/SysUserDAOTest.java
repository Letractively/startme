package com.yehongyu.mansys.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.yehongyu.mansys.BaseDAOTestCase;
import com.yehongyu.mansys.dao.domain.SysUserDO;
import com.yehongyu.mansys.dao.ibatis.SysUserDAO;
import com.yehongyu.mansys.dao.query.SysUserQuery;

/**
 * sys_user测试类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserDAOTest extends BaseDAOTestCase {
	@Resource
	private SysUserDAO sysUserDAO;

	@Test
	public final void testInsertUpdateQueryDeleteSysUserDO() {
		//准备测试数据
		SysUserDO sysUserDO = new SysUserDO();
		sysUserDO.setUsername("s2");	/*username*/
		sysUserDO.setPassword("s3");	/*password*/
		sysUserDO.setName("s4");	/*name*/
		sysUserDO.setIssys(5);	/*issys*/
		sysUserDO.setStatus(6);	/*status*/
		sysUserDO.setMemo("s7");	/*memo*/
		Long id = null;
		try {
			//插入数据
			id = sysUserDAO.insertSysUserDO(sysUserDO);
			if(id!=null){
				//更新数据测试
				sysUserDO = new SysUserDO();
				sysUserDO.setId(id);
				sysUserDO.setUsername("ms2");	/*username*/
				sysUserDO.setPassword("ms3");	/*password*/
				sysUserDO.setName("ms4");	/*name*/
				sysUserDO.setIssys(15);	/*issys*/
				sysUserDO.setStatus(16);	/*status*/
				sysUserDO.setMemo("ms7");	/*memo*/
				Integer rsUpdate = sysUserDAO.updateSysUserDO(sysUserDO);
				Assert.assertEquals(1, rsUpdate.intValue());
				//查询测试
				SysUserQuery sysUserQuery = new SysUserQuery();
				sysUserQuery.setId(id);
				SysUserDO queryRs = sysUserDAO.getSysUserDO(sysUserQuery);
				Assert.assertEquals("ms2", queryRs.getUsername());	/*username*/
				Assert.assertEquals("ms3", queryRs.getPassword());	/*password*/
				Assert.assertEquals("ms4", queryRs.getName());	/*name*/
				Assert.assertEquals(15, queryRs.getIssys().intValue());	/*issys*/
				Assert.assertEquals(16, queryRs.getStatus().intValue());	/*status*/
				Assert.assertEquals("ms7", queryRs.getMemo());	/*memo*/
				//删除数据测试
				Integer rsDelete = sysUserDAO.deleteSysUserDO(id);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryDeleteSysUserDO() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally {
			if(id!=null){
				try {
					sysUserDAO.deleteSysUserDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryDeleteSysUserDO() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

	@Test
	public final void testInsertUpdateQueryWithPageDeleteSysUserDOList() {
		//准备测试数据
		SysUserDO sysUserDO = new SysUserDO();
		sysUserDO.setUsername("s2");	/*username*/
		sysUserDO.setPassword("s3");	/*password*/
		sysUserDO.setName("s4");	/*name*/
		sysUserDO.setIssys(5);	/*issys*/
		sysUserDO.setStatus(6);	/*status*/
		sysUserDO.setMemo("s7");	/*memo*/
		Long id = null;
		try {
			//插入数据
			id = sysUserDAO.insertSysUserDO(sysUserDO);
			if(id!=null){
				//批量更新数据测试
				sysUserDO = new SysUserDO();
				sysUserDO.addIdList(id);
				sysUserDO.setUsername("ms2");	/*username*/
				sysUserDO.setPassword("ms3");	/*password*/
				sysUserDO.setName("ms4");	/*name*/
				sysUserDO.setIssys(15);	/*issys*/
				sysUserDO.setStatus(16);	/*status*/
				sysUserDO.setMemo("ms7");	/*memo*/
				Integer rs = sysUserDAO.updateSysUserDOList(sysUserDO);
				Assert.assertEquals(1, rs.intValue());
				//批量查询测试
				SysUserQuery sysUserQuery = new SysUserQuery();
				sysUserQuery.addIdList(id);
				sysUserQuery.orderbyId(false);
				sysUserQuery.orderbyUsername(false);
				sysUserQuery.orderbyPassword(false);
				sysUserQuery.orderbyName(false);
				sysUserQuery.orderbyIssys(false);
				sysUserQuery.orderbyStatus(false);
				sysUserQuery.orderbyMemo(false);
				sysUserQuery.orderbyGmtCreate(false);
				sysUserQuery.orderbyGmtModified(false);
				List<SysUserDO> queryRs = sysUserDAO.getSysUserDOList(sysUserQuery);
				Assert.assertEquals(1,queryRs.size());
				Assert.assertEquals("ms2", queryRs.get(0).getUsername());	/*username*/
				Assert.assertEquals("ms3", queryRs.get(0).getPassword());	/*password*/
				Assert.assertEquals("ms4", queryRs.get(0).getName());	/*name*/
				Assert.assertEquals(15, queryRs.get(0).getIssys().intValue());	/*issys*/
				Assert.assertEquals(16, queryRs.get(0).getStatus().intValue());	/*status*/
				Assert.assertEquals("ms7", queryRs.get(0).getMemo());	/*memo*/
				//分页查询测试
				sysUserQuery = new SysUserQuery();
				sysUserQuery.addIdList(id);
				sysUserQuery.orderbyId(true);
				sysUserQuery.orderbyUsername(true);
				sysUserQuery.orderbyPassword(true);
				sysUserQuery.orderbyName(true);
				sysUserQuery.orderbyIssys(true);
				sysUserQuery.orderbyStatus(true);
				sysUserQuery.orderbyMemo(true);
				sysUserQuery.orderbyGmtCreate(true);
				sysUserQuery.orderbyGmtModified(true);
				List<SysUserDO> queryRsWithPage = sysUserDAO.getSysUserDOListWithPage(sysUserQuery);
				Assert.assertEquals(1,queryRsWithPage.size());
				Assert.assertEquals("ms2", queryRs.get(0).getUsername());	/*username*/
				Assert.assertEquals("ms3", queryRs.get(0).getPassword());	/*password*/
				Assert.assertEquals("ms4", queryRs.get(0).getName());	/*name*/
				Assert.assertEquals(15, queryRs.get(0).getIssys().intValue());	/*issys*/
				Assert.assertEquals(16, queryRs.get(0).getStatus().intValue());	/*status*/
				Assert.assertEquals("ms7", queryRs.get(0).getMemo());	/*memo*/
				//删除数据测试
				sysUserQuery = new SysUserQuery();
				sysUserQuery.addIdList(id);
				Integer rsDelete = sysUserDAO.deleteSysUserDOList(sysUserQuery);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryWithPageDeleteSysUserDOList() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally{
			if(id!=null){
				try {
					sysUserDAO.deleteSysUserDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryWithPageDeleteSysUserDOList() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

}
