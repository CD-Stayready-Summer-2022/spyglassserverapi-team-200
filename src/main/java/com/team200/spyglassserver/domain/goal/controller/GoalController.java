package com.team200.spyglassserver.domain.goal.controller;


import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.services.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    private GoalService goalService;
    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/getByStatus/{id}/{status}")
    public ResponseEntity<List<Goal>> getByStatus(@PathVariable(name = "id") String id, @PathVariable(name = "status") String statusString){
        List<Goal> goals =  goalService.getAllByStatus(id, statusString);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("/getByTargetAmount")
    public ResponseEntity<List<Goal>> getByTargetAmount(@RequestParam(name = "id") String id, @RequestParam(name = "start") Double start, @RequestParam(name = "start") Double end) {
        List<Goal> goals = goalService.getByTargetAmount(id, start, end);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

}
