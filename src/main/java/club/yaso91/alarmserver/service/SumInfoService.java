/**
 * projectName: alarmserver
 * fileName: SumInfoService.java
 * packageName: club.yaso91.alarmserver.service
 * date: 2019-10-07 15:47
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.entity.AlarmItemInfo;
import club.yaso91.alarmserver.mapper.AlarmItemInfoMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("ID");
        headRow.createCell(2).setCellValue("报警点");
        headRow.createCell(3).setCellValue("报警时间");
        headRow.createCell(4).setCellValue("报警时长");
        headRow.createCell(5).setCellValue("结束时间");
        headRow.createCell(6).setCellValue("员工编号");
        headRow.createCell(7).setCellValue("员工姓名");
        headRow.createCell(8).setCellValue("推送");
        headRow.createCell(9).setCellValue("组长编号");
        headRow.createCell(10).setCellValue("组长姓名");

        int index = 1;
        for (AlarmItemInfo itemInfo : allSumInfos) {
            HSSFRow headRowTemp = sheet.createRow(index);
            headRowTemp.createCell(0).setCellValue(index);
            headRowTemp.createCell(1).setCellValue(itemInfo.getId());
            headRowTemp.createCell(2).setCellValue(itemInfo.getAlarmInfo().getName());
            headRowTemp.createCell(3).setCellValue(itemInfo.getAlarmStartTime());
            headRowTemp.createCell(4).setCellValue(itemInfo.getAlarmSpan());
            headRowTemp.createCell(5).setCellValue("" + itemInfo.getAlarmStartTime() + itemInfo.getAlarmSpan());
            headRowTemp.createCell(6).setCellValue(itemInfo.getEmployee().getWorkId());
            headRowTemp.createCell(7).setCellValue(itemInfo.getEmployee().getName());
            headRowTemp.createCell(8).setCellValue(itemInfo.getPushLevel());
            headRowTemp.createCell(9).setCellValue(itemInfo.getMaster().getWorkId());
            headRowTemp.createCell(10).setCellValue(itemInfo.getMaster().getName());
            index++;
        }
        workbook.setActiveSheet(0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }
}
