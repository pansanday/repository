package com.git.testing;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 为了在TestNG中用TestContext框架访问托管的应用上线文,
 * 你可以扩展TestContext支持类AbstractTestNGSpringContextTests
 * .这个类也实现ApplicationContextAware接口
 * 
 * @author Pansanday
 * 
 */
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceTestNGContextTests extends
		AbstractTestNGSpringContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	/**
	 * 使用了@Autowired['waɪəd]注解后, 就不用accountService = (AccountService)
	 * applicationContext.getBean("accountService")了,
	 * TestNG扩展TestContext支持类AbstractTestNGSpringContextTests,从托管的应用上下文注入测试夹具
	 */
	@Autowired
	private AccountService accountService;

	@BeforeMethod
	public void init() {
		// accountService = (AccountService) applicationContext
		// .getBean("accountService");
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

	@AfterMethod
	public void cleanup() {
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
