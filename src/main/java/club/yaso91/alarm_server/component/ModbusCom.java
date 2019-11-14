/**
 * projectName: alarm_server
 * fileName: CurrentAlarmStateReader.java
 * packageName: club.yaso91.alarm_server.component
 * date: 2019-10-19 15:53
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.component;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.io.ModbusSerialTransaction;
import com.ghgande.j2mod.modbus.msg.*;
import com.ghgande.j2mod.modbus.net.AbstractSerialConnection;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.util.ModbusUtil;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: CurrentAlarmStateReader
 * @packageName: club.yaso91.alarm_server.component
 * @description: 使用modbus实时读取报警点数据.
 * @data: 2019-10-19 15:53
 **/
@Data
public class ModbusCom {
    private static final String SERIAL_ENCODING_RTU = Modbus.SERIAL_ENCODING_RTU;
    private static final int STOP_BITS = 1;
    private static final String PARITY = "None";
    private static final int DATABITS = 8;
    private static final int RATE = 9600;
    private AbstractSerialConnection com;
    private String portName;
    private HashMap<String, ModbusPoint> points = new HashMap<>();
    private int millis = 500;
    private boolean connected = false;
    private final SerialParameters params = new SerialParameters();


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


    public void initAndOpenCom() {
        if (connected) {
            return;
        }
        com = new SerialConnection(params);
        try {
            com.open();
            System.out.println(this.portName + " open success.");
            connected = true;
        } catch (IOException e) {
            System.out.println(this.portName + " open failed.");
            connected = false;
        }
    }

    public void addPoint(ModbusPoint point) {
        points.put(point.getName(), point);
    }

    public void comminucateWithModbus() {
        if(!connected) {
            System.out.println(this.portName  + " reconnecting...");
            initAndOpenCom();
            return;
        }
        try {
            //TODO 这里需要将keyset 改为entryset 提高性能.
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
                    point.setValue(String.valueOf(res.getDiscreteStatus(0)));
                } else if (3 == code) {
                    ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();

                    byte[] message = res.getMessage();
                    byte[] data = new byte[4];
                    for (int i = 0; i < 4; i++) {
                        data[i] = message[i + 1];
                    }
                    point.setValue(String.valueOf(ModbusUtil.registersToFloat(data)));
                } else {

                }
            }
        } catch (ModbusException e) {
            e.printStackTrace();
        }
    }
}
