package com.team200.spyglassserver.domain.goal.controller;

import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.services.GoalService;
import com.team200.spyglassserver.domain.user.model.User;
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

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Goal>> getByStatus(@RequestParam(name = "id") String id, @RequestParam(name = "status") String statusString){
        List<Goal> goals =  goalService.getAllByStatus(id, statusString);
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
