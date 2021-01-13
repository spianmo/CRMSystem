package dao;

import java.util.List;

import entity.Employee;

/**
 * 员工实体操作的Dao数据访问层
 *
 * @author Finger
 */
public interface EmployeeDao {

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return
     */
    Employee selectByUserId(int userId);

    /**
     * 查询所有的员工
     *
     * @return 返回所有的员工
     */
    List<Employee> selectAllEmployee();

    /**
     * 根据部门ID查询员工
     *
     * @param departmentId 部门ID
     * @return 返回根据部门ID查询的员工实体集
     */
    List<Employee> selectEmployeeByDepartment(int departmentId);

    /**
     * 插入一条新的员工记录
     *
     * @param employee 需要新增的员工对象
     * @return 返回影响行数
     */
    int insertEmployee(Employee employee);

    /**
     * 更新某一条员工记录
     *
     * @param employee 需要更新的员工对象
     * @return 返回影响行数
     */
    int updateEmployee(Employee employee);

    /**
     * 根据员工ID删除员工
     *
     * @param employeeId 需要删除的员工ID
     * @return 返回影响行数
     */
    int deleteEmployeeById(int employeeId);
}
