package com.team200.spyglassserver.domain.goal.controller;


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

    @GetMapping("/getByStatus/{id}/{status}")
    public ResponseEntity<List<Goal>> getByStatus(@PathVariable(name = "id") String id, @PathVariable(name = "status") String statusString) {
        List<Goal> goals = goalService.getAllByStatus(id, statusString);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }


    @PostMapping("create")
    public ResponseEntity<Goal> create(@RequestBody Goal goal) {
        Goal createdGoal = goalService.create(goal);
        return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
    }

    @GetMapping("{id}/goals")
    public ResponseEntity<List<Goal>> getAll(@RequestParam String id) {
        List<Goal> goals = goalService.getAll(id);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }


    @GetMapping("/getByTargetAmount")
    public ResponseEntity<List<Goal>> getByTargetAmount(@RequestParam(name = "id") String id, @RequestParam(name = "start") Double start, @RequestParam(name = "start") Double end) {
        List<Goal> goals = goalService.getByTargetAmount(id, start, end);
        return new ResponseEntity<>(goals, HttpStatus.OK);

    }
}


