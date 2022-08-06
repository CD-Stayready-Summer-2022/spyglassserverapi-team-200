package com.team200.spyglassserver.domain.goal.controller;


import com.team200.spyglassserver.domain.goal.DTOS.StatusDTO;
import com.team200.spyglassserver.domain.goal.DTOS.TargetAmountDTO;
import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.services.GoalService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    private GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Goal>> getByStatus(@RequestBody StatusDTO status){
        List<Goal> goals =  goalService.getAllByStatus(status.getId(), status.getStatusString());
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }


    @PostMapping("create/{id}")
    public ResponseEntity<Goal> create(@PathVariable String id,  @RequestBody Goal goal) throws ResourceCreationException {
        Goal createdGoal = goalService.create(id, goal);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

<<<<<<< HEAD
    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        Goal goal = goalService.getById(id);
        return new ResponseEntity<>(goal, HttpStatus.OK);

    }

    @GetMapping("{id}/goals")
    public ResponseEntity<List<Goal>> getAll(@RequestParam String id) {
=======
    @GetMapping("{id}")
    public ResponseEntity<List<Goal>> getAll(@PathVariable(name = "id") String id) {
>>>>>>> 32e640ed83c6e231e686c7b9bf31c53bb9126a4c
        List<Goal> goals = goalService.getAll(id);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
    @GetMapping("/getByTargetAmount")
    public ResponseEntity<List<Goal>> getByTargetAmount(@RequestBody TargetAmountDTO targetAmountDTO) {
        List<Goal> goals = goalService.getByTargetAmount(targetAmountDTO.getId(), targetAmountDTO.getStart(), targetAmountDTO.getEnd());
        return new ResponseEntity<>(goals, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        goalService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable long id, @RequestBody Goal goal) {
        try {
            Goal updatedGoal = goalService.update(id, goal);
            ResponseEntity response = new ResponseEntity(updatedGoal, HttpStatus.OK);
            return response;
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
