/**
 * projectName: alarmserver
 * fileName: ModbusPoint.java
 * packageName: club.yaso91.alarmserver.component
 * date: 2019-10-19 18:58
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.component;

import lombok.Data;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: ModbusPoint
 * @packageName: club.yaso91.alarmserver.component
 * @description:
 * @data: 2019-10-19 18:58
 **/
@Data
public class ModbusPoint {

    private String name;
    private int deviceId = 1;
    private int code = 3;
    private int ref = 0;
    private int count = 0;
    private String value;
    private String lastValue;

    /**
     * @title: ModbusPoint
     * @author: Yaso
     * @date: 2019-10-20 8:02
     * @description: modbus设备里需要采集的数据点.
     * @param: name
     * @param: deviceId
     * @param: code
     * @param: ref
     * @param: count
     * @return:
     * @throws:
     **/
    public ModbusPoint(String name, int deviceId, int code, int ref, int count) {
        this.name = name;
        this.deviceId = deviceId;
        this.code = code;
        this.ref = ref;
        this.count = count;
    }

    /**
     * 与setValue不同,update在更新当前值前,先将当前值赋值给lastValue.
     *
     * @param value
     */
    public void updateCurrentValue(String value) {
        if (this.value != value) {
            this.setLastValue(this.value);
            this.value = value;
        }
    }
}
