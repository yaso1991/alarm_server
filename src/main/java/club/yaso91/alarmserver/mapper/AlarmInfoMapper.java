/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:AlarmInfoMapper.java
 *    Date:2019/11/14 下午2:57
 *    Author:Yaso
 */

package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.AlarmInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @title: AlarmInfoMapper
 * @package club.yaso91.alarmserver.mapper
 * @description:
 * @author: Yaso
 * @date: 2019-11-14 15:48
 * @version: V1.0
*/



@Repository
public interface AlarmInfoMapper {
    /**
     * 读取alarm_Info表中所有条目.
     *
     * @return 读到的alarmInfo List
     */
    ArrayList<AlarmInfo> selectAlarmInfos();

    /**
     * 插入一条报警信息到表.
     *
     * @param alarmInfo 要插入的报警信息.
     * @return 成功返回1, 失败返回0.
     */
    int insertAlarmInfo(AlarmInfo alarmInfo);

    /**
     * 更新一条报警记录
     *
     * @param alarmInfo 需要更新的记录
     * @return 成功返回1, 失败返回0.
     */
    int updateAlarmInfo(AlarmInfo alarmInfo);

    /**
     * 删除多条信息
     *
     * @param ids 需要删除信息ID的list
     * @return 成功返回删除的条数, 失败返回0.
     */
    int deleteAlarmInfos(ArrayList<Integer> ids);

    /**
     * 更新某条信息报警状态
     *
     * @param alarmInfo 需要更新的信息
     * @return 成功返回1, 失败返回0
     */
    int updateAlarming(AlarmInfo alarmInfo);

    /**
     * 根据串口名称和对应的modbus地址更新状态.
     *
     * @param comPort   串口名称
     * @param pointName 对应的地址(不方便rename).
     * @return 成功返回1, 失败返回0.
     */
    int updateStateIsNormal(String comPort, String pointName);

    /**
     * 根据串口名称和对应的modbus地址查找报警信息的ID
     *
     * @param comPort   串口名称
     * @param pointName 对应的地址
     * @return 成功返回1, 失败返回0.
     */
    int findId(String comPort, String pointName);

    /**
     * 根据串口名称和对应的modbus地址查找报警信息.
     *
     * @param comPort   串口名称
     * @param pointName 对应的地址
     * @return 放回读到的信息, 或null
     */
    AlarmInfo find(String comPort, String pointName);

    /**
     * 更新报警状态
     *
     * @param alarmInfo 需要更新报警信息的alarmInfo
     * @return 成功返回1, 失败返回0
     */
    int updateStateAlarmed(AlarmInfo alarmInfo);
}
