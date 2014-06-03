package com.git.testing;

public interface AccountDao {

	public void createAccount(Account account);

	public void updateAccount(Account account);

	public void removeAccount(Account account);

	public Account findAccount(String accountNo);

}
