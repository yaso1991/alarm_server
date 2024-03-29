/**
 * projectName: alarmserver
 * fileName: ModbusManger.java
 * packageName: club.yaso91.alarmserver.service.component
 * date: 2019-10-19 18:49
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service.component;

import club.yaso91.alarmserver.domain.ModbusPointInfo;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 串口管理类.
 * @author: Yaso
 * @date: 2019-11-22 20:30
 */
@Data
@Component
public class ModbusManger {
    private ArrayList<ModbusCom> coms = new ArrayList<>();
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 根据数据库点位信息,添加点位.
     *
     * @param modbusPointInfos
     */
    public void addPoints(ArrayList<ModbusPointInfo> modbusPointInfos) {
        for (ModbusPointInfo modbusPointInfo : modbusPointInfos) {
            ModbusCom newCom = new ModbusCom(modbusPointInfo.getComInfo().getName());
            if (!coms.contains(newCom)) {
                this.coms.add(newCom);
            }
            ModbusPoint point = new ModbusPoint(modbusPointInfo.getName(),
                    modbusPointInfo.getDeviceId(),
                    modbusPointInfo.getCode(), modbusPointInfo.getRef(),
                    modbusPointInfo.getCount());
            coms.get(coms.indexOf(newCom)).addPoint(point);
        }
    }

    /**
     * 开始通信.
     */
    public void startCommunication() {
        // 根据串口数量初始化线程池.固定size个线程.
        int size = coms.size();
        threadPoolExecutor = new ThreadPoolExecutor(size, size + 10, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("modbus-%d"
        ).build());
        for (ModbusCom com : coms) {
            threadPoolExecutor.submit(() -> {
                while (true) {
                    com.comminucateWithModbus();
                    Thread.sleep(com.getMillis());
                }
            });
        }
    }

}

