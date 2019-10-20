/**
 * projectName: alarm_server
 * fileName: CurrentAlarmStateReader.java
 * packageName: club.yaso91.alarm_server.component
 * date: 2019-10-19 15:53
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.component;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
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
    AbstractSerialConnection com;
    private String name;
    private HashMap<String, ModbusPoint> points = new HashMap<>();


    public ModbusCom(String name) {
        this.name = name;
    }


    public void initAndOpenCOMS() {

        SerialParameters params = new SerialParameters();
        params.setPortName(name);
        params.setBaudRate(9600);
        params.setDatabits(8);
        params.setParity("None");
        params.setStopbits(1);
        params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
        params.setEcho(false);
        com = new SerialConnection(params);
        try {
            com.open();
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
