package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.mapper.AlarmItemInfoMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AlarmStateServiceTest {
    @Autowired
    AlarmStateService alarmStateService;

    @Autowired
    AlarmItemInfoMapper alarmItemInfoMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClass() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateAlarmInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    alarmStateService.updateAlarmInfo();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        try {
            Thread.currentThread().join(90000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pushSumInfo() {
        //生成测试数据.
//        for (int i = 0; i < 60; i++) {
//            AlarmItemInfo alarmItemInfo = new AlarmItemInfo();
//
//            AlarmInfo alarmInfo = new AlarmInfo();
//            alarmInfo.setId(new Random(System.currentTimeMillis()).nextInt(29)+1);
//            alarmItemInfo.setAlarmInfo(alarmInfo);
//
//            Timestamp alarmStartTime =
//                    new Timestamp(YasoUtils.getYestodayMills() + new Random(System.currentTimeMillis()).nextInt(80000) * 1000);
//            alarmItemInfo.setAlarmStartTime(alarmStartTime);
//            int alarmSpan = new Random(System.currentTimeMillis()).nextInt(1000);
//            alarmItemInfo.setAlarmSpan(alarmSpan);
//
//            if (alarmSpan < 20) {
//                alarmItemInfo.setPushLevel("未推送");
//            } else if (alarmSpan >= 20 && alarmSpan < 40) {
//                alarmItemInfo.setPushLevel("班组长级");
//            } else if (alarmSpan >= 40 && alarmSpan < 60) {
//                alarmItemInfo.setPushLevel("主任级");
//            } else if (alarmSpan >= 60) {
//                alarmItemInfo.setPushLevel("经理级");
//            } else {
//
//            }
//
//            Employee employee = new Employee();
//            employee.setId(4);
//            alarmItemInfo.setEmployee(employee);
//
//            Employee master = new Employee();
//            master.setId(17);
//
//            alarmItemInfo.setMaster(master);
//
//            alarmItemInfoMapper.insert(alarmItemInfo);
//        }
        alarmStateService.checkAndPushSumInfo();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
