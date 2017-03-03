/**
 * @(#)com.crimps.poetry_dataClean.PoetryDataSource.java Copyright (c) 2014-2018 crimps
 */
package com.crimps.poetry_dataClean;

import com.crimps.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author crimps
 * @version 1.0  17/3/3
 * @modified crimps  17/3/3  <创建>
 */
public class PoetryDataSource {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement;

    public static final String TABLE_ID = "id";
    public static final String TABLE_NAME = "name";
    public static final String TABLE_AUTHOR = "author";
    public static final String TABLE_CONTENT = "content";
    public static final String TABLE_ROLLID = "rollId";
    public static final String TABLE_ARTICLESID = "articlesId";

    /**
     * 简单查询
     * @param sql 简单查询语句
     * @return
     */
    public ResultSet getResultBySimpleSql(String sql) {
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

    /**
     * 简单查询
     * @param sql 简单查询语句
     * @return
     * @throws Exception
     */
    public List<Map<String, String>> getMapBySimpleSql(String sql) throws Exception{
        List<Map<String, String>> resultMapList = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (null != resultMapList) {
                while (resultSet.next()) {
                    Map<String, String> map = new HashMap<>();
                    map.put(TABLE_ID, resultSet.getString(TABLE_ID));
                    map.put(TABLE_NAME, resultSet.getString(TABLE_NAME));
                    map.put(TABLE_AUTHOR, resultSet.getString(TABLE_AUTHOR));
                    map.put(TABLE_CONTENT, resultSet.getString(TABLE_CONTENT));
                    map.put(TABLE_ROLLID, resultSet.getString(TABLE_ROLLID));
                    map.put(TABLE_ARTICLESID, resultSet.getString(TABLE_ARTICLESID));
                    resultMapList.add(map);
                }
            }
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

        return resultMapList;
    }

    public int saveBySimpleSql(String sql) throws Exception {
        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
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