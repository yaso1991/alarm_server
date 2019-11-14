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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
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

    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime, Timestamp endTime, String alarmName, String employeeName) {
        return alarmItemInfoMapper.selectSumInfos(beginTime, endTime, alarmName, employeeName);
    }


    public ArrayList<AlarmItemInfo> getAllSumInfos() {
        return this.getSumInfos(null, null, null, null);
    }

    public ByteArrayOutputStream exportSumInfos() throws IOException, ParseException {
        ArrayList<AlarmItemInfo> allSumInfos = this.getAllSumInfos();
        HSSFWorkbook workbook = new HSSFWorkbook();
        AlarmItemInfoExcelHandler.generatorExcelBook(allSumInfos,workbook);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }
}
