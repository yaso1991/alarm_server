/**
 * projectName: alarm_server
 * fileName: SumInfoController.java
 * packageName: club.yaso91.alarm_server.controller
 * date: 2019-10-07 14:23
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.AlarmItemInfo;
import club.yaso91.alarm_server.service.SumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SumInfoController
 * @packageName: club.yaso91.alarm_server.controller
 * @description:
 * @data: 2019-10-07 14:23
 **/
@RequestMapping("/sumInfo")
@RestController
public class SumInfoController {
    @Autowired
    private SumInfoService sumInfoService;

    @RequestMapping("/getSumInfos")
    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime,Timestamp endTime, String alarmName, String employeeName) {
        return sumInfoService.getSumInfos(beginTime,endTime,alarmName,employeeName);
    }
}
