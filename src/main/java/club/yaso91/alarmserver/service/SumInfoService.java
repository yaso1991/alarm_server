/**
 * projectName: alarmserver
 * fileName: SumInfoService.java
 * packageName: club.yaso91.alarmserver.service
 * date: 2019-10-07 15:47
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.common.AlarmItemInfoExcelHandler;
import club.yaso91.alarmserver.entity.AlarmItemInfo;
import club.yaso91.alarmserver.mapper.AlarmItemInfoMapper;
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
@Slf4j
public class SumInfoService {
    @Autowired
    AlarmItemInfoMapper alarmItemInfoMapper;

    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime, Timestamp endTime, String alarmName,
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
    public ByteArrayOutputStream exportSumInfos() {
        ArrayList<AlarmItemInfo> allSumInfos = this.getAllSumInfos();
        HSSFWorkbook workbook = new HSSFWorkbook();
        AlarmItemInfoExcelHandler.generatorExcelBook(allSumInfos, workbook);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            log.warn(e.toString());
        }
        return outputStream;
    }
}
