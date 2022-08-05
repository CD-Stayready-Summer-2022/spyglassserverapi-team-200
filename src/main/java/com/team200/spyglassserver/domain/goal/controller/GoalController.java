package com.team200.spyglassserver.domain.goal.controller;


import com.team200.spyglassserver.domain.goal.DTOS.StatusDTO;
import com.team200.spyglassserver.domain.goal.DTOS.TargetAmountDTO;
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
    @Autowired
    private GoalService goalService;
    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Goal>> getByStatus(@RequestBody StatusDTO status){
        List<Goal> goals =  goalService.getAllByStatus(status.getId(), status.getStatusString());
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @GetMapping("/getByTargetAmount")
    public ResponseEntity<List<Goal>> getByTargetAmount(@RequestBody TargetAmountDTO targetAmountDTO) {
        List<Goal> goals = goalService.getByTargetAmount(targetAmountDTO.getId(), targetAmountDTO.getStart(), targetAmountDTO.getEnd());
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

}
