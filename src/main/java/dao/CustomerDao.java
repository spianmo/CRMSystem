package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Customer;

/**
 * @ClassName CustomerDao
 * @Description 客户实体操作的Dao数据访问层
 * @Author Finger
 * @Date 12/28/2020
 **/
public interface CustomerDao {
    /**
     * 查询所有的客户
     * @return 所有的客户实体集
     * @throws SQLException SQL查询异常
     */
    List<Customer> selectAll() throws SQLException;

    /**
     * 根据员工ID查询其所属的所有客户
     * @param employeeId 员工ID
     * @return 某员工所有客户的实体集
     * @throws SQLException SQL查询异常
     */
    List<Customer> selectCustomerByEmployeeId(int employeeId) throws SQLException;
}
