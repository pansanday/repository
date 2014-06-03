package com.git.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

@SuppressWarnings(value = "deprecation")
public class AccountServiceJUnit38LegacyTests6 extends
		AbstractTransactionalDataSourceSpringContextTests  {
	private static final String TEST_ACCOUNT_NO = "12";

	protected String[] getConfigLocations() {
		return new String[] { "beans.xml" };
	}
	
	@Autowired
	private AccountService accountService;

	protected void onSetUpInTransaction() throws Exception {
		accountService = (AccountService) getApplicationContext().getBean(
				"accountService");
		executeSqlScript("classpath:/bank.sql", true);
		getJdbcTemplate().update(
				"INSERT INTO ACCOUNT(ACCOUNT_NO,BALANCE) VALUES (?,?)",
				new Object[] { TEST_ACCOUNT_NO, 100 });
	}

	public void testDeposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		double balance = getJdbcTemplate().queryForObject(
				"SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=?",
				new Object[] { TEST_ACCOUNT_NO }, Double.class);
		assertEquals(balance, 150.0);
	}

	public void testWithDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		double balance = getJdbcTemplate().queryForObject(
				"SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO=?",
				new Object[] { TEST_ACCOUNT_NO }, Double.class);
		assertEquals(balance, 50.0);
	}
}
