package service;

import java.util.List;

import entity.Customer;

public interface CustomerService {
    /**
     * 查询所有的客户
     * @return 所有的客户实体集
     */
    List<Customer> selectAll();

    /**
     * 根据员工ID查询其所属的所有客户
     * @param employeeId 员工ID
     * @return 某员工所有客户的实体集
     */
    List<Customer> selectCustomerByEmployeeId(int employeeId);
}
