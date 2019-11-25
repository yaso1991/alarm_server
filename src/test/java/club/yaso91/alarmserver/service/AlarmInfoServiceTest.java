/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:AlarmInfoServiceTest.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */

package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.AlarmInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AlarmInfoServiceTest {
    @Autowired
    private AlarmInfoService alarmInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAlarmInfos() {
        assertEquals(true, alarmInfoService.getAlarmInfos() != null);
        assertEquals("员工1", alarmInfoService.getAlarmInfos().get(0).getEmployee().getName());
    }

    @Test
    public void addAlarmInfo() {
        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setCardReaderId(4);
        alarmInfo.setComPort("com4");
        alarmInfo.setName("83号报警点");
        alarmInfo.setType("测厚系统");
        alarmInfo.setState("维护");
        alarmInfo.setPointName("40001");
        assertEquals(true, alarmInfoService.insertAlarmInfo(alarmInfo));
    }

    @Test
    public void fixAlarmInfo() {
        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setId(32);
        alarmInfo.setCardReaderId(4);
        alarmInfo.setComPort("com4");
        alarmInfo.setName("83号报警点");
        alarmInfo.setType("测厚系统");
        alarmInfo.setState("维护");
        alarmInfo.setPointName("40001");
        assertEquals(true, alarmInfoService.updateAlarmInfo(alarmInfo));
    }

    @Test
    public void deleteAlarmInfos() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(12);
        ids.add(14);
        assertEquals(true, alarmInfoService.deleteAlarmInfos(ids));


    }
}
