package com.team200.spyglassserver.domain.goal.services;
import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.core.exceptions.enums.CompletionStatus;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.user.model.User;
import com.team200.spyglassserver.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {
    private GoalRepo goalRepo;
    private UserService userService;


    @Autowired
    public GoalServiceImpl(GoalRepo goalRepo, UserService userService) {
        this.goalRepo = goalRepo;
        this.userService = userService;
    }

    @Override
    public Goal create(Goal goal) throws ResourceCreationException {
        Optional<Goal> goalOptional  = goalRepo.findByTitle(goal.getTitle());
        if(goalOptional.isPresent()) {
            throw new ResourceCreationException();
        }
        return goalRepo.save(goal);
    }



    @Override
    public Goal update(Long id, Goal goal) throws ResourceNotFoundException {
        return null;
    }

    public Optional getById(Long id) throws ResourceNotFoundException {
        Optional goal = goalRepo.findById(id);
        if(goal.isEmpty()){
            throw new ResourceNotFoundException("this goal doesn' exist by that id");
        }
        return goal;
    }

    @Override
    public List<Goal> getAll(String id) throws ResourceNotFoundException {
        User user = userService.retrieveById(id);
        List<Goal> goals = goalRepo.findByOwner(user);
        if(goals.size() == 0) {
            throw new ResourceNotFoundException(String.format("User with id %d has no goals",id));
        }
        return goals;
    }


    @Override
    public Goal getAllByTargetDate(Date date) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Goal getAllByGoalDate(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Goal getAllByStatus(CompletionStatus status) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Goal getByTargetAmount(Double start, Double end) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {
        return null;
    }

}
