package club.yaso91.alarmserver.service.component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModbusMangerTest {
    @Autowired
    ModbusManger modbusManger ;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void startCommunication() {
        modbusManger.startCommunication();
    }

    @Test
    public void init() {
        assertEquals(16, modbusManger.getComs().size());
    }
}
