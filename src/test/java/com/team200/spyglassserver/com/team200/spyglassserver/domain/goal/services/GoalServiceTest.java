package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import com.team200.spyglassserver.domain.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalServiceTest {
    @MockBean
    private GoalRepo goalRepo;
    @Autowired
    private GoalService goalService;

    private Goal mockGoal;
    private Goal TestGoal1;
    private Goal testGoal2;

    @BeforeEach
    public void setUp() {
        mockGoal = new Goal("test",new Date(),new Date(),0.0,0.0, Status.COMPLETE,new User());
        mockGoal.setId(1l);
    }


    @Test
    @DisplayName("Create Test - Success")
    public void create() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        Goal createdGoal = goalService.create(mockGoal);

    }




}
