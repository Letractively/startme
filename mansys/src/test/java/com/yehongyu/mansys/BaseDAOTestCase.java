package com.yehongyu.mansys;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { 
		"classpath:dal/biz-dao.xml",
		"classpath:dal/persistence/persistence.xml"
		}
)

/**
 * 测试类说明，测试本地DAO访问业务类
 * @author yingyang
 * @since 2011-11-11
 */
public class BaseDAOTestCase extends TestCase {

	protected Log logger = LogFactory.getLog(getClass());	
	
	@Before
	public void setUp() {
		
	}

	@Test
	public final void testXX(){
		Assert.assertTrue(true);
	}

}
