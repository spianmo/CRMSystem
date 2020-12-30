package dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import factory.DaoFactory;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

    CustomerDao customerDao = DaoFactory.getCustomerDaoInstance();

    @Test
    void selectAll() {
        try {
            System.out.println(customerDao.selectAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void selectCustomerByEmployeeId() {
        try {
            System.out.println(customerDao.selectCustomerByEmployeeId(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}