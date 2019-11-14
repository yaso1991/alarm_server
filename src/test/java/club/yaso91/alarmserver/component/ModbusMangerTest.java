package club.yaso91.alarmserver.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModbusMangerTest {
    ModbusManger modbusManger = new ModbusManger();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void startCommunication() {
        modbusManger.startCommunication();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}