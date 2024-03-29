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
import com.google.common.base.Objects;
import lombok.Data;

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
public class ModbusCom {
    /**
     * 通信状态
     */
    public static final String COMM_STATE_SUCCESS = "通信正常";
    public static final String COMM_STATE_FAILURE = "通信失败";
    public static final String COMM_STATE_CLOSE = "通信关闭";
    public static final String COMM_STATE_OPEN = "通信打开";

    /**
     * 串口参数
     */
    private static final String SERIAL_ENCODING_RTU = Modbus.SERIAL_ENCODING_RTU;
    private static final int STOP_BITS = 1;
    private static final String PARITY = "None";
    private static final int DATABITS = 8;
    private static final int RATE = 9600;
    private static final int BYTE_COUNT_OF_A_DOUBLE_WORD = 4;
    private static final int TIMEOUT = 500;
    private final SerialParameters params = new SerialParameters();
    private static final int COMM_FAILURE_COUNT_OF_REBOOT = 10;
    private int commFailTimes = 0;
    private AbstractSerialConnection com;
    private int millis = 500;
    private String portName;
    private HashMap<String, ModbusPoint> points = new HashMap<>();
    private String comState = COMM_STATE_CLOSE;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModbusCom modbusCom = (ModbusCom) o;
        return Objects.equal(portName, modbusCom.portName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(portName);
    }

    /**
     * 初始化并打开串口.
     *
     * @return
     */
    private void initAndOpenCom() throws IOException {
        if (com != null) {
            com.close();
            com = null;
        }
        com = new SerialConnection(params);
        com.setTimeout(TIMEOUT);
        com.open();
        comState = COMM_STATE_OPEN;
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
    public void comminucateWithModbus() throws IOException {
        // 如果未连接,重新连接.
        if (com == null || !com.isOpen()) {
            initAndOpenCom();
            return;
        }


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
            try {
                trans.execute();
                if (2 == code) {
                    ReadInputDiscretesResponse res =
                            (ReadInputDiscretesResponse) trans.getResponse();
                    point.updateCurrentValue(String.valueOf(res.getDiscreteStatus(0)));
                } else if (3 == code) {
                    ReadMultipleRegistersResponse res =
                            (ReadMultipleRegistersResponse) trans.getResponse();

                    byte[] message = res.getMessage();
                    byte[] data = new byte[BYTE_COUNT_OF_A_DOUBLE_WORD];
                    for (int i = 0; i < BYTE_COUNT_OF_A_DOUBLE_WORD; i++) {
                        data[i] = message[i + 1];
                    }
                    point.updateCurrentValue(String.valueOf(ModbusUtil.registersToFloat(data)));
                } else {

                }
                comState = COMM_STATE_SUCCESS;
            } catch (ModbusException e) {
                comState = COMM_STATE_FAILURE;

                // 连续读取失败到达预订次数,重启串口
                commFailTimes++;
                if(commFailTimes > COMM_FAILURE_COUNT_OF_REBOOT) {
                    com.close();
                    com = null;
                    commFailTimes = 0;
                    comState = COMM_STATE_CLOSE;
                }
            }
        }

    }

}
