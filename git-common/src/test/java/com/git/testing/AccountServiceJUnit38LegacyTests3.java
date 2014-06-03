package com.git.testing;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("deprecation")
public class AccountServiceJUnit38LegacyTests3 extends
		AbstractDependencyInjectionSpringContextTests {
	private static final String TEST_ACCOUNT_NO = "1234";

	protected String[] getConfigLocations() {
		return new String[] { "beans.xml" };
	}

	private AccountService accountService;

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	// protected AccountService accountService;
	// public AccountServiceJUnit38LegacyTests3(){
	// setPopulateProtectedVariables(true);
	// }

	@Override
	protected void onSetUp() throws Exception {
		accountService = (AccountService) getApplicationContext().getBean(
				"accountService");
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
	protected void onTearDown() throws Exception {
		// 测试结束之后,移除创建的账号
		accountService.removeAccount(TEST_ACCOUNT_NO);
	}

}
