package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl {
    private GoalRepo goalrepo;
    private UserService userService;
@Autowired
    public GoalServiceImpl(GoalRepo goalrepo, UserService userService) {
        this.goalrepo = goalrepo;
        this.userService = userService;
    }

    public Goal getById(Long id) throws ResourceNotFoundException{
        Goal goal = goalrepo.findById(id);
                .orElseThrow(()-> new ResourceNotFoundException("a goal doesn't exist with that Id"));
        return goal;
    }
    public Goal update(Long id, Goal goalDetail){
    Goal goal = getById(id);
    goal.set



    }




}
