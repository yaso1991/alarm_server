/**
 * projectName: alarmserver
 * fileName: EmployeeInfoMapper.java
 * packageName: club.yaso91.alarmserver.mapper
 * date: 2019-10-10 16:50
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: EmployeeInfoMapper
 * @packageName: club.yaso91.alarmserver.mapper
 * @description:
 * @data: 2019-10-10 16:50
 **/
@Repository
public interface EmployeeInfoMapper {
    ArrayList<Employee> selectAll();

    int insertEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployees(ArrayList<Integer> ids);

    ArrayList<String> selectEmails(String position);
}