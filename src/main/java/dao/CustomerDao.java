package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Customer;

/**
 * @ClassName CustomerDao
 * @Description TODO
 * @Author Finger
 * @Date 12/28/2020
 **/
public interface CustomerDao {
    List<Customer> selectAll() throws SQLException;

    List<Customer> selectCustomerByEmployeeId(int employeeId) throws SQLException;
}
