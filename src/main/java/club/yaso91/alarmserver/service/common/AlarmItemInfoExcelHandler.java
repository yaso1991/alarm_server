/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ExcelHandler.java
 *    Date:2019/11/14 下午4:00
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service.common;

import club.yaso91.alarmserver.domain.AlarmItemInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ExcelHandler
 * @packageName: club.yaso91.alarmserver.service.common
 * @description: 汇总excel处理类.
 * @data: 2019-11-14 15:59
 **/
public class AlarmItemInfoExcelHandler {
    public static void generatorExcelBook(ArrayList<AlarmItemInfo> alarmItemInfos, HSSFWorkbook workbook) {
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
        for (AlarmItemInfo itemInfo : alarmItemInfos) {
            HSSFRow headRowTemp = sheet.createRow(index);
            headRowTemp.createCell(0).setCellValue(index);
            headRowTemp.createCell(1).setCellValue(itemInfo.getId());
            headRowTemp.createCell(2).setCellValue(itemInfo.getAlarmInfo().getName());
            headRowTemp.createCell(3).setCellValue(itemInfo.getAlarmStartTime().toString());
            headRowTemp.createCell(4).setCellValue(itemInfo.getAlarmSpan());
            headRowTemp.createCell(5).setCellValue(new Timestamp(itemInfo.getAlarmStartTime().getTime() + itemInfo.getAlarmSpan() * 1000).toString());
            headRowTemp.createCell(6).setCellValue(itemInfo.getEmployee().getWorkId());
            headRowTemp.createCell(7).setCellValue(itemInfo.getEmployee().getName());
            headRowTemp.createCell(8).setCellValue(itemInfo.getPushLevel());
            headRowTemp.createCell(9).setCellValue(itemInfo.getMaster().getWorkId());
            headRowTemp.createCell(10).setCellValue(itemInfo.getMaster().getName());
            index++;
        }
        workbook.setActiveSheet(0);
    }
}
