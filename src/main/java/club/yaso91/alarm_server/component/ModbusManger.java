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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public ModbusManger() {
        ModbusCom com41 = new ModbusCom("COM41");
        com41.addPoint(new ModbusPoint("1#报警点", 17, 2, 0, 1));
        com41.addPoint(new ModbusPoint("2#报警点", 17, 2, 1, 1));
        coms.add(com41);
        ModbusCom com42 = new ModbusCom("COM42");
        com42.addPoint(new ModbusPoint("3#报警点", 17, 2, 0, 1));
        com42.addPoint(new ModbusPoint("4#报警点", 17, 2, 1, 1));
        coms.add(com42);

        for (ModbusCom com : coms) {
            com.initAndOpenCOMS();
        }
    }

    public void startCommunication() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (ModbusCom com : coms) {
            executorService.execute(new Runnable() {
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
