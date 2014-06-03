package com.git.testing;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/beans.xml")
public class AccountServiceTestNGContextTests2 extends
		AbstractTransactionalTestNGSpringContextTests {
	private static final String TEST_ACCOUNT_NO = "1234";

	@Autowired
	private AccountService accountService;

	@BeforeMethod
	public void init() {
		executeSqlScript("classpath:/bank.sql", true);
		accountService.createAccount(TEST_ACCOUNT_NO);
		accountService.deposit(TEST_ACCOUNT_NO, 100);
	}

	@Test
	public void deposit() {
		accountService.deposit(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150.0, 0);
	}

	@Test
	public void withDraw() {
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
	}

}
