package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.core.exceptions.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;

import java.util.Date;
import java.util.Optional;

public interface GoalService {

    Goal create(Goal goal) throws ResourceCreationException;

    Goal update(Long id, Goal goal) throws ResourceNotFoundException;

   Goal getById(Long id) throws ResourceNotFoundException;

    Goal getAll(Long id) throws ResourceNotFoundException;

    Goal getAllByTargetDate(Date date) throws ResourceNotFoundException;
    Goal getAllByGoalDate(Long id) throws ResourceNotFoundException;

    Goal getAllByStatus(Status status) throws ResourceNotFoundException;
    Goal getByTargetAmount(Double start, Double end) throws ResourceNotFoundException;
    Goal update(Long id, Goal detail) throws ResourceNotFoundException

    void delete(Long id) throws ResourceNotFoundException;

}
