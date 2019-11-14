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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
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
    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime, Timestamp endTime, String alarmName, String employeeName) {
        return sumInfoService.getSumInfos(beginTime, endTime, alarmName, employeeName);
    }

    @RequestMapping("/exportSumInfos")
    public ResponseEntity<byte[]> exportSumInfos() throws IOException, ParseException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                new String("员工表.xls".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(sumInfoService.exportSumInfos().toByteArray(), headers, HttpStatus.CREATED);
    }
}
