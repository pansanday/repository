package com.git.jdbc;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author pansanday
 * @since 2016年1月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bonecp_datasource.xml")
public class BoneCpConnectionFactoryTest {

    @Resource(name="bonecpService")
    private BoneCpService boneCpService;
    
    @Test
    public void testGetData() {
        Map<String, Object> map = boneCpService.fetchData("1");
        assertEquals("两者不相等", "panda1",map.get("name"));
    }

}
