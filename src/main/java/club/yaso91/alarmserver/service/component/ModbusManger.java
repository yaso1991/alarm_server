/**
 * projectName: alarmserver
 * fileName: ModbusManger.java
 * packageName: club.yaso91.alarmserver.service.component
 * date: 2019-10-19 18:49
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service.component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ModbusManger
 * @packageName: club.yaso91.alarmserver.service.component
 * @description: 串口管理
 * TODO 等注解完成后,对ModbusManager,和AlarmStateService进行整合.
 * @data: 2019-10-19 18:49
 **/

@Data
@Slf4j
public class ModbusManger {
    private ArrayList<ModbusCom> coms = new ArrayList<>();
    private ThreadPoolExecutor threadPoolExecutor;


    public ModbusManger() {
        // TODO 串口数量和点配置使用数据库保存.在数据库里建表.
        // 添加串口和点信息.
        ModbusCom com11 = new ModbusCom("COM11");
        com11.addPoint(new ModbusPoint("1#报警点", 17, 2, 34, 1));
        com11.addPoint(new ModbusPoint("2#报警点", 17, 2, 31, 1));
        coms.add(com11);

        // 根据串口数量初始化线程池.固定size个线程.
        int size = coms.size();
        threadPoolExecutor = new ThreadPoolExecutor(size, size + 10, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("modbus-%d").build());

    }

    /**
     * 开始通信.
     */
    public void startCommunication() {
        for (ModbusCom com : coms) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        com.comminucateWithModbus();
                        try {
                            Thread.sleep(com.getMillis());
                        } catch (InterruptedException e) {
                            log.warn(e.toString());
                        }
                    }
                }
            });
        }
    }
}

