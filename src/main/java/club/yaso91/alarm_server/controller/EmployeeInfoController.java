/**
 * projectName: alarm_server
 * fileName: EmployeeInfoController.java
 * packageName: club.yaso91.alarm_server.controller
 * date: 2019-10-10 16:27
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.Employee;
import club.yaso91.alarm_server.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: EmployeeInfoController
 * @packageName: club.yaso91.alarm_server.controller
 * @description:
 * @data: 2019-10-10 16:27
 **/

@RequestMapping("/employeeInfo")
@RestController
public class EmployeeInfoController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @RequestMapping
    public ArrayList<Employee> home() {
        return employeeInfoService.loadAllEmployees();
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public boolean addEmployee(@RequestBody Employee employee) {
        return employeeInfoService.addEmployee(employee);
    }
}
