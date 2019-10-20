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
        ModbusCom modbusCom = new ModbusCom("COM8");

        modbusCom.addPoint(new ModbusPoint("1#报警点", 17, 2, 0, 1));
        modbusCom.addPoint(new ModbusPoint("1#报警点数值", 17, 3, 0, 2));

        coms.add(modbusCom);

        for (ModbusCom com : coms) {
            com.initAndOpenCOMS();
        }
    }


    public void startCommunication() {
        for (ModbusCom com : coms) {
            com.comminucateWithModbus();
        }
    }
}
