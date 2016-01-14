package com.git.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author pansanday
 * @since 2016年1月14日
 * BoneCp数据库连接池
 */
public class BoneCpConnectionFactory {
    
    Logger logger = LoggerFactory.getLogger(BoneCpConnectionFactory.class);

    private static BoneCP connectionPool = null;

    /**
     * Constructor
     */
    private BoneCpConnectionFactory() {
        ResourceBundle bundle = ResourceBundle.getBundle("bonecp_jdbc");
        try {
            // load the database driver
            Class.forName(bundle.getString("driverClass"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Configuration for pool,setup the connection pool
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(bundle.getString("jdbcUrl"));
        config.setUsername(bundle.getString("user"));
        config.setPassword(bundle.getString("password"));
        config.setMinConnectionsPerPartition(5);
        config.setMaxConnectionsPerPartition(10);
        config.setPartitionCount(1);

        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池 
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        synchronized (BoneCpConnectionFactory.class) {
            if (connectionPool == null) {
                new BoneCpConnectionFactory();
            }
            // fetch a connection
            Connection connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            return connection;
        }
    }
    
    /**
     * 释放连接
     * @param connection
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            Connection connection = BoneCpConnectionFactory.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from testtable");
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString(2));
            }
            rs.close();
            pst.close();
            releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
