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
import com.ghgande.j2mod.modbus.msg.ReadInputDiscretesRequest;
import com.ghgande.j2mod.modbus.msg.ReadInputDiscretesResponse;
import com.ghgande.j2mod.modbus.net.AbstractSerialConnection;
import com.ghgande.j2mod.modbus.net.SerialConnection;
import com.ghgande.j2mod.modbus.util.ModbusUtil;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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
    private String name;
    AbstractSerialConnection com;
    private HashMap<String,ModbusPoint> points = new HashMap<>();


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
        points.put(point.getName(),point);
    }

    public void comminucateWithModbus() {
        for(String key:points.keySet()) {
            ReadInputDiscretesRequest req = new ReadInputDiscretesRequest(points.get(key).getRef(), points.get(key).getCount());
            req.setUnitID(points.get(key).getDeviceId());
            req.setHeadless();
            ModbusSerialTransaction trans = new ModbusSerialTransaction(com);
            trans.setRequest(req);

            try {
                trans.execute();
            } catch (ModbusException e) {
                e.printStackTrace();
            }
            ReadInputDiscretesResponse res = (ReadInputDiscretesResponse) trans.getResponse();
            points.get(key).setValue(String.valueOf(res.getDiscreteStatus(0)));

        }


    }

}
