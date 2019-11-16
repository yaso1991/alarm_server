/**
 * projectName: alarmserver
 * fileName: EmployeeInfoService.java
 * packageName: club.yaso91.alarmserver.service
 * date: 2019-10-10 16:29
 * copyright(c) 2017-2020 FuYun design studio.
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

    public boolean addEmployee(Employee employee) {
        return employeeInfoMapper.insertEmployee(employee) == 1;
    }

    public boolean fixEmployee(Employee employee) {
        return employeeInfoMapper.updateEmployee(employee) == 1;
    }

    public boolean deleteEmployees(ArrayList<Integer> ids) {
        return employeeInfoMapper.deleteEmployees(ids) != 0;
    }
}
