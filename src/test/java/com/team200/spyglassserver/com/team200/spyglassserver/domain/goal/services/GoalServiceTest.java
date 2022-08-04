package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.enums.CompletionStatus;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalServiceTest {
    @MockBean
    private GoalRepo goalRepo;



    @Autowired
    private GoalService goalService;

    private Goal mockGoal;
    private Goal testGoal1;
    private Goal testGoal2;
    private User mockUser;
    private List<Goal> mockGoalList;

    @BeforeEach
    public void setUp() {
        mockUser = new User("test", "user", "daniel", mockGoalList);
        mockUser.setId("test");
        mockGoal = new Goal("test", new Date(), new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        mockGoal.setId(1l);
        testGoal1 = new Goal("tes01", new Date(), new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        mockGoalList = new ArrayList<>();
        mockGoalList.add(mockGoal);
        mockGoalList.add(testGoal1);


    }


    @Test
    @DisplayName("Create Test - Success")
    public void create01() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        Goal createdGoal = goalService.create(mockGoal);
        Assertions.assertEquals(createdGoal.getId(), mockGoal.getId());
    }

    @Test
    @DisplayName("Create Test - Fail ")
    public void create02() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        BDDMockito.doReturn(Optional.of(mockGoal)).when(goalRepo).findByTitle("test");

        Assertions.assertThrows(ResourceCreationException.class, () -> {
            Goal newGoal = goalService.create(mockGoal);
        });

    }

    @Test
    @DisplayName("Get All test - success")
    public void getAllTest01() {
        BDDMockito.doReturn(mockGoalList).when(goalRepo).findByOwner(mockUser);
        List<Goal> goals = goalService.getAll(mockUser.getId());
        Assertions.assertEquals(goals, mockGoalList);
    }


}
