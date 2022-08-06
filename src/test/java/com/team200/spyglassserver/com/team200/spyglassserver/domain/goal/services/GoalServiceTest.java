package com.team200.spyglassserver.com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.services.UserService;

import com.team200.spyglassserver.domain.user.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.crypto.Data;
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

    private User mockUser;
    private User mockUser2;
    private List<Goal> mockGoalList;

    @BeforeEach
    public void setUp() {
        mockUser = new User("test", "user", "daniel");
        mockUser.setId("test");

        mockUser2 = new User();
        mockUser2.setId("Test2");
        mockGoal = new Goal("test", "this is a test", new Date(),  new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        mockGoal.setId(1l);

        testGoal1 = new Goal("test2", "this another test", new Date(),  new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        testGoal1.setId(2l);

        mockGoalList = new ArrayList<>();
        mockGoalList.add(mockGoal);
        mockGoalList.add(testGoal1);

        owner = new User();
        owner.setId("ownerID");
    }


    @Test
    @DisplayName("Create Test - Success")
    public void create01() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        Goal createdGoal = goalService.create("test", mockGoal);
        Assertions.assertEquals(createdGoal.getId(), mockGoal.getId());
    }

    @Test
    @DisplayName("Create Test - Fail ")
    public void create02() {
        BDDMockito.doReturn(mockGoal).when(goalRepo).save(mockGoal);
        BDDMockito.doReturn(Optional.of(mockGoal)).when(goalRepo).findByTitle("test");

        Assertions.assertThrows(ResourceCreationException.class, () -> {
            Goal newGoal = goalService.create("test",mockGoal);
        });

    }

    @Test
    @DisplayName("Get All test - success")
    public void getAllTest01() {
        BDDMockito.doReturn(mockUser).when(userService).retrieveById("test");
        BDDMockito.doReturn(Optional.of(mockGoalList)).when(goalRepo).findByOwner(mockUser);
        List<Goal> goals = goalService.getAll(mockUser.getId());
        Assertions.assertEquals(goals, mockGoalList);
    }

<<<<<<< HEAD




=======
>>>>>>> 32e640ed83c6e231e686c7b9bf31c53bb9126a4c
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
    @DisplayName("Get By Id - Success")
    public void getGoalByIdTestSuccess() throws ResourceNotFoundException {
        BDDMockito.doReturn(Optional.of(mockGoal)).when(goalRepo).findById(1L);
        Goal foundGoal = goalService.getById(1L);
        Assertions.assertEquals(mockGoal,foundGoal);

    }
    @Test
    @DisplayName("Get BY Id - Failed")
    public void getGoalByIdTestFail(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            goalService.getById(1L);
        });

    }
    @Test
    @DisplayName("Update -Success")
    public void updateGoalTestSuccess()throws ResourceNotFoundException{
        Goal expectedGoalUpdate = new Goal("tes01", "this is a test", new Date(),  new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        expectedGoalUpdate.setId(1L);
        BDDMockito.doReturn(Optional.of(mockGoal)).when(goalRepo).findById(1L);
        BDDMockito.doReturn(expectedGoalUpdate).when(goalRepo).save(ArgumentMatchers.any());
        Goal actualgoal = goalService.update(1L,expectedGoalUpdate);
        Assertions.assertEquals(expectedGoalUpdate.toString(),actualgoal.toString());

    }
    @Test
    @DisplayName("Update-Fail")
    public void updateGoalTestFail(){
        Goal expectedGoalUpdate = new Goal("tes01", "this is a test", new Date(),  new Date(), 0.0, 0.0, CompletionStatus.COMPLETE, mockUser);
        expectedGoalUpdate.setId(1L);
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            goalService.update(1L,expectedGoalUpdate);
        });

    }
    @Test
    @DisplayName("Delete - sucess")
    public void deleteGoalTestSuccess() throws ResourceNotFoundException{
        BDDMockito.doReturn(Optional.of(mockGoal)).when(goalRepo).findById(1L);
        Boolean actualResponse = goalService.delete(1L);
        Assertions.assertTrue(actualResponse);

    }
    @Test
    @DisplayName("Delete- fail")
    public void deleteGoalFail(){
        BDDMockito.doReturn(Optional.empty()).when(goalRepo).findById(1L);
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
            goalService.delete(1L);
        });
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
