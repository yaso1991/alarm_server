/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:EmployeeInfoServiceTest.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */

package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class EmployeeInfoServiceTest {
    @Autowired
    EmployeeInfoService employeeInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void loadAllEmployees() {
        ArrayList<Employee> employees = employeeInfoService.loadAllEmployees();
        assertEquals(true, !employees.isEmpty());
        assertEquals("班组长2", employees.get(3).getName());
    }

    @Test
    public void testEmployee() {
        Employee employee = new Employee();
        employee.setName("员工C");
        employee.setWorkId("1234567890");
        employee.setPosition("员工");
        employee.setEmail("1721662545@qq.com");
        assertEquals(true, employeeInfoService.insertEmployee(employee));
    }

    @Test
    public void updateEmployee() {
        ArrayList<Employee> employees = employeeInfoService.loadAllEmployees();
        Employee employee = employees.get(0);
        employee.setEmail("fuckyou@email.com");
        employeeInfoService.updateEmployee(employee);
    }

    @Test
    public void deleteEmployees() {
    }
}
