package club.yaso91.alarm_server.common;

import club.yaso91.client.util.YasoUtils;
import org.junit.Before;
import org.junit.Test;

public class YasoUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void genarateBCryptPasswordEncoder() {
        System.out.println(YasoUtils.genarateBcryptPasswordEncoder("user",4));
    }
}
