package com.team200.spyglassserver.domain.goal.repo;


import com.team200.spyglassserver.domain.core.enums.CompletionStatus;

import com.team200.spyglassserver.domain.goal.model.Goal;
import com.team200.spyglassserver.domain.user.dtos.UserDTO;

import com.team200.spyglassserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GoalRepo extends JpaRepository<Goal, Long> {


    Optional<Goal> findByTitle(String title);

    List<Goal> findByTargetDate(Date date);

    List<Goal>findByTargetAmount(Double targetAmount);

    List<Goal>findByCompletionStatus(CompletionStatus completionStatus);

    Optional<List<Goal>> findByOwner(User user);

}
