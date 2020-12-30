package utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName JdbcUtil
 * @Description JDBC连接工具类，使用了单例模式，封装了连接和关闭方法
 * @Author Finger
 * @Date 11/15/2020
 **/

public class JDBCUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/opencrm?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=GMT%2B8";
    private static final String USERNAME = "Kirito666";
    private static final String PASSWORD = "123456";
    private static Connection connection = null;
    private static JDBCUtil jdbcUtil = null;

    /**
     * 获得JDBCUtil实例
     *
     * @return JDBCUtil实例
     */
    public static JDBCUtil getInitJdbcUtil() {
        if (jdbcUtil == null) {
            synchronized (JDBCUtil.class) {
                if (jdbcUtil == null) {
                    jdbcUtil = new JDBCUtil();
                }
            }
        }
        return jdbcUtil;
    }

    private JDBCUtil() {
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     *
     * @return CONNECTION
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;

    }

    /**
     * 关闭连接（Connection连接对象必须在最后关闭）
     * @param st 编译执行对象
     * @param rs 结果集
     */
    public void close(Statement st, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(st != null){
                st.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = JDBCUtil.getInitJdbcUtil().getConnection();
        if (connection != null) {
            System.out.println("连接成功");
        }
    }
}

