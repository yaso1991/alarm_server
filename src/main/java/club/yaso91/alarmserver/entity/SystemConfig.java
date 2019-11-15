package club.yaso91.alarmserver.entity;

import lombok.Data;

import java.sql.Time;


/**
 * @title: SystemConfig
 * @package club.yaso91.alarmserver.entity
 * @description:
 * @author: Yaso
 * @date: 2019-102-11 14:02
 * @version: V1.0
*/
@Data
public class SystemConfig {
    private int id;
    /**
     * 班组长级推送延时.
     */
    private int monitorPushDelay;
    /**
     * 主任级推送延时.
     */
    private int masterPushDelay;
    /**
     * 经理级推送延时.
     */
    private int managerPushDelay;
    private Time sumPushTime;
}
