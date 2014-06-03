package com.git.testing;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceImplMockTests {

	private static final String TEST_ACCOUNT_NO = "1234";
	// private EasyMock easyMock;
	private AccountDao accountDao;
	private AccountService accountService;

	@Before
	public void init() {
		accountDao = EasyMock.createMock(AccountDao.class);
		accountService = new AccountServiceImpl(accountDao);
	}

	@Test
	public void deposit() {
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		// first tell the Mock what calls it should expect, and any return values
		EasyMock.expect(accountDao.findAccount(TEST_ACCOUNT_NO)).andReturn(
				account);
		// accountDao.findAccount(TEST_ACCOUNT_NO);
		// EasyMock.expectLastCall().andReturn(account);
		account.setBalance(150);
		accountDao.updateAccount(account);

		// now put the Mock into replay mode
		EasyMock.replay(accountDao);
		accountService.deposit(TEST_ACCOUNT_NO, 50);

		// verify the Mock was called as expected
		EasyMock.verify(accountDao);
	}

	@Test
	public void withdrawWithSufficientBalance() {
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		accountDao.findAccount(TEST_ACCOUNT_NO);
		EasyMock.expectLastCall().andReturn(account);
		account.setBalance(50);
		accountDao.updateAccount(account);
		EasyMock.replay(accountDao);
		accountService.withdraw(TEST_ACCOUNT_NO, 50);
		EasyMock.verify(accountDao);
	}

	@Test(expected = InsufficientBanlanceException.class)
	public void testWithdrawInsufficientBalance() {
		Account account = new Account(TEST_ACCOUNT_NO, 100);
		accountDao.findAccount(TEST_ACCOUNT_NO);
		EasyMock.expectLastCall().andReturn(account);
		EasyMock.replay(accountDao);
		accountService.withdraw(TEST_ACCOUNT_NO, 150);
		EasyMock.verify(accountDao);
	}

}
