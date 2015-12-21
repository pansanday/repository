package com.git.transaction;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 转账示例的业务层实现类,第一种实现方法
 */
public class AccountServiceImpl1 implements AccountService {

    // 注入转账的DAO的类
    private AccountDao accountDao;
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    
    // 注入事务管理的模板
    private TransactionTemplate transactionTemplate;
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /**
     * 转账方法的第一种实现方法
     * @param outAccount 转出账号
     * @param inAccount 转入账号
     * @param money 转账金额
     */
    public void transfer(final String outAccount,final String inAccount,final Double money) {
        // 匿名内部类TransactionCallbackWithoutResult
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 被绑定到一个事务中
                accountDao.outMoney(outAccount, money);
                // 给定一个异常,以检测是否能同时成功/同时失败
//                int i = 1 / 0;
                accountDao.inMoney(inAccount, money);
            }
        });
    }
}