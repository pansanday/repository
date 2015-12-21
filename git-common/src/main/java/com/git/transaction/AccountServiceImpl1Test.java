package com.git.transaction;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:c3p0_tx_demo1.xml")
public class AccountServiceImpl1Test {

    @Resource(name="accountService")
    private AccountService accountService;
    
    @Test
    public void testTransfer(){
        accountService.transfer("aaa", "bbb", 200d);
    }
}