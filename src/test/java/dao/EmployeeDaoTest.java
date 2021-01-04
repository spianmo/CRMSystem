package dao;

import entity.Employee;
import factory.DaoFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class EmployeeDaoTest {
    EmployeeDao employeeDao = DaoFactory.getEmployeeDaoInstance();

    @Test
    void selectAllEmployee() {
        System.out.println(employeeDao.selectAllEmployee());
    }

    @Test
    void selectEmployeeByDepartment() {
        System.out.println(employeeDao.selectEmployeeByDepartment(1));
    }

    @Test
    void insertEmployee() {
        System.out.println(employeeDao.insertEmployee(Employee.builder()
                .name("余欣婷")
                .departmentId(1)
                .produceType("游戏")
                .salary(new BigDecimal("23000"))
                .build()
        ));
    }

    @Test
    void updateEmployee() {
        System.out.println(employeeDao.updateEmployee(Employee.builder()
                .employeeId(12)
                .name("余欣婷修改")
                .departmentId(2)
                .produceType("游戏")
                .salary(new BigDecimal("22000"))
                .build()
        ));
    }

    @Test
    void deleteEmployeeById() {
        System.out.println(employeeDao.deleteEmployeeById(13));
    }
}