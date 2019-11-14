package club.yaso91.alarmserver.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModbusComTest {
    ModbusCom modbusCom;

    @Before
    public void setUp() throws Exception {
        modbusCom = new ModbusCom("COM8");

        modbusCom.addPoint(new ModbusPoint("1#报警点", 17, 2, 0, 1));
        modbusCom.addPoint(new ModbusPoint("1#报警点数值", 17, 3, 0, 2));

        modbusCom.initAndOpenCom();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void comminucateWithModbus() {
        int repeat = 3;
        try {
            while (repeat > 0) {

                modbusCom.comminucateWithModbus();
                System.out.println(modbusCom.getPoints());
                assertEquals("true", modbusCom.getPoints().get("1#报警点").getValue());
                assertEquals("3.15", modbusCom.getPoints().get("1#报警点数值").getValue());
                Thread.sleep(1000);
                repeat--;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
