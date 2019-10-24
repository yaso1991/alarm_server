/**
 * projectName: alarm_server
 * fileName: AlarmInfo.java
 * packageName: club.yaso91.alarm_server.entity
 * date: 2019-10-05 16:34
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfo
 * @packageName: club.yaso91.alarm_server.entity
 * @description:
 * @data: 2019-10-05 16:34
 **/
@Data
public class AlarmInfo {
    private int id;
    private String name;
    private String type;
    private int cardReaderId;
    private String state;
    private boolean alarming;
    private Timestamp alarmTime;
    private int alarmSpan;
    private String pushLevel;
    private int employeeId;
    private int masterId;
    private Employee employee;
    private Employee master;
    private CardReader cardReader;
    private String comPort;
    private String pointName;
}
