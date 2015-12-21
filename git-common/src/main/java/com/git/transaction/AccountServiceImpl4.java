package com.git.transaction;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 转账案例的业务层实现类
 * @Transactional注解中的属性
 *  propagation     事务的传播行为
 *  isolation       事务的隔离级别
 *  readOnly        只读
 *  rollbackFor     发生哪些异常回滚
 *  noRollbackFor   发生哪些异常不回滚
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl4 implements AccountService {

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
//        int i = 1 / 0;
        accountDao.inMoney(inAccount, money);
    }
}