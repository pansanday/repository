package com.git.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

/**
 * 在JUnit 3中,如果使用了@ContextConfiguration进行加载beans.xml,
 * 那么这个类需要继承AbstractJUnit38SpringContextTests
 * 
 * @author Pansanday
 * 
 */
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceJUnit38LegacyTests4 extends
		AbstractJUnit38SpringContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";

	@Autowired
	private AccountService accountService;

	@Override
	protected void setUp() throws Exception {
		System.out.println("初始化账户...");
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	public void testDeposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150.0);
	}

	public void testWithDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50.0);
	}

	@Override
	protected void tearDown() throws Exception {
		System.out.println("清除账号: " + TEST_ACCOUNT_NO);
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
