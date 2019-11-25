/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:EmployeeInfoController.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */
package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.domain.Employee;
import club.yaso91.alarmserver.service.EmployeeInfoService;
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
 * @packageName: club.yaso91.alarmserver.controller
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
        return employeeInfoService.insertEmployee(employee);
    }

    @RequestMapping(value = "/fixEmployee", method = RequestMethod.POST)
    public boolean fixEmployee(@RequestBody Employee employee) {
        return employeeInfoService.updateEmployee(employee);
    }

    @RequestMapping(value = "deleteEmployees", method = RequestMethod.POST)
    public boolean deleteEmployees(@RequestBody ArrayList<Integer> ids) {
        return employeeInfoService.deleteEmployees(ids);
    }
}
