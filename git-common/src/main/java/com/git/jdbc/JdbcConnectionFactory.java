package com.git.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author pansanday
 * @since 2016年1月10日
 */
public class JdbcConnectionFactory {

    final static Logger logger = LoggerFactory.getLogger(JdbcConnectionFactory.class);

    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static String driverClass = bundle.getString("driverClass");
    private static String jdbcUrl = bundle.getString("jdbcUrl");
    private static String userName = bundle.getString("user");
    private static String password = bundle.getString("password");

    private static Connection connection = null;

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            return connection;
        } catch (ClassNotFoundException e) {
            logger.error("no definition for the class with the specified name could be found");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("database access error or other errors");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 释放连接
     * TODO:没有效果,不知道为啥
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                logger.error("释放connection时出错!");
                e.printStackTrace();
            }
        }
    }
}
