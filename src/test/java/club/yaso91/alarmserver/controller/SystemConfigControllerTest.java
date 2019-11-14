package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.entity.SystemConfig;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SystemConfigControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private SystemConfigController systemConfigController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(systemConfigController).build();
    }

    @Test
    public void loadSystemConfig() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/systemInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("home"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true, !result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void updateSystemConfig() throws Exception {
        SystemConfig config = new SystemConfig();
        config.setId(1);
        config.setMonitorPushDelay(21);
        config.setManagerPushDelay(61);
        config.setMasterPushDelay(41);
        config.setSumPushTime( new Time(System.currentTimeMillis()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/systemInfo/updateSystemConfig")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(config));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("updateSystemConfig"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true, !result.getResponse().getContentAsString().isEmpty());
    }
}
