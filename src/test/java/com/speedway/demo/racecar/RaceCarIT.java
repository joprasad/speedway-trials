package com.speedway.demo.racecar;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
@AutoConfigureRestDocs
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RaceCarIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getZeroRaceCars() throws Exception {
        mockMvc.perform(get("/api/v1/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(0))
                .andDo(document("Get-Zero-RaceCar",
                        responseFields(
                                fieldWithPath("status").description("Return the http status desc"),
                                fieldWithPath("status_code").description("Return the http status code"),
                                fieldWithPath("data").description("Return the race car list")
                        )));
    }

    @Test
    public void getOneRaceCar() throws Exception {
        var mvcResult = createRaceCar();
        mvcResult.andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("data[0].nickname").value("The Condor"))
                .andExpect(jsonPath("data[0].model").value("Corvette"))
                .andExpect(jsonPath("data[0].year").value(2019))
                .andExpect(jsonPath("data[0].owner").value(27))
                .andExpect(jsonPath("data[0].status").value("AVAILABLE"))
                .andExpect(jsonPath("data[0].top_speed").value(189))
                .andDo(document("Get-One-RaceCar",
                        responseFields(
                                fieldWithPath("status").description("Return the http status desc"),
                                fieldWithPath("status_code").description("Return the http status code"),
                                fieldWithPath("data").description("Return the race car list"),
                                fieldWithPath("data[0].nickname").description("Race car nickname"),
                                fieldWithPath("data[0].model").description("Race car model name"),
                                fieldWithPath("data[0].year").description("Race car year"),
                                fieldWithPath("data[0].owner").description("Race car owner id"),
                                fieldWithPath("data[0].status").description("Race car status"),
                                fieldWithPath("data[0].top_speed").description("Race car top speed")
                        )));
    }

    @Test
    public void createRaceCarTest() throws Exception {
        var mvcResult = createRaceCar();
        mvcResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Race car created successfully!"))
                .andDo(document("Post-RaceCar", requestFields(
                            fieldWithPath("nickname").description("Race car nickname"),
                            fieldWithPath("model").description("Race car model name"),
                            fieldWithPath("year").description("Race car year"),
                            fieldWithPath("owner").description("Race car owner id"),
                            fieldWithPath("status").description("Race car status"),
                            fieldWithPath("top_speed").description("Race car top speed")),
                        responseFields(
                            fieldWithPath("status").description("Return the http status desc"),
                            fieldWithPath("status_code").description("Return the http status code"),
                            fieldWithPath("data").description("Return the race car created or not message")
                )));
    }

    private ResultActions createRaceCar() throws Exception {
        var raceDto = new RaceCarDTO("The Condor", "Corvette", 2019, 27, "AVAILABLE", 189);

        return mockMvc.perform(post("/api/v1/racecar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceDto)));
    }
}
