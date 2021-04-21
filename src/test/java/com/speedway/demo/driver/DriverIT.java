package com.speedway.demo.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedway.demo.model.Driver;
import com.speedway.demo.racecar.RaceCarDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@Transactional
public class DriverIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    Driver driver;
    RaceCarDTO raceCarDTO;

    @BeforeEach
    public void setup() {
        raceCarDTO = new RaceCarDTO();
        driver = new Driver("Zack", "R", 30, "John", List.of(raceCarDTO), 1, 0);
    }

    @Test
    public void addDriverTest() throws Exception {
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Driver created successfully!"))
                .andDo(print())
                .andDo(document("Post-Driver"));
    }

    @Test
    public void getDriverTest() throws Exception {
        mockMvc.perform(get("/driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(0));
    }


    @Test
    public void postAndGetDriverTest() throws Exception {
        Driver driver = new Driver("Raj", "R", 33, "John", List.of(raceCarDTO), 1, 0);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Driver created successfully!"));
        mockMvc.perform(get("/driver"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data.[0]firstName").value("Raj"))
                .andDo(print())
                .andDo(document("Get-Driver"));
    }

    @Test
    public void addManyDriversTest() throws Exception {
        Driver driver2 = new Driver("Raj", "R", 33, "John", List.of(raceCarDTO), 1, 1);
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Driver created successfully!"));
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver2)))
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Driver created successfully!"));
        mockMvc.perform(get("/driver"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data.[0].firstName").value("Zack"))
                .andExpect(jsonPath("$.data.[1].firstName").value("Raj"));
    }

}
