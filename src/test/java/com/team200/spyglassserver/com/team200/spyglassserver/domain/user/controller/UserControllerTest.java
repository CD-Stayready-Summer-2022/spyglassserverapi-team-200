package com.team200.spyglassserver.com.team200.spyglassserver.domain.user.controller;

import com.team200.spyglassserver.com.team200.spyglassserver.domain.JsonConverter;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import com.team200.spyglassserver.security.firebase.services.FirebaseUserMgrService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private FirebaseUserMgrService firebaseUserMgrService;

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private UserCreateRequest mockDetail;
    private User mockUser;
    private String expectedId;
    private UserDTO mockDTO;

    @BeforeEach
    public void setup(){
        expectedId = "test237";
        mockDetail = new UserCreateRequest("first", "last", "lastlast@gmail.com", "password");
        mockUser = new User("first", "last", "lastlast@gmail.com");
        mockUser.setId("test237");
        mockDTO = new UserDTO(mockUser);


    }

    @Test
    @DisplayName("User Create - /api/v1/user/create : success")
    public void createItemRequest()throws Exception{
        BDDMockito.doReturn(mockDTO).when(userService).create(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.asJsonString(mockDetail)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1l)));

    }


}
