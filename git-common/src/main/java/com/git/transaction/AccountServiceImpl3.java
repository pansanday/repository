package com.git.transaction;

/**
 * 转账示例的业务层实现类,第三种实现方法
 */
public class AccountServiceImpl3 implements AccountService {

    // 注入转账的DAO的类
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @param outAccount 转出账号
     * @param inAccount 转入账号
     * @param money 转账金额
     */
    public void transfer(String outAccount,String inAccount,Double money) {
        accountDao.outMoney(outAccount, money);
        int i = 1 / 0;
        accountDao.inMoney(inAccount, money);
    }
}