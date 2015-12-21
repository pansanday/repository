package com.git.transaction;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring的声明式事务管理的方式二:基于AspectJ的XML方式的配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:c3p0_tx_demo3.xml")
public class AccountServiceImpl3Test {
    
    /**
     * 注入代理类:因为代理类进行增强的操作
     */
    // 测试业务层的类
    @Resource(name="accountService")
    private AccountService accountService;

    /**
     * 转账案例
     */
    @Test
    public void demo3() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}