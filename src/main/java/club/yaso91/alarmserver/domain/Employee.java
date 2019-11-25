/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:Employee.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */
package club.yaso91.alarmserver.domain;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: Employee
 * @packageName: club.yaso91.alarmserver.domain
 * @description:
 * @data: 2019-10-05 17:10
 **/
@Data
public class Employee {
    private int id;
    private String name;
    private String position;
    private String workId;
    @Email
    private String email;

}
