package service;

import entity.Employee;

/**
 * @ClassName EmployeeService
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public interface EmployeeService {
    Employee selectByUserId(String employeeId);
}
