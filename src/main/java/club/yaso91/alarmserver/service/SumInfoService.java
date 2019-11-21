/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SumInfoService.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.AlarmItemInfo;
import club.yaso91.alarmserver.mapper.AlarmItemInfoMapper;
import club.yaso91.alarmserver.service.common.AlarmItemInfoExcelHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SumInfoService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-10-07 15:47
 **/
@Service
public class SumInfoService {
    @Autowired
    AlarmItemInfoMapper alarmItemInfoMapper;

    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime, Timestamp endTime,
                                                String alarmName,
                                                String employeeName) {
        return alarmItemInfoMapper.selectSumInfos(beginTime, endTime, alarmName, employeeName);
    }


    public ArrayList<AlarmItemInfo> getAllSumInfos() {
        return this.getSumInfos(null, null, null, null);
    }

    /**
     * 生成汇总信息的Excel文档.
     *
     * @return
     */
    public ByteArrayOutputStream exportSumInfos() throws IOException {
        ArrayList<AlarmItemInfo> allSumInfos = this.getAllSumInfos();
        HSSFWorkbook workbook = new HSSFWorkbook();
        AlarmItemInfoExcelHandler.generatorExcelBook(allSumInfos, workbook);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }
}
