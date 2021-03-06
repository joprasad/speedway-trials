package com.speedway.demo.race;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedway.demo.racecar.RaceCarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
@AutoConfigureRestDocs
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RaceIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getZeroRaces() throws Exception {
        mockMvc.perform(get("/api/v1/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(0))
                .andDo(document("Get-Zero-Races",
                        responseFields(
                                fieldWithPath("status").description("Return the http status desc"),
                                fieldWithPath("status_code").description("Return the http status code"),
                                fieldWithPath("data").description("Return the race list")
                        )));
    }

    @Test
    public void getOneRace() throws Exception {
        var mvcResult = createRace();
        mvcResult.andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/races"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.status_code").value(200))
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("data[0].name").value("Grand Prix III"))
                .andExpect(jsonPath("data[0].category").value("stock car"))
                .andExpect(jsonPath("data[0].date").value("2020-06-03"))
                .andExpect(jsonPath("data[0].bestTime").value("03:36:78"))
                .andExpect(jsonPath("data[0].winner").value(12))
                .andExpect(jsonPath("data[0].participants[0]").value(23))
                .andExpect(jsonPath("data[0].participants[1]").value(45))
                .andExpect(jsonPath("data[0].participants[2]").value(12))
                .andDo(document("Get-One-Race",
                        responseFields(

                                fieldWithPath("status").description("Return the http status desc"),
                                fieldWithPath("status_code").description("Return the http status code"),
                                fieldWithPath("data").description("Return the race list"),
//                                fieldWithPath("data[0].id").description("Race id"),
                                fieldWithPath("data[0].name").description("Race name"),
                                fieldWithPath("data[0].category").description("Race category"),
                                fieldWithPath("data[0].date").description("Race date"),
                                fieldWithPath("data[0].bestTime").description("Race best time"),
                                fieldWithPath("data[0].winner").description("Race winner"),
                                fieldWithPath("data[0].participants").description("Race participants")
                        )));
    }

    @Test
    public void createRaceTest() throws Exception {
        var mvcResult = createRace();
        mvcResult.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("Created"))
                .andExpect(jsonPath("$.status_code").value(201))
                .andExpect(jsonPath("$.data").value("Race created successfully!"))
                .andDo(document("Post-Race", requestFields(
                        fieldWithPath("name").description("Race name"),
                        fieldWithPath("category").description("Race category"),
                        fieldWithPath("date").description("Race date"),
                        fieldWithPath("bestTime").description("Race best time"),
                        fieldWithPath("winner").description("Race winner"),
                        fieldWithPath("participants").description("Race participants")),
                        responseFields(
                                fieldWithPath("status").description("Return the http status desc"),
                                fieldWithPath("status_code").description("Return the http status code"),
                                fieldWithPath("data").description("Return the race created or not message")
                        )));
    }


    private ResultActions createRace() throws Exception {
        var raceDto = new RaceDTO("Grand Prix III", "stock car", LocalDate.of(2020, 6, 3),
                "03:36:78", 12L, new ArrayList<Integer>(Arrays.asList(23, 45, 12)));

        return mockMvc.perform(post("/api/v1/race")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(raceDto)));
    }
}
