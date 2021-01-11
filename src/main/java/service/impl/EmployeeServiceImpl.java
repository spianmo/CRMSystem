package service.impl;

import java.util.List;

import dao.EmployeeDao;
import entity.Employee;
import factory.DaoFactory;
import service.EmployeeService;

/**
 * @ClassName EmployeeServiceImpl
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao mEmployeeDao = DaoFactory.getEmployeeDaoInstance();
    @Override
    public Employee selectByUserId(String userId) {
        return mEmployeeDao.selectByUserId(userId);
    }

    @Override
    public List<Employee> selectAllEmployee(){
        return mEmployeeDao.selectAllEmployee();
    }

    @Override
    public List<Employee> selectEmployeeByDepartment(int departmentId) {
        return mEmployeeDao.selectEmployeeByDepartment(departmentId);
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        return mEmployeeDao.insertEmployee(employee) == 1;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return mEmployeeDao.updateEmployee(employee) == 1;
    }

    @Override
    public boolean deleteEmployeeById(int employeeId) {
        return mEmployeeDao.deleteEmployeeById(employeeId) == 1;
    }
}
