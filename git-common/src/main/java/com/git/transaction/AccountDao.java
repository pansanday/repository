package com.git.transaction;

/**
 * 转账示例的DAO层的接口
 */
public interface AccountDao {

    /**
     * 转出账户方法
     * @param outAccount 转出账号
     * @param money 转账金额
     */
    public void outMoney(String outAccount, Double money);

    /**
     * 转入账户方法
     * @param inAccount 转入账号
     * @param money 转账金额
     */
    public void inMoney(String inAccount, Double money);
}