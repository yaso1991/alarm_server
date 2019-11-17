/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ModbusPointInfo.java
 *    Date:2019/11/17 上午9:44
 *    Author:Yaso
 */
package club.yaso91.alarmserver.domain;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ModbusPointInfo
 * @packageName: club.yaso91.alarmserver.domain
 * @description:
 * @data: 2019-11-17 9:44
 **/
@Data
public class ModbusPointInfo {
    private int id;
    private ComInfo comInfo;
    private String  name;
    private int deviceId;
    private int code;
    private int ref;
    private int count;


}
