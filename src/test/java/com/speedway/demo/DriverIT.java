package com.speedway.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedway.demo.model.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@AutoConfigureMockMvc
@SpringBootTest
public class DriverIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addDriverTest() throws Exception{
        Driver driver=new Driver("Zack","R",30,"John","",1,0);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isCreated());
    }
}
