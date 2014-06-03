package com.git.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 访问托管应用上下文第二个选择是扩展专用于JUnit 4的TestContext支持类AbstractJUnit4SpringContextTests
 * 
 * @author Pansanday
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
//指定Bean配置文件位置
@ContextConfiguration(locations = "/beans.xml")
@Transactional
public class AccountServiceJUnit4ContextTests3 extends AbstractTransactionalJUnit4SpringContextTests{

	private static final String TEST_ACCOUNT_NO = "1234";
	@Autowired
	private AccountService accountService;
	// private ApplicationContext applicationContext;

	// public void setApplicationContext(ApplicationContext applicationContext)
	// throws BeansException {
	// this.applicationContext = applicationContext;
	// }

	@Before
	public void init() {
		executeSqlScript("classpath:/bank.sql",true);
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	@Timed(millis=1000)
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	@Test
	@Repeat(5)
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}

//	@After
//	public void cleanup() {
//		accountService.removeAccount(TEST_ACCOUNT_NO);
//	}

}
