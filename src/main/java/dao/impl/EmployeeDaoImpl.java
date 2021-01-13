package dao.impl;

import org.intellij.lang.annotations.Language;

import java.util.List;

import dao.EmployeeDao;
import entity.Employee;
import utils.jdbc.BeanHandler;
import utils.jdbc.BeanListHandler;
import utils.jdbc.Db;

/**
 * @ClassName EmployeeDao的Impl实现类
 * @Description EmployeeDao的Impl实现类
 * @Author Finger
 * @Date 12/30/2020
 **/
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public Employee selectByUserId(int userId) {
        @Language("SQL") String sql = "SELECT * FROM t_employee WHERE user_id = ?";
        return Db.executeQuery(sql, new BeanHandler<>(Employee.class), userId);
    }

    @Override
    public List<Employee> selectAllEmployee() {
        @Language("SQL") String sql = "SELECT * FROM t_employee";
        return Db.executeQuery(sql, new BeanListHandler<>(Employee.class));
    }

    @Override
    public List<Employee> selectEmployeeByDepartment(int departmentId) {
        @Language("SQL") String sql = "SELECT * FROM t_employee WHERE department_id = ?";
        return Db.executeQuery(sql, new BeanListHandler<>(Employee.class), departmentId);
    }

    @Override
    public int insertEmployee(Employee employee) {
        @Language("SQL") String sql = "INSERT INTO t_employee (name, produce_type, department_id, salary) VALUES (?, ?, ?, ?);";
        return Db.executeUpdate(sql, employee.getName(), employee.getProduceType(), employee.getDepartmentId(), employee.getSalary());
    }

    @Override
    public int updateEmployee(Employee employee) {
        @Language("SQL") String sql = "UPDATE t_employee SET name = ?, produce_type = ?, department_id = ?, salary = ? WHERE employee_id = ?";
        return Db.executeUpdate(sql, employee.getName(), employee.getProduceType(), employee.getDepartmentId(), employee.getSalary(), employee.getEmployeeId());
    }

    @Override
    public int deleteEmployeeById(int employeeId) {
        @Language("SQL") String sql = "DELETE FROM t_employee WHERE employee_id = ?";
        return Db.executeUpdate(sql, employeeId);
    }

}
