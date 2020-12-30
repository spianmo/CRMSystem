package utils.jdbc;

/**
 * @ClassName CRUDTemplate
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {
    /**
     * 增删改操作
     *
     * @param sql    传入的SQL语句
     * @param params 可变参数
     * @return 操作结果
     */
    public static int executeUpdate(String sql, Object... params) {
        if (countMark(sql) != params.length) {
            System.err.println("SQL占位符与实际参数个数不匹配，占位符数目" + countMark(sql) + ",实际参数个数" + params.length);
            return 0;
        }
        JDBCUtil jdbcUtil = JDBCUtil.getInitJdbcUtil();
        Connection connection = null;
        PreparedStatement psmt = null;
        int result = 0;
        try {
            connection = jdbcUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(psmt, null);
        }
        return result;
    }

    /**
     * 查询操作
     *
     * @param sql     SQL语句
     * @param handler 判断查询一个还是多个
     * @param params  可变参数
     * @param <T>     具体操作的实体类
     * @return 返回IResultSetHandler接口中的泛型
     */
    public static <T> T executeQuery(String sql, IResultSetHandler<T> handler, Object... params) {
        if (countMark(sql) != params.length) {
            System.err.println("SQL占位符与实际参数个数不匹配，占位符数目" + countMark(sql) + ",实际参数个数" + params.length);
            return null;
        }
        JDBCUtil jdbcUtil = JDBCUtil.getInitJdbcUtil();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            connection = jdbcUtil.getConnection();
            psmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
            rs = psmt.executeQuery();
            return handler.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(psmt, rs);
        }
        return null;
    }

    private static int countMark(String sql) {
        int count = 0;
        char[] data = new char[sql.length()];
        sql.getChars(0, sql.length(), data, 0);
        for (char c : data) {
            if (c == '?') {
                count++;
            }
        }
        return count;
    }
}


