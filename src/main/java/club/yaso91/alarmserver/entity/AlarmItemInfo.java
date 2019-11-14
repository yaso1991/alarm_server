/**
 * projectName: alarmserver
 * fileName: AlarmItemInfo.java
 * packageName: club.yaso91.alarmserver.entity
 * date: 2019-10-07 14:24
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmItemInfo
 * @packageName: club.yaso91.alarmserver.entity
 * @description:
 * @data: 2019-10-07 14:24
 **/
@Data
public class AlarmItemInfo {
    private int id;
    private AlarmInfo alarmInfo;
    private Timestamp alarmStartTime;
    private int alarmSpan;
    private String pushLevel;
    private Employee employee;
    private Employee master;
}
