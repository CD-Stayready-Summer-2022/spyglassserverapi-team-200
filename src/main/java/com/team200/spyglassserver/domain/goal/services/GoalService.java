package com.team200.spyglassserver.domain.goal.services;

import com.team200.spyglassserver.domain.core.exceptions.ResourceCreationException;
import com.team200.spyglassserver.domain.core.exceptions.ResourceNotFoundException;
import com.team200.spyglassserver.domain.core.enums.Status;
import com.team200.spyglassserver.domain.goal.model.Goal;
import org.apache.catalina.User;

import java.util.Date;
import java.util.List;

public interface GoalService {

    Goal create(Goal goal) throws ResourceCreationException;

    Goal update(Long id, Goal goal) throws ResourceNotFoundException;

    Goal getById(Long id) throws ResourceNotFoundException;

    Goal getAll(Long id) throws ResourceNotFoundException;

    Goal getAllByTargetDate(Date date) throws ResourceNotFoundException;
    Goal getAllByGoalDate(Long id) throws ResourceNotFoundException;

    List<Goal> getAllByStatus(String id, String statusString) throws ResourceNotFoundException;
    Goal getByTargetAmount(Double start, Double end) throws ResourceNotFoundException;

    Boolean delete(Long id) throws ResourceNotFoundException;
    Status getStatusEnum(String status);

}
