package club.yaso91.alarm_server.controller;

import org.junit.After;
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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SumInfoControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private SumInfoController sumInfoController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(sumInfoController).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSumInfos() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sumInfo/getSumInfos")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("beginTime","2019-9-25 09:12:43.053")
                .param("endTime","2019-9-27 09:12:43.053");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("getSumInfos"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true, !result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void exportSumInfos() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sumInfo/exportSumInfos")
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.handler().methodName("exportSumInfos"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true, !result.getResponse().getContentAsString().isEmpty());
    }
}
