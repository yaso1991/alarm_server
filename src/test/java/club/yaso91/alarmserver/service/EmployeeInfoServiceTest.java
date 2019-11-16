package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.entity.Employee;
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
    public void addEmployee() {
        Employee employee = new Employee();
        employee.setName("员工C");
        employee.setWorkId("1234567890");
        employee.setPosition("员工");
        employee.setEmail("1721662545@qq.com");
        assertEquals(true, employeeInfoService.addEmployee(employee));
    }
}
