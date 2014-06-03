package com.git.testing;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceJUnit38ContextTests2 extends
		AbstractJUnit38SpringContextTests {

	private static final String TEST_ACCOUNT_NO = "1234";
	private AccountService accountService;

	@Override
	protected void setUp() throws Exception {
		accountService = (AccountService) applicationContext
				.getBean("accountService");
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
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
