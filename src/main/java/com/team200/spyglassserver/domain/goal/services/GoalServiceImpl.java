package com.team200.spyglassserver.domain.goal.services;
import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.goal.repo.GoalRepo;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;
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
    public Goal update(Long id, Goal detaill) throws ResourceNotFoundException {
        Goal goal = getById(id);
        goal.setGoalStart(detaill.getGoalStart());
        goal.setStatus(detaill.getStatus());
        goal.setOwner(detaill.getOwner());
        goal.setDescription(detaill.getDescription());
        goal.setTitle(detaill.getTitle());
        goal.setCurrentAmount(detaill.getCurrentAmount());
        goal.setTargetDate(detaill.getTargetDate());
        goal.setTargetAmount(detaill.getTargetAmount());
        return goal;


    }

    public Goal getById(Long id) throws ResourceNotFoundException {
        Optional<Goal> goal =goalRepo.findById(id);
        if(goal.isEmpty()){
            throw new ResourceNotFoundException("the goal with this id is not found");
        }
        return goal.get();

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
    public List<Goal> getAllByStatus(String id, String statusString) throws ResourceNotFoundException {
        UserDTO owner = userService.getById(id);
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
    public boolean delete(Long id) throws ResourceNotFoundException {
     Optional<Goal>goalOptional =  goalRepo.findById(id);
     if(goalOptional.isEmpty()){
         throw new ResourceNotFoundException("the goal with that id is not found");
     }
     Goal goalToDelete = goalOptional.get();
     goalRepo.delete(goalToDelete);
     return true;

    }
    
    @Override
    public CompletionStatus getStatusEnum(String status){

        CompletionStatus returnStatus = null;
        switch (status){
            case "Not Started":
                returnStatus =  CompletionStatus.NOT_STARTED;
                break;
            case "In Progress":
                returnStatus = CompletionStatus.IN_PROGRESS;
                break;
            case "Complete":
                returnStatus = CompletionStatus.COMPLETE;
        }
        return returnStatus;
    }
}
