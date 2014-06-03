package com.git.testing;

import org.springframework.test.annotation.AbstractAnnotationAwareTransactionalTests;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;

@SuppressWarnings("deprecation")
public class AccountServiceJUnit38LegacyTests extends AbstractAnnotationAwareTransactionalTests {

	private AccountService accountService;
	private static final String TEST_ACCOUNT_NO = "1234";

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		executeSqlScript("classpath:/bank.sql", true);
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}
	
	@Timed(millis=1000)
	public void testDeposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
	}

	@Repeat(5)
	public void testWithDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}
}
