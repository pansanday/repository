package com.git.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractTransactionalJUnit38SpringContextTests;

/**
 * 使用TestContext框架为JUnit3创建测试时,必须扩展一个TestContext支持类以使用Spring的测试注解
 * 
 * @author Pansanday
 * 
 */
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceJUnit38ContextTests extends
		AbstractTransactionalJUnit38SpringContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";
	@Autowired
	private AccountService accountService;

	@Override
	protected void setUp() throws Exception {
		// accountService = (AccountService) applicationContext
		// .getBean("accountService");
		executeSqlScript("classpath:/bank.sql", true);
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	/**
	 * @Timed 表示测试方法必须在指定的时间段(毫秒)之内完成,否则测试失败.时间段包括测试方法的重复和所有初始化和清理工作.
	 */
	@Timed(millis = 1000)
	public void testDeposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	/**
	 * @Repeat 表示测试方法必须多次运行.运行的次数在注解值中指出.
	 */
	@Repeat(5)
	public void testWithDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}
}
