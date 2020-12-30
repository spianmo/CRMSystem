package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import entity.Customer;
import utils.jdbc.JDBCUtil;

/**
 * @ClassName CustomerDaoImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/
public class CustomerDaoImpl implements CustomerDao {

    @Override
    public List<Customer> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customerList = convert(resultSet);
        jdbcUtil.close(preparedStatement,resultSet);
        return customerList;
    }

    @Override
    public List<Customer> selectCustomerByEmployeeId(int employeeId) throws SQLException{
        JDBCUtil jdbcUtil = JDBCUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_customer WHERE employee_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, String.valueOf(employeeId));
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Customer> customerList = convert(resultSet);
        resultSet.close();
        preparedStatement.close();
        jdbcUtil.close(preparedStatement,resultSet);
        return customerList;
    }

    private List<Customer> convert(ResultSet resultSet) throws SQLException{
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(resultSet.getInt("customer_id"));
            customer.setName(resultSet.getString("name"));
            customer.setAddress(resultSet.getString("address"));
            customer.setCredit(resultSet.getInt("credit"));
            customer.setPhone(resultSet.getString("phone"));
            customer.setEmployeeId(resultSet.getInt("employee_id"));
            customerList.add(customer);
        }
        return customerList;
    }
}
