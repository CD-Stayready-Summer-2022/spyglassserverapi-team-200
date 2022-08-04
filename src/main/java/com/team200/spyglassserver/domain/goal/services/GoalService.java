package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.goal.model.Goal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoalService {

    Goal create(Goal goal) throws ResourceCreationException;

    Optional getById(Long id) throws ResourceNotFoundException;

    Goal getAll(Long id) throws ResourceNotFoundException;

    Goal getAllByTargetDate(Date date) throws ResourceNotFoundException;
    Goal getAllByGoalDate(Long id) throws ResourceNotFoundException;

    List<Goal> getAllByStatus(String id, String statusString) throws ResourceNotFoundException;
    Goal update(Long id, Goal detail) throws ResourceNotFoundException;
    List<Goal> getByTargetAmount(String id, Double start, Double end) throws ResourceNotFoundException;

    Boolean delete(Long id) throws ResourceNotFoundException;
    CompletionStatus getStatusEnum(String status);

}
