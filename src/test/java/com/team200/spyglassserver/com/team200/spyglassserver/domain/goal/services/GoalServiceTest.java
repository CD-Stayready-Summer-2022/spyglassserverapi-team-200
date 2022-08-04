package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
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
    @MockBean
    private UserService userService;

    private Goal mockGoal;
    private Goal testGoal1;
    private Goal testGoal2;
    private User owner;

    @BeforeEach
    public void setUp() {
        mockGoal = new Goal("test",new Date(),new Date(),0.0,0.0, CompletionStatus.COMPLETE,new User());
        mockGoal.setId(1l);

        owner = new User();
        owner.setId("ownerID");
        testGoal1 = new Goal("first goal", "this is a goal", new Date(2000,1,1), 200.00);
        testGoal1.setId(1L);
        testGoal1.setOwner(owner);
        testGoal1.setCompletionStatus(CompletionStatus.IN_PROGRESS);

        testGoal2 = new Goal("second goal", "this is another goal", new Date(2000,01,01), 150.00);
        testGoal2.setId(3L);
        testGoal2.setOwner(owner);
        testGoal2.setCompletionStatus(CompletionStatus.COMPLETE);
    }


    @Test
    @DisplayName("Create Test - Success")
    public void create() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        Goal createdGoal = goalService.create(mockGoal);
        Assertions.assertEquals(createdGoal.getId(),mockGoal.getId());
    }

    @Test
    @DisplayName("Get By Status Test - success ")
    public void getByStatusTest01() {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(testGoal1);
        BDDMockito.doReturn(testGoal1).when(goalRepo).save(testGoal1);
        BDDMockito.doReturn(owner).when(userService).retrieveById("ownerID");
        BDDMockito.doReturn(Optional.of(expectedGoals)).when(goalRepo).findByOwner(owner);
        List<Goal> actualGoals = goalService.getAllByStatus("ownerID", "In Progress");
        Assertions.assertEquals(expectedGoals, actualGoals);
    }

    @Test
    @DisplayName("Get By Status Test - fail ")
    public void getByStatusTest02() {
        BDDMockito.doReturn(owner).when(userService).retrieveById("ownerID");
        BDDMockito.doThrow(new ResourceNotFoundException("User has no goals")).when(goalRepo).findByOwner(owner);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            goalService.getAllByStatus("ownerID", "In Progress");
        });
    }

    @Test
    @DisplayName("Get By Target Amount Test - success")
    public void getByTargetAmountTest01() {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(testGoal1);
        BDDMockito.doReturn(owner).when(userService).retrieveById("ownerID");;
        BDDMockito.doReturn(Optional.of(expectedGoals)).when(goalRepo).findByOwner(owner);
        List<Goal> actual = goalService.getByTargetAmount("ownerID", 195.00, 205.00);
        Assertions.assertEquals(expectedGoals, actual);
    }

    @Test
    @DisplayName("Get By Target Amount Test - fail")
    public void getByTargetAmountTest02() {
        BDDMockito.doReturn(owner).when(userService).retrieveById("ownerID");;
        BDDMockito.doThrow(new ResourceNotFoundException("User has no goals")).when(goalRepo).findByOwner(owner);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            goalService.getByTargetAmount("ownerID", 195.00, 200.00);
        });
    }
}
