package com.git.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 访问托管应用上下文第二个选择是扩展专用于JUnit 4的TestContext支持类AbstractJUnit4SpringContextTests
 * 
 * @author Pansanday
 * 
 */
// 不需要@RunWith这个注解了,因为这个注解从父类继承而来
// @RunWith(SpringJUnit4ClassRunner.class)
//指定Bean配置文件位置
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceJUnit4ContextTests2 extends
		AbstractJUnit4SpringContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";
	private AccountService accountService;
	// private ApplicationContext applicationContext;

	// public void setApplicationContext(ApplicationContext applicationContext)
	// throws BeansException {
	// this.applicationContext = applicationContext;
	// }

	@Before
	public void init() {
		accountService = (AccountService) applicationContext
				.getBean("accountService");
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	@Test
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}

	@After
	public void cleanup() {
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
