package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.services.UserService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GoalServiceTest {
    @MockBean
    private GoalRepo goalRepo;
    @Autowired
    private GoalService goalService;
    @MockBean
    private UserService userService;

    private Goal mockGoal;
    private Goal testGoal1;
    private Goal testGoal2;
    private User owner;

    @BeforeEach
    public void setUp() {
        mockGoal = new Goal("test",new Date(),new Date(),0.0,0.0, Status.COMPLETE,new User());
        mockGoal.setId(1l);

        owner = new User();
        owner.setId("ownerID");
        testGoal1 = new Goal("first goal", "this is a goal", new Date(1,1,1), 200.00);
        testGoal1.setId(1L);
        testGoal1.setOwner(owner);

        testGoal2 = new Goal("second goal", "this is another goal", new Date(1,1,1), 200.00);
        testGoal2.setId(3L);
        testGoal2.setOwner(owner);
        testGoal2.setStatus(Status.IN_PROGRESS);
    }


    @Test
    @DisplayName("Create Test - Success")
    public void create() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        Goal createdGoal = goalService.create(mockGoal);
        Assertions.assertEquals(createdGoal.getId(),mockGoal.getId());
    }

    @Test
    @DisplayName("Get By Status Test ")
    public void getByStatusTest01() {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(testGoal1);
        expectedGoals.add(testGoal2);
        BDDMockito.doReturn(owner).when(userService).getById("ownerID");
        BDDMockito.doReturn(expectedGoals).when(goalRepo).findByOwnerAndStatus(owner,  Status.IN_PROGRESS);
        List<Goal> actual = goalService.getAllByStatus("OwnerID", "In Progress");
        Assertions.assertEquals(expectedGoals, actual);
    }
}
