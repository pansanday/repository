package com.git.transaction;

/**
 * 转账示例Service层接口
 */
public interface AccountService {

    /**
     * 转账方法
     * @param outAccount 转出账号
     * @param inAccount 转入账号
     * @param money 转账金额
     */
    public void transfer(String outAccount, String inAccount, Double money);
}