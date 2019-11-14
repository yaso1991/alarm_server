/**
 * projectName: alarmserver
 * fileName: AlarmPoint.java
 * packageName: club.yaso91.alarmserver.bean
 * date: 2019-10-19 15:57
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.component;

import lombok.Data;
import lombok.NonNull;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmPoint
 * @packageName: club.yaso91.alarmserver.bean
 * @description: 报警点
 * @data: 2019-10-19 15:57
 **/
@Data
public class AlarmPoint {
    @NonNull
    private String name;
    @NonNull
    private String com;
    private boolean alarming;

    public AlarmPoint(String name,String com) {
        this.name = name;
        this.com = com;
    }
}
