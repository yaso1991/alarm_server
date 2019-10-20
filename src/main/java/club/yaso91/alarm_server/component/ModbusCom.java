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
public class ModbusCom implements Runnable {
    private String serialEncodingRtu = Modbus.SERIAL_ENCODING_RTU;
    private int stopbits = 1;
    private String none = "None";
    private int DATABITS = 8;
    private int rate = 9600;
    private AbstractSerialConnection com;
    private String portName;
    private HashMap<String, ModbusPoint> points = new HashMap<>();
    private int millis = 500;


    public ModbusCom(String portName) {
        this.portName = portName;
    }


    public void initAndOpenCOMS() {

        SerialParameters params = new SerialParameters();
        params.setPortName(portName);
        params.setBaudRate(rate);
        params.setDatabits(DATABITS);
        params.setParity(none);
        params.setStopbits(stopbits);
        params.setEncoding(serialEncodingRtu);
        params.setEcho(false);
        com = new SerialConnection(params);
        try {
            com.open();
            System.out.println(this.portName + " open.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPoint(ModbusPoint point) {
        points.put(point.getName(), point);
    }

    public void comminucateWithModbus() {
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
                    point.setValue(String.valueOf(res.getDiscreteStatus(0)));
                } else if (3 == code) {
                    ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();

                    byte[] message = res.getMessage();
                    byte[] data = new byte[4];
                    for (int i = 0; i < 4; i++) {
                        data[i] = message[i + stopbits];
                    }
                    point.setValue(String.valueOf(ModbusUtil.registersToFloat(data)));
                } else {

                }

            }
        } catch (ModbusException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
            comminucateWithModbus();
            try {
                Thread.sleep(millis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
