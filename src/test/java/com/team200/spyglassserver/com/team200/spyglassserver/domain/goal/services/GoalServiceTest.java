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
    @MockBean
    private UserService userService;
    @Autowired
    private GoalService goalService;

    private Goal mockGoal;
    private Goal savedGoal1;
    private Goal savedGoal2;
    private Goal savedGoal3;
    private User owner;
    @BeforeEach
    public void setUp() {
        owner = new User();
        owner.setId("ownerID");
        mockGoal = new Goal("VacationGoal", "I want to go on vacation", new Date(1,1,1), 200.00);
        savedGoal1 = new Goal("VacationGoal", "I want to go on vacation", new Date(1,1,1), 200.00);
        savedGoal1.setId(1L);
        savedGoal1.setOwner(owner);
        savedGoal2 = new Goal("Changed Goal", "I changed my goal", new Date(1,1,1), 200.00);
        savedGoal2.setId(1L);
        savedGoal2.setOwner(owner);
        savedGoal2.setStatus(Status.IN_PROGRESS);
        savedGoal3 = new Goal("VacationGoal", "I want to go on vacation", new Date(1,1,1), 200.00);
        savedGoal3.setId(3L);
        savedGoal3.setOwner(owner);
        savedGoal3.setStatus(Status.IN_PROGRESS);
    }

    @Test
    @DisplayName("Get By Status Test ")
    public void getByStatusTest01() {
        List expectedGoals = new ArrayList<>();
        expectedGoals.add(savedGoal1);
        expectedGoals.add(savedGoal3);
        BDDMockito.doReturn(owner).when(userService).getById("ownerID");
        BDDMockito.doReturn(expectedGoals).when(goalRepo).findByOwnerAndStatus(owner,  Status.IN_PROGRESS);
        List<Goal> actual = goalService.getAllByStatus("OwnerID", "In Progress");

        Assertions.assertEquals(expectedGoals, actual);
    }

}
