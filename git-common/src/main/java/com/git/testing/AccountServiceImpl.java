package com.git.testing;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void createAccount(String accountNo) {
		accountDao.createAccount(new Account(accountNo, 0));
	}

	public void removeAccount(String accountNo) {
		Account account = accountDao.findAccount(accountNo);
		accountDao.removeAccount(account);
	}

	/**
	 * 存款
	 */
	public void deposit(String accountNo, double amount) {
		Account account = accountDao.findAccount(accountNo);
		account.setBalance(account.getBalance() + amount);
		accountDao.updateAccount(account);
	}

	/**
	 * 取款
	 */
	public void withdraw(String accountNo, double amount) {
		Account account = accountDao.findAccount(accountNo);
		if (account.getBalance() < amount) {
			throw new InsufficientBanlanceException();
		}
		account.setBalance(account.getBalance() - amount);
		accountDao.updateAccount(account);
	}

	public double getBalance(String accountNo) {
		return accountDao.findAccount(accountNo).getBalance();
	}

}
