package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.Employee;
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
        assertEquals("组长1", employees.get(3).getName());
    }
}
