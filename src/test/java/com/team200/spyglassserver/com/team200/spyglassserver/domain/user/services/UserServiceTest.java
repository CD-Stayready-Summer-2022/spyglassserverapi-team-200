package com.team200.spyglassserver.com.team200.spyglassserver.domain.user.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import com.team200.spyglassserver.security.firebase.services.FirebaseUserMgrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private FirebaseUserMgrService firebaseUserMgrService;

    @Autowired
    private UserService userService;
    private UserCreateRequest mockDetail;
    private User mockUser;
    private String expectedId;

    @BeforeEach
    public void setup(){
        expectedId = "test237";
        mockDetail = new UserCreateRequest("first", "last", "lastlast@gmail.com", "password");
        mockUser = new User("first", "last", "lastlast@gmail.com");
        mockUser.setId("test237");
    }
    @Test
    public void userCreationTest01(){
        BDDMockito.doReturn(Optional.empty()).when(userRepo).findByEmail(any());
        BDDMockito.doReturn(expectedId).when(firebaseUserMgrService).createFireBaseUser(any());
        BDDMockito.doReturn(mockUser).when(userRepo).save(any());
        UserDTO user =userService.create(mockDetail);
        Assertions.assertEquals(expectedId, user.getId());
    }
    @Test
    public void createUserProfileTest02(){
        BDDMockito.doReturn(Optional.of(mockUser)).when((userRepo).findByEmail(any()));
        Assertions.assertThrows(ResourceCreationException.class, ()->{
            userService.create(mockDetail);
        });
    }
    @Test
    public void getByIdTest01(){
        BDDMockito.doReturn(Optional.of(mockUser)).when(userRepo).findById(any());
        UserDTO user = userService.getById("test237");
        Assertions.assertEquals(expectedId,user.getId());
    }
}
