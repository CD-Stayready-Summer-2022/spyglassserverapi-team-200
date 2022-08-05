package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.enums.CompletionStatus;
import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;

import com.team200.spyglassserver.domain.goal.model.Goal;

import java.util.Date;
import java.util.List;

public interface GoalService {

    Goal create(Goal goal) throws ResourceCreationException;


    Goal update(Long id, Goal goal) throws ResourceNotFoundException;


    Goal getById(Long id) throws ResourceNotFoundException;


    List<Goal> getAll(String id) ;

    Goal getAllByTargetDate(Date date) throws ResourceNotFoundException;

    List<Goal> getAllByStatus(String id, String statusString) throws ResourceNotFoundException;
    List<Goal> getByTargetAmount(String id, Double start, Double end) throws ResourceNotFoundException;
    Boolean delete(Long id) throws ResourceNotFoundException;
}
