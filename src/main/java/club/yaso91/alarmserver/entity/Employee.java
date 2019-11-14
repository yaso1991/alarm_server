/**
 * projectName: alarmserver
 * fileName: Employee.java
 * packageName: club.yaso91.alarmserver.entity
 * date: 2019-10-05 17:10
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.entity;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: Employee
 * @packageName: club.yaso91.alarmserver.entity
 * @description:
 * @data: 2019-10-05 17:10
 **/
@Data
public class Employee {
    private int id;
    private String name;
    private String position;
    private String workId;
    private String email;

}
