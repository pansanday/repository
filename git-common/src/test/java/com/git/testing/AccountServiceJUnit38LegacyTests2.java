package com.git.testing;

import org.springframework.test.AbstractSingleSpringContextTests;

@SuppressWarnings("deprecation")
public class AccountServiceJUnit38LegacyTests2 extends
		AbstractSingleSpringContextTests {
	private static final String TEST_ACCOUNT_NO = "1234";
	private AccountService accountService;

	protected String[] getConfigLocations() {
		return new String[] { "beans.xml" };
	}

	@Override
	protected void onSetUp() throws Exception {
		// 从配置文件中根据bean名称(accountService)拿到bean的实例化对象,这个对象是容器初始加载时就被实例化创建的
		accountService = (AccountService) getApplicationContext().getBean(
				"accountService");
		// 获得bean后,用这个bean进行创建账号,存入初始存款
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
