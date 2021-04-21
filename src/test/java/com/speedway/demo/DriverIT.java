package com.speedway.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.speedway.demo.model.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DriverIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addDriverTest() throws Exception {
        Driver driver = new Driver("Zack", "R", 30, "John", "", 1, 0);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getDriverTest() throws Exception {
        mockMvc.perform(get("/driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }


    @Test
    public void postAndGetDriverTest() throws Exception {
        Driver driver = new Driver("Raj", "R", 33, "John", "", 1, 0);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(1))
                .andExpect(jsonPath("[0].firstName").value("Raj"));
    }

    @Test
    public void addManyDriversTest() throws Exception {
        Driver driver = new Driver("Zach", "R", 30, "John", "", 4, 0);
        Driver driver2 = new Driver("Raj", "R", 33, "John", "", 1, 1);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isCreated());
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver2)))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(2))
                .andExpect(jsonPath("[0].firstName").value("Zach"))
        .andExpect(jsonPath("[1].firstName").value("Raj"));
    }

}
