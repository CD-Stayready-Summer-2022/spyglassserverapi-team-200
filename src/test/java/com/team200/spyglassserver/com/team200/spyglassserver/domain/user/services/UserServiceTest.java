package com.team200.spyglassserver.com.team200.spyglassserver.domain.user.services;

import com.team200.spyglassserver.domain.user.dtos.UserCreateRequest;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import com.team200.spyglassserver.security.firebase.services.FirebaseUserMgrService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    }
}
