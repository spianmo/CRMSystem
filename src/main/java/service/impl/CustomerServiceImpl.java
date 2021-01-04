package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CustomerDao;
import entity.Customer;
import factory.DaoFactory;
import service.CustomerService;

/**
 * @ClassName CustomerServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public class CustomerServiceImpl implements CustomerService {

    CustomerDao mCustomerDao = DaoFactory.getCustomerDaoInstance();
    @Override
    public List<Customer> selectAll() {
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = mCustomerDao.selectAll();
        } catch (SQLException throwables) {
            System.err.println("查询客户数据出错");
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> selectCustomerByEmployeeId(int employeeId) {
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList = mCustomerDao.selectCustomerByEmployeeId(employeeId);
        } catch (SQLException throwables) {
            System.err.println("查询客户数据出错");
            throwables.printStackTrace();
        }
        return customerList;
    }
}
