package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.controller;

import com.google.api.client.json.Json;
import com.team200.spyglassserver.com.team200.spyglassserver.domain.JsonConverter;
import com.team200.spyglassserver.domain.goal.controller.GoalController;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private GoalService goalService;

    private Goal mockGoal;

    @BeforeEach
    public void setUp() {
        mockGoal = new Goal("Test","test",new Date(),0.0);
        mockGoal.setId(1l);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GoalController(goalService))
                .build();
    }

    @Test
    @DisplayName("Create test - Success")
    public void createTest01() throws Exception {
       BDDMockito.doReturn(mockGoal).when(goalService).create(any());
     mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/goals/create")
                      .contentType(MediaType.APPLICATION_JSON)
                       .content(JsonConverter.asJsonString(mockGoal)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1l)));

    }
}
