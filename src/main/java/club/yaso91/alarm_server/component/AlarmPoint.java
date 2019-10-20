/**
 * projectName: alarm_server
 * fileName: AlarmPoint.java
 * packageName: club.yaso91.alarm_server.bean
 * date: 2019-10-19 15:57
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.component;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmPoint
 * @packageName: club.yaso91.alarm_server.bean
 * @description: 报警点
 * @data: 2019-10-19 15:57
 **/
@Data
public class AlarmPoint {
    private String name;
    private String com;
    private boolean alarming;

    public AlarmPoint(String name,String com) {
        this.name = name;
        this.com = com;
    }
}
