/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:EmployeeInfoService.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.Employee;
import club.yaso91.alarmserver.mapper.EmployeeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: EmployeeInfoService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-10-10 16:29
 **/
@Service
public class EmployeeInfoService {
    @Autowired
    EmployeeInfoMapper employeeInfoMapper;

    public ArrayList<Employee> loadAllEmployees() {
        return employeeInfoMapper.selectAll();
    }

    public boolean insertEmployee(Employee employee) {
        return employeeInfoMapper.insertEmployee(employee) == 1;
    }

    public boolean updateEmployee(Employee employee) {
        return employeeInfoMapper.updateEmployee(employee) == 1;
    }

    public boolean deleteEmployees(ArrayList<Integer> ids) {
        return employeeInfoMapper.deleteEmployees(ids) != 0;
    }
}
