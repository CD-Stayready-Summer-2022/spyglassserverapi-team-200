package com.team200.spyglassserver.com.team200.spyglassserver.domain.user.controller;

import com.team200.spyglassserver.com.team200.spyglassserver.domain.JsonConverter;
import com.team200.spyglassserver.com.team200.spyglassserver.domain.PrincipalDetailsArgumentResolver;
import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.goal.controller.GoalController;
import com.team200.spyglassserver.domain.user.controller.UserController;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import com.team200.spyglassserver.security.firebase.models.FireBaseUser;
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

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static com.team200.spyglassserver.com.team200.spyglassserver.domain.JsonConverter.asJsonString;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
        mockUser.setId("1");
        mockDTO = new UserDTO(mockUser);
        FireBaseUser fireBaseUser = new FireBaseUser();
        fireBaseUser.setEmail("lastlast@gmail.com");
        fireBaseUser.setUid("test237");
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserController(userService))
                .setCustomArgumentResolvers(new PrincipalDetailsArgumentResolver(fireBaseUser))
                .build();


    }

    @Test
    @DisplayName("User Create - /api/v1/user/create : success")
    public void createItemRequest()throws Exception{
        BDDMockito.doReturn(mockDTO).when(userService).create(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockDetail)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is("1")));

    }

    @Test
    @DisplayName("User Create -/api/v1/user/create : fail")
    public void createItemRequestFailed()throws Exception {
        BDDMockito.doThrow(new ResourceCreationException("exists")).when(userService).create(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockDetail)))
        .andExpect(status().isConflict());



    }
    @Test
    @DisplayName("User get By Id  - /api/v1/user/{id} : success")
    public void getByIdSuccess()throws Exception {
        BDDMockito.doReturn(mockDTO).when(userService).getById("1");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",Is.is("1")));
    }

    @Test
    @DisplayName("User get by Id - failed" )
    public void  getBYIdFail()throws Exception{
        BDDMockito.doThrow(new ResourceNotFoundException("not found")).when(userService).getById("1");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{id}","1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("User get by email - success")
    public void getByEmailSuccess()throws Exception{
        BDDMockito.doReturn(mockDTO).when(userService).getByEmail("lastlast@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/lookup?email={email}","lastlast@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",Is.is("1")));


    }
    @Test
    @DisplayName("User get by email- fail")
    public void getByEmailFail()throws Exception{
        BDDMockito.doThrow(new ResourceNotFoundException("not found")).when(userService).getByEmail("lastlast@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/lookup?email={email}","lastlast@gmail.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("User update test - sucess")
    public void updateUserSucess() throws Exception{
        User expectedUserUpdate = new User();
        expectedUserUpdate.setId("1");
        expectedUserUpdate.setFirstName("firstNameAfterUpdate");
        UserDTO expectedUserDTOUpdate = new UserDTO(expectedUserUpdate);


        BDDMockito.doReturn(expectedUserDTOUpdate).when(userService).update(any(),any());
        mockMvc.perform(put("/api/v1/user/{id}","1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is("1")))
                .andExpect( jsonPath("$.firstName", Is.is("firstNameAfterUpdate")));


    }



}
