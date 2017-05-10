package com.crimps.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 使用配置文件来配置JDBC连接数据库 该类用来管理数据库的连接
 */
public class DBUtil {
    // 连接数据库的路径
    public static String url;
    // 连接数据库的用户名
    public static String user;
    // 连接数据库的密码
    public static String pwd;

    public static String driver;

    private static Connection connection = null;
    private static Statement statement = null;

    // 静态块
    static {
        try {
            // 读取配置文件
            Properties prop = new Properties();
            /*
             * 这种写法是将来更加推荐的相对路径 写法。
             */
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(
                    "db.properties");

            prop.load(is);
            is.close();
            // 获取驱动
            driver = prop.getProperty("jdbc.driver");
            // 获取地址
            url = prop.getProperty("jdbc.url");
            // 获取用户名
            user = prop.getProperty("jdbc.user");
            // 获取密码
            pwd = prop.getProperty("jdbc.password");

            // 注册驱动
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        try {
            /*
             * 通过DriverManager创建一个数据库的连接 并返回
             */
            Connection conn = DriverManager.getConnection(url, user, pwd);
            /*
             * ThreadLocal的set方法 会将当前线程作为key,并将给定的值 作为value存入内部的map中保存。
             */

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            // 通知调用者，创建连接出错
            throw e;
        }
    }

    /**
     * 关闭给定的连接
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试是否连接成功
     * @param args
     * @throws Exception
     */
    public static void connection(String[] args) throws Exception {
        System.out.println(getConnection());
    }

    /**
     * 简单查询
     * @param sql 简单查询语句
     * @return
     */
    public static ResultSet getResultBySimpleSql(String sql) {
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != statement) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                DBUtil.closeConnection(connection);
            }
        }
    }
}