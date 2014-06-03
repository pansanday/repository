package com.git.testing;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

@SuppressWarnings(value = "deprecation")
@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceJUnit38LegacyTests5 extends
		AbstractTransactionalDataSourceSpringContextTests {
	private static final String TEST_ACCOUNT_NO = "1234";

	private AccountService accountService;
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		executeSqlScript("classpath:/bank.sql", true);
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

}
