package com.yehongyu.mansys.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.yehongyu.mansys.BaseDAOTestCase;
import com.yehongyu.mansys.dao.domain.SysUserMenuDO;
import com.yehongyu.mansys.dao.ibatis.SysUserMenuDAO;
import com.yehongyu.mansys.dao.query.SysUserMenuQuery;

/**
 * sys_user_menu测试类
 * @author yingyang
 * @since 2011-11-11
 */
public class SysUserMenuDAOTest extends BaseDAOTestCase {
	@Resource
	private SysUserMenuDAO sysUserMenuDAO;

	@Test
	public final void testInsertUpdateQueryDeleteSysUserMenuDO() {
		//准备测试数据
		SysUserMenuDO sysUserMenuDO = new SysUserMenuDO();
		sysUserMenuDO.setUserid(2L);	/*userid*/
		sysUserMenuDO.setMenuid(3L);	/*menuid*/
		Long id = null;
		try {
			//插入数据
			id = sysUserMenuDAO.insertSysUserMenuDO(sysUserMenuDO);
			if(id!=null){
				//更新数据测试
				sysUserMenuDO = new SysUserMenuDO();
				sysUserMenuDO.setId(id);
				sysUserMenuDO.setUserid(12L);	/*userid*/
				sysUserMenuDO.setMenuid(13L);	/*menuid*/
				Integer rsUpdate = sysUserMenuDAO.updateSysUserMenuDO(sysUserMenuDO);
				Assert.assertEquals(1, rsUpdate.intValue());
				//查询测试
				SysUserMenuQuery sysUserMenuQuery = new SysUserMenuQuery();
				sysUserMenuQuery.setId(id);
				SysUserMenuDO queryRs = sysUserMenuDAO.getSysUserMenuDO(sysUserMenuQuery);
				Assert.assertEquals(12L, queryRs.getUserid().longValue());	/*userid*/
				Assert.assertEquals(13L, queryRs.getMenuid().longValue());	/*menuid*/
				//删除数据测试
				Integer rsDelete = sysUserMenuDAO.deleteSysUserMenuDO(id);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryDeleteSysUserMenuDO() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally {
			if(id!=null){
				try {
					sysUserMenuDAO.deleteSysUserMenuDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryDeleteSysUserMenuDO() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

	@Test
	public final void testInsertUpdateQueryWithPageDeleteSysUserMenuDOList() {
		//准备测试数据
		SysUserMenuDO sysUserMenuDO = new SysUserMenuDO();
		sysUserMenuDO.setUserid(2L);	/*userid*/
		sysUserMenuDO.setMenuid(3L);	/*menuid*/
		Long id = null;
		try {
			//插入数据
			id = sysUserMenuDAO.insertSysUserMenuDO(sysUserMenuDO);
			if(id!=null){
				//批量更新数据测试
				sysUserMenuDO = new SysUserMenuDO();
				sysUserMenuDO.addIdList(id);
				sysUserMenuDO.setUserid(12L);	/*userid*/
				sysUserMenuDO.setMenuid(13L);	/*menuid*/
				Integer rs = sysUserMenuDAO.updateSysUserMenuDOList(sysUserMenuDO);
				Assert.assertEquals(1, rs.intValue());
				//批量查询测试
				SysUserMenuQuery sysUserMenuQuery = new SysUserMenuQuery();
				sysUserMenuQuery.addIdList(id);
				sysUserMenuQuery.orderbyId(false);
				sysUserMenuQuery.orderbyUserid(false);
				sysUserMenuQuery.orderbyMenuid(false);
				sysUserMenuQuery.orderbyGmtCreate(false);
				sysUserMenuQuery.orderbyGmtModified(false);
				List<SysUserMenuDO> queryRs = sysUserMenuDAO.getSysUserMenuDOList(sysUserMenuQuery);
				Assert.assertEquals(1,queryRs.size());
				Assert.assertEquals(12L, queryRs.get(0).getUserid().longValue());	/*userid*/
				Assert.assertEquals(13L, queryRs.get(0).getMenuid().longValue());	/*menuid*/
				//分页查询测试
				sysUserMenuQuery = new SysUserMenuQuery();
				sysUserMenuQuery.addIdList(id);
				sysUserMenuQuery.orderbyId(true);
				sysUserMenuQuery.orderbyUserid(true);
				sysUserMenuQuery.orderbyMenuid(true);
				sysUserMenuQuery.orderbyGmtCreate(true);
				sysUserMenuQuery.orderbyGmtModified(true);
				List<SysUserMenuDO> queryRsWithPage = sysUserMenuDAO.getSysUserMenuDOListWithPage(sysUserMenuQuery);
				Assert.assertEquals(1,queryRsWithPage.size());
				Assert.assertEquals(12L, queryRs.get(0).getUserid().longValue());	/*userid*/
				Assert.assertEquals(13L, queryRs.get(0).getMenuid().longValue());	/*menuid*/
				//删除数据测试
				sysUserMenuQuery = new SysUserMenuQuery();
				sysUserMenuQuery.addIdList(id);
				Integer rsDelete = sysUserMenuDAO.deleteSysUserMenuDOList(sysUserMenuQuery);
				Assert.assertEquals(1, rsDelete.intValue());
			}else{
				Assert.assertFalse("happen Exception,not insert demo data", true);
			}
		} catch (Exception e) {
			logger.error("testInsertUpdateQueryWithPageDeleteSysUserMenuDOList() error", e);
			Assert.assertFalse("happen Exception", true);
		} finally{
			if(id!=null){
				try {
					sysUserMenuDAO.deleteSysUserMenuDO(id);
				} catch (Exception e) {
					logger.error("testInsertUpdateQueryWithPageDeleteSysUserMenuDOList() error at finally delete demo data", e);
					Assert.assertFalse("happen Exception", true);
				}
			}
		}
	}

}
