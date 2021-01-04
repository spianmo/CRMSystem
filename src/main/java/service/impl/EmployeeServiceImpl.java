package service.impl;

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
    public Employee selectByUserId(String employeeId) {
        return mEmployeeDao.selectByUserId(employeeId);
    }
}
