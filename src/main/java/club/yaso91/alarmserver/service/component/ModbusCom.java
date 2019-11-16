/**
 * projectName: alarmserver
 * fileName: CurrentAlarmStateReader.java
 * packageName: club.yaso91.alarmserver.service.component
 * date: 2019-10-19 15:53
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service.component;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusSerialTransaction;
import com.ghgande.j2mod.modbus.msg.*;
import com.ghgande.j2mod.modbus.net.AbstractSerialConnection;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.util.ModbusUtil;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: CurrentAlarmStateReader
 * @packageName: club.yaso91.alarmserver.service.component
 * @description: 使用modbus实时读取报警点数据.
 * @data: 2019-10-19 15:53
 **/
@Data
@Slf4j
public class ModbusCom {
    private static final String SERIAL_ENCODING_RTU = Modbus.SERIAL_ENCODING_RTU;
    private static final int STOP_BITS = 1;
    private static final String PARITY = "None";
    private static final int DATABITS = 8;
    private static final int RATE = 9600;
    private static final int BYTE_COUNT_OF_A_DOUBLE_WORD = 4;
    private final SerialParameters params = new SerialParameters();
    private AbstractSerialConnection com;
    private int millis = 500;
    private String portName;
    private HashMap<String, ModbusPoint> points = new HashMap<>();
    private boolean connected = false;


    public ModbusCom(String portName) {
        this.portName = portName;

        params.setPortName(this.portName);
        params.setBaudRate(RATE);
        params.setDatabits(DATABITS);
        params.setParity(PARITY);
        params.setStopbits(STOP_BITS);
        params.setEncoding(SERIAL_ENCODING_RTU);
        params.setEcho(false);
    }

    /**
     * 初始化并打开串口.
     */
    private void initAndOpenCom() {
        if (connected) {
            return;
        }
        com = new SerialConnection(params);
        try {
            com.open();
            log.info(this.portName + " open success.");
            connected = true;
        } catch (IOException e) {
            log.warn(e.toString());
            connected = false;
        }
    }

    /**
     * 添加采集点
     *
     * @param point
     */
    public void addPoint(ModbusPoint point) {
        points.put(point.getName(), point);
    }

    /**
     * 通过串口的modbus协议采集数据.
     */
    public void comminucateWithModbus() {
        // 如果未连接,重新连接.
        if (!connected) {
            log.info(this.portName + " reconnecting...");
            initAndOpenCom();
            return;
        }

        // 读取数据
        try {
            for (String key : points.keySet()) {
                ModbusPoint point = points.get(key);
                ModbusRequest req = null;
                int code = point.getCode();
                int ref = point.getRef();
                int count = point.getCount();
                if (2 == code) {
                    req = new ReadInputDiscretesRequest(ref, count);
                } else if (3 == code) {
                    req = new ReadMultipleRegistersRequest(ref, count);
                } else {

                }

                int deviceId = point.getDeviceId();
                req.setUnitID(deviceId);
                req.setUnitID(deviceId);
                req.setHeadless();

                ModbusSerialTransaction trans = new ModbusSerialTransaction(com);
                trans.setRequest(req);
                trans.execute();

                if (2 == code) {
                    ReadInputDiscretesResponse res = (ReadInputDiscretesResponse) trans.getResponse();
                    point.updateCurrentValue(String.valueOf(res.getDiscreteStatus(0)));
                } else if (3 == code) {
                    ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();

                    byte[] message = res.getMessage();
                    byte[] data = new byte[BYTE_COUNT_OF_A_DOUBLE_WORD];
                    for (int i = 0; i < BYTE_COUNT_OF_A_DOUBLE_WORD; i++) {
                        data[i] = message[i + 1];
                    }
                    point.updateCurrentValue(String.valueOf(ModbusUtil.registersToFloat(data)));
                } else {

                }
            }
        } catch (ModbusException e) {
            log.warn(e.toString());
        }
    }
}
