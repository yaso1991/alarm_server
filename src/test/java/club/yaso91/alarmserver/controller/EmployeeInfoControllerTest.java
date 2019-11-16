package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.domain.Employee;
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

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeInfoControllerTest {
    @Autowired
    EmployeeInfoController employeeInfoController;
    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeInfoController).build();
    }

    @Test
    public void home() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employeeInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("home"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true,!result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void addEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setName("员工C");
        employee.setWorkId("1234567890");
        employee.setPosition("员工");
        employee.setEmail("1721662545@qq.com");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employeeInfo/addEmployee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(employee));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("addEmployee"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals(true,!result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void fixEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(23);
        employee.setName("员工E");
        employee.setWorkId("1234567890");
        employee.setPosition("员工");
        employee.setEmail("1721662545@qq.com");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employeeInfo/fixEmployee")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(employee));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("fixEmployee"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }

    @Test
    public void deleteEmployees() throws Exception {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(22);
        ids.add(23);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employeeInfo/deleteEmployees")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(ids));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("deleteEmployees"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        assertEquals("true",result.getResponse().getContentAsString());
    }
}
