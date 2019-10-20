package club.yaso91.alarm_server.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModbusComTest {
    ModbusCom modbusCom;
    @Before
    public void setUp() throws Exception {
        modbusCom = new ModbusCom("COM8");
        ModbusPoint point = new ModbusPoint("1#报警点",17,2,0,1);
        modbusCom.initAndOpenCOMS();
        modbusCom.addPoint(point);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void comminucateWithModbus() {
        while (true) {
            try {
                modbusCom.comminucateWithModbus();
                System.out.println(modbusCom.getPoints().get("1#报警点").getValue());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}
