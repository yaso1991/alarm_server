package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.AlarmInfo;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AlarmInfoControllerTest {
    @Autowired
    private AlarmInfoController alarmInfoController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(alarmInfoController).build();
    }


    @Test
    public void getAlarmInfos() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/alarmInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        ResultActions perform = mockMvc.perform(requestBuilder);

        MvcResult result = perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("getAlarmInfos"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void addAlarmInfo() throws Exception {

        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setCardReaderId(4);
        alarmInfo.setComPort("com4");
        alarmInfo.setName("83号报警点");
        alarmInfo.setType("测厚系统");
        alarmInfo.setState("维护");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/alarmInfo/addAlarmInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(alarmInfo));
        ResultActions perform = mockMvc.perform(requestBuilder);

        MvcResult result = perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("addAlarmInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }

    @Test
    public void loadCardReadersData() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/alarmInfo/loadCardReadersData")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        ResultActions perform = mockMvc.perform(requestBuilder);

        MvcResult result = perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("loadCardReadersData"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true,!result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void fixAlarmInfo() throws Exception {
        AlarmInfo alarmInfo = new AlarmInfo();
        alarmInfo.setId(32);
        alarmInfo.setCardReaderId(4);
        alarmInfo.setComPort("com4");
        alarmInfo.setName("83号报警点");
        alarmInfo.setType("测厚系统");
        alarmInfo.setState("维护");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/alarmInfo/fixAlarmInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(alarmInfo));
        ResultActions perform = mockMvc.perform(requestBuilder);

        MvcResult result = perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("fixAlarmInfo"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }

    @Test
    public void deleteAlarmInfos() throws Exception {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(12);
        ids.add(14);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/alarmInfo/deleteAlarmInfos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(ids));
        ResultActions perform = mockMvc.perform(requestBuilder);

        MvcResult result = perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteAlarmInfos"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }
}
