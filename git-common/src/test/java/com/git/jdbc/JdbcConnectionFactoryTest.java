package com.git.jdbc;

import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;

/**
 * @author pansanday
 * @since 2016年1月10日
 */
public class JdbcConnectionFactoryTest {

    @Test
    public void testGetConnection() {
        Connection connection = null;
        try {
            connection = JdbcConnectionFactory.getConnection();
            PreparedStatement pStatement = connection.prepareStatement("select * from dual");
            ResultSet rs = pStatement.executeQuery();
            String result = "";
            while (rs.next()) {
                result = rs.getString("DUMMY");
            }
            assertEquals("两者不相等", "X", result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            /*
             * if (connection == null) { System.out.println("ok"); }
             */
        }
    }
}
