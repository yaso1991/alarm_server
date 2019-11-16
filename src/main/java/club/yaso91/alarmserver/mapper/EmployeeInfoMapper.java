/**
 * projectName: alarmserver
 * fileName: EmployeeInfoMapper.java
 * packageName: club.yaso91.alarmserver.mapper
 * date: 2019-10-10 16:50
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.domain.Employee;
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
    /**
     * 选取
     *
     * @return
     */
    ArrayList<Employee> selectAll();

    /**
     * 插入
     *
     * @param employee
     * @return
     */
    int insertEmployee(Employee employee);

    /**
     * 更新
     *
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteEmployees(ArrayList<Integer> ids);

    /**
     * 根据职位选取emails
     *
     * @param position 职务
     * @return 选取的email List
     */
    ArrayList<String> selectEmails(String position);
}
