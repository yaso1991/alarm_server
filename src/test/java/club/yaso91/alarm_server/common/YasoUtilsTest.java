package club.yaso91.alarm_server.common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class YasoUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void genarateBCryptPasswordEncoder() {
        System.out.println(YasoUtils.genarateBCryptPasswordEncoder("123",4));
    }
}
