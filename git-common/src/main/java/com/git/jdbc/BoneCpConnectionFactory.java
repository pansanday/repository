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
 * 不使用Spring,通过JDBC获得BoneCp数据库连接池
 */
public class BoneCpConnectionFactory {

    Logger logger = LoggerFactory.getLogger(BoneCpConnectionFactory.class);

    private static BoneCP connectionPool = null;
    private static BoneCpConnectionFactory instance;

    /**
     * Constructor
     */
    private BoneCpConnectionFactory() {
        // 读取classpath下的bonecp_jdbc.properties文件
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
        config.setMinConnectionsPerPartition(Integer.parseInt(bundle.getString("minConnectionsPerPartition")));
        config.setMaxConnectionsPerPartition(Integer.parseInt(bundle.getString("maxConnectionsPerPartition")));
        config.setPartitionCount(Integer.parseInt(bundle.getString("partitionCount")));

        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 单例模式类实例.保证只调用一次构造函数,只创建一个数据库连接池
     * @return 类实例
     */
    private static BoneCpConnectionFactory getInstance() {
        synchronized (BoneCpConnectionFactory.class) {
            if (instance == null) {
//              System.out.println("类实例为空,创建,并只创建一次.");
              BoneCpConnectionFactory.instance = new BoneCpConnectionFactory();
            }
        }
        return instance;
    }

    /**
     * 从数据库连接池中获取对象
     * @return connection连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        getInstance();
        // System.out.println("每次获得的数据库连接池都是唯一的:" + connectionPool);
        Connection connection = connectionPool.getConnection();
        connection.setAutoCommit(true);
        return connection;
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
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            try {
                connection = BoneCpConnectionFactory.getConnection();
                // System.out.println("connection" + i + " ==> " + connection);
                pst = connection.prepareStatement("select * from testtable");
                rs = pst.executeQuery();
                while (rs.next()) {
                    // System.out.println(rs.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pst != null) {
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("消耗的时间为:" + (endTime - startTime) + "毫秒");
    }
}