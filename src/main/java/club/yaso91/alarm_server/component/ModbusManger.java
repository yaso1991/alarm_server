/**
 * projectName: alarm_server
 * fileName: ModbusManger.java
 * packageName: club.yaso91.alarm_server.component
 * date: 2019-10-19 18:49
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.component;

import lombok.Data;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ModbusManger
 * @packageName: club.yaso91.alarm_server.component
 * @description: 串口管理
 * @data: 2019-10-19 18:49
 **/

@Data
public class ModbusManger {
    private ArrayList<ModbusCom> coms = new ArrayList<>();
    private ThreadPoolExecutor threadPoolExecutor;

    public ModbusManger() {
        // 添加串口
        ModbusCom com11 = new ModbusCom("COM11");
        com11.addPoint(new ModbusPoint("1#报警点", 17, 2, 34, 1));
        com11.addPoint(new ModbusPoint("2#报警点", 17, 2, 31, 1));
        coms.add(com11);

        // 根据串口数量初始化线程池
        int size = coms.size();
        threadPoolExecutor = new ThreadPoolExecutor(size, size, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(size), new YasoThreadFactory("modbus"));

    }

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
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}

