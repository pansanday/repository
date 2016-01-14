package com.git.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author pansanday
 * @since 2016年1月14日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bonecp_datasource.xml")
public class BoneCpConnectionFactoryTest2 {

    // 通过注解自动获得Spring初始化的数据源
    @Autowired
    DataSource dSource;

    @Test
    public void testGetConnection() throws SQLException {
        // 获得链接connection
        Connection connection = dSource.getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from testtable");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
    }
}
