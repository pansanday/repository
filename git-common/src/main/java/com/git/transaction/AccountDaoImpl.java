package com.git.transaction;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 转账DAO的实现类
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    /**
     * @param outAccount 转出账号
     * @param money 转账金额
     */
    public void outMoney(String outAccount, Double money) {
        String sql = "update account set money = money - ? where name = ?";
        this.getJdbcTemplate().update(sql, money, outAccount);
    }

    /**
     * @param inAccount 转入账号
     * @param money 转账金额
     */
    public void inMoney(String inAccount, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql, money, inAccount);
    }
}