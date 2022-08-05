package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.controller;

import com.team200.spyglassserver.com.team200.spyglassserver.domain.JsonConverter;
import com.team200.spyglassserver.domain.goal.controller.GoalController;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.services.GoalService;

import com.team200.spyglassserver.domain.user.dtos.UserDTO;

import com.team200.spyglassserver.domain.user.services.UserService;
import org.hamcrest.core.Is;

import com.team200.spyglassserver.com.team200.spyglassserver.domain.PrincipalDetailsArgumentResolver;
import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.security.firebase.models.FireBaseUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class GoalControllerTest {
    @MockBean
    private GoalService goalService;
    @MockBean
    private UserService userService;

    MockMvc mockMvc;

    private Goal mockGoal;
    private Goal testGoal1;
    private Goal testGoal2;
    private User owner;

    private User mockUser;
    private UserDTO mockDTO;
    private List<Goal> mockGoalList;

    @BeforeEach
    public void setUp() {
        mockUser = new User("test", "user", "daniel");
        mockUser.setId("test");
        mockDTO = new UserDTO(mockUser);
        mockGoal = new Goal("test", new Date(), new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        mockGoal.setId(1l);
        testGoal1 = new Goal("tes01", new Date(), new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        mockGoalList = new ArrayList<>();
        mockGoalList.add(mockGoal);
        mockGoalList.add(testGoal1);
        mockUser.setGoals(mockGoalList);
        owner = new User();
        owner.setId("ownerID");
        FireBaseUser fireBaseUser = new FireBaseUser();
        fireBaseUser.setEmail("test@user.com");
        fireBaseUser.setUid("xyz");
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GoalController(goalService))
                .setCustomArgumentResolvers(new PrincipalDetailsArgumentResolver(fireBaseUser))
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
    }


    @DisplayName("GET byStatus /getByStatus - success")
    public void getByStatusTest01() throws Exception {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(testGoal1);
        BDDMockito.doReturn(expectedGoals).when(goalService).getAllByStatus(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goals/getByStatus/{id}/{status}", "test","Complete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET byStatus /getByStatus/{id}/{status} - fail")
    public void getByStatusTest02() throws Exception {
        BDDMockito.doThrow(new ResourceNotFoundException("User has no goals")).when(goalService).getAllByStatus(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goals/getByStatus/{id}/{status}", "test","Complete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("GET byTargetAmount /getByTargetAmount/ - success")
    public void getByTargetAmount01() throws Exception {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(testGoal1);
        BDDMockito.doReturn(expectedGoals).when(goalService).getByTargetAmount(any(),any(),any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goals/getByTargetAmount")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "test")
                .param("start", "0")
                .param("end", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET byTargetAmount /getByTargetAmount/ - fail")
    public void getByTargetAmount02() throws Exception {
        BDDMockito.doThrow(new ResourceNotFoundException("User has no goals")).when(goalService).getByTargetAmount(any(),any(),any());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/goals/getByTargetAmount")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "test")
                .param("start", "0")
                .param("end", "5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
