/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ModbusPointInfoMapper.java
 *    Date:2019/11/17 上午10:08
 *    Author:Yaso
 */

package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.domain.ModbusPointInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @title: ModbusPointInfoMapper
 * @package club.yaso91.alarm_server.mapper
 * @description:
 * @author: Yaso
 * @date: 2019-11-17 10:05
 * @version: V1.0
*/
@Repository
public interface ModbusPointInfoMapper {
    /**
     * 选取所有modbus point.
     * @return
     */
    ArrayList<ModbusPointInfo> selectAll();
}
