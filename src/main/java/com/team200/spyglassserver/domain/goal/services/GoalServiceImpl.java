package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.user.services.UserService;
import org.apache.catalina.User;
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

    @Override
    public Goal getById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Goal getAll(Long id) throws ResourceNotFoundException {
        return null;
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
    public List<Goal> getAllByStatus(String id, String statusString) throws ResourceNotFoundException {
        User owner = userService.getById(id);
        Optional<List<Goal>> optional = goalRepo.findByOwnerAndStatus(owner, getStatusEnum(statusString));
        if(optional.isEmpty())
            throw new ResourceNotFoundException("User has no goals of this status");
        return optional.get();
    }

    @Override
    public Goal getByTargetAmount(Double start, Double end) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws ResourceNotFoundException {
        return null;
    }
    @Override
    public Status getStatusEnum(String status){
        Status returnStatus = null;
        switch (status){
            case "Not Started":
                returnStatus =  Status.NOT_STARTED;
                break;
            case "In Progress":
                returnStatus = Status.IN_PROGRESS;
                break;
            case "Complete":
                returnStatus = Status.COMPLETE;
        }
        return returnStatus;
    }
}
